package ru.sovzond.mgis2.property.services.nesting;

import ru.sovzond.mgis2.property.model.nesting.IncludedObjects;
import ru.sovzond.mgis2.property.services.common.IPropertyService;

import java.util.List;

/**
 * Created by Sergey Lvov on 22.03.16.
 *
 */
public interface IIncludedObjectsService extends IPropertyService<IncludedObjects> {
	List<IncludedObjects> getIncludedObjectsByCapitalConstruct(Long constructId);
	List<IncludedObjects> getIncludedObjectsByLand(Long landId);
}
