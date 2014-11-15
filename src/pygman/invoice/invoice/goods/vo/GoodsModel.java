package pygman.invoice.invoice.goods.vo;

import pygman.invoice.invoice.goodstype.vo.GoodsTypeModel;
import pygman.invoice.util.format.FormatUtil;

import java.text.DecimalFormat;

public class GoodsModel {
	private Long uuid;
	private String name;
	//产地
	private String origin;
	//生产商
	private String producer;
	//单位
	private String unit;
	//使用频度
	private Integer useNum;
	//最大预警值
	private Integer maxNum;
	//最小预警值
	private Integer minNum;
	
	//企业开发金额类的数据类型：String,BigDecimal,Double,Float
	private Double inPrice;
	private Double outPrice;
	
	private String inPriceView;
	private String outPriceView;
	
	//关系
	//对商品类别多对一
	private GoodsTypeModel gtm;

	public Long getUuid() {
		return uuid;
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

	public String getInPriceView() {
		return inPriceView;
	}

	public String getOutPriceView() {
		return outPriceView;
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

	public Double getInPrice() {
		return inPrice;
	}

	public void setInPrice(Double inPrice) {
		this.inPrice = inPrice;
		this.inPriceView = FormatUtil.formatMoney(inPrice);
	}

	public Double getOutPrice() {
		return outPrice;
	}

	public void setOutPrice(Double outPrice) {
		this.outPrice = outPrice;
		this.outPriceView = FormatUtil.formatMoney(outPrice);
	}

	public GoodsTypeModel getGtm() {
		return gtm;
	}

	public void setGtm(GoodsTypeModel gtm) {
		this.gtm = gtm;
	}
}
