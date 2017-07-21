#ifndef _ECORE_JOB_EO_H_
#define _ECORE_JOB_EO_H_

#ifndef _ECORE_JOB_EO_CLASS_TYPE
#define _ECORE_JOB_EO_CLASS_TYPE

typedef Eo Ecore_Job;

#endif

#ifndef _ECORE_JOB_EO_TYPES
#define _ECORE_JOB_EO_TYPES


#endif
#define ECORE_JOB_CLASS ecore_job_class_get()

EAPI const Eo_Class *ecore_job_class_get(void) EINA_CONST;

/**
 * @internal
 *
 * @brief Constructor.
 *
 * @param[in] data
 *
 * @ingroup Ecore_Job
 */
EOAPI void  ecore_job_constructor(Ecore_Cb func, const void *data);


#endif
