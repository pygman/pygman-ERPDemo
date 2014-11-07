package pygman.invoice.invoice.orderDetail.vo;

import java.util.HashMap;
import java.util.Map;
import pygman.invoice.util.format.FormatUtil;

public class OrderDetailModel {

	
	private Long uuid;
	private Integer num;
	private Double price;
	private Integer surplus;
	private String priceView;
	private String totalPriceView;
	private GoodModel gm;
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

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getSurplus() {
		return surplus;
	}

	public void setSurplus(Integer surplus) {
		this.surplus = surplus;
	}

	public String getPriceView() {
		return priceView;
	}

	public void setPriceView(String priceView) {
		this.priceView = priceView;
	}

	public String getTotalPriceView() {
		return totalPriceView;
	}

	public void setTotalPriceView(String totalPriceView) {
		this.totalPriceView = totalPriceView;
	}

	public GoodModel getGm() {
		return gm;
	}

	public void setGm(GoodModel gm) {
		this.gm = gm;
	}

	public OrderModel getOm() {
		return om;
	}

	public void setOm(OrderModel om) {
		this.om = om;
	}



}
