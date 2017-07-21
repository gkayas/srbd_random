/*
 * Copyright 2015 Samsung Electronics co., Ltd. All Rights Reserved.
 *
 * Permission to use, copy, modify, distribute, and sell this
 * software and its documentation for any purpose is hereby granted
 * without fee, provided that\n the above copyright notice appear in
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

#ifndef TBM_CLIENT_PROTOCOL_H
#define TBM_CLIENT_PROTOCOL_H

#ifdef  __cplusplus
extern "C" {
#endif

#include <stdint.h>
#include <stddef.h>
#include "wayland-client.h"

struct wl_client;
struct wl_resource;

struct wl_buffer;
struct wl_surface;
struct wl_tbm;
struct wl_tbm_monitor;
struct wl_tbm_queue;

extern const struct wl_interface wl_tbm_interface;
extern const struct wl_interface wl_tbm_queue_interface;
extern const struct wl_interface wl_tbm_monitor_interface;

#ifndef WL_TBM_ERROR_ENUM
#define WL_TBM_ERROR_ENUM
enum wl_tbm_error {
	WL_TBM_ERROR_INVALID_FORMAT = 1,
	WL_TBM_ERROR_INVALID_NAME = 2,
};
#endif /* WL_TBM_ERROR_ENUM */

struct wl_tbm_listener {
	/**
	 * buffer_import_with_id - (none)
	 * @wl_buffer: (none)
	 * @width: (none)
	 * @height: (none)
	 * @format: (none)
	 * @num_plane: (none)
	 * @buf_idx0: (none)
	 * @offset0: (none)
	 * @stride0: (none)
	 * @buf_idx1: (none)
	 * @offset1: (none)
	 * @stride1: (none)
	 * @buf_idx2: (none)
	 * @offset2: (none)
	 * @stride2: (none)
	 * @flags: (none)
	 * @num_buf: (none)
	 * @buf0: (none)
	 * @buf1: (none)
	 * @buf2: (none)
	 */
	void (*buffer_import_with_id)(void *data,
				      struct wl_tbm *wl_tbm,
				      struct wl_buffer *wl_buffer,
				      int32_t width,
				      int32_t height,
				      uint32_t format,
				      int32_t num_plane,
				      int32_t buf_idx0,
				      int32_t offset0,
				      int32_t stride0,
				      int32_t buf_idx1,
				      int32_t offset1,
				      int32_t stride1,
				      int32_t buf_idx2,
				      int32_t offset2,
				      int32_t stride2,
				      uint32_t flags,
				      int32_t num_buf,
				      uint32_t buf0,
				      uint32_t buf1,
				      uint32_t buf2);
	/**
	 * buffer_import_with_fd - (none)
	 * @wl_buffer: (none)
	 * @width: (none)
	 * @height: (none)
	 * @format: (none)
	 * @num_plane: (none)
	 * @buf_idx0: (none)
	 * @offset0: (none)
	 * @stride0: (none)
	 * @buf_idx1: (none)
	 * @offset1: (none)
	 * @stride1: (none)
	 * @buf_idx2: (none)
	 * @offset2: (none)
	 * @stride2: (none)
	 * @flags: (none)
	 * @num_buf: (none)
	 * @buf0: (none)
	 * @buf1: (none)
	 * @buf2: (none)
	 */
	void (*buffer_import_with_fd)(void *data,
				      struct wl_tbm *wl_tbm,
				      struct wl_buffer *wl_buffer,
				      int32_t width,
				      int32_t height,
				      uint32_t format,
				      int32_t num_plane,
				      int32_t buf_idx0,
				      int32_t offset0,
				      int32_t stride0,
				      int32_t buf_idx1,
				      int32_t offset1,
				      int32_t stride1,
				      int32_t buf_idx2,
				      int32_t offset2,
				      int32_t stride2,
				      uint32_t flags,
				      int32_t num_buf,
				      int32_t buf0,
				      int32_t buf1,
				      int32_t buf2);
};

static inline int
wl_tbm_add_listener(struct wl_tbm *wl_tbm,
		    const struct wl_tbm_listener *listener, void *data)
{
	return wl_proxy_add_listener((struct wl_proxy *) wl_tbm,
				     (void (**)(void)) listener, data);
}

#define WL_TBM_CREATE_BUFFER	0
#define WL_TBM_CREATE_BUFFER_WITH_FD	1
#define WL_TBM_CREATE_SURFACE_QUEUE	2
#define WL_TBM_SET_SYNC_TIMELINE	3
#define WL_TBM_DESTROY	4

#define WL_TBM_CREATE_BUFFER_SINCE_VERSION	1
#define WL_TBM_CREATE_BUFFER_WITH_FD_SINCE_VERSION	1
#define WL_TBM_CREATE_SURFACE_QUEUE_SINCE_VERSION	1
#define WL_TBM_SET_SYNC_TIMELINE_SINCE_VERSION	1
#define WL_TBM_DESTROY_SINCE_VERSION	1

static inline void
wl_tbm_set_user_data(struct wl_tbm *wl_tbm, void *user_data)
{
	wl_proxy_set_user_data((struct wl_proxy *) wl_tbm, user_data);
}

static inline void *
wl_tbm_get_user_data(struct wl_tbm *wl_tbm)
{
	return wl_proxy_get_user_data((struct wl_proxy *) wl_tbm);
}

static inline uint32_t
wl_tbm_get_version(struct wl_tbm *wl_tbm)
{
	return wl_proxy_get_version((struct wl_proxy *) wl_tbm);
}

static inline struct wl_buffer *
wl_tbm_create_buffer(struct wl_tbm *wl_tbm, int32_t width, int32_t height, uint32_t format, int32_t num_plane, int32_t buf_idx0, int32_t offset0, int32_t stride0, int32_t buf_idx1, int32_t offset1, int32_t stride1, int32_t buf_idx2, int32_t offset2, int32_t stride2, uint32_t flags, int32_t num_buf, uint32_t buf0, uint32_t buf1, uint32_t buf2)
{
	struct wl_proxy *id;

	id = wl_proxy_marshal_constructor((struct wl_proxy *) wl_tbm,
			 WL_TBM_CREATE_BUFFER, &wl_buffer_interface, NULL, width, height, format, num_plane, buf_idx0, offset0, stride0, buf_idx1, offset1, stride1, buf_idx2, offset2, stride2, flags, num_buf, buf0, buf1, buf2);

	return (struct wl_buffer *) id;
}

static inline struct wl_buffer *
wl_tbm_create_buffer_with_fd(struct wl_tbm *wl_tbm, int32_t width, int32_t height, uint32_t format, int32_t num_plane, int32_t buf_idx0, int32_t offset0, int32_t stride0, int32_t buf_idx1, int32_t offset1, int32_t stride1, int32_t buf_idx2, int32_t offset2, int32_t stride2, uint32_t flags, int32_t num_buf, int32_t buf0, int32_t buf1, int32_t buf2)
{
	struct wl_proxy *id;

	id = wl_proxy_marshal_constructor((struct wl_proxy *) wl_tbm,
			 WL_TBM_CREATE_BUFFER_WITH_FD, &wl_buffer_interface, NULL, width, height, format, num_plane, buf_idx0, offset0, stride0, buf_idx1, offset1, stride1, buf_idx2, offset2, stride2, flags, num_buf, buf0, buf1, buf2);

	return (struct wl_buffer *) id;
}

static inline struct wl_tbm_queue *
wl_tbm_create_surface_queue(struct wl_tbm *wl_tbm, struct wl_surface *surface)
{
	struct wl_proxy *surface_queue;

	surface_queue = wl_proxy_marshal_constructor((struct wl_proxy *) wl_tbm,
			 WL_TBM_CREATE_SURFACE_QUEUE, &wl_tbm_queue_interface, NULL, surface);

	return (struct wl_tbm_queue *) surface_queue;
}

static inline void
wl_tbm_set_sync_timeline(struct wl_tbm *wl_tbm, struct wl_buffer *buffer, int32_t timeline)
{
	wl_proxy_marshal((struct wl_proxy *) wl_tbm,
			 WL_TBM_SET_SYNC_TIMELINE, buffer, timeline);
}

static inline void
wl_tbm_destroy(struct wl_tbm *wl_tbm)
{
	wl_proxy_marshal((struct wl_proxy *) wl_tbm,
			 WL_TBM_DESTROY);

	wl_proxy_destroy((struct wl_proxy *) wl_tbm);
}

struct wl_tbm_queue_listener {
	/**
	 * buffer_attached - (none)
	 * @id: (none)
	 * @flags: (none)
	 */
	void (*buffer_attached)(void *data,
				struct wl_tbm_queue *wl_tbm_queue,
				struct wl_buffer *id,
				uint32_t flags);
	/**
	 * active - (none)
	 * @usage: (none)
	 * @queue_size: (none)
	 * @need_flush: (none)
	 */
	void (*active)(void *data,
		       struct wl_tbm_queue *wl_tbm_queue,
		       uint32_t usage,
		       uint32_t queue_size,
		       uint32_t need_flush);
	/**
	 * deactive - (none)
	 */
	void (*deactive)(void *data,
			 struct wl_tbm_queue *wl_tbm_queue);
	/**
	 * flush - (none)
	 */
	void (*flush)(void *data,
		      struct wl_tbm_queue *wl_tbm_queue);
};

static inline int
wl_tbm_queue_add_listener(struct wl_tbm_queue *wl_tbm_queue,
			  const struct wl_tbm_queue_listener *listener, void *data)
{
	return wl_proxy_add_listener((struct wl_proxy *) wl_tbm_queue,
				     (void (**)(void)) listener, data);
}

#define WL_TBM_QUEUE_DESTROY	0
#define WL_TBM_QUEUE_DETACH_BUFFER	1

#define WL_TBM_QUEUE_DESTROY_SINCE_VERSION	1
#define WL_TBM_QUEUE_DETACH_BUFFER_SINCE_VERSION	1

static inline void
wl_tbm_queue_set_user_data(struct wl_tbm_queue *wl_tbm_queue, void *user_data)
{
	wl_proxy_set_user_data((struct wl_proxy *) wl_tbm_queue, user_data);
}

static inline void *
wl_tbm_queue_get_user_data(struct wl_tbm_queue *wl_tbm_queue)
{
	return wl_proxy_get_user_data((struct wl_proxy *) wl_tbm_queue);
}

static inline uint32_t
wl_tbm_queue_get_version(struct wl_tbm_queue *wl_tbm_queue)
{
	return wl_proxy_get_version((struct wl_proxy *) wl_tbm_queue);
}

static inline void
wl_tbm_queue_destroy(struct wl_tbm_queue *wl_tbm_queue)
{
	wl_proxy_marshal((struct wl_proxy *) wl_tbm_queue,
			 WL_TBM_QUEUE_DESTROY);

	wl_proxy_destroy((struct wl_proxy *) wl_tbm_queue);
}

static inline void
wl_tbm_queue_detach_buffer(struct wl_tbm_queue *wl_tbm_queue, struct wl_buffer *buffer)
{
	wl_proxy_marshal((struct wl_proxy *) wl_tbm_queue,
			 WL_TBM_QUEUE_DETACH_BUFFER, buffer);
}

struct wl_tbm_monitor_listener {
	/**
	 * monitor_client_tbm_bo - (none)
	 * @command: (none)
	 * @trace_command: (none)
	 * @target: (none)
	 * @pid: (none)
	 */
	void (*monitor_client_tbm_bo)(void *data,
				      struct wl_tbm_monitor *wl_tbm_monitor,
				      int32_t command,
				      int32_t trace_command,
				      int32_t target,
				      int32_t pid);
};

static inline int
wl_tbm_monitor_add_listener(struct wl_tbm_monitor *wl_tbm_monitor,
			    const struct wl_tbm_monitor_listener *listener, void *data)
{
	return wl_proxy_add_listener((struct wl_proxy *) wl_tbm_monitor,
				     (void (**)(void)) listener, data);
}

#define WL_TBM_MONITOR_DESTROY	0
#define WL_TBM_MONITOR_REQUEST_TBM_MONITOR	1

#define WL_TBM_MONITOR_DESTROY_SINCE_VERSION	1
#define WL_TBM_MONITOR_REQUEST_TBM_MONITOR_SINCE_VERSION	1

static inline void
wl_tbm_monitor_set_user_data(struct wl_tbm_monitor *wl_tbm_monitor, void *user_data)
{
	wl_proxy_set_user_data((struct wl_proxy *) wl_tbm_monitor, user_data);
}

static inline void *
wl_tbm_monitor_get_user_data(struct wl_tbm_monitor *wl_tbm_monitor)
{
	return wl_proxy_get_user_data((struct wl_proxy *) wl_tbm_monitor);
}

static inline uint32_t
wl_tbm_monitor_get_version(struct wl_tbm_monitor *wl_tbm_monitor)
{
	return wl_proxy_get_version((struct wl_proxy *) wl_tbm_monitor);
}

static inline void
wl_tbm_monitor_destroy(struct wl_tbm_monitor *wl_tbm_monitor)
{
	wl_proxy_marshal((struct wl_proxy *) wl_tbm_monitor,
			 WL_TBM_MONITOR_DESTROY);

	wl_proxy_destroy((struct wl_proxy *) wl_tbm_monitor);
}

static inline void
wl_tbm_monitor_request_tbm_monitor(struct wl_tbm_monitor *wl_tbm_monitor, int32_t command, int32_t trace_command, int32_t target, int32_t pid)
{
	wl_proxy_marshal((struct wl_proxy *) wl_tbm_monitor,
			 WL_TBM_MONITOR_REQUEST_TBM_MONITOR, command, trace_command, target, pid);
}

#ifdef  __cplusplus
}
#endif

#endif
