package ru.sovzond.mgis2.property.services.oks;

import ru.sovzond.mgis2.dataaccess.base.PageableContainer;
import ru.sovzond.mgis2.property.model.nesting.IncludedObjects;
import ru.sovzond.mgis2.property.model.oks.CapitalConstruction;
import ru.sovzond.mgis2.property.services.common.IPropertyService;

import java.util.List;

/**
 * Created by Sergey Lvov on 22.03.16.
 *
 */
public interface ICapitalConstructService extends IPropertyService<CapitalConstruction> {
	PageableContainer<CapitalConstruction> list(String cadastralNumber, String name, String orderBy, int first, int max);
	CapitalConstruction findByCadastralNumber(String cadastralNumber);
	boolean saveGeospatialAttribute(Long id, String wktString);
	List<CapitalConstruction> getByIncludedObjects(List<IncludedObjects> includedObjects);
}
