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

#ifndef ewk_security_origin_internal_h
#define ewk_security_origin_internal_h

#include "ewk_security_origin.h"

#ifdef __cplusplus
extern "C" {
#endif

/**
 * Requests for getting host of security origin.
 *
 * @param origin security origin
 *
 * @return host of security origin
 */
EXPORT_API uint16_t ewk_security_origin_port_get(const Ewk_Security_Origin* origin);

/**
 * Release all resources allocated by a security origin object.
 *
 * @param o security origin object
 */
EXPORT_API void ewk_security_origin_free(Ewk_Security_Origin *o);

/**
 * Creates a security origin for a url.
 *
 * @param url the url for the security origin.
 *
 * @return the security origin object
 */
EXPORT_API Ewk_Security_Origin *ewk_security_origin_new_from_string(const char *url);

#ifdef __cplusplus
}
#endif
#endif // ewk_security_origin_internal_h
