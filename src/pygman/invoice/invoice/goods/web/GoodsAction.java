package pygman.invoice.invoice.goods.web;

import java.util.List;

import pygman.invoice.invoice.goods.business.ebi.GoodsEbi;
import pygman.invoice.invoice.goods.vo.GoodsModel;
import pygman.invoice.invoice.goods.vo.GoodsQueryModel;
import pygman.invoice.util.base.BaseAction;

public class GoodsAction extends BaseAction{
	public GoodsModel gm = new GoodsModel();
	public GoodsQueryModel gqm = new GoodsQueryModel();

	private GoodsEbi goodsEbi;
	public void setGoodsEbi(GoodsEbi goodsEbi) {
		this.goodsEbi = goodsEbi;
	}

	public String list(){
		setDataTotal(goodsEbi.getCount(gqm));
		List<GoodsModel> goodsList = goodsEbi.getAll(gqm,pageNum,pageCount);
		put("goodsList", goodsList);
		return LIST;
	}

	public String input(){
		if(gm.getUuid() != null){
			gm = goodsEbi.get(gm.getUuid());
		}
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
