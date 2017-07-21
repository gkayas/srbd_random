// Copyright 2013 Samsung Electronics. All rights reserved.
// Use of this source code is governed by a BSD-style license that can be
// found in the LICENSE file.

#ifndef ewk_notification_product_h
#define ewk_notification_product_h

#include <Eina.h>
#include <Evas.h>
#include <tizen.h>

#ifdef __cplusplus
extern "C" {
#endif

typedef struct Ewk_Notification Ewk_Notification;
typedef struct Ewk_Notification_Permission_Request Ewk_Notification_Permission_Request;

/**
 * Requests for getting body of notification.
 *
 * @param ewk_notification pointer of notificaion data
 *
 * @return body of notification
 *         Lifetime only valid as long as @a ewk_notification is valid.
 */
EXPORT_API const char* ewk_notification_body_get(const Ewk_Notification* ewk_notification);

/**
 * Notify that notification is clicked.
 *
 * @param notification_id identifier of notification
 *
 * @return EINA_TRUE on success, EINA_FALSE if notification id is invalid
 */
EXPORT_API Eina_Bool ewk_notification_clicked(uint64_t notification_id);

/**
 * Requests for getting icon url of notification.
 *
 * @param ewk_notification pointer of notification data
 *
 * @return Always returns NULL - this API is deprecated.
 *
 * @deprecated
 */
EINA_DEPRECATED EXPORT_API const char* ewk_notification_icon_url_get(const Ewk_Notification* ewk_notification);

/**
 * Requests for getting id of notification.
 *
 * @param ewk_notification pointer of notification data
 *
 * @return id of notification
 */
EXPORT_API uint64_t ewk_notification_id_get(const Ewk_Notification* ewk_notification);

/**
 * Requests for getting origin of notification permission request.
 *
 * @param request Ewk_Notification_Permission_Request object to get origin for notification permission request
 *
 * @return security origin of notification permission request
 *         Lifetime only valid as long as @a ewk_notification is valid.
 */
EXPORT_API const Ewk_Security_Origin* ewk_notification_permission_request_origin_get(const Ewk_Notification_Permission_Request* request);

/**
 * Reply the result about notification permission.
 *
 * @param request Ewk_Notification_Permission_Request object to get the
 *                infomation about notification permission request.
 * @param allow result about notification permission
 *
 * @return EINA_TRUE is successful. EINA_FALSE if reply was already called for
 *         this request or if request is NULL
 */
EXPORT_API Eina_Bool ewk_notification_permission_reply(Ewk_Notification_Permission_Request *request, Eina_Bool allow);

/**
 * Suspend the operation for permission request.
 *
 * This suspends the operation for permission request.
 * This is very useful to decide the policy from the additional UI operation like the popup.
 *
 * @param request Ewk_Notification_Permission_Request object to suspend notification permission request
 */
EXPORT_API Eina_Bool ewk_notification_permission_request_suspend(Ewk_Notification_Permission_Request* request);

/**
 * Notify that notification policies are removed.
 *
 * @param context context object
 * @param origins list of security origins(made by UAs)
 */
EXPORT_API Eina_Bool ewk_notification_policies_removed(Eina_List* origins);

/**
 * Requests for getting security origin of notification.
 *
 * @param ewk_notification pointer of notification data
 *
 * @return security origin of notification
 *         Lifetime only valid as long as @a ewk_notification is valid.
 */
EXPORT_API const Ewk_Security_Origin* ewk_notification_security_origin_get(const Ewk_Notification* ewk_notification);

/**
 * Notify that notification is showed.
 *
 * @param notification_id identifier of notification
 */
EXPORT_API Eina_Bool ewk_notification_showed(uint64_t notification_id);

/**
 * Requests for getting title of notification.
 *
 * @param ewk_notification pointer of notification data
 *
 * @return title of notification
 *         Lifetime only valid as long as @a ewk_notification is valid.
 */
EXPORT_API const char* ewk_notification_title_get(const Ewk_Notification* ewk_notification);

#ifdef __cplusplus
}
#endif
#endif // ewk_notification_product.h
