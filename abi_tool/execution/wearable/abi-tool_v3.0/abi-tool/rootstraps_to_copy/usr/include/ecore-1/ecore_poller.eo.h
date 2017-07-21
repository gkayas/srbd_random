#ifndef _ECORE_POLLER_EO_H_
#define _ECORE_POLLER_EO_H_

#ifndef _ECORE_POLLER_EO_CLASS_TYPE
#define _ECORE_POLLER_EO_CLASS_TYPE

typedef Eo Ecore_Poller;

#endif

#ifndef _ECORE_POLLER_EO_TYPES
#define _ECORE_POLLER_EO_TYPES


#endif
#define ECORE_POLLER_CLASS ecore_poller_class_get()

EAPI const Eo_Class *ecore_poller_class_get(void) EINA_CONST;

/**
 * @brief Changes the polling interval rate of the poller.
 *
 * This allows the changing of a poller's polling interval. It is useful when
 * you want to alter a poll rate without deleting and re-creating a poller.
 *
 * @if MOBILE @since_tizen 2.3 @elseif WEARABLE @since_tizen 2.3.1 @endif
 *
 * @param[in] interval The tick interval; must be a power of 2 and <= 32768.
 *
 * @return true on success, false on failure.
 *
 * @ingroup Ecore_Poller
 */
EOAPI Eina_Bool  ecore_poller_interval_set(int interval);

/**
 * @brief Gets the polling interval rate of the poller.
 *
 * @if MOBILE @since_tizen 2.3 @elseif WEARABLE @since_tizen 2.3.1 @endif
 *
 * @return The tick interval; must be a power of 2 and <= 32768.
 *
 * @ingroup Ecore_Poller
 */
EOAPI int  ecore_poller_interval_get(void);

/**
 * @internal
 *
 * @brief Constructor with parameters for Ecore Poller.
 *
 * @param[in] interval
 * @param[in] func
 * @param[in] data
 *
 * @ingroup Ecore_Poller
 */
EOAPI void  ecore_poller_constructor(Ecore_Poller_Type type, int interval, Ecore_Task_Cb func, const void *data);


#endif
