package ru.sovzond.mgis2.property.dao.oks;

import ru.sovzond.mgis2.property.dao.common.IPropertyBaseDao;
import ru.sovzond.mgis2.property.model.oks.ConstructionType;

/**
 * Created by Sergey Lvov on 21.03.16.
 *
 */
public interface IConstructTypeDao extends IPropertyBaseDao<ConstructionType> {
	ConstructionType findByCode(String code);
}
