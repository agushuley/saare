package saare.server

import saare.http.Request
import saare.http.Response

interface Filter {
	fun processRequest(req: Request): RequestResponse

	fun handleResponse(resp: Response): Response?
}