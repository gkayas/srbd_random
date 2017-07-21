/*
 * Copyright (C) 2016 Samsung Electronics. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Library General Public
 * License as published by the Free Software Foundation; either
 * version 2 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Library General Public License for more details.
 *
 * You should have received a copy of the GNU Library General Public
 * License along with this library; if not, write to the
 * Free Software Foundation, Inc., 51 Franklin St, Fifth Floor,
 * Boston, MA  02110-1301, USA.
 *
 */

/**
 * @file    ewk_context_menu_product.h
 * @brief   Describes the context menu product API.
 */

#ifndef ewk_context_menu_product_h
#define ewk_context_menu_product_h

#include "ewk_context_menu_internal.h"

#ifdef __cplusplus
extern "C" {
#endif

/**
 * Gets a title of the item.
 *
 * @param item the item to get the title
 * @return a title of the item on success, or @c NULL on failure
 *
 * @see ewk_context_menu_item_title_set
 */
EXPORT_API const char* ewk_context_menu_item_title_get(const Ewk_Context_Menu_Item *item);

/**
 * Queries if the item is toggled.
 *
 * @param item the item to query if the item is toggled
 * @return @c EINA_TRUE if the item is toggled or @c EINA_FALSE if not or on failure
 */
EXPORT_API Eina_Bool  ewk_context_menu_item_checked_get(const Ewk_Context_Menu_Item *item);

/**
 * Gets the submenu for the item.
 *
 * @param item item to get the submenu
 *
 * @return the pointer to submenu on success or @c NULL on failure
 */
EXPORT_API Ewk_Context_Menu *ewk_context_menu_item_submenu_get(const Ewk_Context_Menu_Item *item);

/**
 * Gets the list of items.
 *
 * @param o the context menu to get list of the items
 * @return the list of the items on success or @c NULL on failure
 */
EXPORT_API const Eina_List *ewk_context_menu_items_get(const Ewk_Context_Menu *o);

/**
 * Gets the parent menu for the item.
 *
 * @param o item to get the parent
 *
 * @return the pointer to parent menu on success or @c NULL on failure
 */
EXPORT_API Ewk_Context_Menu *ewk_context_menu_item_parent_menu_get(const Ewk_Context_Menu_Item *o);

/**
 * Selects the item from the context menu.
 *
 * @param menu the context menu
 * @param item the item is selected
 * @return @c EINA_TRUE on success or @c EINA_FALSE on failure
 */
EXPORT_API Eina_Bool ewk_context_menu_item_select(Ewk_Context_Menu *menu, Ewk_Context_Menu_Item *item);

/**
 * Hides the context menu.
 *
 * @param menu the context menu to hide
 * @return @c EINA_TRUE on success, @c EINA_FALSE on failure
 */
EXPORT_API Eina_Bool ewk_context_menu_hide(Ewk_Context_Menu *menu);

#ifdef __cplusplus
}
#endif

#endif // ewk_context_menu_product_h
