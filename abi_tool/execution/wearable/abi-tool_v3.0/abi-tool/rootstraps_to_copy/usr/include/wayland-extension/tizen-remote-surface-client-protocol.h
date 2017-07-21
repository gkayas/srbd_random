#ifndef TIZEN_REMOTE_SURFACE_CLIENT_PROTOCOL_H
#define TIZEN_REMOTE_SURFACE_CLIENT_PROTOCOL_H

#ifdef  __cplusplus
extern "C" {
#endif

#include <stdint.h>
#include <stddef.h>
#include "wayland-client.h"

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

#define TIZEN_REMOTE_SURFACE_MANAGER_CREATE_PROVIDER	0
#define TIZEN_REMOTE_SURFACE_MANAGER_CREATE_SURFACE	1
#define TIZEN_REMOTE_SURFACE_MANAGER_BIND_SURFACE	2

#define TIZEN_REMOTE_SURFACE_MANAGER_CREATE_PROVIDER_SINCE_VERSION	1
#define TIZEN_REMOTE_SURFACE_MANAGER_CREATE_SURFACE_SINCE_VERSION	1
#define TIZEN_REMOTE_SURFACE_MANAGER_BIND_SURFACE_SINCE_VERSION	1

static inline void
tizen_remote_surface_manager_set_user_data(struct tizen_remote_surface_manager *tizen_remote_surface_manager, void *user_data)
{
	wl_proxy_set_user_data((struct wl_proxy *) tizen_remote_surface_manager, user_data);
}

static inline void *
tizen_remote_surface_manager_get_user_data(struct tizen_remote_surface_manager *tizen_remote_surface_manager)
{
	return wl_proxy_get_user_data((struct wl_proxy *) tizen_remote_surface_manager);
}

static inline uint32_t
tizen_remote_surface_manager_get_version(struct tizen_remote_surface_manager *tizen_remote_surface_manager)
{
	return wl_proxy_get_version((struct wl_proxy *) tizen_remote_surface_manager);
}

static inline void
tizen_remote_surface_manager_destroy(struct tizen_remote_surface_manager *tizen_remote_surface_manager)
{
	wl_proxy_destroy((struct wl_proxy *) tizen_remote_surface_manager);
}

static inline struct tizen_remote_surface_provider *
tizen_remote_surface_manager_create_provider(struct tizen_remote_surface_manager *tizen_remote_surface_manager, struct wl_surface *surface)
{
	struct wl_proxy *id;

	id = wl_proxy_marshal_constructor((struct wl_proxy *) tizen_remote_surface_manager,
			 TIZEN_REMOTE_SURFACE_MANAGER_CREATE_PROVIDER, &tizen_remote_surface_provider_interface, NULL, surface);

	return (struct tizen_remote_surface_provider *) id;
}

static inline struct tizen_remote_surface *
tizen_remote_surface_manager_create_surface(struct tizen_remote_surface_manager *tizen_remote_surface_manager, uint32_t resource_id, struct wl_tbm *tbm)
{
	struct wl_proxy *id;

	id = wl_proxy_marshal_constructor((struct wl_proxy *) tizen_remote_surface_manager,
			 TIZEN_REMOTE_SURFACE_MANAGER_CREATE_SURFACE, &tizen_remote_surface_interface, NULL, resource_id, tbm);

	return (struct tizen_remote_surface *) id;
}

static inline void
tizen_remote_surface_manager_bind_surface(struct tizen_remote_surface_manager *tizen_remote_surface_manager, struct wl_surface *surface, struct tizen_remote_surface *remote_surface)
{
	wl_proxy_marshal((struct wl_proxy *) tizen_remote_surface_manager,
			 TIZEN_REMOTE_SURFACE_MANAGER_BIND_SURFACE, surface, remote_surface);
}

#ifndef TIZEN_REMOTE_SURFACE_PROVIDER_VISIBILITY_TYPE_ENUM
#define TIZEN_REMOTE_SURFACE_PROVIDER_VISIBILITY_TYPE_ENUM
enum tizen_remote_surface_provider_visibility_type {
	TIZEN_REMOTE_SURFACE_PROVIDER_VISIBILITY_TYPE_VISIBLE = 0,
	TIZEN_REMOTE_SURFACE_PROVIDER_VISIBILITY_TYPE_INVISIBLE = 1,
};
#endif /* TIZEN_REMOTE_SURFACE_PROVIDER_VISIBILITY_TYPE_ENUM */

/**
 * tizen_remote_surface_provider - a provider of remote buffer source
 * @resource_id: announce resource id of provider
 * @visibility: notify of visibility change
 *
 * A provider client. Surfaces of providers are offscreen. An attached
 * buffer of the provider is used for creating remote buffer and the remote
 * buffer is delivered to remote surfaces.
 */
struct tizen_remote_surface_provider_listener {
	/**
	 * resource_id - announce resource id of provider
	 * @resource_id: this provider's resource_id
	 *
	 * Announce resource id of this provider.
	 */
	void (*resource_id)(void *data,
			    struct tizen_remote_surface_provider *tizen_remote_surface_provider,
			    uint32_t resource_id);
	/**
	 * visibility - notify of visibility change
	 * @visibility: visibility type
	 *
	 * Notify of visibility chages. This provider's visibility is
	 * determined by its remote surfaces.
	 */
	void (*visibility)(void *data,
			   struct tizen_remote_surface_provider *tizen_remote_surface_provider,
			   uint32_t visibility);
};

static inline int
tizen_remote_surface_provider_add_listener(struct tizen_remote_surface_provider *tizen_remote_surface_provider,
					   const struct tizen_remote_surface_provider_listener *listener, void *data)
{
	return wl_proxy_add_listener((struct wl_proxy *) tizen_remote_surface_provider,
				     (void (**)(void)) listener, data);
}

#define TIZEN_REMOTE_SURFACE_PROVIDER_DESTROY	0
#define TIZEN_REMOTE_SURFACE_PROVIDER_OFFSCREEN_SET	1

#define TIZEN_REMOTE_SURFACE_PROVIDER_DESTROY_SINCE_VERSION	1
#define TIZEN_REMOTE_SURFACE_PROVIDER_OFFSCREEN_SET_SINCE_VERSION	1

static inline void
tizen_remote_surface_provider_set_user_data(struct tizen_remote_surface_provider *tizen_remote_surface_provider, void *user_data)
{
	wl_proxy_set_user_data((struct wl_proxy *) tizen_remote_surface_provider, user_data);
}

static inline void *
tizen_remote_surface_provider_get_user_data(struct tizen_remote_surface_provider *tizen_remote_surface_provider)
{
	return wl_proxy_get_user_data((struct wl_proxy *) tizen_remote_surface_provider);
}

static inline uint32_t
tizen_remote_surface_provider_get_version(struct tizen_remote_surface_provider *tizen_remote_surface_provider)
{
	return wl_proxy_get_version((struct wl_proxy *) tizen_remote_surface_provider);
}

static inline void
tizen_remote_surface_provider_destroy(struct tizen_remote_surface_provider *tizen_remote_surface_provider)
{
	wl_proxy_marshal((struct wl_proxy *) tizen_remote_surface_provider,
			 TIZEN_REMOTE_SURFACE_PROVIDER_DESTROY);

	wl_proxy_destroy((struct wl_proxy *) tizen_remote_surface_provider);
}

static inline void
tizen_remote_surface_provider_offscreen_set(struct tizen_remote_surface_provider *tizen_remote_surface_provider, uint32_t set)
{
	wl_proxy_marshal((struct wl_proxy *) tizen_remote_surface_provider,
			 TIZEN_REMOTE_SURFACE_PROVIDER_OFFSCREEN_SET, set);
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
 * @update_buffer: deliver updated buffer of provider
 * @missing: notify of leave of provider
 * @changed_buffer: deliver a changed buffer of the provider client
 *
 * A consumer client of provider client's buffer. The consumer can
 * receive notice of buffer updating of its provider and use the received
 * buffer for drawing its own buffer. The consumer also can request for
 * transfering input events into its provider.
 */
struct tizen_remote_surface_listener {
	/**
	 * update_buffer - deliver updated buffer of provider
	 * @buffer: wayland buffer based on provider updated buffer
	 * @time: timestampa
	 *
	 * When a provider client of a remote surface sent
	 * wl_surface.commit, server deliver a remote buffer is created
	 * based on provider's buffer. This event is deprecated. Please use
	 * changed_buffer event instead of it.
	 */
	void (*update_buffer)(void *data,
			      struct tizen_remote_surface *tizen_remote_surface,
			      struct wl_buffer *buffer,
			      uint32_t time);
	/**
	 * missing - notify of leave of provider
	 *
	 * 
	 */
	void (*missing)(void *data,
			struct tizen_remote_surface *tizen_remote_surface);
	/**
	 * changed_buffer - deliver a changed buffer of the provider
	 *	client
	 * @type: buffer type
	 * @tbm: wayland buffer based on provider updated buffer
	 * @img_file_fd: static image file descriptor
	 * @img_file_size: size of static image file
	 * @time: timestamp
	 * @options: array of options
	 *
	 * When a provider client of a remote surface sent
	 * wl_surface.commit, the compositor delivers a remote buffer is
	 * created based on provider's buffer. And the buffer of provider
	 * client can be changed to the static image file by the compositor
	 * whenever its window is iconify. Then consumer has to use fd
	 * instead of buffer.
	 * @since: 3
	 */
	void (*changed_buffer)(void *data,
			       struct tizen_remote_surface *tizen_remote_surface,
			       uint32_t type,
			       struct wl_buffer *tbm,
			       int32_t img_file_fd,
			       uint32_t img_file_size,
			       uint32_t time,
			       struct wl_array *options);
};

static inline int
tizen_remote_surface_add_listener(struct tizen_remote_surface *tizen_remote_surface,
				  const struct tizen_remote_surface_listener *listener, void *data)
{
	return wl_proxy_add_listener((struct wl_proxy *) tizen_remote_surface,
				     (void (**)(void)) listener, data);
}

#define TIZEN_REMOTE_SURFACE_DESTROY	0
#define TIZEN_REMOTE_SURFACE_REDIRECT	1
#define TIZEN_REMOTE_SURFACE_UNREDIRECT	2
#define TIZEN_REMOTE_SURFACE_TRANSFER_MOUSE_EVENT	3
#define TIZEN_REMOTE_SURFACE_TRANSFER_MOUSE_WHEEL	4
#define TIZEN_REMOTE_SURFACE_TRANSFER_TOUCH_EVENT	5
#define TIZEN_REMOTE_SURFACE_TRANSFER_TOUCH_CANCEL	6
#define TIZEN_REMOTE_SURFACE_TRANSFER_KEY_EVENT	7
#define TIZEN_REMOTE_SURFACE_TRANSFER_VISIBILITY	8
#define TIZEN_REMOTE_SURFACE_SET_OWNER	9
#define TIZEN_REMOTE_SURFACE_CREATE_REGION	10
#define TIZEN_REMOTE_SURFACE_RELEASE	11

#define TIZEN_REMOTE_SURFACE_DESTROY_SINCE_VERSION	1
#define TIZEN_REMOTE_SURFACE_REDIRECT_SINCE_VERSION	1
#define TIZEN_REMOTE_SURFACE_UNREDIRECT_SINCE_VERSION	1
#define TIZEN_REMOTE_SURFACE_TRANSFER_MOUSE_EVENT_SINCE_VERSION	1
#define TIZEN_REMOTE_SURFACE_TRANSFER_MOUSE_WHEEL_SINCE_VERSION	1
#define TIZEN_REMOTE_SURFACE_TRANSFER_TOUCH_EVENT_SINCE_VERSION	1
#define TIZEN_REMOTE_SURFACE_TRANSFER_TOUCH_CANCEL_SINCE_VERSION	1
#define TIZEN_REMOTE_SURFACE_TRANSFER_KEY_EVENT_SINCE_VERSION	1
#define TIZEN_REMOTE_SURFACE_TRANSFER_VISIBILITY_SINCE_VERSION	1
#define TIZEN_REMOTE_SURFACE_SET_OWNER_SINCE_VERSION	1
#define TIZEN_REMOTE_SURFACE_CREATE_REGION_SINCE_VERSION	1
#define TIZEN_REMOTE_SURFACE_RELEASE_SINCE_VERSION	2

static inline void
tizen_remote_surface_set_user_data(struct tizen_remote_surface *tizen_remote_surface, void *user_data)
{
	wl_proxy_set_user_data((struct wl_proxy *) tizen_remote_surface, user_data);
}

static inline void *
tizen_remote_surface_get_user_data(struct tizen_remote_surface *tizen_remote_surface)
{
	return wl_proxy_get_user_data((struct wl_proxy *) tizen_remote_surface);
}

static inline uint32_t
tizen_remote_surface_get_version(struct tizen_remote_surface *tizen_remote_surface)
{
	return wl_proxy_get_version((struct wl_proxy *) tizen_remote_surface);
}

static inline void
tizen_remote_surface_destroy(struct tizen_remote_surface *tizen_remote_surface)
{
	wl_proxy_marshal((struct wl_proxy *) tizen_remote_surface,
			 TIZEN_REMOTE_SURFACE_DESTROY);

	wl_proxy_destroy((struct wl_proxy *) tizen_remote_surface);
}

static inline void
tizen_remote_surface_redirect(struct tizen_remote_surface *tizen_remote_surface)
{
	wl_proxy_marshal((struct wl_proxy *) tizen_remote_surface,
			 TIZEN_REMOTE_SURFACE_REDIRECT);
}

static inline void
tizen_remote_surface_unredirect(struct tizen_remote_surface *tizen_remote_surface)
{
	wl_proxy_marshal((struct wl_proxy *) tizen_remote_surface,
			 TIZEN_REMOTE_SURFACE_UNREDIRECT);
}

static inline void
tizen_remote_surface_transfer_mouse_event(struct tizen_remote_surface *tizen_remote_surface, uint32_t event_type, int32_t device, int32_t button, int32_t x, int32_t y, wl_fixed_t radius_x, wl_fixed_t radius_y, wl_fixed_t pressure, wl_fixed_t angle, uint32_t clas, uint32_t subclas, const char *identifier, uint32_t time)
{
	wl_proxy_marshal((struct wl_proxy *) tizen_remote_surface,
			 TIZEN_REMOTE_SURFACE_TRANSFER_MOUSE_EVENT, event_type, device, button, x, y, radius_x, radius_y, pressure, angle, clas, subclas, identifier, time);
}

static inline void
tizen_remote_surface_transfer_mouse_wheel(struct tizen_remote_surface *tizen_remote_surface, uint32_t direction, int32_t z, uint32_t clas, uint32_t subclas, const char *identifier, uint32_t time)
{
	wl_proxy_marshal((struct wl_proxy *) tizen_remote_surface,
			 TIZEN_REMOTE_SURFACE_TRANSFER_MOUSE_WHEEL, direction, z, clas, subclas, identifier, time);
}

static inline void
tizen_remote_surface_transfer_touch_event(struct tizen_remote_surface *tizen_remote_surface, uint32_t event_type, int32_t device, int32_t button, int32_t x, int32_t y, wl_fixed_t radius_x, wl_fixed_t radius_y, wl_fixed_t pressure, wl_fixed_t angle, uint32_t clas, uint32_t subclas, const char *identifier, uint32_t time)
{
	wl_proxy_marshal((struct wl_proxy *) tizen_remote_surface,
			 TIZEN_REMOTE_SURFACE_TRANSFER_TOUCH_EVENT, event_type, device, button, x, y, radius_x, radius_y, pressure, angle, clas, subclas, identifier, time);
}

static inline void
tizen_remote_surface_transfer_touch_cancel(struct tizen_remote_surface *tizen_remote_surface)
{
	wl_proxy_marshal((struct wl_proxy *) tizen_remote_surface,
			 TIZEN_REMOTE_SURFACE_TRANSFER_TOUCH_CANCEL);
}

static inline void
tizen_remote_surface_transfer_key_event(struct tizen_remote_surface *tizen_remote_surface, uint32_t event_type, int32_t keycode, uint32_t clas, uint32_t subclas, const char *identifier, uint32_t time)
{
	wl_proxy_marshal((struct wl_proxy *) tizen_remote_surface,
			 TIZEN_REMOTE_SURFACE_TRANSFER_KEY_EVENT, event_type, keycode, clas, subclas, identifier, time);
}

static inline void
tizen_remote_surface_transfer_visibility(struct tizen_remote_surface *tizen_remote_surface, uint32_t visibility)
{
	wl_proxy_marshal((struct wl_proxy *) tizen_remote_surface,
			 TIZEN_REMOTE_SURFACE_TRANSFER_VISIBILITY, visibility);
}

static inline void
tizen_remote_surface_set_owner(struct tizen_remote_surface *tizen_remote_surface, struct wl_surface *owner)
{
	wl_proxy_marshal((struct wl_proxy *) tizen_remote_surface,
			 TIZEN_REMOTE_SURFACE_SET_OWNER, owner);
}

static inline struct tizen_remote_surface_region *
tizen_remote_surface_create_region(struct tizen_remote_surface *tizen_remote_surface)
{
	struct wl_proxy *id;

	id = wl_proxy_marshal_constructor((struct wl_proxy *) tizen_remote_surface,
			 TIZEN_REMOTE_SURFACE_CREATE_REGION, &tizen_remote_surface_region_interface, NULL);

	return (struct tizen_remote_surface_region *) id;
}

static inline void
tizen_remote_surface_release(struct tizen_remote_surface *tizen_remote_surface, struct wl_buffer *buffer)
{
	wl_proxy_marshal((struct wl_proxy *) tizen_remote_surface,
			 TIZEN_REMOTE_SURFACE_RELEASE, buffer);
}

#define TIZEN_REMOTE_SURFACE_REGION_DESTROY	0
#define TIZEN_REMOTE_SURFACE_REGION_SET_GEOMETRY	1

#define TIZEN_REMOTE_SURFACE_REGION_DESTROY_SINCE_VERSION	1
#define TIZEN_REMOTE_SURFACE_REGION_SET_GEOMETRY_SINCE_VERSION	1

static inline void
tizen_remote_surface_region_set_user_data(struct tizen_remote_surface_region *tizen_remote_surface_region, void *user_data)
{
	wl_proxy_set_user_data((struct wl_proxy *) tizen_remote_surface_region, user_data);
}

static inline void *
tizen_remote_surface_region_get_user_data(struct tizen_remote_surface_region *tizen_remote_surface_region)
{
	return wl_proxy_get_user_data((struct wl_proxy *) tizen_remote_surface_region);
}

static inline uint32_t
tizen_remote_surface_region_get_version(struct tizen_remote_surface_region *tizen_remote_surface_region)
{
	return wl_proxy_get_version((struct wl_proxy *) tizen_remote_surface_region);
}

static inline void
tizen_remote_surface_region_destroy(struct tizen_remote_surface_region *tizen_remote_surface_region)
{
	wl_proxy_marshal((struct wl_proxy *) tizen_remote_surface_region,
			 TIZEN_REMOTE_SURFACE_REGION_DESTROY);

	wl_proxy_destroy((struct wl_proxy *) tizen_remote_surface_region);
}

static inline void
tizen_remote_surface_region_set_geometry(struct tizen_remote_surface_region *tizen_remote_surface_region, int32_t x, int32_t y, int32_t w, int32_t h)
{
	wl_proxy_marshal((struct wl_proxy *) tizen_remote_surface_region,
			 TIZEN_REMOTE_SURFACE_REGION_SET_GEOMETRY, x, y, w, h);
}

#ifdef  __cplusplus
}
#endif

#endif
