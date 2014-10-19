package pygman.invoice.auth.emp.business.ebo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import pygman.invoice.auth.emp.business.ebi.EmpEbi;
import pygman.invoice.auth.emp.dao.dao.EmpDao;
import pygman.invoice.auth.emp.vo.EmpModel;
import pygman.invoice.auth.role.vo.RoleModel;
import pygman.invoice.util.base.BaseQueryModel;
import pygman.invoice.util.format.MD5Utils;

public class EmpEbo implements EmpEbi{
	private EmpDao empDao;
	public void setEmpDao(EmpDao empDao) {
		this.empDao = empDao;
	}

	public void save(EmpModel em) {
	}

	public void update(EmpModel em) {
	}

	public void delete(EmpModel em) {
		empDao.delete(em);
	}

	public EmpModel get(Serializable uuid) {
		return empDao.get(uuid);
	}

	public List<EmpModel> getAll() {
		return empDao.getAll();
	}

	public Integer getCount(BaseQueryModel qm) {
		return empDao.getCount(qm);
	}

	public List<EmpModel> getAll(BaseQueryModel qm, Integer pageNum,Integer pageCount) {
		return empDao.getAll(qm,pageNum,pageCount);
	}

	public EmpModel login(String userName, String pwd,String ip) {
				pwd = MD5Utils.md5(pwd);
		EmpModel loginEm = empDao.getByUserNameAndPwd(userName,pwd);
		if(loginEm != null){
						loginEm.setLastLoginIp(ip);
						loginEm.setLastLoginTime(System.currentTimeMillis());
						loginEm.setLoginTimes(loginEm.getLoginTimes()+1);
		}
		return loginEm;
	}

	public boolean changePwd(String userName, String pwd, String newPwd) {
		pwd = MD5Utils.md5(pwd);
		newPwd = MD5Utils.md5(newPwd);
		return empDao.updatePwd(userName,pwd,newPwd);
	}

	public void save(EmpModel em, Long[] roleUuids) {
				em.setPwd(MD5Utils.md5(em.getPwd()));
				em.setLastLoginTime(System.currentTimeMillis());
				em.setLastLoginIp("-");
				em.setLoginTimes(0);
				Set<RoleModel> roles = new HashSet<RoleModel>();
				for(Long uuid:roleUuids){
			RoleModel temp = new RoleModel();
			temp.setUuid(uuid);
			roles.add(temp);
		}
		em.setRoles(roles);
		empDao.save(em);
	}

	public void update(EmpModel em, Long[] roleUuids) {
										EmpModel temp = empDao.get(em.getUuid());
				temp.setName(em.getName());
		temp.setEmail(em.getEmail());
		temp.setTele(em.getTele());
		temp.setAddress(em.getAddress());
		temp.setDm(em.getDm());
						Set<RoleModel> roles = new HashSet<RoleModel>();
				for(Long uuid:roleUuids){
			RoleModel rm = new RoleModel();
			rm.setUuid(uuid);
			roles.add(rm);
		}
		temp.setRoles(roles);
	}

	public List<EmpModel> getAllByDepUuid(Long depUuid) {
		return empDao.getAllByDep(depUuid);
	}
}
