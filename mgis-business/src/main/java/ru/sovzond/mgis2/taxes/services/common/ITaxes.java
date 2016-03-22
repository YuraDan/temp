package ru.sovzond.mgis2.taxes.services.common;

import ru.sovzond.mgis2.dataaccess.base.PageableContainer;

import java.util.List;

/**
 * Created by donchenko-y on 3/22/16.
 */
public interface ITaxes<T> {

	PageableContainer<T> list(int first, int max);

	PageableContainer<T> list(String orderBy, int first, int max);

	T load(Long id);

	List<T> load(List<Long> ids);

	T save(T entity);

	void remove(T entity);
}
