package saare;

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
public class HttpServlet : GenericServlet() {
	private val workflow = SaareWorkflow()

	override
	public fun service( req: ServletRequest, resp: ServletResponse ) {
		val req = req as HttpServletRequest
		val httpResp = resp as HttpServletResponse

		var adapter = object : SaareWorkflowAdapter {
			override fun handleResponseWithFilters(resp: Response, filters: List<Filter>) {
				httpResp.setContentType(resp.contentType)
				httpResp.getWriter().write(resp.body)
			}

			override fun createRequest(): Request {
				return Request(req.getMethod() ?: "", req.getRequestURI() ?: "", req.getHeader("HttpHost") ?: "")
			}
		}

		this.workflow.handle(adapter)
	}
}
