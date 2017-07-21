#ifndef _ECORE_CON_SERVER_EO_H_
#define _ECORE_CON_SERVER_EO_H_

#ifndef _ECORE_CON_SERVER_EO_CLASS_TYPE
#define _ECORE_CON_SERVER_EO_CLASS_TYPE

typedef Eo Ecore_Con_Server;

#endif

#ifndef _ECORE_CON_SERVER_EO_TYPES
#define _ECORE_CON_SERVER_EO_TYPES


#endif
#define ECORE_CON_SERVER_CLASS ecore_con_server_class_get()

EAPI const Eo_Class *ecore_con_server_class_get(void) EINA_CONST;

/**
 * @brief Retrieves the name of server.
 *
 * The name returned is the name used to connect on this server.
 *
 * @param[in] name The name of the server.
 *
 * @ingroup Ecore_Con_Server
 */
EOAPI void  ecore_con_server_obj_name_set(const char *name);

/**
 * @brief Retrieves the name of server.
 *
 * The name returned is the name used to connect on this server.
 *
 * @if MOBILE @since_tizen 3.0 @elseif WEARABLE @since_tizen 3.0 @endif
 *
 * @return The name of the server.
 *
 * @ingroup Ecore_Con_Server
 */
EOAPI const char * ecore_con_server_obj_name_get(void);

/**
 * @brief Sets a limit on the number of clients that can be handled
 * concurrently by the given server, and a policy on what to do if excess
 * clients try to connect.
 *
 * Beware that if you set this once ecore is already running, you may already
 * have pending CLIENT_ADD events in your event queue.  Those clients have
 * already connected and will not be affected by this call. Only clients
 * subsequently trying to connect will be affected.
 *
 * @if MOBILE @since_tizen 3.0 @elseif WEARABLE @since_tizen 3.0 @endif
 *
 * @param[in] client_limit The maximum number of clients to handle
 * concurrently. -1 means unlimited (default). 0 effectively disables the
 * server.
 * @param[in] reject_excess_clients Set to 1 to automatically disconnect excess
 * clients as soon as they connect if you are already handling client_limit
 * clients. Set to 0 (default) to just hold off on the "accept()" system call
 * until the number of active  clients drops. This causes the kernel to queue
 * up to 4096 connections (or your kernel's limit, whichever is lower).
 *
 * @ingroup Ecore_Con_Server
 */
EOAPI void  ecore_con_server_obj_client_limit_set(int client_limit, char reject_excess_clients);

/**
 * @brief Sets a limit on the number of clients that can be handled
 * concurrently by the given server, and a policy on what to do if excess
 * clients try to connect.
 *
 * Beware that if you set this once ecore is already running, you may already
 * have pending CLIENT_ADD events in your event queue.  Those clients have
 * already connected and will not be affected by this call. Only clients
 * subsequently trying to connect will be affected.
 *
 * @param[out] client_limit The maximum number of clients to handle
 * concurrently. -1 means unlimited (default). 0 effectively disables the
 * server.
 * @param[out] reject_excess_clients Set to 1 to automatically disconnect
 * excess clients as soon as they connect if you are already handling
 * client_limit clients. Set to 0 (default) to just hold off on the "accept()"
 * system call until the number of active  clients drops. This causes the
 * kernel to queue up to 4096 connections (or your kernel's limit, whichever is
 * lower).
 *
 * @ingroup Ecore_Con_Server
 */
EOAPI void  ecore_con_server_obj_client_limit_get(int *client_limit, char *reject_excess_clients);

/**
 * @brief Retrieves the current list of clients.
 *
 * Each node in the returned list points to an @ref Ecore_Con_Client. This list
 * cannot be modified or freed. It can also change if new clients are connected
 * or disconnected, and will become invalid when the server is deleted/freed.
 *
 * @if MOBILE @since_tizen 3.0 @elseif WEARABLE @since_tizen 3.0 @endif
 *
 * @return The list of clients on this server.
 *
 * @ingroup Ecore_Con_Server
 */
EOAPI const Eina_List * ecore_con_server_obj_clients_get(void);

EOAPI void  ecore_con_server_obj_connection_type_set(Ecore_Con_Type conn_type);

EOAPI Ecore_Con_Type  ecore_con_server_obj_connection_type_get(void);


#endif
