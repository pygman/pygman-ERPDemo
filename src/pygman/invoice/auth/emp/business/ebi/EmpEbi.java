package pygman.invoice.auth.emp.business.ebi;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import pygman.invoice.auth.emp.vo.EmpModel;
import pygman.invoice.util.base.BaseEbi;
@Transactional
public interface EmpEbi extends BaseEbi<EmpModel>{

	public EmpModel login(String userName, String pwd ,String ip);

	public boolean changePwd(String userName, String pwd, String newPwd);

	public void save(EmpModel em, Long[] roleUuids);

	public void update(EmpModel em, Long[] roleUuids);

	public List<EmpModel> getAllByDepUuid(Long depUuid);

}
