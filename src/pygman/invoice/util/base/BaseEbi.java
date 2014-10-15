package pygman.invoice.util.base;

import java.io.Serializable;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface BaseEbi<T> {
	public void save(T t);
	public void delete(T t);
	public void update(T t);
	public T get(Serializable uuid);
	public List<T> getAll();
	public List<T> getAll(BaseQueryModel qm, Integer pageNum,Integer pageCount);
	public Integer getCount(BaseQueryModel qm);
}
