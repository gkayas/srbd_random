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

#ifndef TEXT_CLIENT_PROTOCOL_H
#define TEXT_CLIENT_PROTOCOL_H

#ifdef  __cplusplus
extern "C" {
#endif

#include <stdint.h>
#include <stddef.h>
#include "wayland-client.h"

struct wl_client;
struct wl_resource;

struct wl_seat;
struct wl_surface;
struct wl_text_input;
struct wl_text_input_manager;

extern const struct wl_interface wl_text_input_interface;
extern const struct wl_interface wl_text_input_manager_interface;

#ifndef WL_TEXT_INPUT_CONTENT_HINT_ENUM
#define WL_TEXT_INPUT_CONTENT_HINT_ENUM
/**
 * wl_text_input_content_hint - content hint
 * @WL_TEXT_INPUT_CONTENT_HINT_NONE: no special behaviour
 * @WL_TEXT_INPUT_CONTENT_HINT_DEFAULT: auto completion, correction and
 *	capitalization
 * @WL_TEXT_INPUT_CONTENT_HINT_PASSWORD: hidden and sensitive text
 * @WL_TEXT_INPUT_CONTENT_HINT_AUTO_COMPLETION: suggest word completions
 * @WL_TEXT_INPUT_CONTENT_HINT_AUTO_CORRECTION: suggest word corrections
 * @WL_TEXT_INPUT_CONTENT_HINT_AUTO_CAPITALIZATION: switch to uppercase
 *	letters at the start of a sentence
 * @WL_TEXT_INPUT_CONTENT_HINT_LOWERCASE: prefer lowercase letters
 * @WL_TEXT_INPUT_CONTENT_HINT_UPPERCASE: prefer uppercase letters
 * @WL_TEXT_INPUT_CONTENT_HINT_TITLECASE: prefer casing for titles and
 *	headings (can be language dependent)
 * @WL_TEXT_INPUT_CONTENT_HINT_HIDDEN_TEXT: characters should be hidden
 * @WL_TEXT_INPUT_CONTENT_HINT_SENSITIVE_DATA: typed text should not be
 *	stored
 * @WL_TEXT_INPUT_CONTENT_HINT_LATIN: just latin characters should be
 *	entered
 * @WL_TEXT_INPUT_CONTENT_HINT_MULTILINE: the text input is multiline
 * @WL_TEXT_INPUT_CONTENT_HINT_WORD_CAPITALIZATION: switch to uppercase
 *	letters at the start of a word
 *
 * Content hint is a bitmask to allow to modify the behavior of the text
 * input.
 */
enum wl_text_input_content_hint {
	WL_TEXT_INPUT_CONTENT_HINT_NONE = 0x0,
	WL_TEXT_INPUT_CONTENT_HINT_DEFAULT = 0x7,
	WL_TEXT_INPUT_CONTENT_HINT_PASSWORD = 0xc0,
	WL_TEXT_INPUT_CONTENT_HINT_AUTO_COMPLETION = 0x1,
	WL_TEXT_INPUT_CONTENT_HINT_AUTO_CORRECTION = 0x2,
	WL_TEXT_INPUT_CONTENT_HINT_AUTO_CAPITALIZATION = 0x4,
	WL_TEXT_INPUT_CONTENT_HINT_LOWERCASE = 0x8,
	WL_TEXT_INPUT_CONTENT_HINT_UPPERCASE = 0x10,
	WL_TEXT_INPUT_CONTENT_HINT_TITLECASE = 0x20,
	WL_TEXT_INPUT_CONTENT_HINT_HIDDEN_TEXT = 0x40,
	WL_TEXT_INPUT_CONTENT_HINT_SENSITIVE_DATA = 0x80,
	WL_TEXT_INPUT_CONTENT_HINT_LATIN = 0x100,
	WL_TEXT_INPUT_CONTENT_HINT_MULTILINE = 0x200,
	WL_TEXT_INPUT_CONTENT_HINT_WORD_CAPITALIZATION = 0x400,
};
#endif /* WL_TEXT_INPUT_CONTENT_HINT_ENUM */

#ifndef WL_TEXT_INPUT_CONTENT_PURPOSE_ENUM
#define WL_TEXT_INPUT_CONTENT_PURPOSE_ENUM
/**
 * wl_text_input_content_purpose - content purpose
 * @WL_TEXT_INPUT_CONTENT_PURPOSE_NORMAL: default input, allowing all
 *	characters
 * @WL_TEXT_INPUT_CONTENT_PURPOSE_ALPHA: allow only alphabetic characters
 * @WL_TEXT_INPUT_CONTENT_PURPOSE_DIGITS: allow only digits
 * @WL_TEXT_INPUT_CONTENT_PURPOSE_NUMBER: input a number (including
 *	decimal separator and sign)
 * @WL_TEXT_INPUT_CONTENT_PURPOSE_PHONE: input a phone number
 * @WL_TEXT_INPUT_CONTENT_PURPOSE_URL: input an URL
 * @WL_TEXT_INPUT_CONTENT_PURPOSE_EMAIL: input an email address
 * @WL_TEXT_INPUT_CONTENT_PURPOSE_NAME: input a name of a person
 * @WL_TEXT_INPUT_CONTENT_PURPOSE_PASSWORD: input a password (combine
 *	with password or sensitive_data hint)
 * @WL_TEXT_INPUT_CONTENT_PURPOSE_DATE: input a date
 * @WL_TEXT_INPUT_CONTENT_PURPOSE_TIME: input a time
 * @WL_TEXT_INPUT_CONTENT_PURPOSE_DATETIME: input a date and time
 * @WL_TEXT_INPUT_CONTENT_PURPOSE_TERMINAL: input for a terminal
 * @WL_TEXT_INPUT_CONTENT_PURPOSE_IP: input for a IP (number and a-f for
 *	Ipv6)
 * @WL_TEXT_INPUT_CONTENT_PURPOSE_EMOTICON: input for an emoticon
 * @WL_TEXT_INPUT_CONTENT_PURPOSE_DIGITS_SIGNED: allow digits and
 *	negative sign
 * @WL_TEXT_INPUT_CONTENT_PURPOSE_DIGITS_DECIMAL: allow digits and
 *	decimal point
 * @WL_TEXT_INPUT_CONTENT_PURPOSE_DIGITS_SIGNEDDECIMAL: allow digits,
 *	negative sign and decimal point
 * @WL_TEXT_INPUT_CONTENT_PURPOSE_PASSWORD_DIGITS: input a password with
 *	only digits
 * @WL_TEXT_INPUT_CONTENT_PURPOSE_FILENAME: default input for the name of
 *	a file (symbols such as '/' should be disabled)
 * @WL_TEXT_INPUT_CONTENT_PURPOSE_HEX: input for a hexadecimal
 *
 * The content purpose allows to specify the primary purpose of a text
 * input.
 *
 * This allows an input method to show special purpose input panels with
 * extra characters or to disallow some characters.
 */
enum wl_text_input_content_purpose {
	WL_TEXT_INPUT_CONTENT_PURPOSE_NORMAL = 0,
	WL_TEXT_INPUT_CONTENT_PURPOSE_ALPHA = 1,
	WL_TEXT_INPUT_CONTENT_PURPOSE_DIGITS = 2,
	WL_TEXT_INPUT_CONTENT_PURPOSE_NUMBER = 3,
	WL_TEXT_INPUT_CONTENT_PURPOSE_PHONE = 4,
	WL_TEXT_INPUT_CONTENT_PURPOSE_URL = 5,
	WL_TEXT_INPUT_CONTENT_PURPOSE_EMAIL = 6,
	WL_TEXT_INPUT_CONTENT_PURPOSE_NAME = 7,
	WL_TEXT_INPUT_CONTENT_PURPOSE_PASSWORD = 8,
	WL_TEXT_INPUT_CONTENT_PURPOSE_DATE = 9,
	WL_TEXT_INPUT_CONTENT_PURPOSE_TIME = 10,
	WL_TEXT_INPUT_CONTENT_PURPOSE_DATETIME = 11,
	WL_TEXT_INPUT_CONTENT_PURPOSE_TERMINAL = 12,
	WL_TEXT_INPUT_CONTENT_PURPOSE_IP = 13,
	WL_TEXT_INPUT_CONTENT_PURPOSE_EMOTICON = 14,
	WL_TEXT_INPUT_CONTENT_PURPOSE_DIGITS_SIGNED = 15,
	WL_TEXT_INPUT_CONTENT_PURPOSE_DIGITS_DECIMAL = 16,
	WL_TEXT_INPUT_CONTENT_PURPOSE_DIGITS_SIGNEDDECIMAL = 17,
	WL_TEXT_INPUT_CONTENT_PURPOSE_PASSWORD_DIGITS = 18,
	WL_TEXT_INPUT_CONTENT_PURPOSE_FILENAME = 19,
	WL_TEXT_INPUT_CONTENT_PURPOSE_HEX = 20,
};
#endif /* WL_TEXT_INPUT_CONTENT_PURPOSE_ENUM */

#ifndef WL_TEXT_INPUT_RETURN_KEY_TYPE_ENUM
#define WL_TEXT_INPUT_RETURN_KEY_TYPE_ENUM
/**
 * wl_text_input_return_key_type - return key type
 * @WL_TEXT_INPUT_RETURN_KEY_TYPE_DEFAULT: default
 * @WL_TEXT_INPUT_RETURN_KEY_TYPE_DONE: done
 * @WL_TEXT_INPUT_RETURN_KEY_TYPE_GO: go
 * @WL_TEXT_INPUT_RETURN_KEY_TYPE_JOIN: join
 * @WL_TEXT_INPUT_RETURN_KEY_TYPE_LOGIN: login
 * @WL_TEXT_INPUT_RETURN_KEY_TYPE_NEXT: next
 * @WL_TEXT_INPUT_RETURN_KEY_TYPE_SEARCH: search
 * @WL_TEXT_INPUT_RETURN_KEY_TYPE_SEND: send
 *
 * The return key type allows to specify the return key on the input
 * panel.
 */
enum wl_text_input_return_key_type {
	WL_TEXT_INPUT_RETURN_KEY_TYPE_DEFAULT = 0,
	WL_TEXT_INPUT_RETURN_KEY_TYPE_DONE = 1,
	WL_TEXT_INPUT_RETURN_KEY_TYPE_GO = 2,
	WL_TEXT_INPUT_RETURN_KEY_TYPE_JOIN = 3,
	WL_TEXT_INPUT_RETURN_KEY_TYPE_LOGIN = 4,
	WL_TEXT_INPUT_RETURN_KEY_TYPE_NEXT = 5,
	WL_TEXT_INPUT_RETURN_KEY_TYPE_SEARCH = 6,
	WL_TEXT_INPUT_RETURN_KEY_TYPE_SEND = 7,
};
#endif /* WL_TEXT_INPUT_RETURN_KEY_TYPE_ENUM */

#ifndef WL_TEXT_INPUT_INPUT_PANEL_STATE_ENUM
#define WL_TEXT_INPUT_INPUT_PANEL_STATE_ENUM
enum wl_text_input_input_panel_state {
	WL_TEXT_INPUT_INPUT_PANEL_STATE_HIDE = 0,
	WL_TEXT_INPUT_INPUT_PANEL_STATE_SHOW = 1,
};
#endif /* WL_TEXT_INPUT_INPUT_PANEL_STATE_ENUM */

#ifndef WL_TEXT_INPUT_PREEDIT_STYLE_ENUM
#define WL_TEXT_INPUT_PREEDIT_STYLE_ENUM
enum wl_text_input_preedit_style {
	WL_TEXT_INPUT_PREEDIT_STYLE_DEFAULT = 0,
	WL_TEXT_INPUT_PREEDIT_STYLE_NONE = 1,
	WL_TEXT_INPUT_PREEDIT_STYLE_ACTIVE = 2,
	WL_TEXT_INPUT_PREEDIT_STYLE_INACTIVE = 3,
	WL_TEXT_INPUT_PREEDIT_STYLE_HIGHLIGHT = 4,
	WL_TEXT_INPUT_PREEDIT_STYLE_UNDERLINE = 5,
	WL_TEXT_INPUT_PREEDIT_STYLE_SELECTION = 6,
	WL_TEXT_INPUT_PREEDIT_STYLE_INCORRECT = 7,
};
#endif /* WL_TEXT_INPUT_PREEDIT_STYLE_ENUM */

#ifndef WL_TEXT_INPUT_TEXT_DIRECTION_ENUM
#define WL_TEXT_INPUT_TEXT_DIRECTION_ENUM
enum wl_text_input_text_direction {
	WL_TEXT_INPUT_TEXT_DIRECTION_AUTO = 0,
	WL_TEXT_INPUT_TEXT_DIRECTION_LTR = 1,
	WL_TEXT_INPUT_TEXT_DIRECTION_RTL = 2,
};
#endif /* WL_TEXT_INPUT_TEXT_DIRECTION_ENUM */

/**
 * wl_text_input - text input
 * @enter: enter event
 * @leave: leave event
 * @modifiers_map: modifiers map
 * @input_panel_state: state of the input panel
 * @preedit_string: pre-edit
 * @preedit_styling: pre-edit styling
 * @preedit_cursor: pre-edit cursor
 * @commit_string: commit
 * @cursor_position: set cursor to new position
 * @delete_surrounding_text: delete surrounding text
 * @keysym: keysym
 * @language: language
 * @text_direction: text direction
 * @selection_region: selection region
 * @private_command: private command
 * @input_panel_geometry: geometry of the input panel
 * @input_panel_data: input panel data
 * @get_selection_text: (none)
 * @get_surrounding_text: (none)
 * @filter_key_event_done: (none)
 * @reset_done: (none)
 *
 * An object used for text input. Adds support for text input and input
 * methods to applications. A text-input object is created from a
 * wl_text_input_manager and corresponds typically to a text entry in an
 * application. Requests are used to activate/deactivate the text-input
 * object and set state information like surrounding and selected text or
 * the content type. The information about entered text is sent to the
 * text-input object via the pre-edit and commit events. Using this
 * interface removes the need for applications to directly process hardware
 * key events and compose text out of them.
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
struct wl_text_input_listener {
	/**
	 * enter - enter event
	 * @surface: (none)
	 *
	 * Notify the text-input object when it received focus. Typically
	 * in response to an activate request.
	 */
	void (*enter)(void *data,
		      struct wl_text_input *wl_text_input,
		      struct wl_surface *surface);
	/**
	 * leave - leave event
	 *
	 * Notify the text-input object when it lost focus. Either in
	 * response to a deactivate request or when the assigned surface
	 * lost focus or was destroyed.
	 */
	void (*leave)(void *data,
		      struct wl_text_input *wl_text_input);
	/**
	 * modifiers_map - modifiers map
	 * @map: (none)
	 *
	 * Transfer an array of 0-terminated modifiers names. The
	 * position in the array is the index of the modifier as used in
	 * the modifiers bitmask in the keysym event.
	 */
	void (*modifiers_map)(void *data,
			      struct wl_text_input *wl_text_input,
			      struct wl_array *map);
	/**
	 * input_panel_state - state of the input panel
	 * @state: (none)
	 *
	 * Notify when the visibility state of the input panel changed.
	 */
	void (*input_panel_state)(void *data,
				  struct wl_text_input *wl_text_input,
				  uint32_t state);
	/**
	 * preedit_string - pre-edit
	 * @serial: serial of the latest known text input state
	 * @text: (none)
	 * @commit: (none)
	 *
	 * Notify when a new composing text (pre-edit) should be set
	 * around the current cursor position. Any previously set composing
	 * text should be removed.
	 *
	 * The commit text can be used to replace the preedit text on reset
	 * (for example on unfocus).
	 *
	 * The text input should also handle all preedit_style and
	 * preedit_cursor events occurring directly before preedit_string.
	 */
	void (*preedit_string)(void *data,
			       struct wl_text_input *wl_text_input,
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
	 * applied for length bytes from index relative to the beginning of
	 * the composing text (as byte offset). Multiple styles can be
	 * applied to a composing text by sending multiple preedit_styling
	 * events.
	 *
	 * This event is handled as part of a following preedit_string
	 * event.
	 */
	void (*preedit_styling)(void *data,
				struct wl_text_input *wl_text_input,
				uint32_t index,
				uint32_t length,
				uint32_t style);
	/**
	 * preedit_cursor - pre-edit cursor
	 * @index: (none)
	 *
	 * Set the cursor position inside the composing text (as byte
	 * offset) relative to the start of the composing text. When index
	 * is a negative number no cursor is shown.
	 *
	 * This event is handled as part of a following preedit_string
	 * event.
	 */
	void (*preedit_cursor)(void *data,
			       struct wl_text_input *wl_text_input,
			       int32_t index);
	/**
	 * commit_string - commit
	 * @serial: serial of the latest known text input state
	 * @text: (none)
	 *
	 * Notify when text should be inserted into the editor widget.
	 * The text to commit could be either just a single character after
	 * a key press or the result of some composing (pre-edit). It could
	 * be also an empty text when some text should be removed (see
	 * delete_surrounding_text) or when the input cursor should be
	 * moved (see cursor_position).
	 *
	 * Any previously set composing text should be removed.
	 */
	void (*commit_string)(void *data,
			      struct wl_text_input *wl_text_input,
			      uint32_t serial,
			      const char *text);
	/**
	 * cursor_position - set cursor to new position
	 * @index: (none)
	 * @anchor: (none)
	 *
	 * Notify when the cursor or anchor position should be modified.
	 *
	 * This event should be handled as part of a following
	 * commit_string event.
	 */
	void (*cursor_position)(void *data,
				struct wl_text_input *wl_text_input,
				int32_t index,
				int32_t anchor);
	/**
	 * delete_surrounding_text - delete surrounding text
	 * @index: (none)
	 * @length: (none)
	 *
	 * Notify when the text around the current cursor position should
	 * be deleted.
	 *
	 * Index is relative to the current cursor (in bytes). Length is
	 * the length of deleted text (in bytes).
	 *
	 * This event should be handled as part of a following
	 * commit_string event.
	 */
	void (*delete_surrounding_text)(void *data,
					struct wl_text_input *wl_text_input,
					int32_t index,
					uint32_t length);
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
	 * commit_string, delete_surrounding_text, etc. The key event
	 * follows the wl_keyboard key event convention. Sym is a XKB
	 * keysym, state a wl_keyboard key_state. Modifiers are a mask for
	 * effective modifiers (where the modifier indices are set by the
	 * modifiers_map event)
	 */
	void (*keysym)(void *data,
		       struct wl_text_input *wl_text_input,
		       uint32_t serial,
		       uint32_t time,
		       uint32_t sym,
		       uint32_t state,
		       uint32_t modifiers);
	/**
	 * language - language
	 * @serial: serial of the latest known text input state
	 * @language: (none)
	 *
	 * Set the language of the input text. The "language" argument is
	 * a RFC-3066 format language tag.
	 */
	void (*language)(void *data,
			 struct wl_text_input *wl_text_input,
			 uint32_t serial,
			 const char *language);
	/**
	 * text_direction - text direction
	 * @serial: serial of the latest known text input state
	 * @direction: (none)
	 *
	 * Set the text direction of input text.
	 *
	 * It is mainly needed for showing input cursor on correct side of
	 * the editor when there is no input yet done and making sure
	 * neutral direction text is laid out properly.
	 */
	void (*text_direction)(void *data,
			       struct wl_text_input *wl_text_input,
			       uint32_t serial,
			       uint32_t direction);
	/**
	 * selection_region - selection region
	 * @serial: serial of the latest known text input state
	 * @start: (none)
	 * @end: (none)
	 *
	 * Notify when the input panels ask to select the characters from
	 * the start cursor position to the end cursor position.
	 */
	void (*selection_region)(void *data,
				 struct wl_text_input *wl_text_input,
				 uint32_t serial,
				 int32_t start,
				 int32_t end);
	/**
	 * private_command - private command
	 * @serial: serial of the latest known text input state
	 * @command: (none)
	 *
	 * Notify when the input panels ask to send private command
	 */
	void (*private_command)(void *data,
				struct wl_text_input *wl_text_input,
				uint32_t serial,
				const char *command);
	/**
	 * input_panel_geometry - geometry of the input panel
	 * @x: (none)
	 * @y: (none)
	 * @width: (none)
	 * @height: (none)
	 *
	 * Notify when the geometry of the input panel changed.
	 */
	void (*input_panel_geometry)(void *data,
				     struct wl_text_input *wl_text_input,
				     uint32_t x,
				     uint32_t y,
				     uint32_t width,
				     uint32_t height);
	/**
	 * input_panel_data - input panel data
	 * @serial: serial of the latest known text input state
	 * @input_panel_data: (none)
	 * @input_panel_data_length: (none)
	 *
	 * Notify when the input panels ask to send input panel data
	 */
	void (*input_panel_data)(void *data,
				 struct wl_text_input *wl_text_input,
				 uint32_t serial,
				 const char *input_panel_data,
				 uint32_t input_panel_data_length);
	/**
	 * get_selection_text - (none)
	 * @fd: (none)
	 */
	void (*get_selection_text)(void *data,
				   struct wl_text_input *wl_text_input,
				   int32_t fd);
	/**
	 * get_surrounding_text - (none)
	 * @maxlen_before: (none)
	 * @maxlen_after: (none)
	 * @fd: (none)
	 */
	void (*get_surrounding_text)(void *data,
				     struct wl_text_input *wl_text_input,
				     uint32_t maxlen_before,
				     uint32_t maxlen_after,
				     int32_t fd);
	/**
	 * filter_key_event_done - (none)
	 * @serial: (none)
	 * @state: (none)
	 */
	void (*filter_key_event_done)(void *data,
				      struct wl_text_input *wl_text_input,
				      uint32_t serial,
				      uint32_t state);
	/**
	 * reset_done - (none)
	 * @serial: (none)
	 */
	void (*reset_done)(void *data,
			   struct wl_text_input *wl_text_input,
			   uint32_t serial);
};

static inline int
wl_text_input_add_listener(struct wl_text_input *wl_text_input,
			   const struct wl_text_input_listener *listener, void *data)
{
	return wl_proxy_add_listener((struct wl_proxy *) wl_text_input,
				     (void (**)(void)) listener, data);
}

#define WL_TEXT_INPUT_ACTIVATE	0
#define WL_TEXT_INPUT_DEACTIVATE	1
#define WL_TEXT_INPUT_SHOW_INPUT_PANEL	2
#define WL_TEXT_INPUT_HIDE_INPUT_PANEL	3
#define WL_TEXT_INPUT_RESET	4
#define WL_TEXT_INPUT_SET_CONTENT_TYPE	5
#define WL_TEXT_INPUT_SET_CURSOR_RECTANGLE	6
#define WL_TEXT_INPUT_SET_PREFERRED_LANGUAGE	7
#define WL_TEXT_INPUT_COMMIT_STATE	8
#define WL_TEXT_INPUT_INVOKE_ACTION	9
#define WL_TEXT_INPUT_SET_RETURN_KEY_TYPE	10
#define WL_TEXT_INPUT_SET_RETURN_KEY_DISABLED	11
#define WL_TEXT_INPUT_SET_INPUT_PANEL_DATA	12
#define WL_TEXT_INPUT_BIDI_DIRECTION	13
#define WL_TEXT_INPUT_SET_CURSOR_POSITION	14
#define WL_TEXT_INPUT_PROCESS_INPUT_DEVICE_EVENT	15
#define WL_TEXT_INPUT_FILTER_KEY_EVENT	16
#define WL_TEXT_INPUT_RESET_SYNC	17

#define WL_TEXT_INPUT_ACTIVATE_SINCE_VERSION	1
#define WL_TEXT_INPUT_DEACTIVATE_SINCE_VERSION	1
#define WL_TEXT_INPUT_SHOW_INPUT_PANEL_SINCE_VERSION	1
#define WL_TEXT_INPUT_HIDE_INPUT_PANEL_SINCE_VERSION	1
#define WL_TEXT_INPUT_RESET_SINCE_VERSION	1
#define WL_TEXT_INPUT_SET_CONTENT_TYPE_SINCE_VERSION	1
#define WL_TEXT_INPUT_SET_CURSOR_RECTANGLE_SINCE_VERSION	1
#define WL_TEXT_INPUT_SET_PREFERRED_LANGUAGE_SINCE_VERSION	1
#define WL_TEXT_INPUT_COMMIT_STATE_SINCE_VERSION	1
#define WL_TEXT_INPUT_INVOKE_ACTION_SINCE_VERSION	1
#define WL_TEXT_INPUT_SET_RETURN_KEY_TYPE_SINCE_VERSION	1
#define WL_TEXT_INPUT_SET_RETURN_KEY_DISABLED_SINCE_VERSION	1
#define WL_TEXT_INPUT_SET_INPUT_PANEL_DATA_SINCE_VERSION	1
#define WL_TEXT_INPUT_BIDI_DIRECTION_SINCE_VERSION	1
#define WL_TEXT_INPUT_SET_CURSOR_POSITION_SINCE_VERSION	1
#define WL_TEXT_INPUT_PROCESS_INPUT_DEVICE_EVENT_SINCE_VERSION	1
#define WL_TEXT_INPUT_FILTER_KEY_EVENT_SINCE_VERSION	1
#define WL_TEXT_INPUT_RESET_SYNC_SINCE_VERSION	1

static inline void
wl_text_input_set_user_data(struct wl_text_input *wl_text_input, void *user_data)
{
	wl_proxy_set_user_data((struct wl_proxy *) wl_text_input, user_data);
}

static inline void *
wl_text_input_get_user_data(struct wl_text_input *wl_text_input)
{
	return wl_proxy_get_user_data((struct wl_proxy *) wl_text_input);
}

static inline uint32_t
wl_text_input_get_version(struct wl_text_input *wl_text_input)
{
	return wl_proxy_get_version((struct wl_proxy *) wl_text_input);
}

static inline void
wl_text_input_destroy(struct wl_text_input *wl_text_input)
{
	wl_proxy_destroy((struct wl_proxy *) wl_text_input);
}

static inline void
wl_text_input_activate(struct wl_text_input *wl_text_input, struct wl_seat *seat, struct wl_surface *surface)
{
	wl_proxy_marshal((struct wl_proxy *) wl_text_input,
			 WL_TEXT_INPUT_ACTIVATE, seat, surface);
}

static inline void
wl_text_input_deactivate(struct wl_text_input *wl_text_input, struct wl_seat *seat)
{
	wl_proxy_marshal((struct wl_proxy *) wl_text_input,
			 WL_TEXT_INPUT_DEACTIVATE, seat);
}

static inline void
wl_text_input_show_input_panel(struct wl_text_input *wl_text_input)
{
	wl_proxy_marshal((struct wl_proxy *) wl_text_input,
			 WL_TEXT_INPUT_SHOW_INPUT_PANEL);
}

static inline void
wl_text_input_hide_input_panel(struct wl_text_input *wl_text_input)
{
	wl_proxy_marshal((struct wl_proxy *) wl_text_input,
			 WL_TEXT_INPUT_HIDE_INPUT_PANEL);
}

static inline void
wl_text_input_reset(struct wl_text_input *wl_text_input)
{
	wl_proxy_marshal((struct wl_proxy *) wl_text_input,
			 WL_TEXT_INPUT_RESET);
}

static inline void
wl_text_input_set_content_type(struct wl_text_input *wl_text_input, uint32_t hint, uint32_t purpose)
{
	wl_proxy_marshal((struct wl_proxy *) wl_text_input,
			 WL_TEXT_INPUT_SET_CONTENT_TYPE, hint, purpose);
}

static inline void
wl_text_input_set_cursor_rectangle(struct wl_text_input *wl_text_input, int32_t x, int32_t y, int32_t width, int32_t height)
{
	wl_proxy_marshal((struct wl_proxy *) wl_text_input,
			 WL_TEXT_INPUT_SET_CURSOR_RECTANGLE, x, y, width, height);
}

static inline void
wl_text_input_set_preferred_language(struct wl_text_input *wl_text_input, const char *language)
{
	wl_proxy_marshal((struct wl_proxy *) wl_text_input,
			 WL_TEXT_INPUT_SET_PREFERRED_LANGUAGE, language);
}

static inline void
wl_text_input_commit_state(struct wl_text_input *wl_text_input, uint32_t serial)
{
	wl_proxy_marshal((struct wl_proxy *) wl_text_input,
			 WL_TEXT_INPUT_COMMIT_STATE, serial);
}

static inline void
wl_text_input_invoke_action(struct wl_text_input *wl_text_input, uint32_t button, uint32_t index)
{
	wl_proxy_marshal((struct wl_proxy *) wl_text_input,
			 WL_TEXT_INPUT_INVOKE_ACTION, button, index);
}

static inline void
wl_text_input_set_return_key_type(struct wl_text_input *wl_text_input, uint32_t return_key_type)
{
	wl_proxy_marshal((struct wl_proxy *) wl_text_input,
			 WL_TEXT_INPUT_SET_RETURN_KEY_TYPE, return_key_type);
}

static inline void
wl_text_input_set_return_key_disabled(struct wl_text_input *wl_text_input, uint32_t return_key_disabled)
{
	wl_proxy_marshal((struct wl_proxy *) wl_text_input,
			 WL_TEXT_INPUT_SET_RETURN_KEY_DISABLED, return_key_disabled);
}

static inline void
wl_text_input_set_input_panel_data(struct wl_text_input *wl_text_input, const char *input_panel_data, uint32_t input_panel_length)
{
	wl_proxy_marshal((struct wl_proxy *) wl_text_input,
			 WL_TEXT_INPUT_SET_INPUT_PANEL_DATA, input_panel_data, input_panel_length);
}

static inline void
wl_text_input_bidi_direction(struct wl_text_input *wl_text_input, uint32_t direction)
{
	wl_proxy_marshal((struct wl_proxy *) wl_text_input,
			 WL_TEXT_INPUT_BIDI_DIRECTION, direction);
}

static inline void
wl_text_input_set_cursor_position(struct wl_text_input *wl_text_input, uint32_t cursor_position)
{
	wl_proxy_marshal((struct wl_proxy *) wl_text_input,
			 WL_TEXT_INPUT_SET_CURSOR_POSITION, cursor_position);
}

static inline void
wl_text_input_process_input_device_event(struct wl_text_input *wl_text_input, uint32_t event_type, const char *event_data, uint32_t event_length)
{
	wl_proxy_marshal((struct wl_proxy *) wl_text_input,
			 WL_TEXT_INPUT_PROCESS_INPUT_DEVICE_EVENT, event_type, event_data, event_length);
}

static inline void
wl_text_input_filter_key_event(struct wl_text_input *wl_text_input, uint32_t serial, uint32_t time, const char *keyname, uint32_t state, uint32_t modifiers)
{
	wl_proxy_marshal((struct wl_proxy *) wl_text_input,
			 WL_TEXT_INPUT_FILTER_KEY_EVENT, serial, time, keyname, state, modifiers);
}

static inline void
wl_text_input_reset_sync(struct wl_text_input *wl_text_input, uint32_t serial)
{
	wl_proxy_marshal((struct wl_proxy *) wl_text_input,
			 WL_TEXT_INPUT_RESET_SYNC, serial);
}

#define WL_TEXT_INPUT_MANAGER_CREATE_TEXT_INPUT	0

#define WL_TEXT_INPUT_MANAGER_CREATE_TEXT_INPUT_SINCE_VERSION	1

static inline void
wl_text_input_manager_set_user_data(struct wl_text_input_manager *wl_text_input_manager, void *user_data)
{
	wl_proxy_set_user_data((struct wl_proxy *) wl_text_input_manager, user_data);
}

static inline void *
wl_text_input_manager_get_user_data(struct wl_text_input_manager *wl_text_input_manager)
{
	return wl_proxy_get_user_data((struct wl_proxy *) wl_text_input_manager);
}

static inline uint32_t
wl_text_input_manager_get_version(struct wl_text_input_manager *wl_text_input_manager)
{
	return wl_proxy_get_version((struct wl_proxy *) wl_text_input_manager);
}

static inline void
wl_text_input_manager_destroy(struct wl_text_input_manager *wl_text_input_manager)
{
	wl_proxy_destroy((struct wl_proxy *) wl_text_input_manager);
}

static inline struct wl_text_input *
wl_text_input_manager_create_text_input(struct wl_text_input_manager *wl_text_input_manager)
{
	struct wl_proxy *id;

	id = wl_proxy_marshal_constructor((struct wl_proxy *) wl_text_input_manager,
			 WL_TEXT_INPUT_MANAGER_CREATE_TEXT_INPUT, &wl_text_input_interface, NULL);

	return (struct wl_text_input *) id;
}

#ifdef  __cplusplus
}
#endif

#endif
