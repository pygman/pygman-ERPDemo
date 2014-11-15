package pygman.invoice.invoice.storeoper.business.ebo;


import pygman.invoice.invoice.storeoper.business.ebi.StoreOperEbi;
import pygman.invoice.invoice.storeoper.dao.dao.StoreOperDao;
import pygman.invoice.invoice.storeoper.vo.StoreOperModel;
import pygman.invoice.util.base.BaseQueryModel;

import java.io.Serializable;
import java.util.List;

public class StoreOperEbo implements StoreOperEbi {
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
