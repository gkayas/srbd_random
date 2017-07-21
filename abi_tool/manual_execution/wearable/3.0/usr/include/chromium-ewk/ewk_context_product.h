/*
 * Copyright (C) 2016 Samsung Electronics
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Library General Public
 * License as published by the Free Software Foundation; either
 * version 2 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Library General Public License for more details.
 *
 * You should have received a copy of the GNU Library General Public License
 * along with this program; see the file COPYING.LIB.  If not, write to
 * the Free Software Foundation, Inc., 51 Franklin Street, Fifth Floor,
 * Boston, MA 02110-1301, USA.
 */

/**
 * @file    ewk_context_product.h
 * @brief   Describes the context API.
 *
 * @note ewk_context encapsulates all pages related to specific use of
 *       Chromium-efl
 *
 * Applications have the option of creating a context different than the default
 * one and use it for a group of pages. All pages in the same context share the
 * same preferences, visited link set, local storage, etc.
 *
 * A process model can be specified per context. The default one is the shared
 * model where the web-engine process is shared among the pages in the context.
 * The second model allows each page to use a separate web-engine process.
 * This latter model is currently not supported by Chromium-efl.
 *
 */

#ifndef ewk_context_product_h
#define ewk_context_product_h

#include <stdint.h>

#include "ewk_context_internal.h"

#ifdef __cplusplus
extern "C" {
#endif

enum _Ewk_Application_Type {
  EWK_APPLICATION_TYPE_WEBBROWSER = 0,
  EWK_APPLICATION_TYPE_HBBTV = 1,
  EWK_APPLICATION_TYPE_TIZENWRT = 2,
  EWK_APPLICATION_TYPE_OTHER = 3
};

typedef enum _Ewk_Application_Type Ewk_Application_Type;

/**
 * Callback to check a file request is allowed for specific tizen app id
 *
 * @param tizen_app_id Tizen app id.
 * @param url The url for the file request.
 * @return @c EINA_TRUE if the url can access or @c EINA_FALSE otherwise.
 */
typedef Eina_Bool (*Ewk_Context_Check_Accessible_Path_Callback)(
    const char* tizen_app_id,
    const char* url,
    void* user_data);

/**
 * @brief Sets the given proxy to network backend of specific context.
 *        Proxy string and bypass_rule string follow rules of proxy_config.h
 *        Note that, it does not support username:password. If proxy string
 *        contains username:password, the proxy setting will not work.
 *        default auth setting see @c ewk_context_proxy_default_auth_set api
 *
 * @since_tizen 2.3
 *
 * @param[in] context context object to set proxy
 * @param[in] proxy URI to set
 * @param[in] bypass rule to set
 */
EXPORT_API void ewk_context_proxy_set(Ewk_Context* context,
                                      const char* proxy,
                                      const char* bypass_rule);

/**
 * @brief Gets the proxy URI from the network backend of specific context.
 *
 * @details It returns an internal string and should not\n
 *          be modified. The string is guaranteed to be stringshared.
 *          until next call of @c ewk_context_proxy_set api
 *          or @a context is destroyed.
 *
 * @since_tizen 2.3
 *
 * @param[in] context context object to get proxy URI
 *
 * @return current proxy URI or @c NULL if it's not set
 *
 * @see ewk_context_proxy_set
 */
EXPORT_API const char* ewk_context_proxy_uri_get(Ewk_Context* context);

/**
 * @brief Gets the proxy bypass rule from the network backend of specific
 *        context.
 *
 * @details It returns an internal string and should not
 *          be modified. The string is guaranteed to be stringshared,
 *          until next call of @c ewk_context_proxy_set api
 *          or @a context is destroyed.
 *
 * @since_tizen 3.0
 *
 * @param[in] context context object to get proxy bypass rule
 *
 * @return current proxy bypass rule or @c NULL if it's not set
 *
 * @see ewk_context_proxy_set
 */
EXPORT_API const char* ewk_context_proxy_bypass_rule_get(Ewk_Context* context);

/*
 * Set a new max size for URL in Tizen APP.
 *
 * Must be called before loading URL.
 *
 * @param context context object
 * @param max_chars  max chars for url
 */
EXPORT_API void ewk_context_url_maxchars_set(Ewk_Context* context, size_t max_chars);

/**
 * @brief Sets a proxy auth credential to network backend of specific context.
 *
 * @details Normally, proxy auth credential should be got from the callback
 *          set by ewk_view_authentication_callback_set, once the username in
 *          this API has been set with a non-null value, the authentication
 *          callback will never been invoked. Try to avoid using this API.
 *
 * @since_tizen 3.0
 *
 * @param[in] context context object to set proxy
 * @param[in] username username to set
 * @param[in] password password to set
 *
 * @return @c EINA_TRUE on success or @c EINA_FALSE on failure
 */
EXPORT_API Eina_Bool ewk_context_proxy_default_auth_set(Ewk_Context* context,
                                                        const char* username,
                                                        const char* password);

/**
 * @brief Callback for ewk_context_local_file_system_origins_get
 *
 * @since_tizen 2.3
 *
 * @param[in] origins local file system origins
 * @param[in] user_data user_data will be passsed when
 *            ewk_context_local_file_system_origins_get is called
 */
typedef void (*Ewk_Local_File_System_Origins_Get_Callback)(Eina_List* origins, void* user_data);

/**
 * @brief Callback for ewk_context_web_database_quota_for_origin_get.
 *
 * @since_tizen 2.3
 *
 * @param[in] quota web database quota
 * @param[in] user_data user_data will be passsed when
 *            ewk_context_web_database_quota_for_origin_get is called
 */
typedef void (*Ewk_Web_Database_Quota_Get_Callback)(uint64_t quota, void* user_data);

/**
 * @brief Callback for ewk_context_web_storage_origins_get.
 *
 * @since_tizen 2.3
 *
 * @param[in] origins web storage origins
 * @param[in] user_data user_data will be passsed when
 *            ewk_context_web_storage_origins_get is called
 */
typedef void (*Ewk_Web_Storage_Origins_Get_Callback)(Eina_List* origins, void* user_data);

/**
 * @brief Callback for ewk_context_web_storage_usage_for_origin_get.
 *
 * @since_tizen 2.3
 *
 * @param[in] usage usage of web storage
 * @param[in] user_data user_data will be passsed when
 *            ewk_context_web_storage_usage_for_origin_get is called
 */
typedef void (*Ewk_Web_Storage_Usage_Get_Callback)(uint64_t usage, void* user_data);

/**
 * @brief Callback for didStartDownload
 *
 * @since_tizen 2.3
 *
 * @param[in] download_url url to download
 * @param[in] user_data user_data will be passsed when download is started
 */
typedef void (*Ewk_Context_Did_Start_Download_Callback)(const char* download_url, void* user_data);

/**
 * @brief Callback for passworSaveConfirmPopupCallbackCall
 *
 * @since_tizen 2.3
 *
 * @param[in] view current view
 * @param[in] user_data user_data will be passsed when password save confirm
 *            popup show
 */
typedef void (*Ewk_Context_Password_Confirm_Popup_Callback)(Evas_Object* view, void* user_data);

/**
 * @brief Requests for setting application cache quota for origin.
 *
 * @since_tizen 2.3
 *
 * @param[in] context context object
 * @param[in] origin serucity origin
 * @param[in] quota size of quota
 *
 * @return @c EINA_TRUE on successful request or @c EINA_FALSE on failure
 */
EXPORT_API Eina_Bool ewk_context_application_cache_quota_for_origin_set(Ewk_Context* context, const Ewk_Security_Origin* origin, int64_t quota);

/**
 * @brief Requests for deleting all local file systems.
 *
 * @since_tizen 2.3
 *
 * @param[in] context context object
 *
 * @return @c EINA_TRUE on successful request or @c EINA_FALSE on failure
 */
EXPORT_API Eina_Bool ewk_context_local_file_system_all_delete(Ewk_Context* context);

/**
 * @brief Requests for deleting local file system for origin.
 *
 * @since_tizen 2.3
 *
 * @param[in] context context object
 * @param[in] origin local file system origin
 *
 * @return @c EINA_TRUE on successful request or @c EINA_FALSE on failure
 */
EXPORT_API Eina_Bool ewk_context_local_file_system_delete(Ewk_Context* context, Ewk_Security_Origin* origin);

 /**
 * @brief Requests for getting local file system origins.
 *
 * @since_tizen 2.3
 *
 * @param[in] context context object
 * @param[in] callback callback to get local file system origins
 * @param[in] user_data user_data will be passed when callback is called\n
 *    -I.e., user data will be kept until callback is called
 *
 * @return @c EINA_TRUE on successful request or @c EINA_FALSE on failure
 *
 * @see ewk_context_origins_free
 */
EXPORT_API Eina_Bool ewk_context_local_file_system_origins_get(const Ewk_Context* context, Ewk_Local_File_System_Origins_Get_Callback callback, void* user_data);

/**
 * @brief Requests for deleting web databases for origin.
 *
 * @since_tizen 2.3
 *
 * @param[in] context context object
 * @param[in] origin database origin
 *
 * @return @c EINA_TRUE on successful request or @c EINA_FALSE on failure
 */
EXPORT_API Eina_Bool ewk_context_web_database_delete(Ewk_Context* context, Ewk_Security_Origin* origin);

/**
 * @brief Requests for setting web database quota for origin.
 *
 * @since_tizen 2.3
 *
 * @param[in] context context object
 * @param[in] origin database origin
 * @param[in] quota size of quota
 *
 * @return @c EINA_TRUE on successful request or @c EINA_FALSE on failure
 */
EXPORT_API Eina_Bool ewk_context_web_database_quota_for_origin_set(Ewk_Context* context, Ewk_Security_Origin* origin, uint64_t quota);

/**
 * @brief Deletes origin that is stored in web storage db.
 *
 * @since_tizen 2.3
 *
 * @param[in] context context object
 * @param[in] origin origin of db
 *
 * @return @c EINA_TRUE on success, @c EINA_FALSE on failure
 */
EXPORT_API Eina_Bool ewk_context_web_storage_origin_delete(Ewk_Context* context, Ewk_Security_Origin* origin);

/**
 * @brief Gets list of origins that is stored in web storage db.
 *
 * @details This function allocates memory for context structure made from
 *          callback and user_data.
 *
 * @since_tizen 2.3
 *
 * @param[in] context context object
 * @param[in] callback callback to get web storage origins
 * @param[in] user_data user_data will be passed when callback is called\n
 *    -I.e., user data will be kept until callback is called
 *
 * @return @c EINA_TRUE on success, @c EINA_FALSE on failure
 *
 * @see ewk_context_origins_free()
 */
EXPORT_API Eina_Bool ewk_context_web_storage_origins_get(Ewk_Context* context, Ewk_Web_Storage_Origins_Get_Callback callback, void* user_data);

/**
 * @brief Gets usage of web storage for certain origin.
 *
 * @details This function allocates memory for context structure made from
 *          callback and user_data.
 *
 * @since_tizen 2.3
 *
 * @param[in] context context object
 * @param[in] origin security origin
 * @param[in] callback callback to get web storage usage
 * @param[in] user_data user_data will be passed when callback is called\n
 *    -I.e., user data will be kept until callback is called
 *
 * @return @c EINA_TRUE on success, @c EINA_FALSE on failure
 */
EXPORT_API Eina_Bool ewk_context_web_storage_usage_for_origin_get(Ewk_Context* context, Ewk_Security_Origin* origin, Ewk_Web_Storage_Usage_Get_Callback callback, void* user_data);

/**
 * @brief Queries if the cache is enabled
 *
 * @since_tizen 2.3
 *
 * @param[in] context context object
 *
 * @return @c EINA_TRUE is cache is enabled or @c EINA_FALSE otherwise
 */
EXPORT_API Eina_Bool ewk_context_cache_disabled_get(const Ewk_Context* context);

/**
 * @brief start memory sampler.
 *
 * @since_tizen 2.3
 *
 * @param[in] context context object
 * @param[in] timer_interval time gap to fire the timer
*/
EXPORT_API void ewk_context_memory_sampler_start(Ewk_Context* context, double timer_interval);

/**
* @brief stop memory sampler.
*
* @param[in] context context object
*/
EXPORT_API void ewk_context_memory_sampler_stop(Ewk_Context* context);

/**
 * @brief Callback for ewk_context_vibration_client_callbacks_set
 *
 * @since_tizen 2.3
 *
 * @details Type definition for a function that will be called back when vibrate
 *          request receiveed from the vibration controller.
 *
 * @param[in] vibration_time the number of vibration times
 * @param[in] user_data user_data will be passsed when
 *            ewk_context_vibration_client_callbacks_set is called
 */
typedef void (*Ewk_Vibration_Client_Vibrate_Cb)(uint64_t vibration_time, void* user_data);

/**
 * @brief Callback for ewk_context_vibration_client_callbacks_set
 *
 * @since_tizen 2.3
 *
 * @details Type definition for a function that will be called back when cancel
 *           vibration request receiveed from the vibration controller.
 *
 * @param[in] user_data user_data will be passsed when
 *            ewk_context_vibration_client_callbacks_set is called
 */
typedef void (*Ewk_Vibration_Client_Vibration_Cancel_Cb)(void* user_data);

/**
 * @brief Sets memory saving mode.
 *
 * @since_tizen 2.3
 *
 * @param[in] context context object
 * @param[in] mode or disable memory saving mode
 *
 */
EXPORT_API void ewk_context_memory_saving_mode_set(Ewk_Context* context, Eina_Bool mode);

/**
 * @brief Deletes password data from DB for given URL
 *
 * @details The API will delete the a password data from DB.
 *
 * @since_tizen 2.3
 *
 * @param[in] context context object
 * @param[in] url url saved form password
 *
 * @see ewk_context_form_password_data_list_free
 * @see ewk_context_form_password_data_list_get
 */
EXPORT_API void ewk_context_form_password_data_delete(Ewk_Context* context, const char* url);

/**
 * @brief Get all password url list
 *
 * @details The API will delete the a password data list only from the memory.\n
 *          To remove the password data for URL permenantly,
 *          use ewk_context_form_password_data_delete
 *
 * @since_tizen 2.3
 *
 * @param[in] context context object
 *
 * @return @c Eina_List of Ewk_Password_Data @c NULL otherwise
 * @see ewk_context_form_password_data_delete
 * @see ewk_context_form_password_data_list_free
 */
EXPORT_API Eina_List* ewk_context_form_password_data_list_get(Ewk_Context* context);

/**
 * @brief Deletes a given password data list
 *
 * @details The API will delete the a password data list only from the memory.\n
 *          To remove the password data for URL permenantly,
 *          use ewk_context_form_password_data_delete
 *
 * @since_tizen 2.3
 *
 * @param[in] context context object
 * @param[in] list Eina_List with Ewk_Password_Data
 *
 * @see ewk_context_form_password_data_delete
 * @see ewk_context_form_password_data_list_get
 */
EXPORT_API void ewk_context_form_password_data_list_free(Ewk_Context* context, Eina_List* list);

/**
 * @brief Requests setting of the favicon database path.
 *
 * @since_tizen 2.3
 *
 * @param[in] context context object
 * @param[in] path database path
 *
 * @return @c EINA_TRUE on successful request or @c EINA_FALSE on failure
 */
EXPORT_API Eina_Bool ewk_context_icon_database_path_set(Ewk_Context* context, const char* path);

/**
 * @brief Requests for getting web database quota for origin.
 *
 * @since_tizen 2.3
 *
 * @param[in] context context object
 * @param[in] callback callback to get web database quota
 * @param[in] user_data user_data will be passed when callback is called\n
 *    -I.e., user data will be kept until callback is called
 * @param[in] origin database origin
 *
 * @return @c EINA_TRUE on successful request or @c EINA_FALSE on failure
 */
EXPORT_API Eina_Bool ewk_context_web_database_quota_for_origin_get(Ewk_Context* context, Ewk_Web_Database_Quota_Get_Callback callback, void* user_data, Ewk_Security_Origin* origin);

/**
 * @brief Requests for getting web application cache origins.
 *
 * @since_tizen 2.3
 *
 * @param[in] context context object
 * @param[in] callback callback to get web application cache origins
 * @param[in] user_data user_data will be passsed when callback is called
 *
 * @return @c EINA_TRUE on successful request or @c EINA_FALSE on failure
 *
 * @see ewk_context_origins_free
 */
EXPORT_API Eina_Bool ewk_context_application_cache_origins_get(Ewk_Context* context, Ewk_Web_Application_Cache_Origins_Get_Callback callback, void* user_data);

/**
 * To declare application type
 *
 * @param context context object
 * @param applicationType The Ewk_Application_Type enum
 *
 */
EXPORT_API void ewk_context_application_type_set(Ewk_Context* ewkContext, const Ewk_Application_Type applicationType);

/**
 * @brief Returns the application type.
 *
 * @param[in] context The context object
 *
 * @return #Ewk_Application_Type
 */
EXPORT_API Ewk_Application_Type ewk_context_application_type_get(Ewk_Context* ewkContext);

EXPORT_API void ewk_context_form_candidate_data_clear(Ewk_Context *ewkContext);

EXPORT_API void ewk_context_form_password_data_clear(Ewk_Context *ewkContext);

/**
* Requests for setting application memory cache capacities.
*
* @param ewk_context context object
* @param cache_min_dead_capacity set min dead memory cache value
* @param cache_max_dead_capacity set max dead memory cache value
* @param cache_total_capacity set total memory cache value
*
*/
EXPORT_API void ewk_context_cache_model_memory_cache_capacities_set(
    Ewk_Context* ewk_context,
    uint32_t cache_min_dead_capacity,
    uint32_t cache_max_dead_capacity,
    uint32_t cache_total_capacity);

/**
 * Requests for getting application page cache capacity.
 *
 * @param ewk_context context object
 * @param cache_value the page memory cache value
 *
 * @return @c EINA_TRUE on successful request or @c EINA_FALSE on failure
 */
EXPORT_API Eina_Bool ewk_context_cache_model_page_cache_capacity_get(
    Ewk_Context* ewk_context,
    uint32_t* cache_value);

/**
 * Requests for setting application page cache capacity.
 *
 * @param ewk_context context object
 * @param capacity set page memory cache capacity value
 */
EXPORT_API void ewk_context_cache_model_page_cache_capacity_set(
    Ewk_Context* ewk_context,
    uint32_t capacity);

/**
 * Requests for application memory cache capacity.
 *
 * @param ewk_context context object
 * @param capacity_value the memory cache capacity value
 *
 * @return @c EINA_TRUE on successful request or @c EINA_FALSE on failure
 */
EXPORT_API Eina_Bool ewk_context_cache_model_memory_cache_capacity_get(
    Ewk_Context* ewk_context,
    uint32_t* capacity_value);

/**
 * Requests for application memory min dead capacity.
 *
 * @param ewk_context context object
 * @param capacity_value the memory min dead capacity value
 *
 * @return @c EINA_TRUE on successful request or @c EINA_FALSE on failure
 */
EXPORT_API Eina_Bool ewk_context_cache_model_memory_min_dead_capacity_get(
    Ewk_Context* ewk_context,
    uint32_t* capacity_value);

/**
 * Requests for application memory max dead capacity.
 *
 * @param ewk_context context object
 * @param capacity_value the memory max dead capacity value
 *
 * @return @c EINA_TRUE on successful request or @c EINA_FALSE on failure
 */
EXPORT_API Eina_Bool ewk_context_cache_model_memory_max_dead_capacity_get(
    Ewk_Context* ewk_context,
    uint32_t* capacity_value);

enum _Ewk_Audio_Latency_Mode {
    EWK_AUDIO_LATENCY_MODE_LOW = 0, /**< Low audio latency mode */
    EWK_AUDIO_LATENCY_MODE_MID, /**< Middle audio latency mode */
    EWK_AUDIO_LATENCY_MODE_HIGH, /**< High audio latency mode */
    EWK_AUDIO_LATENCY_MODE_ERROR = -1 /** Error */
};

typedef enum _Ewk_Audio_Latency_Mode Ewk_Audio_Latency_Mode;

/**
 * @brief Set the mode of audio latency.
 *
 * @since_tizen 3.0
 *
 * @param[in] context context object
 * @param[in] latency_mode Ewk_Audio_Latency_Mode
 *
 * @return @c EINA_TRUE if successful, @c EINA_FALSE otherwise
 */
EXPORT_API Eina_Bool ewk_context_audio_latency_mode_set(Ewk_Context* context, Ewk_Audio_Latency_Mode latency_mode);

/**
 * @brief Get the mode of audio latency.
 *
 * @since_tizen 3.0
 *
 * @param[in] context context object
 *
 * @return @c Ewk_Audio_Latency_Mode if successful, @c EWK_AUDIO_LATENCY_MODE_ERROR otherwise
 */
EXPORT_API Ewk_Audio_Latency_Mode ewk_context_audio_latency_mode_get(Ewk_Context* context);

/**
 * @brief Sets default zoom factor
 *
 * Sets default zoom factor for all pages opened with this context. Default
 * zoom can be overridden with ewk_view_page_zoom_set on per page basis.
 *
 * @since_tizen 3.0
 *
 * @param[in] context context object
 * @param[in] zoom_factor default zoom factor
 */
EXPORT_API void ewk_context_default_zoom_factor_set(Ewk_Context* context, double zoom_factor);

/**
 * @brief Gets default zoom factor
 *
 * Gets default zoom factor for all pages opened with this context.
 *
 * @since_tizen 3.0
 *
 * @param[in] context context object
 *
 * @return @c default zoom factor or negative value on error
 */
EXPORT_API double ewk_context_default_zoom_factor_get(Ewk_Context* context);

/**
 * @brief Sets callback for checking file request accessibility. When requested
 *        to load a file, need to invoke the callback to check whether the file
 *        path is accessible
 *
 * @param context context object
 * @param callback The callback of Ewk_Context_Check_Accessible_Path_Callback
 * @return @c EINA_TRUE if successful, @c EINA_FALSE otherwise
 */
EXPORT_API Eina_Bool ewk_context_check_accessible_path_callback_set(
    Ewk_Context* context,
    Ewk_Context_Check_Accessible_Path_Callback callback,
    void* user_data);

/**
 * @brief Registers url schemes as CORS enabled. It is applied
 *        for all the pages opened within the context.
 *        This API is supposed to be used by Web frameworks,
 *        not by Web browser application.
 *
 * @param context context object
 * @param schemes The URL schemes list which will be added to
 *                web security policy as valid schemes to pass CORS check.
 *
 */
EXPORT_API void ewk_context_register_url_schemes_as_cors_enabled(Ewk_Context* context,
                                                                 const Eina_List* schemes);

/**
* @}
*/

#ifdef __cplusplus
}
#endif

#endif // ewk_context_product_h
