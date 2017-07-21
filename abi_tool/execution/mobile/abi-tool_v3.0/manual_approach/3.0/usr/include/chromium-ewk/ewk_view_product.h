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
#include "ewk_enums_product.h"

#if defined(TIZEN_PEPPER_EXTENSIONS)
#include "ewk_value_product.h"
#endif

#ifdef __cplusplus
extern "C" {
#endif

/// Enum values containing text directionality values.
typedef enum {
    EWK_TEXT_DIRECTION_RIGHT_TO_LEFT,
    EWK_TEXT_DIRECTION_LEFT_TO_RIGHT
} Ewk_Text_Direction;

/// Represents types of touch event.
typedef enum {
    EWK_TOUCH_START,
    EWK_TOUCH_MOVE,
    EWK_TOUCH_END,
    EWK_TOUCH_CANCEL
} Ewk_Touch_Event_Type;

/// Creates a type name for Ewk_Touch_Point.
typedef struct _Ewk_Touch_Point Ewk_Touch_Point;

/// Represents a touch point.
struct _Ewk_Touch_Point {
    int id; /**< identifier of the touch event */
    int x; /**< the horizontal position of the touch event */
    int y; /**< the vertical position of the touch event */
    Evas_Touch_Point_State state; /**< state of the touch event */
};

typedef struct Ewk_View_Smart_Data Ewk_View_Smart_Data;
typedef struct Ewk_View_Smart_Class Ewk_View_Smart_Class;

// #if PLATFORM(TIZEN)
/// Creates a type name for _Ewk_Event_Gesture.
typedef struct Ewk_Event_Gesture Ewk_Event_Gesture;

/// Represents a gesture event.
struct Ewk_Event_Gesture {
    Ewk_Gesture_Type type; /**< type of the gesture event */
    Evas_Coord_Point position; /**< position of the gesture event */
    Evas_Point velocity; /**< velocity of the gesture event. The unit is pixel per second. */
    double scale; /**< scale of the gesture event */
    int count; /**< count of the gesture */
    unsigned int timestamp; /**< timestamp of the gesture */
};

// #if ENABLE(TIZEN_INPUT_TAG_EXTENSION)
/**
 * \enum    Ewk_Input_Type
 * @brief   Provides type of focused input element
 */
enum Ewk_Input_Type {
    EWK_INPUT_TYPE_TEXT,
    EWK_INPUT_TYPE_TELEPHONE,
    EWK_INPUT_TYPE_NUMBER,
    EWK_INPUT_TYPE_EMAIL,
    EWK_INPUT_TYPE_URL,
    EWK_INPUT_TYPE_PASSWORD,
    EWK_INPUT_TYPE_COLOR,
    EWK_INPUT_TYPE_DATE,
    EWK_INPUT_TYPE_DATETIME,
    EWK_INPUT_TYPE_DATETIMELOCAL,
    EWK_INPUT_TYPE_MONTH,
    EWK_INPUT_TYPE_TIME,
    EWK_INPUT_TYPE_WEEK
};
typedef enum Ewk_Input_Type Ewk_Input_Type;
// #endif // ENABLE(TIZEN_INPUT_TAG_EXTENSION)

// #if ENABLE(TIZEN_WEBKIT2_TEXT_SELECTION)
/**
 * \enum    Ewk_Selection_Handle_Type
 * @brief   Provides type of selection handle
 */
enum Ewk_Selection_Handle_Type {
    EWK_SELECTION_HANDLE_TYPE_LEFT,
    EWK_SELECTION_HANDLE_TYPE_RIGHT,
    EWK_SELECTION_HANDLE_TYPE_LARGE
};
typedef enum Ewk_Selection_Handle_Type Ewk_Selection_Handle_Type;
// #endif // ENABLE(TIZEN_WEBKIT2_TEXT_SELECTION)
// #endif // #if PLATFORM(TIZEN)

enum Ewk_View_Mode {
    EWK_VIEW_MODE_WINDOWED = 0,
    EWK_VIEW_MODE_FLOATING,
    EWK_VIEW_MODE_FULLSCREEN,
    EWK_VIEW_MODE_MAXIMIZED,
    EWK_VIEW_MODE_MINIMIZED
};
typedef enum Ewk_View_Mode Ewk_View_Mode;

enum Ewk_Top_Control_State {
    EWK_TOP_CONTROL_SHOWN = 1,
    EWK_TOP_CONTROL_HIDDEN = 2,
    EWK_TOP_CONTROL_BOTH = 3
};
typedef enum Ewk_Top_Control_State Ewk_Top_Control_State;

/// Ewk view's class, to be overridden by sub-classes.
struct Ewk_View_Smart_Class {
    Evas_Smart_Class sc; /**< all but 'data' is free to be changed. */
    unsigned long version;

    //Evas_Object* (*window_create)(Ewk_View_Smart_Data *sd, const Ewk_Window_Features *window_features); /**< creates a new window, requested by webkit */
    //void (*window_close)(Ewk_View_Smart_Data *sd); /**< closes a window */

    Eina_Bool (*context_menu_show)(Ewk_View_Smart_Data *sd, Evas_Coord x, Evas_Coord y, Ewk_Context_Menu *menu);
    Eina_Bool (*context_menu_hide)(Ewk_View_Smart_Data *sd);

    Eina_Bool (*popup_menu_show)(Ewk_View_Smart_Data *sd, Eina_Rectangle rect, Ewk_Text_Direction text_direction, double page_scale_factor, Eina_List* items, int selected_index);
    Eina_Bool (*popup_menu_hide)(Ewk_View_Smart_Data *sd);
    Eina_Bool (*popup_menu_update)(Ewk_View_Smart_Data *sd, Eina_Rectangle rect, Ewk_Text_Direction text_direction, Eina_List* items, int selected_index);

    Eina_Bool (*text_selection_down)(Ewk_View_Smart_Data *sd, int x, int y);
    Eina_Bool (*text_selection_up)(Ewk_View_Smart_Data *sd, int x, int y);

    Eina_Bool (*input_picker_show)(Ewk_View_Smart_Data *sd, Ewk_Input_Type inputType, const char* inputValue);

    Eina_Bool (*orientation_lock)(Ewk_View_Smart_Data* sd, int orientations);
    void (*orientation_unlock)(Ewk_View_Smart_Data* sd);

    // event handling:
    //  - returns true if handled
    //  - if overridden, have to call parent method if desired
    Eina_Bool (*focus_in)(Ewk_View_Smart_Data *sd);
    Eina_Bool (*focus_out)(Ewk_View_Smart_Data *sd);
    Eina_Bool (*fullscreen_enter)(Ewk_View_Smart_Data *sd, Ewk_Security_Origin *origin);
    Eina_Bool (*fullscreen_exit)(Ewk_View_Smart_Data *sd);
    Eina_Bool (*mouse_wheel)(Ewk_View_Smart_Data *sd, const Evas_Event_Mouse_Wheel *ev);
    Eina_Bool (*mouse_down)(Ewk_View_Smart_Data *sd, const Evas_Event_Mouse_Down *ev);
    Eina_Bool (*mouse_up)(Ewk_View_Smart_Data *sd, const Evas_Event_Mouse_Up *ev);
    Eina_Bool (*mouse_move)(Ewk_View_Smart_Data *sd, const Evas_Event_Mouse_Move *ev);
    Eina_Bool (*key_down)(Ewk_View_Smart_Data *sd, const Evas_Event_Key_Down *ev);
    Eina_Bool (*key_up)(Ewk_View_Smart_Data *sd, const Evas_Event_Key_Up *ev);

    // color picker:
    //   - Shows and hides color picker.
    Eina_Bool (*input_picker_color_request)(Ewk_View_Smart_Data *sd, int r, int g, int b, int a);
    Eina_Bool (*input_picker_color_dismiss)(Ewk_View_Smart_Data *sd);

    // storage:
    //   - Web database.
    unsigned long long (*exceeded_database_quota)(Ewk_View_Smart_Data *sd, const char *databaseName, const char *displayName, unsigned long long currentQuota, unsigned long long currentOriginUsage, unsigned long long currentDatabaseUsage, unsigned long long expectedUsage);

    Eina_Bool (*formdata_candidate_show)(Ewk_View_Smart_Data *sd, int x, int y, int w, int h);
    Eina_Bool (*formdata_candidate_hide)(Ewk_View_Smart_Data *sd);
    Eina_Bool (*formdata_candidate_update_data)(Ewk_View_Smart_Data *sd, Eina_List *dataList);
    Eina_Bool (*formdata_candidate_is_showing)(Ewk_View_Smart_Data *sd);

    Eina_Bool (*gesture_start)(Ewk_View_Smart_Data *sd, const Ewk_Event_Gesture *ev);
    Eina_Bool (*gesture_end)(Ewk_View_Smart_Data *sd, const Ewk_Event_Gesture *ev);
    Eina_Bool (*gesture_move)(Ewk_View_Smart_Data *sd, const Ewk_Event_Gesture *ev);

    void (*selection_handle_down)(Ewk_View_Smart_Data *sd, Ewk_Selection_Handle_Type handleType, int x, int y);
    void (*selection_handle_move)(Ewk_View_Smart_Data *sd, Ewk_Selection_Handle_Type handleType, int x, int y);
    void (*selection_handle_up)(Ewk_View_Smart_Data *sd, Ewk_Selection_Handle_Type handleType, int x, int y);

    Eina_Bool (*window_geometry_set)(Ewk_View_Smart_Data *sd, Evas_Coord x, Evas_Coord y, Evas_Coord width, Evas_Coord height);
    Eina_Bool (*window_geometry_get)(Ewk_View_Smart_Data *sd, Evas_Coord *x, Evas_Coord *y, Evas_Coord *width, Evas_Coord *height);
};

/**
 * The version you have to put into the version field
 * in the @a Ewk_View_Smart_Class structure.
 */
#define EWK_VIEW_SMART_CLASS_VERSION 1UL

/**
 * Initializer for whole Ewk_View_Smart_Class structure.
 *
 * @param smart_class_init initializer to use for the "base" field
 * (Evas_Smart_Class).
 *
 * @see EWK_VIEW_SMART_CLASS_INIT_NULL
 * @see EWK_VIEW_SMART_CLASS_INIT_VERSION
 * @see EWK_VIEW_SMART_CLASS_INIT_NAME_VERSION
 */
#define EWK_VIEW_SMART_CLASS_INIT(smart_class_init) {smart_class_init, EWK_VIEW_SMART_CLASS_VERSION}

/**
* Initializer to zero a whole Ewk_View_Smart_Class structure.
*
* @see EWK_VIEW_SMART_CLASS_INIT_VERSION
* @see EWK_VIEW_SMART_CLASS_INIT_NAME_VERSION
* @see EWK_VIEW_SMART_CLASS_INIT
*/
#define EWK_VIEW_SMART_CLASS_INIT_NULL EWK_VIEW_SMART_CLASS_INIT(EVAS_SMART_CLASS_INIT_NULL)

/**
 * Initializer to zero a whole Ewk_View_Smart_Class structure and set
 * name and version.
 *
 * Similar to EWK_VIEW_SMART_CLASS_INIT_NULL, but will set version field of
 * Evas_Smart_Class (base field) to latest EVAS_SMART_CLASS_VERSION and name
 * to the specific value.
 *
 * It will keep a reference to name field as a "const char *", that is,
 * name must be available while the structure is used (hint: static or global!)
 * and will not be modified.
 *
 * @see EWK_VIEW_SMART_CLASS_INIT_NULL
 * @see EWK_VIEW_SMART_CLASS_INIT_VERSION
 * @see EWK_VIEW_SMART_CLASS_INIT
 */
#define EWK_VIEW_SMART_CLASS_INIT_NAME_VERSION(name) EWK_VIEW_SMART_CLASS_INIT(EVAS_SMART_CLASS_INIT_NAME_VERSION(name))

typedef struct EwkViewImpl EwkViewImpl;

/**
 * @brief Contains an internal View data.
 *
 * It is to be considered private by users, but may be extended or
 * changed by sub-classes (that's why it's in public header file).
 */
struct Ewk_View_Smart_Data {
    Evas_Object_Smart_Clipped_Data base;
    const Ewk_View_Smart_Class* api; /**< reference to casted class instance */
    Evas_Object* self; /**< reference to owner object */
    EwkViewImpl* priv; /**< should never be accessed, c++ stuff */
    struct {
        Evas_Coord x, y, w, h; /**< last used viewport */
    } view;
    struct { /**< what changed since last smart_calculate */
        Eina_Bool any:1;

        // WebKit use these but we don't. We should remove these if we are sure
        // we do it right.
        Eina_Bool size:1;
        Eina_Bool position:1;
    } changed;
};

/**
 * Callback for ewk_view_script_execute
 *
 * @param o the view object
 * @param result_value value returned by script
 * @param user_data user data
 */
typedef void (*Ewk_View_Script_Execute_Callback)(Evas_Object* o, const char* result_value, void* user_data);

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
 * Callback for ewk_view_screenshot_contents_get_async
 *
 * @param image captured screenshot
 * @param user_data user_data will be passsed when ewk_view_screenshot_contents_get_async is called
 */
typedef void (*Ewk_Web_App_Screenshot_Captured_Callback)(Evas_Object* image, void* user_data);

/**
 * Makes request of evas image object of the specified viewArea of page asynchronously
 *
 * The returned evas image object through async callback @b should be freed after use.
 *
 * @param o view object to get specified rectangle of cairo surface.
 * @param viewArea rectangle of cairo surface.
 * @param scaleFactor scale factor of cairo surface.
 * @param canvas canvas for creating evas image.
 * @param callback result callback to get captured screenshot.
 * @param user_data will be passed when result_callback is called
 *    -I.e., user data will be kept until callback is called.
 *
 * @return @c EINA_TRUE on successful request, @c EINA_FALSE on errors.
 */
EXPORT_API Eina_Bool ewk_view_screenshot_contents_get_async(const Evas_Object* o, Eina_Rectangle viewArea, float scaleFactor, Evas* canvas, Ewk_Web_App_Screenshot_Captured_Callback callback, void* user_data);

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
 * @brief Callback for before unload popup
 *
 * @since_tizen 2.3
 *
 * @param[in] o view object
 * @param[in] message the contents of before unload popup
 * @param[in] user_data user data
 */
typedef Eina_Bool (*Ewk_View_Before_Unload_Confirm_Panel_Callback)(Evas_Object* o, const char* message, void* user_data);

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

typedef Eina_Bool (*Ewk_View_Applicacion_Cache_Permission_Callback)(Evas_Object* o, Ewk_Security_Origin* origin,  void* user_data);

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

enum Ewk_Unfocus_Direction {
    EWK_UNFOCUS_DIRECTION_NONE = 0,
    EWK_UNFOCUS_DIRECTION_FORWARD,
    EWK_UNFOCUS_DIRECTION_BACKWARD,
    EWK_UNFOCUS_DIRECTION_UP,
    EWK_UNFOCUS_DIRECTION_DOWN,
    EWK_UNFOCUS_DIRECTION_LEFT,
    EWK_UNFOCUS_DIRECTION_RIGHT,
};
typedef enum Ewk_Unfocus_Direction Ewk_Unfocus_Direction;

//#if ENABLE(TIZEN_FOCUS_UI)
typedef Eina_Bool (*Ewk_View_Unfocus_Allow_Callback)(Evas_Object* o, Ewk_Unfocus_Direction direction, void* user_data);
//#endif

/**
 * @brief To give a chance to intercept request data before sending it.
 *
 * @since_tizen 2.3
 *
 * @param[in] o view object to intercept request
 * @param[in] intercept_request Defined structure to notify requesting infomation
 * @param[in] user_data user data
 *
 * @see ewk_view_intercept_request_callback_set()
 */
typedef void (*Ewk_View_Intercept_Request_Callback)(Evas_Object* o, Ewk_Intercept_Request* intercept_request, void* user_data);

/**
 * @brief  To set Ewk_View_Intercept_Request_Callback to give a chance to intercept request data before sending it.
 *
 * @since_tizen 2.3
 *
 * @param[in] o view object to intercept request
 * @param[in] callback Defined callback
 * @param[in] user_data user data
 *
 * @see Ewk_View_Intercept_Request_Callback
 */
EXPORT_API void ewk_view_intercept_request_callback_set (Evas_Object* o, Ewk_View_Intercept_Request_Callback callback, void* user_data);

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
 * @brief Callback for ewk_view_cache_image_get
 *
 * @since_tizen 3.0
 *
 * @param[in] o view object
 * @param[in] image_url url of the image in the cache
 * @param[in] image cache image @b should be freed after use
 * @param[in] user_data user data
 */
typedef void (*Ewk_View_Cache_Image_Get_Callback)(Evas_Object* o, const char* image_url, Evas_Object* image, void* user_data);

/**
 * @brief Asynchronous request for get the cache image specified in url.
 *
 * @since_tizen 3.0
 *
 * @param[in] o view object
 * @param[in] image_url url of the image in the cache
 * @param[in] canvas canvas for creating evas image
 * @param[in] callback result callback to get cache image
 * @param[in] user_data user_data will be passed when @a callback is called
 *
 * @return @c EINA_TRUE on successful request, @c EINA_FALSE on failure
 */
EXPORT_API Eina_Bool ewk_view_cache_image_get(const Evas_Object* o, const char* image_url, Evas* canvas,
                                              Ewk_View_Cache_Image_Get_Callback callback, void* user_data);

/**
 * Callback for ewk_view_web_app_capable_get
 *
 * @param capable web application capable
 * @param user_data user_data will be passsed when ewk_view_web_app_capable_get is called
 */
typedef void (*Ewk_Web_App_Capable_Get_Callback)(Eina_Bool capable, void* user_data);

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
 * Callback for ewk_view_web_app_icon_get
 *
 * @param icon_url web application icon
 * @param user_data user_data will be passsed when ewk_view_web_app_icon_get is called
 */
typedef void (*Ewk_Web_App_Icon_URL_Get_Callback)(const char* icon_url, void* user_data);

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
 * Callback for ewk_view_web_app_icon_urls_get.
 *
 * @param icon_urls list of Ewk_Web_App_Icon_Data for web app
 * @param user_data user_data will be passsed when ewk_view_web_app_icon_urls_get is called
 */
typedef void (*Ewk_Web_App_Icon_URLs_Get_Callback)(Eina_List *icon_urls, void *user_data);

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

/*
 * Get cookies associated with an URL.
 *
 * @param o view object in which URL is opened.
 * @param url the url for which cookies needs to be obtained.
 *
 * @return @c character array containing cookies, @c NULL if no cookies are found.
 *
 * The return character array has to be owned by the application and freed when not required.
 */
EXPORT_API char* ewk_view_cookies_get(Evas_Object* o, const char* url);

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
 * @brief Gets whether horizontal panning is holding.
 *
 * @since_tizen 2.3
 *
 * @param[in] o view object to get whether horizontal panning is holding
 *
 * @return @c EINA_TRUE if horizontal panning is holding
 *         @c EINA_FALSE if not or on failure
 */
EXPORT_API Eina_Bool ewk_view_horizontal_panning_hold_get(Evas_Object* o);

/**
 * @brief Sets to hold horizontal panning.
 *
 * @since_tizen 2.3
 *
 * @param[in] o view object to set to hold horizontal panning
 * @param[in] hold @c EINA_TRUE to hold horizontal panning
 *        @c EINA_FALSE not to hold
 */
EXPORT_API void ewk_view_horizontal_panning_hold_set(Evas_Object* o, Eina_Bool hold);

/**
 * @brief Gets whether vertical panning is holding.
 *
 * @since_tizen 2.3
 *
 * @param[in] o view object to get whether vertical panning is holding
 *
 * @return @c EINA_TRUE if vertical panning is holding
 *         @c EINA_FALSE if not or on failure
 */
EXPORT_API Eina_Bool ewk_view_vertical_panning_hold_get(Evas_Object* o);

/**
  * Block/Release the vertical pan
  *
  * @param o view object on which pan is to be blocked/release
  * @param hold status of pan
  */
EXPORT_API void ewk_view_vertical_panning_hold_set(Evas_Object* o, Eina_Bool hold);

/**
 * Sets whether to draw transparent background or not.
 *
 * @param o view object to enable/disable transparent background
 * @param enabled a state to set
 *
 * @return @c EINA_TRUE on success or @c EINA_FALSE on failure
 */
EXPORT_API Eina_Bool ewk_view_draws_transparent_background_set(Evas_Object *o, Eina_Bool enabled);

/**
 * @brief Queries if transparent background is enabled.
 *
 * @since_tizen 2.3
 *
 * @param[in] o view object to get whether transparent background is enabled or not
 *
 * @return @c EINA_TRUE on enable or @c EINA_FALSE on disable
 */
EXPORT_API Eina_Bool ewk_view_draws_transparent_background_get(Evas_Object* o);

/**
 * Creates a type name for the callback function used to get the page contents.
 *
 * @param o view object
 * @param data mhtml data of the page contents
 * @param user_data user data will be passed when ewk_view_mhtml_data_get is called
 */
typedef void (*Ewk_View_MHTML_Data_Get_Callback)(Evas_Object *o, const char *data, void *user_data);

/**
 * Get page contents as MHTML data
 *
 * @param o view object to get the page contents
 * @param callback callback function to be called when the operation is finished
 * @param user_data user data to be passed to the callback function
 *
 * @return @c EINA_TRUE on success or @c EINA_FALSE otherwise
 */
EXPORT_API Eina_Bool ewk_view_mhtml_data_get(Evas_Object *o, Ewk_View_MHTML_Data_Get_Callback callback, void *user_data);

/**
 * Gets the minimum and maximum value of the scale range or -1 on failure
 *
 * @param o view object to get the minimum and maximum value of the scale range
 * @param min_scale Pointer to an double in which to store the minimum scale factor of the object.
 * @param max_scale Pointer to an double in which to store the maximum scale factor of the object.
 *
 * @note Use @c NULL pointers on the scale components you're not
 * interested in: they'll be ignored by the function.
 */
EXPORT_API void ewk_view_scale_range_get(Evas_Object* o, double* min_scale, double* max_scale);

/**
 * Returns the evas image object of the specified viewArea of page
 *
 * The returned evas image object @b should be freed after use.
 *
 * @param o view object to get specified rectangle of cairo surface.
 * @param viewArea rectangle of cairo surface.
 * @param scaleFactor scale factor of cairo surface.
 * @param canvas canvas for creating evas image.
 *
 * @return newly allocated evas image object on sucess or @c 0 on failure.
 */
EXPORT_API Evas_Object* ewk_view_screenshot_contents_get(const Evas_Object* o, Eina_Rectangle viewArea, float scaleFactor, Evas* canvas);

/**
 * Gets the possible scroll size of the given view.
 *
 * Possible scroll size is contents size minus the viewport size.
 *
 * @param o view object to get scroll size
 * @param w the pointer to store the horizontal size that is possible to scroll,
 *        may be @c 0
 * @param h the pointer to store the vertical size that is possible to scroll,
 *        may be @c 0
 *
 * @return @c EINA_TRUE on success, @c EINA_FALSE otherwise and
 *         values are zeroed
 */
EXPORT_API Eina_Bool ewk_view_scroll_size_get(const Evas_Object* o, int* w, int* h);

/**
 * Clears the highlight of searched text.
 *
 * @param o view object to find text
 *
 * @return @c EINA_TRUE on success, @c EINA_FALSE on errors
 */
EXPORT_API Eina_Bool ewk_view_text_find_highlight_clear(Evas_Object *o);

/**
 * Counts the given string in the document.
 *
 * This does not highlight the matched string and just count the matched string.\n
 *
 * As the search is carried out through the whole document,\n
 * only the following #Ewk_Find_Options are valid.\n
 *  - EWK_FIND_OPTIONS_NONE\n
 *  - EWK_FIND_OPTIONS_CASE_INSENSITIVE\n
 *  - EWK_FIND_OPTIONS_AT_WORD_START\n
 *  - EWK_FIND_OPTIONS_TREAT_MEDIAL_CAPITAL_AS_WORD_START\n
 *
 * The "text,found" callback will be called with the number of matched string.
 *
 * @since_tizen 2.3
 *
 * @param o view object to find text
 * @param text text to find
 * @param options options to find
 * @param max_match_count maximum match count to find, unlimited if 0
 *
 * @return @c EINA_TRUE on success, @c EINA_FALSE on errors
 */
EXPORT_API Eina_Bool ewk_view_text_matches_count(Evas_Object* o, const char* text, Ewk_Find_Options options, unsigned max_match_count);

/**
 * Gets the current text zoom level.
 *
 * @param o view object to get the zoom level
 *
 * @return current zoom level in use on success or @c -1.0 on failure
 */
EXPORT_API double ewk_view_text_zoom_get(const Evas_Object* o);

/**
 * Sets the current text zoom level.
 *
 * @param o view object to set the zoom level
 * @param textZoomFactor a new level to set
 *
 * @return @c EINA_TRUE on success or @c EINA_FALSE otherwise
 */
EXPORT_API Eina_Bool ewk_view_text_zoom_set(Evas_Object* o, double text_zoom_factor);

/**
 * Requests web login using password database.
 *
 * @param o view object
 *
 * @return void
 */
EXPORT_API Eina_Bool ewk_view_web_login_request(Evas_Object* ewkView);

/**
 * Executes editor command.
 *
 * @param o view object to execute command
 * @param command editor command to execute
 * @param value the value to be passed into command
 *
 * @return @c EINA_TRUE on success or @c EINA_FALSE on failure
 */
EXPORT_API Eina_Bool ewk_view_command_execute(Evas_Object* o, const char* command, const char* value);

/**
 * Enable or disable supporting of the split scrolling for overflow scroll.
 *
 * @param ewkView view object to set the support of the split scrolling for overflow scroll
 * @param enable @c EINA_TRUE to support split scrolling, @c EINA_FALSE not to support
 *
 * @return @c EINA_TRUE on success or @c EINA_FALSE on failure
 */
EXPORT_API Eina_Bool ewk_view_split_scroll_overflow_enabled_set(Evas_Object* ewkView, const Eina_Bool enabled);

/**
 * @brief Gets the staus of split scrolling supporting for overflow scroll.
 *
 * @since_tizen 2.3
 *
 * @param[in] o view object to get the status of split scrolling supporting
 *
 * @return the status of split scrolling supporting
 */
EXPORT_API Eina_Bool ewk_view_split_scroll_overflow_enabled_get(const Evas_Object* o);

/**
 * Callback for geolocation permission request feature.
 *
 * @param ewk_view view object where geolocation permission was requested
 * @param request geolocation permission request object
 * @param user_data user data passed to
 *        ewk_view_geolocation_permission_callback_set
 *
 * @return Unused
 */
typedef Eina_Bool (*Ewk_View_Geolocation_Permission_Callback)(Evas_Object* ewk_view, Ewk_Geolocation_Permission_Request* request, void* user_data);

/**
 * Sets callback which will be called upon geolocation permission request. This
 * function can be used also to unset this callback. Do that by passing NULL as
 * callback param.
 *
 * @param ewk_view view object to set the callback to
 * @param callback callback function called upon geolocation permission request
 * @param user_data user_data passsed to set callback when called
 *
 * @note When callback is set by this function, it will be called insted of
 *       "geolocation,permission,request" smart callback.
 */
EXPORT_API void ewk_view_geolocation_permission_callback_set(Evas_Object* ewk_view, Ewk_View_Geolocation_Permission_Callback callback, void* user_data);

/**
 * Callback for ewk_view_notification_permission_callback_set
 *
 * @param o view object to request the notification permission
 * @param request Ewk_Notification_Permission_Request object to get the information about notification permission request.
 * @param user_data user data
 *
 * @return returned value is not used
 */
typedef Eina_Bool (*Ewk_View_Notification_Permission_Callback)(Evas_Object *o, Ewk_Notification_Permission_Request *request, void *user_data);

/**
 * Sets the notification permission callback.
 *
 * @param o view object to request the notification permission
 * @param callback Ewk_View_Notification_Permission_Callback function to notification permission
 * @param user_data user data
 */
EXPORT_API void ewk_view_notification_permission_callback_set(Evas_Object *o, Ewk_View_Notification_Permission_Callback callback, void *user_data);

typedef Eina_Bool (*Ewk_View_Exceeded_Indexed_Database_Quota_Callback)(Evas_Object* o, Ewk_Security_Origin* origin, long long currentQuota, void* user_data);
EXPORT_API void ewk_view_exceeded_indexed_database_quota_callback_set(Evas_Object* o, Ewk_View_Exceeded_Indexed_Database_Quota_Callback callback, void* user_data);
EXPORT_API void ewk_view_exceeded_indexed_database_quota_reply(Evas_Object* o, Eina_Bool allow);


typedef Eina_Bool (*Ewk_View_Exceeded_Database_Quota_Callback)(Evas_Object* o, Ewk_Security_Origin* origin, const char* database_name, unsigned long long expectedQuota, void* user_data);
EXPORT_API void ewk_view_exceeded_database_quota_callback_set(Evas_Object* o, Ewk_View_Exceeded_Database_Quota_Callback callback, void* user_data);
EXPORT_API void ewk_view_exceeded_database_quota_reply(Evas_Object* o, Eina_Bool allow);

typedef Eina_Bool (*Ewk_View_Exceeded_Local_File_System_Quota_Callback)(Evas_Object* o, Ewk_Security_Origin* origin, long long currentQuota, void* user_data);
EXPORT_API void ewk_view_exceeded_local_file_system_quota_callback_set(Evas_Object* o, Ewk_View_Exceeded_Local_File_System_Quota_Callback callback, void* user_data);
EXPORT_API void ewk_view_exceeded_local_file_system_quota_reply(Evas_Object* o, Eina_Bool allow);

typedef Eina_Bool (*Ewk_View_User_Media_Permission_Callback)(
    Evas_Object* ewk_view,
    Ewk_User_Media_Permission_Request* user_media_permission_request,
    void* user_data);
EXPORT_API void ewk_view_user_media_permission_callback_set(
    Evas_Object* ewk_view,
    Ewk_View_User_Media_Permission_Callback callback,
    void* user_data);

/*
* add custom header
*
* @param o view object to add custom header
*
* @param name custom header name to add the custom header
*
* @param value custom header value to add the custom header
*
* @return @c EINA_TRUE on success or @c EINA_FALSE on failure
*/
EXPORT_API Eina_Bool ewk_view_custom_header_add(const Evas_Object* o, const char* name, const char* value);

/**
* remove custom header
*
* @param o view object to remove custom header
*
* @param name custom header name to remove the custom header
*
* @return @c EINA_TRUE on success or @c EINA_FALSE on failure
*/
EXPORT_API Eina_Bool ewk_view_custom_header_remove(const Evas_Object* o, const char* name);

/**
* Request to set the user agent with application name.
*
* @param o view object to set the user agent with application name
*
* @param application_name string to set the user agent
*
* @return @c EINA_TRUE on success or @c EINA_FALSE on failure
*/
EXPORT_API Eina_Bool ewk_view_application_name_for_user_agent_set(Evas_Object* o, const char* application_name);

/**
* Returns application name string.
*
* @param o view object to get the application name
*
* @return @c application name. The returned string @b should be freed by
*         eina_stringshare_del() after use.
*/
EXPORT_API const char* ewk_view_application_name_for_user_agent_get(const Evas_Object* o);

/**
 * Deprecated.
 * Notify that notification is closed.
 *
 * @param notification_list list of Ewk_Notification pointer
 *        notification_list is freed in this function.
 *
 * @return this function will always return EINA_FALSE since it is deprecated
 *
 * @deprecated
 * @see ewk_notification_closed
 */
EINA_DEPRECATED EXPORT_API Eina_Bool ewk_view_notification_closed(Evas_Object* o, Eina_List* notification_list);

/**
 * @internal
 * @brief Callback for ewk_view_notification_show_callback_set
 *
 * @since_tizen 2.3
 *
 * @param[in] o view object to request the notification show
 * @param[in] notification Ewk_Notification object to get the information about notification show request
 * @param[in] user_data user data
 */
typedef Eina_Bool (*Ewk_View_Notification_Show_Callback)(Evas_Object *o, Ewk_Notification *notification, void *user_data);

/**
 * @internal
 * @brief Sets the notification show callback.
 *
 * @since_tizen 2.3
 *
 * @param[in] o view object to request the notification show
 * @param[in] show_callback Ewk_View_Notification_Show_Callback function to notification show
 * @param[in] user_data user data
 */
EXPORT_API void ewk_view_notification_show_callback_set(Evas_Object *o, Ewk_View_Notification_Show_Callback show_callback, void *user_data);

/**
 * @internal
 * @brief Callback for ewk_view_notification_cancel_callback_set
 *
 * @since_tizen 2.3
 *
 * @param[in] o view object to request the notification cancel
 * @param[in] notification_id Ewk_Notification object to get the information about notification cancel request
 * @param[in] user_data user data
 */
typedef Eina_Bool (*Ewk_View_Notification_Cancel_Callback)(Evas_Object *o, uint64_t notification_id, void *user_data);

/**
 * @internal
 * @brief Sets the notification cancel callback.
 *
 * @since_tizen 2.3
 *
 * @param[in] o view object to request the notification show
 * @param[in] cancel_callback Ewk_View_Notification_Cancel_Callback function to notification cancel
 * @param[in] user_data user data
 */
EXPORT_API void ewk_view_notification_cancel_callback_set(Evas_Object *o, Ewk_View_Notification_Cancel_Callback cancel_callback, void *user_data);

/**
 * @brief Gets the current custom character encoding name.
 *
 * @since_tizen 2.3
 *
 * @param[in] o view object to get the current encoding
 *
 * @return @c eina_strinshare containing the current encoding, or\n
 *         @c NULL if it's not set
 */
EXPORT_API const char* ewk_view_custom_encoding_get(const Evas_Object* o);

/**
 * @brief Sets the custom character encoding and reloads the page.
 *
 * @since_tizen 2.3
 *
 * @param[in] o view to set the encoding
 * @param[in] encoding the new encoding to set or @c NULL to restore the default one
 *
 * @return @c EINA_TRUE on success @c EINA_FALSE otherwise
 */
EXPORT_API Eina_Bool ewk_view_custom_encoding_set(Evas_Object* o, const char* encoding);

/**
 * @brief Forces web page to relayout
 *
 * @since_tizen 2.3
 *
 * @param [in] o view
 */
EXPORT_API void ewk_view_force_layout(const Evas_Object* o);
/**
* @}
*/

/**
 * @brief Sets the height of top controls.
 *
 * @since_tizen 3.0
 *
 * @param[in] ewk_view view object to set height of top control
 * @param[in] top_height the height of the top controls in pixels
 * @param[in] bottom_height the height of the bottom controls in pixels
 *
 * @return @c EINA_TRUE on success @c EINA_FALSE otherwise
 */
EXPORT_API Eina_Bool ewk_view_top_controls_height_set(Evas_Object* ewk_view,
    size_t top_height, size_t bottom_height);

/**
 * @brief Sets the state of top controls.
 *
 * @since_tizen 3.0
 *
 * @note EWK_TOP_CONTROL_BOTH for current to preserve the current position.
 *
 * @param[in] ewk_view view object to set state of top control
 * @param[in] constraint constrain the top controls to being shown or hidden
 * @param[in] current set current state
 * @param[in] animate whether or not to animate to the proper state
 *
 * @return @c EINA_TRUE on success or @c EINA_FALSE on failure
 */
EXPORT_API Eina_Bool ewk_view_top_controls_state_set(Evas_Object* ewk_view, Ewk_Top_Control_State constraint,  Ewk_Top_Control_State current, Eina_Bool animation);

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

#ifdef __cplusplus
}
#endif
#endif // ewk_view_product_h
