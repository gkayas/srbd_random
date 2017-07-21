#ifndef _ECORE_AUDIO_OUT_EO_H_
#define _ECORE_AUDIO_OUT_EO_H_

#ifndef _ECORE_AUDIO_OUT_EO_CLASS_TYPE
#define _ECORE_AUDIO_OUT_EO_CLASS_TYPE

typedef Eo Ecore_Audio_Out;

#endif

#ifndef _ECORE_AUDIO_OUT_EO_TYPES
#define _ECORE_AUDIO_OUT_EO_TYPES


#endif
#define ECORE_AUDIO_OUT_CLASS ecore_audio_out_class_get()

EAPI const Eo_Class *ecore_audio_out_class_get(void) EINA_CONST;

/**
 * @brief Attach an input to an output
 *
 * @param[in] input The input to attach to the output
 *
 * @return true if the input was attached, false otherwise
 *
 * @since 1.8
 *
 * @ingroup Ecore_Audio_Out
 */
EOAPI Eina_Bool  ecore_audio_obj_out_input_attach(Ecore_Audio_In *input);

/**
 * @brief Detach an input from an output
 *
 * @param[in] input The input to detach to the output
 *
 * @return true if the input was detached, false otherwise
 *
 * @since 1.8
 *
 * @ingroup Ecore_Audio_Out
 */
EOAPI Eina_Bool  ecore_audio_obj_out_input_detach(Ecore_Audio_In *input);

/**
 * @brief Returns the list of all attached inputs
 *
 * @return A list of the inputs that are attached to the output
 *
 * @since 1.8
 *
 * @ingroup Ecore_Audio_Out
 */
EOAPI Eina_List * ecore_audio_obj_out_inputs_get(void);


#endif
