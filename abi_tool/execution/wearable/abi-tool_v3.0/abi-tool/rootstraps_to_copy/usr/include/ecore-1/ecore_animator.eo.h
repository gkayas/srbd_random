#ifndef _ECORE_ANIMATOR_EO_H_
#define _ECORE_ANIMATOR_EO_H_

#ifndef _ECORE_ANIMATOR_EO_CLASS_TYPE
#define _ECORE_ANIMATOR_EO_CLASS_TYPE

typedef Eo Ecore_Animator;

#endif

#ifndef _ECORE_ANIMATOR_EO_TYPES
#define _ECORE_ANIMATOR_EO_TYPES


#endif
#define ECORE_ANIMATOR_CLASS ecore_animator_class_get()

EAPI const Eo_Class *ecore_animator_class_get(void) EINA_CONST;

/**
 * @internal
 *
 * @brief Constructor.
 *
 * @param[in] func
 * @param[in] data
 *
 * @ingroup Ecore_Animator
 */
EOAPI void  ecore_animator_timeline_constructor(double runtime, Ecore_Timeline_Cb func, const void *data);

/**
 * @internal
 *
 * @brief Constructor.
 *
 * @param[in] data
 *
 * @ingroup Ecore_Animator
 */
EOAPI void  ecore_animator_constructor(Ecore_Task_Cb func, const void *data);


#endif
