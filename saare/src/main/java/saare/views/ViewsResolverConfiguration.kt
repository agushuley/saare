package saare.views

import saare.DefaultConfig

public interface ViewsResolverConfiguration {
	val views: List<View>
		get

	companion object : DefaultConfig<ViewsResolverConfiguration>(defaultValue = object : ViewsResolverConfiguration {
			override val views: List<View>
				get() = emptyList()
		}, type = ViewsResolverConfiguration::class.java) {

	}
}


