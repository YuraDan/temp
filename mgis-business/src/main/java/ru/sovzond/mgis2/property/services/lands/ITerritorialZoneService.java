package ru.sovzond.mgis2.property.services.lands;

import ru.sovzond.mgis2.dataaccess.base.PageableContainer;
import ru.sovzond.mgis2.property.model.lands.TerritorialZone;
import ru.sovzond.mgis2.property.services.common.IPropertyService;
import ru.sovzond.mgis2.registers.national_classifiers.TerritorialZoneType;

import java.util.List;

/**
 * Created by Sergey Lvov on 22.03.16.
 *
 */
public interface ITerritorialZoneService extends IPropertyService<TerritorialZone> {
	PageableContainer<TerritorialZone> list(String orderBy, int first, int max, String name);
	List<TerritorialZone> findByCadastralNumberAndZoneType(String cadastralNumber, TerritorialZoneType zoneType);
}
