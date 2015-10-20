package saare_test.settings

import HelloView
import saare.views.View
import saare.views.ViewsResolverConfiguration

/**
 * User: andriyg
 * Date: 16/07/2015
 * Time: 13:24
 *
 *
 */
public open class Base  {

	public companion object : ViewsResolverConfiguration {
		override val views: List<View>
			get() {
				return emptyList<View>() + HelloView(uri="/test", supportedMethods = listOf("GET"))
			}
	}
}

