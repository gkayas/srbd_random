#ifndef _ECORE_PARENT_EO_H_
#define _ECORE_PARENT_EO_H_

#ifndef _ECORE_PARENT_EO_CLASS_TYPE
#define _ECORE_PARENT_EO_CLASS_TYPE

typedef Eo Ecore_Parent;

#endif

#ifndef _ECORE_PARENT_EO_TYPES
#define _ECORE_PARENT_EO_TYPES


#endif
#define ECORE_PARENT_CLASS ecore_parent_class_get()

EAPI const Eo_Class *ecore_parent_class_get(void) EINA_CONST;


#endif
