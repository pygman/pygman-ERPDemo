package pygman.invoice.util.base;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import pygman.invoice.auth.emp.vo.EmpModel;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport {
	//结果返回字符串常量
	protected static final String LIST = "list";
	protected static final String TO_LIST = "toList";
	protected static final String INPUT = "input";
	
	public String getActionName() {
		//对actionName初始化
		//DepAction	-> dep
		//1.获取执行类的类名
		//getClass().getName()--> cn.itcast.invoice.auth.dep.DepAction
		//getClass().getSimpleName() -->DepAction
		String actionStr = getClass().getSimpleName();
		//2.字符串截取  Dep
		actionStr = actionStr.substring(0, actionStr.length()-6);
		//3.转化Dep->dep		GoodsTypeAction    goodsType
		return actionStr.toLowerCase();
	}
	
	// 列表+查询(暂定)
	// 设置分页使用的变量
	// 当前页码值
	public Integer pageNum = 1;
	// 每页显示数
	public Integer pageCount = 10;
	// 最大页码值
	public Integer maxPageNum;
	// 数据总量
	public Integer dataTotal;

	protected void put(String name,Object obj){
		ActionContext.getContext().put(name, obj);
	}
	
	protected void putSession(String name,Object obj){
		ActionContext.getContext().getSession().put(name, obj);
	}
	
	protected Object get(String name){
		return ActionContext.getContext().get(name);
	}
	
	protected Object getSession(String name){
		return ActionContext.getContext().getSession().get(name);
	}
	
	protected EmpModel getLogin(){
		return (EmpModel) getSession("loginEm");
	}
	
	protected void setDataTotal(int dataTotal){
		this.dataTotal = dataTotal;
		maxPageNum = (dataTotal + pageCount -1 )/pageCount;
	}
	
	protected HttpServletRequest getRequest(){
		return ServletActionContext.getRequest();
	}
	
	protected HttpServletResponse getResponse(){
		return ServletActionContext.getResponse();
	}
	
}









