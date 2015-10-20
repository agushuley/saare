package saare.views

import saare.http.Request
import saare.http.Response

public interface View {
	val uri: String

	fun handle(request: Request, params: Map<String, String>): Response
	val supportedMethods: List<String>
}