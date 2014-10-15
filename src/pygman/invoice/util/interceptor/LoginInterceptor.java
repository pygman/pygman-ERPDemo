package pygman.invoice.util.interceptor;

import pygman.invoice.auth.emp.vo.EmpModel;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class LoginInterceptor extends AbstractInterceptor {
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		String invockName = invocation.getProxy().getActionName();
		if ("pages_login".equals(invockName)) {
			return invocation.invoke();
		}

		String actionName = invocation.getAction().getClass().getName();
		String methodName = invocation.getProxy().getMethod();
		String totalName = actionName + "." + methodName;
		if ("pygman.invoice.auth.emp.web.EmpAction.login".equals(totalName)) {
			return invocation.invoke();
		}

		EmpModel loginEm = (EmpModel) ActionContext.getContext().getSession()
				.get("loginEm");
		if (loginEm != null) {
			return invocation.invoke();
		}
		return "loginFail";
	}

}
