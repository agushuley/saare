package saare.views

import saare.views

public interface ViewsResolverConfiguration {
	val views: List<views.View>
		get

	companion object : DefaultConfig<ViewsResolverConfiguration>(object : ViewsResolverConfiguration {
		override val views: List<View>
			get() = emptyList()
	}
	)
}

public abstract class DefaultConfig<T>(var defaultValue: T) {
	val DEFAULT: T
		get() = defaultValue

	fun from(any: Any): T {
		return any as T ?: DEFAULT
	}

}


