package pygman.invoice.auth.dep.business.ebo;

import java.io.Serializable;
import java.util.List;

import pygman.invoice.auth.dep.business.ebi.DepEbi;
import pygman.invoice.auth.dep.dao.dao.DepDao;
import pygman.invoice.auth.dep.vo.DepModel;
import pygman.invoice.util.base.BaseQueryModel;
import pygman.invoice.util.exception.AppException;

public class DepEbo implements DepEbi{
	private DepDao depDao;
	public void setDepDao(DepDao depDao) {
		this.depDao = depDao;
	}

	public void save(DepModel dm) {
		//1.数据校验
		//不允许部门名称为空
		if(dm.getName().trim().length() == 0){
			//出问题了，尖叫
			//标准错误码
			throw new AppException("INFO_DEP_FIELD_NAME_IS_EMPTY");
		}
		//2.逻辑校验
		depDao.save(dm);
	}

	public void update(DepModel dm) {
		depDao.update(dm);
	}

	public void delete(DepModel dm) {
		depDao.delete(dm);
	}
	
	public DepModel get(Serializable uuid) {
		return depDao.get(uuid);
	}
	
	public List<DepModel> getAll() {
		return depDao.getAll();
	}
	
	public Integer getCount(BaseQueryModel qm) {
		return depDao.getCount(qm);
	}
	
	public List<DepModel> getAll(BaseQueryModel qm, Integer pageNum,Integer pageCount) {
		return depDao.getAll(qm,pageNum,pageCount);
	}
	
}
