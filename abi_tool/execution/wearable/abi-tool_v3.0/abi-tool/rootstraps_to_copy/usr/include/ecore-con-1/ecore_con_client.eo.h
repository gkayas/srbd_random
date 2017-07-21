#ifndef _ECORE_CON_CLIENT_EO_H_
#define _ECORE_CON_CLIENT_EO_H_

#ifndef _ECORE_CON_CLIENT_EO_CLASS_TYPE
#define _ECORE_CON_CLIENT_EO_CLASS_TYPE

typedef Eo Ecore_Con_Client;

#endif

#ifndef _ECORE_CON_CLIENT_EO_TYPES
#define _ECORE_CON_CLIENT_EO_TYPES


#endif
#define ECORE_CON_CLIENT_CLASS ecore_con_client_class_get()

EAPI const Eo_Class *ecore_con_client_class_get(void) EINA_CONST;

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
EOAPI Ecore_Con_Server * ecore_con_client_obj_server_get(void);


#endif
