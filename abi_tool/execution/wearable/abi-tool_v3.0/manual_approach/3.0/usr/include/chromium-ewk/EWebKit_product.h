/*
 * Copyright (C) 2016 Samsung Electronics. All rights reserved.
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
 * @file    EWebKit_product.h
 * @brief   Contains the header files that are required by Chromium-EFL.
 *
 * It includes the all header files that are exported to product API.
 */

#ifndef EWebKit_product_h
#define EWebKit_product_h

#include "ewk_context_product.h"
#include "ewk_settings_product.h"
#include "ewk_view_product.h"

/**
 * @ingroup  CAPI_WEB_FRAMEWORK
 * @brief    The WebView API product specific functions.
 *
 * @section  WEBVIEW_HEADER Required Header
 *   \#include <EWebKit_product.h>
 *
 * @section  WEBVIEW_OVERVIEW Overview
 * Product specific API
 *
 * @section  WEBVIEW_SMART_OBJECT Smart object
 * The following signals (see evas_object_smart_callback_add()) are emitted:
 * <table>
 *     <tr>
 *         <th> Signals </th>
 *         <th> Type </th>
 *         <th> Description </th>
 *     </tr>
 *     <tr>
 *         <td> link,hover,over </td>
 *         <td> char* </td>
 *         <td> Mouse cursor hovers over a link </td>
 *     </tr>
 *     <tr>
 *         <td> link,hover,out </td>
 *         <td> char* </td>
 *         <td> Mouse cursor is moved away from a link </td>
 *     </tr>
 * </table>
 */

#endif // EWebKit_product_h
