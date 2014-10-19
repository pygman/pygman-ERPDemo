package pygman.invoice.util.interceptor;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import pygman.invoice.auth.emp.vo.EmpModel;
import pygman.invoice.auth.res.business.ebi.ResEbi;
import pygman.invoice.auth.res.vo.ResModel;
import pygman.invoice.util.exception.AppException;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class AuthInterceptor extends AbstractInterceptor {
	private ResEbi resEbi;

	public void setResEbi(ResEbi resEbi) {
		this.resEbi = resEbi;
	}

	public String intercept(ActionInvocation invocation) throws Exception {
		String actionName = invocation.getAction().getClass().getName();
		String methodName = invocation.getProxy().getMethod();
		String totalName = actionName + "." + methodName;

		String allRes = ServletActionContext.getServletContext()
				.getAttribute("allRes").toString();
		if (!allRes.contains(totalName)) {
			return invocation.invoke();
		}

		EmpModel loginEm = (EmpModel) ActionContext.getContext().getSession()
				.get("loginEm");
		if (loginEm != null) {
			if (loginEm.getAllRes().contains(totalName)) {
				return invocation.invoke();
			} else {
				throw new AppException("对不起，您没有访问权限，请不要进行非法操作！");
			}
		} else {
			return "loginFail";
		}
	}

	public String intercept2(ActionInvocation invocation) throws Exception {
		String actionName = invocation.getAction().getClass().getName();
		String methodName = invocation.getProxy().getMethod();
		String totalName = actionName + "." + methodName;
		String allRes = ServletActionContext.getServletContext()
				.getAttribute("allRes").toString();
		if (!allRes.contains(totalName)) {
			return invocation.invoke();
		}
		EmpModel loginEm = (EmpModel) ActionContext.getContext().getSession()
				.get("loginEm");
		if (loginEm != null) {
			List<ResModel> resList = resEbi.getAllByEmpUuid(loginEm.getUuid());
			for (ResModel temp : resList) {
				if (temp.getUrl().equals(totalName)) {
					return invocation.invoke();
				}
			}
			throw new AppException("对不起，您没有访问权限，请不要进行非法操作！");
		} else {
			return "loginFail";
		}
	}

	public String intercept1(ActionInvocation invocation) throws Exception {

		String actionName = invocation.getAction().getClass().getName();
		String methodName = invocation.getProxy().getMethod();
		String totalName = actionName + "." + methodName;

		List<ResModel> resListAll = resEbi.getAll();
		for (ResModel rm : resListAll) {
			if (rm.getUrl().equals(totalName)) {
				EmpModel loginEm = (EmpModel) ActionContext.getContext()
						.getSession().get("loginEm");
				if (loginEm != null) {
					List<ResModel> resList = resEbi.getAllByEmpUuid(loginEm
							.getUuid());
					for (ResModel temp : resList) {
						if (temp.getUrl().equals(totalName)) {
							return invocation.invoke();
						}
					}
				}
				throw new AppException("对不起，您没有访问权限，请不要进行非法操作！");
			}
		}
		return invocation.invoke();
	}
}
