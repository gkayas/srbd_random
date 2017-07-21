// Copyright 2014 The Samsung Electronics Co., Ltd All rights reserved.
// Use of this source code is governed by a BSD-style license that can be
// found in the LICENSE file.

#ifndef ewk_tracing_internal_h
#define ewk_tracing_internal_h

#include <Eina.h>

#include <tizen.h>

#ifdef __cplusplus
extern "C" {
#endif

/**
 * Start recording traces.
 *
 * @param categories comma separated list of categories e.g. cc,ipc
 * @param trace_options traces options (currently not used).
 * @param trace_file_name filename in which traces should be recorded.
 *
 * @return @c EINA_TRUE on success or @c EINA_FALSE on failure.
*/
EXPORT_API Eina_Bool ewk_start_tracing(const char* categories, const char* trace_options, const char* trace_file_name);

/**
* Stop recording traces, works only if recording is started before calling
* this.
*/
EXPORT_API void ewk_stop_tracing();

#ifdef __cplusplus
}
#endif

#endif  // ewk_tracing_internal_h
