#ifndef _EFL_NETWORK_URL_EO_H_
#define _EFL_NETWORK_URL_EO_H_

#ifndef _EFL_NETWORK_URL_EO_CLASS_TYPE
#define _EFL_NETWORK_URL_EO_CLASS_TYPE

typedef Eo Efl_Network_Url;

#endif

#ifndef _EFL_NETWORK_URL_EO_TYPES
#define _EFL_NETWORK_URL_EO_TYPES


#endif
#define EFL_NETWORK_URL_CLASS efl_network_url_class_get()

EAPI const Eo_Class *efl_network_url_class_get(void) EINA_CONST;

/**
 * @brief Controls the URL to send the request to.
 *
 * @param[in] url The URL
 *
 * @return true on success, false on error.
 *
 * @ingroup Efl_Network_Url
 */
EOAPI Eina_Bool  efl_network_url_set(const char *url);

/**
 * @brief Controls the URL to send the request to.
 *
 * @return The URL
 *
 * @ingroup Efl_Network_Url
 */
EOAPI const char * efl_network_url_get(void);

EOAPI extern const Eo_Event_Description _EFL_NETWORK_URL_EVENT_DATA;
EOAPI extern const Eo_Event_Description _EFL_NETWORK_URL_EVENT_PROGRESS;
EOAPI extern const Eo_Event_Description _EFL_NETWORK_URL_EVENT_COMPLETE;

/** Triggered when data arrives at the socket.
 *
 * @ingroup Efl_Network_Url
 */
#define EFL_NETWORK_URL_EVENT_DATA (&(_EFL_NETWORK_URL_EVENT_DATA))

/** Triggered when progress is made in upload/download.
 *
 * @ingroup Efl_Network_Url
 */
#define EFL_NETWORK_URL_EVENT_PROGRESS (&(_EFL_NETWORK_URL_EVENT_PROGRESS))

/** Triggered when the operation is complete.
 *
 * @ingroup Efl_Network_Url
 */
#define EFL_NETWORK_URL_EVENT_COMPLETE (&(_EFL_NETWORK_URL_EVENT_COMPLETE))

#endif
