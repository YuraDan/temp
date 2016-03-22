package ru.sovzond.mgis2.property.dao.lands;

import ru.sovzond.mgis2.dataaccess.base.impl.PagerBuilderCriteria;
import ru.sovzond.mgis2.property.dao.common.IPropertyBaseDao;
import ru.sovzond.mgis2.property.model.lands.TerritorialZone;
import ru.sovzond.mgis2.registers.national_classifiers.TerritorialZoneType;

import java.util.List;

/**
 * Created by Sergey Lvov on 21.03.16.
 *
 */
public interface ITerritorialZoneDao extends IPropertyBaseDao<TerritorialZone> {
	PagerBuilderCriteria<TerritorialZone> createFilter(String name, String orderBy, int first, int max);
	List<TerritorialZone> findByCadastralNumberAndZoneType(String cadastralNumber, TerritorialZoneType zoneType);
}
