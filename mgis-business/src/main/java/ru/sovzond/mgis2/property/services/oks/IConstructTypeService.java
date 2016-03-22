package ru.sovzond.mgis2.property.services.oks;

import ru.sovzond.mgis2.dataaccess.base.PageableContainer;
import ru.sovzond.mgis2.property.model.oks.ConstructionType;
import ru.sovzond.mgis2.property.services.common.IPropertyService;

/**
 * Created by Sergey Lvov on 22.03.16.
 *
 */
public interface IConstructTypeService extends IPropertyService<ConstructionType> {
	PageableContainer<ConstructionType> list(String orderBy, int first, int max);
	ConstructionType findByCode(String code);
}
