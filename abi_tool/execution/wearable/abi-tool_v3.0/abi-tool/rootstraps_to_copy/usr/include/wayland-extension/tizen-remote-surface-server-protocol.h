#ifndef TIZEN_REMOTE_SURFACE_SERVER_PROTOCOL_H
#define TIZEN_REMOTE_SURFACE_SERVER_PROTOCOL_H

#ifdef  __cplusplus
extern "C" {
#endif

#include <stdint.h>
#include <stddef.h>
#include "wayland-server.h"

struct wl_client;
struct wl_resource;

struct tizen_remote_surface;
struct tizen_remote_surface_manager;
struct tizen_remote_surface_provider;
struct tizen_remote_surface_region;
struct wl_buffer;
struct wl_surface;
struct wl_tbm;

extern const struct wl_interface tizen_remote_surface_manager_interface;
extern const struct wl_interface tizen_remote_surface_provider_interface;
extern const struct wl_interface tizen_remote_surface_interface;
extern const struct wl_interface tizen_remote_surface_region_interface;

/**
 * tizen_remote_surface_manager - manager of tizen_remote_surface
 * @create_provider: create new remote surface provider
 * @create_surface: create new remote surface
 * @bind_surface: bind a remote surface and a wl_surface each other
 *
 * A manager of tizen_remote_surface. This object is in charge of
 * creating tizen_remote_surface_provider and tizen_remote_surface and
 * provide additional operations for those objects.
 */
struct tizen_remote_surface_manager_interface {
	/**
	 * create_provider - create new remote surface provider
	 * @id: new remote surface provider id
	 * @surface: wayland surface to be handled as provider
	 *
	 * Ask manager creation of a new remote surface provider. A
	 * wl_surface is required for making remote surface provider.
	 */
	void (*create_provider)(struct wl_client *client,
				struct wl_resource *resource,
				uint32_t id,
				struct wl_resource *surface);
	/**
	 * create_surface - create new remote surface
	 * @id: new remote surface id
	 * @resource_id: provider's resource id
	 * @tbm: wl_tbm object used to get tbm_surface
	 *
	 * Ask manager creation of a new remote surface. resource_id is
	 * required to identify this remote surface is what provider of and
	 * wl_tbm object is used for remote buffer of this remote surface.
	 */
	void (*create_surface)(struct wl_client *client,
			       struct wl_resource *resource,
			       uint32_t id,
			       uint32_t resource_id,
			       struct wl_resource *tbm);
	/**
	 * bind_surface - bind a remote surface and a wl_surface each
	 *	other
	 * @surface: wayland surface to be set remote surface
	 * @remote_surface: remote surface for target wayland surface
	 *
	 * Combining a remote surface object into a wl_surface object. If
	 * a remote surface and a wayland surface are bound each other,
	 * wayland surface can be automatically drawed by buffer updating
	 * of remote surface provider without notice to an owner of remote
	 * surface.
	 */
	void (*bind_surface)(struct wl_client *client,
			     struct wl_resource *resource,
			     struct wl_resource *surface,
			     struct wl_resource *remote_surface);
};


#ifndef TIZEN_REMOTE_SURFACE_PROVIDER_VISIBILITY_TYPE_ENUM
#define TIZEN_REMOTE_SURFACE_PROVIDER_VISIBILITY_TYPE_ENUM
enum tizen_remote_surface_provider_visibility_type {
	TIZEN_REMOTE_SURFACE_PROVIDER_VISIBILITY_TYPE_VISIBLE = 0,
	TIZEN_REMOTE_SURFACE_PROVIDER_VISIBILITY_TYPE_INVISIBLE = 1,
};
#endif /* TIZEN_REMOTE_SURFACE_PROVIDER_VISIBILITY_TYPE_ENUM */

/**
 * tizen_remote_surface_provider - a provider of remote buffer source
 * @destroy: (none)
 * @offscreen_set: request offscreen rendering
 *
 * A provider client. Surfaces of providers are offscreen. An attached
 * buffer of the provider is used for creating remote buffer and the remote
 * buffer is delivered to remote surfaces.
 */
struct tizen_remote_surface_provider_interface {
	/**
	 * destroy - (none)
	 */
	void (*destroy)(struct wl_client *client,
			struct wl_resource *resource);
	/**
	 * offscreen_set - request offscreen rendering
	 * @set: zero value means unset and non-zero value means set of
	 *	offscreen
	 *
	 * Requests for setting or unsetting offscreen. The default is
	 * offscreen if provider client never requests this.
	 */
	void (*offscreen_set)(struct wl_client *client,
			      struct wl_resource *resource,
			      uint32_t set);
};

#define TIZEN_REMOTE_SURFACE_PROVIDER_RESOURCE_ID	0
#define TIZEN_REMOTE_SURFACE_PROVIDER_VISIBILITY	1

#define TIZEN_REMOTE_SURFACE_PROVIDER_RESOURCE_ID_SINCE_VERSION	1
#define TIZEN_REMOTE_SURFACE_PROVIDER_VISIBILITY_SINCE_VERSION	1

static inline void
tizen_remote_surface_provider_send_resource_id(struct wl_resource *resource_, uint32_t resource_id)
{
	wl_resource_post_event(resource_, TIZEN_REMOTE_SURFACE_PROVIDER_RESOURCE_ID, resource_id);
}

static inline void
tizen_remote_surface_provider_send_visibility(struct wl_resource *resource_, uint32_t visibility)
{
	wl_resource_post_event(resource_, TIZEN_REMOTE_SURFACE_PROVIDER_VISIBILITY, visibility);
}

#ifndef TIZEN_REMOTE_SURFACE_EVENT_TYPE_ENUM
#define TIZEN_REMOTE_SURFACE_EVENT_TYPE_ENUM
enum tizen_remote_surface_event_type {
	TIZEN_REMOTE_SURFACE_EVENT_TYPE_NONE = 0,
	TIZEN_REMOTE_SURFACE_EVENT_TYPE_MOUSE_DOWN = 1,
	TIZEN_REMOTE_SURFACE_EVENT_TYPE_MOUSE_UP = 2,
	TIZEN_REMOTE_SURFACE_EVENT_TYPE_MOUSE_MOVE = 3,
	TIZEN_REMOTE_SURFACE_EVENT_TYPE_TOUCH_DOWN = 4,
	TIZEN_REMOTE_SURFACE_EVENT_TYPE_TOUCH_UP = 5,
	TIZEN_REMOTE_SURFACE_EVENT_TYPE_TOUCH_MOVE = 6,
	TIZEN_REMOTE_SURFACE_EVENT_TYPE_KEY_DOWN = 7,
	TIZEN_REMOTE_SURFACE_EVENT_TYPE_KEY_UP = 8,
};
#endif /* TIZEN_REMOTE_SURFACE_EVENT_TYPE_ENUM */

#ifndef TIZEN_REMOTE_SURFACE_VISIBILITY_TYPE_ENUM
#define TIZEN_REMOTE_SURFACE_VISIBILITY_TYPE_ENUM
enum tizen_remote_surface_visibility_type {
	TIZEN_REMOTE_SURFACE_VISIBILITY_TYPE_VISIBLE = 0,
	TIZEN_REMOTE_SURFACE_VISIBILITY_TYPE_INVISIBLE = 1,
};
#endif /* TIZEN_REMOTE_SURFACE_VISIBILITY_TYPE_ENUM */

#ifndef TIZEN_REMOTE_SURFACE_BUFFER_TYPE_ENUM
#define TIZEN_REMOTE_SURFACE_BUFFER_TYPE_ENUM
/**
 * tizen_remote_surface_buffer_type - type of remote surface buffer
 * @TIZEN_REMOTE_SURFACE_BUFFER_TYPE_TBM: tbm type
 * @TIZEN_REMOTE_SURFACE_BUFFER_TYPE_IMAGE_FILE: image file type
 *
 * 
 */
enum tizen_remote_surface_buffer_type {
	TIZEN_REMOTE_SURFACE_BUFFER_TYPE_TBM = 0,
	TIZEN_REMOTE_SURFACE_BUFFER_TYPE_IMAGE_FILE = 1,
};
#endif /* TIZEN_REMOTE_SURFACE_BUFFER_TYPE_ENUM */

/**
 * tizen_remote_surface - a consumer of buffers from a provider
 * @destroy: (none)
 * @redirect: request redirect of provider's buffer
 * @unredirect: request for stop redirect of provider's buffer
 * @transfer_mouse_event: notify of mouse up/down/move event on remote
 *	surface
 * @transfer_mouse_wheel: notify of mouse wheel event on remote surface
 * @transfer_touch_event: notify of touch up/down/move event on remote
 *	surface
 * @transfer_touch_cancel: notify of touch cancel
 * @transfer_key_event: notify of key down/up event
 * @transfer_visibility: notify of visibility change of remote surface
 * @set_owner: set owner surface of remote sruface
 * @create_region: create new region
 * @release: request release of wayland buffer
 *
 * A consumer client of provider client's buffer. The consumer can
 * receive notice of buffer updating of its provider and use the received
 * buffer for drawing its own buffer. The consumer also can request for
 * transfering input events into its provider.
 */
struct tizen_remote_surface_interface {
	/**
	 * destroy - (none)
	 */
	void (*destroy)(struct wl_client *client,
			struct wl_resource *resource);
	/**
	 * redirect - request redirect of provider's buffer
	 *
	 * Request for redirect of provider's buffer. A consumer is able
	 * to receive buffer_update after this request.
	 */
	void (*redirect)(struct wl_client *client,
			 struct wl_resource *resource);
	/**
	 * unredirect - request for stop redirect of provider's buffer
	 *
	 * 
	 */
	void (*unredirect)(struct wl_client *client,
			   struct wl_resource *resource);
	/**
	 * transfer_mouse_event - notify of mouse up/down/move event on
	 *	remote surface
	 * @event_type: event type
	 * @device: device type
	 * @button: button id
	 * @x: x coordinate
	 * @y: y coordinate
	 * @radius_x: minor axis of touch point
	 * @radius_y: major axis of touch point
	 * @pressure: pressure of touch point
	 * @angle: angle of touch point
	 * @clas: class of event generator(device)
	 * @subclas: subclass of event generator(device)
	 * @identifier: description of event generator(device)
	 * @time: timestamp
	 *
	 * 
	 */
	void (*transfer_mouse_event)(struct wl_client *client,
				     struct wl_resource *resource,
				     uint32_t event_type,
				     int32_t device,
				     int32_t button,
				     int32_t x,
				     int32_t y,
				     wl_fixed_t radius_x,
				     wl_fixed_t radius_y,
				     wl_fixed_t pressure,
				     wl_fixed_t angle,
				     uint32_t clas,
				     uint32_t subclas,
				     const char *identifier,
				     uint32_t time);
	/**
	 * transfer_mouse_wheel - notify of mouse wheel event on remote
	 *	surface
	 * @direction: wheel direction
	 * @z: z coordinate
	 * @clas: class of event generator(device)
	 * @subclas: subclass of event generator(device)
	 * @identifier: description of event generator(device)
	 * @time: timestamp
	 *
	 * 
	 */
	void (*transfer_mouse_wheel)(struct wl_client *client,
				     struct wl_resource *resource,
				     uint32_t direction,
				     int32_t z,
				     uint32_t clas,
				     uint32_t subclas,
				     const char *identifier,
				     uint32_t time);
	/**
	 * transfer_touch_event - notify of touch up/down/move event on
	 *	remote surface
	 * @event_type: event type
	 * @device: device type
	 * @button: button id
	 * @x: x coordinate
	 * @y: y coordinate
	 * @radius_x: minor axis of touch point
	 * @radius_y: major axis of touch point
	 * @pressure: pressure of touch point
	 * @angle: angle of touch point
	 * @clas: class of event generator(device)
	 * @subclas: subclass of event generator(device)
	 * @identifier: description of event generator(device)
	 * @time: timestamp
	 *
	 * 
	 */
	void (*transfer_touch_event)(struct wl_client *client,
				     struct wl_resource *resource,
				     uint32_t event_type,
				     int32_t device,
				     int32_t button,
				     int32_t x,
				     int32_t y,
				     wl_fixed_t radius_x,
				     wl_fixed_t radius_y,
				     wl_fixed_t pressure,
				     wl_fixed_t angle,
				     uint32_t clas,
				     uint32_t subclas,
				     const char *identifier,
				     uint32_t time);
	/**
	 * transfer_touch_cancel - notify of touch cancel
	 *
	 * 
	 */
	void (*transfer_touch_cancel)(struct wl_client *client,
				      struct wl_resource *resource);
	/**
	 * transfer_key_event - notify of key down/up event
	 * @event_type: event type
	 * @keycode: keycode
	 * @clas: class of event generator(device)
	 * @subclas: subclass of event generator(device)
	 * @identifier: description of event generator(device)
	 * @time: timestamp
	 *
	 * 
	 */
	void (*transfer_key_event)(struct wl_client *client,
				   struct wl_resource *resource,
				   uint32_t event_type,
				   int32_t keycode,
				   uint32_t clas,
				   uint32_t subclas,
				   const char *identifier,
				   uint32_t time);
	/**
	 * transfer_visibility - notify of visibility change of remote
	 *	surface
	 * @visibility: changed visibility type
	 *
	 * 
	 */
	void (*transfer_visibility)(struct wl_client *client,
				    struct wl_resource *resource,
				    uint32_t visibility);
	/**
	 * set_owner - set owner surface of remote sruface
	 * @owner: wayland surface to be set to an owner
	 *
	 * Set owner wl_surface object of this remote surface.
	 */
	void (*set_owner)(struct wl_client *client,
			  struct wl_resource *resource,
			  struct wl_resource *owner);
	/**
	 * create_region - create new region
	 * @id: (none)
	 *
	 * Ask tizen_remote_surface for creation of new remote surface
	 * region object. tizen_remote_surface_region object can be used
	 * for representing region of tizen_remote_surface.
	 */
	void (*create_region)(struct wl_client *client,
			      struct wl_resource *resource,
			      uint32_t id);
	/**
	 * release - request release of wayland buffer
	 * @buffer: wayland buffer to be released
	 *
	 * Notify of end of using the wayland buffer.
	 * tizen_remote_surface client SHOULD request this after all work
	 * using wayland buffer is done so that provider of the wl_buffer
	 * can re-use the buffer.
	 * @since: 2
	 */
	void (*release)(struct wl_client *client,
			struct wl_resource *resource,
			struct wl_resource *buffer);
};

#define TIZEN_REMOTE_SURFACE_UPDATE_BUFFER	0
#define TIZEN_REMOTE_SURFACE_MISSING	1
#define TIZEN_REMOTE_SURFACE_CHANGED_BUFFER	2

#define TIZEN_REMOTE_SURFACE_UPDATE_BUFFER_SINCE_VERSION	1
#define TIZEN_REMOTE_SURFACE_MISSING_SINCE_VERSION	1
#define TIZEN_REMOTE_SURFACE_CHANGED_BUFFER_SINCE_VERSION	3

static inline void
tizen_remote_surface_send_update_buffer(struct wl_resource *resource_, struct wl_resource *buffer, uint32_t time)
{
	wl_resource_post_event(resource_, TIZEN_REMOTE_SURFACE_UPDATE_BUFFER, buffer, time);
}

static inline void
tizen_remote_surface_send_missing(struct wl_resource *resource_)
{
	wl_resource_post_event(resource_, TIZEN_REMOTE_SURFACE_MISSING);
}

static inline void
tizen_remote_surface_send_changed_buffer(struct wl_resource *resource_, uint32_t type, struct wl_resource *tbm, int32_t img_file_fd, uint32_t img_file_size, uint32_t time, struct wl_array *options)
{
	wl_resource_post_event(resource_, TIZEN_REMOTE_SURFACE_CHANGED_BUFFER, type, tbm, img_file_fd, img_file_size, time, options);
}

/**
 * tizen_remote_surface_region - a region object
 * @destroy: (none)
 * @set_geometry: set geometry of this region
 *
 * A region object having geometry information and etc.
 */
struct tizen_remote_surface_region_interface {
	/**
	 * destroy - (none)
	 */
	void (*destroy)(struct wl_client *client,
			struct wl_resource *resource);
	/**
	 * set_geometry - set geometry of this region
	 * @x: x coordinate
	 * @y: y coordinate
	 * @w: width
	 * @h: height
	 *
	 * Set geometry information of this region object.
	 */
	void (*set_geometry)(struct wl_client *client,
			     struct wl_resource *resource,
			     int32_t x,
			     int32_t y,
			     int32_t w,
			     int32_t h);
};


#ifdef  __cplusplus
}
#endif

#endif
