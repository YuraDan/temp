package ru.sovzond.mgis2.property.services.oks.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.business.CRUDBeanBase;
import ru.sovzond.mgis2.dataaccess.base.IIdentifiableDao;
import ru.sovzond.mgis2.dataaccess.base.IPageableDAOBase;
import ru.sovzond.mgis2.property.dao.oks.IConstructCharacteristicsDao;
import ru.sovzond.mgis2.property.model.oks.characteristics.ConstructionCharacteristics;
import ru.sovzond.mgis2.property.services.oks.IConstructCharacteristicsService;

/**
 * Created by Alexander Arakelyan on 14/11/15.
 *
 */
@Service
public class ConstructCharacteristicsService
		extends CRUDBeanBase<ConstructionCharacteristics>
		implements IConstructCharacteristicsService {

	@Autowired
	private IConstructCharacteristicsDao dao;

	@Override
	protected IPageableDAOBase<ConstructionCharacteristics> getPageableDao() {
		return dao;
	}

	@Override
	protected IIdentifiableDao<ConstructionCharacteristics> getIIdentifiableDao() {
		return dao;
	}

}
