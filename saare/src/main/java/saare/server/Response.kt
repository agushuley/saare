package saare.server

data open class Response(
		open val returnCode: Int = 200,
		open val contentType: String,
		open val body: String
)