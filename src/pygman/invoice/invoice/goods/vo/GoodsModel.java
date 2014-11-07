package pygman.invoice.invoice.goods.vo;

import java.util.HashMap;
import java.util.Map;
import pygman.invoice.util.format.FormatUtil;

public class GoodsModel {

	
	private Long uuid;
	private String name;
	private String origin;
	private String producer;
	private String unit;
	private Integer useNum;
	private Integer maxNum;
	private Integer minNum;
	private Double inprice;
	private Double outprice;
	private String inPriceView;
	private String outPriceView;

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

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getProducer() {
		return producer;
	}

	public void setProducer(String producer) {
		this.producer = producer;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Integer getUseNum() {
		return useNum;
	}

	public void setUseNum(Integer useNum) {
		this.useNum = useNum;
	}

	public Integer getMaxNum() {
		return maxNum;
	}

	public void setMaxNum(Integer maxNum) {
		this.maxNum = maxNum;
	}

	public Integer getMinNum() {
		return minNum;
	}

	public void setMinNum(Integer minNum) {
		this.minNum = minNum;
	}

	public Double getInprice() {
		return inprice;
	}

	public void setInprice(Double inprice) {
		this.inprice = inprice;
	}

	public Double getOutprice() {
		return outprice;
	}

	public void setOutprice(Double outprice) {
		this.outprice = outprice;
	}

	public String getInPriceView() {
		return inPriceView;
	}

	public void setInPriceView(String inPriceView) {
		this.inPriceView = inPriceView;
	}

	public String getOutPriceView() {
		return outPriceView;
	}

	public void setOutPriceView(String outPriceView) {
		this.outPriceView = outPriceView;
	}



}
