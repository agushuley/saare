package saare.http

public interface Response {
	val returnCode: Int
		get
	val contentType: String
		get
}