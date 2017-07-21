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
 * @file    ewk_context_menu_internal.h
 * @brief   Describes the context menu API.
 */

#ifndef ewk_context_menu_internal_h
#define ewk_context_menu_internal_h

#include "ewk_context_menu.h"

#ifdef __cplusplus
extern "C" {
#endif

/**
 * \enum    _Ewk_Context_Menu_Item_Type
 * @brief   Enumeration that defines the types of the items for the context menu.
 */
enum _Ewk_Context_Menu_Item_Type {
    EWK_CONTEXT_MENU_ITEM_TYPE_ACTION,
    EWK_CONTEXT_MENU_ITEM_TYPE_CHECKABLE_ACTION,
    EWK_CONTEXT_MENU_ITEM_TYPE_SEPARATOR,
    EWK_CONTEXT_MENU_ITEM_TYPE_SUBMENU
};

/**
 * @brief Creates a type name for _Ewk_Context_Menu_Item_Type
 */
typedef enum _Ewk_Context_Menu_Item_Type Ewk_Context_Menu_Item_Type;

/**
 * Returns the type of context menu item.
 *
 * @param item The context menu item object
 *
 * @return The type of context menu item
 */
EXPORT_API Ewk_Context_Menu_Item_Type ewk_context_menu_item_type_get(Ewk_Context_Menu_Item* item);

/**
 * Returns the item is enabled.
 *
 * @param item The context menu item object to get enabled state
 *
 * @return EINA_TRUE if it is enabled, @c EINA_FALSE if not or on failure
 */
EXPORT_API Eina_Bool ewk_context_menu_item_enabled_get(const Ewk_Context_Menu_Item* item);


#ifdef __cplusplus
}
#endif

#endif // ewk_context_menu_internal_h

