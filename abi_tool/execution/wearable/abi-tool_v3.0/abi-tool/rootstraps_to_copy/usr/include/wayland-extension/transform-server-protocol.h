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

#ifndef TRANSFORM_SERVER_PROTOCOL_H
#define TRANSFORM_SERVER_PROTOCOL_H

#ifdef  __cplusplus
extern "C" {
#endif

#include <stdint.h>
#include <stddef.h>
#include "wayland-server.h"

struct wl_client;
struct wl_resource;

struct wl_rotator;
struct wl_surface;
struct wl_transform;

extern const struct wl_interface wl_transform_interface;
extern const struct wl_interface wl_rotator_interface;

/**
 * wl_transform - 
 * @destroy: 
 * @get_rotator: 
 *
 * 
 */
struct wl_transform_interface {
	/**
	 * destroy - 
	 *
	 * 
	 */
	void (*destroy)(struct wl_client *client,
			struct wl_resource *resource);
	/**
	 * get_rotator - 
	 * @id: 
	 * @surface: the surface
	 *
	 * 
	 */
	void (*get_rotator)(struct wl_client *client,
			    struct wl_resource *resource,
			    uint32_t id,
			    struct wl_resource *surface);
};


#ifndef WL_ROTATOR_ERROR_ENUM
#define WL_ROTATOR_ERROR_ENUM
enum wl_rotator_error {
	WL_ROTATOR_ERROR_BAD_VALUE = 0,
};
#endif /* WL_ROTATOR_ERROR_ENUM */

/**
 * wl_rotator - crop and scale interface to a wl_surface
 * @destroy: 
 * @set: 
 * @unset: 
 *
 * 
 */
struct wl_rotator_interface {
	/**
	 * destroy - 
	 *
	 * 
	 */
	void (*destroy)(struct wl_client *client,
			struct wl_resource *resource);
	/**
	 * set - 
	 *
	 * 
	 */
	void (*set)(struct wl_client *client,
		    struct wl_resource *resource);
	/**
	 * unset - 
	 *
	 * 
	 */
	void (*unset)(struct wl_client *client,
		      struct wl_resource *resource);
};


#ifdef  __cplusplus
}
#endif

#endif
