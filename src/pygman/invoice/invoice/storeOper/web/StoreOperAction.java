package pygman.invoice.invoice.storeoper.web;

import java.util.List;

import pygman.invoice.invoice.storeoper.business.ebi.StoreOperEbi;
import pygman.invoice.invoice.storeoper.vo.StoreOperModel;
import pygman.invoice.invoice.storeoper.vo.StoreOperQueryModel;
import pygman.invoice.util.base.BaseAction;

public class StoreOperAction extends BaseAction{
	public StoreOperModel sm = new StoreOperModel();
	public StoreOperQueryModel sqm = new StoreOperQueryModel();

	private StoreOperEbi storeOperEbi;
	public void setStoreOperEbi(StoreOperEbi storeOperEbi) {
		this.storeOperEbi = storeOperEbi;
	}

	public String list(){
		setDataTotal(storeOperEbi.getCount(sqm));
		List<StoreOperModel> storeOperList = storeOperEbi.getAll(sqm,pageNum,pageCount);
		put("storeOperList", storeOperList);
		return LIST;
	}

	public String input(){
		if(sm.getUuid() != null){
			sm = storeOperEbi.get(sm.getUuid());
		}
		return INPUT;
	}

	public String save(){
		if(sm.getUuid() == null){
			storeOperEbi.save(sm);
		}else{
			storeOperEbi.update(sm);
		}
		return TO_LIST;
	}

	public String delete(){
		storeOperEbi.delete(sm);
		return TO_LIST;
	}
}
