package saare.http

import saare.CIString
import java.util.*

data class Request(
		val method: String,
		val url: String,
		val hostName: String,
		var headers: Map<HttpHeader, String>
)

