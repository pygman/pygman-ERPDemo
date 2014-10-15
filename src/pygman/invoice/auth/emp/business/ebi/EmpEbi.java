package pygman.invoice.auth.emp.business.ebi;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import pygman.invoice.auth.emp.vo.EmpModel;
import pygman.invoice.util.base.BaseEbi;
@Transactional
public interface EmpEbi extends BaseEbi<EmpModel>{
	/**
	 * 根据用户名密码进行登陆，密码需要进行加密
	 * @param userName 用户名
	 * @param pwd 密码
	 * @param ip 登录IP地址
	 */
	public EmpModel login(String userName, String pwd ,String ip);
	/**
	 * 根据用户名密码修改密码信息
	 * @param userName 用户名
	 * @param pwd 原始密码
	 * @param newPwd 新密码
	 * @return 修改是否成功
	 */
	public boolean changePwd(String userName, String pwd, String newPwd);
	/**
	 * 添加新员工
	 * @param em 员工信息数据
	 * @param roleUuids 对应的角色uuid
	 */
	public void save(EmpModel em, Long[] roleUuids);
	
	public void update(EmpModel em, Long[] roleUuids);
	/**
	 * 获取指定部门所有员工信息
	 * @param depUuid 部门uuid
	 * @return
	 */
	public List<EmpModel> getAllByDepUuid(Long depUuid);
}
