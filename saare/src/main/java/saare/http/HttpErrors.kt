package saare.http


public data class Http404(
		message: String? = null,
		cause: Exception? = null
) : HttpError(
		returnCode = 404, message = message, cause = cause
) {
	override val returnCode: Int = 404
}


public open class HttpError(
		open val returnCode: Int,
		message: String? = null,
		cause: Throwable? = null
) : Exception(message, cause){
}

public class Http500(
		message: String? = null,
		cause: Throwable? = null
) : HttpError(
		returnCode = 500, message = message, cause = cause
) {
}