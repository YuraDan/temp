package ru.sovzond.mgis2.property.services.lands.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.business.CRUDBeanBase;
import ru.sovzond.mgis2.dataaccess.base.IIdentifiableDao;
import ru.sovzond.mgis2.dataaccess.base.IPageableDAOBase;
import ru.sovzond.mgis2.property.dao.lands.ILandTypeOfEngineeringSupportAreaDao;
import ru.sovzond.mgis2.property.model.lands.characteristics.LandCharacteristicsEngineeringSupportArea;
import ru.sovzond.mgis2.property.services.lands.ILandTypeOfEngineeringSupportAreaService;

/**
 * Created by Alexander Arakelyan on 31.07.15.
 *
 */
@Service
public class LandTypeOfEngineeringSupportAreaService
		extends CRUDBeanBase<LandCharacteristicsEngineeringSupportArea>
		implements ILandTypeOfEngineeringSupportAreaService {

	@Autowired
	private ILandTypeOfEngineeringSupportAreaDao dao;

	@Override
	protected IPageableDAOBase<LandCharacteristicsEngineeringSupportArea> getPageableDao() {
		return dao;
	}

	@Override
	protected IIdentifiableDao<LandCharacteristicsEngineeringSupportArea> getIIdentifiableDao() {
		return dao;
	}
}
