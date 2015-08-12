package saare.http

import saare.http.Response

/**
 * Created by andriy on 12/08/15.
 */

public data class BinaryResponse(
		override var returnCode: Int = 200,
		override var contentType: String = "text/plain",
		val body: ByteArray? = null,
		var stream: Sequence<ByteArray>? = null
) : Response {
}