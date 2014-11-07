package pygman.invoice.invoice.bill.vo;

import java.util.HashMap;
import java.util.Map;
import pygman.invoice.util.format.FormatUtil;

public class BillModel {

	
	private Long start;
	private Long end;
	private Integer type;
	private Long supplierUuid;
	private Long goodsUuid;
	private String startView;
	private String endView;

	public Long getStart() {
		return start;
	}

	public void setStart(Long start) {
		this.start = start;
	}

	public Long getEnd() {
		return end;
	}

	public void setEnd(Long end) {
		this.end = end;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Long getSupplierUuid() {
		return supplierUuid;
	}

	public void setSupplierUuid(Long supplierUuid) {
		this.supplierUuid = supplierUuid;
	}

	public Long getGoodsUuid() {
		return goodsUuid;
	}

	public void setGoodsUuid(Long goodsUuid) {
		this.goodsUuid = goodsUuid;
	}

	public String getStartView() {
		return startView;
	}

	public void setStartView(String startView) {
		this.startView = startView;
	}

	public String getEndView() {
		return endView;
	}

	public void setEndView(String endView) {
		this.endView = endView;
	}



}
