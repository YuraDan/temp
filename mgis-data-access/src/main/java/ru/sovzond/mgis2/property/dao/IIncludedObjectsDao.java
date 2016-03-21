package ru.sovzond.mgis2.property.dao;

import ru.sovzond.mgis2.property.model.IncludedObjects;

import java.util.List;

/**
 * Created by Sergey Lvov on 21.03.16.
 *
 */
public interface IIncludedObjectsDao extends IPropertyBaseDao<IncludedObjects> {
	List<IncludedObjects> findByNestedObject(Long landId, Long capitalConstructId);
}
