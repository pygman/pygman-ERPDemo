package pygman.invoice.invoice.store.web;

import java.util.List;

import pygman.invoice.auth.emp.business.ebi.EmpEbi;
import pygman.invoice.auth.emp.vo.EmpModel;
import pygman.invoice.invoice.store.business.ebi.StoreEbi;
import pygman.invoice.invoice.store.vo.StoreModel;
import pygman.invoice.invoice.store.vo.StoreQueryModel;
import pygman.invoice.util.base.BaseAction;
import sun.security.action.GetLongAction;

public class StoreAction extends BaseAction {
	public StoreModel sm = new StoreModel();
	public StoreQueryModel sqm = new StoreQueryModel();

	private StoreEbi storeEbi;
	private EmpEbi empEbi;
	
	public void setEmpEbi(EmpEbi empEbi) {
		this.empEbi = empEbi;
	}

	public void setStoreEbi(StoreEbi storeEbi) {
		this.storeEbi = storeEbi;
	}

	public String list(){
		setDataTotal(storeEbi.getCount(sqm));
		List<StoreModel> storeList = storeEbi.getAll(sqm,pageNum,pageCount);
		put("storeList", storeList);
		return LIST;
	}

	public String input(){
		List<EmpModel> empList = empEbi.getAllByDepUuid(getLogin().getDm().getUuid());
		put("empList",empList);
		if(sm.getUuid() != null){
			sm = storeEbi.get(sm.getUuid());
		}
		return INPUT;
	}

	public String save(){
		if(sm.getUuid() == null){
			storeEbi.save(sm);
		}else{
			storeEbi.update(sm);
		}
		return TO_LIST;
	}

	public String delete(){
		storeEbi.delete(sm);
		return TO_LIST;
	}
	
}
