package ru.sovzond.mgis2.property.services.lands;

import ru.sovzond.mgis2.property.services.common.IPropertyService;
import ru.sovzond.mgis2.registers.national_classifiers.TerritorialZoneType;

import java.util.List;

/**
 * Created by Sergey Lvov on 22.03.16.
 *
 */
public interface ITerritorialZoneTypeService extends IPropertyService<TerritorialZoneType> {
	List<TerritorialZoneType> findByNameSubstring(String nameSubstring);
	TerritorialZoneType findByCode(String code);
}
