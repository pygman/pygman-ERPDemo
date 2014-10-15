package pygman.invoice.auth.emp.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import pygman.invoice.auth.dep.business.ebi.DepEbi;
import pygman.invoice.auth.dep.vo.DepModel;
import pygman.invoice.auth.emp.business.ebi.EmpEbi;
import pygman.invoice.auth.emp.vo.EmpModel;
import pygman.invoice.auth.emp.vo.EmpQueryModel;
import pygman.invoice.auth.res.business.ebi.ResEbi;
import pygman.invoice.auth.role.business.ebi.RoleEbi;
import pygman.invoice.auth.role.vo.RoleModel;
import pygman.invoice.util.base.BaseAction;
import pygman.invoice.util.exception.AppException;

public class EmpAction extends BaseAction {
	public EmpModel em = new EmpModel();
	public EmpQueryModel eqm = new EmpQueryModel();

	private EmpEbi empEbi;
	private DepEbi depEbi;
	private RoleEbi roleEbi;
	private ResEbi resEbi;

	public void setResEbi(ResEbi resEbi) {
		this.resEbi = resEbi;
	}

	public void setRoleEbi(RoleEbi roleEbi) {
		this.roleEbi = roleEbi;
	}

	public void setDepEbi(DepEbi depEbi) {
		this.depEbi = depEbi;
	}

	public void setEmpEbi(EmpEbi empEbi) {
		this.empEbi = empEbi;
	}

	public String list() {

		List<DepModel> depList = depEbi.getAll();

		put("depList", depList);

		setDataTotal(empEbi.getCount(eqm));
		List<EmpModel> empList = empEbi.getAll(eqm, pageNum, pageCount);
		put("empList", empList);
		return LIST;
	}

	public String input() {

		List<RoleModel> roleList = roleEbi.getAll();
		put("roleList", roleList);

		List<DepModel> depList = depEbi.getAll();

		put("depList", depList);
		if (em.getUuid() != null) {
			em = empEbi.get(em.getUuid());

			roleUuids = new Long[em.getRoles().size()];
			int i = 0;
			for (RoleModel rm : em.getRoles()) {
				roleUuids[i++] = rm.getUuid();
			}
		}
		return INPUT;
	}

	public Long[] roleUuids;

	public String save() {
		if (em.getUuid() == null) {
			empEbi.save(em, roleUuids);
		} else {
			empEbi.update(em, roleUuids);
		}
		return TO_LIST;
	}

	public String delete() {
		empEbi.delete(em);
		return TO_LIST;
	}

	public String login() {

		HttpServletRequest request = getRequest();
		String loginIp = request.getHeader("x-forwarded-for");
		if (loginIp == null || loginIp.length() == 0
				|| "unknown".equalsIgnoreCase(loginIp)) {
			loginIp = request.getHeader("Proxy-Client-IP");
		}
		if (loginIp == null || loginIp.length() == 0
				|| "unknown".equalsIgnoreCase(loginIp)) {
			loginIp = request.getHeader("WL-Proxy-Client-IP");
		}
		if (loginIp == null || loginIp.length() == 0
				|| "unknown".equalsIgnoreCase(loginIp)) {
			loginIp = request.getRemoteAddr();
		}

		EmpModel loginEm = empEbi.login(em.getUserName(), em.getPwd(), loginIp);

		if (loginEm == null) {

			return "loginFail";
		} else {

			List<String> resList = resEbi.getResesEmpUuid(loginEm.getUuid());

			StringBuilder sbf = new StringBuilder();
			for (String url : resList) {
				sbf.append(url);
				sbf.append(";");
			}
			loginEm.setAllRes(sbf.toString());

			putSession("loginEm", loginEm);
			return "loginSuccess";
		}
	}

	public String logout() {

		putSession("loginEm", null);

		return "loginFail";
	}

	public String newPwd;

	public String changePwd() {

		boolean flag = empEbi.changePwd(getLogin().getUserName(), em.getPwd(),
				newPwd);
		if (flag) {

			return logout();

		} else {

			throw new AppException("INFO_EMP_CHANGEPWD_USER_AND_PWD_IS_ERROR");
		}

	}
}
