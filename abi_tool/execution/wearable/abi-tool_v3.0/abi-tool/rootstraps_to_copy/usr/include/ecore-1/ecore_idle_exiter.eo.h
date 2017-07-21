#ifndef _ECORE_IDLE_EXITER_EO_H_
#define _ECORE_IDLE_EXITER_EO_H_

#ifndef _ECORE_IDLE_EXITER_EO_CLASS_TYPE
#define _ECORE_IDLE_EXITER_EO_CLASS_TYPE

typedef Eo Ecore_Idle_Exiter;

#endif

#ifndef _ECORE_IDLE_EXITER_EO_TYPES
#define _ECORE_IDLE_EXITER_EO_TYPES


#endif
#define ECORE_IDLE_EXITER_CLASS ecore_idle_exiter_class_get()

EAPI const Eo_Class *ecore_idle_exiter_class_get(void) EINA_CONST;

/**
 * @internal
 *
 * @brief Constructor.
 *
 * @param[in] data
 *
 * @ingroup Ecore_Idle_Exiter
 */
EOAPI void  ecore_idle_exiter_constructor(Ecore_Task_Cb func, const void *data);


#endif
