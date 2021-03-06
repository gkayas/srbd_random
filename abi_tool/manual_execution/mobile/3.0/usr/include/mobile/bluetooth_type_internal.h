/*
 * Copyright (c) 2011 Samsung Electronics Co., Ltd All Rights Reserved
 *
 * Licensed under the Apache License, Version 2.0 (the License);
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an AS IS BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


#ifndef __TIZEN_NETWORK_BLUETOOTH_TYPE_INTERNAL_H__
#define __TIZEN_NETWORK_BLUETOOTH_TYPE_INTERNAL_H__

 #ifdef __cplusplus
extern "C"
{
#endif /* __cplusplus */

/**
 * @file bluetooth_type_internal.h
 */

/**
 * @ingroup CAPI_NETWORK_BLUETOOTH_ADAPTER_LE_MODULE
 * @brief  Enumerations of the Bluetooth adapter le state.
 * @since_tizen 2.3
 */
typedef enum {
	BT_ADAPTER_LE_DISABLED = 0x00, /**< Bluetooth le is disabled */
	BT_ADAPTER_LE_ENABLED, /**< Bluetooth le is enabled */
} bt_adapter_le_state_e;

/**
 * @ingroup CAPI_NETWORK_BLUETOOTH_ADAPTER_LE_MODULE
 * @brief  Called when the Bluetooth adapter le state changes.
 * @since_tizen 2.3
 *
 * @param[in]   result  The result of the adapter state changing
 * @param[in]   adapter_le_state  The adapter le state to be changed
 * @param[in]   user_data  The user data passed from the callback registration function
 * @pre Either bt_adapter_le_enable() or bt_adapter_le_disable() will invoke this callback if you register this callback using bt_adapter_le_set_state_changed_cb().
 * @see bt_adapter_le_enable()
 * @see bt_adapter_le_disable()
 * @see bt_adapter_le_set_state_changed_cb()
 * @see bt_adapter_le_unset_state_changed_cb()
 */
typedef void (*bt_adapter_le_state_changed_cb)(int result, bt_adapter_le_state_e adapter_le_state, void *user_data);

/**
 * @ingroup CAPI_NETWORK_BLUETOOTH_AUDIO_AG_MODULE
 * @brief  Enumerations for the call state
 * @since_tizen 2.3
 */
typedef enum {
	BT_AG_CALL_EVENT_IDLE = 0x00,  /**< Idle */
	BT_AG_CALL_EVENT_ANSWERED,  /**< Answered */
	BT_AG_CALL_EVENT_HELD,  /**< Held */
	BT_AG_CALL_EVENT_RETRIEVED,  /**< Retrieved */
	BT_AG_CALL_EVENT_DIALING,  /**< Dialing */
	BT_AG_CALL_EVENT_ALERTING,  /**< Alerting */
	BT_AG_CALL_EVENT_INCOMING,  /**< Incoming */
} bt_ag_call_event_e;

/**
 * @ingroup CAPI_NETWORK_BLUETOOTH_AUDIO_AG_MODULE
 * @brief  Enumerations for the call state
 * @since_tizen 2.3
 */
typedef enum {
	BT_AG_CALL_STATE_IDLE = 0x00,  /**< Idle state */
	BT_AG_CALL_STATE_ACTIVE,  /**< Active state */
	BT_AG_CALL_STATE_HELD,  /**< Held state */
	BT_AG_CALL_STATE_DIALING,  /**< Dialing state */
	BT_AG_CALL_STATE_ALERTING,  /**< Alerting state */
	BT_AG_CALL_STATE_INCOMING,  /**< Incoming state */
	BT_AG_CALL_STATE_WAITING,  /**< Waiting for connected indication event after answering an incoming call*/
} bt_ag_call_state_e;

/**
 * @ingroup CAPI_NETWORK_BLUETOOTH_ADAPTER_MODULE
 * @brief  Called when the connectable state changes.
 * @since_tizen 2.3
 *
 * @param[in] result The result of the connectable state changing
 * @param[in] connectable The connectable to be changed
 * @param[in] user_data The user data passed from the callback registration function
 *
 * @pre This function will be invoked when the connectable state of local Bluetooth adapter changes
 * if you register this callback using bt_adapter_set_connectable_changed_cb().
 *
 * @see bt_adapter_set_connectable()
 * @see bt_adapter_set_connectable_changed_cb()
 * @see bt_adapter_unset_connectable_changed_cb()
 */
typedef void (*bt_adapter_connectable_changed_cb)
	(int result, bool connectable, void *user_data);

/**
 * @ingroup CAPI_NETWORK_BLUETOOTH_OPP_SERVER_MODULE
 * @brief  Called when the push is requested.
 * @since_tizen 2.3
 *
 * @details You must call bt_opp_server_accept() if you want to accept.
 * Otherwise, you must call bt_opp_server_reject().
 * @param[in] file  The path of file to be pushed
 * @param[in] size The file size (bytes)
 * @param[in] user_data The user data passed from the callback registration function
 * @see bt_opp_server_initialize()
 */
typedef void (*bt_opp_server_push_requested_cb)(const char *file, int size, void *user_data);

/**
 * @ingroup CAPI_NETWORK_BLUETOOTH_ADAPTER_LE_MODULE
 * @brief  Enumerations of the Bluetooth adapter le scan type.
 * @since_tizen 2.3
 */
typedef enum {
	BT_ADAPTER_LE_PASSIVE_SCAN = 0x00,
	BT_ADAPTER_LE_ACTIVE_SCAN
} bt_adapter_le_scan_type_e;

/**
 * @ingroup CAPI_NETWORK_BLUETOOTH_ADAPTER_LE_MODULE
 * @brief  Enumerations of the Bluetooth le scan mode.
 * @since_tizen 2.3
 */
typedef enum {
	BT_ADAPTER_LE_SCAN_MODE_BALANCED,
	BT_ADAPTER_LE_SCAN_MODE_LOW_LATENCY,
	BT_ADAPTER_LE_SCAN_MODE_LOW_ENERGY
} bt_adapter_le_scan_mode_e;

/**
 * @ingroup CAPI_NETWORK_BLUETOOTH_ADAPTER_MODULE
 * @brief  Called when the manufacturer dat changes.
 * @since_tizen 2.3
 *
 * @param[in]   data		The manufacurer data of the Bluetooth device to be changed
 * @param[in]   len			The length of @a data
 * @param[in]   user_data	The user data passed from the callback registration function
 * @pre This function will be invoked when the manufacturer data of Bluetooth adapter changes
 * if callback is registered using bt_adapter_set_manufacturer_data_changed_cb().
 * @see bt_adapter_set_manufacturer_data()
 * @see bt_adapter_set_manufacturer_data_changed_cb()
 * @see bt_adapter_unset_manufacturer_data_changed_cb()
 */
typedef void (*bt_adapter_manufacturer_data_changed_cb) (char *data,
		int len, void *user_data);

/**
 * @internal
 * @ingroup CAPI_NETWORK_BLUETOOTH_ADAPTER_MODULE
 * @brief  Called repeatedly when you get the devices connected with specific profile.
 * @since_tizen 3.0
 *
 * @param[in]   remote_address	The address of remote device
 * @param[in]   user_data	The user data passed from the callback registration function
 * @return @c true to continue with the next iteration of the loop,
 * \n @c false to break out of the loop.
 * @pre bt_adapter_foreach_profile_connected_devices() will invoke this function.
 * @see bt_adapter_foreach_profile_connected_devices()
 */
typedef bool (*bt_adapter_profile_connected_devices_cb)(const char *remote_address, void *user_data);

/**
* @ingroup CAPI_NETWORK_BLUETOOTH_DPM_MODULE
* @brief DPM BT allowance state
* @since_tizen 3.0
*/
typedef enum {
	BT_DPM_ERROR	  = -1,   /**< bluetooth allowance error */
	BT_DPM_BT_ALLOWED,	  /**< bluetooth allowance allowed */
	BT_DPM_HANDSFREE_ONLY, /**< bluetooth allowance handsfree only */
	BT_DPM_BT_RESTRICTED,  /**< bluetooth allowance restricted */
} bt_dpm_allow_e;

/**
* @ingroup CAPI_NETWORK_BLUETOOTH_DPM_MODULE
* @brief DPM Policy status
* @since_tizen 3.0
*/
typedef enum {
	BT_DPM_STATUS_ERROR = -1,

	BT_DPM_ALLOWED	 = 0,	 /**< DPM Policy status allowed. */
	BT_DPM_RESTRICTED		 = 1,	 /**< DPM Policy status restricted. */

	BT_DPM_ENABLE			 = 1,	 /**< DPM Policy status enabled. */
	BT_DPM_DISABLE	= 0,	 /**< DPM Policy status disabled. */

	BT_DPM_FALSE	 = 0,	/**< DPM Policy status false. */
	BT_DPM_TRUE		= 1,	/**< DPM Policy status true. */
} bt_dpm_status_e;

/**
* @ingroup CAPI_NETWORK_BLUETOOTH_DPM_MODULE
* @brief DPM Profile states
* @since_tizen 3.0
*/
typedef enum {
	BT_DPM_POLICY_A2DP_PROFILE_STATE,
	BT_DPM_POLICY_AVRCP_PROFILE_STATE,
	BT_DPM_POLICY_BPP_PROFILE_STATE,
	BT_DPM_POLICY_DUN_PROFILE_STATE,
	BT_DPM_POLICY_FTP_PROFILE_STATE,
	BT_DPM_POLICY_HFP_PROFILE_STATE,
	BT_DPM_POLICY_HSP_PROFILE_STATE,
	BT_DPM_POLICY_PBAP_PROFILE_STATE,
	BT_DPM_POLICY_SAP_PROFILE_STATE,
	BT_DPM_POLICY_SPP_PROFILE_STATE,
	BT_DPM_PROFILE_NONE,
} bt_dpm_profile_e;

/**
 * @ingroup CAPI_NETWORK_BLUETOOTH_ADAPTER_LE_MODULE
 * @brief The handle to control Bluetooth LE scan filter
 * @since_tizen 2.4
 */
typedef void *bt_scan_filter_h;

/**
 * @ingroup CAPI_NETWORK_BLUETOOTH_DEVICE_MODULE
 * @brief Device LE connection update structure.
 * @since_tizen 2.3
 */
typedef struct {
	unsigned int interval_min;   /**< Minimum value for the connection event interval (msec) */
	unsigned int interval_max;   /**< Maximum value for the connection event interval (msec) */
	unsigned int latency;   /**< Slave latency (msec) */
	unsigned int time_out;   /**< Supervision timeout (msec) */
} bt_le_conn_update_s;

/**
 * Structure to DPM device list
 */
typedef struct {
	int count;
	char **devices;
} bt_dpm_device_list_s;

/**
 * Structure to DPM uuid list
 */
typedef struct {
	int count;
	char **uuids;
} bt_dpm_uuids_list_s;

/**
 * @ingroup CAPI_NETWORK_BLUETOOTH_HID_DEVICE_MODULE
 * @brief Enumerations of the Bluetooth HID mouse's button.
 * @since_tizen 3.0
 */
typedef enum {
	BT_HID_MOUSE_BUTTON_NONE = 0x00, /**<The mouse's none value*/
	BT_HID_MOUSE_BUTTON_LEFT = 0x01, /**<The mouse's left button value*/
	BT_HID_MOUSE_BUTTON_RIGHT = 0x02,  /**<The mouse's right button value*/
	BT_HID_MOUSE_BUTTON_MIDDLE = 0x04 /**<The mouse's middle button value*/
} bt_hid_mouse_button_e;

/**
 * @ingroup CAPI_NETWORK_BLUETOOTH_HID_DEVICE_MODULE
 * @brief The structure type containing the HID mouse event information.
 * @since_tizen 3.0
 *
 * @see bt_hid_device_send_mouse_event()
 */
typedef struct {
	int buttons; /**< The button values, we can combine key's values when we pressed multiple mouse buttons*/
	int axis_x; /**< The location's x value, -128 ~127 */
	int axis_y; /**< The location's y value, -128 ~127 */
	int padding; /**< The padding value, -128 ~127 */
} bt_hid_mouse_data_s;

/**
 * @ingroup CAPI_NETWORK_BLUETOOTH_HID_DEVICE_MODULE
 * @brief The structure type containing the HID keyboard event information.
 * @since_tizen 3.0
 * @details If you want to know more detail values, refer to http://www.usb.org/developers/hidpage/ and see "HID Usage Tables"
 *
 * @see bt_hid_device_send_key_event()
 */
typedef struct {
	unsigned char modifier; /**< The modifier keys : such as shift, alt */
	unsigned char key[8]; /**< The key value - currently pressed keys : Max 8 at once */
} bt_hid_key_data_s;

/**
 * @ingroup CAPI_NETWORK_BLUETOOTH_HID_DEVICE_MODULE
 * @brief Enumerations of the Bluetooth HID header type.
 * @since_tizen 3.0
 */
typedef enum {
	BT_HID_HEADER_HANDSHAKE, /**< The Bluetooth HID header type: Handshake */
	BT_HID_HEADER_HID_CONTROL, /**< The Bluetooth HID header type: HID control */
	BT_HID_HEADER_GET_REPORT, /**< The Bluetooth HID header type: Get report */
	BT_HID_HEADER_SET_REPORT, /**< The Bluetooth HID header type: Set report */
	BT_HID_HEADER_GET_PROTOCOL, /**< The Bluetooth HID header type: Get protocol */
	BT_HID_HEADER_SET_PROTOCOL, /**< The Bluetooth HID header type: Set protocol */
	BT_HID_HEADER_DATA, /**< The Bluetooth HID header type: Data */
	BT_HID_HEADER_UNKNOWN /**< The Bluetooth HID header type: Unknown */
} bt_hid_header_type_e;

/**
 * @ingroup CAPI_NETWORK_BLUETOOTH_HID_DEVICE_MODULE
 * @brief Enumerations of the Bluetooth HID parameter type.
 * @since_tizen 3.0
 */
typedef enum {
	BT_HID_PARAM_DATA_RTYPE_INPUT, /**< Parameter type: Input */
	BT_HID_PARAM_DATA_RTYPE_OUTPUT /**< Parameter type: Output */
} bt_hid_param_type_e;

/**
 * @ingroup CAPI_NETWORK_BLUETOOTH_HID_DEVICE_MODULE
 * @brief Enumerations of the Bluetooth HID handshake type.
 * @since_tizen 3.0
 */
typedef enum {
	BT_HID_HANDSHAKE_SUCCESSFUL = 0x00, /**< Handshake error code none */
	BT_HID_HANDSHAKE_NOT_READY, /**< Handshake error code Not Ready */
	BT_HID_HANDSHAKE_ERR_INVALID_REPORT_ID, /**< Handshake error code send invalid report id */
	BT_HID_HANDSHAKE_ERR_UNSUPPORTED_REQUEST, /**< Handshake error code request unsupported request */
	BT_HID_HANDSHAKE_ERR_INVALID_PARAMETER, /**< Handshake error code received invalid parameter */
	BT_HID_HANDSHAKE_ERR_UNKNOWN = 0x0e, /**< unknown error */
	BT_HID_HANDSHAKE_ERR_FATAL /**< Fatal error */
} bt_hid_handshake_type_e;

/**
 * @ingroup CAPI_NETWORK_BLUETOOTH_HID_DEVICE_MODULE
 * @brief The structure type containing data received from the HID Host.
 * @since_tizen 3.0
 */
typedef struct {
	const char *address;  /**< The remote device's address  */
	bt_hid_header_type_e header_type;  /**< The header type */
	bt_hid_param_type_e param_type;  /**< The parameter type */
	int data_size;  /**< The length of the received data */
	const char *data;     /**< The received data */
} bt_hid_device_received_data_s;

/**
 * @ingroup CAPI_NETWORK_BLUETOOTH_HID_DEVICE_MODULE
 * @brief  Called when the Bluetooth HID Device connection state changes.
 * @details The following error codes can be delivered: \n
 *             #BT_ERROR_NONE \n
 *             #BT_ERROR_OPERATION_FAILED \n
 * @since_tizen 3.0
 *
 * @param[in]   result  The result of changing the connection state.
 * @param[in]   connected  The requested state. @a true means the connection is enabled, @false means the connection is disabled.
 * @param[in]   remote_address  The remote device's address
 * @param[in]   user_data  The user data passed from the callback registration function
 * @see bt_hid_device_activate()
 */
typedef void (*bt_hid_device_connection_state_changed_cb) (int result,
	bool connected, const char *remote_address, void *user_data);

/**
 * @ingroup CAPI_NETWORK_BLUETOOTH_HID_DEVICE_MODULE
 * @brief  Called when the HID Device receives data from the HID Host.
 * @details The following error codes can be delivered: \n
 *             #BT_ERROR_NONE \n
 *             #BT_ERROR_OPERATION_FAILED \n
 * @since_tizen 3.0
 *
 * @param[in]   data  The data received from the HID Host.
 * @param[in]   user_data  The user data passed from the callback registration function
 * @see bt_hid_device_set_data_received_cb()
 */
typedef void (*bt_hid_device_data_received_cb)(const bt_hid_device_received_data_s *data, void *user_data);

/**
 * @ingroup CAPI_NETWORK_BLUETOOTH_DEVICE_MODULE
 * @brief Trusted Profile enumeration.
 * @since_tizen 3.0
 *
 * @see bt_device_set_profile_trusted()
 * @see bt_device_get_profile_trusted()
 */
typedef enum {
	BT_TRUSTED_PROFILE_PBAP = 1,
	BT_TRUSTED_PROFILE_MAP,
	BT_TRUSTED_PROFILE_SAP,
	BT_TRUSTED_PROFILE_ALL = 0xFFFFFFFF,
} bt_trusted_profile_t;

/**
 * @ingroup CAPI_NETWORK_BLUETOOTH_DEVICE_MODULE
 * @brief  Called when Trusted Profiles is changed.
 * @since_tizen 3.0
 *
 * @param[in]   result  The result of supported profile callback
 * @param[in]   remote_address  Address of remote device
 * @param[in]   trusted_profiles  Trusted profile FLAG
 * @param[in]   user_data  The user data passed from the callback registration function
 * @see bt_device_set_trusted_profile_cb()
 * @see bt_device_unset_trusted_profile_cb()
 */
typedef void (*bt_device_trusted_profiles_cb)
	(int result, char *remote_address, int trusted_profile, bool supported, bool trusted,void *user_data);


/**
 * @internal
 * @ingroup
 * @brief IPSP Init state changed callback
 * @since_tizen 3.0
 */
typedef void (*bt_ipsp_init_state_changed_cb)
		(int result, bool ipsp_initialized, void *user_data);

/**
 * @internal
 * @ingroup
 * @since_tizen 3.0

 * @brief IPSP Connection state changed callback
 */
typedef void (*bt_ipsp_connection_state_changed_cb)
		(int result, bool connected, const char *remote_address, const char *iface_name, void *user_data);


/**
 * @}
 */

#ifdef __cplusplus
}
#endif /* __cplusplus */

#endif /* __TIZEN_NETWORK_BLUETOOTH_TYPE_INTERNAL_H__ */
