/*
 * Copyright (C) 2012 Intel Corporation. All rights reserved.
 * Copyright (C) 2016 Samsung Electronics. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions, and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY APPLE INC. AND ITS CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL APPLE INC. OR ITS CONTRIBUTORS
 * BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
 * THE POSSIBILITY OF SUCH DAMAGE.
 */

/**
 * @file    ewk_back_forward_list.h
 * @brief   This file describes the Ewk Back Forward List API.
 */

#ifndef ewk_back_forward_list_h
#define ewk_back_forward_list_h

#include <Eina.h>
#include <tizen.h>
#include "ewk_back_forward_list_item.h"

#ifdef __cplusplus
extern "C" {
#endif

/**
 * @addtogroup WEBVIEW
 * @{
 */

/**
 * @brief The structure type that creates a type name for
 *        Ewk_Back_Forward_List.
 * @since_tizen @if MOBILE 2.3 @elseif WEARABLE 2.3.1 @endif
 */
typedef struct _Ewk_Back_Forward_List Ewk_Back_Forward_List;

/**
 * @brief Returns the current item in the @a list.
 *
 * @since_tizen @if MOBILE 2.3 @elseif WEARABLE 2.3.1 @endif
 *
 * @param[in] list The back-forward list instance
 *
 * @return The current item in the @a list,\n
 *         otherwise @c NULL in case of an error
 */
EXPORT_API Ewk_Back_Forward_List_Item* ewk_back_forward_list_current_item_get(const Ewk_Back_Forward_List* list);

/**
 * @brief Returns the item that precedes the current item in the @a list.
 *
 * @since_tizen @if MOBILE 2.3 @elseif WEARABLE 2.3.1 @endif
 *
 * @param[in] list The back-forward list instance
 *
 * @return The item that precedes the current item in the @a list,\n
 *         otherwise @c NULL in case of an error
 */
EXPORT_API Ewk_Back_Forward_List_Item* ewk_back_forward_list_previous_item_get(const Ewk_Back_Forward_List* list);

/**
 * @brief Returns the item that follows the current item in the @a list.
 *
 * @since_tizen @if MOBILE 2.3 @elseif WEARABLE 2.3.1 @endif
 *
 * @param[in] list The back-forward list instance
 *
 * @return The item that follows the current item in the @a list,\n
 *         otherwise @c NULL in case of an error
 */
EXPORT_API Ewk_Back_Forward_List_Item* ewk_back_forward_list_next_item_get(const Ewk_Back_Forward_List* list);

/**
 * @brief Returns the item at a given @a index relative to the current item.
 *
 * @since_tizen @if MOBILE 2.3 @elseif WEARABLE 2.3.1 @endif
 *
 * @param[in] list The back-forward list instance
 * @param[in] index The index of the item
 *
 * @return The item at a given @a index relative to the current item,\n
 *         otherwise @c NULL in case of an error
 */
EXPORT_API Ewk_Back_Forward_List_Item* ewk_back_forward_list_item_at_index_get(const Ewk_Back_Forward_List* list, int index);

/**
 * @brief Returns the length of the back-forward list including the current
 *        item.
 *
 * @since_tizen @if MOBILE 2.3 @elseif WEARABLE 2.3.1 @endif
 *
 * @param[in] list The back-forward list instance
 *
 * @return The length of the back-forward list including the current item,\n
 *         otherwise @c 0 in case of an error
 */
EXPORT_API unsigned ewk_back_forward_list_count(Ewk_Back_Forward_List* list);

/**
 * @brief Creates a list containing the items preceding the current item limited
 *        by @a limit.
 *
 * @details The @c Ewk_Back_Forward_List_Item elements are located in the result
            list starting with the oldest one.\n
 *          If @a limit is equal to @c -1 all the items preceding the current
 *          item are returned.
 *
 * @since_tizen @if MOBILE 2.3 @elseif WEARABLE 2.3.1 @endif
 *
 * @param[in] list The back-forward list instance
 * @param[in] limit The number of items to retrieve
 *
 * @return @c Eina_List containing @c Ewk_Back_Forward_List_Item elements,\n
 *         otherwise @c NULL in case of an error\n
 *         The Eina_List and its items should be freed after use\n
 *         Use ewk_back_forward_list_item_unref() to free the items
 */
EXPORT_API Eina_List* ewk_back_forward_list_n_back_items_copy(const Ewk_Back_Forward_List* list, int limit);

/**
 * @brief Creates the list containing the items following the current item
 *        limited by @a limit.
 *
 * @details The @c Ewk_Back_Forward_List_Item elements are located in the result
 *          list starting with the oldest one.\n
 *          If @a limit is equal to @c -1 all the items preceding the current
 *          item are returned.
 *
 * @since_tizen @if MOBILE 2.3 @elseif WEARABLE 2.3.1 @endif
 *
 * @param[in] list The back-forward list instance
 * @param[in] limit The number of items to retrieve
 *
 * @return @c Eina_List containing @c Ewk_Back_Forward_List_Item elements,\n
 *         otherwise @c NULL in case of an error,\n
 *         The Eina_List and its items should be freed after use\n
 *         Use ewk_back_forward_list_item_unref() to free the items
 */
EXPORT_API Eina_List* ewk_back_forward_list_n_forward_items_copy(const Ewk_Back_Forward_List* list, int limit);

/**
* @}
*/

#ifdef __cplusplus
}
#endif

#endif // ewk_back_forward_list_h
