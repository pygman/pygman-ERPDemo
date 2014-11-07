package @package_addr@.@small@.web;

import java.util.List;

import @package_addr@.@small@.business.ebi.@big@Ebi;
import @package_addr@.@small@.vo.@big@Model;
import @package_addr@.@small@.vo.@big@QueryModel;
import pygman.invoice.util.base.BaseAction;

public class @big@Action extends BaseAction{
	public @big@Model @little@m = new @big@Model();
	public @big@QueryModel @little@qm = new @big@QueryModel();

	private @big@Ebi @small@Ebi;
	public void set@big@Ebi(@big@Ebi @small@Ebi) {
		this.@small@Ebi = @small@Ebi;
	}

	public String list(){
		setDataTotal(@small@Ebi.getCount(@little@qm));
		List<@big@Model> @small@List = @small@Ebi.getAll(@little@qm,pageNum,pageCount);
		put("@small@List", @small@List);
		return LIST;
	}

	public String input(){
		if(@little@m.getUuid() != null){
			@little@m = @small@Ebi.get(@little@m.getUuid());
		}
		return INPUT;
	}

	public String save(){
		if(@little@m.getUuid() == null){
			@small@Ebi.save(@little@m);
		}else{
			@small@Ebi.update(@little@m);
		}
		return TO_LIST;
	}

	public String delete(){
		@small@Ebi.delete(@little@m);
		return TO_LIST;
	}
}
