package saare.http

import saare.http.Response

public data class TextResponse(
		override var returnCode: Int = 200,
		override var contentType: String = "text/plain",
		var contentEncoding:String = "UTF-8",
		val body: String? = null,
		var stream: Sequence<String>? = null
) : Response {
}