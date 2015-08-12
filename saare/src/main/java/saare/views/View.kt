package saare.views

import saare.server.HttpMethod
import saare.server.Request
import saare.server.Response

interface View {
	val uri: String

	fun handle(request: Request, params: Map<String, String>): Response
	val supportedMethods: List<String>
}