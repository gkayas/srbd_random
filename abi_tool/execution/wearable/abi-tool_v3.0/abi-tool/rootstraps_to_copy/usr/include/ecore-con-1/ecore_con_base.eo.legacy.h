#ifndef _ECORE_CON_BASE_EO_LEGACY_H_
#define _ECORE_CON_BASE_EO_LEGACY_H_

#ifndef _ECORE_CON_BASE_EO_CLASS_TYPE
#define _ECORE_CON_BASE_EO_CLASS_TYPE

typedef Eo Ecore_Con_Base;

#endif

#ifndef _ECORE_CON_BASE_EO_TYPES
#define _ECORE_CON_BASE_EO_TYPES

typedef struct _Ecore_Con_Event_Data_Received
{
  void *data; /** The data thet got sent. */
  int size; /** The length of the data sent. */
} Ecore_Con_Event_Data_Received;


#endif

/**
 * @brief Do an asynchronous DNS lookup.
 *
 * This function performs a DNS lookup on the hostname specified by name, then
 * calls done_cb with the result and the data given as parameter. The result
 * will be given to the done_cb as follows:
 *
 * canonname - the canonical name of the address, ip - the resolved ip address,
 * addr - a pointer to the socket address, addrlen - the length of the socket
 * address, in bytes, data - the data pointer given as parameter.
 *
 * @if MOBILE @since_tizen 3.0 @elseif WEARABLE @since_tizen 3.0 @endif
 *
 * @param[in] done_cb Callback to notify when done.
 * @param[in] data User data to be given to done_cb.
 *
 * @return true if the request did not fail to be set up, false otherwise.
 *
 * @ingroup Ecore_Con_Base
 */
EAPI Eina_Bool ecore_con_lookup(const char *name, Ecore_Con_Dns_Cb done_cb, const void *data) EINA_ARG_NONNULL(2);

#endif
