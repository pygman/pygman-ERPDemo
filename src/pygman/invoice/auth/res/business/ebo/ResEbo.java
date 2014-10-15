package pygman.invoice.auth.res.business.ebo;

import java.io.Serializable;
import java.util.List;

import pygman.invoice.auth.res.business.ebi.ResEbi;
import pygman.invoice.auth.res.dao.dao.ResDao;
import pygman.invoice.auth.res.vo.ResModel;
import pygman.invoice.util.base.BaseQueryModel;

public class ResEbo implements ResEbi{
	private ResDao resDao;
	public void setResDao(ResDao resDao) {
		this.resDao = resDao;
	}

	public void save(ResModel rm) {
		resDao.save(rm);
	}

	public void update(ResModel rm) {
		resDao.update(rm);
	}

	public void delete(ResModel rm) {
		resDao.delete(rm);
	}

	public ResModel get(Serializable uuid) {
		return resDao.get(uuid);
	}

	public List<ResModel> getAll() {
		return resDao.getAll();
	}

	public Integer getCount(BaseQueryModel qm) {
		return resDao.getCount(qm);
	}

	public List<ResModel> getAll(BaseQueryModel qm, Integer pageNum,Integer pageCount) {
		return resDao.getAll(qm,pageNum,pageCount);
	}

	public List<ResModel> getAllByEmpUuid(Long uuid) {
		return resDao.getAllByEmpUuid(uuid);
	}

	public List<String> getResesEmpUuid(Long uuid) {
		return resDao.getResesEmpUuid(uuid);
	}

}
