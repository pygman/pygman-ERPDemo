package pygman.invoice.invoice.storedetail.web;

import pygman.invoice.invoice.storedetail.business.ebi.StoreDetailEbi;
import pygman.invoice.invoice.storedetail.vo.StoreDetailModel;
import pygman.invoice.invoice.storedetail.vo.StoreDetailQueryModel;
import pygman.invoice.util.base.BaseAction;

import java.util.List;

public class StoreDetailAction extends BaseAction {
	public StoreDetailModel sm = new StoreDetailModel();
	public StoreDetailQueryModel sqm = new StoreDetailQueryModel();

	private StoreDetailEbi storeDetailEbi;
	public void setStoreDetailEbi(StoreDetailEbi storeDetailEbi) {
		this.storeDetailEbi = storeDetailEbi;
	}

	public String list(){
		setDataTotal(storeDetailEbi.getCount(sqm));
		List<StoreDetailModel> storeDetailList = storeDetailEbi.getAll(sqm,pageNum,pageCount);
		put("storeDetailList", storeDetailList);
		return LIST;
	}

	public String input(){
		if(sm.getUuid() != null){
			sm = storeDetailEbi.get(sm.getUuid());
		}
		return INPUT;
	}

	public String save(){
		if(sm.getUuid() == null){
			storeDetailEbi.save(sm);
		}else{
			storeDetailEbi.update(sm);
		}
		return TO_LIST;
	}

	public String delete(){
		storeDetailEbi.delete(sm);
		return TO_LIST;
	}
}
