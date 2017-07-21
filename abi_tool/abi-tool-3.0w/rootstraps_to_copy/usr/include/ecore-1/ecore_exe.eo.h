#ifndef _ECORE_EXE_EO_H_
#define _ECORE_EXE_EO_H_

#ifndef _ECORE_EXE_EO_CLASS_TYPE
#define _ECORE_EXE_EO_CLASS_TYPE

typedef Eo Ecore_Exe;

#endif

#ifndef _ECORE_EXE_EO_TYPES
#define _ECORE_EXE_EO_TYPES


#endif
#define ECORE_EXE_CLASS ecore_exe_class_get()

EAPI const Eo_Class *ecore_exe_class_get(void) EINA_CONST;

/**
 * @brief Control the command that's executed. FIXME: May need a split/rename.
 *
 * @param[in] exe_cmd The command to execute.
 * @param[in] flags The execution flags.
 *
 * @ingroup Ecore_Exe
 */
EOAPI void  ecore_obj_exe_command_set(const char *exe_cmd, Ecore_Exe_Flags flags);

/**
 * @brief Control the command that's executed. FIXME: May need a split/rename.
 *
 * @param[out] exe_cmd The command to execute.
 * @param[out] flags The execution flags.
 *
 * @ingroup Ecore_Exe
 */
EOAPI void  ecore_obj_exe_command_get(const char **exe_cmd, Ecore_Exe_Flags *flags);

EOAPI extern const Eo_Event_Description _ECORE_EXE_EVENT_DATA_GET;
EOAPI extern const Eo_Event_Description _ECORE_EXE_EVENT_DATA_ERROR;

/**
 * No description
 */
#define ECORE_EXE_EVENT_DATA_GET (&(_ECORE_EXE_EVENT_DATA_GET))

/**
 * No description
 */
#define ECORE_EXE_EVENT_DATA_ERROR (&(_ECORE_EXE_EVENT_DATA_ERROR))

#endif
