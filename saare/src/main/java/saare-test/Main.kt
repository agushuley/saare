package saare

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.boot.context.web.SpringBootServletInitializer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*
import javax.servlet.GenericServlet
import javax.servlet.Servlet
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest
import kotlin.platform.platformStatic

/**
 * Created by andriy on 14/07/15.
 */

@EnableAutoConfiguration
@Configuration
public open class Main : SpringBootServletInitializer() {
	@Bean
	open public fun dispatcherServlet(): Servlet {
		return Main;
	}

	override protected fun configure(application: SpringApplicationBuilder): SpringApplicationBuilder {
		return application.sources(javaClass<Main>());
	}

	companion object : GenericServlet() {
		override public fun service(req: ServletRequest, res: ServletResponse) {
			val req = req as HttpServletRequest
			res.setContentType("text/plain");

			var request = Request(req.getMethod(), req.getRequestURI())
			
			val filters = emptyList<Filter>()

			var handled = emptyList<Filter>()

			for (f: Filter in filters) {
				val requestResponse = f.processRequest(request);
				request = requestResponse.request ?: request
				handled += f
				if (requestResponse.response != null) {
					handleResponseWithFilters(requestResponse.response, filters, res)
					return
				}
			}
			val viewParams = find_view(req.getMethod()?: "", req.getRequestURI()?: "",
					req.getHeader("HttpHost") ?: "")
			val view = viewParams.first
			val params = viewParams.second
			val resp = view.handle(request, params)

			handleResponseWithFilters(resp, filters, res)
		}

		private fun handleResponseWithFilters(resp: Response, filters: List<Filter>, servletResponse: ServletResponse) {
			var resp = resp
			filters.forEach { (filter) -> resp = filter.handleResponse(resp) ?: resp }

			servletResponse.getWriter().print(resp.body)
			servletResponse.flushBuffer()
		}

		@platformStatic
		public fun main(args: Array<String>) {
			SpringApplication.run(arrayOf(javaClass<Main>()), args);
		}
	}
}

fun find_view(method: String, uri: String, hostName: String): Pair<View, Map<String, String>> = Pair(HelloView(listOf(HttpMethod.GET)), emptyMap<String, String>())

enum class HttpMethod {
	GET
	POST
}

interface View {
	fun handle(request: Request, params: Map<String, String>): Response
	val supportedMethods: List<HttpMethod>
}

class HelloView(
		override val supportedMethods: List<HttpMethod>
): View {

	override fun handle( request: Request, params: Map<String, String>) : Response {
		return Response(body = "Hello from saare, url = ${request.url}", contentType = "text/html")
	}
}

interface Filter {
	fun processRequest(req: Request): RequestResponse

	fun handleResponse(resp: Response): Response?
}

data class RequestResponse(val request: Request?, val response: Response?) {}

data open class Response(
		open val returnCode: Int = 200,
		open val contentType: String,
		open val body: String
)



data class Request(val method: String, val url: String) {
}
