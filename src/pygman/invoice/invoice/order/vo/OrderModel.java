package pygman.invoice.invoice.order.vo;

import pygman.invoice.auth.emp.vo.EmpModel;
import pygman.invoice.invoice.orderdetail.vo.OrderDetailModel;
import pygman.invoice.invoice.supplier.vo.SupplierModel;
import pygman.invoice.util.format.FormatUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class OrderModel {
	public static final Integer ORDER_ORDERTYPE_OF_BUY = 1;
	public static final Integer ORDER_ORDERTYPE_OF_SALE = 2;
	public static final Integer ORDER_ORDERTYPE_OF_RETURN_BUY = 3;
	public static final Integer ORDER_ORDERTYPE_OF_RETURN_SALE = 4;
	
	public static final String ORDER_ORDERTYPE_OF_BUY_VIEW = "采购";
	public static final String ORDER_ORDERTYPE_OF_SALE_VIEW = "销售";
	public static final String ORDER_ORDERTYPE_OF_RETURN_BUY_VIEW = "采购退货";
	public static final String ORDER_ORDERTYPE_OF_RETURN_SALE_VIEW = "销售退货";
	//刚申请还未审核
	//审核驳回
	//审核通过//审核完毕未指派任务人
	//审核完毕指派任务人完毕//订单未采购完成
	//订单已经采购完成//订单未入库
	//订单入库完毕
	
	//状态值的设计规范
	
	public static final Integer ORDER_TYPE_OF_BUY_NO_CHECK = 111;
	public static final Integer ORDER_TYPE_OF_BUY_CHECK_NO_PASS = 120;
	public static final Integer ORDER_TYPE_OF_BUY_CHECK_PASS = 121;
	public static final Integer ORDER_TYPE_OF_BUY_BUYING = 131;
	public static final Integer ORDER_TYPE_OF_BUY_IN_STORE = 141;
	public static final Integer ORDER_TYPE_OF_BUY_COMPLETE = 199;
	
	public static final String ORDER_TYPE_OF_BUY_NO_CHECK_VIEW = "未审核";
	public static final String ORDER_TYPE_OF_BUY_CHECK_NO_PASS_VIEW = "驳回";
	public static final String ORDER_TYPE_OF_BUY_CHECK_PASS_VIEW = "任务指派中";
	public static final String ORDER_TYPE_OF_BUY_BUYING_VIEW = "采购中";
	public static final String ORDER_TYPE_OF_BUY_IN_STORE_VIEW = "入库中";
	public static final String ORDER_TYPE_OF_BUY_COMPLETE_VIEW = "完成";
	
	public static final Integer ORDER_TYPE_OF_SALE_NO_CHECK = 211;
	public static final Integer ORDER_TYPE_OF_SALE_CHECK_NO_PASS = 220;
	
	public static final String ORDER_TYPE_OF_SALE_NO_CHECK_VIEW = "测试状态1";
	public static final String ORDER_TYPE_OF_SALE_CHECK_NO_PASS_VIEW = "测试状态2";
	
	public static final Map<Integer , String> orderTypeMap = new HashMap<Integer, String>();
	public static final Map<Integer , String> buyMap = new TreeMap<Integer, String>();
	public static final Map<Integer , String> saleMap = new TreeMap<Integer, String>();
	private static final Map<Integer , String> typeMap = new TreeMap<Integer, String>();
	
	static{
		orderTypeMap.put(ORDER_ORDERTYPE_OF_BUY,ORDER_ORDERTYPE_OF_BUY_VIEW);
		orderTypeMap.put(ORDER_ORDERTYPE_OF_SALE,ORDER_ORDERTYPE_OF_SALE_VIEW);
		orderTypeMap.put(ORDER_ORDERTYPE_OF_RETURN_BUY,ORDER_ORDERTYPE_OF_RETURN_BUY_VIEW);
		orderTypeMap.put(ORDER_ORDERTYPE_OF_RETURN_SALE,ORDER_ORDERTYPE_OF_RETURN_SALE_VIEW);

		buyMap.put(ORDER_TYPE_OF_BUY_NO_CHECK, ORDER_TYPE_OF_BUY_NO_CHECK_VIEW);
		buyMap.put(ORDER_TYPE_OF_BUY_CHECK_NO_PASS, ORDER_TYPE_OF_BUY_CHECK_NO_PASS_VIEW);
		buyMap.put(ORDER_TYPE_OF_BUY_CHECK_PASS, ORDER_TYPE_OF_BUY_CHECK_PASS_VIEW);
		buyMap.put(ORDER_TYPE_OF_BUY_BUYING, ORDER_TYPE_OF_BUY_BUYING_VIEW);
		buyMap.put(ORDER_TYPE_OF_BUY_IN_STORE, ORDER_TYPE_OF_BUY_IN_STORE_VIEW);
		buyMap.put(ORDER_TYPE_OF_BUY_COMPLETE, ORDER_TYPE_OF_BUY_COMPLETE_VIEW);
		
		saleMap.put(ORDER_TYPE_OF_SALE_NO_CHECK,ORDER_TYPE_OF_SALE_NO_CHECK_VIEW );
		saleMap.put(ORDER_TYPE_OF_SALE_CHECK_NO_PASS, ORDER_TYPE_OF_SALE_CHECK_NO_PASS_VIEW);
		
		typeMap.putAll(buyMap);
		typeMap.putAll(saleMap);
	}
	
	private Long uuid;
	//订单号
	private String orderNum;
	
	//总货物数量
	private Integer totalNum;
	
	//订单总价格
	private Double totalPrice;
	//申请时间
	private Long createTime;
	//审核时间
	private Long checkTime;
	//完成时间
	private Long completeTime;
	//订单类别-采购，销售，采购退货，销售退货
	private Integer orderType; 
	//订单状态
	private Integer type;

	//视图值
	private String totalPriceView;
	private String createTimeView;
	private String checkTimeView;
	private String completeTimeView;
	private String orderTypeView; 
	private String typeView;
	
	//关系
	//订单所属供应商
	private SupplierModel sm;
	//申请人/创建者
	private EmpModel creater;
	//审核人
	private EmpModel checker;
	//跟单人
	private EmpModel completer;
	//订单明细(一对多)
	private Set<OrderDetailModel> odms;
	
	public String getTotalPriceView() {
		return totalPriceView;
	}
	public String getCreateTimeView() {
		return createTimeView;
	}
	public String getCheckTimeView() {
		return checkTimeView;
	}
	public String getCompleteTimeView() {
		return completeTimeView;
	}
	public String getOrderTypeView() {
		return orderTypeView;
	}
	public String getTypeView() {
		return typeView;
	}
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
		this.totalPriceView = FormatUtil.formatMoney(totalPrice);
	}
	public Long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
		this.createTimeView = FormatUtil.formatDate(createTime);
	}
	public Long getCheckTime() {
		return checkTime;
	}
	public void setCheckTime(Long checkTime) {
		this.checkTime = checkTime;
		this.checkTimeView = FormatUtil.formatDate(checkTime);
	}
	public Long getCompleteTime() {
		return completeTime;
	}
	public void setCompleteTime(Long completeTime) {
		this.completeTime = completeTime;
		this.completeTimeView = FormatUtil.formatDate(completeTime);
	}
	public Integer getOrderType() {
		return orderType;
	}
	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
		this.orderTypeView = orderTypeMap.get(orderType);
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
		this.typeView = typeMap.get(type);
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
