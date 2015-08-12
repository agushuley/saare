package saare.server

import saare.*
import saare.http.Http500
import saare.http.HttpError
import saare.http.Response
import saare.http.TextResponse
import saare.server.find_view
import saare.views.View
import saare.views.ViewsResolverConfiguration

class SaareWorkflow(val configuration: Any) {

	public fun handle(adapter: SaareWorkflowAdapter) {
		var request = adapter.createRequest()

		val filters = emptyList<Filter>()

		var handled = emptyList<Filter>()

		val resp: Response
		try {
			for (f: Filter in filters) {
				val requestResponse = f.processRequest(request);
				request = requestResponse.request ?: request
				handled += f
				if (requestResponse.response != null) {
					adapter.handleResponseWithFilters(requestResponse.response, filters)
					return
				}
			}
			val viewParams = find_view(request.method, request.url, request.hostName, ViewsResolverConfiguration.from(configuration).views)
			val view = viewParams.first
			val params = viewParams.second
			resp = view.handle(request, params)
		} catch (e: Throwable) {
			when (e) {
				is HttpError ->
						resp = httpErrorToResponse(e)
				else ->
						resp = errorToServerError(e)
			}
		}

		adapter.handleResponseWithFilters(resp, filters)
	}

	private fun errorToServerError(e: Throwable): Response {
		return httpErrorToResponse(Http500(cause = e))
	}

	private fun httpErrorToResponse(e: HttpError): Response {

		return TextResponse(returnCode = e.returnCode)
		throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
	}


}

