package pygman.invoice.invoice.storedetail.vo;


import pygman.invoice.invoice.goods.vo.GoodsModel;
import pygman.invoice.invoice.store.vo.StoreModel;

public class StoreDetailModel {
	private Long uuid;
	private Integer num;
	
	private StoreModel sm;
	private GoodsModel gm;
	public Long getUuid() {
		return uuid;
	}
	public void setUuid(Long uuid) {
		this.uuid = uuid;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public StoreModel getSm() {
		return sm;
	}
	public void setSm(StoreModel sm) {
		this.sm = sm;
	}
	public GoodsModel getGm() {
		return gm;
	}
	public void setGm(GoodsModel gm) {
		this.gm = gm;
	}
	
}
