package ru.sovzond.mgis2.property.services.common;

import ru.sovzond.mgis2.dataaccess.base.PageableContainer;

import java.util.List;

/**
 * Created by Sergey Lvov on 22.03.16.
 *
 */
public interface IPropertyService<T> {
	PageableContainer<T> list(int first, int max);
	PageableContainer<T> list(String orderBy, int first, int max);
	T load(Long id);
	List<T> load(List<Long> ids);
	T save(T entity);
	void remove(T entity);
}
