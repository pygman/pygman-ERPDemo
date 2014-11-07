package pygman.invoice.invoice.supplier.vo;

import java.util.HashMap;
import java.util.Map;
import pygman.invoice.util.format.FormatUtil;

public class SupplierModel {

	
	private Long uuid;
	private String name;
	private String address;
	private String contact;
	private String tele;
	private Integer needs;
	private String needsView;
	private Set<GoodsTypeModel> gtms;

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
	}

	public String getNeedsView() {
		return needsView;
	}

	public void setNeedsView(String needsView) {
		this.needsView = needsView;
	}

	public Set<GoodsTypeModel> getGtms() {
		return gtms;
	}

	public void setGtms(Set<GoodsTypeModel> gtms) {
		this.gtms = gtms;
	}



}
