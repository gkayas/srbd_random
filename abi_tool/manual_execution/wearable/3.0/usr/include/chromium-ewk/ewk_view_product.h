/*
   Copyright (C) 2016 Samsung Electronics. All rights reserved.

    This library is free software; you can redistribute it and/or
    modify it under the terms of the GNU Library General Public
    License as published by the Free Software Foundation; either
    version 2 of the License, or (at your option) any later version.

    This library is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
    Library General Public License for more details.

    You should have received a copy of the GNU Library General Public License
    along with this library; see the file COPYING.LIB.  If not, write to
    the Free Software Foundation, Inc., 51 Franklin Street, Fifth Floor,
    Boston, MA 02110-1301, USA.
*/

/**
 * @file    ewk_view_product.h
 * @brief   Chromium main smart object.
 *
 * This object provides view related APIs of Chromium to EFL object.
 */

#ifndef ewk_view_product_h
#define ewk_view_product_h

#include "ewk_context_product.h"
#include "ewk_view_internal.h"

#if defined(TIZEN_PEPPER_EXTENSIONS)
#include "ewk_value_product.h"
#endif

#ifdef __cplusplus
extern "C" {
#endif

/**
 * A callback to check whether allowed to run mixed content or not
 *
 * @param ewkView view object
 * @param user_data user_data will be passed when callback is called
 * @return true: allow to run mixed content. false: not allow to run mixed content
 */
typedef Eina_Bool (*Ewk_View_Run_Mixed_Content_Confirm_Callback)(Evas_Object* ewkView, void* user_data);

/**
 * @brief Creates a new EFL Chromium view object.
 *
 * @since_tizen 2.3
 *
 * @param[in] e canvas object where to create the view object
 * @param[in] data a pointer to data to restore session data
 * @param[in] length length of session data to restore session data
 *
 * @return view object on success or @c NULL on failure
 */
EXPORT_API Evas_Object* ewk_view_add_with_session_data(Evas* e, const char* data, unsigned length);

/**
 * @brief Gets the reference object for frame that represents the main frame.
 *
 * @since_tizen 2.3
 *
 * @param[in] o view object to get main frame
 *
 * @return frame reference of frame object on success, or NULL on failure
 */
EXPORT_API Ewk_Frame_Ref ewk_view_main_frame_get(Evas_Object* o);

/**
 * @brief Reply of javascript alert popup
 *
 * @since_tizen 2.3
 *
 * @param[in] o view object
 */
EXPORT_API void ewk_view_javascript_alert_reply(Evas_Object* o);

/**
 * @brief Reply of javascript confirm popup
 *
 * @since_tizen 2.3
 *
 * @param[in] o view object
 * @param[in] result result of javascript confirm popup
 */
EXPORT_API void ewk_view_javascript_confirm_reply(Evas_Object* o, Eina_Bool result);

/**
 * @brief Reply of javascript prompt popup
 *
 * @since_tizen 2.3
 *
 * @param[in] o view object
 * @param[in] result entered characters of javascript prompt popup
 */
EXPORT_API void ewk_view_javascript_prompt_reply(Evas_Object* o, const char* result);

/**
 * @brief Sets callback of before unload popup
 *
 * @since_tizen 2.3
 *
 * @param[in] o view object to set the callback
 * @param[in] callback callback function for before unload popoup
 * @param[in] user_data user data
 */
EXPORT_API void ewk_view_before_unload_confirm_panel_callback_set(Evas_Object* o, Ewk_View_Before_Unload_Confirm_Panel_Callback callback, void* user_data);

/**
 * @brief Reply of before unload popup
 *
 * @since_tizen 2.3
 *
 * @param[in] o view object
 * @param[in] result result of before unload popup
 */
EXPORT_API void ewk_view_before_unload_confirm_panel_reply(Evas_Object* o, Eina_Bool result);

/**
 * @brief Sets callback of getting application cache permission.
 *
 * @since_tizen 2.3
 *
 * @param[in] o view object to set the callback of application cache permission
 * @param[in] callback function to be called when application cache need to
 *            get permission
 * @param[in] user_data user data
 */
EXPORT_API void ewk_view_application_cache_permission_callback_set(Evas_Object* o, Ewk_View_Applicacion_Cache_Permission_Callback callback, void* user_data);

/**
 * @brief Application cache permission confirm popup reply
 *
 * @since_tizen 2.3
 *
 * @param[in] o view object to reply permission confirm popup
 * @param[in] allow of response
 */
EXPORT_API void ewk_view_application_cache_permission_reply(Evas_Object* o, Eina_Bool allow);

/**
 * @brief Set to callback to controll unfocus operation from the arrow of
 *        h/w keyboard.
 *
 * @since_tizen 2.3
 *
 * @param[in] o view object
 * @param[in] callback callback to controll unfocus operation from the arrow of
 *            h/w keyboard
 * @param[in] user_data user data
 */
EXPORT_API void ewk_view_unfocus_allow_callback_set(Evas_Object* o, Ewk_View_Unfocus_Allow_Callback callback, void* user_data);

/**
 * @brief Requests loading the given contents.
 *
 * @since_tizen 2.3
 *
 * @param[in] o view object to load document
 * @param[in] html what to load
 * @param[in] base_uri base uri to use for relative resources, may be @c 0,\n
 *        if provided @b must be an absolute uri
 *
 * @return @c EINA_TRUE on successful request, @c EINA_FALSE on errors
 */
EXPORT_API Eina_Bool ewk_view_html_contents_set(Evas_Object* o, const char* html, const char* base_uri);

/**
 * @brief Returns the evas image object for the cache image specified in url.
 *
 * @details The returned evas image object @b should be freed after use.
 *
 * @since_tizen 2.3
 *
 * @param[in] o view object to get specified rectangle of cairo surface
 * @param[in] image_url url of the image in the cache
 * @param[in] canvas canvas for creating evas image
 *
 * @return newly allocated evas image object on sucess or @c 0 on failure
 */
EXPORT_API Evas_Object* ewk_view_cache_image_get(const Evas_Object* o, const char* image_url, Evas* canvas);

/**
 * @brief Requests for getting web application capable.
 *
 * @since_tizen 2.3
 *
 * @param[in] o view object
 * @param[in] callback result callback to get web database quota
 * @param[in] user_data user_data will be passed when result_callback is
 *            called\n -I.e., user data will be kept until callback is called
 *
 * @return @c EINA_TRUE on successful request or @c EINA_FALSE on failure
 */
EXPORT_API Eina_Bool ewk_view_web_application_capable_get(Evas_Object* o, Ewk_Web_App_Capable_Get_Callback callback, void* user_data);

/**
 * @brief Requests for getting web application icon string.
 *
 * @since_tizen 2.3
 *
 * @param[in] o view object
 * @param[in] callback result callback to get web database quota
 * @param[in] user_data user_data will be passed when result_callback is
 *            called\n -I.e., user data will be kept until callback is called
 *
 * @return @c EINA_TRUE on successful request or @c EINA_FALSE on failure
 */
EXPORT_API Eina_Bool ewk_view_web_application_icon_url_get(Evas_Object* o, Ewk_Web_App_Icon_URL_Get_Callback callback, void* user_data);

/**
 * @brief Requests for getting web application icon list of
 *        Ewk_Web_App_Icon_Data.
 *
 * @since_tizen 2.3
 *
 * @param[in] o view object
 * @param[in] callback result callback to get web application icon urls
 * @param[in] user_data user_data will be passed when result_callback is
 *            called\n -I.e., user data will be kept until callback is called
 *
 * @return @c EINA_TRUE on successful request or @c EINA_FALSE on failure
 */
EXPORT_API Eina_Bool ewk_view_web_application_icon_urls_get(Evas_Object* o, Ewk_Web_App_Icon_URLs_Get_Callback callback, void* user_data);

/**
 * @brief Get the whole history(whole back & forward list) associated with this
 *        view.
 *
 * @since_tizen 2.3
 *
 * @param[in] o view object to get the history(whole back & forward list)
 *
 * @return a newly allocated history of @b newly allocated item\n
 *         instance. This memory of each item must be released with\n
 *         ewk_history_free() after use
 *
 * @see ewk_history_free()
 */
EXPORT_API Ewk_History* ewk_view_history_get(Evas_Object* o);

/**
 * @brief Gets the selection ranges
 *
 * @since_tizen 2.3
 *
 * @param[in] o view object to get theselection ranges
 * @param[out] left_rect the start lect(left rect) of the selection ranges
 * @param[out] right_rect the end lect(right rect) of the selection ranges
 *
 * @return @c EINA_TRUE on success, @c EINA_FALSE on failure
 */
EXPORT_API Eina_Bool ewk_view_text_selection_range_get(Evas_Object* o, Eina_Rectangle* left_rect, Eina_Rectangle* right_rect);

/**
 * @brief Sets the focused input element value
 *
 * @since_tizen 2.3
 *
 * @param[in] o view object to send the value
 * @param[in] value the string value to be set
 */
EXPORT_API void ewk_view_focused_input_element_value_set(Evas_Object* o, const char* value);

/**
 * @brief Gets the focused input element's value
 *
 * @since_tizen 2.3
 *
 * @param[in] o view object to get the value
 *
 * @return focused input element's value on success or NULL on failure
 */
EXPORT_API const char* ewk_view_focused_input_element_value_get(Evas_Object* o);

/**
 * @brief Selects index of current popup menu.
 *
 * @since_tizen 2.3
 *
 * @param[in] o view object contains popup menu
 * @param[in] index index of item to select
 *
 * @return @c EINA_TRUE on success, @c EINA_FALSE on failure (probably\n
 *         popup menu is not selected or index is out of range)
 */
EXPORT_API Eina_Bool ewk_view_popup_menu_select(Evas_Object* o, unsigned int index);

/**
 * @brief Selects Multiple indexes  of current popup menu.
 *
 * @since_tizen 2.3
 *
 * @param[in] o view object contains popup menu.
 * @param[in] changed_list  list of item selected and deselected
 *
 * @return @c EINA_TRUE on success, @c EINA_FALSE on failure (probably\n
 *         popup menu is not selected or index is out of range)
 */
EXPORT_API Eina_Bool ewk_view_popup_menu_multiple_select(Evas_Object* o, Eina_Inarray* changed_list);

/*
 * @brief Sets the user chosen color. To be used when implementing a color
 *        picker.
 *
 * @details The function should only be called when a color has been requested
 *          by the document.\n If called when this is not the case or when the
 *          input picker has been dismissed, this\n function will fail and
 *          return EINA_FALSE.
 *
 * @since_tizen 2.3
 *
 * @param[in] o view object contains color picker
 * @param[in] r red channel value to be set
 * @param[in] g green channel value to be set
 * @param[in] b blue channel value to be set
 * @param[in] a alpha channel value to be set
 *
 * @return @c EINA_TRUE on success @c EINA_FALSE otherwise
 */
EXPORT_API Eina_Bool ewk_view_color_picker_color_set(Evas_Object* o, int r, int g, int b, int a);

/**
 * @brief Feeds the touch event to the view.
 *
 * @since_tizen 2.3
 *
 * @param[in] o view object to feed touch event
 * @param[in] type the type of touch event
 * @param[in] points a list of points (Ewk_Touch_Point) to process
 * @param[in] modifiers an Evas_Modifier handle to the list of modifier keys\n
 *        registered in the Evas. Users can get the Evas_Modifier from the
 *        Evas\n using evas_key_modifier_get() and can set each modifier key
 *        using\n evas_key_modifier_on() and evas_key_modifier_off()
 *
 * @return @c EINA_TRUE on success or @c EINA_FALSE on failure
 */
EXPORT_API Eina_Bool ewk_view_feed_touch_event(Evas_Object* o, Ewk_Touch_Event_Type type, const Eina_List* points, const Evas_Modifier* modifiers);

/**
 * Creates a type name for the callback function used to get the background color.
 *
 * @param o view object
 * @param r red color component
 * @param g green color component
 * @param b blue color component
 * @param a transparency
 * @param user_data user data will be passed when ewk_view_bg_color_get is called
 */
typedef void (*Ewk_View_Background_Color_Get_Callback)(Evas_Object *o, int r, int g, int b, int a, void* user_data);

/**
 * Gets the background color and transparency of the view.
 *
 * @param o view object to get the background color from
 * @param callback callback function
 * @param user_data user data will be passed when the callback is called
 *
 * @return @c EINA_TRUE on success or @c EINA_FALSE on failure
 *
 *  On success the background color of the view object o is retrieved
 *  in the callback function
 */
EXPORT_API Eina_Bool ewk_view_bg_color_get(Evas_Object *o, Ewk_View_Background_Color_Get_Callback callback, void *user_data);

/**
 * @brief Sets the visibility of main frame scrollbar.
 *
 * @since_tizen 2.3
 *
 * @param[in] o view object
 * @param[in] visible visibility of main frame scrollbar
 *
 * @return @c EINA_TRUE on success or @c EINA_FALSE on failure
 */
EXPORT_API Eina_Bool ewk_view_main_frame_scrollbar_visible_set(Evas_Object* o, Eina_Bool visible);

/**
 * Callback for ewk_view_main_frame_scrollbar_visible_get
 *
 * @param o view object
 * @param visibility visibility of main frame scrollbar
 * @param user_data user data passed to ewk_view_main_frame_scrollbar_visible_get
 */
typedef void (*Ewk_View_Main_Frame_Scrollbar_Visible_Get_Callback)(Evas_Object* o, Eina_Bool visible, void* user_data);

/**
 * @brief Gets the visibility of main frame scrollbar.
 *
 * @since_tizen 3.0
 *
 * @param[in] o view object
 * @param callback callback function
 * @param user_data user data will be passed when the callback is caller
 *
 * @return @c EINA_TRUE on success or @c EINA_FALSE on failure
 *
 *  On success the visibility of the scrollbar of the view object o is retrieved
 *  in the callback function
 */
EXPORT_API Eina_Bool ewk_view_main_frame_scrollbar_visible_get(Evas_Object* view, Ewk_View_Main_Frame_Scrollbar_Visible_Get_Callback callback, void* user_data);

/**
 * @brief Gets the session data to be saved in a persistent store on
 *        browser exit
 *
 * @since_tizen 2.3
 *
 * @param[in] o view object whose session needs to be stored.
 * @param[in] data out parameter session data
 * @param[in] length out parameter length of session data
 */
EXPORT_API void ewk_view_session_data_get(Evas_Object* o, const char** data, unsigned* length);

/**
 * @brief Reloads the current page's document without cache.
 *
 * @since_tizen 2.3
 *
 * @param[in] o view object to reload current document
 *
 * @return @c EINA_TRUE on success or @c EINA_FALSE otherwise
 */
EXPORT_API Eina_Bool ewk_view_reload_bypass_cache(Evas_Object* o);

/**
 * @brief Creates a new hit test for the given veiw object and point.
 *
 * @since_tizen 2.3
 *
 * @remarks The returned object should be freed by ewk_hit_test_free().
 *
 * @param[in] o view object to do hit test on
 * @param[in] x the horizontal position to query
 * @param[in] y the vertical position to query
 * @param[in] hit_test_mode the #Ewk_Hit_Test_Mode enum value to query
 *
 * @return a newly allocated hit test on success, @c 0 otherwise
 */
EXPORT_API Ewk_Hit_Test* ewk_view_hit_test_new(Evas_Object* o, int x, int y, int hit_test_mode);

/**
 * Create PDF file of page contents
 *
 * @param o view object to get page contents.
 * @param width the suface width of PDF file.
 * @param height the suface height of PDF file.
 * @param fileName the file name for creating PDF file.
 *
 * @return @c EINA_TRUE on success or @c EINA_FALSE on failure
 */
/* This return value is status of the request not the status of actual operation.
 * There should be some callback to get the actual status or reason of failure.
 */
EXPORT_API Eina_Bool ewk_view_contents_pdf_get(Evas_Object* o, int width, int height, const char* fileName);

/**
 * Requests for setting callback function
 *
 * @param ewkView view object
 * @param user_data user_data will be passed when callback is called
 * @param callback callback function
 */
EXPORT_API void ewk_view_run_mixed_content_confirm_callback_set(Evas_Object* ewkView, Ewk_View_Run_Mixed_Content_Confirm_Callback callback, void* user_data);

/**
 * Returns the current favicon of view object.
 *
 * @param item view object to get current icon URL
 *
 * @return current favicon on success or @c NULL if unavailable or on failure.
 * The returned Evas_Object needs to be freed after use.
 */
EXPORT_API Evas_Object *ewk_view_favicon_get(const Evas_Object *item);

/**
 * To resume new url network loading
 *
 * @param item view object to resume new url loading
 *
 */
EXPORT_API void ewk_view_resume_network_loading(Evas_Object* item);

EXPORT_API void ewk_view_poweroff_suspend(Evas_Object *item);

/**
 * To suspend all url loading
 *
 * @param item view object to suspend url loading
 *
 */
EXPORT_API void ewk_view_suspend_network_loading(Evas_Object* item);

/**
 * This function should be use for browser edge scroll.
 * It can also be used when the mouse pointer is out of webview.
 * Scrolls webpage of view by dx and dy.
 *
 * @param item view object to scroll
 * @param dx horizontal offset to scroll
 * @param dy vertical offset to scroll
 * @return @c EINA_TRUE on success, @c EINA_FALSE otherwise.
 */
EXPORT_API Eina_Bool ewk_view_edge_scroll_by(Evas_Object *item, int dx, int dy);

/**
 * Allow a browser to set its own cursor by setting a flag
 * which prevents setting a default web page cursor.
 *
 * @param ewkView view object
 * @param enable EINA_TRUE - prevent update of cursor by engine
 *               EINA_FALSE - allow for update of cursor by engine
 */
EXPORT_API void ewk_view_set_cursor_by_client(Evas_Object* ewkView, Eina_Bool enable);

/**
 * Reply of running mixed content or not
 *
 * @param ewkView view object
 * @param result reply
 */
EXPORT_API void ewk_view_run_mixed_content_confirm_reply(Evas_Object* ewkView, Eina_Bool result);

/**
 * Sets the cover-area (soon rect)  multiplier.
 *
 * @param ewkView view object
 * @param coverAreaMultiplier the multiplier of cover-area.
 */
EXPORT_API void ewk_view_tile_cover_area_multiplier_set(
    Evas_Object* ewkView,
    float cover_area_multiplier);

/**
 * Set to enabled/disabled clear tiles on hide.
 *
 * @param ewkView view object
 * @param enabled/disabled a state to set
 *
 */
EXPORT_API void ewk_view_clear_tiles_on_hide_enabled_set(Evas_Object* ewkView,
                                                         Eina_Bool enable);

/**
 * @brief Callback for ewk_view_is_video_playing
 *
 * @param[in] o the view object
 * @param[in] is_playing video is playing or not
 * @param[in] user_data user_data will be passsed when ewk_view_is_video_playing is
 *                      called
 */
typedef void (*Ewk_Is_Video_Playing_Callback)(Evas_Object* o, Eina_Bool is_playing, void* user_data);

/**
 * @brief Asynchronous request for check if there is a video playing in the given view
 *
 * @param[in] o The view object
 * @param[in] callback result callback to get web application capable
 * @param[in] user_data user_data will be passed when result_callback is called
 *
 * @return @c EINA_TRUE on successful request or @c EINA_FALSE on failure
 */
EXPORT_API Eina_Bool ewk_view_is_video_playing(Evas_Object* o, Ewk_Is_Video_Playing_Callback callback, void* user_data);

/**
 * Callback for ewk_view_stop_video
 *
 * @param o view object
 * @param is_stopped video is stopped or not
 * @param user_data user_data will be passsed when ewk_view_stop_video is called
 */
typedef void (*Ewk_Stop_Video_Callback)(Evas_Object* o, Eina_Bool is_stopped, void* user_data);

/**
 * Asynchronous request for stopping any playing video in the given view
 *
 * @param[in] o The view object
 * @param[in] callback result callback to get web application capable
 * @param[in] user_data user_data will be passed when result_callback is called
 *
 * @return @c EINA_TRUE if any video was stopped or @c EINA_FALSE is there was no active video
 */
EXPORT_API Eina_Bool ewk_view_stop_video(Evas_Object* o, Ewk_Stop_Video_Callback callback, void* user_data);

/**
 * @brief Sets the support of video hole and video window, Use H/W overlay for performance of video output
 *
 * @since_tizen 3.0
 *
 * @param[in] o the view object
 * @param[in] o the top-level window object
 * @param[in] enable EINA_TRUE to set on support the video hole,
 *            EINA_FALSE otherwise
 * @param[in] enable EINA_TRUE to set on the video window of video hole,
 *            EINA_FALSE to set on the video windowless of video hole
 *
 * @return return @c EINA_TRUE on success or @c EINA_FALSE on failure
 */
EXPORT_API Eina_Bool ewk_view_set_support_video_hole(Evas_Object* ewkView, Evas_Object* window, Eina_Bool enable, Eina_Bool isVideoWindow);

/**
 * @brief Sets the support of 4K video, Customize the device pixel ratio for
 * video plane.
 *
 * @since_tizen 3.0
 *
 * @note Should be used after ewk_view_url_set().
 *
 * @param[in] o the view object
 * @param[in] o enabled a state to set
 *
 * @return return @c EINA_TRUE on success or @c EINA_FALSE on failure
 */
EXPORT_API Eina_Bool ewk_view_set_custom_device_pixel_ratio(Evas_Object* ewkView, Eina_Bool enabled);

#if defined(TIZEN_PEPPER_EXTENSIONS)

/**
 * Callback for the generic sync call.
 * It requests for performing operation/call giving its name. Arguments
 * and return value is operation/call specific.
 *
 * @param[in] name requested call name
 * @param[in] arguments call argumets, format is defined by opertion itself
 * @param[in] user_data user_data will be passed when result_callback is called
 *
 * @return return value from the call, format is defind by operation itself
 */
typedef Ewk_Value (*Generic_Sync_Call_Callback)(const char* name, Ewk_Value arguments, void* user_data);

/**
 * Sets the function pointer for the generic sync call
 *
 * @param ewk_view view object to set the function pointer in
 * @param cb pointer to the function
 * @param user_data pointer to user data to be passed to the function when
 *        it's being called
 */
EXPORT_API void ewk_view_widget_pepper_extension_callback_set(Evas_Object* ewk_view, Generic_Sync_Call_Callback cb, void* user_data);

/**
* Sets the pepper widget extension info
*
* @param ewk_view view object to set the info in
* @param widget_pepper_ext_info the Ewk_Value containing the information
*/
EXPORT_API void ewk_view_widget_pepper_extension_info_set(Evas_Object* ewk_view, Ewk_Value widget_pepper_ext_info);

#endif  // defined(TIZEN_PEPPER_EXTENSIONS)

/**
 * @brief Sends key event.
 *
 * @since_tizen 2.4
 *
 * @param[in] o The view object
 * @param[in] key_event Evas_Event_Key_Down struct or Evas_Event_Key_Up struct
 * @param[in] isPress EINA_TRUE: keydown, EINA_FALSE: keyup
 * @return @c EINA_TRUE on success,\n
 *         otherwise @c EINA_FALSE
 */
EXPORT_API Eina_Bool ewk_view_send_key_event(Evas_Object* o, void* key_event, Eina_Bool is_press);

/**
 * @brief Sets whether the ewk_view supports the key events or not.
 *
 * @since_tizen 2.4
 *
 * @note Should be used after ewk_view_url_set().
 *
 * @remarks The ewk_view will support the key events if EINA_TRUE or not support the
 *          key events otherwise. The default value is EINA_TRUE.
 *
 * @param[in] o The view object
 * @param[in] enabled a state to set
 *
 * @return @c EINA_TRUE on success,\n
 *         otherwise @c EINA_FALSE
 */
EXPORT_API Eina_Bool ewk_view_key_events_enabled_set(Evas_Object *o, Eina_Bool enabled);

enum Ewk_Scrollbar_Orientation {
  EWK_HORIZONTAL_SCROLLBAR = 0,
  EWK_VERTICAL_SCROLLBAR
};

typedef enum Ewk_Scrollbar_Orientation Ewk_Scrollbar_Orientation;

struct Ewk_Scrollbar_Data {
  Ewk_Scrollbar_Orientation orientation; /**< scrollbar orientation */
  Eina_Bool focused;                     /**< isFocused */
};

typedef struct Ewk_Scrollbar_Data Ewk_Scrollbar_Data;

/**
 * @brief Adds an item to back forward list
 *
 * @since_tizen 2.4
 *
 * @param[in] o The view object
 * @param[in] item The back-forward list item instance
 * @return @c EINA_TRUE on success,\n
 *         otherwise @c EINA_FALSE
 */
EXPORT_API Eina_Bool
ewk_view_add_item_to_back_forward_list(Evas_Object* o,
                                       const Ewk_Back_Forward_List_Item* item);

/**
* @}
*/

#ifdef __cplusplus
}
#endif
#endif // ewk_view_product_h
