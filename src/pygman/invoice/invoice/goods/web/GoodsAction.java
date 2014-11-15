package pygman.invoice.invoice.goods.web;

import pygman.invoice.invoice.goods.business.ebi.GoodsEbi;
import pygman.invoice.invoice.goods.vo.GoodsModel;
import pygman.invoice.invoice.goods.vo.GoodsQueryModel;
import pygman.invoice.invoice.goodstype.business.ebi.GoodsTypeEbi;
import pygman.invoice.invoice.goodstype.vo.GoodsTypeModel;
import pygman.invoice.invoice.supplier.business.ebi.SupplierEbi;
import pygman.invoice.invoice.supplier.vo.SupplierModel;
import pygman.invoice.util.base.BaseAction;

import java.util.List;

public class GoodsAction extends BaseAction {
	public GoodsModel gm = new GoodsModel();
	public GoodsQueryModel gqm = new GoodsQueryModel();

	private GoodsEbi goodsEbi;
	private SupplierEbi supplierEbi;
	private GoodsTypeEbi goodsTypeEbi;
	
	public void setGoodsTypeEbi(GoodsTypeEbi goodsTypeEbi) {
		this.goodsTypeEbi = goodsTypeEbi;
	}

	public void setSupplierEbi(SupplierEbi supplierEbi) {
		this.supplierEbi = supplierEbi;
	}

	public void setGoodsEbi(GoodsEbi goodsEbi) {
		this.goodsEbi = goodsEbi;
	}

	public String list(){
		//加载所有供应商信息
		List<SupplierModel> supplierList = supplierEbi.getAll();
		put("supplierList",supplierList);
		setDataTotal(goodsEbi.getCount(gqm));
		List<GoodsModel> goodsList = goodsEbi.getAll(gqm,pageNum,pageCount);
		put("goodsList", goodsList);
		return LIST;
	}

	public String input(){
		//加载具有商品类别信息的供应商全信息
		List<SupplierModel> supplierList = supplierEbi.getAllUnion();
		put("supplierList",supplierList);
		Long supplierUuid = null;
		if(gm.getUuid() != null){
			gm = goodsEbi.get(gm.getUuid());
			//修改时，将商品类别信息更改为当前商品对应的供应商的全商品信息
			supplierUuid = gm.getGtm().getSm().getUuid();
		}else{
			//加载第一个供应商的商品类别全信息
			supplierUuid = supplierList.get(0).getUuid();
		}
		List<GoodsTypeModel> gtmList = goodsTypeEbi.getAllBySupplierUuid(supplierUuid);
		put("gtmList",gtmList);
		return INPUT;
	}

	public String save(){
		if(gm.getUuid() == null){
			goodsEbi.save(gm);
		}else{
			goodsEbi.update(gm);
		}
		return TO_LIST;
	}

	public String delete(){
		goodsEbi.delete(gm);
		return TO_LIST;
	}
}
