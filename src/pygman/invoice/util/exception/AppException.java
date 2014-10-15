package pygman.invoice.util.exception;

public class AppException extends RuntimeException{
	//四个构造方法
	public AppException(){
		super();
	}
	public AppException(String msg){
		super(msg);
	}
	public AppException(String msg,Throwable t){
		super(msg,t);
	}
	public AppException(Throwable t){
		super(t);
	}
}
