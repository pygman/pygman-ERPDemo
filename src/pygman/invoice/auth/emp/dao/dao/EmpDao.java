package pygman.invoice.auth.emp.dao.dao;

import java.util.List;

import pygman.invoice.auth.emp.vo.EmpModel;
import pygman.invoice.util.base.BaseDao;

public interface EmpDao extends BaseDao<EmpModel>{
	public EmpModel getByUserNameAndPwd(String userName, String pwd);

	public boolean updatePwd(String userName, String pwd, String newPwd);

	public List<EmpModel> getAllByDep(Long depUuid);
}
