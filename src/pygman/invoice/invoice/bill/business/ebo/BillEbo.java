package pygman.invoice.invoice.bill.business.ebo;

import java.io.Serializable;
import java.util.List;

import pygman.invoice.invoice.bill.business.ebi.BillEbi;
import pygman.invoice.invoice.bill.dao.dao.BillDao;
import pygman.invoice.invoice.bill.vo.BillModel;
import pygman.invoice.util.base.BaseQueryModel;

public class BillEbo implements BillEbi{
	private BillDao billDao;
	public void setBillDao(BillDao billDao) {
		this.billDao = billDao;
	}

	public void save(BillModel bm) {
		billDao.save(bm);
	}

	public void update(BillModel bm) {
		billDao.update(bm);
	}

	public void delete(BillModel bm) {
		billDao.delete(bm);
	}

	public BillModel get(Serializable uuid) {
		return billDao.get(uuid);
	}

	public List<BillModel> getAll() {
		return billDao.getAll();
	}

	public Integer getCount(BaseQueryModel qm) {
		return billDao.getCount(qm);
	}

	public List<BillModel> getAll(BaseQueryModel qm, Integer pageNum,Integer pageCount) {
		return billDao.getAll(qm,pageNum,pageCount);
	}

}
