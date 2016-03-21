package ru.sovzond.mgis2.property.dao.lands;

import ru.sovzond.mgis2.property.dao.IPropertyBaseDao;
import ru.sovzond.mgis2.registers.national_classifiers.TerritorialZoneType;

import java.util.List;

/**
 * Created by Sergey Lvov on 21.03.16.
 *
 */
public interface ITerritorialZoneTypeDao extends IPropertyBaseDao<TerritorialZoneType> {
	List<TerritorialZoneType> findByNameSubstring(String nameSubstring);
	TerritorialZoneType findByCode(String code);
}
