// Copyright 2013-2016 Samsung Electronics. All rights reserved.
// Use of this source code is governed by a BSD-style license that can be
// found in the LICENSE file.

#ifndef EWK_EFL_INTEGRATION_PUBLIC_EWK_INTERCEPT_REQUEST_INTERNAL_H_
#define EWK_EFL_INTEGRATION_PUBLIC_EWK_INTERCEPT_REQUEST_INTERNAL_H_

#include <Eina.h>
#include <tizen.h>

#include "ewk_intercept_request.h"

#ifdef __cplusplus
extern "C" {
#endif

/**
 * Returns request url sheme from Intercept Request object.
 *
 * Returned string is owned by Intercept Request object, you have to make a
 * copy if you want to use it past owner lifetime.
 *
 * @param intercept_request intercept request instance received from
 *        Ewk_Context_Intercept_Request_Callback ewk_context callback
 *
 * @return @c url sheme string on success or NULL on failure
 */
EXPORT_API const char* ewk_intercept_request_scheme_get(
    Ewk_Intercept_Request* intercept_request);

#ifdef __cplusplus
}
#endif

#endif  // EWK_EFL_INTEGRATION_PUBLIC_EWK_INTERCEPT_REQUEST_INTERNAL_H_
