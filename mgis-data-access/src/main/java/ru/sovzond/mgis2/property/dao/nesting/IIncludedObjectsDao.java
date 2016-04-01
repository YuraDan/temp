package ru.sovzond.mgis2.property.dao.nesting;

import ru.sovzond.mgis2.property.dao.common.IPropertyBaseDao;
import ru.sovzond.mgis2.property.model.nesting.IncludedObjects;

import java.util.List;

/**
 * Created by Sergey Lvov on 21.03.16.
 *
 */
public interface IIncludedObjectsDao extends IPropertyBaseDao<IncludedObjects> {
	List<IncludedObjects> findByNestedObject(Long landId, Long capitalConstructId);
}
