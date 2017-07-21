#ifndef FULLSCREEN_SHELL_CLIENT_PROTOCOL_H
#define FULLSCREEN_SHELL_CLIENT_PROTOCOL_H

#ifdef  __cplusplus
extern "C" {
#endif

#include <stdint.h>
#include <stddef.h>
#include "wayland-client.h"

struct wl_client;
struct wl_resource;

struct _wl_fullscreen_shell;
struct _wl_fullscreen_shell_mode_feedback;
struct wl_output;
struct wl_surface;

extern const struct wl_interface _wl_fullscreen_shell_interface;
extern const struct wl_interface _wl_fullscreen_shell_mode_feedback_interface;

#ifndef _WL_FULLSCREEN_SHELL_CAPABILITY_ENUM
#define _WL_FULLSCREEN_SHELL_CAPABILITY_ENUM
/**
 * _wl_fullscreen_shell_capability - capabilities advertised by the
 *	compositor
 * @_WL_FULLSCREEN_SHELL_CAPABILITY_ARBITRARY_MODES: compositor is
 *	capable of almost any output mode
 * @_WL_FULLSCREEN_SHELL_CAPABILITY_CURSOR_PLANE: compositor has a
 *	seperate cursor plane
 *
 * Various capabilities that can be advertised by the compositor. They
 * are advertised one-at-a-time when the wl_fullscreen_shell interface is
 * bound. See the wl_fullscreen_shell.capability event for more details.
 *
 * ARBITRARY_MODE: This is a hint to the client that indicates that the
 * compositor is capable of setting practically any mode on its outputs. If
 * this capability is provided,
 * wl_fullscreen_shell.present_surface_for_mode will almost never fail and
 * clients should feel free to set whatever mode they like. If the
 * compositor does not advertise this, it may still support some modes that
 * are not advertised through wl_global.mode but it is less likely.
 *
 * CURSOR_PLANE: This is a hint to the client that indicates that the
 * compositor can handle a cursor surface from the client without actually
 * compositing. This may be because of a hardware cursor plane or some
 * other mechanism. If the compositor does not advertise this capability
 * then setting wl_pointer.cursor may degrade performance or be ignored
 * entirely. If CURSOR_PLANE is not advertised, it is recommended that the
 * client draw its own cursor and set wl_pointer.cursor(NULL).
 */
enum _wl_fullscreen_shell_capability {
	_WL_FULLSCREEN_SHELL_CAPABILITY_ARBITRARY_MODES = 1,
	_WL_FULLSCREEN_SHELL_CAPABILITY_CURSOR_PLANE = 2,
};
#endif /* _WL_FULLSCREEN_SHELL_CAPABILITY_ENUM */

#ifndef _WL_FULLSCREEN_SHELL_PRESENT_METHOD_ENUM
#define _WL_FULLSCREEN_SHELL_PRESENT_METHOD_ENUM
/**
 * _wl_fullscreen_shell_present_method - different method to set the
 *	surface fullscreen
 * @_WL_FULLSCREEN_SHELL_PRESENT_METHOD_DEFAULT: no preference, apply
 *	default policy
 * @_WL_FULLSCREEN_SHELL_PRESENT_METHOD_CENTER: center the surface on the
 *	output
 * @_WL_FULLSCREEN_SHELL_PRESENT_METHOD_ZOOM: scale the surface,
 *	preserving aspect ratio, to the largest size that will fit on the output
 * @_WL_FULLSCREEN_SHELL_PRESENT_METHOD_ZOOM_CROP: scale the surface,
 *	preserving aspect ratio, to fully fill the output cropping if needed
 * @_WL_FULLSCREEN_SHELL_PRESENT_METHOD_STRETCH: scale the surface to the
 *	size of the output ignoring aspect ratio
 *
 * Hints to indicate to the compositor how to deal with a conflict
 * between the dimensions of the surface and the dimensions of the output.
 * The compositor is free to ignore this parameter.
 */
enum _wl_fullscreen_shell_present_method {
	_WL_FULLSCREEN_SHELL_PRESENT_METHOD_DEFAULT = 0,
	_WL_FULLSCREEN_SHELL_PRESENT_METHOD_CENTER = 1,
	_WL_FULLSCREEN_SHELL_PRESENT_METHOD_ZOOM = 2,
	_WL_FULLSCREEN_SHELL_PRESENT_METHOD_ZOOM_CROP = 3,
	_WL_FULLSCREEN_SHELL_PRESENT_METHOD_STRETCH = 4,
};
#endif /* _WL_FULLSCREEN_SHELL_PRESENT_METHOD_ENUM */

#ifndef _WL_FULLSCREEN_SHELL_ERROR_ENUM
#define _WL_FULLSCREEN_SHELL_ERROR_ENUM
/**
 * _wl_fullscreen_shell_error - wl_fullscreen_shell error values
 * @_WL_FULLSCREEN_SHELL_ERROR_INVALID_METHOD: present_method is not
 *	known
 *
 * These errors can be emitted in response to wl_fullscreen_shell
 * requests
 */
enum _wl_fullscreen_shell_error {
	_WL_FULLSCREEN_SHELL_ERROR_INVALID_METHOD = 0,
};
#endif /* _WL_FULLSCREEN_SHELL_ERROR_ENUM */

/**
 * _wl_fullscreen_shell - Displays a single surface per output
 * @capability: advertises a capability of the compositor
 *
 * Displays a single surface per output.
 *
 * This interface provides a mechanism for a single client to display
 * simple full-screen surfaces. While there technically may be multiple
 * clients bound to this interface, only one of those clients should be
 * shown at a time.
 *
 * To present a surface, the client uses either the present_surface or
 * present_surface_for_mode requests. Presenting a surface takes effect on
 * the next wl_surface.commit. See the individual requests for details
 * about scaling and mode switches.
 *
 * The client can have at most one surface per output at any time.
 * Requesting a surface be presented on an output that already has a
 * surface replaces the previously presented surface. Presenting a null
 * surface removes its content and effectively disables the output. Exactly
 * what happens when an output is "disabled" is compositor-specific. The
 * same surface may be presented on multiple outputs simultaneously.
 *
 * Once a surface is presented on an output, it stays on that output until
 * either the client removes it or the compositor destroys the output. This
 * way, the client can update the output's contents by simply attaching a
 * new buffer.
 */
struct _wl_fullscreen_shell_listener {
	/**
	 * capability - advertises a capability of the compositor
	 * @capabilty: (none)
	 *
	 * Advertises a single capability of the compositor.
	 *
	 * When the wl_fullscreen_shell interface is bound, this event is
	 * emitted once for each capability advertised. Valid capabilities
	 * are given by the wl_fullscreen_shell.capability enum. If clients
	 * want to take advantage of any of these capabilities, they should
	 * use a wl_display.sync request immediately after binding to
	 * ensure that they receive all the capability events.
	 */
	void (*capability)(void *data,
			   struct _wl_fullscreen_shell *_wl_fullscreen_shell,
			   uint32_t capabilty);
};

static inline int
_wl_fullscreen_shell_add_listener(struct _wl_fullscreen_shell *_wl_fullscreen_shell,
				  const struct _wl_fullscreen_shell_listener *listener, void *data)
{
	return wl_proxy_add_listener((struct wl_proxy *) _wl_fullscreen_shell,
				     (void (**)(void)) listener, data);
}

#define _WL_FULLSCREEN_SHELL_RELEASE	0
#define _WL_FULLSCREEN_SHELL_PRESENT_SURFACE	1
#define _WL_FULLSCREEN_SHELL_PRESENT_SURFACE_FOR_MODE	2

#define _WL_FULLSCREEN_SHELL_RELEASE_SINCE_VERSION	1
#define _WL_FULLSCREEN_SHELL_PRESENT_SURFACE_SINCE_VERSION	1
#define _WL_FULLSCREEN_SHELL_PRESENT_SURFACE_FOR_MODE_SINCE_VERSION	1

static inline void
_wl_fullscreen_shell_set_user_data(struct _wl_fullscreen_shell *_wl_fullscreen_shell, void *user_data)
{
	wl_proxy_set_user_data((struct wl_proxy *) _wl_fullscreen_shell, user_data);
}

static inline void *
_wl_fullscreen_shell_get_user_data(struct _wl_fullscreen_shell *_wl_fullscreen_shell)
{
	return wl_proxy_get_user_data((struct wl_proxy *) _wl_fullscreen_shell);
}

static inline uint32_t
_wl_fullscreen_shell_get_version(struct _wl_fullscreen_shell *_wl_fullscreen_shell)
{
	return wl_proxy_get_version((struct wl_proxy *) _wl_fullscreen_shell);
}

static inline void
_wl_fullscreen_shell_destroy(struct _wl_fullscreen_shell *_wl_fullscreen_shell)
{
	wl_proxy_destroy((struct wl_proxy *) _wl_fullscreen_shell);
}

static inline void
_wl_fullscreen_shell_release(struct _wl_fullscreen_shell *_wl_fullscreen_shell)
{
	wl_proxy_marshal((struct wl_proxy *) _wl_fullscreen_shell,
			 _WL_FULLSCREEN_SHELL_RELEASE);

	wl_proxy_destroy((struct wl_proxy *) _wl_fullscreen_shell);
}

static inline void
_wl_fullscreen_shell_present_surface(struct _wl_fullscreen_shell *_wl_fullscreen_shell, struct wl_surface *surface, uint32_t method, struct wl_output *output)
{
	wl_proxy_marshal((struct wl_proxy *) _wl_fullscreen_shell,
			 _WL_FULLSCREEN_SHELL_PRESENT_SURFACE, surface, method, output);
}

static inline struct _wl_fullscreen_shell_mode_feedback *
_wl_fullscreen_shell_present_surface_for_mode(struct _wl_fullscreen_shell *_wl_fullscreen_shell, struct wl_surface *surface, struct wl_output *output, int32_t framerate)
{
	struct wl_proxy *feedback;

	feedback = wl_proxy_marshal_constructor((struct wl_proxy *) _wl_fullscreen_shell,
			 _WL_FULLSCREEN_SHELL_PRESENT_SURFACE_FOR_MODE, &_wl_fullscreen_shell_mode_feedback_interface, surface, output, framerate, NULL);

	return (struct _wl_fullscreen_shell_mode_feedback *) feedback;
}

struct _wl_fullscreen_shell_mode_feedback_listener {
	/**
	 * mode_successful - mode switch succeeded
	 *
	 * This event indicates that the attempted mode switch operation
	 * was successful. A surface of the size requested in the mode
	 * switch will fill the output without scaling.
	 *
	 * Upon receiving this event, the client should destroy the
	 * wl_fullscreen_shell_mode_feedback object.
	 */
	void (*mode_successful)(void *data,
				struct _wl_fullscreen_shell_mode_feedback *_wl_fullscreen_shell_mode_feedback);
	/**
	 * mode_failed - mode switch failed
	 *
	 * This event indicates that the attempted mode switch operation
	 * failed. This may be because the requested output mode is not
	 * possible or it may mean that the compositor does not want to
	 * allow it.
	 *
	 * Upon receiving this event, the client should destroy the
	 * wl_fullscreen_shell_mode_feedback object.
	 */
	void (*mode_failed)(void *data,
			    struct _wl_fullscreen_shell_mode_feedback *_wl_fullscreen_shell_mode_feedback);
	/**
	 * present_cancelled - mode switch cancelled
	 *
	 * This event indicates that the attempted mode switch operation
	 * was cancelled. Most likely this is because the client requested
	 * a second mode switch before the first one completed.
	 *
	 * Upon receiving this event, the client should destroy the
	 * wl_fullscreen_shell_mode_feedback object.
	 */
	void (*present_cancelled)(void *data,
				  struct _wl_fullscreen_shell_mode_feedback *_wl_fullscreen_shell_mode_feedback);
};

static inline int
_wl_fullscreen_shell_mode_feedback_add_listener(struct _wl_fullscreen_shell_mode_feedback *_wl_fullscreen_shell_mode_feedback,
						const struct _wl_fullscreen_shell_mode_feedback_listener *listener, void *data)
{
	return wl_proxy_add_listener((struct wl_proxy *) _wl_fullscreen_shell_mode_feedback,
				     (void (**)(void)) listener, data);
}


static inline void
_wl_fullscreen_shell_mode_feedback_set_user_data(struct _wl_fullscreen_shell_mode_feedback *_wl_fullscreen_shell_mode_feedback, void *user_data)
{
	wl_proxy_set_user_data((struct wl_proxy *) _wl_fullscreen_shell_mode_feedback, user_data);
}

static inline void *
_wl_fullscreen_shell_mode_feedback_get_user_data(struct _wl_fullscreen_shell_mode_feedback *_wl_fullscreen_shell_mode_feedback)
{
	return wl_proxy_get_user_data((struct wl_proxy *) _wl_fullscreen_shell_mode_feedback);
}

static inline uint32_t
_wl_fullscreen_shell_mode_feedback_get_version(struct _wl_fullscreen_shell_mode_feedback *_wl_fullscreen_shell_mode_feedback)
{
	return wl_proxy_get_version((struct wl_proxy *) _wl_fullscreen_shell_mode_feedback);
}

static inline void
_wl_fullscreen_shell_mode_feedback_destroy(struct _wl_fullscreen_shell_mode_feedback *_wl_fullscreen_shell_mode_feedback)
{
	wl_proxy_destroy((struct wl_proxy *) _wl_fullscreen_shell_mode_feedback);
}

#ifdef  __cplusplus
}
#endif

#endif
