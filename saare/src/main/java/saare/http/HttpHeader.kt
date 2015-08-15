package saare.http

import saare.CIString

/**
 * Created by andriy on 15/08/15.
 */

public class HttpHeader private constructor(name: String): CIString(name) {
	companion object {
		private var interns = emptyMap<HttpHeader, HttpHeader>()
		synchronized fun header(name: String): HttpHeader {
			val header = HttpHeader(name = name)
			if (!interns.containsKey(header)) {
				interns += header to header
			}
			return interns.get(header) ?: header
		}

		public val HOST: HttpHeader = header("Host")
	}
}