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
 * @file    ewk_context_product.h
 * @brief   Describes the context API.
 *
 * @note ewk_context encapsulates all pages related to specific use of
 *       Chromium-efl
 *
 * Applications have the option of creating a context different than the default
 * one and use it for a group of pages. All pages in the same context share the
 * same preferences, visited link set, local storage, etc.
 *
 * A process model can be specified per context. The default one is the shared
 * model where the web-engine process is shared among the pages in the context.
 * The second model allows each page to use a separate web-engine process.
 * This latter model is currently not supported by Chromium-efl.
 *
 */

#ifndef ewk_context_product_h
#define ewk_context_product_h

#ifdef __cplusplus
extern "C" {
#endif

/**
 * @brief Gets default Ewk_Context instance.
 *
 * @details The returned Ewk_Context object @b should not be unref'ed if application\n
 * does not call ewk_context_ref() for that.
 *
 * @since_tizen 2.3
 *
 * @return Ewk_Context object
 */
EXPORT_API Ewk_Context* ewk_context_default_get(void);

/**
 * @brief Toggles tizen background music property enable and disable
 *
 * @since_tizen 2.3.2
 *
 * @param[in] context context object
 * @param[in] enable enable or disable tizen background music property
 *
 * @return @c EINA_TRUE on success or @c EINA_FALSE on failure
 */
EXPORT_API Eina_Bool ewk_context_background_music_set(Ewk_Context *ewkContext, Eina_Bool enable);

#ifdef __cplusplus
}
#endif

#endif // ewk_context_product_h
