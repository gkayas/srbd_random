/*
 * Copyright © 2013-2014 Collabora, Ltd.
 *
 * Permission to use, copy, modify, distribute, and sell this
 * software and its documentation for any purpose is hereby granted
 * without fee, provided that the above copyright notice appear in
 * all copies and that both that copyright notice and this permission
 * notice appear in supporting documentation, and that the name of
 * the copyright holders not be used in advertising or publicity
 * pertaining to distribution of the software without specific,
 * written prior permission.  The copyright holders make no
 * representations about the suitability of this software for any
 * purpose.  It is provided "as is" without express or implied
 * warranty.
 *
 * THE COPYRIGHT HOLDERS DISCLAIM ALL WARRANTIES WITH REGARD TO THIS
 * SOFTWARE, INCLUDING ALL IMPLIED WARRANTIES OF MERCHANTABILITY AND
 * FITNESS, IN NO EVENT SHALL THE COPYRIGHT HOLDERS BE LIABLE FOR ANY
 * SPECIAL, INDIRECT OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES
 * WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR PROFITS, WHETHER IN
 * AN ACTION OF CONTRACT, NEGLIGENCE OR OTHER TORTIOUS ACTION,
 * ARISING OUT OF OR IN CONNECTION WITH THE USE OR PERFORMANCE OF
 * THIS SOFTWARE.
 */

#ifndef TRANSFORM_CLIENT_PROTOCOL_H
#define TRANSFORM_CLIENT_PROTOCOL_H

#ifdef  __cplusplus
extern "C" {
#endif

#include <stdint.h>
#include <stddef.h>
#include "wayland-client.h"

struct wl_client;
struct wl_resource;

struct wl_rotator;
struct wl_surface;
struct wl_transform;

extern const struct wl_interface wl_transform_interface;
extern const struct wl_interface wl_rotator_interface;

#define WL_TRANSFORM_DESTROY	0
#define WL_TRANSFORM_GET_ROTATOR	1

#define WL_TRANSFORM_DESTROY_SINCE_VERSION	1
#define WL_TRANSFORM_GET_ROTATOR_SINCE_VERSION	1

static inline void
wl_transform_set_user_data(struct wl_transform *wl_transform, void *user_data)
{
	wl_proxy_set_user_data((struct wl_proxy *) wl_transform, user_data);
}

static inline void *
wl_transform_get_user_data(struct wl_transform *wl_transform)
{
	return wl_proxy_get_user_data((struct wl_proxy *) wl_transform);
}

static inline uint32_t
wl_transform_get_version(struct wl_transform *wl_transform)
{
	return wl_proxy_get_version((struct wl_proxy *) wl_transform);
}

static inline void
wl_transform_destroy(struct wl_transform *wl_transform)
{
	wl_proxy_marshal((struct wl_proxy *) wl_transform,
			 WL_TRANSFORM_DESTROY);

	wl_proxy_destroy((struct wl_proxy *) wl_transform);
}

static inline struct wl_rotator *
wl_transform_get_rotator(struct wl_transform *wl_transform, struct wl_surface *surface)
{
	struct wl_proxy *id;

	id = wl_proxy_marshal_constructor((struct wl_proxy *) wl_transform,
			 WL_TRANSFORM_GET_ROTATOR, &wl_rotator_interface, NULL, surface);

	return (struct wl_rotator *) id;
}

#ifndef WL_ROTATOR_ERROR_ENUM
#define WL_ROTATOR_ERROR_ENUM
enum wl_rotator_error {
	WL_ROTATOR_ERROR_BAD_VALUE = 0,
};
#endif /* WL_ROTATOR_ERROR_ENUM */

#define WL_ROTATOR_DESTROY	0
#define WL_ROTATOR_SET	1
#define WL_ROTATOR_UNSET	2

#define WL_ROTATOR_DESTROY_SINCE_VERSION	1
#define WL_ROTATOR_SET_SINCE_VERSION	1
#define WL_ROTATOR_UNSET_SINCE_VERSION	1

static inline void
wl_rotator_set_user_data(struct wl_rotator *wl_rotator, void *user_data)
{
	wl_proxy_set_user_data((struct wl_proxy *) wl_rotator, user_data);
}

static inline void *
wl_rotator_get_user_data(struct wl_rotator *wl_rotator)
{
	return wl_proxy_get_user_data((struct wl_proxy *) wl_rotator);
}

static inline uint32_t
wl_rotator_get_version(struct wl_rotator *wl_rotator)
{
	return wl_proxy_get_version((struct wl_proxy *) wl_rotator);
}

static inline void
wl_rotator_destroy(struct wl_rotator *wl_rotator)
{
	wl_proxy_marshal((struct wl_proxy *) wl_rotator,
			 WL_ROTATOR_DESTROY);

	wl_proxy_destroy((struct wl_proxy *) wl_rotator);
}

static inline void
wl_rotator_set(struct wl_rotator *wl_rotator)
{
	wl_proxy_marshal((struct wl_proxy *) wl_rotator,
			 WL_ROTATOR_SET);
}

static inline void
wl_rotator_unset(struct wl_rotator *wl_rotator)
{
	wl_proxy_marshal((struct wl_proxy *) wl_rotator,
			 WL_ROTATOR_UNSET);
}

#ifdef  __cplusplus
}
#endif

#endif
