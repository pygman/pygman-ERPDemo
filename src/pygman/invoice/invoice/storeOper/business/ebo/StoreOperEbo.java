package pygman.invoice.invoice.storeOper.business.ebo;

import java.io.Serializable;
import java.util.List;

import pygman.invoice.invoice.storeOper.business.ebi.StoreOperEbi;
import pygman.invoice.invoice.storeOper.dao.dao.StoreOperDao;
import pygman.invoice.invoice.storeOper.vo.StoreOperModel;
import pygman.invoice.util.base.BaseQueryModel;

public class StoreOperEbo implements StoreOperEbi{
	private StoreOperDao storeOperDao;
	public void setStoreOperDao(StoreOperDao storeOperDao) {
		this.storeOperDao = storeOperDao;
	}

	public void save(StoreOperModel sm) {
		storeOperDao.save(sm);
	}

	public void update(StoreOperModel sm) {
		storeOperDao.update(sm);
	}

	public void delete(StoreOperModel sm) {
		storeOperDao.delete(sm);
	}

	public StoreOperModel get(Serializable uuid) {
		return storeOperDao.get(uuid);
	}

	public List<StoreOperModel> getAll() {
		return storeOperDao.getAll();
	}

	public Integer getCount(BaseQueryModel qm) {
		return storeOperDao.getCount(qm);
	}

	public List<StoreOperModel> getAll(BaseQueryModel qm, Integer pageNum,Integer pageCount) {
		return storeOperDao.getAll(qm,pageNum,pageCount);
	}

}
