package pygman.invoice.util.interceptor;

import pygman.invoice.util.exception.AppException;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class ExceptionInterceptor extends AbstractInterceptor {

	public String intercept(ActionInvocation invocation) throws Exception {
		try {
			return invocation.invoke();
		} catch (AppException e) {
			ActionSupport action = (ActionSupport) invocation.getAction();
			String msg = action.getText(e.getMessage());
			action.addActionError(msg);
			return "error";
		} catch (Exception e) {
			e.printStackTrace();
			return invocation.invoke();
		}
	}

}
