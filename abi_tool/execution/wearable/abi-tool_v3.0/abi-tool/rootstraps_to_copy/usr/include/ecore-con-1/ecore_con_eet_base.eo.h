#ifndef _ECORE_CON_EET_BASE_EO_H_
#define _ECORE_CON_EET_BASE_EO_H_

#ifndef _ECORE_CON_EET_BASE_EO_CLASS_TYPE
#define _ECORE_CON_EET_BASE_EO_CLASS_TYPE

typedef Eo Ecore_Con_Eet_Base;

#endif

#ifndef _ECORE_CON_EET_BASE_EO_TYPES
#define _ECORE_CON_EET_BASE_EO_TYPES


#endif
#define ECORE_CON_EET_BASE_CLASS ecore_con_eet_base_class_get()

EAPI const Eo_Class *ecore_con_eet_base_class_get(void) EINA_CONST;

/**
 * @brief The server object to which we send and receive.
 *
 * @param[in] data
 *
 * @ingroup Ecore_Con_Eet_Base
 */
EOAPI void  ecore_con_eet_base_server_set(Ecore_Con_Server *data);

/** The server object to which we send and receive.
 *
 * @ingroup Ecore_Con_Eet_Base
 */
EOAPI Ecore_Con_Server * ecore_con_eet_base_server_get(void);

/**
 * @brief A callback function which should be called when data is received by
 * ecore_con_eet_object.
 *
 * @param[in] name The name of the eet stream.
 * @param[in] func The callback function.
 * @param[in] data The data (if any) that should be passed to callback
 * function.
 *
 * @ingroup Ecore_Con_Eet_Base
 */
EOAPI void  ecore_con_eet_base_data_callback_set(const char *name, Ecore_Con_Eet_Data_Cb func, const void *data);

/**
 * @brief A callback function which should be calledn when raw data is received
 * by ecore_con_eet_object.
 *
 * @param[in] name The name of the eet stream.
 * @param[in] func The callback function.
 * @param[in] data The data (if any) that should be passed to callback
 * function.
 *
 * @ingroup Ecore_Con_Eet_Base
 */
EOAPI void  ecore_con_eet_base_raw_data_callback_set(const char *name, Ecore_Con_Eet_Raw_Data_Cb func, const void *data);

/**
 * @brief Function to delete the @ref ecore_con_eet_base_data_callback_set.
 *
 * @param[in] name The name of the eet stream.
 *
 * @ingroup Ecore_Con_Eet_Base
 */
EOAPI void  ecore_con_eet_base_data_callback_del(const char *name);

/**
 * @brief Function to delete the @ref ecore_con_eet_base_raw_data_callback_set.
 *
 * @param[in] name The name of the eet stream.
 *
 * @ingroup Ecore_Con_Eet_Base
 */
EOAPI void  ecore_con_eet_base_raw_data_callback_del(const char *name);

/**
 * @brief Function to register a @ref Eet_Data_Descriptor to the ecore_con_eet
 * object.
 *
 * @if MOBILE @since_tizen 3.0 @elseif WEARABLE @since_tizen 3.0 @endif
 *
 * @param[in] edd The Eet_Data_Descriptor that is to be registered.
 *
 * @ingroup Ecore_Con_Eet_Base
 */
EOAPI void  ecore_con_eet_base_register(const char *name, Eet_Data_Descriptor *edd);

/**
 * @brief Function to send data.
 *
 * @param[in] name The name of the eet stream.
 * @param[in] value Actual data
 *
 * @ingroup Ecore_Con_Eet_Base
 */
EOAPI void  ecore_con_eet_base_send(Ecore_Con_Reply *reply, const char *name, void *value);

/**
 * @brief Function to send raw data.
 *
 * @param[in] protocol_name The name of the eet stream.
 * @param[in] section Name of section in the eet descriptor.
 * @param[in] value The value of the section.
 * @param[in] length The length of the data that is being sent.
 *
 * @ingroup Ecore_Con_Eet_Base
 */
EOAPI void  ecore_con_eet_base_raw_send(Ecore_Con_Reply *reply, const char *protocol_name, const char *section, void *value, unsigned int length);


#endif
