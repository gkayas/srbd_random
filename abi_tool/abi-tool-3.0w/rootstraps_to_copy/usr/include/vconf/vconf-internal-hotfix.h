/*
 * vconf-internal-keys
 *
 * Copyright (c) 2000 - 2011 Samsung Electronics Co., Ltd. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

#ifndef __VCONF_INTERNAL_HOTFIX_KEYS_H__
#define __VCONF_INTERNAL_HOTFIX_KEYS_H__
#define VCONFKEY_SETAPPL_TOUCH_FEEDBACK_SOUND_VOLUME_INT  "db/setting/sound/touch_feedback/sound_volume"

#define VCONFKEY_SETAPPL_NOTI_SOUND_VOLUME_INT  "db/setting/sound/noti/sound_volume"

#ifdef USE_NONCREATED_VCONF_KEY
#warning using non-created vconf-key ----- please make sure this key is in use actually
#endif
#define VCONFKEY_SETUP_WIZARD_FIRST_BOOT  "db/setup_wizard/first_boot"

#ifdef USE_NONCREATED_VCONF_KEY
#warning using non-created vconf-key ----- please make sure this key is in use actually
#endif
#define VCONFKEY_SETUP_WIZARD_STATE  "memory/setup_wizard/state"

enum {
	VCONFKEY_SETUP_WIZARD_UNLOCK = 0x00,
		VCONFKEY_SETUP_WIZARD_LOCK
};

#ifdef USE_NONCREATED_VCONF_KEY
#warning using non-created vconf-key ----- please make sure this key is in use actually
#endif
#define VCONFKEY_ODE_CRYPTO_STATE  "db/ode/crypto_state"

#ifdef USE_NONCREATED_VCONF_KEY
#warning using non-created vconf-key ----- please make sure this key is in use actually
#endif
#define VCONFKEY_SDE_ENCRYPT_PROGRESS  "db/sde/encrypt_progress"

#ifdef USE_NONCREATED_VCONF_KEY
#warning using non-created vconf-key ----- please make sure this key is in use actually
#endif
#define VCONFKEY_SDE_CRYPTO_STATE  "db/sde/crypto_state"

#ifdef USE_NONCREATED_VCONF_KEY
#warning using non-created vconf-key ----- please make sure this key is in use actually
#endif
#define VCONFKEY_CISSAPPL_ANSWERING_MODE_INT  "db/ciss/answering_mode"

#ifdef USE_NONCREATED_VCONF_KEY
#warning using non-created vconf-key ----- please make sure this key is in use actually
#endif
#define VCONFKEY_CISSAPPL_ANSWERING_MODE_TIME_INT  "db/ciss/answering_mode_time"

#ifdef USE_NONCREATED_VCONF_KEY
#warning using non-created vconf-key ----- please make sure this key is in use actually
#endif
#define VCONFKEY_CISSAPPL_OUTGOING_CALL_CONDITIONS_INT  "db/ciss/outgoing_call_conditions"

#ifdef USE_NONCREATED_VCONF_KEY
#warning using non-created vconf-key ----- please make sure this key is in use actually
#endif
#define VCONFKEY_CISSAPPL_OUTGOING_CALL_TYPE_INT  "db/ciss/outgoing_call_type"

#ifdef USE_NONCREATED_VCONF_KEY
#warning using non-created vconf-key ----- please make sure this key is in use actually
#endif
#define VCONFKEY_SETAPPL_PERFERED_ARM_LEFT_BOOL  "db/setting/prefer_arm_left"

#ifdef USE_NONCREATED_VCONF_KEY
#warning using non-created vconf-key ----- please make sure this key is in use actually
#endif
#define VCONFKEY_SETAPPL_COLORBLIND_STATUS_BOOL  "db/setting/colorblind/status"

#ifdef USE_NONCREATED_VCONF_KEY
#warning using non-created vconf-key ----- please make sure this key is in use actually
#endif
#define VCONFKEY_SETAPPL_COLORBLIND_LAST_RGBCMY_STR  "db/setting/colorblind/rgbcmy"

#define VCONFKEY_SETAPPL_PALM_SWIP_CAPTURE  "db/setting/motion/palm_swipe_capture"

#define VCONFKEY_SETAPPL_PALM_TOUCH_MUTE  "db/setting/motion/palm_touch_mute"

#ifdef USE_NONCREATED_VCONF_KEY
#warning using non-created vconf-key ----- please make sure this key is in use actually
#endif
#define VCONFKEY_SETAPPL_STATE_TICKER_NOTI_EMAIL_BOOL  "db/setting/ticker_noti/email"

#ifdef USE_NONCREATED_VCONF_KEY
#warning using non-created vconf-key ----- please make sure this key is in use actually
#endif
#define VCONFKEY_SETAPPL_ACCESSORY_DOCK_SOUND  "db/setting/accessories/dock_sound"

#ifdef USE_NONCREATED_VCONF_KEY
#warning using non-created vconf-key ----- please make sure this key is in use actually
#endif
#define VCONFKEY_SETAPPL_EASYMODE_HOME_SCREEN  "db/setting/easymoe/home_screen"

#ifdef USE_NONCREATED_VCONF_KEY
#warning using non-created vconf-key ----- please make sure this key is in use actually
#endif
#define VCONFKEY_SETAPPL_SMARTSCREEN_SMARTSTAY_STATUS  "db/setting/smartscreen/smart_stay"

#ifdef USE_NONCREATED_VCONF_KEY
#warning using non-created vconf-key ----- please make sure this key is in use actually
#endif
#define VCONFKEY_SETAPPL_BLOCKINGMODE_ALLOWED_CONTACT_TYPE  "db/setting/blockingmode/allowed_contact_type"

#ifdef USE_NONCREATED_VCONF_KEY
#warning using non-created vconf-key ----- please make sure this key is in use actually
#endif
#define VCONFKEY_SETAPPL_BLOCKINGMODE_ALLOWED_CONTACT_LIST  "db/setting/blockingmode/allowed_contact_list"

#ifdef USE_NONCREATED_VCONF_KEY
#warning using non-created vconf-key ----- please make sure this key is in use actually
#endif
#define VCONFKEY_SETAPPL_BLOCKINGMODE_NOTIFICATIONS  "db/setting/blockingmode/notifications"

#ifdef USE_NONCREATED_VCONF_KEY
#warning using non-created vconf-key ----- please make sure this key is in use actually
#endif
#define VCONFKEY_CSC_SALESCODE  "db/csc/salescode"

#ifdef USE_NONCREATED_VCONF_KEY
#warning using non-created vconf-key ----- please make sure this key is in use actually
#endif
#define VCONFKEY_CSC_CONFIG_MODE_RUNNING  "memory/csc/config_mode_running"

#ifdef USE_NONCREATED_VCONF_KEY
#warning using non-created vconf-key ----- please make sure this key is in use actually
#endif
#define VCONFKEY_MOBEX_CONNECTOR_PREFIX  "memory/mobex_connector"

#ifdef USE_NONCREATED_VCONF_KEY
#warning using non-created vconf-key ----- please make sure this key is in use actually
#endif
#define VCONFKEY_MOBEX_CONNECTOR_STATUS  "memory/mobex_connector/status"

enum {
	VCONFKEY_MOBEX_CONNECTOR_STATUS_DISCONNECTED = 0,
		VCONFKEY_MOBEX_CONNECTOR_STATUS_WAITING,
		VCONFKEY_MOBEX_CONNECTOR_STATUS_CONNECTED
};

#ifdef USE_NONCREATED_VCONF_KEY
#warning using non-created vconf-key ----- please make sure this key is in use actually
#endif
#define VCONFKEY_MOBEX_ENGINE_PREFIX  "memory/mobex_engine"

#ifdef USE_NONCREATED_VCONF_KEY
#warning using non-created vconf-key ----- please make sure this key is in use actually
#endif
#define VCONFKEY_MOBEX_ENGINE_STATUS_INT  "memory/mobex_engine/status"

enum {
	VCONFKEY_MOBEX_ENGINE_STATUS_NOT_CONNECT = 0x00,
		VCONFKEY_MOBEX_ENGINE_STATUS_CONNECTED = 0x01,
		VCONFKEY_MOBEX_ENGINE_STATUS_SYNC_START = 0x02,
		VCONFKEY_MOBEX_ENGINE_STATUS_SYNC_END = 0x04
};

#ifdef USE_NONCREATED_VCONF_KEY
#warning using non-created vconf-key ----- please make sure this key is in use actually
#endif
#define VCONFKEY_MOBEX_ENGINE_MEMO_SYNC_STATUS  "memory/mobex_engine/memo_sync_status"

#ifdef USE_NONCREATED_VCONF_KEY
#warning using non-created vconf-key ----- please make sure this key is in use actually
#endif
#define VCONFKEY_MOBEX_ENGINE_CONNECTION_METHOD_INT  "memory/mobex_engine/conn_method"

enum {
	VCONFKEY_MOBEX_ENGIN_NONE = 0,
		VCONFKEY_MOBEX_ENGIN_USB,
		VCONFKEY_MOBEX_ENGIN_WIFI
};

#ifdef USE_NONCREATED_VCONF_KEY
#warning using non-created vconf-key ----- please make sure this key is in use actually
#endif
#define VCONFKEY_MOBEX_ENGINE_FUS_ODE_STATUS_INT  "memory/mobex_engine/fus_ode_status"

enum {
	VCONFKEY_MOBEX_ENGINE_FUS_ODE_STATUS_UNSET = 0,
		VCONFKEY_MOBEX_ENGINE_FUS_ODE_STATUS_SET
};

#endif /*   __VCONF_INTERNAL_HOTFIX_KEYS_H__    */
