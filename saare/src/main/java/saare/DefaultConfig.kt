package saare

import kotlin.reflect.KClass

public abstract class DefaultConfig<T>(val defaultValue: T, val type: Class<T>) {
	val DEFAULT: T
		get() = defaultValue

	fun from(any: Any): T {
		when {
			type.isInstance(any) ->
					return type.cast(any)
			else ->
					return defaultValue
		}
	}

}