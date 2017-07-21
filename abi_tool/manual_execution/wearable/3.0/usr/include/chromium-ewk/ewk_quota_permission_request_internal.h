// Copyright 2013 Samsung Electronics. All rights reserved.
// Use of this source code is governed by a BSD-style license that can be
// found in the LICENSE file.

#ifndef ewk_quota_permission_request_internal_h
#define ewk_quota_permission_request_internal_h

#include <stdint.h>
#include <Eina.h>
#include <tizen.h>

#ifdef __cplusplus
extern "C" {
#endif

typedef struct _Ewk_Quota_Permission_Request Ewk_Quota_Permission_Request;

/**
 * Requests for getting protocol of quota permission request
 *
 * @param request quota permission request
 *
 * @return protocol of security origin
 */
EXPORT_API Eina_Stringshare* ewk_quota_permission_request_origin_protocol_get(const Ewk_Quota_Permission_Request* request);

/**
 * Requests for getting host of quota permission request
 *
 * @param request quota permission request
 *
 * @return host of security origin
 */
EXPORT_API Eina_Stringshare* ewk_quota_permission_request_origin_host_get(const Ewk_Quota_Permission_Request* request);

/**
 * Requests for getting port of quota permission request
 *
 * @param request quota permission request
 *
 * @return port of security origin
 */
EXPORT_API uint16_t ewk_quota_permission_request_origin_port_get(const Ewk_Quota_Permission_Request* request);

/**
 * Requests for getting new quota size of quota permission request
 *
 * @param request quota permission request
 *
 * @return protocol of security origin
 */
EXPORT_API int64_t ewk_quota_permission_request_quota_get(const Ewk_Quota_Permission_Request* request);

/**
 * Requests for checking if storage type of quota permission request is persistent
 *
 * @param request quota permission request
 *
 * @return @c EINA_TRUE if storage is persistent, otherwise @c EINA_FALSE
 */
EXPORT_API Eina_Bool ewk_quota_permission_request_is_persistent_get(const Ewk_Quota_Permission_Request* request);

#ifdef __cplusplus
}
#endif
#endif // ewk_quota_permission_request_internal_h
