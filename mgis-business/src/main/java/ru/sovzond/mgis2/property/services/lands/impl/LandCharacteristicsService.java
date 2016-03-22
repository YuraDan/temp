package ru.sovzond.mgis2.property.services.lands.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.business.CRUDBeanBase;
import ru.sovzond.mgis2.dataaccess.base.IIdentifiableDao;
import ru.sovzond.mgis2.dataaccess.base.IPageableDAOBase;
import ru.sovzond.mgis2.property.dao.lands.ILandCharacteristicsDao;
import ru.sovzond.mgis2.property.model.lands.characteristics.LandCharacteristics;
import ru.sovzond.mgis2.property.services.lands.ILandCharacteristicsService;

/**
 * Created by Alexander Arakelyan on 31.07.15.
 *
 */
@Service
public class LandCharacteristicsService extends CRUDBeanBase<LandCharacteristics> implements ILandCharacteristicsService {

	@Autowired
	private ILandCharacteristicsDao dao;

	@Override
	protected IPageableDAOBase<LandCharacteristics> getPageableDao() {
		return dao;
	}

	@Override
	protected IIdentifiableDao<LandCharacteristics> getIIdentifiableDao() {
		return dao;
	}
}
