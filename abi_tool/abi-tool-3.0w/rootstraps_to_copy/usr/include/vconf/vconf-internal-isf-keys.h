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

#ifndef __VCONF_INTERNAL_ISF_KEYS_H__
#define __VCONF_INTERNAL_ISF_KEYS_H__
#define VCONFKEY_ISF_INPUT_LANGUAGE  "db/isf/input_language"

#define VCONFKEY_ISF_ACTIVE_KEYBOARD_UUID  "db/isf/input_keyboard_uuid"

#define VCONFKEY_ISF_INPUT_PANEL_STATE  "memory/isf/input_panel_state"

enum {
	VCONFKEY_ISF_INPUT_PANEL_STATE_HIDE = 0,
		VCONFKEY_ISF_INPUT_PANEL_STATE_WILL_HIDE,
		VCONFKEY_ISF_INPUT_PANEL_STATE_SHOW,
		VCONFKEY_ISF_INPUT_PANEL_STATE_WILL_SHOW
};

#define VCONFKEY_ISF_HW_KEYBOARD_INPUT_DETECTED  "memory/isf/hw_keyboard_input_detected"

#endif /*   __VCONF_INTERNAL_ISF_KEYS_H__    */
