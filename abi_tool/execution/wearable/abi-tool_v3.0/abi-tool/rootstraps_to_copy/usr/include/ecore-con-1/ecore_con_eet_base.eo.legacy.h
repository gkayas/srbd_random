#ifndef _ECORE_CON_EET_BASE_EO_LEGACY_H_
#define _ECORE_CON_EET_BASE_EO_LEGACY_H_

#ifndef _ECORE_CON_EET_BASE_EO_CLASS_TYPE
#define _ECORE_CON_EET_BASE_EO_CLASS_TYPE

typedef Eo Ecore_Con_Eet_Base;

#endif

#ifndef _ECORE_CON_EET_BASE_EO_TYPES
#define _ECORE_CON_EET_BASE_EO_TYPES


#endif

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
EAPI void ecore_con_eet(Ecore_Con_Eet_Base *obj, const char *name, Eet_Data_Descriptor *edd);

#endif
