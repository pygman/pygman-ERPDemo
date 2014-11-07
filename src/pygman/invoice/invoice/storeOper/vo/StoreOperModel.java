package pygman.invoice.invoice.storeOper.vo;

import java.util.HashMap;
import java.util.Map;
import pygman.invoice.util.format.FormatUtil;

public class StoreOperModel {

	
	private Long uuid;
	private Integer num;
	private Long operTime;
	private Integer type;
	private String operTimeView;
	private String typeView;
	private EmpModel em;
	private StoreModel sm;
	private GoodsModel gm;
	private OrderDetailModel odm;

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

	public Long getOperTime() {
		return operTime;
	}

	public void setOperTime(Long operTime) {
		this.operTime = operTime;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getOperTimeView() {
		return operTimeView;
	}

	public void setOperTimeView(String operTimeView) {
		this.operTimeView = operTimeView;
	}

	public String getTypeView() {
		return typeView;
	}

	public void setTypeView(String typeView) {
		this.typeView = typeView;
	}

	public EmpModel getEm() {
		return em;
	}

	public void setEm(EmpModel em) {
		this.em = em;
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

	public OrderDetailModel getOdm() {
		return odm;
	}

	public void setOdm(OrderDetailModel odm) {
		this.odm = odm;
	}



}
