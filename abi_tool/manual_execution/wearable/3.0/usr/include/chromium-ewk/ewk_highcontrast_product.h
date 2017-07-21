#ifndef ewk_highcontrast_h
#define ewk_highcontrast_h

#include <Eina.h>

#include <tizen.h>

#ifdef __cplusplus
extern "C" {
#endif

/**
* Set enable/disable highcontrast.
*
* If highcontrast enabled, highcontrast controller listen system highcontrast configuration.
*
* @param enabled enable/disable set for high contrast
*/
EXPORT_API void ewk_highcontrast_enabled_set(Eina_Bool enabled);

/**
 * Returns current hightcontrast settings.
 *
 * @return EINA_TRUE if highcontrast enabled
 */
EXPORT_API Eina_Bool ewk_highcontrast_enabled_get();

/**
 * Add highcontrast forbidden URL.
 *
 * If the current page's url contains this url, it should not use highcontrast filter
 *
 * @param url URL that should not apply hightcontrast filter
 * @return EINA_TRUE if successfully added
 */
EXPORT_API Eina_Bool ewk_highcontrast_forbidden_url_add(const char* url);

/**
 * Remove the specific highcontrast forbidden URL.
 *
 * @param url URL that already in the forbidden url list
 * @return EINA_TRUE if successfully removed
 */
EXPORT_API Eina_Bool ewk_highcontrast_forbidden_url_remove(const char* url);


#ifdef __cplusplus
}
#endif

#endif // ewk_highcontrast_h
