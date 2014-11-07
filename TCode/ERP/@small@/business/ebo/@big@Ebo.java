package @package_addr@.@small@.business.ebo;

import java.io.Serializable;
import java.util.List;

import @package_addr@.@small@.business.ebi.@big@Ebi;
import @package_addr@.@small@.dao.dao.@big@Dao;
import @package_addr@.@small@.vo.@big@Model;
import pygman.invoice.util.base.BaseQueryModel;

public class @big@Ebo implements @big@Ebi{
	private @big@Dao @small@Dao;
	public void set@big@Dao(@big@Dao @small@Dao) {
		this.@small@Dao = @small@Dao;
	}

	public void save(@big@Model @little@m) {
		@small@Dao.save(@little@m);
	}

	public void update(@big@Model @little@m) {
		@small@Dao.update(@little@m);
	}

	public void delete(@big@Model @little@m) {
		@small@Dao.delete(@little@m);
	}

	public @big@Model get(Serializable uuid) {
		return @small@Dao.get(uuid);
	}

	public List<@big@Model> getAll() {
		return @small@Dao.getAll();
	}

	public Integer getCount(BaseQueryModel qm) {
		return @small@Dao.getCount(qm);
	}

	public List<@big@Model> getAll(BaseQueryModel qm, Integer pageNum,Integer pageCount) {
		return @small@Dao.getAll(qm,pageNum,pageCount);
	}

}
