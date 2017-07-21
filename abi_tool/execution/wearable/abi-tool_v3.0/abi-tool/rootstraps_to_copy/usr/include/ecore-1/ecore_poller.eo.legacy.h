#ifndef _ECORE_POLLER_EO_LEGACY_H_
#define _ECORE_POLLER_EO_LEGACY_H_

#ifndef _ECORE_POLLER_EO_CLASS_TYPE
#define _ECORE_POLLER_EO_CLASS_TYPE

typedef Eo Ecore_Poller;

#endif

#ifndef _ECORE_POLLER_EO_TYPES
#define _ECORE_POLLER_EO_TYPES


#endif

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
EAPI Eina_Bool ecore_poller_poller_interval_set(Ecore_Poller *obj, int interval);

/**
 * @brief Gets the polling interval rate of the poller.
 *
 * @if MOBILE @since_tizen 2.3 @elseif WEARABLE @since_tizen 2.3.1 @endif
 *
 * @return The tick interval; must be a power of 2 and <= 32768.
 *
 * @ingroup Ecore_Poller
 */
EAPI int ecore_poller_poller_interval_get(const Ecore_Poller *obj);

#endif
