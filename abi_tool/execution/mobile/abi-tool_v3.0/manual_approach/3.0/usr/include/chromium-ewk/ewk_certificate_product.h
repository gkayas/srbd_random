/*
 * Copyright (C) 2013 Samsung Electronics
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
 * You should have received a copy of the GNU Library General Public License
 * along with this library; see the file COPYING.LIB.  If not, write to
 * the Free Software Foundation, Inc., 51 Franklin Street, Fifth Floor,
 * Boston, MA 02110-1301, USA.
 */

#ifndef ewk_certificate_product_h
#define ewk_certificate_product_h

#include <Eina.h>

#ifdef __cplusplus
extern "C" {
#endif

/**
 * @addtogroup WEBVIEW
 * @{
 */

/**
 * @brief Creates a type name for #Ewk_Certificate_Policy_Decision
 * @since_tizen 2.3
 */
typedef struct _Ewk_Certificate_Policy_Decision Ewk_Certificate_Policy_Decision;

/**
 * @brief Set the variable to allow the site access about certificate error.
 *
 * @since_tizen 2.3
 *
 * @param[in] certificate_policy_decision certificate information data
 * @param[in] allowed decided permission value from user
 */
EXPORT_API void ewk_certificate_policy_decision_allowed_set(Ewk_Certificate_Policy_Decision* certificate_policy_decision, Eina_Bool allowed);

/**
 * @brief Suspend the operation for certificate error policy decision.
 *
 * @details This suspends the operation for certificate error policy decision when the signal for policy is emitted.\n
 * This is very usefull to decide the policy from the additional UI operation like the popup.
 *
 * @since_tizen 2.3
 *
 * @param[in] certificate_policy_decision certificate information data
 */
EXPORT_API void ewk_certificate_policy_decision_suspend(Ewk_Certificate_Policy_Decision* certificate_policy_decision);

/**
 * @brief Get the variable url to check the site's url data about certificate error.
 *
 * @since_tizen 2.3
 *
 * @param[in] certificate_policy_decision certificate information data
 *
 * @return @c url string on success or empty string on failure
 */
EXPORT_API const char* ewk_certificate_policy_decision_url_get(Ewk_Certificate_Policy_Decision* certificate_policy_decision);

/**
 * @brief Get the variable certificate pem data to check the information about certificate error.
 *
 * @since_tizen 2.3
 *
 * @param[in] certificate_policy_decision certificate information data
 *
 * @return @c certificate pem string on success or empty string on failure
 */
EXPORT_API const char* ewk_certificate_policy_decision_certificate_pem_get(Ewk_Certificate_Policy_Decision* certificate_policy_decision);

/**
 * @brief The structure type that hold certificate's information
 */
typedef struct _Ewk_Certificate_Info Ewk_Certificate_Info;

/**
 * @brief Query certificate's PEM data
 *
 * @param[in] cert_info Certificate's information
 *
 * @return A certificate itself in the PEM format. It may be null what indicates that
 *         webpage doesn't use the SSL protocol (e.g. HTTP).
 */
EXPORT_API const char* ewk_certificate_info_pem_get(const Ewk_Certificate_Info* cert_info);

/**
 * @brief Query if the context loaded with a given certificate is secure
 *
 * @details Even that webpage was successfully loaded with a given certificate, its context may not be secure.
 *          Secure context means that webpage is fully authenticated (using SSL certificates) and
 *          its content doesn't contain any insecure elements (like HTTP CSS, images, scripts etc.)
 *
 * @param[in] cert_info Certificate's information
 *
 * @return EINA_TRUE if the context is secure. Otherwise returns EINA_FALSE
 */
EXPORT_API Eina_Bool ewk_certificate_info_is_context_secure(const Ewk_Certificate_Info* cert_info);

/**
 * @brief Returns information whether the certificate compromise comes from main frame.
 *
 * @details Certificate issue can be associated with main frame or sub resource
 *          such as image, script, font etc. Browsers usually notify the user about
 *          certificate compromise if it comes from main frame, whereas all
 *          sub resource are silently blocked, since the user does not really
 *          have a context for making the right decision.
 *
 * @param[in] certificate_policy_decision certificate information data
 *
 * @return EINA_TRUE if the certificate compromise comes from main frame, EINA_FALSE otherwise
 */
EXPORT_API Eina_Bool ewk_certificate_policy_decision_from_main_frame_get(const Ewk_Certificate_Policy_Decision* certificate_policy_decision);

/**
* @}
*/

#ifdef __cplusplus
}
#endif
#endif
