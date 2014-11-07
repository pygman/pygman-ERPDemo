package pygman.invoice.invoice.goodsType.web;

import java.util.List;

import pygman.invoice.invoice.goodsType.business.ebi.GoodsTypeEbi;
import pygman.invoice.invoice.goodsType.vo.GoodsTypeModel;
import pygman.invoice.invoice.goodsType.vo.GoodsTypeQueryModel;
import pygman.invoice.util.base.BaseAction;

public class GoodsTypeAction extends BaseAction{
	public GoodsTypeModel gm = new GoodsTypeModel();
	public GoodsTypeQueryModel gqm = new GoodsTypeQueryModel();

	private GoodsTypeEbi goodsTypeEbi;
	public void setGoodsTypeEbi(GoodsTypeEbi goodsTypeEbi) {
		this.goodsTypeEbi = goodsTypeEbi;
	}

	public String list(){
		setDataTotal(goodsTypeEbi.getCount(gqm));
		List<GoodsTypeModel> goodsTypeList = goodsTypeEbi.getAll(gqm,pageNum,pageCount);
		put("goodsTypeList", goodsTypeList);
		return LIST;
	}

	public String input(){
		if(gm.getUuid() != null){
			gm = goodsTypeEbi.get(gm.getUuid());
		}
		return INPUT;
	}

	public String save(){
		if(gm.getUuid() == null){
			goodsTypeEbi.save(gm);
		}else{
			goodsTypeEbi.update(gm);
		}
		return TO_LIST;
	}

	public String delete(){
		goodsTypeEbi.delete(gm);
		return TO_LIST;
	}
}
