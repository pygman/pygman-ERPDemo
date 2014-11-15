package pygman.invoice.invoice.supplier.vo;

import pygman.invoice.invoice.goodstype.vo.GoodsTypeModel;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class SupplierModel {
	public static final Integer SUPPLIER_NEEDS_IS_YES = 1;
	public static final Integer SUPPLIER_NEEDS_IS_NO = 0;
	
	public static final String SUPPLIER_NEEDS_IS_YES_VIEW = "送货";
	public static final String SUPPLIER_NEEDS_IS_NO_VIEW = "自提";
	
	public static final Map<Integer, String> needsMap = new HashMap<Integer, String>();
	static{
		needsMap.put(SUPPLIER_NEEDS_IS_YES,SUPPLIER_NEEDS_IS_YES_VIEW );
		needsMap.put(SUPPLIER_NEEDS_IS_NO, SUPPLIER_NEEDS_IS_NO_VIEW);
	}
	
	private Long uuid;
	private String name;
	private String address;
	private String contact;
	private String tele;
	//需要送货
	private Integer needs;
	
	private String needsView;
	
	private Set<GoodsTypeModel> gtms;
	public Set<GoodsTypeModel> getGtms() {
		return gtms;
	}
	
	public void setGtms(Set<GoodsTypeModel> gtms) {
		this.gtms = gtms;
	}
	public String getNeedsView() {
		return needsView;
	}
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getTele() {
		return tele;
	}
	public void setTele(String tele) {
		this.tele = tele;
	}
	public Integer getNeeds() {
		return needs;
	}
	public void setNeeds(Integer needs) {
		this.needs = needs;
		this.needsView = needsMap.get(needs);
	}
	
}
