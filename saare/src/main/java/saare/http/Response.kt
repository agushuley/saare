package saare.http

public interface Response {
	abstract val returnCode: Int
		get
	abstract val contentType: String
		get
}