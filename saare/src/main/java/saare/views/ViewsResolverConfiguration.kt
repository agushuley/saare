package saare.views

import saare.DefaultConfig
import saare.views

public interface ViewsResolverConfiguration {
	val views: List<views.View>
		get

	companion object : DefaultConfig<ViewsResolverConfiguration>(defaultValue = object : ViewsResolverConfiguration {
			override val views: List<View>
				get() = emptyList()
		}, type = javaClass<ViewsResolverConfiguration>()) {

	}
}


