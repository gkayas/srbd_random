#ifndef TIZEN_EXTENSION_CLIENT_PROTOCOL_H
#define TIZEN_EXTENSION_CLIENT_PROTOCOL_H

#ifdef  __cplusplus
extern "C" {
#endif

#include <stdint.h>
#include <stddef.h>
#include "wayland-client.h"

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

#define TIZEN_SURFACE_GET_TIZEN_RESOURCE	0

#define TIZEN_SURFACE_GET_TIZEN_RESOURCE_SINCE_VERSION	1

static inline void
tizen_surface_set_user_data(struct tizen_surface *tizen_surface, void *user_data)
{
	wl_proxy_set_user_data((struct wl_proxy *) tizen_surface, user_data);
}

static inline void *
tizen_surface_get_user_data(struct tizen_surface *tizen_surface)
{
	return wl_proxy_get_user_data((struct wl_proxy *) tizen_surface);
}

static inline uint32_t
tizen_surface_get_version(struct tizen_surface *tizen_surface)
{
	return wl_proxy_get_version((struct wl_proxy *) tizen_surface);
}

static inline void
tizen_surface_destroy(struct tizen_surface *tizen_surface)
{
	wl_proxy_destroy((struct wl_proxy *) tizen_surface);
}

static inline struct tizen_resource *
tizen_surface_get_tizen_resource(struct tizen_surface *tizen_surface, struct wl_surface *surface)
{
	struct wl_proxy *id;

	id = wl_proxy_marshal_constructor((struct wl_proxy *) tizen_surface,
			 TIZEN_SURFACE_GET_TIZEN_RESOURCE, &tizen_resource_interface, NULL, surface);

	return (struct tizen_resource *) id;
}

struct tizen_resource_listener {
	/**
	 * resource_id - (none)
	 * @id: (none)
	 */
	void (*resource_id)(void *data,
			    struct tizen_resource *tizen_resource,
			    uint32_t id);
};

static inline int
tizen_resource_add_listener(struct tizen_resource *tizen_resource,
			    const struct tizen_resource_listener *listener, void *data)
{
	return wl_proxy_add_listener((struct wl_proxy *) tizen_resource,
				     (void (**)(void)) listener, data);
}

#define TIZEN_RESOURCE_DESTROY	0

#define TIZEN_RESOURCE_DESTROY_SINCE_VERSION	1

static inline void
tizen_resource_set_user_data(struct tizen_resource *tizen_resource, void *user_data)
{
	wl_proxy_set_user_data((struct wl_proxy *) tizen_resource, user_data);
}

static inline void *
tizen_resource_get_user_data(struct tizen_resource *tizen_resource)
{
	return wl_proxy_get_user_data((struct wl_proxy *) tizen_resource);
}

static inline uint32_t
tizen_resource_get_version(struct tizen_resource *tizen_resource)
{
	return wl_proxy_get_version((struct wl_proxy *) tizen_resource);
}

static inline void
tizen_resource_destroy(struct tizen_resource *tizen_resource)
{
	wl_proxy_marshal((struct wl_proxy *) tizen_resource,
			 TIZEN_RESOURCE_DESTROY);

	wl_proxy_destroy((struct wl_proxy *) tizen_resource);
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

struct tizen_policy_listener {
	/**
	 * conformant - (none)
	 * @surface: surface object
	 * @is_conformant: (none)
	 */
	void (*conformant)(void *data,
			   struct tizen_policy *tizen_policy,
			   struct wl_surface *surface,
			   uint32_t is_conformant);
	/**
	 * conformant_area - (none)
	 * @surface: surface object
	 * @conformant_part: (none)
	 * @state: (none)
	 * @x: (none)
	 * @y: (none)
	 * @w: (none)
	 * @h: (none)
	 */
	void (*conformant_area)(void *data,
				struct tizen_policy *tizen_policy,
				struct wl_surface *surface,
				uint32_t conformant_part,
				uint32_t state,
				int32_t x,
				int32_t y,
				int32_t w,
				int32_t h);
	/**
	 * notification_done - (none)
	 * @surface: (none)
	 * @level: (none)
	 * @error_state: (none)
	 */
	void (*notification_done)(void *data,
				  struct tizen_policy *tizen_policy,
				  struct wl_surface *surface,
				  int32_t level,
				  uint32_t error_state);
	/**
	 * transient_for_done - (none)
	 * @child_id: (none)
	 */
	void (*transient_for_done)(void *data,
				   struct tizen_policy *tizen_policy,
				   uint32_t child_id);
	/**
	 * window_screen_mode_done - (none)
	 * @surface: (none)
	 * @mode: (none)
	 * @error_state: (none)
	 */
	void (*window_screen_mode_done)(void *data,
					struct tizen_policy *tizen_policy,
					struct wl_surface *surface,
					uint32_t mode,
					uint32_t error_state);
	/**
	 * iconify_state_changed - (none)
	 * @surface: (none)
	 * @iconified: (none)
	 * @force: (none)
	 */
	void (*iconify_state_changed)(void *data,
				      struct tizen_policy *tizen_policy,
				      struct wl_surface *surface,
				      uint32_t iconified,
				      uint32_t force);
	/**
	 * supported_aux_hints - (none)
	 * @surface: (none)
	 * @hints: (none)
	 * @num_hints: (none)
	 */
	void (*supported_aux_hints)(void *data,
				    struct tizen_policy *tizen_policy,
				    struct wl_surface *surface,
				    struct wl_array *hints,
				    uint32_t num_hints);
	/**
	 * allowed_aux_hint - (none)
	 * @surface: (none)
	 * @id: (none)
	 */
	void (*allowed_aux_hint)(void *data,
				 struct tizen_policy *tizen_policy,
				 struct wl_surface *surface,
				 int32_t id);
	/**
	 * aux_message - (none)
	 * @surface: (none)
	 * @key: (none)
	 * @value: (none)
	 * @options: (none)
	 */
	void (*aux_message)(void *data,
			    struct tizen_policy *tizen_policy,
			    struct wl_surface *surface,
			    const char *key,
			    const char *value,
			    struct wl_array *options);
};

static inline int
tizen_policy_add_listener(struct tizen_policy *tizen_policy,
			  const struct tizen_policy_listener *listener, void *data)
{
	return wl_proxy_add_listener((struct wl_proxy *) tizen_policy,
				     (void (**)(void)) listener, data);
}

#define TIZEN_POLICY_GET_VISIBILITY	0
#define TIZEN_POLICY_GET_POSITION	1
#define TIZEN_POLICY_ACTIVATE	2
#define TIZEN_POLICY_ACTIVATE_BELOW_BY_RES_ID	3
#define TIZEN_POLICY_RAISE	4
#define TIZEN_POLICY_LOWER	5
#define TIZEN_POLICY_LOWER_BY_RES_ID	6
#define TIZEN_POLICY_SET_FOCUS_SKIP	7
#define TIZEN_POLICY_UNSET_FOCUS_SKIP	8
#define TIZEN_POLICY_SET_ROLE	9
#define TIZEN_POLICY_SET_TYPE	10
#define TIZEN_POLICY_SET_CONFORMANT	11
#define TIZEN_POLICY_UNSET_CONFORMANT	12
#define TIZEN_POLICY_GET_CONFORMANT	13
#define TIZEN_POLICY_SET_NOTIFICATION_LEVEL	14
#define TIZEN_POLICY_SET_TRANSIENT_FOR	15
#define TIZEN_POLICY_UNSET_TRANSIENT_FOR	16
#define TIZEN_POLICY_SET_WINDOW_SCREEN_MODE	17
#define TIZEN_POLICY_PLACE_SUBSURFACE_BELOW_PARENT	18
#define TIZEN_POLICY_SET_SUBSURFACE_STAND_ALONE	19
#define TIZEN_POLICY_GET_SUBSURFACE	20
#define TIZEN_POLICY_SET_OPAQUE_STATE	21
#define TIZEN_POLICY_ICONIFY	22
#define TIZEN_POLICY_UNICONIFY	23
#define TIZEN_POLICY_ADD_AUX_HINT	24
#define TIZEN_POLICY_CHANGE_AUX_HINT	25
#define TIZEN_POLICY_DEL_AUX_HINT	26
#define TIZEN_POLICY_GET_SUPPORTED_AUX_HINTS	27
#define TIZEN_POLICY_SET_BACKGROUND_STATE	28
#define TIZEN_POLICY_UNSET_BACKGROUND_STATE	29
#define TIZEN_POLICY_SET_FLOATING_MODE	30
#define TIZEN_POLICY_UNSET_FLOATING_MODE	31
#define TIZEN_POLICY_SET_STACK_MODE	32
#define TIZEN_POLICY_ACTIVATE_ABOVE_BY_RES_ID	33
#define TIZEN_POLICY_GET_SUBSURFACE_WATCHER	34

#define TIZEN_POLICY_GET_VISIBILITY_SINCE_VERSION	1
#define TIZEN_POLICY_GET_POSITION_SINCE_VERSION	1
#define TIZEN_POLICY_ACTIVATE_SINCE_VERSION	1
#define TIZEN_POLICY_ACTIVATE_BELOW_BY_RES_ID_SINCE_VERSION	1
#define TIZEN_POLICY_RAISE_SINCE_VERSION	1
#define TIZEN_POLICY_LOWER_SINCE_VERSION	1
#define TIZEN_POLICY_LOWER_BY_RES_ID_SINCE_VERSION	1
#define TIZEN_POLICY_SET_FOCUS_SKIP_SINCE_VERSION	1
#define TIZEN_POLICY_UNSET_FOCUS_SKIP_SINCE_VERSION	1
#define TIZEN_POLICY_SET_ROLE_SINCE_VERSION	1
#define TIZEN_POLICY_SET_TYPE_SINCE_VERSION	1
#define TIZEN_POLICY_SET_CONFORMANT_SINCE_VERSION	1
#define TIZEN_POLICY_UNSET_CONFORMANT_SINCE_VERSION	1
#define TIZEN_POLICY_GET_CONFORMANT_SINCE_VERSION	1
#define TIZEN_POLICY_SET_NOTIFICATION_LEVEL_SINCE_VERSION	1
#define TIZEN_POLICY_SET_TRANSIENT_FOR_SINCE_VERSION	1
#define TIZEN_POLICY_UNSET_TRANSIENT_FOR_SINCE_VERSION	1
#define TIZEN_POLICY_SET_WINDOW_SCREEN_MODE_SINCE_VERSION	1
#define TIZEN_POLICY_PLACE_SUBSURFACE_BELOW_PARENT_SINCE_VERSION	1
#define TIZEN_POLICY_SET_SUBSURFACE_STAND_ALONE_SINCE_VERSION	1
#define TIZEN_POLICY_GET_SUBSURFACE_SINCE_VERSION	1
#define TIZEN_POLICY_SET_OPAQUE_STATE_SINCE_VERSION	1
#define TIZEN_POLICY_ICONIFY_SINCE_VERSION	1
#define TIZEN_POLICY_UNICONIFY_SINCE_VERSION	1
#define TIZEN_POLICY_ADD_AUX_HINT_SINCE_VERSION	1
#define TIZEN_POLICY_CHANGE_AUX_HINT_SINCE_VERSION	1
#define TIZEN_POLICY_DEL_AUX_HINT_SINCE_VERSION	1
#define TIZEN_POLICY_GET_SUPPORTED_AUX_HINTS_SINCE_VERSION	1
#define TIZEN_POLICY_SET_BACKGROUND_STATE_SINCE_VERSION	1
#define TIZEN_POLICY_UNSET_BACKGROUND_STATE_SINCE_VERSION	1
#define TIZEN_POLICY_SET_FLOATING_MODE_SINCE_VERSION	1
#define TIZEN_POLICY_UNSET_FLOATING_MODE_SINCE_VERSION	1
#define TIZEN_POLICY_SET_STACK_MODE_SINCE_VERSION	1
#define TIZEN_POLICY_ACTIVATE_ABOVE_BY_RES_ID_SINCE_VERSION	1
#define TIZEN_POLICY_GET_SUBSURFACE_WATCHER_SINCE_VERSION	2

static inline void
tizen_policy_set_user_data(struct tizen_policy *tizen_policy, void *user_data)
{
	wl_proxy_set_user_data((struct wl_proxy *) tizen_policy, user_data);
}

static inline void *
tizen_policy_get_user_data(struct tizen_policy *tizen_policy)
{
	return wl_proxy_get_user_data((struct wl_proxy *) tizen_policy);
}

static inline uint32_t
tizen_policy_get_version(struct tizen_policy *tizen_policy)
{
	return wl_proxy_get_version((struct wl_proxy *) tizen_policy);
}

static inline void
tizen_policy_destroy(struct tizen_policy *tizen_policy)
{
	wl_proxy_destroy((struct wl_proxy *) tizen_policy);
}

static inline struct tizen_visibility *
tizen_policy_get_visibility(struct tizen_policy *tizen_policy, struct wl_surface *surface)
{
	struct wl_proxy *id;

	id = wl_proxy_marshal_constructor((struct wl_proxy *) tizen_policy,
			 TIZEN_POLICY_GET_VISIBILITY, &tizen_visibility_interface, NULL, surface);

	return (struct tizen_visibility *) id;
}

static inline struct tizen_position *
tizen_policy_get_position(struct tizen_policy *tizen_policy, struct wl_surface *surface)
{
	struct wl_proxy *id;

	id = wl_proxy_marshal_constructor((struct wl_proxy *) tizen_policy,
			 TIZEN_POLICY_GET_POSITION, &tizen_position_interface, NULL, surface);

	return (struct tizen_position *) id;
}

static inline void
tizen_policy_activate(struct tizen_policy *tizen_policy, struct wl_surface *surface)
{
	wl_proxy_marshal((struct wl_proxy *) tizen_policy,
			 TIZEN_POLICY_ACTIVATE, surface);
}

static inline void
tizen_policy_activate_below_by_res_id(struct tizen_policy *tizen_policy, uint32_t res_id, uint32_t below_res_id)
{
	wl_proxy_marshal((struct wl_proxy *) tizen_policy,
			 TIZEN_POLICY_ACTIVATE_BELOW_BY_RES_ID, res_id, below_res_id);
}

static inline void
tizen_policy_raise(struct tizen_policy *tizen_policy, struct wl_surface *surface)
{
	wl_proxy_marshal((struct wl_proxy *) tizen_policy,
			 TIZEN_POLICY_RAISE, surface);
}

static inline void
tizen_policy_lower(struct tizen_policy *tizen_policy, struct wl_surface *surface)
{
	wl_proxy_marshal((struct wl_proxy *) tizen_policy,
			 TIZEN_POLICY_LOWER, surface);
}

static inline void
tizen_policy_lower_by_res_id(struct tizen_policy *tizen_policy, uint32_t res_id)
{
	wl_proxy_marshal((struct wl_proxy *) tizen_policy,
			 TIZEN_POLICY_LOWER_BY_RES_ID, res_id);
}

static inline void
tizen_policy_set_focus_skip(struct tizen_policy *tizen_policy, struct wl_surface *surface)
{
	wl_proxy_marshal((struct wl_proxy *) tizen_policy,
			 TIZEN_POLICY_SET_FOCUS_SKIP, surface);
}

static inline void
tizen_policy_unset_focus_skip(struct tizen_policy *tizen_policy, struct wl_surface *surface)
{
	wl_proxy_marshal((struct wl_proxy *) tizen_policy,
			 TIZEN_POLICY_UNSET_FOCUS_SKIP, surface);
}

static inline void
tizen_policy_set_role(struct tizen_policy *tizen_policy, struct wl_surface *surface, const char *role)
{
	wl_proxy_marshal((struct wl_proxy *) tizen_policy,
			 TIZEN_POLICY_SET_ROLE, surface, role);
}

static inline void
tizen_policy_set_type(struct tizen_policy *tizen_policy, struct wl_surface *surface, uint32_t win_type)
{
	wl_proxy_marshal((struct wl_proxy *) tizen_policy,
			 TIZEN_POLICY_SET_TYPE, surface, win_type);
}

static inline void
tizen_policy_set_conformant(struct tizen_policy *tizen_policy, struct wl_surface *surface)
{
	wl_proxy_marshal((struct wl_proxy *) tizen_policy,
			 TIZEN_POLICY_SET_CONFORMANT, surface);
}

static inline void
tizen_policy_unset_conformant(struct tizen_policy *tizen_policy, struct wl_surface *surface)
{
	wl_proxy_marshal((struct wl_proxy *) tizen_policy,
			 TIZEN_POLICY_UNSET_CONFORMANT, surface);
}

static inline void
tizen_policy_get_conformant(struct tizen_policy *tizen_policy, struct wl_surface *surface)
{
	wl_proxy_marshal((struct wl_proxy *) tizen_policy,
			 TIZEN_POLICY_GET_CONFORMANT, surface);
}

static inline void
tizen_policy_set_notification_level(struct tizen_policy *tizen_policy, struct wl_surface *surface, int32_t level)
{
	wl_proxy_marshal((struct wl_proxy *) tizen_policy,
			 TIZEN_POLICY_SET_NOTIFICATION_LEVEL, surface, level);
}

static inline void
tizen_policy_set_transient_for(struct tizen_policy *tizen_policy, uint32_t child_id, uint32_t parent_id)
{
	wl_proxy_marshal((struct wl_proxy *) tizen_policy,
			 TIZEN_POLICY_SET_TRANSIENT_FOR, child_id, parent_id);
}

static inline void
tizen_policy_unset_transient_for(struct tizen_policy *tizen_policy, uint32_t child_id)
{
	wl_proxy_marshal((struct wl_proxy *) tizen_policy,
			 TIZEN_POLICY_UNSET_TRANSIENT_FOR, child_id);
}

static inline void
tizen_policy_set_window_screen_mode(struct tizen_policy *tizen_policy, struct wl_surface *surface, uint32_t mode)
{
	wl_proxy_marshal((struct wl_proxy *) tizen_policy,
			 TIZEN_POLICY_SET_WINDOW_SCREEN_MODE, surface, mode);
}

static inline void
tizen_policy_place_subsurface_below_parent(struct tizen_policy *tizen_policy, struct wl_subsurface *subsurface)
{
	wl_proxy_marshal((struct wl_proxy *) tizen_policy,
			 TIZEN_POLICY_PLACE_SUBSURFACE_BELOW_PARENT, subsurface);
}

static inline void
tizen_policy_set_subsurface_stand_alone(struct tizen_policy *tizen_policy, struct wl_subsurface *subsurface)
{
	wl_proxy_marshal((struct wl_proxy *) tizen_policy,
			 TIZEN_POLICY_SET_SUBSURFACE_STAND_ALONE, subsurface);
}

static inline struct wl_subsurface *
tizen_policy_get_subsurface(struct tizen_policy *tizen_policy, struct wl_surface *surface, uint32_t parent_id)
{
	struct wl_proxy *id;

	id = wl_proxy_marshal_constructor((struct wl_proxy *) tizen_policy,
			 TIZEN_POLICY_GET_SUBSURFACE, &wl_subsurface_interface, NULL, surface, parent_id);

	return (struct wl_subsurface *) id;
}

static inline void
tizen_policy_set_opaque_state(struct tizen_policy *tizen_policy, struct wl_surface *surface, int32_t state)
{
	wl_proxy_marshal((struct wl_proxy *) tizen_policy,
			 TIZEN_POLICY_SET_OPAQUE_STATE, surface, state);
}

static inline void
tizen_policy_iconify(struct tizen_policy *tizen_policy, struct wl_surface *surface)
{
	wl_proxy_marshal((struct wl_proxy *) tizen_policy,
			 TIZEN_POLICY_ICONIFY, surface);
}

static inline void
tizen_policy_uniconify(struct tizen_policy *tizen_policy, struct wl_surface *surface)
{
	wl_proxy_marshal((struct wl_proxy *) tizen_policy,
			 TIZEN_POLICY_UNICONIFY, surface);
}

static inline void
tizen_policy_add_aux_hint(struct tizen_policy *tizen_policy, struct wl_surface *surface, int32_t id, const char *name, const char *value)
{
	wl_proxy_marshal((struct wl_proxy *) tizen_policy,
			 TIZEN_POLICY_ADD_AUX_HINT, surface, id, name, value);
}

static inline void
tizen_policy_change_aux_hint(struct tizen_policy *tizen_policy, struct wl_surface *surface, int32_t id, const char *value)
{
	wl_proxy_marshal((struct wl_proxy *) tizen_policy,
			 TIZEN_POLICY_CHANGE_AUX_HINT, surface, id, value);
}

static inline void
tizen_policy_del_aux_hint(struct tizen_policy *tizen_policy, struct wl_surface *surface, int32_t id)
{
	wl_proxy_marshal((struct wl_proxy *) tizen_policy,
			 TIZEN_POLICY_DEL_AUX_HINT, surface, id);
}

static inline void
tizen_policy_get_supported_aux_hints(struct tizen_policy *tizen_policy, struct wl_surface *surface)
{
	wl_proxy_marshal((struct wl_proxy *) tizen_policy,
			 TIZEN_POLICY_GET_SUPPORTED_AUX_HINTS, surface);
}

static inline void
tizen_policy_set_background_state(struct tizen_policy *tizen_policy, uint32_t pid)
{
	wl_proxy_marshal((struct wl_proxy *) tizen_policy,
			 TIZEN_POLICY_SET_BACKGROUND_STATE, pid);
}

static inline void
tizen_policy_unset_background_state(struct tizen_policy *tizen_policy, uint32_t pid)
{
	wl_proxy_marshal((struct wl_proxy *) tizen_policy,
			 TIZEN_POLICY_UNSET_BACKGROUND_STATE, pid);
}

static inline void
tizen_policy_set_floating_mode(struct tizen_policy *tizen_policy, struct wl_surface *surface)
{
	wl_proxy_marshal((struct wl_proxy *) tizen_policy,
			 TIZEN_POLICY_SET_FLOATING_MODE, surface);
}

static inline void
tizen_policy_unset_floating_mode(struct tizen_policy *tizen_policy, struct wl_surface *surface)
{
	wl_proxy_marshal((struct wl_proxy *) tizen_policy,
			 TIZEN_POLICY_UNSET_FLOATING_MODE, surface);
}

static inline void
tizen_policy_set_stack_mode(struct tizen_policy *tizen_policy, struct wl_surface *surface, uint32_t mode)
{
	wl_proxy_marshal((struct wl_proxy *) tizen_policy,
			 TIZEN_POLICY_SET_STACK_MODE, surface, mode);
}

static inline void
tizen_policy_activate_above_by_res_id(struct tizen_policy *tizen_policy, uint32_t res_id, uint32_t above_res_id)
{
	wl_proxy_marshal((struct wl_proxy *) tizen_policy,
			 TIZEN_POLICY_ACTIVATE_ABOVE_BY_RES_ID, res_id, above_res_id);
}

static inline struct tizen_subsurface_watcher *
tizen_policy_get_subsurface_watcher(struct tizen_policy *tizen_policy, struct wl_surface *surface)
{
	struct wl_proxy *id;

	id = wl_proxy_marshal_constructor((struct wl_proxy *) tizen_policy,
			 TIZEN_POLICY_GET_SUBSURFACE_WATCHER, &tizen_subsurface_watcher_interface, NULL, surface);

	return (struct tizen_subsurface_watcher *) id;
}

#ifndef TIZEN_VISIBILITY_VISIBILITY_ENUM
#define TIZEN_VISIBILITY_VISIBILITY_ENUM
enum tizen_visibility_visibility {
	TIZEN_VISIBILITY_VISIBILITY_UNOBSCURED = 0,
	TIZEN_VISIBILITY_VISIBILITY_PARTIALLY_OBSCURED = 1,
	TIZEN_VISIBILITY_VISIBILITY_FULLY_OBSCURED = 2,
};
#endif /* TIZEN_VISIBILITY_VISIBILITY_ENUM */

struct tizen_visibility_listener {
	/**
	 * notify - (none)
	 * @visibility: (none)
	 */
	void (*notify)(void *data,
		       struct tizen_visibility *tizen_visibility,
		       uint32_t visibility);
};

static inline int
tizen_visibility_add_listener(struct tizen_visibility *tizen_visibility,
			      const struct tizen_visibility_listener *listener, void *data)
{
	return wl_proxy_add_listener((struct wl_proxy *) tizen_visibility,
				     (void (**)(void)) listener, data);
}

#define TIZEN_VISIBILITY_DESTROY	0

#define TIZEN_VISIBILITY_DESTROY_SINCE_VERSION	1

static inline void
tizen_visibility_set_user_data(struct tizen_visibility *tizen_visibility, void *user_data)
{
	wl_proxy_set_user_data((struct wl_proxy *) tizen_visibility, user_data);
}

static inline void *
tizen_visibility_get_user_data(struct tizen_visibility *tizen_visibility)
{
	return wl_proxy_get_user_data((struct wl_proxy *) tizen_visibility);
}

static inline uint32_t
tizen_visibility_get_version(struct tizen_visibility *tizen_visibility)
{
	return wl_proxy_get_version((struct wl_proxy *) tizen_visibility);
}

static inline void
tizen_visibility_destroy(struct tizen_visibility *tizen_visibility)
{
	wl_proxy_marshal((struct wl_proxy *) tizen_visibility,
			 TIZEN_VISIBILITY_DESTROY);

	wl_proxy_destroy((struct wl_proxy *) tizen_visibility);
}

struct tizen_position_listener {
	/**
	 * changed - (none)
	 * @x: (none)
	 * @y: (none)
	 */
	void (*changed)(void *data,
			struct tizen_position *tizen_position,
			int32_t x,
			int32_t y);
};

static inline int
tizen_position_add_listener(struct tizen_position *tizen_position,
			    const struct tizen_position_listener *listener, void *data)
{
	return wl_proxy_add_listener((struct wl_proxy *) tizen_position,
				     (void (**)(void)) listener, data);
}

#define TIZEN_POSITION_DESTROY	0
#define TIZEN_POSITION_SET	1

#define TIZEN_POSITION_DESTROY_SINCE_VERSION	1
#define TIZEN_POSITION_SET_SINCE_VERSION	1

static inline void
tizen_position_set_user_data(struct tizen_position *tizen_position, void *user_data)
{
	wl_proxy_set_user_data((struct wl_proxy *) tizen_position, user_data);
}

static inline void *
tizen_position_get_user_data(struct tizen_position *tizen_position)
{
	return wl_proxy_get_user_data((struct wl_proxy *) tizen_position);
}

static inline uint32_t
tizen_position_get_version(struct tizen_position *tizen_position)
{
	return wl_proxy_get_version((struct wl_proxy *) tizen_position);
}

static inline void
tizen_position_destroy(struct tizen_position *tizen_position)
{
	wl_proxy_marshal((struct wl_proxy *) tizen_position,
			 TIZEN_POSITION_DESTROY);

	wl_proxy_destroy((struct wl_proxy *) tizen_position);
}

static inline void
tizen_position_set(struct tizen_position *tizen_position, int32_t x, int32_t y)
{
	wl_proxy_marshal((struct wl_proxy *) tizen_position,
			 TIZEN_POSITION_SET, x, y);
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

struct tizen_gesture_listener {
	/**
	 * grab_edge_swipe_notify - (none)
	 * @fingers: (none)
	 * @edge: (none)
	 * @error: (none)
	 */
	void (*grab_edge_swipe_notify)(void *data,
				       struct tizen_gesture *tizen_gesture,
				       uint32_t fingers,
				       uint32_t edge,
				       uint32_t error);
	/**
	 * edge_swipe - (none)
	 * @mode: (none)
	 * @fingers: (none)
	 * @sx: x coordinate of touch down point
	 * @sy: y coordinate of touch down point
	 * @edge: (none)
	 */
	void (*edge_swipe)(void *data,
			   struct tizen_gesture *tizen_gesture,
			   uint32_t mode,
			   uint32_t fingers,
			   int32_t sx,
			   int32_t sy,
			   uint32_t edge);
};

static inline int
tizen_gesture_add_listener(struct tizen_gesture *tizen_gesture,
			   const struct tizen_gesture_listener *listener, void *data)
{
	return wl_proxy_add_listener((struct wl_proxy *) tizen_gesture,
				     (void (**)(void)) listener, data);
}

#define TIZEN_GESTURE_GRAB_EDGE_SWIPE	0
#define TIZEN_GESTURE_UNGRAB_EDGE_SWIPE	1

#define TIZEN_GESTURE_GRAB_EDGE_SWIPE_SINCE_VERSION	1
#define TIZEN_GESTURE_UNGRAB_EDGE_SWIPE_SINCE_VERSION	1

static inline void
tizen_gesture_set_user_data(struct tizen_gesture *tizen_gesture, void *user_data)
{
	wl_proxy_set_user_data((struct wl_proxy *) tizen_gesture, user_data);
}

static inline void *
tizen_gesture_get_user_data(struct tizen_gesture *tizen_gesture)
{
	return wl_proxy_get_user_data((struct wl_proxy *) tizen_gesture);
}

static inline uint32_t
tizen_gesture_get_version(struct tizen_gesture *tizen_gesture)
{
	return wl_proxy_get_version((struct wl_proxy *) tizen_gesture);
}

static inline void
tizen_gesture_destroy(struct tizen_gesture *tizen_gesture)
{
	wl_proxy_destroy((struct wl_proxy *) tizen_gesture);
}

static inline void
tizen_gesture_grab_edge_swipe(struct tizen_gesture *tizen_gesture, uint32_t fingers, uint32_t edge)
{
	wl_proxy_marshal((struct wl_proxy *) tizen_gesture,
			 TIZEN_GESTURE_GRAB_EDGE_SWIPE, fingers, edge);
}

static inline void
tizen_gesture_ungrab_edge_swipe(struct tizen_gesture *tizen_gesture, uint32_t fingers, uint32_t edge)
{
	wl_proxy_marshal((struct wl_proxy *) tizen_gesture,
			 TIZEN_GESTURE_UNGRAB_EDGE_SWIPE, fingers, edge);
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
 * @keygrab_notify: (none)
 * @keygrab_notify_list: (none)
 * @getgrab_notify_list: (none)
 * @set_register_none_key_notify: (none)
 * @keyregister_notify: (none)
 * @set_input_config_notify: (none)
 * @key_cancel: (none)
 *
 * In tradition, all the keys in a keyboard and a device on which some
 * keys are attached will be sent to focus surface by default. Currently
 * it's possible to set up each focus for each key in a keyboard and a
 * device. Therefore, by setting a key grab for a surface, the owner of the
 * surface will get the key event when it has the key grab for the key.
 */
struct tizen_keyrouter_listener {
	/**
	 * keygrab_notify - (none)
	 * @surface: (none)
	 * @key: (none)
	 * @mode: (none)
	 * @error: (none)
	 */
	void (*keygrab_notify)(void *data,
			       struct tizen_keyrouter *tizen_keyrouter,
			       struct wl_surface *surface,
			       uint32_t key,
			       uint32_t mode,
			       uint32_t error);
	/**
	 * keygrab_notify_list - (none)
	 * @surface: (none)
	 * @grab_result: array of three integer variables pairs each
	 *	pairs consist of keycode, keygrab mode and keygrab result
	 */
	void (*keygrab_notify_list)(void *data,
				    struct tizen_keyrouter *tizen_keyrouter,
				    struct wl_surface *surface,
				    struct wl_array *grab_result);
	/**
	 * getgrab_notify_list - (none)
	 * @surface: (none)
	 * @grab_result: array of two integer variables pairs each pairs
	 *	consist of keycode, keygrab mode
	 */
	void (*getgrab_notify_list)(void *data,
				    struct tizen_keyrouter *tizen_keyrouter,
				    struct wl_surface *surface,
				    struct wl_array *grab_result);
	/**
	 * set_register_none_key_notify - (none)
	 * @surface: (none)
	 * @mode: (none)
	 */
	void (*set_register_none_key_notify)(void *data,
					     struct tizen_keyrouter *tizen_keyrouter,
					     struct wl_surface *surface,
					     uint32_t mode);
	/**
	 * keyregister_notify - (none)
	 * @status: (none)
	 */
	void (*keyregister_notify)(void *data,
				   struct tizen_keyrouter *tizen_keyrouter,
				   uint32_t status);
	/**
	 * set_input_config_notify - (none)
	 * @status: (none)
	 */
	void (*set_input_config_notify)(void *data,
					struct tizen_keyrouter *tizen_keyrouter,
					uint32_t status);
	/**
	 * key_cancel - (none)
	 * @key: (none)
	 */
	void (*key_cancel)(void *data,
			   struct tizen_keyrouter *tizen_keyrouter,
			   uint32_t key);
};

static inline int
tizen_keyrouter_add_listener(struct tizen_keyrouter *tizen_keyrouter,
			     const struct tizen_keyrouter_listener *listener, void *data)
{
	return wl_proxy_add_listener((struct wl_proxy *) tizen_keyrouter,
				     (void (**)(void)) listener, data);
}

#define TIZEN_KEYROUTER_SET_KEYGRAB	0
#define TIZEN_KEYROUTER_UNSET_KEYGRAB	1
#define TIZEN_KEYROUTER_GET_KEYGRAB_STATUS	2
#define TIZEN_KEYROUTER_SET_KEYGRAB_LIST	3
#define TIZEN_KEYROUTER_UNSET_KEYGRAB_LIST	4
#define TIZEN_KEYROUTER_GET_KEYGRAB_LIST	5
#define TIZEN_KEYROUTER_SET_REGISTER_NONE_KEY	6
#define TIZEN_KEYROUTER_GET_KEYREGISTER_STATUS	7
#define TIZEN_KEYROUTER_SET_INPUT_CONFIG	8

#define TIZEN_KEYROUTER_SET_KEYGRAB_SINCE_VERSION	1
#define TIZEN_KEYROUTER_UNSET_KEYGRAB_SINCE_VERSION	1
#define TIZEN_KEYROUTER_GET_KEYGRAB_STATUS_SINCE_VERSION	1
#define TIZEN_KEYROUTER_SET_KEYGRAB_LIST_SINCE_VERSION	1
#define TIZEN_KEYROUTER_UNSET_KEYGRAB_LIST_SINCE_VERSION	1
#define TIZEN_KEYROUTER_GET_KEYGRAB_LIST_SINCE_VERSION	1
#define TIZEN_KEYROUTER_SET_REGISTER_NONE_KEY_SINCE_VERSION	1
#define TIZEN_KEYROUTER_GET_KEYREGISTER_STATUS_SINCE_VERSION	1
#define TIZEN_KEYROUTER_SET_INPUT_CONFIG_SINCE_VERSION	1

static inline void
tizen_keyrouter_set_user_data(struct tizen_keyrouter *tizen_keyrouter, void *user_data)
{
	wl_proxy_set_user_data((struct wl_proxy *) tizen_keyrouter, user_data);
}

static inline void *
tizen_keyrouter_get_user_data(struct tizen_keyrouter *tizen_keyrouter)
{
	return wl_proxy_get_user_data((struct wl_proxy *) tizen_keyrouter);
}

static inline uint32_t
tizen_keyrouter_get_version(struct tizen_keyrouter *tizen_keyrouter)
{
	return wl_proxy_get_version((struct wl_proxy *) tizen_keyrouter);
}

static inline void
tizen_keyrouter_destroy(struct tizen_keyrouter *tizen_keyrouter)
{
	wl_proxy_destroy((struct wl_proxy *) tizen_keyrouter);
}

static inline void
tizen_keyrouter_set_keygrab(struct tizen_keyrouter *tizen_keyrouter, struct wl_surface *surface, uint32_t key, uint32_t mode)
{
	wl_proxy_marshal((struct wl_proxy *) tizen_keyrouter,
			 TIZEN_KEYROUTER_SET_KEYGRAB, surface, key, mode);
}

static inline void
tizen_keyrouter_unset_keygrab(struct tizen_keyrouter *tizen_keyrouter, struct wl_surface *surface, uint32_t key)
{
	wl_proxy_marshal((struct wl_proxy *) tizen_keyrouter,
			 TIZEN_KEYROUTER_UNSET_KEYGRAB, surface, key);
}

static inline void
tizen_keyrouter_get_keygrab_status(struct tizen_keyrouter *tizen_keyrouter, struct wl_surface *surface, uint32_t key)
{
	wl_proxy_marshal((struct wl_proxy *) tizen_keyrouter,
			 TIZEN_KEYROUTER_GET_KEYGRAB_STATUS, surface, key);
}

static inline void
tizen_keyrouter_set_keygrab_list(struct tizen_keyrouter *tizen_keyrouter, struct wl_surface *surface, struct wl_array *grab_list)
{
	wl_proxy_marshal((struct wl_proxy *) tizen_keyrouter,
			 TIZEN_KEYROUTER_SET_KEYGRAB_LIST, surface, grab_list);
}

static inline void
tizen_keyrouter_unset_keygrab_list(struct tizen_keyrouter *tizen_keyrouter, struct wl_surface *surface, struct wl_array *ungrab_list)
{
	wl_proxy_marshal((struct wl_proxy *) tizen_keyrouter,
			 TIZEN_KEYROUTER_UNSET_KEYGRAB_LIST, surface, ungrab_list);
}

static inline void
tizen_keyrouter_get_keygrab_list(struct tizen_keyrouter *tizen_keyrouter, struct wl_surface *surface)
{
	wl_proxy_marshal((struct wl_proxy *) tizen_keyrouter,
			 TIZEN_KEYROUTER_GET_KEYGRAB_LIST, surface);
}

static inline void
tizen_keyrouter_set_register_none_key(struct tizen_keyrouter *tizen_keyrouter, struct wl_surface *surface, uint32_t data)
{
	wl_proxy_marshal((struct wl_proxy *) tizen_keyrouter,
			 TIZEN_KEYROUTER_SET_REGISTER_NONE_KEY, surface, data);
}

static inline void
tizen_keyrouter_get_keyregister_status(struct tizen_keyrouter *tizen_keyrouter, uint32_t data)
{
	wl_proxy_marshal((struct wl_proxy *) tizen_keyrouter,
			 TIZEN_KEYROUTER_GET_KEYREGISTER_STATUS, data);
}

static inline void
tizen_keyrouter_set_input_config(struct tizen_keyrouter *tizen_keyrouter, struct wl_surface *surface, uint32_t config_mode, uint32_t value)
{
	wl_proxy_marshal((struct wl_proxy *) tizen_keyrouter,
			 TIZEN_KEYROUTER_SET_INPUT_CONFIG, surface, config_mode, value);
}

/**
 * tizen_screenshooter - interface for tizen-screenshooter
 * @format: supported format for screenshooter
 * @screenshooter_notify: send notification of screenshooter
 *
 * Clients can get a screenmirror object from this interface.
 */
struct tizen_screenshooter_listener {
	/**
	 * format - supported format for screenshooter
	 * @format: (none)
	 *
	 * The tbm format codes match the #defines in tbm_surface.h. The
	 * formats actually supported by the compositor will be reported by
	 * the format event.
	 */
	void (*format)(void *data,
		       struct tizen_screenshooter *tizen_screenshooter,
		       uint32_t format);
	/**
	 * screenshooter_notify - send notification of screenshooter
	 * @noti: (none)
	 *
	 * Clients can get notification of screenshooter.
	 */
	void (*screenshooter_notify)(void *data,
				     struct tizen_screenshooter *tizen_screenshooter,
				     uint32_t noti);
};

static inline int
tizen_screenshooter_add_listener(struct tizen_screenshooter *tizen_screenshooter,
				 const struct tizen_screenshooter_listener *listener, void *data)
{
	return wl_proxy_add_listener((struct wl_proxy *) tizen_screenshooter,
				     (void (**)(void)) listener, data);
}

#define TIZEN_SCREENSHOOTER_GET_SCREENMIRROR	0

#define TIZEN_SCREENSHOOTER_GET_SCREENMIRROR_SINCE_VERSION	1

static inline void
tizen_screenshooter_set_user_data(struct tizen_screenshooter *tizen_screenshooter, void *user_data)
{
	wl_proxy_set_user_data((struct wl_proxy *) tizen_screenshooter, user_data);
}

static inline void *
tizen_screenshooter_get_user_data(struct tizen_screenshooter *tizen_screenshooter)
{
	return wl_proxy_get_user_data((struct wl_proxy *) tizen_screenshooter);
}

static inline uint32_t
tizen_screenshooter_get_version(struct tizen_screenshooter *tizen_screenshooter)
{
	return wl_proxy_get_version((struct wl_proxy *) tizen_screenshooter);
}

static inline void
tizen_screenshooter_destroy(struct tizen_screenshooter *tizen_screenshooter)
{
	wl_proxy_destroy((struct wl_proxy *) tizen_screenshooter);
}

static inline struct tizen_screenmirror *
tizen_screenshooter_get_screenmirror(struct tizen_screenshooter *tizen_screenshooter, struct wl_output *output)
{
	struct wl_proxy *id;

	id = wl_proxy_marshal_constructor((struct wl_proxy *) tizen_screenshooter,
			 TIZEN_SCREENSHOOTER_GET_SCREENMIRROR, &tizen_screenmirror_interface, NULL, output);

	return (struct tizen_screenmirror *) id;
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
 * @dequeued: dequeued event
 * @content: content changed event
 * @stop: stop event
 *
 * A client can use this interface to get stream images of screen. Before
 * starting, queue all buffers. Then, start a screenmirror. After starting,
 * a dequeued event will occur when drawing a captured image on a buffer is
 * finished. You might need to queue the dequeued buffer again to get a new
 * image from display server.
 */
struct tizen_screenmirror_listener {
	/**
	 * dequeued - dequeued event
	 * @buffer: dequeued buffer which contains a captured image
	 *
	 * occurs when drawing a captured image on a buffer is finished
	 */
	void (*dequeued)(void *data,
			 struct tizen_screenmirror *tizen_screenmirror,
			 struct wl_buffer *buffer);
	/**
	 * content - content changed event
	 * @content: (none)
	 *
	 * occurs when the content of a captured image is changed.
	 * (normal or video)
	 */
	void (*content)(void *data,
			struct tizen_screenmirror *tizen_screenmirror,
			uint32_t content);
	/**
	 * stop - stop event
	 *
	 * occurs when the screenmirror is stopped eventually
	 */
	void (*stop)(void *data,
		     struct tizen_screenmirror *tizen_screenmirror);
};

static inline int
tizen_screenmirror_add_listener(struct tizen_screenmirror *tizen_screenmirror,
				const struct tizen_screenmirror_listener *listener, void *data)
{
	return wl_proxy_add_listener((struct wl_proxy *) tizen_screenmirror,
				     (void (**)(void)) listener, data);
}

#define TIZEN_SCREENMIRROR_DESTROY	0
#define TIZEN_SCREENMIRROR_SET_STRETCH	1
#define TIZEN_SCREENMIRROR_QUEUE	2
#define TIZEN_SCREENMIRROR_DEQUEUE	3
#define TIZEN_SCREENMIRROR_START	4
#define TIZEN_SCREENMIRROR_STOP	5

#define TIZEN_SCREENMIRROR_DESTROY_SINCE_VERSION	1
#define TIZEN_SCREENMIRROR_SET_STRETCH_SINCE_VERSION	1
#define TIZEN_SCREENMIRROR_QUEUE_SINCE_VERSION	1
#define TIZEN_SCREENMIRROR_DEQUEUE_SINCE_VERSION	1
#define TIZEN_SCREENMIRROR_START_SINCE_VERSION	1
#define TIZEN_SCREENMIRROR_STOP_SINCE_VERSION	1

static inline void
tizen_screenmirror_set_user_data(struct tizen_screenmirror *tizen_screenmirror, void *user_data)
{
	wl_proxy_set_user_data((struct wl_proxy *) tizen_screenmirror, user_data);
}

static inline void *
tizen_screenmirror_get_user_data(struct tizen_screenmirror *tizen_screenmirror)
{
	return wl_proxy_get_user_data((struct wl_proxy *) tizen_screenmirror);
}

static inline uint32_t
tizen_screenmirror_get_version(struct tizen_screenmirror *tizen_screenmirror)
{
	return wl_proxy_get_version((struct wl_proxy *) tizen_screenmirror);
}

static inline void
tizen_screenmirror_destroy(struct tizen_screenmirror *tizen_screenmirror)
{
	wl_proxy_marshal((struct wl_proxy *) tizen_screenmirror,
			 TIZEN_SCREENMIRROR_DESTROY);

	wl_proxy_destroy((struct wl_proxy *) tizen_screenmirror);
}

static inline void
tizen_screenmirror_set_stretch(struct tizen_screenmirror *tizen_screenmirror, uint32_t stretch)
{
	wl_proxy_marshal((struct wl_proxy *) tizen_screenmirror,
			 TIZEN_SCREENMIRROR_SET_STRETCH, stretch);
}

static inline void
tizen_screenmirror_queue(struct tizen_screenmirror *tizen_screenmirror, struct wl_buffer *buffer)
{
	wl_proxy_marshal((struct wl_proxy *) tizen_screenmirror,
			 TIZEN_SCREENMIRROR_QUEUE, buffer);
}

static inline void
tizen_screenmirror_dequeue(struct tizen_screenmirror *tizen_screenmirror, struct wl_buffer *buffer)
{
	wl_proxy_marshal((struct wl_proxy *) tizen_screenmirror,
			 TIZEN_SCREENMIRROR_DEQUEUE, buffer);
}

static inline void
tizen_screenmirror_start(struct tizen_screenmirror *tizen_screenmirror)
{
	wl_proxy_marshal((struct wl_proxy *) tizen_screenmirror,
			 TIZEN_SCREENMIRROR_START);
}

static inline void
tizen_screenmirror_stop(struct tizen_screenmirror *tizen_screenmirror)
{
	wl_proxy_marshal((struct wl_proxy *) tizen_screenmirror,
			 TIZEN_SCREENMIRROR_STOP);
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
 * @format: supported format for video
 *
 * Clients can get the video information that the compositor can handle
 * from this interface.
 */
struct tizen_video_listener {
	/**
	 * format - supported format for video
	 * @format: (none)
	 *
	 * The tbm format codes match the #defines in tbm_surface.h. The
	 * formats actually supported by the compositor will be reported by
	 * the format event.
	 */
	void (*format)(void *data,
		       struct tizen_video *tizen_video,
		       uint32_t format);
};

static inline int
tizen_video_add_listener(struct tizen_video *tizen_video,
			 const struct tizen_video_listener *listener, void *data)
{
	return wl_proxy_add_listener((struct wl_proxy *) tizen_video,
				     (void (**)(void)) listener, data);
}

#define TIZEN_VIDEO_GET_OBJECT	0
#define TIZEN_VIDEO_GET_VIEWPORT	1

#define TIZEN_VIDEO_GET_OBJECT_SINCE_VERSION	1
#define TIZEN_VIDEO_GET_VIEWPORT_SINCE_VERSION	1

static inline void
tizen_video_set_user_data(struct tizen_video *tizen_video, void *user_data)
{
	wl_proxy_set_user_data((struct wl_proxy *) tizen_video, user_data);
}

static inline void *
tizen_video_get_user_data(struct tizen_video *tizen_video)
{
	return wl_proxy_get_user_data((struct wl_proxy *) tizen_video);
}

static inline uint32_t
tizen_video_get_version(struct tizen_video *tizen_video)
{
	return wl_proxy_get_version((struct wl_proxy *) tizen_video);
}

static inline void
tizen_video_destroy(struct tizen_video *tizen_video)
{
	wl_proxy_destroy((struct wl_proxy *) tizen_video);
}

static inline struct tizen_video_object *
tizen_video_get_object(struct tizen_video *tizen_video, struct wl_surface *surface)
{
	struct wl_proxy *id;

	id = wl_proxy_marshal_constructor((struct wl_proxy *) tizen_video,
			 TIZEN_VIDEO_GET_OBJECT, &tizen_video_object_interface, NULL, surface);

	return (struct tizen_video_object *) id;
}

static inline struct tizen_viewport *
tizen_video_get_viewport(struct tizen_video *tizen_video, struct wl_surface *surface)
{
	struct wl_proxy *id;

	id = wl_proxy_marshal_constructor((struct wl_proxy *) tizen_video,
			 TIZEN_VIDEO_GET_VIEWPORT, &tizen_viewport_interface, NULL, surface);

	return (struct tizen_viewport *) id;
}

struct tizen_video_object_listener {
	/**
	 * attribute - (none)
	 * @name: (none)
	 * @value: (none)
	 */
	void (*attribute)(void *data,
			  struct tizen_video_object *tizen_video_object,
			  const char *name,
			  uint32_t value);
	/**
	 * size - (none)
	 * @min_w: (none)
	 * @min_h: (none)
	 * @max_w: (none)
	 * @max_h: (none)
	 * @prefer_align: (none)
	 */
	void (*size)(void *data,
		     struct tizen_video_object *tizen_video_object,
		     int32_t min_w,
		     int32_t min_h,
		     int32_t max_w,
		     int32_t max_h,
		     int32_t prefer_align);
};

static inline int
tizen_video_object_add_listener(struct tizen_video_object *tizen_video_object,
				const struct tizen_video_object_listener *listener, void *data)
{
	return wl_proxy_add_listener((struct wl_proxy *) tizen_video_object,
				     (void (**)(void)) listener, data);
}

#define TIZEN_VIDEO_OBJECT_DESTROY	0
#define TIZEN_VIDEO_OBJECT_SET_ATTRIBUTE	1

#define TIZEN_VIDEO_OBJECT_DESTROY_SINCE_VERSION	1
#define TIZEN_VIDEO_OBJECT_SET_ATTRIBUTE_SINCE_VERSION	1

static inline void
tizen_video_object_set_user_data(struct tizen_video_object *tizen_video_object, void *user_data)
{
	wl_proxy_set_user_data((struct wl_proxy *) tizen_video_object, user_data);
}

static inline void *
tizen_video_object_get_user_data(struct tizen_video_object *tizen_video_object)
{
	return wl_proxy_get_user_data((struct wl_proxy *) tizen_video_object);
}

static inline uint32_t
tizen_video_object_get_version(struct tizen_video_object *tizen_video_object)
{
	return wl_proxy_get_version((struct wl_proxy *) tizen_video_object);
}

static inline void
tizen_video_object_destroy(struct tizen_video_object *tizen_video_object)
{
	wl_proxy_marshal((struct wl_proxy *) tizen_video_object,
			 TIZEN_VIDEO_OBJECT_DESTROY);

	wl_proxy_destroy((struct wl_proxy *) tizen_video_object);
}

static inline void
tizen_video_object_set_attribute(struct tizen_video_object *tizen_video_object, const char *name, int32_t value)
{
	wl_proxy_marshal((struct wl_proxy *) tizen_video_object,
			 TIZEN_VIDEO_OBJECT_SET_ATTRIBUTE, name, value);
}

#ifndef TIZEN_SUBSURFACE_WATCHER_MSG_ENUM
#define TIZEN_SUBSURFACE_WATCHER_MSG_ENUM
enum tizen_subsurface_watcher_msg {
	TIZEN_SUBSURFACE_WATCHER_MSG_SUCCESS = 0,
	TIZEN_SUBSURFACE_WATCHER_MSG_PARENT_ID_INVALID = 1,
	TIZEN_SUBSURFACE_WATCHER_MSG_PARENT_ID_DESTROYED = 2,
};
#endif /* TIZEN_SUBSURFACE_WATCHER_MSG_ENUM */

struct tizen_subsurface_watcher_listener {
	/**
	 * message - (none)
	 * @value: (none)
	 */
	void (*message)(void *data,
			struct tizen_subsurface_watcher *tizen_subsurface_watcher,
			uint32_t value);
};

static inline int
tizen_subsurface_watcher_add_listener(struct tizen_subsurface_watcher *tizen_subsurface_watcher,
				      const struct tizen_subsurface_watcher_listener *listener, void *data)
{
	return wl_proxy_add_listener((struct wl_proxy *) tizen_subsurface_watcher,
				     (void (**)(void)) listener, data);
}


static inline void
tizen_subsurface_watcher_set_user_data(struct tizen_subsurface_watcher *tizen_subsurface_watcher, void *user_data)
{
	wl_proxy_set_user_data((struct wl_proxy *) tizen_subsurface_watcher, user_data);
}

static inline void *
tizen_subsurface_watcher_get_user_data(struct tizen_subsurface_watcher *tizen_subsurface_watcher)
{
	return wl_proxy_get_user_data((struct wl_proxy *) tizen_subsurface_watcher);
}

static inline uint32_t
tizen_subsurface_watcher_get_version(struct tizen_subsurface_watcher *tizen_subsurface_watcher)
{
	return wl_proxy_get_version((struct wl_proxy *) tizen_subsurface_watcher);
}

static inline void
tizen_subsurface_watcher_destroy(struct tizen_subsurface_watcher *tizen_subsurface_watcher)
{
	wl_proxy_destroy((struct wl_proxy *) tizen_subsurface_watcher);
}

/**
 * tizen_viewport - the viewport for a surface
 * @destination_changed: (none)
 * @parent_size: (none)
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
struct tizen_viewport_listener {
	/**
	 * destination_changed - (none)
	 * @transform: (none)
	 * @x: (none)
	 * @y: (none)
	 * @width: (none)
	 * @height: (none)
	 */
	void (*destination_changed)(void *data,
				    struct tizen_viewport *tizen_viewport,
				    uint32_t transform,
				    int32_t x,
				    int32_t y,
				    uint32_t width,
				    uint32_t height);
	/**
	 * parent_size - (none)
	 * @width: (none)
	 * @height: (none)
	 */
	void (*parent_size)(void *data,
			    struct tizen_viewport *tizen_viewport,
			    uint32_t width,
			    uint32_t height);
};

static inline int
tizen_viewport_add_listener(struct tizen_viewport *tizen_viewport,
			    const struct tizen_viewport_listener *listener, void *data)
{
	return wl_proxy_add_listener((struct wl_proxy *) tizen_viewport,
				     (void (**)(void)) listener, data);
}

#define TIZEN_VIEWPORT_DESTROY	0
#define TIZEN_VIEWPORT_SET_TRANSFORM	1
#define TIZEN_VIEWPORT_SET_SOURCE	2
#define TIZEN_VIEWPORT_SET_DESTINATION	3
#define TIZEN_VIEWPORT_SET_DESTINATION_RATIO	4
#define TIZEN_VIEWPORT_GET_DESTINATION_MODE	5
#define TIZEN_VIEWPORT_QUERY_PARENT_SIZE	6
#define TIZEN_VIEWPORT_FOLLOW_PARENT_TRANSFORM	7
#define TIZEN_VIEWPORT_UNFOLLOW_PARENT_TRANSFORM	8

#define TIZEN_VIEWPORT_DESTROY_SINCE_VERSION	1
#define TIZEN_VIEWPORT_SET_TRANSFORM_SINCE_VERSION	1
#define TIZEN_VIEWPORT_SET_SOURCE_SINCE_VERSION	1
#define TIZEN_VIEWPORT_SET_DESTINATION_SINCE_VERSION	1
#define TIZEN_VIEWPORT_SET_DESTINATION_RATIO_SINCE_VERSION	1
#define TIZEN_VIEWPORT_GET_DESTINATION_MODE_SINCE_VERSION	1
#define TIZEN_VIEWPORT_QUERY_PARENT_SIZE_SINCE_VERSION	1
#define TIZEN_VIEWPORT_FOLLOW_PARENT_TRANSFORM_SINCE_VERSION	1
#define TIZEN_VIEWPORT_UNFOLLOW_PARENT_TRANSFORM_SINCE_VERSION	1

static inline void
tizen_viewport_set_user_data(struct tizen_viewport *tizen_viewport, void *user_data)
{
	wl_proxy_set_user_data((struct wl_proxy *) tizen_viewport, user_data);
}

static inline void *
tizen_viewport_get_user_data(struct tizen_viewport *tizen_viewport)
{
	return wl_proxy_get_user_data((struct wl_proxy *) tizen_viewport);
}

static inline uint32_t
tizen_viewport_get_version(struct tizen_viewport *tizen_viewport)
{
	return wl_proxy_get_version((struct wl_proxy *) tizen_viewport);
}

static inline void
tizen_viewport_destroy(struct tizen_viewport *tizen_viewport)
{
	wl_proxy_marshal((struct wl_proxy *) tizen_viewport,
			 TIZEN_VIEWPORT_DESTROY);

	wl_proxy_destroy((struct wl_proxy *) tizen_viewport);
}

static inline void
tizen_viewport_set_transform(struct tizen_viewport *tizen_viewport, uint32_t transform)
{
	wl_proxy_marshal((struct wl_proxy *) tizen_viewport,
			 TIZEN_VIEWPORT_SET_TRANSFORM, transform);
}

static inline void
tizen_viewport_set_source(struct tizen_viewport *tizen_viewport, uint32_t x, uint32_t y, uint32_t width, uint32_t height)
{
	wl_proxy_marshal((struct wl_proxy *) tizen_viewport,
			 TIZEN_VIEWPORT_SET_SOURCE, x, y, width, height);
}

static inline void
tizen_viewport_set_destination(struct tizen_viewport *tizen_viewport, int32_t x, int32_t y, uint32_t width, uint32_t height)
{
	wl_proxy_marshal((struct wl_proxy *) tizen_viewport,
			 TIZEN_VIEWPORT_SET_DESTINATION, x, y, width, height);
}

static inline void
tizen_viewport_set_destination_ratio(struct tizen_viewport *tizen_viewport, wl_fixed_t x, wl_fixed_t y, wl_fixed_t width, wl_fixed_t height)
{
	wl_proxy_marshal((struct wl_proxy *) tizen_viewport,
			 TIZEN_VIEWPORT_SET_DESTINATION_RATIO, x, y, width, height);
}

static inline struct tizen_destination_mode *
tizen_viewport_get_destination_mode(struct tizen_viewport *tizen_viewport)
{
	struct wl_proxy *id;

	id = wl_proxy_marshal_constructor((struct wl_proxy *) tizen_viewport,
			 TIZEN_VIEWPORT_GET_DESTINATION_MODE, &tizen_destination_mode_interface, NULL);

	return (struct tizen_destination_mode *) id;
}

static inline void
tizen_viewport_query_parent_size(struct tizen_viewport *tizen_viewport)
{
	wl_proxy_marshal((struct wl_proxy *) tizen_viewport,
			 TIZEN_VIEWPORT_QUERY_PARENT_SIZE);
}

static inline void
tizen_viewport_follow_parent_transform(struct tizen_viewport *tizen_viewport)
{
	wl_proxy_marshal((struct wl_proxy *) tizen_viewport,
			 TIZEN_VIEWPORT_FOLLOW_PARENT_TRANSFORM);
}

static inline void
tizen_viewport_unfollow_parent_transform(struct tizen_viewport *tizen_viewport)
{
	wl_proxy_marshal((struct wl_proxy *) tizen_viewport,
			 TIZEN_VIEWPORT_UNFOLLOW_PARENT_TRANSFORM);
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

#define TIZEN_DESTINATION_MODE_DESTROY	0
#define TIZEN_DESTINATION_MODE_FOLLOW_PARENT_TRANSFORM	1
#define TIZEN_DESTINATION_MODE_UNFOLLOW_PARENT_TRANSFORM	2
#define TIZEN_DESTINATION_MODE_SET	3
#define TIZEN_DESTINATION_MODE_SET_RATIO	4
#define TIZEN_DESTINATION_MODE_SET_SCALE	5
#define TIZEN_DESTINATION_MODE_SET_ALIGN	6
#define TIZEN_DESTINATION_MODE_SET_OFFSET	7

#define TIZEN_DESTINATION_MODE_DESTROY_SINCE_VERSION	1
#define TIZEN_DESTINATION_MODE_FOLLOW_PARENT_TRANSFORM_SINCE_VERSION	1
#define TIZEN_DESTINATION_MODE_UNFOLLOW_PARENT_TRANSFORM_SINCE_VERSION	1
#define TIZEN_DESTINATION_MODE_SET_SINCE_VERSION	1
#define TIZEN_DESTINATION_MODE_SET_RATIO_SINCE_VERSION	1
#define TIZEN_DESTINATION_MODE_SET_SCALE_SINCE_VERSION	1
#define TIZEN_DESTINATION_MODE_SET_ALIGN_SINCE_VERSION	1
#define TIZEN_DESTINATION_MODE_SET_OFFSET_SINCE_VERSION	1

static inline void
tizen_destination_mode_set_user_data(struct tizen_destination_mode *tizen_destination_mode, void *user_data)
{
	wl_proxy_set_user_data((struct wl_proxy *) tizen_destination_mode, user_data);
}

static inline void *
tizen_destination_mode_get_user_data(struct tizen_destination_mode *tizen_destination_mode)
{
	return wl_proxy_get_user_data((struct wl_proxy *) tizen_destination_mode);
}

static inline uint32_t
tizen_destination_mode_get_version(struct tizen_destination_mode *tizen_destination_mode)
{
	return wl_proxy_get_version((struct wl_proxy *) tizen_destination_mode);
}

static inline void
tizen_destination_mode_destroy(struct tizen_destination_mode *tizen_destination_mode)
{
	wl_proxy_marshal((struct wl_proxy *) tizen_destination_mode,
			 TIZEN_DESTINATION_MODE_DESTROY);

	wl_proxy_destroy((struct wl_proxy *) tizen_destination_mode);
}

static inline void
tizen_destination_mode_follow_parent_transform(struct tizen_destination_mode *tizen_destination_mode)
{
	wl_proxy_marshal((struct wl_proxy *) tizen_destination_mode,
			 TIZEN_DESTINATION_MODE_FOLLOW_PARENT_TRANSFORM);
}

static inline void
tizen_destination_mode_unfollow_parent_transform(struct tizen_destination_mode *tizen_destination_mode)
{
	wl_proxy_marshal((struct wl_proxy *) tizen_destination_mode,
			 TIZEN_DESTINATION_MODE_UNFOLLOW_PARENT_TRANSFORM);
}

static inline void
tizen_destination_mode_set(struct tizen_destination_mode *tizen_destination_mode, uint32_t mode)
{
	wl_proxy_marshal((struct wl_proxy *) tizen_destination_mode,
			 TIZEN_DESTINATION_MODE_SET, mode);
}

static inline void
tizen_destination_mode_set_ratio(struct tizen_destination_mode *tizen_destination_mode, wl_fixed_t horizontal, wl_fixed_t vertical)
{
	wl_proxy_marshal((struct wl_proxy *) tizen_destination_mode,
			 TIZEN_DESTINATION_MODE_SET_RATIO, horizontal, vertical);
}

static inline void
tizen_destination_mode_set_scale(struct tizen_destination_mode *tizen_destination_mode, wl_fixed_t horizontal, wl_fixed_t vertical)
{
	wl_proxy_marshal((struct wl_proxy *) tizen_destination_mode,
			 TIZEN_DESTINATION_MODE_SET_SCALE, horizontal, vertical);
}

static inline void
tizen_destination_mode_set_align(struct tizen_destination_mode *tizen_destination_mode, wl_fixed_t horizontal, wl_fixed_t vertical)
{
	wl_proxy_marshal((struct wl_proxy *) tizen_destination_mode,
			 TIZEN_DESTINATION_MODE_SET_ALIGN, horizontal, vertical);
}

static inline void
tizen_destination_mode_set_offset(struct tizen_destination_mode *tizen_destination_mode, int32_t x, int32_t y, int32_t w, int32_t h)
{
	wl_proxy_marshal((struct wl_proxy *) tizen_destination_mode,
			 TIZEN_DESTINATION_MODE_SET_OFFSET, x, y, w, h);
}

/**
 * tizen_embedded_compositor - global embedded compositor object
 * @socket: socket fd
 *
 * The global obejct. Wayland has 3 type of compositor, embedded
 * compositor is one of them. But tizen application is sendboxing by smack,
 * then a application not allow commutication to other application by
 * socket. So system or session compositor create socket and send to
 * embedded compostior.
 */
struct tizen_embedded_compositor_listener {
	/**
	 * socket - socket fd
	 * @sock_fd: (none)
	 *
	 * Sent immediately after get_socket request
	 */
	void (*socket)(void *data,
		       struct tizen_embedded_compositor *tizen_embedded_compositor,
		       int32_t sock_fd);
};

static inline int
tizen_embedded_compositor_add_listener(struct tizen_embedded_compositor *tizen_embedded_compositor,
				       const struct tizen_embedded_compositor_listener *listener, void *data)
{
	return wl_proxy_add_listener((struct wl_proxy *) tizen_embedded_compositor,
				     (void (**)(void)) listener, data);
}

#define TIZEN_EMBEDDED_COMPOSITOR_GET_SOCKET	0

#define TIZEN_EMBEDDED_COMPOSITOR_GET_SOCKET_SINCE_VERSION	1

static inline void
tizen_embedded_compositor_set_user_data(struct tizen_embedded_compositor *tizen_embedded_compositor, void *user_data)
{
	wl_proxy_set_user_data((struct wl_proxy *) tizen_embedded_compositor, user_data);
}

static inline void *
tizen_embedded_compositor_get_user_data(struct tizen_embedded_compositor *tizen_embedded_compositor)
{
	return wl_proxy_get_user_data((struct wl_proxy *) tizen_embedded_compositor);
}

static inline uint32_t
tizen_embedded_compositor_get_version(struct tizen_embedded_compositor *tizen_embedded_compositor)
{
	return wl_proxy_get_version((struct wl_proxy *) tizen_embedded_compositor);
}

static inline void
tizen_embedded_compositor_destroy(struct tizen_embedded_compositor *tizen_embedded_compositor)
{
	wl_proxy_destroy((struct wl_proxy *) tizen_embedded_compositor);
}

static inline void
tizen_embedded_compositor_get_socket(struct tizen_embedded_compositor *tizen_embedded_compositor)
{
	wl_proxy_marshal((struct wl_proxy *) tizen_embedded_compositor,
			 TIZEN_EMBEDDED_COMPOSITOR_GET_SOCKET);
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
 * @device_add: device addition event
 * @device_remove: device removal event
 * @error: error event
 * @block_expired: block expiration event
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
struct tizen_input_device_manager_listener {
	/**
	 * device_add - device addition event
	 * @serial: (none)
	 * @identifier: (none)
	 * @device: (none)
	 * @seat: (none)
	 *
	 * The device add/remove notification is going to be sent when a
	 * physical/logical device is added/removed to/from the given seat.
	 * Note that a tizen input device object can be assigned to a
	 * wl_seat and can also be assigned to the other wl_seat at any
	 * time. Whenever a relationship between a tizen input device
	 * object changes, device remove event from the current wl_seat
	 * object will be made and device add event to the other wl_seat
	 * object will also be mode.
	 */
	void (*device_add)(void *data,
			   struct tizen_input_device_manager *tizen_input_device_manager,
			   uint32_t serial,
			   const char *identifier,
			   struct tizen_input_device *device,
			   struct wl_seat *seat);
	/**
	 * device_remove - device removal event
	 * @serial: (none)
	 * @identifier: (none)
	 * @device: (none)
	 * @seat: (none)
	 *
	 * The device add/remove notification is going to be sent when a
	 * physical/logical device is added/removed to/from the given seat.
	 * Note that a tizen input device object can be assigned to a
	 * wl_seat and can also be assigned to the other wl_seat at any
	 * time. Whenever a relationship between a tizen input device
	 * object changes, device remove event from the current wl_seat
	 * object will be made and device add event to the other wl_seat
	 * object will also be mode.
	 */
	void (*device_remove)(void *data,
			      struct tizen_input_device_manager *tizen_input_device_manager,
			      uint32_t serial,
			      const char *identifier,
			      struct tizen_input_device *device,
			      struct wl_seat *seat);
	/**
	 * error - error event
	 * @errorcode: (none)
	 *
	 * 
	 */
	void (*error)(void *data,
		      struct tizen_input_device_manager *tizen_input_device_manager,
		      uint32_t errorcode);
	/**
	 * block_expired - block expiration event
	 *
	 * This event will be sent if the duration of existing block is
	 * expired. Note that no block expired event will be sent if there
	 * is no block for the client.
	 */
	void (*block_expired)(void *data,
			      struct tizen_input_device_manager *tizen_input_device_manager);
};

static inline int
tizen_input_device_manager_add_listener(struct tizen_input_device_manager *tizen_input_device_manager,
					const struct tizen_input_device_manager_listener *listener, void *data)
{
	return wl_proxy_add_listener((struct wl_proxy *) tizen_input_device_manager,
				     (void (**)(void)) listener, data);
}

#define TIZEN_INPUT_DEVICE_MANAGER_BLOCK_EVENTS	0
#define TIZEN_INPUT_DEVICE_MANAGER_UNBLOCK_EVENTS	1
#define TIZEN_INPUT_DEVICE_MANAGER_INIT_GENERATOR	2
#define TIZEN_INPUT_DEVICE_MANAGER_DEINIT_GENERATOR	3
#define TIZEN_INPUT_DEVICE_MANAGER_GENERATE_KEY	4
#define TIZEN_INPUT_DEVICE_MANAGER_GENERATE_POINTER	5
#define TIZEN_INPUT_DEVICE_MANAGER_GENERATE_TOUCH	6
#define TIZEN_INPUT_DEVICE_MANAGER_POINTER_WARP	7

#define TIZEN_INPUT_DEVICE_MANAGER_BLOCK_EVENTS_SINCE_VERSION	1
#define TIZEN_INPUT_DEVICE_MANAGER_UNBLOCK_EVENTS_SINCE_VERSION	1
#define TIZEN_INPUT_DEVICE_MANAGER_INIT_GENERATOR_SINCE_VERSION	1
#define TIZEN_INPUT_DEVICE_MANAGER_DEINIT_GENERATOR_SINCE_VERSION	1
#define TIZEN_INPUT_DEVICE_MANAGER_GENERATE_KEY_SINCE_VERSION	1
#define TIZEN_INPUT_DEVICE_MANAGER_GENERATE_POINTER_SINCE_VERSION	1
#define TIZEN_INPUT_DEVICE_MANAGER_GENERATE_TOUCH_SINCE_VERSION	1
#define TIZEN_INPUT_DEVICE_MANAGER_POINTER_WARP_SINCE_VERSION	1

static inline void
tizen_input_device_manager_set_user_data(struct tizen_input_device_manager *tizen_input_device_manager, void *user_data)
{
	wl_proxy_set_user_data((struct wl_proxy *) tizen_input_device_manager, user_data);
}

static inline void *
tizen_input_device_manager_get_user_data(struct tizen_input_device_manager *tizen_input_device_manager)
{
	return wl_proxy_get_user_data((struct wl_proxy *) tizen_input_device_manager);
}

static inline uint32_t
tizen_input_device_manager_get_version(struct tizen_input_device_manager *tizen_input_device_manager)
{
	return wl_proxy_get_version((struct wl_proxy *) tizen_input_device_manager);
}

static inline void
tizen_input_device_manager_destroy(struct tizen_input_device_manager *tizen_input_device_manager)
{
	wl_proxy_destroy((struct wl_proxy *) tizen_input_device_manager);
}

static inline void
tizen_input_device_manager_block_events(struct tizen_input_device_manager *tizen_input_device_manager, uint32_t serial, uint32_t clas, uint32_t duration)
{
	wl_proxy_marshal((struct wl_proxy *) tizen_input_device_manager,
			 TIZEN_INPUT_DEVICE_MANAGER_BLOCK_EVENTS, serial, clas, duration);
}

static inline void
tizen_input_device_manager_unblock_events(struct tizen_input_device_manager *tizen_input_device_manager, uint32_t serial)
{
	wl_proxy_marshal((struct wl_proxy *) tizen_input_device_manager,
			 TIZEN_INPUT_DEVICE_MANAGER_UNBLOCK_EVENTS, serial);
}

static inline void
tizen_input_device_manager_init_generator(struct tizen_input_device_manager *tizen_input_device_manager)
{
	wl_proxy_marshal((struct wl_proxy *) tizen_input_device_manager,
			 TIZEN_INPUT_DEVICE_MANAGER_INIT_GENERATOR);
}

static inline void
tizen_input_device_manager_deinit_generator(struct tizen_input_device_manager *tizen_input_device_manager)
{
	wl_proxy_marshal((struct wl_proxy *) tizen_input_device_manager,
			 TIZEN_INPUT_DEVICE_MANAGER_DEINIT_GENERATOR);
}

static inline void
tizen_input_device_manager_generate_key(struct tizen_input_device_manager *tizen_input_device_manager, const char *keyname, uint32_t pressed)
{
	wl_proxy_marshal((struct wl_proxy *) tizen_input_device_manager,
			 TIZEN_INPUT_DEVICE_MANAGER_GENERATE_KEY, keyname, pressed);
}

static inline void
tizen_input_device_manager_generate_pointer(struct tizen_input_device_manager *tizen_input_device_manager, uint32_t type, uint32_t x, uint32_t y, uint32_t button)
{
	wl_proxy_marshal((struct wl_proxy *) tizen_input_device_manager,
			 TIZEN_INPUT_DEVICE_MANAGER_GENERATE_POINTER, type, x, y, button);
}

static inline void
tizen_input_device_manager_generate_touch(struct tizen_input_device_manager *tizen_input_device_manager, uint32_t type, uint32_t x, uint32_t y, uint32_t finger)
{
	wl_proxy_marshal((struct wl_proxy *) tizen_input_device_manager,
			 TIZEN_INPUT_DEVICE_MANAGER_GENERATE_TOUCH, type, x, y, finger);
}

static inline void
tizen_input_device_manager_pointer_warp(struct tizen_input_device_manager *tizen_input_device_manager, struct wl_surface *surface, wl_fixed_t x, wl_fixed_t y)
{
	wl_proxy_marshal((struct wl_proxy *) tizen_input_device_manager,
			 TIZEN_INPUT_DEVICE_MANAGER_POINTER_WARP, surface, x, y);
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
 * @device_info: event contains device information
 * @event_device: event indicates the source device associated with a
 *	wl_pointer/keyboard/touch event
 * @axis: axis change event
 *
 * The tizen_input_device interface represents one or more input devices
 * associated with a physical/logical input device. This interface provides
 * device specific information/events to allows for client to identify the
 * source device of an event or to get the additional axes/attributes of a
 * device. Note that a tizen_input_device object can be used for a physical
 * input device and can also be used for a group of input devices. e.g. a
 * group of mouse devices
 */
struct tizen_input_device_listener {
	/**
	 * device_info - event contains device information
	 * @name: (none)
	 * @clas: (none)
	 * @subclas: (none)
	 * @axes: array of axis enum
	 *
	 * 
	 */
	void (*device_info)(void *data,
			    struct tizen_input_device *tizen_input_device,
			    const char *name,
			    uint32_t clas,
			    uint32_t subclas,
			    struct wl_array *axes);
	/**
	 * event_device - event indicates the source device associated
	 *	with a wl_pointer/keyboard/touch event
	 * @serial: (none)
	 * @name: (none)
	 * @time: timestamp with millisecond granularity
	 *
	 * 
	 */
	void (*event_device)(void *data,
			     struct tizen_input_device *tizen_input_device,
			     uint32_t serial,
			     const char *name,
			     uint32_t time);
	/**
	 * axis - axis change event
	 * @axis_type: (none)
	 * @value: axis value
	 *
	 * 
	 */
	void (*axis)(void *data,
		     struct tizen_input_device *tizen_input_device,
		     uint32_t axis_type,
		     wl_fixed_t value);
};

static inline int
tizen_input_device_add_listener(struct tizen_input_device *tizen_input_device,
				const struct tizen_input_device_listener *listener, void *data)
{
	return wl_proxy_add_listener((struct wl_proxy *) tizen_input_device,
				     (void (**)(void)) listener, data);
}

#define TIZEN_INPUT_DEVICE_SELECT_AXES	0
#define TIZEN_INPUT_DEVICE_RELEASE	1

#define TIZEN_INPUT_DEVICE_SELECT_AXES_SINCE_VERSION	1
#define TIZEN_INPUT_DEVICE_RELEASE_SINCE_VERSION	1

static inline void
tizen_input_device_set_user_data(struct tizen_input_device *tizen_input_device, void *user_data)
{
	wl_proxy_set_user_data((struct wl_proxy *) tizen_input_device, user_data);
}

static inline void *
tizen_input_device_get_user_data(struct tizen_input_device *tizen_input_device)
{
	return wl_proxy_get_user_data((struct wl_proxy *) tizen_input_device);
}

static inline uint32_t
tizen_input_device_get_version(struct tizen_input_device *tizen_input_device)
{
	return wl_proxy_get_version((struct wl_proxy *) tizen_input_device);
}

static inline void
tizen_input_device_destroy(struct tizen_input_device *tizen_input_device)
{
	wl_proxy_destroy((struct wl_proxy *) tizen_input_device);
}

static inline void
tizen_input_device_select_axes(struct tizen_input_device *tizen_input_device, struct wl_array *axes)
{
	wl_proxy_marshal((struct wl_proxy *) tizen_input_device,
			 TIZEN_INPUT_DEVICE_SELECT_AXES, axes);
}

static inline void
tizen_input_device_release(struct tizen_input_device *tizen_input_device)
{
	wl_proxy_marshal((struct wl_proxy *) tizen_input_device,
			 TIZEN_INPUT_DEVICE_RELEASE);

	wl_proxy_destroy((struct wl_proxy *) tizen_input_device);
}

#define TIZEN_LAUNCHSCREEN_CREATE_IMG	0

#define TIZEN_LAUNCHSCREEN_CREATE_IMG_SINCE_VERSION	1

static inline void
tizen_launchscreen_set_user_data(struct tizen_launchscreen *tizen_launchscreen, void *user_data)
{
	wl_proxy_set_user_data((struct wl_proxy *) tizen_launchscreen, user_data);
}

static inline void *
tizen_launchscreen_get_user_data(struct tizen_launchscreen *tizen_launchscreen)
{
	return wl_proxy_get_user_data((struct wl_proxy *) tizen_launchscreen);
}

static inline uint32_t
tizen_launchscreen_get_version(struct tizen_launchscreen *tizen_launchscreen)
{
	return wl_proxy_get_version((struct wl_proxy *) tizen_launchscreen);
}

static inline void
tizen_launchscreen_destroy(struct tizen_launchscreen *tizen_launchscreen)
{
	wl_proxy_destroy((struct wl_proxy *) tizen_launchscreen);
}

static inline struct tizen_launch_image *
tizen_launchscreen_create_img(struct tizen_launchscreen *tizen_launchscreen)
{
	struct wl_proxy *id;

	id = wl_proxy_marshal_constructor((struct wl_proxy *) tizen_launchscreen,
			 TIZEN_LAUNCHSCREEN_CREATE_IMG, &tizen_launch_image_interface, NULL);

	return (struct tizen_launch_image *) id;
}

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

#define TIZEN_LAUNCH_IMAGE_DESTROY	0
#define TIZEN_LAUNCH_IMAGE_LAUNCH	1
#define TIZEN_LAUNCH_IMAGE_OWNER	2
#define TIZEN_LAUNCH_IMAGE_SHOW	3
#define TIZEN_LAUNCH_IMAGE_HIDE	4

#define TIZEN_LAUNCH_IMAGE_DESTROY_SINCE_VERSION	1
#define TIZEN_LAUNCH_IMAGE_LAUNCH_SINCE_VERSION	1
#define TIZEN_LAUNCH_IMAGE_OWNER_SINCE_VERSION	1
#define TIZEN_LAUNCH_IMAGE_SHOW_SINCE_VERSION	1
#define TIZEN_LAUNCH_IMAGE_HIDE_SINCE_VERSION	1

static inline void
tizen_launch_image_set_user_data(struct tizen_launch_image *tizen_launch_image, void *user_data)
{
	wl_proxy_set_user_data((struct wl_proxy *) tizen_launch_image, user_data);
}

static inline void *
tizen_launch_image_get_user_data(struct tizen_launch_image *tizen_launch_image)
{
	return wl_proxy_get_user_data((struct wl_proxy *) tizen_launch_image);
}

static inline uint32_t
tizen_launch_image_get_version(struct tizen_launch_image *tizen_launch_image)
{
	return wl_proxy_get_version((struct wl_proxy *) tizen_launch_image);
}

static inline void
tizen_launch_image_destroy(struct tizen_launch_image *tizen_launch_image)
{
	wl_proxy_marshal((struct wl_proxy *) tizen_launch_image,
			 TIZEN_LAUNCH_IMAGE_DESTROY);

	wl_proxy_destroy((struct wl_proxy *) tizen_launch_image);
}

static inline void
tizen_launch_image_launch(struct tizen_launch_image *tizen_launch_image, const char *file, uint32_t file_type, uint32_t color_depth, uint32_t rotation, uint32_t indicator, struct wl_array *options)
{
	wl_proxy_marshal((struct wl_proxy *) tizen_launch_image,
			 TIZEN_LAUNCH_IMAGE_LAUNCH, file, file_type, color_depth, rotation, indicator, options);
}

static inline void
tizen_launch_image_owner(struct tizen_launch_image *tizen_launch_image, uint32_t pid)
{
	wl_proxy_marshal((struct wl_proxy *) tizen_launch_image,
			 TIZEN_LAUNCH_IMAGE_OWNER, pid);
}

static inline void
tizen_launch_image_show(struct tizen_launch_image *tizen_launch_image)
{
	wl_proxy_marshal((struct wl_proxy *) tizen_launch_image,
			 TIZEN_LAUNCH_IMAGE_SHOW);
}

static inline void
tizen_launch_image_hide(struct tizen_launch_image *tizen_launch_image)
{
	wl_proxy_marshal((struct wl_proxy *) tizen_launch_image,
			 TIZEN_LAUNCH_IMAGE_HIDE);
}

#ifndef TIZEN_EFFECT_TYPE_ENUM
#define TIZEN_EFFECT_TYPE_ENUM
enum tizen_effect_type {
	TIZEN_EFFECT_TYPE_NONE = 0,
	TIZEN_EFFECT_TYPE_SHOW = 1,
	TIZEN_EFFECT_TYPE_HIDE = 2,
	TIZEN_EFFECT_TYPE_RESTACK = 3,
};
#endif /* TIZEN_EFFECT_TYPE_ENUM */

struct tizen_effect_listener {
	/**
	 * start - (none)
	 * @surface: (none)
	 * @type: (none)
	 */
	void (*start)(void *data,
		      struct tizen_effect *tizen_effect,
		      struct wl_surface *surface,
		      uint32_t type);
	/**
	 * end - (none)
	 * @surface: (none)
	 * @type: (none)
	 */
	void (*end)(void *data,
		    struct tizen_effect *tizen_effect,
		    struct wl_surface *surface,
		    uint32_t type);
};

static inline int
tizen_effect_add_listener(struct tizen_effect *tizen_effect,
			  const struct tizen_effect_listener *listener, void *data)
{
	return wl_proxy_add_listener((struct wl_proxy *) tizen_effect,
				     (void (**)(void)) listener, data);
}

#define TIZEN_EFFECT_DESTROY	0

#define TIZEN_EFFECT_DESTROY_SINCE_VERSION	1

static inline void
tizen_effect_set_user_data(struct tizen_effect *tizen_effect, void *user_data)
{
	wl_proxy_set_user_data((struct wl_proxy *) tizen_effect, user_data);
}

static inline void *
tizen_effect_get_user_data(struct tizen_effect *tizen_effect)
{
	return wl_proxy_get_user_data((struct wl_proxy *) tizen_effect);
}

static inline uint32_t
tizen_effect_get_version(struct tizen_effect *tizen_effect)
{
	return wl_proxy_get_version((struct wl_proxy *) tizen_effect);
}

static inline void
tizen_effect_destroy(struct tizen_effect *tizen_effect)
{
	wl_proxy_marshal((struct wl_proxy *) tizen_effect,
			 TIZEN_EFFECT_DESTROY);

	wl_proxy_destroy((struct wl_proxy *) tizen_effect);
}

#ifndef TIZEN_DISPLAY_POLICY_ERROR_STATE_ENUM
#define TIZEN_DISPLAY_POLICY_ERROR_STATE_ENUM
enum tizen_display_policy_error_state {
	TIZEN_DISPLAY_POLICY_ERROR_STATE_NONE = 0,
	TIZEN_DISPLAY_POLICY_ERROR_STATE_PERMISSION_DENIED = 1,
};
#endif /* TIZEN_DISPLAY_POLICY_ERROR_STATE_ENUM */

struct tizen_display_policy_listener {
	/**
	 * window_brightness_done - (none)
	 * @surface: (none)
	 * @brightness: (none)
	 * @error_state: (none)
	 */
	void (*window_brightness_done)(void *data,
				       struct tizen_display_policy *tizen_display_policy,
				       struct wl_surface *surface,
				       int32_t brightness,
				       uint32_t error_state);
};

static inline int
tizen_display_policy_add_listener(struct tizen_display_policy *tizen_display_policy,
				  const struct tizen_display_policy_listener *listener, void *data)
{
	return wl_proxy_add_listener((struct wl_proxy *) tizen_display_policy,
				     (void (**)(void)) listener, data);
}

#define TIZEN_DISPLAY_POLICY_SET_WINDOW_BRIGHTNESS	0

#define TIZEN_DISPLAY_POLICY_SET_WINDOW_BRIGHTNESS_SINCE_VERSION	1

static inline void
tizen_display_policy_set_user_data(struct tizen_display_policy *tizen_display_policy, void *user_data)
{
	wl_proxy_set_user_data((struct wl_proxy *) tizen_display_policy, user_data);
}

static inline void *
tizen_display_policy_get_user_data(struct tizen_display_policy *tizen_display_policy)
{
	return wl_proxy_get_user_data((struct wl_proxy *) tizen_display_policy);
}

static inline uint32_t
tizen_display_policy_get_version(struct tizen_display_policy *tizen_display_policy)
{
	return wl_proxy_get_version((struct wl_proxy *) tizen_display_policy);
}

static inline void
tizen_display_policy_destroy(struct tizen_display_policy *tizen_display_policy)
{
	wl_proxy_destroy((struct wl_proxy *) tizen_display_policy);
}

static inline void
tizen_display_policy_set_window_brightness(struct tizen_display_policy *tizen_display_policy, struct wl_surface *surface, int32_t brightness)
{
	wl_proxy_marshal((struct wl_proxy *) tizen_display_policy,
			 TIZEN_DISPLAY_POLICY_SET_WINDOW_BRIGHTNESS, surface, brightness);
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

struct tizen_indicator_listener {
	/**
	 * flick - (none)
	 * @surface: occur the flick event
	 * @type: (none)
	 */
	void (*flick)(void *data,
		      struct tizen_indicator *tizen_indicator,
		      struct wl_surface *surface,
		      int32_t type);
};

static inline int
tizen_indicator_add_listener(struct tizen_indicator *tizen_indicator,
			     const struct tizen_indicator_listener *listener, void *data)
{
	return wl_proxy_add_listener((struct wl_proxy *) tizen_indicator,
				     (void (**)(void)) listener, data);
}

#define TIZEN_INDICATOR_DESTROY	0
#define TIZEN_INDICATOR_SET_STATE	1
#define TIZEN_INDICATOR_SET_OPACITY_MODE	2
#define TIZEN_INDICATOR_SET_VISIBLE_TYPE	3

#define TIZEN_INDICATOR_DESTROY_SINCE_VERSION	1
#define TIZEN_INDICATOR_SET_STATE_SINCE_VERSION	1
#define TIZEN_INDICATOR_SET_OPACITY_MODE_SINCE_VERSION	1
#define TIZEN_INDICATOR_SET_VISIBLE_TYPE_SINCE_VERSION	1

static inline void
tizen_indicator_set_user_data(struct tizen_indicator *tizen_indicator, void *user_data)
{
	wl_proxy_set_user_data((struct wl_proxy *) tizen_indicator, user_data);
}

static inline void *
tizen_indicator_get_user_data(struct tizen_indicator *tizen_indicator)
{
	return wl_proxy_get_user_data((struct wl_proxy *) tizen_indicator);
}

static inline uint32_t
tizen_indicator_get_version(struct tizen_indicator *tizen_indicator)
{
	return wl_proxy_get_version((struct wl_proxy *) tizen_indicator);
}

static inline void
tizen_indicator_destroy(struct tizen_indicator *tizen_indicator)
{
	wl_proxy_marshal((struct wl_proxy *) tizen_indicator,
			 TIZEN_INDICATOR_DESTROY);

	wl_proxy_destroy((struct wl_proxy *) tizen_indicator);
}

static inline void
tizen_indicator_set_state(struct tizen_indicator *tizen_indicator, struct wl_surface *surface, int32_t state)
{
	wl_proxy_marshal((struct wl_proxy *) tizen_indicator,
			 TIZEN_INDICATOR_SET_STATE, surface, state);
}

static inline void
tizen_indicator_set_opacity_mode(struct tizen_indicator *tizen_indicator, struct wl_surface *surface, int32_t mode)
{
	wl_proxy_marshal((struct wl_proxy *) tizen_indicator,
			 TIZEN_INDICATOR_SET_OPACITY_MODE, surface, mode);
}

static inline void
tizen_indicator_set_visible_type(struct tizen_indicator *tizen_indicator, struct wl_surface *surface, int32_t type)
{
	wl_proxy_marshal((struct wl_proxy *) tizen_indicator,
			 TIZEN_INDICATOR_SET_VISIBLE_TYPE, surface, type);
}

/**
 * tizen_clipboard - an interface for requests and event about clipboard
 * @data_selected: announce data are selected by clipboard
 *
 * This interface provides some requests and events about clipboard for
 * other clients.
 */
struct tizen_clipboard_listener {
	/**
	 * data_selected - announce data are selected by clipboard
	 * @surface: surface object
	 *
	 * 
	 */
	void (*data_selected)(void *data,
			      struct tizen_clipboard *tizen_clipboard,
			      struct wl_surface *surface);
};

static inline int
tizen_clipboard_add_listener(struct tizen_clipboard *tizen_clipboard,
			     const struct tizen_clipboard_listener *listener, void *data)
{
	return wl_proxy_add_listener((struct wl_proxy *) tizen_clipboard,
				     (void (**)(void)) listener, data);
}

#define TIZEN_CLIPBOARD_DESTROY	0
#define TIZEN_CLIPBOARD_SHOW	1
#define TIZEN_CLIPBOARD_HIDE	2

#define TIZEN_CLIPBOARD_DESTROY_SINCE_VERSION	1
#define TIZEN_CLIPBOARD_SHOW_SINCE_VERSION	1
#define TIZEN_CLIPBOARD_HIDE_SINCE_VERSION	1

static inline void
tizen_clipboard_set_user_data(struct tizen_clipboard *tizen_clipboard, void *user_data)
{
	wl_proxy_set_user_data((struct wl_proxy *) tizen_clipboard, user_data);
}

static inline void *
tizen_clipboard_get_user_data(struct tizen_clipboard *tizen_clipboard)
{
	return wl_proxy_get_user_data((struct wl_proxy *) tizen_clipboard);
}

static inline uint32_t
tizen_clipboard_get_version(struct tizen_clipboard *tizen_clipboard)
{
	return wl_proxy_get_version((struct wl_proxy *) tizen_clipboard);
}

static inline void
tizen_clipboard_destroy(struct tizen_clipboard *tizen_clipboard)
{
	wl_proxy_marshal((struct wl_proxy *) tizen_clipboard,
			 TIZEN_CLIPBOARD_DESTROY);

	wl_proxy_destroy((struct wl_proxy *) tizen_clipboard);
}

static inline void
tizen_clipboard_show(struct tizen_clipboard *tizen_clipboard, struct wl_surface *surface)
{
	wl_proxy_marshal((struct wl_proxy *) tizen_clipboard,
			 TIZEN_CLIPBOARD_SHOW, surface);
}

static inline void
tizen_clipboard_hide(struct tizen_clipboard *tizen_clipboard, struct wl_surface *surface)
{
	wl_proxy_marshal((struct wl_proxy *) tizen_clipboard,
			 TIZEN_CLIPBOARD_HIDE, surface);
}

#ifdef  __cplusplus
}
#endif

#endif
