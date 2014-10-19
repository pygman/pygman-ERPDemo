package pygman.invoice.util.base;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import pygman.invoice.auth.emp.vo.EmpModel;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport {
	protected static final String LIST = "list";
	protected static final String TO_LIST = "toList";
	protected static final String INPUT = "input";

	public String getActionName() {
		String actionStr = getClass().getSimpleName();
		actionStr = actionStr.substring(0, actionStr.length() - 6);
		return actionStr.toLowerCase();
	}

	public Integer pageNum = 1;
	public Integer pageCount = 10;
	public Integer maxPageNum;
	public Integer dataTotal;

	protected void put(String name, Object obj) {
		ActionContext.getContext().put(name, obj);
	}

	protected void putSession(String name, Object obj) {
		ActionContext.getContext().getSession().put(name, obj);
	}

	protected Object get(String name) {
		return ActionContext.getContext().get(name);
	}

	protected Object getSession(String name) {
		return ActionContext.getContext().getSession().get(name);
	}

	protected EmpModel getLogin() {
		return (EmpModel) getSession("loginEm");
	}

	protected void setDataTotal(int dataTotal) {
		this.dataTotal = dataTotal;
		maxPageNum = (dataTotal + pageCount - 1) / pageCount;
	}

	protected HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}

	protected HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}

}
