package pygman.invoice.invoice.order.vo;

import java.util.HashMap;
import java.util.Map;
import pygman.invoice.util.format.FormatUtil;

public class OrderModel {

	
	private Long uuid;
	private String orderNum;
	private Integer totalNum;
	private Double totalPrice;
	private Long createTime;
	private Long checkTime;
	private Long completeTime;
	private Integer orderType;
	private Integer type;
	private String totalPriceView;
	private String createTimeView;
	private String checkTimeView;
	private String completeTimeView;
	private String orderTypeView;
	private String typeView;
	private SupplierModel sm;
	private EmpModel creater;
	private EmpModel checker;
	private EmpModel completer;
	private Set<OrderDetailModel> odms;

	public Long getUuid() {
		return uuid;
	}

	public void setUuid(Long uuid) {
		this.uuid = uuid;
	}

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public Integer getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public Long getCheckTime() {
		return checkTime;
	}

	public void setCheckTime(Long checkTime) {
		this.checkTime = checkTime;
	}

	public Long getCompleteTime() {
		return completeTime;
	}

	public void setCompleteTime(Long completeTime) {
		this.completeTime = completeTime;
	}

	public Integer getOrderType() {
		return orderType;
	}

	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getTotalPriceView() {
		return totalPriceView;
	}

	public void setTotalPriceView(String totalPriceView) {
		this.totalPriceView = totalPriceView;
	}

	public String getCreateTimeView() {
		return createTimeView;
	}

	public void setCreateTimeView(String createTimeView) {
		this.createTimeView = createTimeView;
	}

	public String getCheckTimeView() {
		return checkTimeView;
	}

	public void setCheckTimeView(String checkTimeView) {
		this.checkTimeView = checkTimeView;
	}

	public String getCompleteTimeView() {
		return completeTimeView;
	}

	public void setCompleteTimeView(String completeTimeView) {
		this.completeTimeView = completeTimeView;
	}

	public String getOrderTypeView() {
		return orderTypeView;
	}

	public void setOrderTypeView(String orderTypeView) {
		this.orderTypeView = orderTypeView;
	}

	public String getTypeView() {
		return typeView;
	}

	public void setTypeView(String typeView) {
		this.typeView = typeView;
	}

	public SupplierModel getSm() {
		return sm;
	}

	public void setSm(SupplierModel sm) {
		this.sm = sm;
	}

	public EmpModel getCreater() {
		return creater;
	}

	public void setCreater(EmpModel creater) {
		this.creater = creater;
	}

	public EmpModel getChecker() {
		return checker;
	}

	public void setChecker(EmpModel checker) {
		this.checker = checker;
	}

	public EmpModel getCompleter() {
		return completer;
	}

	public void setCompleter(EmpModel completer) {
		this.completer = completer;
	}

	public Set<OrderDetailModel> getOdms() {
		return odms;
	}

	public void setOdms(Set<OrderDetailModel> odms) {
		this.odms = odms;
	}



}
