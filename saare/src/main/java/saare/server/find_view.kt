package saare.server

import saare.http.Http404
import saare.http.Request
import saare.http.Response
import saare.views.View
import javax.swing.text

fun find_view(method: String, uri: String, hostName: String, views: List<View>): Pair<View, Map<String, String>> {
	for (view in views) {
		if (view.supportedMethods.contains(method) && uri == view.uri) {
			return Pair(view, emptyMap())
		}
	}
	throw Http404("Can't find view $method:$uri in $views")
}