package pygman.invoice.invoice.bill.web;

import java.util.List;

import pygman.invoice.invoice.bill.business.ebi.BillEbi;
import pygman.invoice.invoice.bill.vo.BillModel;
import pygman.invoice.invoice.bill.vo.BillQueryModel;
import pygman.invoice.util.base.BaseAction;

public class BillAction extends BaseAction{
	public BillModel bm = new BillModel();
	public BillQueryModel bqm = new BillQueryModel();

	private BillEbi billEbi;
	public void setBillEbi(BillEbi billEbi) {
		this.billEbi = billEbi;
	}

	public String list(){
		setDataTotal(billEbi.getCount(bqm));
		List<BillModel> billList = billEbi.getAll(bqm,pageNum,pageCount);
		put("billList", billList);
		return LIST;
	}

	public String input(){
		if(bm.getUuid() != null){
			bm = billEbi.get(bm.getUuid());
		}
		return INPUT;
	}

	public String save(){
		if(bm.getUuid() == null){
			billEbi.save(bm);
		}else{
			billEbi.update(bm);
		}
		return TO_LIST;
	}

	public String delete(){
		billEbi.delete(bm);
		return TO_LIST;
	}
}
