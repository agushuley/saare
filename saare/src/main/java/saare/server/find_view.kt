package saare.server

import saare.server.Request
import saare.server.Response
import saare.views.View

fun find_view(method: String, uri: String, hostName: String): Pair<View, Map<String, String>> = Pair(HelloView(listOf(HttpMethod.GET)), emptyMap<String, String>())

class HelloView(
		override val supportedMethods: List<HttpMethod>
): View {

	override fun handle( request: Request, params: Map<String, String>) : Response {
		return Response(body = "Hello from saare, url = ${request.url}", contentType = "text/html")
	}
}