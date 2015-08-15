package saare

open class CIString(val str: String): Comparable<CIString> {
	override fun compareTo(other: CIString): Int {
		return str.compareTo(other.str, ignoreCase = true)
	}

	override fun equals(other: Any?): Boolean {
		return compareTo((other as? CIString) ?: EMPTY) == 0
	}

	override fun hashCode(): Int {
		return str.toLowerCase().hashCode()
	}

	override fun toString(): String {
		return str
	}

	companion object {
		val EMPTY = CIString("")
	}
}