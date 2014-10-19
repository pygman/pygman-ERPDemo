package pygman.invoice.util.exception;

public class AppException extends RuntimeException {
	public AppException() {
		super();
	}

	public AppException(String msg) {
		super(msg);
	}

	public AppException(String msg, Throwable t) {
		super(msg, t);
	}

	public AppException(Throwable t) {
		super(t);
	}
}
