package saare;

import saare.http.*
import saare.server.*
import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * User: andriyg
 * Date: 10/08/2015
 * Time: 14:02
 */
public class HttpServlet(val configuration: Any) : GenericServlet() {
	private val workflow = SaareWorkflow(configuration)

	override
	public fun service( req: ServletRequest, resp: ServletResponse ) {
		val req = req as HttpServletRequest
		val httpResp = resp as HttpServletResponse

		var adapter = object : SaareWorkflowAdapter {
			override fun handleResponseWithFilters(resp: Response, filters: List<Filter>) {
				httpResp.setStatus(resp.returnCode)
				httpResp.setContentType(resp.contentType)
				when (resp) {
					is TextResponse -> {
						httpResp.setCharacterEncoding(resp.contentEncoding)
						var writer = httpResp.getWriter()
						when {
							resp.body != null ->
								writer.write(resp.body)
							resp.stream != null ->
								for (s in resp.stream!!.iterator()) {
									writer.write(s)
								}
						}
					}
					is BinaryResponse -> {
						when {
							resp.body != null ->
								httpResp.getOutputStream().write(resp.body)
							resp.stream != null ->
								for (s in resp.stream!!.iterator()) {
									httpResp.getOutputStream().write(s)
								}
						}
					}
				}
			}

			override fun createRequest(): Request {
				var headers = emptyMap<HttpHeader, String>()
				if (req.getHeaderNames() != null) {
					for (header in req.getHeaderNames()) {
						val header = HttpHeader.header(header)
						headers += header to req.getHeader(header.str)
					}
				}
				val hostName = headers.getOrElse(HttpHeader.HOST) { "" }
				return Request(method = req.getMethod() ?: "", url = req.getRequestURI() ?: "", hostName = hostName, headers = headers)
			}
		}

		this.workflow.handle(adapter)
	}
}

