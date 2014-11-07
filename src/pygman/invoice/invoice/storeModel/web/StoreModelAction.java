package pygman.invoice.invoice.storeModel.web;

import java.util.List;

import pygman.invoice.invoice.storeModel.business.ebi.StoreModelEbi;
import pygman.invoice.invoice.storeModel.vo.StoreModelModel;
import pygman.invoice.invoice.storeModel.vo.StoreModelQueryModel;
import pygman.invoice.util.base.BaseAction;

public class StoreModelAction extends BaseAction{
	public StoreModelModel sm = new StoreModelModel();
	public StoreModelQueryModel sqm = new StoreModelQueryModel();

	private StoreModelEbi storeModelEbi;
	public void setStoreModelEbi(StoreModelEbi storeModelEbi) {
		this.storeModelEbi = storeModelEbi;
	}

	public String list(){
		setDataTotal(storeModelEbi.getCount(sqm));
		List<StoreModelModel> storeModelList = storeModelEbi.getAll(sqm,pageNum,pageCount);
		put("storeModelList", storeModelList);
		return LIST;
	}

	public String input(){
		if(sm.getUuid() != null){
			sm = storeModelEbi.get(sm.getUuid());
		}
		return INPUT;
	}

	public String save(){
		if(sm.getUuid() == null){
			storeModelEbi.save(sm);
		}else{
			storeModelEbi.update(sm);
		}
		return TO_LIST;
	}

	public String delete(){
		storeModelEbi.delete(sm);
		return TO_LIST;
	}
}
