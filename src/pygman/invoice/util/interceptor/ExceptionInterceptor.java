package pygman.invoice.util.interceptor;

import pygman.invoice.util.exception.AppException;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class ExceptionInterceptor extends AbstractInterceptor{

	public String intercept(ActionInvocation invocation) throws Exception {
		try {
			return invocation.invoke();
		} catch (AppException e) {
			//1.获取执行的Action对象
			ActionSupport action = (ActionSupport) invocation.getAction();
			//2.将异常信息加入到 Action类的ActionError中
			//3.国际化消息获取
			String msg = action.getText(e.getMessage());
			action.addActionError(msg);
			//跳转到对应的页面
			return "error";
		} catch (Exception e) {
			//记录日志，发送给程序员
			//ActionSupport action = (ActionSupport) invocation.getAction();
			//action.addActionError("少等，技术人员马上到位！");
			//记录日志
			e.printStackTrace();
			//return "error";
			return invocation.invoke();
		}
	}

}
