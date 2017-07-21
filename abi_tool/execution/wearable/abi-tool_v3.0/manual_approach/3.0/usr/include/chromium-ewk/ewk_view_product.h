/*
   Copyright (C) 2016 Samsung Electronics. All rights reserved.

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
 * @file    ewk_view_product.h
 * @brief   Chromium main smart object.
 *
 * This object provides view related APIs of Chromium to EFL object.
 */

#ifndef ewk_view_product_h
#define ewk_view_product_h

#include <Evas.h>

#ifdef __cplusplus
extern "C" {
#endif

/**
 * Returns the evas image object of the specified viewArea of page
 *
 * The returned evas image object @b should be freed after use.
 *
 * @param o view object to get specified rectangle of cairo surface.
 * @param viewArea rectangle of cairo surface.
 * @param scaleFactor scale factor of cairo surface.
 * @param canvas canvas for creating evas image.
 *
 * @return newly allocated evas image object on sucess or @c 0 on failure.
 */
EXPORT_API Evas_Object* ewk_view_screenshot_contents_get(const Evas_Object* o, Eina_Rectangle viewArea, float scaleFactor, Evas* canvas);

/**
* @}
*/

#ifdef __cplusplus
}
#endif
#endif // ewk_view_product_h
