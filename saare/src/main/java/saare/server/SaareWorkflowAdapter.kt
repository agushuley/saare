package saare.server

import saare.http.Request
import saare.http.Response
import javax.servlet.GenericServlet
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest

interface SaareWorkflowAdapter {
	open fun handleResponseWithFilters(resp: Response, filters: List<Filter>)
	open fun createRequest(): Request
}
