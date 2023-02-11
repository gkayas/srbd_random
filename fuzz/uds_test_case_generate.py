import random
import math
from eae_fuzz.fuzzer.can.utils.can_frame_utils import map_dlc_to_len, map_len_to_dlc
from itertools import product
from eae_fuzz.fuzzer.test_cases.can_utils.test_generation_utils import ( generate_random_uds_single_frame, generate_random_uds_multi_frame, generate_uds_suppress_bit_frame,
generate_pattern_based_uds_frame, generate_invalid_dlc_uds, build_sid_23_payload)
from collections import defaultdict
from auth import check_trial_limits
from eae_fuzz.fuzzer.test_cases.can_utils.test_case import TestCase, TestCases
import eae_fuzz.fuzzer.test_cases.can_uds.uds_constants as uds_constants
from eae_fuzz.fuzzer.test_cases.can_utils.test_case_generator import CANTestCaseGenerator
import typing


TARGET_ARBID = None
iso_sids_list = uds_constants.ISO14229_SIDS
USING_EXTENDED_ADDR = False
SOURCE_BYTE = None
BYTES_TO_FOLLOW_UDS = [0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x10, 0x12, 0x13, 0x14, 0x18, 0x20, 0x32, 0xFF, 0x100, 0x180, 0x200] # describes the length of UDS payload
BYTES_TO_FOLLOW_UDS_CAN_FD = [0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x10, 0x11, 0x12, 0x13] + [i for i in range(0x14, 0x20)]
TEST_CASE_PATTERN_BYTES = [0x00, 0x01, 0x0E, 0x0F, 0x10, 0x7E, 0x7F, 0x80, 0xFE, 0xFF]
PAD_TEST_CASES = True
CLASSIC_CAN_DLCS = [1, 2, 3, 4, 5, 6, 7, 8]
CAN_FD_DLCS = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15]
MAX_NUM_PSEUDO_RANDOM_TC_PER_SID = 2000
MAX_NUM_SUPPRESS_TC_PER_SID = 3
MAX_NUM_INVALID_DLC_TC_PER_SID = 2

class CANUDSTestCaseGroupCounts():
    def __init__(self, num_29_bit_rand:int=100, 
        num_pseudo_random_iso_btf_test_cases:int=100000) -> None:
        # 11-bit Arb-ID scan TCs
        self.arbid_11_bit_scan_index = 0
        
        # 29-bit Randomized Arb-ID TCs 
        self.num_29bit_arbid_rand_test_cases = num_29_bit_rand 
        self.arbid_29bit_rand_tcs_counter = 0
        
        
        # 29-bit Target Arb-ID single byte mutation test cases
        self.mutation_arbid_29_bit_current_byte = 0
        
        # 29-bit Target Arb-ID double byte mutation test cases
        self.bytes_1_2_arbid_mutation_counter = 0
        self.bytes_3_4_arbid_mutation_counter = 0

        # Pseudo random test cases (w/ valid ISO-14229 BTF)
        self.num_pseudo_random_tcs = num_pseudo_random_iso_btf_test_cases
        self.pseudo_random_current_sid_index = 0
        self.pseudo_random_tcs_counter = 0
        self.pseudo_random_sid_permutation_bytes_manipulation_index = 0

        # Mixed Randomized test cases : Random_single_frame, Random_multiframe, Suppress_bit, Pattern_based, Pseudo_random_pattern, Invalid_dlc
        self.invalid_dlc_tc_tracking = defaultdict(int)
        self.pattern_based_tracking = defaultdict(int)
        self.pseudo_rand_pattern_based_sid_tracking = defaultdict(int)
        self.suppress_bit_tc_tracking = defaultdict(int)

        

class SIDPseudoRandomBytesPermutationGroup():
    def __init__(self, sid, bytes_to_iterate, bytes_to_follow) -> None:
        self.sid = sid
        bytes_list_range = []
        for _ in range(0, bytes_to_iterate):
            if sid == 0x11:
                bytes_list_range.append(list(range(0x04, 0xFF + 1))) # exclude ECU resets (0x11 service ID)
            else:
                bytes_list_range.append(list(range(0x0, 0xFF + 1)))
        self.bytes_to_iterate = bytes_to_iterate
        self.bytes_to_follow = bytes_to_follow
        self.byte_manipulations = list(product(*bytes_list_range))
        random.shuffle(self.byte_manipulations)
                
class CANUDSTestCaseGenerator(CANTestCaseGenerator):
    def __init__(self, configurations:dict) -> None:
        super().__init__(configurations = configurations)
        self.fuzzing_type = 'UDS fuzzing'
        self.test_cases = TestCases(fuzzing_type = self.fuzzing_type, are_rerun_test_cases = False, total_amount_of_test_cases = self.total_number_of_test_cases)
        self.messages_2E_index = 0
        # Target info
        self.targets = configurations.get('targets', [])
        self.target_addressing_mode = self.targets[0].get("attributes", {}).get('addressing_type', 'normal')
        self.source_byte = 0x0
        if self.target_addressing_mode == 'extended':
            self.source_byte = int(self.targets[0].get('source_address'), 16)
        if self.target_addressing_mode == 'mixed':
            self.source_byte = int(self.targets[0].get('address_extension'), 16)
        # Generator configurations
        self.fd_enabled = configurations.get('fd_enabled', False)
        self.input_file_path = configurations.get('input_file_path', None)
        self.dids = configurations.get('dids', [])
        self.rids = configurations.get('rids', [])
        self.messages2E = configurations.get('messages2E')
        self.test_groups_dict = {}
        self.target_arbid = None
        self.init_target_arbid()
        self.test_group_name = 'UDS'
        self.include_arbid_fuzzing = configurations.get('include_arbid_fuzzing', True)
        self.arbid_start_range = configurations.get('arbid_scan_start_range', 0x0)
        self.arbid_end_range = configurations.get('arbid_scan_end_range', 0x7ff)
        self.arbid_extended_start_range = configurations.get('arbid_scan_extended_start_range', 0x0)
        self.arbid_extended_end_range = configurations.get('arbid_scan_extended_end_range', 0x1FFFFFFF)
        self.rand_29bit_arbid_range_start = configurations.get('rand_29bit_arbid_range_start', 0x800)
        self.rand_29bit_arbid_range_end = configurations.get('rand_29bit_arbid_range_end', 0x1FFFFFFF)
        self.arbid_29_bit_single_byte_mutation_scan_range_start = 0x0
        self.arbid_29_bit_single_byte_mutation_scan_range_end = 0xFF + 1
        self.arbid_29_bit_single_byte_mutation_scan_range_for_msb_byte_end = 0x1F + 1
        # Form Arb-ID mutations for each byte, such that mutated byte is different from original Arb-ID byte at that position
        self.arbid_29_bit_single_byte_mutations = [
            [i for i in range(self.arbid_29_bit_single_byte_mutation_scan_range_start, self.arbid_29_bit_single_byte_mutation_scan_range_for_msb_byte_end) if ''.join(['{0:02X}'.format(i), self.target_arbid_str[2:]]) != self.target_arbid_str], #for the MSB byte we  do not want to make is more than 1F as "1FFFFFFF" is maximum possible value 
            [i for i in range(self.arbid_29_bit_single_byte_mutation_scan_range_start, self.arbid_29_bit_single_byte_mutation_scan_range_end) if ''.join([self.target_arbid_str[:2],'{0:02X}'.format(i), self.target_arbid_str[4:]]) != self.target_arbid_str],
            [i for i in range(self.arbid_29_bit_single_byte_mutation_scan_range_start, self.arbid_29_bit_single_byte_mutation_scan_range_end) if ''.join([self.target_arbid_str[:4],'{0:02X}'.format(i), self.target_arbid_str[6:]]) != self.target_arbid_str],
            [i for i in range(self.arbid_29_bit_single_byte_mutation_scan_range_start, self.arbid_29_bit_single_byte_mutation_scan_range_end) if ''.join([self.target_arbid_str[:6],'{0:02X}'.format(i)]) != self.target_arbid_str]
        ]
        # Arb-ID mutation TCs
        self.single_byte_arbid_mutations = None
        self.single_byte_addr_extension_mutations = None
        if self.target_arbid < 0x800:
            self.single_byte_arbid_mutations = [i for i in range(0, 0xFF + 1) if ''.join([self.target_arbid_str[0], '{0:02X}'.format(i)])!=self.target_arbid_str]
            self.single_byte_addr_extension_mutations = self.single_byte_arbid_mutations.copy()
        
        bytes_list_1_2_byte_range = []
        bytes_list_3_4_byte_range = []
        bytes_list_range = range(0x00, 0xff+1)

        bytes_list_1_2_byte_range.append(range(0x00, 0x1f+1))
        bytes_list_1_2_byte_range.append(bytes_list_range)

        bytes_list_3_4_byte_range.append(bytes_list_range)
        bytes_list_3_4_byte_range.append(bytes_list_range)
        
        bytes_1_2_permutations = product(*bytes_list_1_2_byte_range)
        bytes_3_4_permutations = product(*bytes_list_3_4_byte_range)
        self.double_byte_1_2_arbid_mutation_chunks = [''.join(['{0:02X}'.format(byte) for byte in p]) for p in bytes_1_2_permutations]
        random.shuffle(self.double_byte_1_2_arbid_mutation_chunks )
        self.double_byte_3_4_arbid_mutation_chunks = [''.join(['{0:02X}'.format(byte) for byte in p]) for p in bytes_3_4_permutations]
        random.shuffle(self.double_byte_3_4_arbid_mutation_chunks )
        self.selected_iso_sids_list = configurations.get('iso_sids_settings', list(uds_constants.ISO_DICTIONARY.keys()))
        if not self.selected_iso_sids_list:
            raise ValueError("No UDS Services selected for UDS generation")
        if len(self.selected_iso_sids_list) > 0:
            self.selected_iso_sids_list[:] = [int(sid, 16) for sid in self.selected_iso_sids_list]
        # Pseudo-Random byte manipulation test group
        self.pseudo_random_iso_btf_test_cases_enabled = configurations.get('pseudo_random_iso_btf_test_cases_enabled', True)
        self.pseudo_random_sid_permutations = []
        total_pseudo_random_tcs = 0
        if self.pseudo_random_iso_btf_test_cases_enabled:
            for sid in self.selected_iso_sids_list:
                service_info = uds_constants.ISO_SERVICES_VALID_BTF[sid]
                for bytes_to_follow in service_info[0]:
                    for bytes_to_iterate in service_info[1]:
                        pseudo_random_sid_group = SIDPseudoRandomBytesPermutationGroup(sid = sid, bytes_to_iterate=bytes_to_iterate, bytes_to_follow=bytes_to_follow)
                        total_pseudo_random_tcs += len(pseudo_random_sid_group.byte_manipulations)
                        self.pseudo_random_sid_permutations.append(pseudo_random_sid_group)
        self.addr_extension_src_bytes = [i for i in range(0x0, 0xFF + 1)]
        # Mixed randomized test groups
        self.random_single_frame_test_cases_enabled = configurations.get('random_single_frame_test_cases_enabled', True)
        self.random_multi_frame_test_cases_enabled = False
        self.suppress_bit_test_cases_enabled = configurations.get('suppress_bit_test_cases_enabled', True)
        self.pattern_based_test_cases_enabled = configurations.get('pattern_based_test_cases_enabled', True)
        self.pseudo_random_pattern_based_test_cases_enabled = configurations.get('pseudo_random_pattern_based_test_cases_enabled', True)
        self.invalid_dlc_test_cases_enabled = False
        self.suppress_bit_capable_sids = [sid for sid in uds_constants.SERVICES_WITH_SUB_FUNCTION if sid in self.selected_iso_sids_list]
        self.random_single_frame_dlcs = CLASSIC_CAN_DLCS if not self.fd_enabled else CAN_FD_DLCS
        self.suppress_bit_dlcs = self.random_single_frame_dlcs.copy()
        self.mixed_test_groups_generator_functions = self.load_mixed_randomized_test_cases_groups()
        self.mixed_randomized_groups_max_payload_len = 7 - int(self.target_addressing_mode == 'extended') if not self.fd_enabled else 62 - int(self.target_addressing_mode == 'extended')
        self.mixed_randomized_groups_bytes_to_follow_list = [btf for btf in BYTES_TO_FOLLOW_UDS if btf <= self.mixed_randomized_groups_max_payload_len]
        self.pseudo_random_pattern_capable_sids = self.selected_iso_sids_list.copy()
        self.invalid_dlc_capable_sids = self.selected_iso_sids_list.copy()
        self.pattern_based_dlcs_for_sid = { sid: [3, 4, 5, 6, 7, 8].copy() for sid in self.selected_iso_sids_list} if not self.fd_enabled else { sid: [3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15].copy() for sid in self.selected_iso_sids_list }
        self.pattern_based_capable_sids = self.selected_iso_sids_list.copy()
        # Test group counters & indexes
        self.test_group_counts = CANUDSTestCaseGroupCounts(num_29_bit_rand=configurations.get('num_29bit_arbid_rand_test_cases', 100), num_pseudo_random_iso_btf_test_cases=total_pseudo_random_tcs)
        # Generator functions (step in after 11-bit Arb-ID scan)
        self.generator_functions = [self.generate_mixed_randomized_test_case]
        if self.include_arbid_fuzzing:
            self.generator_functions.append(self.generate_arbid_randomized_test_case)
            if self.source_byte:
                self.generator_functions.append(self.generate_address_extension_mutation_test_case)
            if self.target_arbid < 0x800:
                # 11-bit Arb-ID
                self.generator_functions.append(self.generate_arbid_and_addr_mutation_test_case)
            else:
                # 29-bit Arb-ID
                self.generator_functions.extend([
                    self.generate_29_bit_arbid_single_byte_mutation_test_case,
                    self.generate_bytes_3_4_permutated_arbid_test_case,
                    self.generate_bytes_1_2_permutated_arbid_test_case
                ])
        if self.pseudo_random_iso_btf_test_cases_enabled:
            self.generator_functions.append(self.generate_pseudo_random_test_case)


    def init_target_arbid(self):
        '''
        Initializes target CAN Arbitration ID by reading target's stored attribute arbid
        '''
        target_arbid_hex_str = self.targets[0].get('attributes', {}).get('arbid', None)
        if target_arbid_hex_str and isinstance(target_arbid_hex_str, str):
            self.target_arbid = int(target_arbid_hex_str, 16)
            self.target_arbid_str = '{0:03X}'.format(self.target_arbid)
            arbid_4_byte = '{0:08X}'.format(self.target_arbid)
            self.target_arbid_bytes = list(filter(lambda x : x != '00', [arbid_4_byte[i:i+2] for i in range(0, len(arbid_4_byte), 2)]))
    
    def update_test_groups(self, test_group_name):
        '''
        Updates test groups dict by updating test group counter for test_group_name
        :param test_group_name: name of the updated test group
        '''
        if self.test_groups_dict.get(test_group_name) != None:
            self.test_groups_dict[test_group_name] += 1
        else:
            self.test_groups_dict[test_group_name] = 0



    def generate_messages2E_test_case(self):
        '''
        Generates and returns a single 2E test case
        '''
        m = self.messages2E[self.messages_2E_index]
        self.messages_2E_index = (self.messages_2E_index + 1) % len(self.messages2E)
        mlist = m[0].split() # split into a list of strings
        mlist = [int(byte, 16) for byte in mlist] # convert list of strings to list of ints one byte at a time
        iso_tp_frames = []
        if len(m[1]) > 0:
            for frame in m[1]:
                frame_list = [int(byte, 16) for byte in frame]
                iso_tp_frames.append(frame_list)
        return TestCase(test_case_name="replay", test_group_name="UDS Write Data By Identifier (DIDs)", arbid=self.target_arbid, dlc=map_len_to_dlc(len(mlist)), data=mlist, 
                            test_case_number=self.current_test_case_index, isotp_frames=iso_tp_frames)



    def generate_arbid_scan_test_case(self):
        '''
        Generates single 11-bit Arb-ID scan test case
        '''
        payload = [0x22, 0xf1, 0x90]

        tc = TestCase(test_case_name="11_bit_Arb-ID_scan_invalid_frame",
            arbid = self.test_group_counts.arbid_11_bit_scan_index, 
            data = payload,
            dlc = len(payload), 
            is_invalid_frame=False, 
            test_case_number=self.current_test_case_index, 
            test_group_name='Arb_ID_fuzzing')

        self.test_group_counts.arbid_11_bit_scan_index += 1
        return tc


    def generate_arbid_randomized_test_case(self):
        '''
        Generates a single randomized 29bit Arb-ID based test cases within given Arb-ID range
        '''
        payload = [0x22, 0xf1, 0x90]
        tc = TestCase(test_case_name="Random_29_bit_Arb-ID_invalid_frame", 
            arbid=random.randint(self.rand_29bit_arbid_range_start, self.rand_29bit_arbid_range_end), 
            data = payload, 
            dlc = len(payload),
            is_invalid_frame=False, 
            test_case_number = self.current_test_case_index, 
            test_group_name="Arb_ID_fuzzing")

        self.test_group_counts.arbid_29bit_rand_tcs_counter += 1
        if self.test_group_counts.arbid_29bit_rand_tcs_counter == self.test_group_counts.num_29bit_arbid_rand_test_cases:
            self.generator_functions.remove(self.generate_arbid_randomized_test_case)
        return tc
    


    def generate_address_extension_mutation_test_case(self):
        '''
        Generates a single manipulated address extension byte test case  (for targets with extended addressing mode)
        '''
        mutated_src_byte = random.choice(self.addr_extension_src_bytes)
        payload = [0x22, 0xf1, 0x90]
        
        test_case = TestCase(test_case_name="Address_extension_mutation_invalid_frame", 
            arbid = self.target_arbid, 
            data = payload,
            dlc = len(payload), 
            is_invalid_frame = False,
            test_case_number = self.current_test_case_index, 
            test_group_name = "Arb_ID_fuzzing", 
            source_byte = mutated_src_byte)
        
        self.addr_extension_src_bytes[:] = [src_byte for src_byte in self.addr_extension_src_bytes if src_byte != mutated_src_byte]
        if len(self.addr_extension_src_bytes) == 0:
            self.generator_functions.remove(self.generate_address_extension_mutation_test_case)
        
        return test_case



    def generate_arbid_and_addr_mutation_test_case(self):
        '''
        Generates a single test case with mutated CAN Arbitration IDs and address extensions
        '''
        target_arbid_str = '{0:03X}'.format(self.target_arbid)
        target_arbid_byte1 = target_arbid_str[0]
        arbid_mutation_byte = random.choice(self.single_byte_arbid_mutations)
        addr_ext_byte = random.choice(self.single_byte_addr_extension_mutations)
        arbid_mutation = '{0:02X}'.format(arbid_mutation_byte)
        new_arbid = target_arbid_byte1 + arbid_mutation
        arbid = int(new_arbid, 16)
        payload = [0x22, 0xf1, 0x90]

        self.single_byte_arbid_mutations[:] = [byte_mutation for byte_mutation in self.single_byte_arbid_mutations if byte_mutation != arbid_mutation_byte]
        self.single_byte_addr_extension_mutations[:] = [[byte_mutation for byte_mutation in self.single_byte_addr_extension_mutations if byte_mutation != addr_ext_byte]]

        if len(self.single_byte_addr_extension_mutations)==0 or len(self.single_byte_addr_extension_mutations) ==0:
            self.generator_functions.remove(self.generate_arbid_and_addr_mutation_test_case)

        return TestCase(test_case_name="ID_and_address_extension_mutations_invalid_frame", 
            arbid=arbid, 
            data=payload,
            dlc = len(payload),
            is_invalid_frame=False,
            test_case_number=self.current_test_case_index, 
            test_group_name="Arb-ID_and_address_extension_mutations_invalid_frame", 
            source_byte=addr_ext_byte)
    


    def generate_bytes_3_4_permutated_arbid_test_case(self):
        '''
        Generates and returns a single test case with CAN Arbitration ID bytes 3 and 4 permutated
        '''
        arb_id_chunk = self.double_byte_3_4_arbid_mutation_chunks[self.test_group_counts.bytes_3_4_arbid_mutation_counter]
        new_arbid_str = self.target_arbid_bytes[0] + self.target_arbid_bytes[1] + arb_id_chunk
        new_arbid = int(new_arbid_str, 16)
        payload = [0x22, 0xf1, 0x90]

        test_case = TestCase(test_case_name="29_bit_Arb-ID_bytes_3_4_mutation_invalid_frame", 
            arbid=new_arbid, 
            data = payload,
            dlc = len(payload),
            is_invalid_frame = False, 
            test_case_number = self.current_test_case_index, 
            test_group_name = "Arb_ID_fuzzing")

        self.test_group_counts.bytes_3_4_arbid_mutation_counter +=1
        if self.test_group_counts.bytes_3_4_arbid_mutation_counter == len(self.double_byte_3_4_arbid_mutation_chunks):
            self.generator_functions.remove(self.generate_bytes_3_4_permutated_arbid_test_case)
            # Re-shuffle double byte Arb-ID mutation chunks to prepare for bytes 1 & 2 mutation test cases
            random.shuffle(self.double_byte_3_4_arbid_mutation_chunks)
        return test_case
    


    def generate_bytes_1_2_permutated_arbid_test_case(self):
        '''
        Generates and returns a single test case with CAN Arbitration ID bytes 1 and 2 permutated
        '''
        arb_id_chunk = self.double_byte_1_2_arbid_mutation_chunks[self.test_group_counts.bytes_1_2_arbid_mutation_counter]
        new_arbid_str = arb_id_chunk + self.target_arbid_bytes[2] + self.target_arbid_bytes[3]
        new_arbid = int(new_arbid_str, 16)
        payload = [0x22, 0xf1, 0x90]

        test_case = TestCase(test_case_name="29_bit_Arb-ID_bytes_1_2_mutation_invalid_frame", 
            arbid = new_arbid, 
            data = payload,
            dlc = len(payload), 
            is_invalid_frame = False, 
            test_case_number = self.current_test_case_index, 
            test_group_name="Arb_ID_fuzzing")

        self.test_group_counts.bytes_1_2_arbid_mutation_counter +=1
        if self.test_group_counts.bytes_1_2_arbid_mutation_counter == len(self.double_byte_1_2_arbid_mutation_chunks):
            self.generator_functions.remove(self.generate_bytes_1_2_permutated_arbid_test_case)
        return test_case


    
    def generate_29_bit_arbid_single_byte_mutation_test_case(self):
        # Collect all Target Arb-ID bytes that aren't currently manipulated byte
        new_arbid_str = [self.target_arbid_bytes[byte_index] if byte_index != self.test_group_counts.mutation_arbid_29_bit_current_byte else '' for byte_index in range(0, len(self.target_arbid_bytes))]
        current_byte_mutations = self.arbid_29_bit_single_byte_mutations[self.test_group_counts.mutation_arbid_29_bit_current_byte]
        mutation_byte = random.choice(current_byte_mutations)
        # Set value for currently manipulated byte
        new_arbid_str[self.test_group_counts.mutation_arbid_29_bit_current_byte] = '{0:02X}'.format(mutation_byte)
        new_arbid_str = ''.join(new_arbid_str)
        new_arbid = int(new_arbid_str, 16)
        payload = [0x22, 0xf1, 0x90]

        tc = TestCase(test_case_name=f'29_bit_Arb-ID_byte_{self.test_group_counts.mutation_arbid_29_bit_current_byte+1}_mutation_invalid_frame', 
            arbid = new_arbid, 
            data = payload,
            dlc = len(payload), 
            is_invalid_frame = False, 
            test_case_number = self.current_test_case_index, 
            test_group_name = 'Arb_ID_fuzzing')

        # Go to next byte if all desired (in range min->max manipulation scan values) byte scan values used
        current_byte_mutations[:] = [b for b in current_byte_mutations if b!=mutation_byte]
        if len(current_byte_mutations) ==0:
            self.test_group_counts.mutation_arbid_29_bit_current_byte += 1
            if self.test_group_counts.mutation_arbid_29_bit_current_byte == 4:
                self.generator_functions.remove(self.generate_29_bit_arbid_single_byte_mutation_test_case)
        return tc
    

    def generate_pseudo_random_test_case(self):
        '''
        Generates a single Pseudo-random scan UDS test case based on selected UDS services:
        Meaning: generates n [0x00-0xFF for 1st byte in combo w/ 0x00-0xFF for 2nd byte etc.; 0 , except for SID 0x11 where range is 0x04-0xFF] permutations for y [1,2,3...7] bytes to iterate ((which bytes to include in permutations)) of x[2,3,..7] bytes to follow for a single service
        '''
        payload = []
        permutation_group = self.pseudo_random_sid_permutations[self.test_group_counts.pseudo_random_current_sid_index]
        bytes_mutation = permutation_group.byte_manipulations[self.test_group_counts.pseudo_random_sid_permutation_bytes_manipulation_index]
        
        # Add Service ID to payload
        payload.append(permutation_group.sid)
        if permutation_group.sid == 0x31:
            payload.append(0x01)
        iteration = list(bytes_mutation)
        payload += iteration
        if permutation_group.sid == 0x23:
            payload = build_sid_23_payload(payload[1], using_ext_addr=int(self.target_addressing_mode=='extended'), fd=self.fd_enabled)
        else:
            while len(payload) < permutation_group.bytes_to_follow + 1 and len(payload) < 8:
                if permutation_group.bytes_to_follow > permutation_group.bytes_to_iterate + 1 and permutation_group.sid != 0x31:
                    payload.append(random.randint(0, 255))
                else:
                    payload.append(0x00)
        
        test_case_name = "Pseudo_random_scan_"
        for idx, byte in enumerate(bytes_mutation):
            test_case_name += 'Subbyte' + str(idx+1) + '_' + '{0:02X}'.format(byte)
            if idx != len(bytes_mutation):
                test_case_name+= "_"
        test_group_name = '$' + '{0:02X}'.format(permutation_group.sid) + ' ' + str(uds_constants.ISO_DICTIONARY_INT_KEYS[permutation_group.sid])

        test_case = TestCase(test_case_name=test_case_name[:-1], 
            test_group_name=test_group_name, 
            arbid=self.target_arbid, 
            data=payload,
            dlc=len(payload),  
            test_case_number=self.current_test_case_index)

        self.update_test_groups(uds_constants.ISO_DICTIONARY[hex(permutation_group.sid).upper().replace('X', 'x')])

        self.test_group_counts.pseudo_random_sid_permutation_bytes_manipulation_index += 1
        if self.test_group_counts.pseudo_random_sid_permutation_bytes_manipulation_index == len(permutation_group.byte_manipulations):
            self.test_group_counts.pseudo_random_sid_permutation_bytes_manipulation_index = 0
            self.test_group_counts.pseudo_random_current_sid_index += 1                
        
        self.test_group_counts.pseudo_random_tcs_counter +=1
        if self.test_group_counts.pseudo_random_tcs_counter == self.test_group_counts.num_pseudo_random_tcs:
            self.generator_functions.remove(self.generate_pseudo_random_test_case)
        
        return test_case

    
    def load_mixed_randomized_test_cases_groups(self):
        '''
        Returns a list of selected test case groups from mixed randomized test case groups based on input configurations
        '''
        selected_mixed_randomized_test_groups_list = []
        if self.random_single_frame_test_cases_enabled:
            selected_mixed_randomized_test_groups_list.append(self.generate_random_single_frame_test_case)
        if self.random_multi_frame_test_cases_enabled:
            selected_mixed_randomized_test_groups_list.append(self.generate_random_multiframe_test_case)
        if self.suppress_bit_test_cases_enabled:
            if len(self.suppress_bit_capable_sids) > 0:
                selected_mixed_randomized_test_groups_list.append(self.generate_suppress_bit_test_case)
        if self.pattern_based_test_cases_enabled:
            selected_mixed_randomized_test_groups_list.append(self.generate_pattern_based_test_case)
        if self.pseudo_random_pattern_based_test_cases_enabled:
            selected_mixed_randomized_test_groups_list.append(self.generate_pseudo_random_pattern_based_test_case)
        if self.invalid_dlc_test_cases_enabled:
            if not self.fd_enabled:
                selected_mixed_randomized_test_groups_list.append(self.generate_invalid_dlc_test_case)
        return selected_mixed_randomized_test_groups_list



    def build_sid_23_payload(self, address_length_id:int=0, pattern_byte_val:int=None) -> typing.List[int]:
        '''
        Calculates targeted SID $23 CAN payload size, builds, and returns properly sized payload
        :param address_length_id: embeds memory address and size value for a ReadMemoryByAddress (SID 23) request
        :param pattern_byte_val: pattern payload bytes - used if this function called from pattern based test case generation context
        '''
        # Calculate targeted payload length based on 
        mem_address = address_length_id & 0x0F
        mem_size = address_length_id >> 4 & 0xF
        bytes_to_add = mem_address + mem_size
        payload_length_targeted = bytes_to_add + 2
        payload = [0x23, address_length_id]
        while len(payload) < payload_length_targeted:
            if pattern_byte_val is None:
                payload.append(random.randint(0x0, 0xFF))
            else:
                payload.append(pattern_byte_val)
        return payload



    def build_sid_2c_payload(self, payload_min_length:int=1) -> typing.List[int]:
        '''
        Builds CAN Payload for UDS Service $2C (Dynamically Define Data Identifier)
        :param payload_min_length: minimal number of bytes for the generated payload
        '''
        payload = [0x2c, random.randint(0x00, 0xff), random.randint(0xf2, 0xf3)]
        while len(payload) < payload_min_length:
            payload.append(random.randint(0x0, 0xFF))
        return payload


    
    def generate_random_single_frame_test_case(self) -> TestCase:
        '''
        Generates a UDS randomized single frame test case using provided UDS SID (Service ID)
        '''
        sid = random.choice(self.selected_iso_sids_list)
        dlc = random.choice(self.random_single_frame_dlcs)
        targeted_payload_length = dlc if not self.fd_enabled else map_dlc_to_len(given_dlc = dlc, fd = True)
        payload = [ sid ] # initialize payload bytes w/ the UDS SID at the first byte
        byte_after_sid = 0
        byte_after_sid_randomization_range_min = 0x0
        byte_after_sid_randomization_range_max = 0xFF
        
        # Special handling of byte that comes after the SID byte for UDS Service 0x23
        if sid == 0x23:
            # Limit the byte after sid to follow the payload length as this byte is the addressAndLengthFormatIdentifier
            # that specifies length of bytes of the memory size and memory address parameters.
            addr_length_format_identifiers = [0x11, 0x12, 0x13, 0x14, 0x21, 0x22, 0x23]
            if self.target_addressing_mode == "extended" and not self.fd_enabled:
                addr_length_format_identifiers.remove(0x14)
                addr_length_format_identifiers.remove(0x23)
            byte_after_sid = random.choice(addr_length_format_identifiers) if not self.fd_enabled else random.choice(addr_length_format_identifiers.append(0x24))
        else: 
            if sid == 0x11:
                # Special handling of byte that comes after the SID byte for UDS Service 0x11 - to avoid using valid ECU Reset codes 11 01, 11 02, 11 03
                byte_after_sid_randomization_range_min = 4
            byte_after_sid = random.randint(byte_after_sid_randomization_range_min, byte_after_sid_randomization_range_max)
            # Ensure that byte_after_sid doesn't have "Suppress Positive Response Message Indication Bit"
            while byte_after_sid - 0x80 >= 0 or int(byte_after_sid - 0x80).bit_length() == 8:
                byte_after_sid = random.randint(byte_after_sid_randomization_range_min, byte_after_sid_randomization_range_max)

        if len(payload) + 1 <= targeted_payload_length:
            payload.append(byte_after_sid)
            if sid == 0x23:
                mem_address = byte_after_sid & 0x0F
                mem_size = byte_after_sid >> 4 & 0xF
                bytes_to_add = mem_address + mem_size
                if len(payload) + bytes_to_add <= targeted_payload_length:
                    payload = self.build_sid_23_payload(address_length_id = byte_after_sid)

            while len(payload) < targeted_payload_length:
                payload.append(random.randint(0x0, 0xFF))

        return TestCase(test_case_name = "Random_single_frame",
            test_group_name = '$' + '{0:02X}'.format(sid) + ' ' + str(uds_constants.ISO_DICTIONARY_INT_KEYS[sid]),
            arbid = self.target_arbid,
            data = payload,
            dlc = len(payload) if not self.fd_enabled else map_len_to_dlc(len(payload)),
            test_case_number = self.current_test_case_index)                     



    def generate_random_multiframe_test_case(self) -> TestCase:
        '''
        Generates a UDS randomized multi-frame test case using provided UDS SID (Service ID)
        '''
        sid = random.choice(self.selected_iso_sids_list)
        dlc = random.randint(9, 15) if not self.fd_enabled else random.choice([128, 192, 256])
        if not self.fd_enabled: 
            targeted_payload_length = map_dlc_to_len(dlc, fd = True)
        payload = [ sid ] # initialize payload bytes w/ the UDS SID at the first byte
        byte_after_sid = 0
        byte_after_sid_randomization_range_min = 0x0
        byte_after_sid_randomization_range_max = 0xFF
        
        # Special handling of byte that comes after the SID byte for UDS Service 0x23
        if sid == 0x23:
            # Limit the byte after sid to follow the payload length as this byte is the addressAndLengthFormatIdentifier
            # that specifies length of bytes of the memory size and memory address parameters.
            addr_length_format_identifiers = [0x11, 0x12, 0x13, 0x14, 0x21, 0x22, 0x23]
            if self.target_addressing_mode == "extended" and not self.fd_enabled:
                addr_length_format_identifiers.remove(0x14)
                addr_length_format_identifiers.remove(0x23)
            byte_after_sid = random.choice(addr_length_format_identifiers) if not self.fd_enabled else random.choice(addr_length_format_identifiers.append(0x24))
        else:
            if sid == 0x11:
                # Special handling of byte that comes after the SID byte for UDS Service 0x11 - to avoid using valid ECU Reset codes 11 01, 11 02, 11 03
                byte_after_sid_randomization_range_min = 4
            byte_after_sid = random.randint(byte_after_sid_randomization_range_min, byte_after_sid_randomization_range_max)
            # Ensure that byte_after_sid doesn't have "Suppress Positive Response Message Indication Bit"
            while byte_after_sid - 0x80 >= 0 or int(byte_after_sid - 0x80).bit_length() == 8:
                byte_after_sid = random.randint(byte_after_sid_randomization_range_min, byte_after_sid_randomization_range_max)

        payload.append(byte_after_sid)

        if sid == 0x23:
            payload = self.build_sid_23_payload(address_length_id = byte_after_sid)
        elif sid == 0x2C:
            payload = self.build_sid_2c_payload(payload_min_length = targeted_payload_length)
        else:
            while len(payload) < targeted_payload_length:
                payload.append(random.randint(0x0, 0xFF))

        return TestCase(test_case_name="Random_multiframe",
            data = payload,
            test_group_name = '$' + '{0:02X}'.format(sid) + ' ' + str(uds_constants.ISO_DICTIONARY_INT_KEYS[sid]),
            arbid = self.target_arbid,
            dlc = dlc if sid != 0x23 else len(payload), 
            test_case_number = self.current_test_case_index)



    def generate_suppress_bit_test_case(self) -> TestCase:
        '''
        Generates and returns a Suppress bit UDS test case
        '''
        dlc = random.choice(self.suppress_bit_dlcs)
        sid = random.choice(self.suppress_bit_capable_sids)
        targeted_payload_length = dlc if not self.fd_enabled else map_dlc_to_len(given_dlc = dlc, fd = True)
                
        payload = [ sid ] # initialize payload bytes w/ the UDS SID at the first byte
        byte_after_sid = 0

        # Special handling for UDS SID & 0x11 (ECU Reset) to avoid using valid ECU Reset codes 
        if sid == 0x11:
            byte_after_sid = random.randint(0x84, 0xFF)
        else:
            byte_after_sid = random.randint(0x81, 0xFF)
        payload.append(byte_after_sid)
        while len(payload) < targeted_payload_length:
            payload.append(random.randint(0x00, 0xFF))

        # Update test case redundancy tracking structures
        self.test_group_counts.suppress_bit_tc_tracking[sid] += 1
        if self.test_group_counts.suppress_bit_tc_tracking[sid] == MAX_NUM_SUPPRESS_TC_PER_SID:
            self.suppress_bit_capable_sids.remove(sid)
            if len(self.suppress_bit_capable_sids) == 0:
                self.mixed_test_groups_generator_functions.remove(self.generate_suppress_bit_test_case)
        
        return TestCase(test_case_name="Suppress_bit",
            data = payload,
            test_group_name = '$' + '{0:02X}'.format(sid) + ' ' + str(uds_constants.ISO_DICTIONARY_INT_KEYS[sid]),
            arbid = self.target_arbid,
            dlc = dlc,
            test_case_number = self.current_test_case_index)



    def generate_pattern_based_test_case(self) -> TestCase:
        '''
        Generates and returns a UDS test case w/ patterned CAN payload
        '''
        sid = random.choice(self.pattern_based_capable_sids)
        dlc = random.choice(self.pattern_based_dlcs_for_sid.get(sid))
        viable_pattern_bytes = TEST_CASE_PATTERN_BYTES.copy()
        payload = [ sid ] # initialize payload bytes w/ the UDS SID at the first byte
        targeted_length = map_dlc_to_len(given_dlc = dlc, fd = True)

        if sid == 0x11:
            # Special handling of byte that comes after the SID byte for UDS Service 0x11 - to avoid using valid ECU Reset codes 11 01, 11 02, 11 03
            for pattern_byte in [0x01, 0x02, 0x03]:
                if pattern_byte in viable_pattern_bytes:
                    viable_pattern_bytes.remove(pattern_byte)
        if sid == 0x23:
            sid_23_addr_length_id = random.choice([0x11, 0x12, 0x22, 0x21, 0x13, 0x23, 0x23, 0x24])
            mem_address = sid_23_addr_length_id & 0x0F
            mem_size = sid_23_addr_length_id >> 4 & 0xF
            bytes_to_add = mem_address + mem_size
            targeted_length = bytes_to_add + 2
            payload.append(sid_23_addr_length_id)

        bytes_to_add = targeted_length - len(payload)
        pattern_byte = random.choice(viable_pattern_bytes)
        payload.extend([pattern_byte] * bytes_to_add)

        # Update test case redundancy tracking structures 
        self.test_group_counts.pattern_based_tracking[sid] += 1
        # Remove current bytes-to-follow value for current SID to avoid redundancy (ensure 1 occurrence of: <<single SID - single byte-to-follow>> combination)
        if targeted_length in self.pattern_based_dlcs_for_sid.get(sid): # Extra check since the picked length can change for Service $23 when it's calculated based on mem_size & mem_address
            self.pattern_based_dlcs_for_sid.get(sid).remove(targeted_length)
        # If all byes-to-follow values "spent" for one SID, then remove it from randomly picked SIDs list to avoid redundancy
        if len(self.pattern_based_dlcs_for_sid.get(sid)) == 0:
            self.pattern_based_capable_sids.remove(sid)
        # If all SIDs removed from randomly picked SIDs stop generating pattern based test cases
        if len(self.pattern_based_capable_sids) == 0:
            self.mixed_test_groups_generator_functions.remove(self.generate_pattern_based_test_case)

        return TestCase(test_case_name="Pattern_based",
                data = payload,
                test_group_name= '$' + '{0:02X}'.format(sid) + ' ' + str(uds_constants.ISO_DICTIONARY_INT_KEYS[sid]),
                arbid = self.target_arbid, 
                dlc = len(payload),
                test_case_number = self.current_test_case_index)



    def generate_pseudo_random_pattern_based_test_case(self) -> TestCase:
        '''
        Generates and returns a UDS test case w/ pseudo-patterned CAN payload
        '''
        dlc = random.choice(CLASSIC_CAN_DLCS) if not self.fd_enabled else random.choice(CAN_FD_DLCS)
        sid = random.choice(self.pseudo_random_pattern_capable_sids)
        viable_pattern_bytes = TEST_CASE_PATTERN_BYTES.copy()
        payload = [ sid ] # initialize payload bytes w/ the UDS SID at the first byte
        targeted_length = map_dlc_to_len(given_dlc = dlc, fd = True)

        if sid == 0x11:
            viable_pattern_bytes.remove(0x01)
        if sid == 0x23:
            sid_23_addr_length_id = random.choice([0x12, 0x22, 0x13, 0x23, 0x23, 0x24])
            mem_address = sid_23_addr_length_id & 0x0F
            mem_size = sid_23_addr_length_id >> 4 & 0xF
            bytes_to_add = mem_address + mem_size
            targeted_length = bytes_to_add + 2
            payload.append(sid_23_addr_length_id)

        while len(payload) < targeted_length:
            pattern_byte = random.choice(viable_pattern_bytes)
            payload.append(pattern_byte)

        # Update test case redundancy tracking structures 
        self.test_group_counts.pseudo_rand_pattern_based_sid_tracking[sid] += 1
        if self.test_group_counts.pseudo_rand_pattern_based_sid_tracking[sid] == MAX_NUM_PSEUDO_RANDOM_TC_PER_SID:
            self.pseudo_random_pattern_capable_sids.remove(sid)
            if len(self.pseudo_random_pattern_capable_sids) == 0:
                self.mixed_test_groups_generator_functions.remove(self.generate_pseudo_random_pattern_based_test_case)
        
        
        return TestCase(test_case_name="Pseudo_random_pattern",
            data = payload,
            test_group_name = '$' + '{0:02X}'.format(sid) + ' ' + str(uds_constants.ISO_DICTIONARY_INT_KEYS[sid]),
            arbid = self.target_arbid,
            dlc = len(payload),
            test_case_number = self.current_test_case_index)



    def generate_invalid_dlc_test_case(self) -> TestCase:
        '''
        Generates and returns a UDS test case w/ invalid CAN DLC
        '''
        sid = random.choice(self.invalid_dlc_capable_sids)
        targeted_length = random.choice(CLASSIC_CAN_DLCS)

        payload = [ sid ] # initialize payload bytes w/ the UDS SID at the first byte
        byte_after_sid = 0
        byte_after_sid_randomization_range_min = 0x0
        byte_after_sid_randomization_range_max = 0xFF
        
        # Generate randomized DLC from invalid DLC range
        invalid_dlc = random.randint(9, 15)

        # Special handling of byte that comes after the SID byte for UDS Service 0x11
        if sid == 0x11:
            byte_after_sid_randomization_range_min = 0x4
        
        byte_after_sid = random.randint(byte_after_sid_randomization_range_min, byte_after_sid_randomization_range_max)
        while byte_after_sid - 0x80 >= 0 or int(byte_after_sid - 0x80).bit_length() == 8:
            byte_after_sid = random.randint(byte_after_sid_randomization_range_min, byte_after_sid_randomization_range_max)
        payload.append(byte_after_sid)

        while len(payload) < targeted_length:
            payload.append(random.randint(0x0, 0xFF))

        # Update test case redundancy tracking structures 
        self.test_group_counts.invalid_dlc_tc_tracking[sid] += 1
        if self.test_group_counts.invalid_dlc_tc_tracking[sid] == MAX_NUM_INVALID_DLC_TC_PER_SID:
            self.invalid_dlc_capable_sids.remove(sid)
            if len(self.invalid_dlc_capable_sids) == 0:
                self.mixed_test_groups_generator_functions.remove(self.generate_invalid_dlc_test_case)
        
        return TestCase(test_case_name = "Invalid_dlc",
            data = payload,
            test_group_name = '$' + '{0:02X}'.format(sid) + ' ' + str(uds_constants.ISO_DICTIONARY_INT_KEYS[sid]),
            arbid = self.target_arbid,
            dlc = invalid_dlc,
            test_case_number = self.current_test_case_index)



    def generate_mixed_randomized_test_case(self):
        '''
        Generates a single test case from mixed randomized test groups (Random_single_frame, Random_multiframe, Suppress_bit, Pattern_based, Pseudo_random_pattern, Invalid_dlc)
        '''
        test_generator_function = random.choice(self.mixed_test_groups_generator_functions)
        test_case = test_generator_function()
        return test_case



    def generate_test_case(self):
        '''
        Generates and returns a single UDS test case
        '''
        # If DB file imported generate only DB file based TCs
        if self.messages2E and len(self.messages2E) > 0:
            return self.generate_messages2E_test_case()
        
        # Orderly generate 11-bit Arb-ID Scan test cases
        if self.total_number_of_test_cases > 2048 and self.test_group_counts.arbid_11_bit_scan_index < 0x800 and self.include_arbid_fuzzing:
            return self.generate_arbid_scan_test_case()
        
        # Pick random UDS test group & generate that group's test case
        # Groups: 29-bit Randomized Arb-ID test cases, Mutation for extended addressing targets only, 11-bit CAN Arbitration ID target mutations, 
        # 29-bit CAN Arbitration ID single byte target mutations, 29-bit CAN Arbitration ID double byte target mutations
        # Pseudo random test cases with valid ISO 14229 bytes to follow, and Mixed randomized test groups (Random_single_frame, Random_multiframe, Suppress_bit, Pattern_based, Pseudo_random_pattern, Invalid_dlc)
        selected_generator_function = self.generator_functions[random.randint(0, len(self.generator_functions) - 1)]
        test_case = selected_generator_function()
        test_case.source_byte = self.source_byte if self.target_addressing_mode == 'extended' else None
        return test_case
    

def uds_set_iso_sids(selected_uds_services):
    ''' createes a list of user selected ISO UDS SIDs'''
    iso_sids_list = []
    if len(selected_uds_services) > 0:
        iso_sids_list = [int(sid, 16) if isinstance(sid, str) else sid for sid in selected_uds_services]

    return iso_sids_list

def get_number_pseudo_random_tc(iso_services_selected):
    iso_valid_btf = uds_constants.ISO_SERVICES_VALID_BTF
    pseudo_random_test_cases_count = 0
    for service in iso_valid_btf:
        if service in iso_services_selected:
            service_test_case_count = 0
            service_info = iso_valid_btf[service]
            multiplier = len(service_info[0])
            for btf in service_info[1]:
                service_test_case_count += math.pow(2, 8*btf)
            pseudo_random_test_cases_count += service_test_case_count * multiplier
        
    return int(pseudo_random_test_cases_count)


def uds_test_generation(configurations:dict) -> CANUDSTestCaseGenerator:

    generator = CANUDSTestCaseGenerator(configurations=configurations)

    if not check_trial_limits('UDS', generator.total_number_of_test_cases, True):
        return None
    generator.generate_all_test_cases()    
    return generator
