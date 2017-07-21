#ifndef _ECORE_CON_CLIENT_EO_LEGACY_H_
#define _ECORE_CON_CLIENT_EO_LEGACY_H_

#ifndef _ECORE_CON_CLIENT_EO_CLASS_TYPE
#define _ECORE_CON_CLIENT_EO_CLASS_TYPE

typedef Eo Ecore_Con_Client;

#endif

#ifndef _ECORE_CON_CLIENT_EO_TYPES
#define _ECORE_CON_CLIENT_EO_TYPES


#endif

/**
 * @brief Controls the server representing the socket the client has connected
 * to.
 *
 * @if MOBILE @since_tizen 3.0 @elseif WEARABLE @since_tizen 3.0 @endif
 *
 * @return The server the client is connected to.
 *
 * @ingroup Ecore_Con_Client
 */
EAPI Ecore_Con_Server *ecore_con_client_server_get(const Ecore_Con_Client *obj);

#endif
