package ru.sovzond.mgis2.property.dao.lands;

import ru.sovzond.mgis2.dataaccess.base.impl.PagerBuilderQuery;
import ru.sovzond.mgis2.property.dao.IPropertyBaseDao;
import ru.sovzond.mgis2.property.model.IncludedObjects;
import ru.sovzond.mgis2.property.model.lands.Land;

import java.util.List;

/**
 * Created by Sergey Lvov on 21.03.16.
 *
 */
public interface ILandDao extends IPropertyBaseDao<Land> {
	PagerBuilderQuery<Land> createFilter(String cadastralNumber, Long[] ids, String orderBy, int first, int max);
	List<Land> find(String cadastralNumber);
	List<Land> getByIncludedObjects(List<IncludedObjects> landIncludedObjects);
}
