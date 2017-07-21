/*
 * Copyright (C) 2016 Samsung Electronics
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Library General Public
 * License as published by the Free Software Foundation; either
 * version 2 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Library General Public License for more details.
 *
 * You should have received a copy of the GNU Library General Public License
 * along with this program; see the file COPYING.LIB.  If not, write to
 * the Free Software Foundation, Inc., 51 Franklin Street, Fifth Floor,
 * Boston, MA 02110-1301, USA.
 */

/**
 * @file    ewk_cookie_manager_internal.h
 * @brief   Describes the Ewk Cookie Manager API.
 */

#ifndef ewk_cookie_manager_internal_h
#define ewk_cookie_manager_internal_h

#include "ewk_cookie_manager.h"
#include "ewk_error.h"

#ifdef __cplusplus
extern "C" {
#endif

/**
 * \enum    Ewk_Cookie_Persistent_Storage
 *
 * @brief   Enum values to denote cookies persistent storage type.
 */
enum Ewk_Cookie_Persistent_Storage {
    /// Cookies are stored in a text file in the Mozilla "cookies.txt" format.
    EWK_COOKIE_PERSISTENT_STORAGE_TEXT,
    /// Cookies are stored in a SQLite file in the current Mozilla format.
    EWK_COOKIE_PERSISTENT_STORAGE_SQLITE
};

/// Creates a type name for the Ewk_Cookie_Persistent_Storage.
typedef enum Ewk_Cookie_Persistent_Storage Ewk_Cookie_Persistent_Storage;

/**
 * @typedef Ewk_Cookie_Manager_Async_Hostnames_Get_Cb Ewk_Cookie_Manager_Async_Hostnames_Get_Cb
 * @brief Callback type for use with ewk_cookie_manager_async_hostnames_with_cookies_get
 *
 * @note The @a hostnames list items are guaranteed to be eina_stringshare. Whenever possible
 * save yourself some cpu cycles and use eina_stringshare_ref() instead of eina_stringshare_add()
 * or strdup().
 */
typedef void (*Ewk_Cookie_Manager_Async_Hostnames_Get_Cb)(Eina_List *hostnames, Ewk_Error *error, void *event_info);


/**
 * Set the @a path where non-session cookies are stored persistently using
 * @a storage as the format to read/write the cookies.
 *
 * Cookies are initially read from @path/Cookies to create an initial
 * set of cookies. Then, non-session cookies will be written to @path/Cookies.
 *
 * By default, @a manager doesn't store the cookies persistenly, so you need to
 * call this method to keep cookies saved across sessions.
 *
 * If @path does not exist it will be created.
 *
 * @param cookie_manager the cookie manager to update
 * @param path the path where to read/write Cookies
 * @param storage the type of storage
 */
EXPORT_API void ewk_cookie_manager_persistent_storage_set(Ewk_Cookie_Manager *manager, const char *path, Ewk_Cookie_Persistent_Storage storage);

/**
 * Asynchronously get the list of host names for which @a manager contains cookies.
 *
 * @param manager The cookie manager to query.
 * @param callback The function to call when the host names have been received.
 * @param data User data (may be @c NULL).
 */
EXPORT_API void ewk_cookie_manager_async_hostnames_with_cookies_get(const Ewk_Cookie_Manager *manager, Ewk_Cookie_Manager_Async_Hostnames_Get_Cb callback, void *data);

/**
 * Remove all cookies of @a manager for the given @a hostname.
 *
 * @param manager The cookie manager to update.
 * @param hostname A host name.
 */
EXPORT_API void ewk_cookie_manager_hostname_cookies_clear(Ewk_Cookie_Manager *manager, const char *hostname);

#ifdef __cplusplus
}
#endif

#endif // ewk_cookie_manager_internal_h
