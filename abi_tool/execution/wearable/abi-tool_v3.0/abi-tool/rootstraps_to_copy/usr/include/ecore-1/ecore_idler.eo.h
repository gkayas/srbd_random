#ifndef _ECORE_IDLER_EO_H_
#define _ECORE_IDLER_EO_H_

#ifndef _ECORE_IDLER_EO_CLASS_TYPE
#define _ECORE_IDLER_EO_CLASS_TYPE

typedef Eo Ecore_Idler;

#endif

#ifndef _ECORE_IDLER_EO_TYPES
#define _ECORE_IDLER_EO_TYPES


#endif
#define ECORE_IDLER_CLASS ecore_idler_class_get()

EAPI const Eo_Class *ecore_idler_class_get(void) EINA_CONST;

/**
 * @internal
 *
 * @brief Constructor.
 *
 * @param[in] data
 *
 * @ingroup Ecore_Idler
 */
EOAPI void  ecore_idler_constructor(Ecore_Task_Cb func, const void *data);


#endif
