/*
 * Copyright (c) 2016 Samsung Electronics Co., Ltd. All rights reserved.
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

#ifndef __AUL_SCREEN_CONNECTOR_H__
#define __AUL_SCREEN_CONNECTOR_H__

#ifdef __cplusplus
extern "C" {
#endif

typedef void (*aul_app_screen_added)(const char *appid, const int pid,
		const unsigned int surface_id, void *data);
typedef void (*aul_app_screen_removed)(const char *appid, const int pid,
		void *data);

typedef struct aul_screen_viewer_handler_s {
	aul_app_screen_added app_added;
	aul_app_screen_removed app_removed;
} aul_screen_viewer_handler;

/*
 * This API is only for Appfw internally.
 */
int aul_screen_connector_add_app_screen(unsigned int surf);

/*
 * This API is only for Appfw internally.
 */
int aul_screen_connector_remove_app_screen(void);

/*
 * This API is only for Appfw internally.
 */
int aul_screen_connector_update_app_screen(const char *appid);

/*
 * This API is only for Appfw internally.
 */
int aul_screen_connector_add_screen_viewer(aul_screen_viewer_handler *cbs,
		void *data);

/*
 * This API is only for Appfw internally.
 */
int aul_screen_connector_remove_screen_viewer(void);

#ifdef __cplusplus
}
#endif

#endif /* __AUL_SCREEN_CONNECTOR_H__ */
