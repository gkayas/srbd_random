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
 * @file    ewk_cookie_manager_product.h
 * @brief   Describes the Ewk Cookie Manager API.
 */

#ifndef ewk_cookie_manager_product_h
#define ewk_cookie_manager_product_h

#ifdef __cplusplus
extern "C" {
#endif

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

#endif // ewk_cookie_manager_product_h
