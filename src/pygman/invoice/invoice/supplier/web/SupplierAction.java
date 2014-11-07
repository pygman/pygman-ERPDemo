package pygman.invoice.invoice.supplier.web;

import java.util.List;

import pygman.invoice.invoice.supplier.business.ebi.SupplierEbi;
import pygman.invoice.invoice.supplier.vo.SupplierModel;
import pygman.invoice.invoice.supplier.vo.SupplierQueryModel;
import pygman.invoice.util.base.BaseAction;

public class SupplierAction extends BaseAction{
	public SupplierModel sm = new SupplierModel();
	public SupplierQueryModel sqm = new SupplierQueryModel();

	private SupplierEbi supplierEbi;
	public void setSupplierEbi(SupplierEbi supplierEbi) {
		this.supplierEbi = supplierEbi;
	}

	public String list(){
		setDataTotal(supplierEbi.getCount(sqm));
		List<SupplierModel> supplierList = supplierEbi.getAll(sqm,pageNum,pageCount);
		put("supplierList", supplierList);
		return LIST;
	}

	public String input(){
		if(sm.getUuid() != null){
			sm = supplierEbi.get(sm.getUuid());
		}
		return INPUT;
	}

	public String save(){
		if(sm.getUuid() == null){
			supplierEbi.save(sm);
		}else{
			supplierEbi.update(sm);
		}
		return TO_LIST;
	}

	public String delete(){
		supplierEbi.delete(sm);
		return TO_LIST;
	}
}
