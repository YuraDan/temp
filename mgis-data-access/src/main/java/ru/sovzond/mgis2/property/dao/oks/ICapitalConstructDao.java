package ru.sovzond.mgis2.property.dao.oks;

import ru.sovzond.mgis2.dataaccess.base.impl.PagerBuilderCriteria;
import ru.sovzond.mgis2.property.dao.IPropertyBaseDao;
import ru.sovzond.mgis2.property.model.oks.CapitalConstruction;

/**
 * Created by Sergey Lvov on 21.03.16.
 *
 */
public interface ICapitalConstructDao extends IPropertyBaseDao<CapitalConstruction> {
	PagerBuilderCriteria<CapitalConstruction> createFilter(String cadastralNumber, String name, String orderBy, int first, int max);
	CapitalConstruction findByCadastralNumber(String cadastralNumber);
}
