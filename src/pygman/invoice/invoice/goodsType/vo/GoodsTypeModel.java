package pygman.invoice.invoice.goodstype.vo;

import pygman.invoice.invoice.goods.vo.GoodsModel;
import pygman.invoice.invoice.supplier.vo.SupplierModel;

import java.util.Set;

public class GoodsTypeModel {
	private Long uuid;
	private String name;
	
	private SupplierModel sm;
	private Set<GoodsModel> goodses;
	
	public Set<GoodsModel> getGoodses() {
		return goodses;
	}
	public void setGoodses(Set<GoodsModel> goodses) {
		this.goodses = goodses;
	}
	public Long getUuid() {
		return uuid;
	}
	public void setUuid(Long uuid) {
		this.uuid = uuid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public SupplierModel getSm() {
		return sm;
	}
	public void setSm(SupplierModel sm) {
		this.sm = sm;
	}
	
}
