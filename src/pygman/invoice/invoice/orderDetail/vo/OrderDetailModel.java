package pygman.invoice.invoice.orderdetail.vo;


import pygman.invoice.invoice.goods.vo.GoodsModel;
import pygman.invoice.invoice.order.vo.OrderModel;
import pygman.invoice.util.format.FormatUtil;

public class OrderDetailModel {
	private Long uuid;
	//订单数量
	private Integer num;
	//本次操作价格
	private Double price;
	//剩余未入库货物总量
	private Integer surplus;
	
	//视图值
	private String priceView;
	private String totalPriceView;
	
	//对应的商品
	private GoodsModel gm;
	//所属订单
	private OrderModel om;
	
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
	
	public String getPriceView() {
		return priceView;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
		this.priceView = FormatUtil.formatMoney(price);
		this.totalPriceView = FormatUtil.formatMoney(num*price);
	}
	public GoodsModel getGm() {
		return gm;
	}
	public void setGm(GoodsModel gm) {
		this.gm = gm;
	}
	public OrderModel getOm() {
		return om;
	}
	public void setOm(OrderModel om) {
		this.om = om;
	}
	public String getTotalPriceView() {
		return totalPriceView;
	}
	public Integer getSurplus() {
		return surplus;
	}
	public void setSurplus(Integer surplus) {
		this.surplus = surplus;
	}
	
}
