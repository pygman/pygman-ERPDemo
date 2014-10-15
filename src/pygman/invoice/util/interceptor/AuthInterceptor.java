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

//权限拦截器
public class AuthInterceptor extends AbstractInterceptor{
	//使用的是struts-spring-plugin.jar提供的自动装配功能完成了属性值的注入
	private ResEbi resEbi;
	public void setResEbi(ResEbi resEbi) {
		this.resEbi = resEbi;
	}
	
	//方案三：
	//将登陆人对应的可操作资源放入到登录人信息中，使用时直接获取，减少查询数据库的次数
	public String intercept(ActionInvocation invocation) throws Exception {
		String actionName = invocation.getAction().getClass().getName();		//全路径名
		String methodName = invocation.getProxy().getMethod();
		String totalName = actionName+"."+methodName;

		String allRes = ServletActionContext.getServletContext().getAttribute("allRes").toString();
		if(!allRes.contains(totalName)){
			return invocation.invoke();
		}
		
		//1.判断当前用户是否能对该操作进行操作（可以进行，放行，没有对应的资源，拦截）
		EmpModel loginEm = (EmpModel)ActionContext.getContext().getSession().get("loginEm");
		if(loginEm != null){
			//获取当前登陆人对应的可操作资源
			if(loginEm.getAllRes().contains(totalName)){
				return invocation.invoke();
			}else{
				//运行到此处说明没有权限
				throw new AppException("对不起，您没有访问权限，请不要进行非法操作！");
			}
		}else{
			return "loginFail";
		}
	}
	
	//方案二：
	//使用监听器设计思想，将全资源在服务器启动时初始化，减少获取全资源的次数
	public String intercept2(ActionInvocation invocation) throws Exception {
		//1.获取当前操作
		String actionName = invocation.getAction().getClass().getName();		//全路径名
		String methodName = invocation.getProxy().getMethod();
		String totalName = actionName+"."+methodName;
		//2.判断该操作是否需要拦截（在资源中出现的需要拦截，未出现的放行）
		//从ServletContext范围中取出全资源
		String allRes = ServletActionContext.getServletContext().getAttribute("allRes").toString();
		//如果你操作的资源不在全资源中，放行
		if(!allRes.contains(totalName)){
			return invocation.invoke();
		}
		//3.判断当前用户是否能对该操作进行操作（可以进行，放行，没有对应的资源，拦截）
		EmpModel loginEm = (EmpModel)ActionContext.getContext().getSession().get("loginEm");
		//2.2根据uuid获取当前用户所有的可操作资源
		if(loginEm != null){
			List<ResModel> resList = resEbi.getAllByEmpUuid(loginEm.getUuid());
			//根据数据进行校验
			//3.如果比对失败，拦截（转入到拦截页面）
			//检测当前调用的操作totalName的值是否出现在resList中对象的url中
			for(ResModel temp : resList){
				if(temp.getUrl().equals(totalName)){
					return invocation.invoke();
				}
			}
			//运行到此处说明没有权限
			throw new AppException("对不起，您没有访问权限，请不要进行非法操作！");
		}else{
			return "loginFail";
		}
	}
	
	
	
	
	//方案一：
	/*
	1.获取当前操作
	2.判断该操作是否需要拦截（在资源中出现的需要拦截，未出现的放行）
	3.判断当前用户是否能对该操作进行操作（可以进行，放行，没有对应的资源，拦截）
	*/
	public String intercept1(ActionInvocation invocation) throws Exception {
		
		//1.首先获取本次用户提交的操作
		//所谓用户提交的操作应该是对应Action类中的某个具体方法
		String actionName = invocation.getAction().getClass().getName();		//全路径名
		String methodName = invocation.getProxy().getMethod();
		String totalName = actionName+"."+methodName;
		
		//1.5.进行权限校验首先应该明确数据库中保存的是需要进行校验的
		//如果该资源部在控制资源范围内，则直接放行
		List<ResModel> resListAll = resEbi.getAll();
		//如果全资源中未出现该资源，放行
		for(ResModel rm:resListAll){
			if(rm.getUrl().equals(totalName)){
				//2.将该操作与当前登陆用户对应的（角色对应的资源）进行比对
				//根据登陆用户获取其可操作的所有资源
				//2.1获取当前登陆用户的uuid
				EmpModel loginEm = (EmpModel)ActionContext.getContext().getSession().get("loginEm");
				//2.2根据uuid获取当前用户所有的可操作资源
				if(loginEm != null){
					List<ResModel> resList = resEbi.getAllByEmpUuid(loginEm.getUuid());
					//根据数据进行校验
					//3.如果比对失败，拦截（转入到拦截页面）
					//检测当前调用的操作totalName的值是否出现在resList中对象的url中
					for(ResModel temp : resList){
						if(temp.getUrl().equals(totalName)){
							return invocation.invoke();
						}
					}
				}
				//运行到此处，说明，校验失败，拦截
				throw new AppException("对不起，您没有访问权限，请不要进行非法操作！");
			}
		}
		return invocation.invoke();
	}
}
//重点：项目中任意数据读取到后，第一件事，null判定





