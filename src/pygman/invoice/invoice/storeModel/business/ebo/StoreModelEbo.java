package pygman.invoice.invoice.storeModel.business.ebo;

import java.io.Serializable;
import java.util.List;

import pygman.invoice.invoice.storeModel.business.ebi.StoreModelEbi;
import pygman.invoice.invoice.storeModel.dao.dao.StoreModelDao;
import pygman.invoice.invoice.storeModel.vo.StoreModelModel;
import pygman.invoice.util.base.BaseQueryModel;

public class StoreModelEbo implements StoreModelEbi{
	private StoreModelDao storeModelDao;
	public void setStoreModelDao(StoreModelDao storeModelDao) {
		this.storeModelDao = storeModelDao;
	}

	public void save(StoreModelModel sm) {
		storeModelDao.save(sm);
	}

	public void update(StoreModelModel sm) {
		storeModelDao.update(sm);
	}

	public void delete(StoreModelModel sm) {
		storeModelDao.delete(sm);
	}

	public StoreModelModel get(Serializable uuid) {
		return storeModelDao.get(uuid);
	}

	public List<StoreModelModel> getAll() {
		return storeModelDao.getAll();
	}

	public Integer getCount(BaseQueryModel qm) {
		return storeModelDao.getCount(qm);
	}

	public List<StoreModelModel> getAll(BaseQueryModel qm, Integer pageNum,Integer pageCount) {
		return storeModelDao.getAll(qm,pageNum,pageCount);
	}

}
