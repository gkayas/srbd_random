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

#ifndef ewk_policy_decision_internal_h
#define ewk_policy_decision_internal_h

#include "ewk_frame_internal.h"
#include "ewk_policy_decision.h"

#ifdef __cplusplus
extern "C" {
#endif

/**
 * Returns user id for Authorization from Policy Decision object.
 *
 * @param policy_decision policy decision object
 *
 * @return @c user id string on success or empty string on failure
 */
EXPORT_API const char* ewk_policy_decision_userid_get(const Ewk_Policy_Decision* policy_decision);

/**
 * Returns password for Authorization from Policy Decision object.
 *
 * @param policy_decision policy decision object
 *
 * @return @c password string on success or empty string on failure
 */
EXPORT_API const char* ewk_policy_decision_password_get(const Ewk_Policy_Decision* policy_decision);

/**
 * Suspend the operation for policy decision.
 *
 * This suspends the operation for policy decision when the signal for policy is emitted.
 * This is very useful to decide the policy from the additional UI operation like the popup.
 *
 * @param policy_decision policy decision object
 *
 * @return @c EINA_TRUE on success or @c EINA_FALSE on failure
 */
EXPORT_API Eina_Bool ewk_policy_decision_suspend(Ewk_Policy_Decision* policy_decision);

/**
 * Cause a download from this decision.
 *
 * @param policy_decision policy decision object
 *
 * @return @c EINA_TRUE on success or @c EINA_FALSE on failure
 */
EXPORT_API Eina_Bool ewk_policy_decision_download(Ewk_Policy_Decision* policy_decision);

/**
 * Gets the frame reference from Policy Decision object.
 *
 * @param policy_decision policy decision object
 *
 * @return frame reference on success, or NULL on failure
 */
EXPORT_API Ewk_Frame_Ref ewk_policy_decision_frame_get(Ewk_Policy_Decision* policy_decision);

/**
 * Checks if frame requested in policy decision is main frame.
 *
 * @param policy_decision policy decision object
 *
 * @return @c EINA_TRUE or @c EINA_FALSE
 */
EXPORT_API Eina_Bool ewk_policy_decision_is_main_frame(const Ewk_Policy_Decision* policy_decision);

#ifdef __cplusplus
}
#endif
#endif // ewk_policy_decision_internal_h
