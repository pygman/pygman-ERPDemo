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
		//加密处理
		pwd = MD5Utils.md5(pwd);
		EmpModel loginEm = empDao.getByUserNameAndPwd(userName,pwd);
		if(loginEm != null){
			//修改登录IP
			loginEm.setLastLoginIp(ip);
			//修改登录时间
			loginEm.setLastLoginTime(System.currentTimeMillis());
			//修改登录次数
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
		//对密码加密
		em.setPwd(MD5Utils.md5(em.getPwd()));
		//设置新添加的用户的最后登录时间为注册时间
		em.setLastLoginTime(System.currentTimeMillis());
		//设置最后登录IP为-
		em.setLastLoginIp("-");
		//设置登录总次数0
		em.setLoginTimes(0);
		//添加员工对角色的关系
		Set<RoleModel> roles = new HashSet<RoleModel>();
		//arr->set
		for(Long uuid:roleUuids){
			RoleModel temp = new RoleModel();
			temp.setUuid(uuid);
			roles.add(temp);
		}
		em.setRoles(roles);
		empDao.save(em);
	}

	public void update(EmpModel em, Long[] roleUuids) {
		//修改功能对应的数据不完整
		//先查询原始数据，将没有修改的数据set到被修改的对象中
		//快照更新
		//1.获取数据，加载快照
		EmpModel temp = empDao.get(em.getUuid());
		//2.设置要修改的字段
		temp.setName(em.getName());
		temp.setEmail(em.getEmail());
		temp.setTele(em.getTele());
		temp.setAddress(em.getAddress());
		temp.setDm(em.getDm());
		//错误	temp.getDm().setUuid(em.getDm().getUuid());
		//添加员工对角色的关系
		Set<RoleModel> roles = new HashSet<RoleModel>();
		//arr->set
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
