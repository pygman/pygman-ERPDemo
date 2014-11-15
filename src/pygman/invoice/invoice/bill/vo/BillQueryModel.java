package pygman.invoice.invoice.bill.vo;


import pygman.invoice.util.format.FormatUtil;

public class BillQueryModel{
	//开始时间
	private Long start;
	private Long end;
	private Integer type;
	private Long supplierUuid;
	private Long goodsUuid;
	
	private String startView;
	private String endView;
	
	public Long getGoodsUuid() {
		return goodsUuid;
	}
	public void setGoodsUuid(Long goodsUuid) {
		this.goodsUuid = goodsUuid;
	}
	public String getStartView() {
		return startView;
	}
	public String getEndView() {
		return endView;
	}
	
	public Long getStart() {
		return start;
	}
	public void setStart(Long start) {
		this.start = start;
		this.startView = FormatUtil.formatDate(start);
	}
	public Long getEnd() {
		return end;
	}
	public void setEnd(Long end) {
		this.end = end;
		this.endView = FormatUtil.formatDate(end);
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
	
	
}
