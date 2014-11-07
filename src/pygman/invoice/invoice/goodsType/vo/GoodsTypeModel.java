package pygman.invoice.invoice.goodsType.vo;

import java.util.HashMap;
import java.util.Map;
import pygman.invoice.util.format.FormatUtil;

public class GoodsTypeModel {

	
	private Long uuid;
	private Long name;
	private SupplierModel sm;
	private Set<GoodsModel> goodses;

	public Long getUuid() {
		return uuid;
	}

	public void setUuid(Long uuid) {
		this.uuid = uuid;
	}

	public Long getName() {
		return name;
	}

	public void setName(Long name) {
		this.name = name;
	}

	public SupplierModel getSm() {
		return sm;
	}

	public void setSm(SupplierModel sm) {
		this.sm = sm;
	}

	public Set<GoodsModel> getGoodses() {
		return goodses;
	}

	public void setGoodses(Set<GoodsModel> goodses) {
		this.goodses = goodses;
	}



}
