/*
    Copyright (C) 2016 Samsung Electronics

    This library is free software; you can redistribute it and/or
    modify it under the terms of the GNU Library General Public
    License as published by the Free Software Foundation; either
    version 2 of the License, or (at your option) any later version.

    This library is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
    Library General Public License for more details.

    You should have received a copy of the GNU Library General Public License
    along with this library; see the file COPYING.LIB.  If not, write to
    the Free Software Foundation, Inc., 51 Franklin Street, Fifth Floor,
    Boston, MA 02110-1301, USA.
*/

/**
 * @file    ewk_main_internal.h
 * @brief   The general initialization of Chromium-efl,
 *          not tied to any view object.
 */

#ifndef ewk_main_internal_h
#define ewk_main_internal_h

#include "ewk_main.h"

#ifdef __cplusplus
extern "C" {
#endif

/**
 * Set argument count and argument vector.
 *
 * Must be called before the following APIs:\n
 * ewk_view_add\n
 * ewk_view_add_in_incognito_mode\n
 * ewk_view_add_with_context\n
 * ewk_view_add_with_session_data\n
 * ewk_context_default_get\n
 * ewk_context_new\n
 * ewk_context_new_with_injected_bundle_path
 *
 * This API allows passing application arguments to the engine.
 * Also there is possible to add custom parameters. However,
 * the engine expects *original* application arguments
 * to remain unchanged. Passing copy of them using strdup and friends
 * is prohibited. The recommended way to call this API is:
 *
 * int main(int argc, char* argv[]) {
 *   ewk_set_arguments(argc, argv);
 *   ...
 * }
 *
 * or
 *
 * int main(int argc, char* argv[]) {
 *     char* arg_options[] = {
 *       argv[0],
 *       "my_custom_param_1",
 *       "my_custom_param_2",
 *     };
 *     int arg_cnt = sizeof(arg_options) / sizeof(arg_options[0]);
 *     ewk_set_arguments(arg_cnt, arg_options);
 * }
 *
 * @note Calling the function for the second time has no user-visible effect.
 *
 * @param argc argument count
 * @param argv argument array
 */
EXPORT_API void ewk_set_arguments(int argc, char** argv);

/**
* Set home directory.
*
* If new path is NULL or empty string, home directory is considered as not set.
*
*/
EXPORT_API void ewk_home_directory_set(const char* path);

#ifdef __cplusplus
}
#endif
#endif // ewk_main_internal_h
