package ru.sovzond.mgis2.property.services.lands.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.business.CRUDBeanBase;
import ru.sovzond.mgis2.dataaccess.base.IIdentifiableDao;
import ru.sovzond.mgis2.dataaccess.base.IPageableDAOBase;
import ru.sovzond.mgis2.property.dao.lands.ILandControlInspectionTypeDao;
import ru.sovzond.mgis2.property.model.lands.control.LandControlInspectionType;
import ru.sovzond.mgis2.property.services.lands.ILandControlInspectionTypeService;

/**
 * Created by Alexander Arakelyan on 31.07.15.
 *
 */
@Service
public class LandControlInspectionTypeService
		extends CRUDBeanBase<LandControlInspectionType>
		implements ILandControlInspectionTypeService {

	@Autowired
	private ILandControlInspectionTypeDao dao;

	@Override
	protected IPageableDAOBase<LandControlInspectionType> getPageableDao() {
		return dao;
	}

	@Override
	protected IIdentifiableDao<LandControlInspectionType> getIIdentifiableDao() {
		return dao;
	}
}
