package saare.server

import saare.*
import saare.server.HelloView
import saare.server.find_view
import saare.views.View

class SaareWorkflow {

	public fun handle(adapter: SaareWorkflowAdapter) {
		var request = adapter.createRequest()

		val filters = emptyList<Filter>()

		var handled = emptyList<Filter>()

		for (f: Filter in filters) {
			val requestResponse = f.processRequest(request);
			request = requestResponse.request ?: request
			handled += f
			if (requestResponse.response != null) {
				adapter.handleResponseWithFilters(requestResponse.response, filters)
				return
			}
		}
		val viewParams = find_view(request.method, request.url, request.hostName)
		val view = viewParams.first
		val params = viewParams.second
		val resp = view.handle(request, params)

		adapter.handleResponseWithFilters(resp, filters)
	}


}

