#ifndef _ECORE_CON_BASE_EO_H_
#define _ECORE_CON_BASE_EO_H_

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
#define ECORE_CON_BASE_CLASS ecore_con_base_class_get()

EAPI const Eo_Class *ecore_con_base_class_get(void) EINA_CONST;

/**
 * @brief Control the IP address of a server that has been connected to.
 *
 * The param is a pointer to an internal string that contains the IP address of
 * the connected server in the form "XXX.YYY.ZZZ.AAA" IP notation. This string
 * should not be modified or trusted to stay valid after deletion for the svr
 * object. If no IP is known null is returned.
 *
 * @return The IP address
 *
 * @ingroup Ecore_Con_Base
 */
EOAPI const char * ecore_con_obj_ip_get(void);

/**
 * @brief Check how long the object has been connected
 *
 * This function is used to find out how long a client has been connected for.
 *
 * @return The total time, in seconds, that the object has been connected.
 *
 * @ingroup Ecore_Con_Base
 */
EOAPI double  ecore_con_obj_uptime_get(void);

/**
 * @brief Return the port that the obj is connected to
 *
 * @param[in] port The The port that obj is connected to, or -1 on error.
 *
 * @ingroup Ecore_Con_Base
 */
EOAPI void  ecore_con_obj_port_set(int port);

/**
 * @brief Return the port that the obj is connected to
 *
 * @return The The port that obj is connected to, or -1 on error.
 *
 * @ingroup Ecore_Con_Base
 */
EOAPI int  ecore_con_obj_port_get(void);

/**
 * @brief Get the fd that the server is connected to
 *
 * This function returns the fd which is used by the underlying server
 * connection. It should not be tampered with unless you REALLY know what you
 * are doing.
 *
 * @note This function is only valid for servers created with @ref
 * ecore_con_server_connect.
 *
 * @warning Seriously. Don't use this unless you know what you are doing.
 *
 * @return The fd, or -1 on failure.
 *
 * @since 1.1
 *
 * @ingroup Ecore_Con_Base
 */
EOAPI int  ecore_con_obj_fd_get(void);

/**
 * @brief Returns whether the client is still connected
 *
 * @return Returns true if connected, false otherwise.
 *
 * @ingroup Ecore_Con_Base
 */
EOAPI Eina_Bool  ecore_con_obj_connected_get(void);

/**
 * @brief Control the default time after which an inactive client will be
 * disconnected.
 *
 * This function is used by the server to set the default idle timeout on
 * clients. If the any of the clients becomes idle for a time higher than this
 * value, it will be disconnected. A value of < 1 disables the idle timeout.
 *
 * This timeout is not affected by the one set by
 * @ref ecore_con_obj_timeout_set. A client will be disconnected whenever the
 * client or the server timeout is reached. That means, the lower timeout value
 *  will be used for that client if @ref ecore_con_obj_timeout_set is used on
 * it.
 *
 * @param[in] timeout The timeout, in seconds, to disconnect after.
 *
 * @ingroup Ecore_Con_Base
 */
EOAPI void  ecore_con_obj_timeout_set(double timeout);

/**
 * @brief Control the default time after which an inactive client will be
 * disconnected.
 *
 * This function is used by the server to set the default idle timeout on
 * clients. If the any of the clients becomes idle for a time higher than this
 * value, it will be disconnected. A value of < 1 disables the idle timeout.
 *
 * This timeout is not affected by the one set by
 * @ref ecore_con_obj_timeout_set. A client will be disconnected whenever the
 * client or the server timeout is reached. That means, the lower timeout value
 *  will be used for that client if @ref ecore_con_obj_timeout_set is used on
 * it.
 *
 * @return The timeout, in seconds, to disconnect after.
 *
 * @ingroup Ecore_Con_Base
 */
EOAPI double  ecore_con_obj_timeout_get(void);

/**
 * @brief Flushes all pending data to the given server.
 *
 * This function will block until all data is sent to the server.
 *
 * @ingroup Ecore_Con_Base
 */
EOAPI void  ecore_con_obj_flush(void);

/**
 * @brief Sends the given data to the given server.
 *
 * This function will send the given data to the server as soon as the program
 * is back to the main loop. Thus, this function returns immediately
 * (non-blocking). If the data needs to be sent now, call @ref
 * ecore_con_server_flush after this one.
 *
 * @param[in] size Length of the data, in bytes.
 *
 * @return The number of bytes sent. 0 will be returned if there is an error.
 *
 * @ingroup Ecore_Con_Base
 */
EOAPI int  ecore_con_obj_send(const void *data, int size);

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
EOAPI Eina_Bool  ecore_con_obj_lookup(const char *name, Ecore_Con_Dns_Cb done_cb, const void *data);

EOAPI extern const Eo_Event_Description _ECORE_CON_BASE_EVENT_DATA_RECEIVED;
EOAPI extern const Eo_Event_Description _ECORE_CON_BASE_EVENT_CONNECTION_UPGRADED;
EOAPI extern const Eo_Event_Description _ECORE_CON_BASE_EVENT_CONNECTION_ERROR;

/**
 * No description
 */
#define ECORE_CON_BASE_EVENT_DATA_RECEIVED (&(_ECORE_CON_BASE_EVENT_DATA_RECEIVED))

/**
 * No description
 */
#define ECORE_CON_BASE_EVENT_CONNECTION_UPGRADED (&(_ECORE_CON_BASE_EVENT_CONNECTION_UPGRADED))

/**
 * No description
 */
#define ECORE_CON_BASE_EVENT_CONNECTION_ERROR (&(_ECORE_CON_BASE_EVENT_CONNECTION_ERROR))

#endif
