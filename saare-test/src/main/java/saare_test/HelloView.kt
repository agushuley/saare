import saare.server.HttpMethod
import saare.server.Request
import saare.server.Response
import saare.views.View

class HelloView(
		override val supportedMethods: List<String>,
		override val uri: String
): View {
	override fun handle( request: Request, params: Map<String, String>) : Response {
		return Response(body = "Hello from saare, url = ${request.url}", contentType = "text/html")
	}
}