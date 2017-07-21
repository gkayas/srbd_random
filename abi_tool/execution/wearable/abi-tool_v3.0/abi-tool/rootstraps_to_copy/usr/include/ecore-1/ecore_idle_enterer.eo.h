#ifndef _ECORE_IDLE_ENTERER_EO_H_
#define _ECORE_IDLE_ENTERER_EO_H_

#ifndef _ECORE_IDLE_ENTERER_EO_CLASS_TYPE
#define _ECORE_IDLE_ENTERER_EO_CLASS_TYPE

typedef Eo Ecore_Idle_Enterer;

#endif

#ifndef _ECORE_IDLE_ENTERER_EO_TYPES
#define _ECORE_IDLE_ENTERER_EO_TYPES


#endif
#define ECORE_IDLE_ENTERER_CLASS ecore_idle_enterer_class_get()

EAPI const Eo_Class *ecore_idle_enterer_class_get(void) EINA_CONST;

/**
 * @internal
 *
 * @brief Contructor. Will insert the handler at the
 * beginning of the list.
 *
 * @param[in] data
 *
 * @ingroup Ecore_Idle_Enterer
 */
EOAPI void  ecore_idle_enterer_before_constructor(Ecore_Task_Cb func, const void *data);

/**
 * @internal
 *
 * @brief Contructor. Will insert the handler at the end of
 * the list.
 *
 * @param[in] data
 *
 * @ingroup Ecore_Idle_Enterer
 */
EOAPI void  ecore_idle_enterer_after_constructor(Ecore_Task_Cb func, const void *data);


#endif
