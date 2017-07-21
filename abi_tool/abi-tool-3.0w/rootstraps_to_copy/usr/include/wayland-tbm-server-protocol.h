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

#ifndef TBM_SERVER_PROTOCOL_H
#define TBM_SERVER_PROTOCOL_H

#ifdef  __cplusplus
extern "C" {
#endif

#include <stdint.h>
#include <stddef.h>
#include "wayland-server.h"

struct wl_client;
struct wl_resource;

struct wl_buffer;
struct wl_surface;
struct wl_tbm;
struct wl_tbm_queue;

extern const struct wl_interface wl_tbm_interface;
extern const struct wl_interface wl_tbm_queue_interface;

#ifndef WL_TBM_ERROR_ENUM
#define WL_TBM_ERROR_ENUM
enum wl_tbm_error {
	WL_TBM_ERROR_INVALID_FORMAT = 1,
	WL_TBM_ERROR_INVALID_NAME = 2,
};
#endif /* WL_TBM_ERROR_ENUM */

struct wl_tbm_interface {
	/**
	 * create_buffer - (none)
	 * @id: (none)
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
	void (*create_buffer)(struct wl_client *client,
			      struct wl_resource *resource,
			      uint32_t id,
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
	 * create_buffer_with_fd - (none)
	 * @id: (none)
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
	void (*create_buffer_with_fd)(struct wl_client *client,
				      struct wl_resource *resource,
				      uint32_t id,
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
	/**
	 * request_tbm_monitor - (none)
	 * @command: (none)
	 * @trace_command: (none)
	 * @target: (none)
	 * @pid: (none)
	 */
	void (*request_tbm_monitor)(struct wl_client *client,
				    struct wl_resource *resource,
				    int32_t command,
				    int32_t trace_command,
				    int32_t target,
				    int32_t pid);
	/**
	 * create_surface_queue - (none)
	 * @surface_queue: (none)
	 * @surface: (none)
	 */
	void (*create_surface_queue)(struct wl_client *client,
				     struct wl_resource *resource,
				     uint32_t surface_queue,
				     struct wl_resource *surface);
};

#define WL_TBM_MONITOR_CLIENT_TBM_BO	0

#define WL_TBM_MONITOR_CLIENT_TBM_BO_SINCE_VERSION	1

static inline void
wl_tbm_send_monitor_client_tbm_bo(struct wl_resource *resource_, int32_t command, int32_t trace_command, int32_t target, int32_t pid)
{
	wl_resource_post_event(resource_, WL_TBM_MONITOR_CLIENT_TBM_BO, command, trace_command, target, pid);
}

struct wl_tbm_queue_interface {
	/**
	 * destroy - (none)
	 */
	void (*destroy)(struct wl_client *client,
			struct wl_resource *resource);
	/**
	 * detach_buffer - (none)
	 * @buffer: (none)
	 */
	void (*detach_buffer)(struct wl_client *client,
			      struct wl_resource *resource,
			      struct wl_resource *buffer);
};

#define WL_TBM_QUEUE_INFO	0
#define WL_TBM_QUEUE_BUFFER_ATTACHED_WITH_ID	1
#define WL_TBM_QUEUE_BUFFER_ATTACHED_WITH_FD	2
#define WL_TBM_QUEUE_ACTIVE	3
#define WL_TBM_QUEUE_DEACTIVE	4
#define WL_TBM_QUEUE_FLUSH	5

#define WL_TBM_QUEUE_INFO_SINCE_VERSION	1
#define WL_TBM_QUEUE_BUFFER_ATTACHED_WITH_ID_SINCE_VERSION	1
#define WL_TBM_QUEUE_BUFFER_ATTACHED_WITH_FD_SINCE_VERSION	1
#define WL_TBM_QUEUE_ACTIVE_SINCE_VERSION	1
#define WL_TBM_QUEUE_DEACTIVE_SINCE_VERSION	1
#define WL_TBM_QUEUE_FLUSH_SINCE_VERSION	1

static inline void
wl_tbm_queue_send_info(struct wl_resource *resource_, int32_t width, int32_t height, int32_t format, uint32_t flags, uint32_t num_buffers)
{
	wl_resource_post_event(resource_, WL_TBM_QUEUE_INFO, width, height, format, flags, num_buffers);
}

static inline void
wl_tbm_queue_send_buffer_attached_with_id(struct wl_resource *resource_, struct wl_resource *id, int32_t width, int32_t height, uint32_t format, int32_t num_plane, int32_t buf_idx0, int32_t offset0, int32_t stride0, int32_t buf_idx1, int32_t offset1, int32_t stride1, int32_t buf_idx2, int32_t offset2, int32_t stride2, uint32_t flags, int32_t num_buf, uint32_t buf0, uint32_t buf1, uint32_t buf2)
{
	wl_resource_post_event(resource_, WL_TBM_QUEUE_BUFFER_ATTACHED_WITH_ID, id, width, height, format, num_plane, buf_idx0, offset0, stride0, buf_idx1, offset1, stride1, buf_idx2, offset2, stride2, flags, num_buf, buf0, buf1, buf2);
}

static inline void
wl_tbm_queue_send_buffer_attached_with_fd(struct wl_resource *resource_, struct wl_resource *id, int32_t width, int32_t height, uint32_t format, int32_t num_plane, int32_t buf_idx0, int32_t offset0, int32_t stride0, int32_t buf_idx1, int32_t offset1, int32_t stride1, int32_t buf_idx2, int32_t offset2, int32_t stride2, uint32_t flags, int32_t num_buf, int32_t buf0, int32_t buf1, int32_t buf2)
{
	wl_resource_post_event(resource_, WL_TBM_QUEUE_BUFFER_ATTACHED_WITH_FD, id, width, height, format, num_plane, buf_idx0, offset0, stride0, buf_idx1, offset1, stride1, buf_idx2, offset2, stride2, flags, num_buf, buf0, buf1, buf2);
}

static inline void
wl_tbm_queue_send_active(struct wl_resource *resource_, uint32_t usage)
{
	wl_resource_post_event(resource_, WL_TBM_QUEUE_ACTIVE, usage);
}

static inline void
wl_tbm_queue_send_deactive(struct wl_resource *resource_)
{
	wl_resource_post_event(resource_, WL_TBM_QUEUE_DEACTIVE);
}

static inline void
wl_tbm_queue_send_flush(struct wl_resource *resource_)
{
	wl_resource_post_event(resource_, WL_TBM_QUEUE_FLUSH);
}

#ifdef  __cplusplus
}
#endif

#endif
