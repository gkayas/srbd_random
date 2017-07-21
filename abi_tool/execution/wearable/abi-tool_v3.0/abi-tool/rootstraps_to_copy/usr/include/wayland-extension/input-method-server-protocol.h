/*
 * Copyright © 2012, 2013 Intel Corporation
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

#ifndef INPUT_METHOD_SERVER_PROTOCOL_H
#define INPUT_METHOD_SERVER_PROTOCOL_H

#ifdef  __cplusplus
extern "C" {
#endif

#include <stdint.h>
#include <stddef.h>
#include "wayland-server.h"

struct wl_client;
struct wl_resource;

struct wl_input_method;
struct wl_input_method_context;
struct wl_input_panel;
struct wl_input_panel_surface;
struct wl_keyboard;
struct wl_output;
struct wl_surface;

extern const struct wl_interface wl_input_method_context_interface;
extern const struct wl_interface wl_input_method_interface;
extern const struct wl_interface wl_input_panel_interface;
extern const struct wl_interface wl_input_panel_surface_interface;

/**
 * wl_input_method_context - input method context
 * @destroy: (none)
 * @commit_string: commit string
 * @preedit_string: pre-edit string
 * @preedit_styling: pre-edit styling
 * @preedit_cursor: pre-edit cursor
 * @delete_surrounding_text: delete text
 * @cursor_position: set cursor to a new position
 * @modifiers_map: (none)
 * @keysym: keysym
 * @grab_keyboard: grab hardware keyboard
 * @key: forward key event
 * @modifiers: forward modifiers event
 * @language: (none)
 * @text_direction: (none)
 * @selection_region: (none)
 * @private_command: (none)
 * @update_input_panel_data: (none)
 * @hide_input_panel: (none)
 * @get_selection_text: (none)
 * @get_surrounding_text: (none)
 * @filter_key_event_done: (none)
 *
 * Corresponds to a text model on input method side. An input method
 * context is created on text model activation on the input method side. It
 * allows to receive information about the text model from the application
 * via events. Input method contexts do not keep state after deactivation
 * and should be destroyed after deactivation is handled.
 *
 * Text is generally UTF-8 encoded, indices and lengths are in bytes.
 *
 * Serials are used to synchronize the state between the text input and an
 * input method. New serials are sent by the text input in the commit_state
 * request and are used by the input method to indicate the known text
 * input state in events like preedit_string, commit_string, and keysym.
 * The text input can then ignore events from the input method which are
 * based on an outdated state (for example after a reset).
 */
struct wl_input_method_context_interface {
	/**
	 * destroy - (none)
	 */
	void (*destroy)(struct wl_client *client,
			struct wl_resource *resource);
	/**
	 * commit_string - commit string
	 * @serial: serial of the latest known text input state
	 * @text: (none)
	 *
	 * Send the commit string text for insertion to the application.
	 *
	 * The text to commit could be either just a single character after
	 * a key press or the result of some composing (pre-edit). It could
	 * be also an empty text when some text should be removed (see
	 * delete_surrounding_text) or when the input cursor should be
	 * moved (see cursor_position).
	 *
	 * Any previously set composing text will be removed.
	 */
	void (*commit_string)(struct wl_client *client,
			      struct wl_resource *resource,
			      uint32_t serial,
			      const char *text);
	/**
	 * preedit_string - pre-edit string
	 * @serial: serial of the latest known text input state
	 * @text: (none)
	 * @commit: (none)
	 *
	 * Send the pre-edit string text to the application text input.
	 *
	 * The commit text can be used to replace the preedit text on reset
	 * (for example on unfocus).
	 *
	 * Also previously sent preedit_style and preedit_cursor requests
	 * are processed bt the text_input also.
	 */
	void (*preedit_string)(struct wl_client *client,
			       struct wl_resource *resource,
			       uint32_t serial,
			       const char *text,
			       const char *commit);
	/**
	 * preedit_styling - pre-edit styling
	 * @index: (none)
	 * @length: (none)
	 * @style: (none)
	 *
	 * Set styling information on composing text. The style is
	 * applied for length in bytes from index relative to the beginning
	 * of the composing text (as byte offset). Multiple styles can be
	 * applied to a composing text.
	 *
	 * This request should be sent before sending preedit_string
	 * request.
	 */
	void (*preedit_styling)(struct wl_client *client,
				struct wl_resource *resource,
				uint32_t index,
				uint32_t length,
				uint32_t style);
	/**
	 * preedit_cursor - pre-edit cursor
	 * @index: (none)
	 *
	 * Set the cursor position inside the composing text (as byte
	 * offset) relative to the start of the composing text.
	 *
	 * When index is negative no cursor should be displayed.
	 *
	 * This request should be sent before sending preedit_string
	 * request.
	 */
	void (*preedit_cursor)(struct wl_client *client,
			       struct wl_resource *resource,
			       int32_t index);
	/**
	 * delete_surrounding_text - delete text
	 * @index: (none)
	 * @length: (none)
	 *
	 * This request will be handled on text_input side as part of a
	 * directly following commit_string request.
	 */
	void (*delete_surrounding_text)(struct wl_client *client,
					struct wl_resource *resource,
					int32_t index,
					uint32_t length);
	/**
	 * cursor_position - set cursor to a new position
	 * @index: (none)
	 * @anchor: (none)
	 *
	 * Set the cursor and anchor to a new position. Index is the new
	 * cursor position in bytes (when >= 0 relative to the end of
	 * inserted text else relative to beginning of inserted text).
	 * Anchor is the new anchor position in bytes (when >= 0 relative
	 * to the end of inserted text, else relative to beginning of
	 * inserted text). When there should be no selected text anchor
	 * should be the same as index.
	 *
	 * This request will be handled on text_input side as part of a
	 * directly following commit_string request.
	 */
	void (*cursor_position)(struct wl_client *client,
				struct wl_resource *resource,
				int32_t index,
				int32_t anchor);
	/**
	 * modifiers_map - (none)
	 * @map: (none)
	 */
	void (*modifiers_map)(struct wl_client *client,
			      struct wl_resource *resource,
			      struct wl_array *map);
	/**
	 * keysym - keysym
	 * @serial: serial of the latest known text input state
	 * @time: (none)
	 * @sym: (none)
	 * @state: (none)
	 * @modifiers: (none)
	 *
	 * Notify when a key event was sent. Key events should not be
	 * used for normal text input operations, which should be done with
	 * commit_string, delete_surrounfing_text, etc. The key event
	 * follows the wl_keyboard key event convention. Sym is a XKB
	 * keysym, state a wl_keyboard key_state.
	 */
	void (*keysym)(struct wl_client *client,
		       struct wl_resource *resource,
		       uint32_t serial,
		       uint32_t time,
		       uint32_t sym,
		       uint32_t state,
		       uint32_t modifiers);
	/**
	 * grab_keyboard - grab hardware keyboard
	 * @keyboard: (none)
	 *
	 * Allows an input method to receive hardware keyboard input and
	 * process key events to generate text events (with pre-edit) over
	 * the wire. This allows input methods which compose multiple key
	 * events for inputting text like it is done for CJK languages.
	 */
	void (*grab_keyboard)(struct wl_client *client,
			      struct wl_resource *resource,
			      uint32_t keyboard);
	/**
	 * key - forward key event
	 * @serial: serial from wl_keyboard::key
	 * @time: time from wl_keyboard::key
	 * @key: key from wl_keyboard::key
	 * @state: state from wl_keyboard::key
	 *
	 * Should be used when filtering key events with grab_keyboard.
	 *
	 * When the wl_keyboard::key event is not processed by the input
	 * method itself and should be sent to the client instead, forward
	 * it with this request. The arguments should be the ones from the
	 * wl_keyboard::key event.
	 *
	 * For generating custom key events use the keysym request instead.
	 */
	void (*key)(struct wl_client *client,
		    struct wl_resource *resource,
		    uint32_t serial,
		    uint32_t time,
		    uint32_t key,
		    uint32_t state);
	/**
	 * modifiers - forward modifiers event
	 * @serial: serial from wl_keyboard::modifiers
	 * @mods_depressed: mods_depressed from wl_keyboard::modifiers
	 * @mods_latched: mods_latched from wl_keyboard::modifiers
	 * @mods_locked: mods_locked from wl_keyboard::modifiers
	 * @group: group from wl_keyboard::modifiers
	 *
	 * Should be used when filtering key events with grab_keyboard.
	 *
	 * When the wl_keyboard::modifiers event should be also send to the
	 * client, forward it with this request. The arguments should be
	 * the ones from the wl_keyboard::modifiers event.
	 */
	void (*modifiers)(struct wl_client *client,
			  struct wl_resource *resource,
			  uint32_t serial,
			  uint32_t mods_depressed,
			  uint32_t mods_latched,
			  uint32_t mods_locked,
			  uint32_t group);
	/**
	 * language - (none)
	 * @serial: serial of the latest known text input state
	 * @language: (none)
	 */
	void (*language)(struct wl_client *client,
			 struct wl_resource *resource,
			 uint32_t serial,
			 const char *language);
	/**
	 * text_direction - (none)
	 * @serial: serial of the latest known text input state
	 * @direction: (none)
	 */
	void (*text_direction)(struct wl_client *client,
			       struct wl_resource *resource,
			       uint32_t serial,
			       uint32_t direction);
	/**
	 * selection_region - (none)
	 * @serial: serial of the latest known text input state
	 * @start: (none)
	 * @end: (none)
	 */
	void (*selection_region)(struct wl_client *client,
				 struct wl_resource *resource,
				 uint32_t serial,
				 int32_t start,
				 int32_t end);
	/**
	 * private_command - (none)
	 * @serial: serial of the latest known text input state
	 * @command: (none)
	 */
	void (*private_command)(struct wl_client *client,
				struct wl_resource *resource,
				uint32_t serial,
				const char *command);
	/**
	 * update_input_panel_data - (none)
	 * @serial: serial of the latest known text input state
	 * @input_panel_data: (none)
	 * @input_panel_data_length: (none)
	 */
	void (*update_input_panel_data)(struct wl_client *client,
					struct wl_resource *resource,
					uint32_t serial,
					const char *input_panel_data,
					uint32_t input_panel_data_length);
	/**
	 * hide_input_panel - (none)
	 * @serial: serial of the latest known text input state
	 */
	void (*hide_input_panel)(struct wl_client *client,
				 struct wl_resource *resource,
				 uint32_t serial);
	/**
	 * get_selection_text - (none)
	 * @fd: (none)
	 */
	void (*get_selection_text)(struct wl_client *client,
				   struct wl_resource *resource,
				   int32_t fd);
	/**
	 * get_surrounding_text - (none)
	 * @maxlen_before: (none)
	 * @maxlen_after: (none)
	 * @fd: (none)
	 */
	void (*get_surrounding_text)(struct wl_client *client,
				     struct wl_resource *resource,
				     uint32_t maxlen_before,
				     uint32_t maxlen_after,
				     int32_t fd);
	/**
	 * filter_key_event_done - (none)
	 * @serial: (none)
	 * @state: (none)
	 */
	void (*filter_key_event_done)(struct wl_client *client,
				      struct wl_resource *resource,
				      uint32_t serial,
				      uint32_t state);
};

#define WL_INPUT_METHOD_CONTEXT_RESET	0
#define WL_INPUT_METHOD_CONTEXT_CONTENT_TYPE	1
#define WL_INPUT_METHOD_CONTEXT_INVOKE_ACTION	2
#define WL_INPUT_METHOD_CONTEXT_COMMIT_STATE	3
#define WL_INPUT_METHOD_CONTEXT_PREFERRED_LANGUAGE	4
#define WL_INPUT_METHOD_CONTEXT_RETURN_KEY_TYPE	5
#define WL_INPUT_METHOD_CONTEXT_RETURN_KEY_DISABLED	6
#define WL_INPUT_METHOD_CONTEXT_INPUT_PANEL_DATA	7
#define WL_INPUT_METHOD_CONTEXT_BIDI_DIRECTION	8
#define WL_INPUT_METHOD_CONTEXT_CURSOR_POSITION	9
#define WL_INPUT_METHOD_CONTEXT_PROCESS_INPUT_DEVICE_EVENT	10
#define WL_INPUT_METHOD_CONTEXT_FILTER_KEY_EVENT	11
#define WL_INPUT_METHOD_CONTEXT_CAPITAL_MODE	12

#define WL_INPUT_METHOD_CONTEXT_RESET_SINCE_VERSION	1
#define WL_INPUT_METHOD_CONTEXT_CONTENT_TYPE_SINCE_VERSION	1
#define WL_INPUT_METHOD_CONTEXT_INVOKE_ACTION_SINCE_VERSION	1
#define WL_INPUT_METHOD_CONTEXT_COMMIT_STATE_SINCE_VERSION	1
#define WL_INPUT_METHOD_CONTEXT_PREFERRED_LANGUAGE_SINCE_VERSION	1
#define WL_INPUT_METHOD_CONTEXT_RETURN_KEY_TYPE_SINCE_VERSION	1
#define WL_INPUT_METHOD_CONTEXT_RETURN_KEY_DISABLED_SINCE_VERSION	1
#define WL_INPUT_METHOD_CONTEXT_INPUT_PANEL_DATA_SINCE_VERSION	1
#define WL_INPUT_METHOD_CONTEXT_BIDI_DIRECTION_SINCE_VERSION	1
#define WL_INPUT_METHOD_CONTEXT_CURSOR_POSITION_SINCE_VERSION	1
#define WL_INPUT_METHOD_CONTEXT_PROCESS_INPUT_DEVICE_EVENT_SINCE_VERSION	1
#define WL_INPUT_METHOD_CONTEXT_FILTER_KEY_EVENT_SINCE_VERSION	1
#define WL_INPUT_METHOD_CONTEXT_CAPITAL_MODE_SINCE_VERSION	1

static inline void
wl_input_method_context_send_reset(struct wl_resource *resource_)
{
	wl_resource_post_event(resource_, WL_INPUT_METHOD_CONTEXT_RESET);
}

static inline void
wl_input_method_context_send_content_type(struct wl_resource *resource_, uint32_t hint, uint32_t purpose)
{
	wl_resource_post_event(resource_, WL_INPUT_METHOD_CONTEXT_CONTENT_TYPE, hint, purpose);
}

static inline void
wl_input_method_context_send_invoke_action(struct wl_resource *resource_, uint32_t button, uint32_t index)
{
	wl_resource_post_event(resource_, WL_INPUT_METHOD_CONTEXT_INVOKE_ACTION, button, index);
}

static inline void
wl_input_method_context_send_commit_state(struct wl_resource *resource_, uint32_t serial)
{
	wl_resource_post_event(resource_, WL_INPUT_METHOD_CONTEXT_COMMIT_STATE, serial);
}

static inline void
wl_input_method_context_send_preferred_language(struct wl_resource *resource_, const char *language)
{
	wl_resource_post_event(resource_, WL_INPUT_METHOD_CONTEXT_PREFERRED_LANGUAGE, language);
}

static inline void
wl_input_method_context_send_return_key_type(struct wl_resource *resource_, uint32_t return_key_type)
{
	wl_resource_post_event(resource_, WL_INPUT_METHOD_CONTEXT_RETURN_KEY_TYPE, return_key_type);
}

static inline void
wl_input_method_context_send_return_key_disabled(struct wl_resource *resource_, uint32_t return_key_disabled)
{
	wl_resource_post_event(resource_, WL_INPUT_METHOD_CONTEXT_RETURN_KEY_DISABLED, return_key_disabled);
}

static inline void
wl_input_method_context_send_input_panel_data(struct wl_resource *resource_, const char *input_panel_data, uint32_t input_panel_data_length)
{
	wl_resource_post_event(resource_, WL_INPUT_METHOD_CONTEXT_INPUT_PANEL_DATA, input_panel_data, input_panel_data_length);
}

static inline void
wl_input_method_context_send_bidi_direction(struct wl_resource *resource_, uint32_t direction)
{
	wl_resource_post_event(resource_, WL_INPUT_METHOD_CONTEXT_BIDI_DIRECTION, direction);
}

static inline void
wl_input_method_context_send_cursor_position(struct wl_resource *resource_, uint32_t cursor_position)
{
	wl_resource_post_event(resource_, WL_INPUT_METHOD_CONTEXT_CURSOR_POSITION, cursor_position);
}

static inline void
wl_input_method_context_send_process_input_device_event(struct wl_resource *resource_, uint32_t event_type, const char *event_data, uint32_t event_length)
{
	wl_resource_post_event(resource_, WL_INPUT_METHOD_CONTEXT_PROCESS_INPUT_DEVICE_EVENT, event_type, event_data, event_length);
}

static inline void
wl_input_method_context_send_filter_key_event(struct wl_resource *resource_, uint32_t serial, uint32_t time, const char *keyname, uint32_t state, uint32_t modifiers, const char *dev_name, uint32_t dev_class, uint32_t dev_subclass)
{
	wl_resource_post_event(resource_, WL_INPUT_METHOD_CONTEXT_FILTER_KEY_EVENT, serial, time, keyname, state, modifiers, dev_name, dev_class, dev_subclass);
}

static inline void
wl_input_method_context_send_capital_mode(struct wl_resource *resource_, uint32_t mode)
{
	wl_resource_post_event(resource_, WL_INPUT_METHOD_CONTEXT_CAPITAL_MODE, mode);
}

#define WL_INPUT_METHOD_ACTIVATE	0
#define WL_INPUT_METHOD_DEACTIVATE	1
#define WL_INPUT_METHOD_SHOW_INPUT_PANEL	2
#define WL_INPUT_METHOD_HIDE_INPUT_PANEL	3

#define WL_INPUT_METHOD_ACTIVATE_SINCE_VERSION	1
#define WL_INPUT_METHOD_DEACTIVATE_SINCE_VERSION	1
#define WL_INPUT_METHOD_SHOW_INPUT_PANEL_SINCE_VERSION	1
#define WL_INPUT_METHOD_HIDE_INPUT_PANEL_SINCE_VERSION	1

static inline void
wl_input_method_send_activate(struct wl_resource *resource_, struct wl_resource *id, uint32_t text_input_id)
{
	wl_resource_post_event(resource_, WL_INPUT_METHOD_ACTIVATE, id, text_input_id);
}

static inline void
wl_input_method_send_deactivate(struct wl_resource *resource_, struct wl_resource *context)
{
	wl_resource_post_event(resource_, WL_INPUT_METHOD_DEACTIVATE, context);
}

static inline void
wl_input_method_send_show_input_panel(struct wl_resource *resource_, struct wl_resource *context)
{
	wl_resource_post_event(resource_, WL_INPUT_METHOD_SHOW_INPUT_PANEL, context);
}

static inline void
wl_input_method_send_hide_input_panel(struct wl_resource *resource_, struct wl_resource *context)
{
	wl_resource_post_event(resource_, WL_INPUT_METHOD_HIDE_INPUT_PANEL, context);
}

/**
 * wl_input_panel - interface for implementing keyboards
 * @get_input_panel_surface: (none)
 *
 * Only one client can bind this interface at a time.
 */
struct wl_input_panel_interface {
	/**
	 * get_input_panel_surface - (none)
	 * @id: (none)
	 * @surface: (none)
	 */
	void (*get_input_panel_surface)(struct wl_client *client,
					struct wl_resource *resource,
					uint32_t id,
					struct wl_resource *surface);
};


#ifndef WL_INPUT_PANEL_SURFACE_POSITION_ENUM
#define WL_INPUT_PANEL_SURFACE_POSITION_ENUM
enum wl_input_panel_surface_position {
	WL_INPUT_PANEL_SURFACE_POSITION_CENTER_BOTTOM = 0,
};
#endif /* WL_INPUT_PANEL_SURFACE_POSITION_ENUM */

struct wl_input_panel_surface_interface {
	/**
	 * set_toplevel - set the surface type as a keyboard
	 * @output: (none)
	 * @position: (none)
	 *
	 * A keyboard surface is only shown, when a text model is active.
	 */
	void (*set_toplevel)(struct wl_client *client,
			     struct wl_resource *resource,
			     struct wl_resource *output,
			     uint32_t position);
	/**
	 * set_overlay_panel - set the surface type as an overlay panel
	 *
	 * An overlay panel is shown near the input cursor above the
	 * application window when a text model is active.
	 */
	void (*set_overlay_panel)(struct wl_client *client,
				  struct wl_resource *resource);
	/**
	 * set_ready - set the current state of the keyboard surface as a
	 *	ready state
	 * @state: (none)
	 *
	 * A keyboard surface needs to be shown after rendering the first
	 * frame is completed. Otherwise, the keyboard surface that
	 * contains inappropriate layout will be displayed.
	 */
	void (*set_ready)(struct wl_client *client,
			  struct wl_resource *resource,
			  uint32_t state);
};


#ifdef  __cplusplus
}
#endif

#endif
