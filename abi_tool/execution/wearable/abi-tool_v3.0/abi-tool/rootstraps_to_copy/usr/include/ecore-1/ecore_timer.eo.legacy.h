#ifndef _ECORE_TIMER_EO_LEGACY_H_
#define _ECORE_TIMER_EO_LEGACY_H_

#ifndef _ECORE_TIMER_EO_CLASS_TYPE
#define _ECORE_TIMER_EO_CLASS_TYPE

typedef Eo Ecore_Timer;

#endif

#ifndef _ECORE_TIMER_EO_TYPES
#define _ECORE_TIMER_EO_TYPES


#endif
/**
 * @brief Timers are objects that will call a given callback at some point in
 * the future.
 *
 * They may also optionall repeat themselves if the timer callback returns
 * true. If it does not they will be automatically deleted and never called
 * again. Timers require the ecore mainloop to be running and functioning
 * properly. They do not guarantee exact timing, but try to work on a "best
 * effort basis.
 *
 * @ingroup Ecore_Timer
 */

/**
 * @brief Change the interval the timer ticks off. If set during a timer call,
 * this will affect the next interval.
 *
 * @if MOBILE @since_tizen 2.3 @elseif WEARABLE @since_tizen 2.3.1 @endif
 *
 * @param[in] in The new interval in seconds
 *
 * @ingroup Ecore_Timer
 */
EAPI void ecore_timer_interval_set(Ecore_Timer *obj, double in);

/**
 * @brief Get the interval the timer ticks on.
 *
 * @if MOBILE @since_tizen 2.3 @elseif WEARABLE @since_tizen 2.3.1 @endif
 *
 * @return The new interval in seconds
 *
 * @ingroup Ecore_Timer
 */
EAPI double ecore_timer_interval_get(const Ecore_Timer *obj);

/**
 * @brief Get the pending time regarding a timer.
 *
 * @if MOBILE @since_tizen 2.3 @elseif WEARABLE @since_tizen 2.3.1 @endif
 *
 * @ingroup Ecore_Timer
 */
EAPI double ecore_timer_pending_get(const Ecore_Timer *obj);

/**
 * @brief Reset a timer to its full interval. This effectively makes the timer
 * start ticking off from zero now.
 *
 * @if MOBILE @since_tizen 2.3 @elseif WEARABLE @since_tizen 2.3.1 @endif
 *
 * @since 1.2
 *
 * @ingroup Ecore_Timer
 */
EAPI void ecore_timer_reset(Ecore_Timer *obj);

/**
 * @brief Add some delay for the next occurrence of a timer. This doesn't
 * affect the interval of a timer.
 *
 * @if MOBILE @since_tizen 2.3 @elseif WEARABLE @since_tizen 2.3.1 @endif
 *
 * @param[in] add The amount of time to delay the timer by in seconds
 *
 * @ingroup Ecore_Timer
 */
EAPI void ecore_timer_delay(Ecore_Timer *obj, double add);

#endif
