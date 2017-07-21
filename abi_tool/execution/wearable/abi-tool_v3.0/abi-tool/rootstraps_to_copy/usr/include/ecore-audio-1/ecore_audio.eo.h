#ifndef _ECORE_AUDIO_EO_H_
#define _ECORE_AUDIO_EO_H_

#ifndef _ECORE_AUDIO_EO_CLASS_TYPE
#define _ECORE_AUDIO_EO_CLASS_TYPE

typedef Eo Ecore_Audio;

#endif

#ifndef _ECORE_AUDIO_EO_TYPES
#define _ECORE_AUDIO_EO_TYPES


#endif
#define ECORE_AUDIO_CLASS ecore_audio_class_get()

EAPI const Eo_Class *ecore_audio_class_get(void) EINA_CONST;

/**
 * @brief Get the source of the object
 *
 * @return the source to set to (i.e. file, URL, device)
 *
 * @since 1.8
 *
 * @ingroup Ecore_Audio
 */
EOAPI const char * ecore_audio_obj_source_get(void);

/**
 * @brief Set the source of the object
 *
 * What sources are supported depends on the actual object. For example, the
 * libsndfile class accepts WAV, OGG, FLAC files as source.
 *
 * @param[in] source the source to set to (i.e. file, URL, device)
 *
 * @return true if the source was set correctly (i.e. the file was opened),
 * EINA_FALSE otherwise
 *
 * @since 1.8
 *
 * @ingroup Ecore_Audio
 */
EOAPI Eina_Bool  ecore_audio_obj_source_set(const char *source);

/**
 * @brief Get the format of the object
 *
 * After setting the source if the format was ECORE_AUDIO_FORMAT_AUTO this
 * function will now return the actual format.
 *
 * @return the format of the object
 *
 * @since 1.8
 *
 * @ingroup Ecore_Audio
 */
EOAPI Ecore_Audio_Format  ecore_audio_obj_format_get(void);

/**
 * @brief Set the format of the object
 *
 * What formats are supported depends on the actual object. Default is
 * ECORE_AUDIO_FORMAT_AUTO
 *
 * @param[in] format the format of the object
 *
 * @return true if the format was supported, false otherwise
 *
 * @since 1.8
 *
 * @ingroup Ecore_Audio
 */
EOAPI Eina_Bool  ecore_audio_obj_format_set(Ecore_Audio_Format format);

/**
 * @brief Set the virtual IO functions
 *
 * @param[in] data user data to pass to the VIO functions
 * @param[in] free_func this function takes care to clean up @c data when he
 * VIO is destroyed. NULL means do nothing.
 *
 * @since 1.8
 *
 * @ingroup Ecore_Audio
 */
EOAPI void  ecore_audio_obj_vio_set(Ecore_Audio_Vio *vio, void *data, eo_key_data_free_func free_func);

/**
 * @brief Set the name of the object
 *
 * @param[in] name
 *
 * @since 1.8
 *
 * @ingroup Ecore_Audio
 */
EOAPI void  ecore_audio_obj_name_set(const char *name);

/** Get the name of the object
 *
 * @since 1.8
 *
 * @ingroup Ecore_Audio
 */
EOAPI const char * ecore_audio_obj_name_get(void);

/**
 * @brief Set the pause state of the object
 *
 * @param[in] paused true if object is paused, false if not
 *
 * @since 1.8
 *
 * @ingroup Ecore_Audio
 */
EOAPI void  ecore_audio_obj_paused_set(Eina_Bool paused);

/**
 * @brief Get the pause state of the object
 *
 * @return true if object is paused, false if not
 *
 * @since 1.8
 *
 * @ingroup Ecore_Audio
 */
EOAPI Eina_Bool  ecore_audio_obj_paused_get(void);

/**
 * @brief Set the volume of the object
 *
 * @param[in] volume the volume
 *
 * @since 1.8
 *
 * @ingroup Ecore_Audio
 */
EOAPI void  ecore_audio_obj_volume_set(double volume);

/**
 * @brief Get the volume of the object
 *
 * @return the volume
 *
 * @since 1.8
 *
 * @ingroup Ecore_Audio
 */
EOAPI double  ecore_audio_obj_volume_get(void);


#endif
