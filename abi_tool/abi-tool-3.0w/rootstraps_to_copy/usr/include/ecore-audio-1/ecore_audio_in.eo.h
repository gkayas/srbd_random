#ifndef _ECORE_AUDIO_IN_EO_H_
#define _ECORE_AUDIO_IN_EO_H_

#ifndef _ECORE_AUDIO_IN_EO_CLASS_TYPE
#define _ECORE_AUDIO_IN_EO_CLASS_TYPE

typedef Eo Ecore_Audio_In;

#endif

#ifndef _ECORE_AUDIO_IN_EO_TYPES
#define _ECORE_AUDIO_IN_EO_TYPES


#endif
#define ECORE_AUDIO_IN_CLASS ecore_audio_in_class_get()

EAPI const Eo_Class *ecore_audio_in_class_get(void) EINA_CONST;

/**
 * @brief Get the the preloaded state of the input
 *
 * @return true if the input should be cached, false otherwise
 *
 * @since 1.8
 *
 * @ingroup Ecore_Audio_In
 */
EOAPI Eina_Bool  ecore_audio_obj_in_preloaded_get(void);

/**
 * @brief Set the preloaded state of the input
 *
 * @param[in] preloaded true if the input should be cached, false otherwise
 *
 * @since 1.8
 *
 * @ingroup Ecore_Audio_In
 */
EOAPI void  ecore_audio_obj_in_preloaded_set(Eina_Bool preloaded);

/**
 * @brief Seek within the input
 *
 * @param[in] mode The seek mode. Is absolute with SEEK_SET, relative to the
 * current position with SEEK_CUR and relative to the end with SEEK_END.
 *
 * @return The current absolute position in seconds within the input
 *
 * @since 1.8
 *
 * @ingroup Ecore_Audio_In
 */
EOAPI double  ecore_audio_obj_in_seek(double offs, int mode);

/**
 * @brief Set the length of the input
 *
 * This function is only implemented by some classes (i.e.
 * ECORE_AUDIO_OBJ_IN_TONE_CLASS)
 *
 * @param[in] length The length of the input in seconds
 *
 * @since 1.8
 *
 * @ingroup Ecore_Audio_In
 */
EOAPI void  ecore_audio_obj_in_length_set(double length);

/**
 * @brief Set the playback speed of the input.
 *
 * @param[in] speed The speed, 1.0 is the default
 *
 * @since 1.8
 *
 * @ingroup Ecore_Audio_In
 */
EOAPI void  ecore_audio_obj_in_speed_set(double speed);

/**
 * @brief Get the playback speed of the input.
 *
 * @return The speed, 1.0 is the default
 *
 * @since 1.8
 *
 * @ingroup Ecore_Audio_In
 */
EOAPI double  ecore_audio_obj_in_speed_get(void);

/**
 * @brief Set the sample-rate of the input
 *
 * @param[in] samplerate The samplerate in Hz
 *
 * @since 1.8
 *
 * @ingroup Ecore_Audio_In
 */
EOAPI void  ecore_audio_obj_in_samplerate_set(int samplerate);

/**
 * @brief Get the he sample-rate of the input
 *
 * @return The samplerate in Hz
 *
 * @since 1.8
 *
 * @ingroup Ecore_Audio_In
 */
EOAPI int  ecore_audio_obj_in_samplerate_get(void);

/**
 * @brief Set the amount of channels the input has
 *
 * @param[in] channels The number of channels
 *
 * @since 1.8
 *
 * @ingroup Ecore_Audio_In
 */
EOAPI void  ecore_audio_obj_in_channels_set(int channels);

/**
 * @brief Get the amount of channels the input has
 *
 * @return The number of channels
 *
 * @since 1.8
 *
 * @ingroup Ecore_Audio_In
 */
EOAPI int  ecore_audio_obj_in_channels_get(void);

/**
 * @brief Set the looped state of the input
 *
 * If the input is looped and reaches the end it will start from the beginning
 * again. At the same time the event @ref ECORE_AUDIO_EV_IN_LOOPED will be
 * emitted
 *
 * @param[in] looped true if the input should be looped, false otherwise
 *
 * @since 1.8
 *
 * @ingroup Ecore_Audio_In
 */
EOAPI void  ecore_audio_obj_in_looped_set(Eina_Bool looped);

/**
 * @brief Get the looped state of the input
 *
 * @return true if the input should be looped, false otherwise
 *
 * @since 1.8
 *
 * @ingroup Ecore_Audio_In
 */
EOAPI Eina_Bool  ecore_audio_obj_in_looped_get(void);

/**
 * @brief Get the length of the input
 *
 * @return The length of the input in seconds
 *
 * @since 1.8
 *
 * @ingroup Ecore_Audio_In
 */
EOAPI double  ecore_audio_obj_in_length_get(void);

/**
 * @brief Get the output that this input is attached to
 *
 * @return The output
 *
 * @since 1.8
 *
 * @ingroup Ecore_Audio_In
 */
EOAPI Ecore_Audio * ecore_audio_obj_in_output_get(void);

/**
 * @brief Get the remaining time of the input
 *
 * @return The amount of time in seconds left to play
 *
 * @since 1.8
 *
 * @ingroup Ecore_Audio_In
 */
EOAPI double  ecore_audio_obj_in_remaining_get(void);

/**
 * @brief Read from the input
 *
 * @param[in] len The amount of samples to read
 *
 * @return The amount of samples written to buf
 *
 * @since 1.8
 *
 * @ingroup Ecore_Audio_In
 */
EOAPI ssize_t  ecore_audio_obj_in_read(void *buf, size_t len);

/**
 * @brief Internal read function
 *
 * @param[in] len The amount of samples to read
 *
 * @return The amount of samples written to buf
 *
 * @since 1.8
 *
 * @ingroup Ecore_Audio_In
 */
EOAPI ssize_t  ecore_audio_obj_in_read_internal(void *buf, size_t len);

EOAPI extern const Eo_Event_Description _ECORE_AUDIO_IN_EVENT_IN_LOOPED;
EOAPI extern const Eo_Event_Description _ECORE_AUDIO_IN_EVENT_IN_STOPPED;
EOAPI extern const Eo_Event_Description _ECORE_AUDIO_IN_EVENT_IN_SAMPLERATE_CHANGED;

/** Called when an input has looped.
 *
 * @ingroup Ecore_Audio_In
 */
#define ECORE_AUDIO_IN_EVENT_IN_LOOPED (&(_ECORE_AUDIO_IN_EVENT_IN_LOOPED))

/** Called when an input has stopped playing.
 *
 * @ingroup Ecore_Audio_In
 */
#define ECORE_AUDIO_IN_EVENT_IN_STOPPED (&(_ECORE_AUDIO_IN_EVENT_IN_STOPPED))

/** Called when the input samplerate has changed.
 *
 * @ingroup Ecore_Audio_In
 */
#define ECORE_AUDIO_IN_EVENT_IN_SAMPLERATE_CHANGED (&(_ECORE_AUDIO_IN_EVENT_IN_SAMPLERATE_CHANGED))

#endif
