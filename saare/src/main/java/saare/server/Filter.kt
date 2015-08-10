package saare.server

interface Filter {
	fun processRequest(req: Request): RequestResponse

	fun handleResponse(resp: Response): Response?
}