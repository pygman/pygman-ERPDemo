package pygman.invoice.auth.dep.web;

import java.util.List;

import pygman.invoice.auth.dep.business.ebi.DepEbi;
import pygman.invoice.auth.dep.vo.DepModel;
import pygman.invoice.auth.dep.vo.DepQueryModel;
import pygman.invoice.util.base.BaseAction;

public class DepAction extends BaseAction{
	public DepModel dm = new DepModel();
		public DepQueryModel dqm = new DepQueryModel();
	
	private DepEbi depEbi;
	public void setDepEbi(DepEbi depEbi) {
		this.depEbi = depEbi;
	}
	
	public String list(){
				setDataTotal(depEbi.getCount(dqm));
		List<DepModel> depList = depEbi.getAll(dqm,pageNum,pageCount);
		put("depList", depList);
		return LIST;
	}
	
		public String input(){
		if(dm.getUuid() != null){
			dm = depEbi.get(dm.getUuid());
		}
		return INPUT;
	}
	
		public String save(){
						if(dm.getUuid() == null){
						depEbi.save(dm);
		}else{
						depEbi.update(dm);
		}
				return TO_LIST;
	}
	
		public String delete(){
		depEbi.delete(dm);
		return TO_LIST;
	}
}



