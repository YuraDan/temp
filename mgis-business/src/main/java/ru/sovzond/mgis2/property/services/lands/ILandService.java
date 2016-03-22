package ru.sovzond.mgis2.property.services.lands;

import ru.sovzond.mgis2.dataaccess.base.PageableContainer;
import ru.sovzond.mgis2.property.model.lands.Land;
import ru.sovzond.mgis2.property.model.nesting.IncludedObjects;
import ru.sovzond.mgis2.property.services.common.IPropertyService;

import java.util.List;

/**
 * Created by Sergey Lvov on 22.03.16.
 *
 */
public interface ILandService extends IPropertyService<Land> {
	PageableContainer<Land> list(String cadastralNumber, Long[] ids, String orderBy, int first, int max);
	boolean saveGeospatialAttribute(Long id, String wktString);
	List<Land> find(String cadastralNumber);
	List<Land> getByIncludedObjects(List<IncludedObjects> includedObjects);
}
