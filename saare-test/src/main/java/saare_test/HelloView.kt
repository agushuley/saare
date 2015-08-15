import saare.server.HttpMethod
import saare.http.Request
import saare.http.Response
import saare.http.TextResponse
import saare.views.View

class HelloView(
		override val supportedMethods: List<String>,
		override val uri: String
): View {
	override fun handle(request: Request, params: Map<String, String>) : Response {
		return TextResponse(body = "Hello from saare, request = ${request}", contentType = "text/html")
	}
}