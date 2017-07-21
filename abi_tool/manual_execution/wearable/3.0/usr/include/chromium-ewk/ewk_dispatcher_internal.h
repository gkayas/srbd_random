// Copyright 2013 Samsung Electronics. All rights reserved.
// Use of this source code is governed by a BSD-style license that can be
// found in the LICENSE file.

#ifndef ewk_dispatcher_internal_h
#define ewk_dispatcher_internal_h

#include <tizen.h>

#ifdef __cplusplus
extern "C" {
#endif

typedef void (*ewk_dispatch_callback)(void *);

/**
 * Call once from the thread you want the dispatcher work on.
 * It should be main thread usually.
 *
 */
EXPORT_API void ewk_dispatcher_touch();

EXPORT_API void ewk_dispatcher_dispatch(ewk_dispatch_callback cb, void *user_data, unsigned delay);

#ifdef __cplusplus
}
#endif

#endif //ewk_dispatcher_internal_h
