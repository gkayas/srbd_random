#ifndef TIZEN_EXTENSION_SERVER_PROTOCOL_H
#define TIZEN_EXTENSION_SERVER_PROTOCOL_H

#ifdef  __cplusplus
extern "C" {
#endif

#include <stdint.h>
#include <stddef.h>
#include "wayland-server.h"

struct wl_client;
struct wl_resource;

struct tizen_clipboard;
struct tizen_destination_mode;
struct tizen_display_policy;
struct tizen_effect;
struct tizen_embedded_compositor;
struct tizen_gesture;
struct tizen_indicator;
struct tizen_input_device;
struct tizen_input_device_manager;
struct tizen_keyrouter;
struct tizen_launch_image;
struct tizen_launchscreen;
struct tizen_policy;
struct tizen_position;
struct tizen_resource;
struct tizen_screenmirror;
struct tizen_screenshooter;
struct tizen_subsurface_watcher;
struct tizen_surface;
struct tizen_video;
struct tizen_video_object;
struct tizen_viewport;
struct tizen_visibility;
struct wl_buffer;
struct wl_output;
struct wl_seat;
struct wl_subsurface;
struct wl_surface;

extern const struct wl_interface tizen_surface_interface;
extern const struct wl_interface tizen_resource_interface;
extern const struct wl_interface tizen_policy_interface;
extern const struct wl_interface tizen_visibility_interface;
extern const struct wl_interface tizen_position_interface;
extern const struct wl_interface tizen_gesture_interface;
extern const struct wl_interface tizen_keyrouter_interface;
extern const struct wl_interface tizen_screenshooter_interface;
extern const struct wl_interface tizen_screenmirror_interface;
extern const struct wl_interface tizen_video_interface;
extern const struct wl_interface tizen_video_object_interface;
extern const struct wl_interface tizen_subsurface_watcher_interface;
extern const struct wl_interface tizen_viewport_interface;
extern const struct wl_interface tizen_destination_mode_interface;
extern const struct wl_interface tizen_embedded_compositor_interface;
extern const struct wl_interface tizen_input_device_manager_interface;
extern const struct wl_interface tizen_input_device_interface;
extern const struct wl_interface tizen_launchscreen_interface;
extern const struct wl_interface tizen_launch_image_interface;
extern const struct wl_interface tizen_effect_interface;
extern const struct wl_interface tizen_display_policy_interface;
extern const struct wl_interface tizen_indicator_interface;
extern const struct wl_interface tizen_clipboard_interface;

struct tizen_surface_interface {
	/**
	 * get_tizen_resource - (none)
	 * @id: (none)
	 * @surface: (none)
	 */
	void (*get_tizen_resource)(struct wl_client *client,
				   struct wl_resource *resource,
				   uint32_t id,
				   struct wl_resource *surface);
};


struct tizen_resource_interface {
	/**
	 * destroy - (none)
	 */
	void (*destroy)(struct wl_client *client,
			struct wl_resource *resource);
};

#define TIZEN_RESOURCE_RESOURCE_ID	0

#define TIZEN_RESOURCE_RESOURCE_ID_SINCE_VERSION	1

static inline void
tizen_resource_send_resource_id(struct wl_resource *resource_, uint32_t id)
{
	wl_resource_post_event(resource_, TIZEN_RESOURCE_RESOURCE_ID, id);
}

#ifndef TIZEN_POLICY_WIN_TYPE_ENUM
#define TIZEN_POLICY_WIN_TYPE_ENUM
enum tizen_policy_win_type {
	TIZEN_POLICY_WIN_TYPE_NONE = 0,
	TIZEN_POLICY_WIN_TYPE_TOPLEVEL = 1,
	TIZEN_POLICY_WIN_TYPE_FULLSCREEN = 2,
	TIZEN_POLICY_WIN_TYPE_MAXIMIZED = 3,
	TIZEN_POLICY_WIN_TYPE_TRANSIENT = 4,
	TIZEN_POLICY_WIN_TYPE_MENU = 5,
	TIZEN_POLICY_WIN_TYPE_DND = 6,
	TIZEN_POLICY_WIN_TYPE_CUSTOM = 7,
	TIZEN_POLICY_WIN_TYPE_NOTIFICATION = 8,
	TIZEN_POLICY_WIN_TYPE_UTILITY = 9,
	TIZEN_POLICY_WIN_TYPE_DIALOG = 10,
	TIZEN_POLICY_WIN_TYPE_DOCK = 11,
	TIZEN_POLICY_WIN_TYPE_SPLASH = 12,
};
#endif /* TIZEN_POLICY_WIN_TYPE_ENUM */

#ifndef TIZEN_POLICY_CONFORMANT_PART_ENUM
#define TIZEN_POLICY_CONFORMANT_PART_ENUM
enum tizen_policy_conformant_part {
	TIZEN_POLICY_CONFORMANT_PART_INDICATOR = 0,
	TIZEN_POLICY_CONFORMANT_PART_KEYBOARD = 1,
	TIZEN_POLICY_CONFORMANT_PART_CLIPBOARD = 2,
};
#endif /* TIZEN_POLICY_CONFORMANT_PART_ENUM */

#ifndef TIZEN_POLICY_ERROR_STATE_ENUM
#define TIZEN_POLICY_ERROR_STATE_ENUM
enum tizen_policy_error_state {
	TIZEN_POLICY_ERROR_STATE_NONE = 0,
	TIZEN_POLICY_ERROR_STATE_PERMISSION_DENIED = 1,
};
#endif /* TIZEN_POLICY_ERROR_STATE_ENUM */

#ifndef TIZEN_POLICY_LEVEL_ENUM
#define TIZEN_POLICY_LEVEL_ENUM
enum tizen_policy_level {
	TIZEN_POLICY_LEVEL_1 = 0,
	TIZEN_POLICY_LEVEL_2 = 1,
	TIZEN_POLICY_LEVEL_3 = 2,
	TIZEN_POLICY_LEVEL_NONE = -1,
	TIZEN_POLICY_LEVEL_DEFAULT = 10,
	TIZEN_POLICY_LEVEL_MEDIUM = 20,
	TIZEN_POLICY_LEVEL_HIGH = 30,
	TIZEN_POLICY_LEVEL_TOP = 40,
};
#endif /* TIZEN_POLICY_LEVEL_ENUM */

#ifndef TIZEN_POLICY_MODE_ENUM
#define TIZEN_POLICY_MODE_ENUM
enum tizen_policy_mode {
	TIZEN_POLICY_MODE_DEFAULT = 0,
	TIZEN_POLICY_MODE_ALWAYS_ON = 1,
};
#endif /* TIZEN_POLICY_MODE_ENUM */

#ifndef TIZEN_POLICY_STACK_MODE_ENUM
#define TIZEN_POLICY_STACK_MODE_ENUM
enum tizen_policy_stack_mode {
	TIZEN_POLICY_STACK_MODE_NONE = 0,
	TIZEN_POLICY_STACK_MODE_ABOVE = 1,
	TIZEN_POLICY_STACK_MODE_BELOW = 2,
};
#endif /* TIZEN_POLICY_STACK_MODE_ENUM */

struct tizen_policy_interface {
	/**
	 * get_visibility - (none)
	 * @id: new visibility object
	 * @surface: surface object
	 */
	void (*get_visibility)(struct wl_client *client,
			       struct wl_resource *resource,
			       uint32_t id,
			       struct wl_resource *surface);
	/**
	 * get_position - (none)
	 * @id: new position object
	 * @surface: surface object
	 */
	void (*get_position)(struct wl_client *client,
			     struct wl_resource *resource,
			     uint32_t id,
			     struct wl_resource *surface);
	/**
	 * activate - (none)
	 * @surface: surface object
	 */
	void (*activate)(struct wl_client *client,
			 struct wl_resource *resource,
			 struct wl_resource *surface);
	/**
	 * activate_below_by_res_id - (none)
	 * @res_id: (none)
	 * @below_res_id: (none)
	 */
	void (*activate_below_by_res_id)(struct wl_client *client,
					 struct wl_resource *resource,
					 uint32_t res_id,
					 uint32_t below_res_id);
	/**
	 * raise - (none)
	 * @surface: surface object
	 */
	void (*raise)(struct wl_client *client,
		      struct wl_resource *resource,
		      struct wl_resource *surface);
	/**
	 * lower - (none)
	 * @surface: surface object
	 */
	void (*lower)(struct wl_client *client,
		      struct wl_resource *resource,
		      struct wl_resource *surface);
	/**
	 * lower_by_res_id - (none)
	 * @res_id: (none)
	 */
	void (*lower_by_res_id)(struct wl_client *client,
				struct wl_resource *resource,
				uint32_t res_id);
	/**
	 * set_focus_skip - (none)
	 * @surface: surface object
	 */
	void (*set_focus_skip)(struct wl_client *client,
			       struct wl_resource *resource,
			       struct wl_resource *surface);
	/**
	 * unset_focus_skip - (none)
	 * @surface: surface object
	 */
	void (*unset_focus_skip)(struct wl_client *client,
				 struct wl_resource *resource,
				 struct wl_resource *surface);
	/**
	 * set_role - (none)
	 * @surface: surface object
	 * @role: (none)
	 */
	void (*set_role)(struct wl_client *client,
			 struct wl_resource *resource,
			 struct wl_resource *surface,
			 const char *role);
	/**
	 * set_type - (none)
	 * @surface: (none)
	 * @win_type: (none)
	 */
	void (*set_type)(struct wl_client *client,
			 struct wl_resource *resource,
			 struct wl_resource *surface,
			 uint32_t win_type);
	/**
	 * set_conformant - (none)
	 * @surface: surface object
	 */
	void (*set_conformant)(struct wl_client *client,
			       struct wl_resource *resource,
			       struct wl_resource *surface);
	/**
	 * unset_conformant - (none)
	 * @surface: surface object
	 */
	void (*unset_conformant)(struct wl_client *client,
				 struct wl_resource *resource,
				 struct wl_resource *surface);
	/**
	 * get_conformant - (none)
	 * @surface: surface object
	 */
	void (*get_conformant)(struct wl_client *client,
			       struct wl_resource *resource,
			       struct wl_resource *surface);
	/**
	 * set_notification_level - (none)
	 * @surface: (none)
	 * @level: (none)
	 */
	void (*set_notification_level)(struct wl_client *client,
				       struct wl_resource *resource,
				       struct wl_resource *surface,
				       int32_t level);
	/**
	 * set_transient_for - (none)
	 * @child_id: (none)
	 * @parent_id: (none)
	 */
	void (*set_transient_for)(struct wl_client *client,
				  struct wl_resource *resource,
				  uint32_t child_id,
				  uint32_t parent_id);
	/**
	 * unset_transient_for - (none)
	 * @child_id: (none)
	 */
	void (*unset_transient_for)(struct wl_client *client,
				    struct wl_resource *resource,
				    uint32_t child_id);
	/**
	 * set_window_screen_mode - (none)
	 * @surface: (none)
	 * @mode: (none)
	 */
	void (*set_window_screen_mode)(struct wl_client *client,
				       struct wl_resource *resource,
				       struct wl_resource *surface,
				       uint32_t mode);
	/**
	 * place_subsurface_below_parent - (none)
	 * @subsurface: (none)
	 */
	void (*place_subsurface_below_parent)(struct wl_client *client,
					      struct wl_resource *resource,
					      struct wl_resource *subsurface);
	/**
	 * set_subsurface_stand_alone - (none)
	 * @subsurface: (none)
	 */
	void (*set_subsurface_stand_alone)(struct wl_client *client,
					   struct wl_resource *resource,
					   struct wl_resource *subsurface);
	/**
	 * get_subsurface - (none)
	 * @id: (none)
	 * @surface: (none)
	 * @parent_id: (none)
	 */
	void (*get_subsurface)(struct wl_client *client,
			       struct wl_resource *resource,
			       uint32_t id,
			       struct wl_resource *surface,
			       uint32_t parent_id);
	/**
	 * set_opaque_state - (none)
	 * @surface: (none)
	 * @state: (none)
	 */
	void (*set_opaque_state)(struct wl_client *client,
				 struct wl_resource *resource,
				 struct wl_resource *surface,
				 int32_t state);
	/**
	 * iconify - (none)
	 * @surface: surface object
	 */
	void (*iconify)(struct wl_client *client,
			struct wl_resource *resource,
			struct wl_resource *surface);
	/**
	 * uniconify - (none)
	 * @surface: surface object
	 */
	void (*uniconify)(struct wl_client *client,
			  struct wl_resource *resource,
			  struct wl_resource *surface);
	/**
	 * add_aux_hint - (none)
	 * @surface: (none)
	 * @id: (none)
	 * @name: (none)
	 * @value: (none)
	 */
	void (*add_aux_hint)(struct wl_client *client,
			     struct wl_resource *resource,
			     struct wl_resource *surface,
			     int32_t id,
			     const char *name,
			     const char *value);
	/**
	 * change_aux_hint - (none)
	 * @surface: (none)
	 * @id: (none)
	 * @value: (none)
	 */
	void (*change_aux_hint)(struct wl_client *client,
				struct wl_resource *resource,
				struct wl_resource *surface,
				int32_t id,
				const char *value);
	/**
	 * del_aux_hint - (none)
	 * @surface: (none)
	 * @id: (none)
	 */
	void (*del_aux_hint)(struct wl_client *client,
			     struct wl_resource *resource,
			     struct wl_resource *surface,
			     int32_t id);
	/**
	 * get_supported_aux_hints - (none)
	 * @surface: (none)
	 */
	void (*get_supported_aux_hints)(struct wl_client *client,
					struct wl_resource *resource,
					struct wl_resource *surface);
	/**
	 * set_background_state - (none)
	 * @pid: (none)
	 */
	void (*set_background_state)(struct wl_client *client,
				     struct wl_resource *resource,
				     uint32_t pid);
	/**
	 * unset_background_state - (none)
	 * @pid: (none)
	 */
	void (*unset_background_state)(struct wl_client *client,
				       struct wl_resource *resource,
				       uint32_t pid);
	/**
	 * set_floating_mode - (none)
	 * @surface: surface object
	 */
	void (*set_floating_mode)(struct wl_client *client,
				  struct wl_resource *resource,
				  struct wl_resource *surface);
	/**
	 * unset_floating_mode - (none)
	 * @surface: surface object
	 */
	void (*unset_floating_mode)(struct wl_client *client,
				    struct wl_resource *resource,
				    struct wl_resource *surface);
	/**
	 * set_stack_mode - (none)
	 * @surface: (none)
	 * @mode: (none)
	 */
	void (*set_stack_mode)(struct wl_client *client,
			       struct wl_resource *resource,
			       struct wl_resource *surface,
			       uint32_t mode);
	/**
	 * activate_above_by_res_id - (none)
	 * @res_id: (none)
	 * @above_res_id: (none)
	 */
	void (*activate_above_by_res_id)(struct wl_client *client,
					 struct wl_resource *resource,
					 uint32_t res_id,
					 uint32_t above_res_id);
	/**
	 * get_subsurface_watcher - (none)
	 * @id: (none)
	 * @surface: (none)
	 * @since: 2
	 */
	void (*get_subsurface_watcher)(struct wl_client *client,
				       struct wl_resource *resource,
				       uint32_t id,
				       struct wl_resource *surface);
};

#define TIZEN_POLICY_CONFORMANT	0
#define TIZEN_POLICY_CONFORMANT_AREA	1
#define TIZEN_POLICY_NOTIFICATION_DONE	2
#define TIZEN_POLICY_TRANSIENT_FOR_DONE	3
#define TIZEN_POLICY_WINDOW_SCREEN_MODE_DONE	4
#define TIZEN_POLICY_ICONIFY_STATE_CHANGED	5
#define TIZEN_POLICY_SUPPORTED_AUX_HINTS	6
#define TIZEN_POLICY_ALLOWED_AUX_HINT	7
#define TIZEN_POLICY_AUX_MESSAGE	8

#define TIZEN_POLICY_CONFORMANT_SINCE_VERSION	1
#define TIZEN_POLICY_CONFORMANT_AREA_SINCE_VERSION	1
#define TIZEN_POLICY_NOTIFICATION_DONE_SINCE_VERSION	1
#define TIZEN_POLICY_TRANSIENT_FOR_DONE_SINCE_VERSION	1
#define TIZEN_POLICY_WINDOW_SCREEN_MODE_DONE_SINCE_VERSION	1
#define TIZEN_POLICY_ICONIFY_STATE_CHANGED_SINCE_VERSION	1
#define TIZEN_POLICY_SUPPORTED_AUX_HINTS_SINCE_VERSION	1
#define TIZEN_POLICY_ALLOWED_AUX_HINT_SINCE_VERSION	1
#define TIZEN_POLICY_AUX_MESSAGE_SINCE_VERSION	1

static inline void
tizen_policy_send_conformant(struct wl_resource *resource_, struct wl_resource *surface, uint32_t is_conformant)
{
	wl_resource_post_event(resource_, TIZEN_POLICY_CONFORMANT, surface, is_conformant);
}

static inline void
tizen_policy_send_conformant_area(struct wl_resource *resource_, struct wl_resource *surface, uint32_t conformant_part, uint32_t state, int32_t x, int32_t y, int32_t w, int32_t h)
{
	wl_resource_post_event(resource_, TIZEN_POLICY_CONFORMANT_AREA, surface, conformant_part, state, x, y, w, h);
}

static inline void
tizen_policy_send_notification_done(struct wl_resource *resource_, struct wl_resource *surface, int32_t level, uint32_t error_state)
{
	wl_resource_post_event(resource_, TIZEN_POLICY_NOTIFICATION_DONE, surface, level, error_state);
}

static inline void
tizen_policy_send_transient_for_done(struct wl_resource *resource_, uint32_t child_id)
{
	wl_resource_post_event(resource_, TIZEN_POLICY_TRANSIENT_FOR_DONE, child_id);
}

static inline void
tizen_policy_send_window_screen_mode_done(struct wl_resource *resource_, struct wl_resource *surface, uint32_t mode, uint32_t error_state)
{
	wl_resource_post_event(resource_, TIZEN_POLICY_WINDOW_SCREEN_MODE_DONE, surface, mode, error_state);
}

static inline void
tizen_policy_send_iconify_state_changed(struct wl_resource *resource_, struct wl_resource *surface, uint32_t iconified, uint32_t force)
{
	wl_resource_post_event(resource_, TIZEN_POLICY_ICONIFY_STATE_CHANGED, surface, iconified, force);
}

static inline void
tizen_policy_send_supported_aux_hints(struct wl_resource *resource_, struct wl_resource *surface, struct wl_array *hints, uint32_t num_hints)
{
	wl_resource_post_event(resource_, TIZEN_POLICY_SUPPORTED_AUX_HINTS, surface, hints, num_hints);
}

static inline void
tizen_policy_send_allowed_aux_hint(struct wl_resource *resource_, struct wl_resource *surface, int32_t id)
{
	wl_resource_post_event(resource_, TIZEN_POLICY_ALLOWED_AUX_HINT, surface, id);
}

static inline void
tizen_policy_send_aux_message(struct wl_resource *resource_, struct wl_resource *surface, const char *key, const char *value, struct wl_array *options)
{
	wl_resource_post_event(resource_, TIZEN_POLICY_AUX_MESSAGE, surface, key, value, options);
}

#ifndef TIZEN_VISIBILITY_VISIBILITY_ENUM
#define TIZEN_VISIBILITY_VISIBILITY_ENUM
enum tizen_visibility_visibility {
	TIZEN_VISIBILITY_VISIBILITY_UNOBSCURED = 0,
	TIZEN_VISIBILITY_VISIBILITY_PARTIALLY_OBSCURED = 1,
	TIZEN_VISIBILITY_VISIBILITY_FULLY_OBSCURED = 2,
};
#endif /* TIZEN_VISIBILITY_VISIBILITY_ENUM */

struct tizen_visibility_interface {
	/**
	 * destroy - (none)
	 */
	void (*destroy)(struct wl_client *client,
			struct wl_resource *resource);
};

#define TIZEN_VISIBILITY_NOTIFY	0

#define TIZEN_VISIBILITY_NOTIFY_SINCE_VERSION	1

static inline void
tizen_visibility_send_notify(struct wl_resource *resource_, uint32_t visibility)
{
	wl_resource_post_event(resource_, TIZEN_VISIBILITY_NOTIFY, visibility);
}

struct tizen_position_interface {
	/**
	 * destroy - (none)
	 */
	void (*destroy)(struct wl_client *client,
			struct wl_resource *resource);
	/**
	 * set - (none)
	 * @x: (none)
	 * @y: (none)
	 */
	void (*set)(struct wl_client *client,
		    struct wl_resource *resource,
		    int32_t x,
		    int32_t y);
};

#define TIZEN_POSITION_CHANGED	0

#define TIZEN_POSITION_CHANGED_SINCE_VERSION	1

static inline void
tizen_position_send_changed(struct wl_resource *resource_, int32_t x, int32_t y)
{
	wl_resource_post_event(resource_, TIZEN_POSITION_CHANGED, x, y);
}

#ifndef TIZEN_GESTURE_ERROR_ENUM
#define TIZEN_GESTURE_ERROR_ENUM
enum tizen_gesture_error {
	TIZEN_GESTURE_ERROR_NONE = 0,
	TIZEN_GESTURE_ERROR_INVALID_DATA = 1,
	TIZEN_GESTURE_ERROR_NO_PERMISSION = 2,
	TIZEN_GESTURE_ERROR_NO_SYSTEM_RESOURCES = 3,
	TIZEN_GESTURE_ERROR_GRABBED_ALREADY = 4,
};
#endif /* TIZEN_GESTURE_ERROR_ENUM */

#ifndef TIZEN_GESTURE_TYPE_ENUM
#define TIZEN_GESTURE_TYPE_ENUM
enum tizen_gesture_type {
	TIZEN_GESTURE_TYPE_EDGE_SWIPE = 1,
};
#endif /* TIZEN_GESTURE_TYPE_ENUM */

#ifndef TIZEN_GESTURE_MODE_ENUM
#define TIZEN_GESTURE_MODE_ENUM
enum tizen_gesture_mode {
	TIZEN_GESTURE_MODE_BEGIN = 1,
	TIZEN_GESTURE_MODE_UPDATE = 2,
	TIZEN_GESTURE_MODE_END = 3,
	TIZEN_GESTURE_MODE_DONE = 4,
};
#endif /* TIZEN_GESTURE_MODE_ENUM */

#ifndef TIZEN_GESTURE_EDGE_ENUM
#define TIZEN_GESTURE_EDGE_ENUM
enum tizen_gesture_edge {
	TIZEN_GESTURE_EDGE_TOP = 1,
	TIZEN_GESTURE_EDGE_RIGHT = 2,
	TIZEN_GESTURE_EDGE_BOTTOM = 4,
	TIZEN_GESTURE_EDGE_LEFT = 8,
};
#endif /* TIZEN_GESTURE_EDGE_ENUM */

struct tizen_gesture_interface {
	/**
	 * grab_edge_swipe - (none)
	 * @fingers: (none)
	 * @edge: (none)
	 */
	void (*grab_edge_swipe)(struct wl_client *client,
				struct wl_resource *resource,
				uint32_t fingers,
				uint32_t edge);
	/**
	 * ungrab_edge_swipe - (none)
	 * @fingers: (none)
	 * @edge: (none)
	 */
	void (*ungrab_edge_swipe)(struct wl_client *client,
				  struct wl_resource *resource,
				  uint32_t fingers,
				  uint32_t edge);
};

#define TIZEN_GESTURE_GRAB_EDGE_SWIPE_NOTIFY	0
#define TIZEN_GESTURE_EDGE_SWIPE	1

#define TIZEN_GESTURE_GRAB_EDGE_SWIPE_NOTIFY_SINCE_VERSION	1
#define TIZEN_GESTURE_EDGE_SWIPE_SINCE_VERSION	1

static inline void
tizen_gesture_send_grab_edge_swipe_notify(struct wl_resource *resource_, uint32_t fingers, uint32_t edge, uint32_t error)
{
	wl_resource_post_event(resource_, TIZEN_GESTURE_GRAB_EDGE_SWIPE_NOTIFY, fingers, edge, error);
}

static inline void
tizen_gesture_send_edge_swipe(struct wl_resource *resource_, uint32_t mode, uint32_t fingers, int32_t sx, int32_t sy, uint32_t edge)
{
	wl_resource_post_event(resource_, TIZEN_GESTURE_EDGE_SWIPE, mode, fingers, sx, sy, edge);
}

#ifndef TIZEN_KEYROUTER_ERROR_ENUM
#define TIZEN_KEYROUTER_ERROR_ENUM
enum tizen_keyrouter_error {
	TIZEN_KEYROUTER_ERROR_NONE = 0,
	TIZEN_KEYROUTER_ERROR_INVALID_SURFACE = 1,
	TIZEN_KEYROUTER_ERROR_INVALID_KEY = 2,
	TIZEN_KEYROUTER_ERROR_INVALID_MODE = 3,
	TIZEN_KEYROUTER_ERROR_GRABBED_ALREADY = 4,
	TIZEN_KEYROUTER_ERROR_NO_PERMISSION = 5,
	TIZEN_KEYROUTER_ERROR_NO_SYSTEM_RESOURCES = 6,
	TIZEN_KEYROUTER_ERROR_INVALID_ARRAY = 7,
};
#endif /* TIZEN_KEYROUTER_ERROR_ENUM */

#ifndef TIZEN_KEYROUTER_MODE_ENUM
#define TIZEN_KEYROUTER_MODE_ENUM
/**
 * tizen_keyrouter_mode - mode for a key grab
 * @TIZEN_KEYROUTER_MODE_NONE: none
 * @TIZEN_KEYROUTER_MODE_SHARED: mode to get a key grab with the other
 *	client surfaces when the focused client surface gets the key
 * @TIZEN_KEYROUTER_MODE_TOPMOST: mode to get a key grab when the client
 *	surface is the top most surface
 * @TIZEN_KEYROUTER_MODE_OVERRIDABLE_EXCLUSIVE: mode to get a key grab
 *	exclusively, overridably regardless of the order in the surface stack
 * @TIZEN_KEYROUTER_MODE_EXCLUSIVE: mode to get a key grab exclusively
 *	regardless of the order in surface stack
 * @TIZEN_KEYROUTER_MODE_REGISTERED: mode to get a key grab only when a
 *	requesting surface is on top among the registering surfaces for the key
 *
 * This value is used to set a mode for a key grab. With this mode and
 * the order of the surface between surfaces' stack, the compositor will
 * determine the destination client surface.
 */
enum tizen_keyrouter_mode {
	TIZEN_KEYROUTER_MODE_NONE = 0,
	TIZEN_KEYROUTER_MODE_SHARED = 1,
	TIZEN_KEYROUTER_MODE_TOPMOST = 2,
	TIZEN_KEYROUTER_MODE_OVERRIDABLE_EXCLUSIVE = 3,
	TIZEN_KEYROUTER_MODE_EXCLUSIVE = 4,
	TIZEN_KEYROUTER_MODE_REGISTERED = 5,
};
#endif /* TIZEN_KEYROUTER_MODE_ENUM */

#ifndef TIZEN_KEYROUTER_CONFIG_MODE_ENUM
#define TIZEN_KEYROUTER_CONFIG_MODE_ENUM
/**
 * tizen_keyrouter_config_mode - mode for setting specific property for
 *	Key delivery
 * @TIZEN_KEYROUTER_CONFIG_MODE_NONE: none
 * @TIZEN_KEYROUTER_CONFIG_MODE_INVISIBLE_SET: mode to set window to
 *	enable send event to invisible window below in stack
 * @TIZEN_KEYROUTER_CONFIG_MODE_INVISIBLE_GET: mode to set window to get
 *	event to invisible state if any top window has set register_set
 * @TIZEN_KEYROUTER_CONFIG_MODE_NUM_KEY_FOCUS: mode to register for num
 *	keys for focus window
 * @TIZEN_KEYROUTER_CONFIG_MODE_PICTURE_OFF: mode to set picture off for
 *	particular key
 *
 * This value is used to set a mode for a window. With this mode and the
 * order of the surface between surfaces' stack, the compositor will
 * determine the destination client surface.
 */
enum tizen_keyrouter_config_mode {
	TIZEN_KEYROUTER_CONFIG_MODE_NONE = 0,
	TIZEN_KEYROUTER_CONFIG_MODE_INVISIBLE_SET = 1,
	TIZEN_KEYROUTER_CONFIG_MODE_INVISIBLE_GET = 2,
	TIZEN_KEYROUTER_CONFIG_MODE_NUM_KEY_FOCUS = 3,
	TIZEN_KEYROUTER_CONFIG_MODE_PICTURE_OFF = 4,
};
#endif /* TIZEN_KEYROUTER_CONFIG_MODE_ENUM */

/**
 * tizen_keyrouter - an interface to set each focus for each key
 * @set_keygrab: (none)
 * @unset_keygrab: (none)
 * @get_keygrab_status: (none)
 * @set_keygrab_list: (none)
 * @unset_keygrab_list: (none)
 * @get_keygrab_list: (none)
 * @set_register_none_key: (none)
 * @get_keyregister_status: (none)
 * @set_input_config: (none)
 *
 * In tradition, all the keys in a keyboard and a device on which some
 * keys are attached will be sent to focus surface by default. Currently
 * it's possible to set up each focus for each key in a keyboard and a
 * device. Therefore, by setting a key grab for a surface, the owner of the
 * surface will get the key event when it has the key grab for the key.
 */
struct tizen_keyrouter_interface {
	/**
	 * set_keygrab - (none)
	 * @surface: (none)
	 * @key: (none)
	 * @mode: (none)
	 */
	void (*set_keygrab)(struct wl_client *client,
			    struct wl_resource *resource,
			    struct wl_resource *surface,
			    uint32_t key,
			    uint32_t mode);
	/**
	 * unset_keygrab - (none)
	 * @surface: (none)
	 * @key: (none)
	 */
	void (*unset_keygrab)(struct wl_client *client,
			      struct wl_resource *resource,
			      struct wl_resource *surface,
			      uint32_t key);
	/**
	 * get_keygrab_status - (none)
	 * @surface: (none)
	 * @key: (none)
	 */
	void (*get_keygrab_status)(struct wl_client *client,
				   struct wl_resource *resource,
				   struct wl_resource *surface,
				   uint32_t key);
	/**
	 * set_keygrab_list - (none)
	 * @surface: (none)
	 * @grab_list: array of two integer variables pairs each pairs
	 *	consist of keycode and keygrab mode
	 */
	void (*set_keygrab_list)(struct wl_client *client,
				 struct wl_resource *resource,
				 struct wl_resource *surface,
				 struct wl_array *grab_list);
	/**
	 * unset_keygrab_list - (none)
	 * @surface: (none)
	 * @ungrab_list: array of integer variables meaning keycode
	 *	wanted to ungrab
	 */
	void (*unset_keygrab_list)(struct wl_client *client,
				   struct wl_resource *resource,
				   struct wl_resource *surface,
				   struct wl_array *ungrab_list);
	/**
	 * get_keygrab_list - (none)
	 * @surface: (none)
	 */
	void (*get_keygrab_list)(struct wl_client *client,
				 struct wl_resource *resource,
				 struct wl_resource *surface);
	/**
	 * set_register_none_key - (none)
	 * @surface: (none)
	 * @data: (none)
	 */
	void (*set_register_none_key)(struct wl_client *client,
				      struct wl_resource *resource,
				      struct wl_resource *surface,
				      uint32_t data);
	/**
	 * get_keyregister_status - (none)
	 * @data: (none)
	 */
	void (*get_keyregister_status)(struct wl_client *client,
				       struct wl_resource *resource,
				       uint32_t data);
	/**
	 * set_input_config - (none)
	 * @surface: (none)
	 * @config_mode: (none)
	 * @value: (none)
	 */
	void (*set_input_config)(struct wl_client *client,
				 struct wl_resource *resource,
				 struct wl_resource *surface,
				 uint32_t config_mode,
				 uint32_t value);
};

#define TIZEN_KEYROUTER_KEYGRAB_NOTIFY	0
#define TIZEN_KEYROUTER_KEYGRAB_NOTIFY_LIST	1
#define TIZEN_KEYROUTER_GETGRAB_NOTIFY_LIST	2
#define TIZEN_KEYROUTER_SET_REGISTER_NONE_KEY_NOTIFY	3
#define TIZEN_KEYROUTER_KEYREGISTER_NOTIFY	4
#define TIZEN_KEYROUTER_SET_INPUT_CONFIG_NOTIFY	5
#define TIZEN_KEYROUTER_KEY_CANCEL	6

#define TIZEN_KEYROUTER_KEYGRAB_NOTIFY_SINCE_VERSION	1
#define TIZEN_KEYROUTER_KEYGRAB_NOTIFY_LIST_SINCE_VERSION	1
#define TIZEN_KEYROUTER_GETGRAB_NOTIFY_LIST_SINCE_VERSION	1
#define TIZEN_KEYROUTER_SET_REGISTER_NONE_KEY_NOTIFY_SINCE_VERSION	1
#define TIZEN_KEYROUTER_KEYREGISTER_NOTIFY_SINCE_VERSION	1
#define TIZEN_KEYROUTER_SET_INPUT_CONFIG_NOTIFY_SINCE_VERSION	1
#define TIZEN_KEYROUTER_KEY_CANCEL_SINCE_VERSION	1

static inline void
tizen_keyrouter_send_keygrab_notify(struct wl_resource *resource_, struct wl_resource *surface, uint32_t key, uint32_t mode, uint32_t error)
{
	wl_resource_post_event(resource_, TIZEN_KEYROUTER_KEYGRAB_NOTIFY, surface, key, mode, error);
}

static inline void
tizen_keyrouter_send_keygrab_notify_list(struct wl_resource *resource_, struct wl_resource *surface, struct wl_array *grab_result)
{
	wl_resource_post_event(resource_, TIZEN_KEYROUTER_KEYGRAB_NOTIFY_LIST, surface, grab_result);
}

static inline void
tizen_keyrouter_send_getgrab_notify_list(struct wl_resource *resource_, struct wl_resource *surface, struct wl_array *grab_result)
{
	wl_resource_post_event(resource_, TIZEN_KEYROUTER_GETGRAB_NOTIFY_LIST, surface, grab_result);
}

static inline void
tizen_keyrouter_send_set_register_none_key_notify(struct wl_resource *resource_, struct wl_resource *surface, uint32_t mode)
{
	wl_resource_post_event(resource_, TIZEN_KEYROUTER_SET_REGISTER_NONE_KEY_NOTIFY, surface, mode);
}

static inline void
tizen_keyrouter_send_keyregister_notify(struct wl_resource *resource_, uint32_t status)
{
	wl_resource_post_event(resource_, TIZEN_KEYROUTER_KEYREGISTER_NOTIFY, status);
}

static inline void
tizen_keyrouter_send_set_input_config_notify(struct wl_resource *resource_, uint32_t status)
{
	wl_resource_post_event(resource_, TIZEN_KEYROUTER_SET_INPUT_CONFIG_NOTIFY, status);
}

static inline void
tizen_keyrouter_send_key_cancel(struct wl_resource *resource_, uint32_t key)
{
	wl_resource_post_event(resource_, TIZEN_KEYROUTER_KEY_CANCEL, key);
}

/**
 * tizen_screenshooter - interface for tizen-screenshooter
 * @get_screenmirror: create a screenmirror object
 *
 * Clients can get a screenmirror object from this interface.
 */
struct tizen_screenshooter_interface {
	/**
	 * get_screenmirror - create a screenmirror object
	 * @id: new screenmirror object
	 * @output: output object for screenmirror
	 *
	 * Before using screenmirror, a client should get a screenmirror
	 * object from display server.
	 */
	void (*get_screenmirror)(struct wl_client *client,
				 struct wl_resource *resource,
				 uint32_t id,
				 struct wl_resource *output);
};

#define TIZEN_SCREENSHOOTER_FORMAT	0
#define TIZEN_SCREENSHOOTER_SCREENSHOOTER_NOTIFY	1

#define TIZEN_SCREENSHOOTER_FORMAT_SINCE_VERSION	1
#define TIZEN_SCREENSHOOTER_SCREENSHOOTER_NOTIFY_SINCE_VERSION	1

static inline void
tizen_screenshooter_send_format(struct wl_resource *resource_, uint32_t format)
{
	wl_resource_post_event(resource_, TIZEN_SCREENSHOOTER_FORMAT, format);
}

static inline void
tizen_screenshooter_send_screenshooter_notify(struct wl_resource *resource_, uint32_t noti)
{
	wl_resource_post_event(resource_, TIZEN_SCREENSHOOTER_SCREENSHOOTER_NOTIFY, noti);
}

#ifndef TIZEN_SCREENMIRROR_CONTENT_ENUM
#define TIZEN_SCREENMIRROR_CONTENT_ENUM
enum tizen_screenmirror_content {
	TIZEN_SCREENMIRROR_CONTENT_NORMAL = 0,
	TIZEN_SCREENMIRROR_CONTENT_VIDEO = 1,
};
#endif /* TIZEN_SCREENMIRROR_CONTENT_ENUM */

#ifndef TIZEN_SCREENMIRROR_STRETCH_ENUM
#define TIZEN_SCREENMIRROR_STRETCH_ENUM
enum tizen_screenmirror_stretch {
	TIZEN_SCREENMIRROR_STRETCH_KEEP_RATIO = 0,
	TIZEN_SCREENMIRROR_STRETCH_FULLY = 1,
};
#endif /* TIZEN_SCREENMIRROR_STRETCH_ENUM */

/**
 * tizen_screenmirror - interface for screenmirror
 * @destroy: (none)
 * @set_stretch: (none)
 * @queue: queue a buffer
 * @dequeue: dequeue a buffer
 * @start: (none)
 * @stop: (none)
 *
 * A client can use this interface to get stream images of screen. Before
 * starting, queue all buffers. Then, start a screenmirror. After starting,
 * a dequeued event will occur when drawing a captured image on a buffer is
 * finished. You might need to queue the dequeued buffer again to get a new
 * image from display server.
 */
struct tizen_screenmirror_interface {
	/**
	 * destroy - (none)
	 */
	void (*destroy)(struct wl_client *client,
			struct wl_resource *resource);
	/**
	 * set_stretch - (none)
	 * @stretch: stretch type for screenmirror
	 */
	void (*set_stretch)(struct wl_client *client,
			    struct wl_resource *resource,
			    uint32_t stretch);
	/**
	 * queue - queue a buffer
	 * @buffer: buffer object for screenmirror
	 *
	 * 
	 */
	void (*queue)(struct wl_client *client,
		      struct wl_resource *resource,
		      struct wl_resource *buffer);
	/**
	 * dequeue - dequeue a buffer
	 * @buffer: buffer object for screenmirror
	 *
	 * A user can dequeue a buffer from display server when he wants
	 * to take back it from server.
	 */
	void (*dequeue)(struct wl_client *client,
			struct wl_resource *resource,
			struct wl_resource *buffer);
	/**
	 * start - (none)
	 */
	void (*start)(struct wl_client *client,
		      struct wl_resource *resource);
	/**
	 * stop - (none)
	 */
	void (*stop)(struct wl_client *client,
		     struct wl_resource *resource);
};

#define TIZEN_SCREENMIRROR_DEQUEUED	0
#define TIZEN_SCREENMIRROR_CONTENT	1
#define TIZEN_SCREENMIRROR_STOP	2

#define TIZEN_SCREENMIRROR_DEQUEUED_SINCE_VERSION	1
#define TIZEN_SCREENMIRROR_CONTENT_SINCE_VERSION	1
#define TIZEN_SCREENMIRROR_STOP_SINCE_VERSION	1

static inline void
tizen_screenmirror_send_dequeued(struct wl_resource *resource_, struct wl_resource *buffer)
{
	wl_resource_post_event(resource_, TIZEN_SCREENMIRROR_DEQUEUED, buffer);
}

static inline void
tizen_screenmirror_send_content(struct wl_resource *resource_, uint32_t content)
{
	wl_resource_post_event(resource_, TIZEN_SCREENMIRROR_CONTENT, content);
}

static inline void
tizen_screenmirror_send_stop(struct wl_resource *resource_)
{
	wl_resource_post_event(resource_, TIZEN_SCREENMIRROR_STOP);
}

#ifndef TIZEN_VIDEO_ERROR_ENUM
#define TIZEN_VIDEO_ERROR_ENUM
enum tizen_video_error {
	TIZEN_VIDEO_ERROR_NONE = 0,
	TIZEN_VIDEO_ERROR_OBJECT_EXISTS = 1,
	TIZEN_VIDEO_ERROR_VIEWPORT_EXISTS = 2,
};
#endif /* TIZEN_VIDEO_ERROR_ENUM */

/**
 * tizen_video - interface for tizen-video
 * @get_object: (none)
 * @get_viewport: (none)
 *
 * Clients can get the video information that the compositor can handle
 * from this interface.
 */
struct tizen_video_interface {
	/**
	 * get_object - (none)
	 * @id: (none)
	 * @surface: (none)
	 */
	void (*get_object)(struct wl_client *client,
			   struct wl_resource *resource,
			   uint32_t id,
			   struct wl_resource *surface);
	/**
	 * get_viewport - (none)
	 * @id: (none)
	 * @surface: (none)
	 */
	void (*get_viewport)(struct wl_client *client,
			     struct wl_resource *resource,
			     uint32_t id,
			     struct wl_resource *surface);
};

#define TIZEN_VIDEO_FORMAT	0

#define TIZEN_VIDEO_FORMAT_SINCE_VERSION	1

static inline void
tizen_video_send_format(struct wl_resource *resource_, uint32_t format)
{
	wl_resource_post_event(resource_, TIZEN_VIDEO_FORMAT, format);
}

struct tizen_video_object_interface {
	/**
	 * destroy - (none)
	 */
	void (*destroy)(struct wl_client *client,
			struct wl_resource *resource);
	/**
	 * set_attribute - (none)
	 * @name: (none)
	 * @value: (none)
	 */
	void (*set_attribute)(struct wl_client *client,
			      struct wl_resource *resource,
			      const char *name,
			      int32_t value);
};

#define TIZEN_VIDEO_OBJECT_ATTRIBUTE	0
#define TIZEN_VIDEO_OBJECT_SIZE	1

#define TIZEN_VIDEO_OBJECT_ATTRIBUTE_SINCE_VERSION	1
#define TIZEN_VIDEO_OBJECT_SIZE_SINCE_VERSION	1

static inline void
tizen_video_object_send_attribute(struct wl_resource *resource_, const char *name, uint32_t value)
{
	wl_resource_post_event(resource_, TIZEN_VIDEO_OBJECT_ATTRIBUTE, name, value);
}

static inline void
tizen_video_object_send_size(struct wl_resource *resource_, int32_t min_w, int32_t min_h, int32_t max_w, int32_t max_h, int32_t prefer_align)
{
	wl_resource_post_event(resource_, TIZEN_VIDEO_OBJECT_SIZE, min_w, min_h, max_w, max_h, prefer_align);
}

#ifndef TIZEN_SUBSURFACE_WATCHER_MSG_ENUM
#define TIZEN_SUBSURFACE_WATCHER_MSG_ENUM
enum tizen_subsurface_watcher_msg {
	TIZEN_SUBSURFACE_WATCHER_MSG_SUCCESS = 0,
	TIZEN_SUBSURFACE_WATCHER_MSG_PARENT_ID_INVALID = 1,
	TIZEN_SUBSURFACE_WATCHER_MSG_PARENT_ID_DESTROYED = 2,
};
#endif /* TIZEN_SUBSURFACE_WATCHER_MSG_ENUM */

#define TIZEN_SUBSURFACE_WATCHER_MESSAGE	0

#define TIZEN_SUBSURFACE_WATCHER_MESSAGE_SINCE_VERSION	1

static inline void
tizen_subsurface_watcher_send_message(struct wl_resource *resource_, uint32_t value)
{
	wl_resource_post_event(resource_, TIZEN_SUBSURFACE_WATCHER_MESSAGE, value);
}

/**
 * tizen_viewport - the viewport for a surface
 * @destroy: (none)
 * @set_transform: set the transform of a surface
 * @set_source: set the source rectalge of a wl_buffer
 * @set_destination: set the destination geometry of a surface
 * @set_destination_ratio: set the ratio destination rectalge in a parent
 *	surface
 * @get_destination_mode: (none)
 * @query_parent_size: set the ratio destination rectalge in a parent
 *	surface
 * @follow_parent_transform: follow the transform change of a parent
 *	surface
 * @unfollow_parent_transform: (none)
 *
 * This is the alternative and convenient solution of wl_viewport to
 * present a surface on screen.
 *
 * The below five functions can be replaced with this interface. The below
 * functions will be ignored after applying this interface to a surface. -
 * wl_surface.set_buffer_transform - wl_surface.set_buffer_scale -
 * wl_subsurface.set_position - wl_viewport.set_source -
 * wl_viewport.set_destination
 *
 * wl_viewport.set_source is very complicated especially when the buffer of
 * wl_surface is transformed by wl_surface.set_buffer_transform. And when
 * the parent is resized, if we want to change the geometry of a subsurface
 * also, wl_subsurface.set_position and wl_viewport.set_destination should
 * be called everytime the parent is resized in client side. This makes
 * difficult to synchronize a parent surface and a subsurface on screen.
 *
 * tizen_viewport allows clients to set the relative geometry to a
 * subsurface in a parent surface. Whenever a parent surface is resized,
 * the geometry of a subsurface will be calculated, moved and resized
 * automatically by a compositor. The tizen_viewport is specified in the
 * coordinates of a subsurface's parent.
 *
 * If tizen_viewport is applied to a shell surface(toplevel), the all value
 * related with x, y pos of tizen_viewport and tizen_destination_mode
 * interface will be ignored.
 *
 * The below 3 functions don't consider the transform of a parent's
 * surface. - tizen_viewport.set_source - tizen_viewport.set_destination -
 * tizen_viewport.set_destination_ratio
 *
 * Furthermore, tizen_destination_mode.follow_parent_transform is called,
 * tizen_viewport will consider the transform of a parent surface when
 * applying the destination mode to a subsurface. If
 * tizen_destination_mode.follow_parent_transform is applied to a shell
 * surface, it will be ignored.
 *
 * The destination will be cropped by a parent surface.
 *
 * The change will be applied when wl_surface.commit is called.
 */
struct tizen_viewport_interface {
	/**
	 * destroy - (none)
	 */
	void (*destroy)(struct wl_client *client,
			struct wl_resource *resource);
	/**
	 * set_transform - set the transform of a surface
	 * @transform: (none)
	 *
	 * The accepted values for the transform parameter are the values
	 * for wl_output.transform according to the output transform.
	 *
	 * The tizen_viewport.set_transform is applied only to itself.
	 * Basically it doesn't effect the transform of its subsurfaces.
	 * That is, if 90 transform is setted and its subsurface still has
	 * 0 transform, its subsurface won't be rotated.
	 *
	 * If needed to rotate the subsurface depended on a parent surface,
	 * tizen_destination_mode.follow_parent_transform will make it
	 * possible.
	 */
	void (*set_transform)(struct wl_client *client,
			      struct wl_resource *resource,
			      uint32_t transform);
	/**
	 * set_source - set the source rectalge of a wl_buffer
	 * @x: (none)
	 * @y: (none)
	 * @width: (none)
	 * @height: (none)
	 *
	 * The source rectangle won't be changed when a parent is
	 * resized. If it needs to be changed, tizen_viewport.set_source
	 * should be called with new values.
	 */
	void (*set_source)(struct wl_client *client,
			   struct wl_resource *resource,
			   uint32_t x,
			   uint32_t y,
			   uint32_t width,
			   uint32_t height);
	/**
	 * set_destination - set the destination geometry of a surface
	 * @x: (none)
	 * @y: (none)
	 * @width: (none)
	 * @height: (none)
	 *
	 * The destination geometry won't be changed when a parent is
	 * resized. If it needs to be changed,
	 * tizen_viewport.set_destination should be called with new values.
	 *
	 * If tizen_viewport is applied to a shell surface(toplevel), the
	 * x, y value of tizen_viewport.set_destination will be ignored.
	 */
	void (*set_destination)(struct wl_client *client,
				struct wl_resource *resource,
				int32_t x,
				int32_t y,
				uint32_t width,
				uint32_t height);
	/**
	 * set_destination_ratio - set the ratio destination rectalge in
	 *	a parent surface
	 * @x: (none)
	 * @y: (none)
	 * @width: (none)
	 * @height: (none)
	 *
	 * The destination rectangle of a subsurface will be
	 * automatically changed when a parent is resized. This allows the
	 * real number between 0.0 and 1.0. See wl_fixed_from_double and
	 * wl_fixed_to_double.
	 *
	 * If tizen_viewport is applied to a shell surface(toplevel), the
	 * x, y value of tizen_viewport.set_destination_ratio will be
	 * ignored.
	 */
	void (*set_destination_ratio)(struct wl_client *client,
				      struct wl_resource *resource,
				      wl_fixed_t x,
				      wl_fixed_t y,
				      wl_fixed_t width,
				      wl_fixed_t height);
	/**
	 * get_destination_mode - (none)
	 * @id: (none)
	 */
	void (*get_destination_mode)(struct wl_client *client,
				     struct wl_resource *resource,
				     uint32_t id);
	/**
	 * query_parent_size - set the ratio destination rectalge in a
	 *	parent surface
	 *
	 * A client can asks the display server to send the size of
	 * tizen_viewport object's parent surface. Once a client requests
	 * it, the "parent_size" event will be sent whenever the parent
	 * surface's size is changed.
	 */
	void (*query_parent_size)(struct wl_client *client,
				  struct wl_resource *resource);
	/**
	 * follow_parent_transform - follow the transform change of a
	 *	parent surface
	 *
	 * The real transform of a subsurface is (parent's transform +
	 * subsurface's transform). The subsurface will be rotated
	 * automatically when the parent is rotated.
	 *
	 * If tizen_viewport.follow_parent_transform is applied to a shell
	 * surface(toplevel), it will be ignored.
	 */
	void (*follow_parent_transform)(struct wl_client *client,
					struct wl_resource *resource);
	/**
	 * unfollow_parent_transform - (none)
	 */
	void (*unfollow_parent_transform)(struct wl_client *client,
					  struct wl_resource *resource);
};

#define TIZEN_VIEWPORT_DESTINATION_CHANGED	0
#define TIZEN_VIEWPORT_PARENT_SIZE	1

#define TIZEN_VIEWPORT_DESTINATION_CHANGED_SINCE_VERSION	1
#define TIZEN_VIEWPORT_PARENT_SIZE_SINCE_VERSION	1

static inline void
tizen_viewport_send_destination_changed(struct wl_resource *resource_, uint32_t transform, int32_t x, int32_t y, uint32_t width, uint32_t height)
{
	wl_resource_post_event(resource_, TIZEN_VIEWPORT_DESTINATION_CHANGED, transform, x, y, width, height);
}

static inline void
tizen_viewport_send_parent_size(struct wl_resource *resource_, uint32_t width, uint32_t height)
{
	wl_resource_post_event(resource_, TIZEN_VIEWPORT_PARENT_SIZE, width, height);
}

#ifndef TIZEN_DESTINATION_MODE_ERROR_ENUM
#define TIZEN_DESTINATION_MODE_ERROR_ENUM
enum tizen_destination_mode_error {
	TIZEN_DESTINATION_MODE_ERROR_INVALID_TYPE = 0,
};
#endif /* TIZEN_DESTINATION_MODE_ERROR_ENUM */

#ifndef TIZEN_DESTINATION_MODE_TYPE_ENUM
#define TIZEN_DESTINATION_MODE_TYPE_ENUM
enum tizen_destination_mode_type {
	TIZEN_DESTINATION_MODE_TYPE_NONE = 0,
	TIZEN_DESTINATION_MODE_TYPE_LETTER_BOX = 1,
	TIZEN_DESTINATION_MODE_TYPE_ORIGIN = 2,
	TIZEN_DESTINATION_MODE_TYPE_FULL = 3,
	TIZEN_DESTINATION_MODE_TYPE_CROPPED_FULL = 4,
	TIZEN_DESTINATION_MODE_TYPE_ORIGIN_OR_LETTER = 5,
};
#endif /* TIZEN_DESTINATION_MODE_TYPE_ENUM */

/**
 * tizen_destination_mode - the destination mode for a surface
 * @destroy: (none)
 * @follow_parent_transform: follow the transform change of a parent
 *	surface
 * @unfollow_parent_transform: (none)
 * @set: (none)
 * @set_ratio: set the ratio of the destination rectangle of a subsurface
 * @set_scale: set the scale of the destination rectangle of a subsurface
 * @set_align: set the align of the destination rectangle of a subsurface
 * @set_offset: (none)
 *
 * The destination rectangle will be automatically changed when a parent
 * is resized. When tizen_destination_mode.set is called, the value of
 * tizen_viewport.set_destination and tizen_viewport.set_destination_ratio
 * will be ignored.
 *
 * The destination of a surface is decided by the mode, ratio, scale,
 * offset and align values. The ratio, scale, offset and align will be
 * applied sequentially.
 *
 * The change will be applied when wl_surface.commit is called.
 */
struct tizen_destination_mode_interface {
	/**
	 * destroy - (none)
	 */
	void (*destroy)(struct wl_client *client,
			struct wl_resource *resource);
	/**
	 * follow_parent_transform - follow the transform change of a
	 *	parent surface
	 *
	 * The real transform of a subsurface is (parent's transform +
	 * subsurface's transform). That is, the subsurface will be rotated
	 * automatically when the parent is rotated.
	 *
	 * If tizen_destination_mode.follow_parent_transform is applied to
	 * a shell surface(toplevel), it will be ignored.
	 */
	void (*follow_parent_transform)(struct wl_client *client,
					struct wl_resource *resource);
	/**
	 * unfollow_parent_transform - (none)
	 */
	void (*unfollow_parent_transform)(struct wl_client *client,
					  struct wl_resource *resource);
	/**
	 * set - (none)
	 * @mode: (none)
	 */
	void (*set)(struct wl_client *client,
		    struct wl_resource *resource,
		    uint32_t mode);
	/**
	 * set_ratio - set the ratio of the destination rectangle of a
	 *	subsurface
	 * @horizontal: (none)
	 * @vertical: (none)
	 *
	 * This allows the real number. See wl_fixed_from_double and
	 * wl_fixed_to_double.
	 */
	void (*set_ratio)(struct wl_client *client,
			  struct wl_resource *resource,
			  wl_fixed_t horizontal,
			  wl_fixed_t vertical);
	/**
	 * set_scale - set the scale of the destination rectangle of a
	 *	subsurface
	 * @horizontal: (none)
	 * @vertical: (none)
	 *
	 * This allows the real number. See wl_fixed_from_double and
	 * wl_fixed_to_double.
	 */
	void (*set_scale)(struct wl_client *client,
			  struct wl_resource *resource,
			  wl_fixed_t horizontal,
			  wl_fixed_t vertical);
	/**
	 * set_align - set the align of the destination rectangle of a
	 *	subsurface
	 * @horizontal: (none)
	 * @vertical: (none)
	 *
	 * This allows the real number between 0.0 and 1.0. See
	 * wl_fixed_from_double and wl_fixed_to_double.
	 */
	void (*set_align)(struct wl_client *client,
			  struct wl_resource *resource,
			  wl_fixed_t horizontal,
			  wl_fixed_t vertical);
	/**
	 * set_offset - (none)
	 * @x: (none)
	 * @y: (none)
	 * @w: (none)
	 * @h: (none)
	 */
	void (*set_offset)(struct wl_client *client,
			   struct wl_resource *resource,
			   int32_t x,
			   int32_t y,
			   int32_t w,
			   int32_t h);
};


/**
 * tizen_embedded_compositor - global embedded compositor object
 * @get_socket: create new socket
 *
 * The global obejct. Wayland has 3 type of compositor, embedded
 * compositor is one of them. But tizen application is sendboxing by smack,
 * then a application not allow commutication to other application by
 * socket. So system or session compositor create socket and send to
 * embedded compostior.
 */
struct tizen_embedded_compositor_interface {
	/**
	 * get_socket - create new socket
	 *
	 * The get_socket request ask the server to create socket and
	 * emit socket event.
	 */
	void (*get_socket)(struct wl_client *client,
			   struct wl_resource *resource);
};

#define TIZEN_EMBEDDED_COMPOSITOR_SOCKET	0

#define TIZEN_EMBEDDED_COMPOSITOR_SOCKET_SINCE_VERSION	1

static inline void
tizen_embedded_compositor_send_socket(struct wl_resource *resource_, int32_t sock_fd)
{
	wl_resource_post_event(resource_, TIZEN_EMBEDDED_COMPOSITOR_SOCKET, sock_fd);
}

#ifndef TIZEN_INPUT_DEVICE_MANAGER_CLAS_ENUM
#define TIZEN_INPUT_DEVICE_MANAGER_CLAS_ENUM
/**
 * tizen_input_device_manager_clas - device class
 * @TIZEN_INPUT_DEVICE_MANAGER_CLAS_NONE: none of class
 * @TIZEN_INPUT_DEVICE_MANAGER_CLAS_MOUSE: mouse class
 * @TIZEN_INPUT_DEVICE_MANAGER_CLAS_KEYBOARD: keyboard class
 * @TIZEN_INPUT_DEVICE_MANAGER_CLAS_TOUCHSCREEN: touchscreen class
 *
 * 
 */
enum tizen_input_device_manager_clas {
	TIZEN_INPUT_DEVICE_MANAGER_CLAS_NONE = 0,
	TIZEN_INPUT_DEVICE_MANAGER_CLAS_MOUSE = 1,
	TIZEN_INPUT_DEVICE_MANAGER_CLAS_KEYBOARD = 2,
	TIZEN_INPUT_DEVICE_MANAGER_CLAS_TOUCHSCREEN = 4,
};
#endif /* TIZEN_INPUT_DEVICE_MANAGER_CLAS_ENUM */

#ifndef TIZEN_INPUT_DEVICE_MANAGER_ERROR_ENUM
#define TIZEN_INPUT_DEVICE_MANAGER_ERROR_ENUM
enum tizen_input_device_manager_error {
	TIZEN_INPUT_DEVICE_MANAGER_ERROR_NONE = 0,
	TIZEN_INPUT_DEVICE_MANAGER_ERROR_NO_PERMISSION = 1,
	TIZEN_INPUT_DEVICE_MANAGER_ERROR_INVALID_CLASS = 2,
	TIZEN_INPUT_DEVICE_MANAGER_ERROR_BLOCKED_ALREADY = 3,
	TIZEN_INPUT_DEVICE_MANAGER_ERROR_NO_SYSTEM_RESOURCES = 4,
	TIZEN_INPUT_DEVICE_MANAGER_ERROR_INVALID_PARAMETER = 5,
	TIZEN_INPUT_DEVICE_MANAGER_ERROR_INVALID_SURFACE = 6,
	TIZEN_INPUT_DEVICE_MANAGER_ERROR_NO_POINTER_AVAILABLE = 7,
};
#endif /* TIZEN_INPUT_DEVICE_MANAGER_ERROR_ENUM */

#ifndef TIZEN_INPUT_DEVICE_MANAGER_POINTER_EVENT_TYPE_ENUM
#define TIZEN_INPUT_DEVICE_MANAGER_POINTER_EVENT_TYPE_ENUM
enum tizen_input_device_manager_pointer_event_type {
	TIZEN_INPUT_DEVICE_MANAGER_POINTER_EVENT_TYPE_BEGIN = 0,
	TIZEN_INPUT_DEVICE_MANAGER_POINTER_EVENT_TYPE_UPDATE = 1,
	TIZEN_INPUT_DEVICE_MANAGER_POINTER_EVENT_TYPE_END = 2,
};
#endif /* TIZEN_INPUT_DEVICE_MANAGER_POINTER_EVENT_TYPE_ENUM */

/**
 * tizen_input_device_manager - global input device manager object
 * @block_events: request to block sending event(s)
 * @unblock_events: request to unblock sending event(s)
 * @init_generator: initialize input generator system
 * @deinit_generator: deinitialize input generator system
 * @generate_key: generate a key event using specific or default device
 * @generate_pointer: generate a pointer event using specific or default
 *	device
 * @generate_touch: generate a touch event using specific or default
 *	device
 * @pointer_warp: warp pointer to the relative position to the given
 *	surface
 *
 * Tizen input device manager is a global interface. This object has
 * device add/remove events to provide tizen input device object to a
 * client. This allows for a client to get the con
 *
 * Be sure to bind this interface after binding wl_seat interface. Tizen
 * input device manager interface will only provide device add/remove event
 * for devices which belongs to the wl_seat object(s) bound by the client.
 * Therefore, the compositor needs to create/send the device add/remove
 * event only for the current client's seat(s).
 */
struct tizen_input_device_manager_interface {
	/**
	 * block_events - request to block sending event(s)
	 * @serial: 
	 * @clas: (none)
	 * @duration: time duration with millisecond granularity
	 *
	 * This request allows a client to request to block one or more
	 * events for its purpose. By specifying class as an argument in
	 * the request, the events belongs to the class will be blocked
	 * during the given duration. Note that an error event will be sent
	 * if there is any error.
	 */
	void (*block_events)(struct wl_client *client,
			     struct wl_resource *resource,
			     uint32_t serial,
			     uint32_t clas,
			     uint32_t duration);
	/**
	 * unblock_events - request to unblock sending event(s)
	 * @serial: 
	 *
	 * This request allows a client to request to release the
	 * existing block for the client. Note that no error event will be
	 * sent if there is no existing block for the client.
	 */
	void (*unblock_events)(struct wl_client *client,
			       struct wl_resource *resource,
			       uint32_t serial);
	/**
	 * init_generator - initialize input generator system
	 *
	 * 
	 */
	void (*init_generator)(struct wl_client *client,
			       struct wl_resource *resource);
	/**
	 * deinit_generator - deinitialize input generator system
	 *
	 * 
	 */
	void (*deinit_generator)(struct wl_client *client,
				 struct wl_resource *resource);
	/**
	 * generate_key - generate a key event using specific or default
	 *	device
	 * @keyname: (none)
	 * @pressed: (none)
	 *
	 * 
	 */
	void (*generate_key)(struct wl_client *client,
			     struct wl_resource *resource,
			     const char *keyname,
			     uint32_t pressed);
	/**
	 * generate_pointer - generate a pointer event using specific or
	 *	default device
	 * @type: (none)
	 * @x: (none)
	 * @y: (none)
	 * @button: (none)
	 *
	 * 
	 */
	void (*generate_pointer)(struct wl_client *client,
				 struct wl_resource *resource,
				 uint32_t type,
				 uint32_t x,
				 uint32_t y,
				 uint32_t button);
	/**
	 * generate_touch - generate a touch event using specific or
	 *	default device
	 * @type: (none)
	 * @x: (none)
	 * @y: (none)
	 * @finger: (none)
	 *
	 * 
	 */
	void (*generate_touch)(struct wl_client *client,
			       struct wl_resource *resource,
			       uint32_t type,
			       uint32_t x,
			       uint32_t y,
			       uint32_t finger);
	/**
	 * pointer_warp - warp pointer to the relative position to the
	 *	given surface
	 * @surface: (none)
	 * @x: (none)
	 * @y: (none)
	 *
	 * 
	 */
	void (*pointer_warp)(struct wl_client *client,
			     struct wl_resource *resource,
			     struct wl_resource *surface,
			     wl_fixed_t x,
			     wl_fixed_t y);
};

#define TIZEN_INPUT_DEVICE_MANAGER_DEVICE_ADD	0
#define TIZEN_INPUT_DEVICE_MANAGER_DEVICE_REMOVE	1
#define TIZEN_INPUT_DEVICE_MANAGER_ERROR	2
#define TIZEN_INPUT_DEVICE_MANAGER_BLOCK_EXPIRED	3

#define TIZEN_INPUT_DEVICE_MANAGER_DEVICE_ADD_SINCE_VERSION	1
#define TIZEN_INPUT_DEVICE_MANAGER_DEVICE_REMOVE_SINCE_VERSION	1
#define TIZEN_INPUT_DEVICE_MANAGER_ERROR_SINCE_VERSION	1
#define TIZEN_INPUT_DEVICE_MANAGER_BLOCK_EXPIRED_SINCE_VERSION	1

static inline void
tizen_input_device_manager_send_device_add(struct wl_resource *resource_, uint32_t serial, const char *identifier, struct wl_resource *device, struct wl_resource *seat)
{
	wl_resource_post_event(resource_, TIZEN_INPUT_DEVICE_MANAGER_DEVICE_ADD, serial, identifier, device, seat);
}

static inline void
tizen_input_device_manager_send_device_remove(struct wl_resource *resource_, uint32_t serial, const char *identifier, struct wl_resource *device, struct wl_resource *seat)
{
	wl_resource_post_event(resource_, TIZEN_INPUT_DEVICE_MANAGER_DEVICE_REMOVE, serial, identifier, device, seat);
}

static inline void
tizen_input_device_manager_send_error(struct wl_resource *resource_, uint32_t errorcode)
{
	wl_resource_post_event(resource_, TIZEN_INPUT_DEVICE_MANAGER_ERROR, errorcode);
}

static inline void
tizen_input_device_manager_send_block_expired(struct wl_resource *resource_)
{
	wl_resource_post_event(resource_, TIZEN_INPUT_DEVICE_MANAGER_BLOCK_EXPIRED);
}

#ifndef TIZEN_INPUT_DEVICE_CLAS_ENUM
#define TIZEN_INPUT_DEVICE_CLAS_ENUM
/**
 * tizen_input_device_clas - device class
 * @TIZEN_INPUT_DEVICE_CLAS_NONE: none of class
 * @TIZEN_INPUT_DEVICE_CLAS_KEYBOARD: keyboard class
 * @TIZEN_INPUT_DEVICE_CLAS_MOUSE: mouse class
 * @TIZEN_INPUT_DEVICE_CLAS_TOUCHSCREEN: touchscreen class
 *
 * 
 */
enum tizen_input_device_clas {
	TIZEN_INPUT_DEVICE_CLAS_NONE = 0,
	TIZEN_INPUT_DEVICE_CLAS_KEYBOARD = 2,
	TIZEN_INPUT_DEVICE_CLAS_MOUSE = 3,
	TIZEN_INPUT_DEVICE_CLAS_TOUCHSCREEN = 4,
};
#endif /* TIZEN_INPUT_DEVICE_CLAS_ENUM */

#ifndef TIZEN_INPUT_DEVICE_SUBCLAS_ENUM
#define TIZEN_INPUT_DEVICE_SUBCLAS_ENUM
/**
 * tizen_input_device_subclas - device subclass
 * @TIZEN_INPUT_DEVICE_SUBCLAS_NONE: none of subclass
 *
 * 
 */
enum tizen_input_device_subclas {
	TIZEN_INPUT_DEVICE_SUBCLAS_NONE = 0,
};
#endif /* TIZEN_INPUT_DEVICE_SUBCLAS_ENUM */

#ifndef TIZEN_INPUT_DEVICE_AXIS_TYPE_ENUM
#define TIZEN_INPUT_DEVICE_AXIS_TYPE_ENUM
/**
 * tizen_input_device_axis_type - axis type enums which can be supported
 *	by a device
 * @TIZEN_INPUT_DEVICE_AXIS_TYPE_NONE: radius of x axis of an event area
 *	e.g. touching area with a finger or a pen
 * @TIZEN_INPUT_DEVICE_AXIS_TYPE_RADIUS_X: radius of x axis of an event
 *	area e.g. touching area with a finger or a pen
 * @TIZEN_INPUT_DEVICE_AXIS_TYPE_RADIUS_Y: radius of y axis of an event
 *	area e.g. touching area with a finger or a pen
 * @TIZEN_INPUT_DEVICE_AXIS_TYPE_PRESSURE: pressure in an event area e.g.
 *	touching area with a finger or a pen
 * @TIZEN_INPUT_DEVICE_AXIS_TYPE_ANGLE: angle in an event area e.g.
 *	touching area with a finger or a pen
 * @TIZEN_INPUT_DEVICE_AXIS_TYPE_DETENT: detent value e.g. moved distance
 *	with a rotary device
 *
 * 
 */
enum tizen_input_device_axis_type {
	TIZEN_INPUT_DEVICE_AXIS_TYPE_NONE = 0,
	TIZEN_INPUT_DEVICE_AXIS_TYPE_RADIUS_X = 1,
	TIZEN_INPUT_DEVICE_AXIS_TYPE_RADIUS_Y = 2,
	TIZEN_INPUT_DEVICE_AXIS_TYPE_PRESSURE = 3,
	TIZEN_INPUT_DEVICE_AXIS_TYPE_ANGLE = 4,
	TIZEN_INPUT_DEVICE_AXIS_TYPE_DETENT = 5,
};
#endif /* TIZEN_INPUT_DEVICE_AXIS_TYPE_ENUM */

/**
 * tizen_input_device - tizen input device object
 * @select_axes: request for selecting some of axes among the axes
 *	supported by a tizen_input_device object
 * @release: release the tizen_input_device object
 *
 * The tizen_input_device interface represents one or more input devices
 * associated with a physical/logical input device. This interface provides
 * device specific information/events to allows for client to identify the
 * source device of an event or to get the additional axes/attributes of a
 * device. Note that a tizen_input_device object can be used for a physical
 * input device and can also be used for a group of input devices. e.g. a
 * group of mouse devices
 */
struct tizen_input_device_interface {
	/**
	 * select_axes - request for selecting some of axes among the
	 *	axes supported by a tizen_input_device object
	 * @axes: array of axis num
	 *
	 * 
	 */
	void (*select_axes)(struct wl_client *client,
			    struct wl_resource *resource,
			    struct wl_array *axes);
	/**
	 * release - release the tizen_input_device object
	 *
	 * 
	 */
	void (*release)(struct wl_client *client,
			struct wl_resource *resource);
};

#define TIZEN_INPUT_DEVICE_DEVICE_INFO	0
#define TIZEN_INPUT_DEVICE_EVENT_DEVICE	1
#define TIZEN_INPUT_DEVICE_AXIS	2

#define TIZEN_INPUT_DEVICE_DEVICE_INFO_SINCE_VERSION	1
#define TIZEN_INPUT_DEVICE_EVENT_DEVICE_SINCE_VERSION	1
#define TIZEN_INPUT_DEVICE_AXIS_SINCE_VERSION	1

static inline void
tizen_input_device_send_device_info(struct wl_resource *resource_, const char *name, uint32_t clas, uint32_t subclas, struct wl_array *axes)
{
	wl_resource_post_event(resource_, TIZEN_INPUT_DEVICE_DEVICE_INFO, name, clas, subclas, axes);
}

static inline void
tizen_input_device_send_event_device(struct wl_resource *resource_, uint32_t serial, const char *name, uint32_t time)
{
	wl_resource_post_event(resource_, TIZEN_INPUT_DEVICE_EVENT_DEVICE, serial, name, time);
}

static inline void
tizen_input_device_send_axis(struct wl_resource *resource_, uint32_t axis_type, wl_fixed_t value)
{
	wl_resource_post_event(resource_, TIZEN_INPUT_DEVICE_AXIS, axis_type, value);
}

struct tizen_launchscreen_interface {
	/**
	 * create_img - (none)
	 * @id: new tizen_launch_image object
	 */
	void (*create_img)(struct wl_client *client,
			   struct wl_resource *resource,
			   uint32_t id);
};


#ifndef TIZEN_LAUNCH_IMAGE_FILE_TYPE_ENUM
#define TIZEN_LAUNCH_IMAGE_FILE_TYPE_ENUM
enum tizen_launch_image_file_type {
	TIZEN_LAUNCH_IMAGE_FILE_TYPE_IMG = 0,
	TIZEN_LAUNCH_IMAGE_FILE_TYPE_EDJ = 1,
};
#endif /* TIZEN_LAUNCH_IMAGE_FILE_TYPE_ENUM */

#ifndef TIZEN_LAUNCH_IMAGE_INDICATOR_ENUM
#define TIZEN_LAUNCH_IMAGE_INDICATOR_ENUM
enum tizen_launch_image_indicator {
	TIZEN_LAUNCH_IMAGE_INDICATOR_OFF = 0,
	TIZEN_LAUNCH_IMAGE_INDICATOR_ON = 1,
};
#endif /* TIZEN_LAUNCH_IMAGE_INDICATOR_ENUM */

#ifndef TIZEN_LAUNCH_IMAGE_ROTATION_ENUM
#define TIZEN_LAUNCH_IMAGE_ROTATION_ENUM
enum tizen_launch_image_rotation {
	TIZEN_LAUNCH_IMAGE_ROTATION_0 = 0,
	TIZEN_LAUNCH_IMAGE_ROTATION_90 = 90,
	TIZEN_LAUNCH_IMAGE_ROTATION_180 = 180,
	TIZEN_LAUNCH_IMAGE_ROTATION_270 = 270,
};
#endif /* TIZEN_LAUNCH_IMAGE_ROTATION_ENUM */

struct tizen_launch_image_interface {
	/**
	 * destroy - (none)
	 */
	void (*destroy)(struct wl_client *client,
			struct wl_resource *resource);
	/**
	 * launch - (none)
	 * @file: (none)
	 * @file_type: (none)
	 * @color_depth: (none)
	 * @rotation: (none)
	 * @indicator: (none)
	 * @options: (none)
	 */
	void (*launch)(struct wl_client *client,
		       struct wl_resource *resource,
		       const char *file,
		       uint32_t file_type,
		       uint32_t color_depth,
		       uint32_t rotation,
		       uint32_t indicator,
		       struct wl_array *options);
	/**
	 * owner - (none)
	 * @pid: (none)
	 */
	void (*owner)(struct wl_client *client,
		      struct wl_resource *resource,
		      uint32_t pid);
	/**
	 * show - (none)
	 */
	void (*show)(struct wl_client *client,
		     struct wl_resource *resource);
	/**
	 * hide - (none)
	 */
	void (*hide)(struct wl_client *client,
		     struct wl_resource *resource);
};


#ifndef TIZEN_EFFECT_TYPE_ENUM
#define TIZEN_EFFECT_TYPE_ENUM
enum tizen_effect_type {
	TIZEN_EFFECT_TYPE_NONE = 0,
	TIZEN_EFFECT_TYPE_SHOW = 1,
	TIZEN_EFFECT_TYPE_HIDE = 2,
	TIZEN_EFFECT_TYPE_RESTACK = 3,
};
#endif /* TIZEN_EFFECT_TYPE_ENUM */

struct tizen_effect_interface {
	/**
	 * destroy - (none)
	 */
	void (*destroy)(struct wl_client *client,
			struct wl_resource *resource);
};

#define TIZEN_EFFECT_START	0
#define TIZEN_EFFECT_END	1

#define TIZEN_EFFECT_START_SINCE_VERSION	1
#define TIZEN_EFFECT_END_SINCE_VERSION	1

static inline void
tizen_effect_send_start(struct wl_resource *resource_, struct wl_resource *surface, uint32_t type)
{
	wl_resource_post_event(resource_, TIZEN_EFFECT_START, surface, type);
}

static inline void
tizen_effect_send_end(struct wl_resource *resource_, struct wl_resource *surface, uint32_t type)
{
	wl_resource_post_event(resource_, TIZEN_EFFECT_END, surface, type);
}

#ifndef TIZEN_DISPLAY_POLICY_ERROR_STATE_ENUM
#define TIZEN_DISPLAY_POLICY_ERROR_STATE_ENUM
enum tizen_display_policy_error_state {
	TIZEN_DISPLAY_POLICY_ERROR_STATE_NONE = 0,
	TIZEN_DISPLAY_POLICY_ERROR_STATE_PERMISSION_DENIED = 1,
};
#endif /* TIZEN_DISPLAY_POLICY_ERROR_STATE_ENUM */

struct tizen_display_policy_interface {
	/**
	 * set_window_brightness - (none)
	 * @surface: (none)
	 * @brightness: (none)
	 */
	void (*set_window_brightness)(struct wl_client *client,
				      struct wl_resource *resource,
				      struct wl_resource *surface,
				      int32_t brightness);
};

#define TIZEN_DISPLAY_POLICY_WINDOW_BRIGHTNESS_DONE	0

#define TIZEN_DISPLAY_POLICY_WINDOW_BRIGHTNESS_DONE_SINCE_VERSION	1

static inline void
tizen_display_policy_send_window_brightness_done(struct wl_resource *resource_, struct wl_resource *surface, int32_t brightness, uint32_t error_state)
{
	wl_resource_post_event(resource_, TIZEN_DISPLAY_POLICY_WINDOW_BRIGHTNESS_DONE, surface, brightness, error_state);
}

#ifndef TIZEN_INDICATOR_STATE_ENUM
#define TIZEN_INDICATOR_STATE_ENUM
enum tizen_indicator_state {
	TIZEN_INDICATOR_STATE_UNKNOWN = 0,
	TIZEN_INDICATOR_STATE_OFF = 1,
	TIZEN_INDICATOR_STATE_ON = 2,
};
#endif /* TIZEN_INDICATOR_STATE_ENUM */

#ifndef TIZEN_INDICATOR_OPACITY_MODE_ENUM
#define TIZEN_INDICATOR_OPACITY_MODE_ENUM
enum tizen_indicator_opacity_mode {
	TIZEN_INDICATOR_OPACITY_MODE_UNKNOWN = 0,
	TIZEN_INDICATOR_OPACITY_MODE_OPAQUE = 1,
	TIZEN_INDICATOR_OPACITY_MODE_TRANSLUCENT = 2,
	TIZEN_INDICATOR_OPACITY_MODE_TRANSPARENT = 3,
	TIZEN_INDICATOR_OPACITY_MODE_BG_TRANSPARENT = 4,
};
#endif /* TIZEN_INDICATOR_OPACITY_MODE_ENUM */

#ifndef TIZEN_INDICATOR_VISIBLE_TYPE_ENUM
#define TIZEN_INDICATOR_VISIBLE_TYPE_ENUM
enum tizen_indicator_visible_type {
	TIZEN_INDICATOR_VISIBLE_TYPE_HIDDEN = 0,
	TIZEN_INDICATOR_VISIBLE_TYPE_SHOWN = 1,
};
#endif /* TIZEN_INDICATOR_VISIBLE_TYPE_ENUM */

struct tizen_indicator_interface {
	/**
	 * destroy - (none)
	 */
	void (*destroy)(struct wl_client *client,
			struct wl_resource *resource);
	/**
	 * set_state - (none)
	 * @surface: surface object
	 * @state: (none)
	 */
	void (*set_state)(struct wl_client *client,
			  struct wl_resource *resource,
			  struct wl_resource *surface,
			  int32_t state);
	/**
	 * set_opacity_mode - (none)
	 * @surface: surface object
	 * @mode: (none)
	 */
	void (*set_opacity_mode)(struct wl_client *client,
				 struct wl_resource *resource,
				 struct wl_resource *surface,
				 int32_t mode);
	/**
	 * set_visible_type - (none)
	 * @surface: surface object
	 * @type: (none)
	 */
	void (*set_visible_type)(struct wl_client *client,
				 struct wl_resource *resource,
				 struct wl_resource *surface,
				 int32_t type);
};

#define TIZEN_INDICATOR_FLICK	0

#define TIZEN_INDICATOR_FLICK_SINCE_VERSION	1

static inline void
tizen_indicator_send_flick(struct wl_resource *resource_, struct wl_resource *surface, int32_t type)
{
	wl_resource_post_event(resource_, TIZEN_INDICATOR_FLICK, surface, type);
}

/**
 * tizen_clipboard - an interface for requests and event about clipboard
 * @destroy: (none)
 * @show: request for show clipboard
 * @hide: request for hide clipboard
 *
 * This interface provides some requests and events about clipboard for
 * other clients.
 */
struct tizen_clipboard_interface {
	/**
	 * destroy - (none)
	 */
	void (*destroy)(struct wl_client *client,
			struct wl_resource *resource);
	/**
	 * show - request for show clipboard
	 * @surface: surface object
	 *
	 * 
	 */
	void (*show)(struct wl_client *client,
		     struct wl_resource *resource,
		     struct wl_resource *surface);
	/**
	 * hide - request for hide clipboard
	 * @surface: surface object
	 *
	 * 
	 */
	void (*hide)(struct wl_client *client,
		     struct wl_resource *resource,
		     struct wl_resource *surface);
};

#define TIZEN_CLIPBOARD_DATA_SELECTED	0

#define TIZEN_CLIPBOARD_DATA_SELECTED_SINCE_VERSION	1

static inline void
tizen_clipboard_send_data_selected(struct wl_resource *resource_, struct wl_resource *surface)
{
	wl_resource_post_event(resource_, TIZEN_CLIPBOARD_DATA_SELECTED, surface);
}

#ifdef  __cplusplus
}
#endif

#endif
