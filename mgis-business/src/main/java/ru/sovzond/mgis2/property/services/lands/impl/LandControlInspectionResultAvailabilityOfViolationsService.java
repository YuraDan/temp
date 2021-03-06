package ru.sovzond.mgis2.property.services.lands.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.business.CRUDBeanBase;
import ru.sovzond.mgis2.dataaccess.base.IIdentifiableDao;
import ru.sovzond.mgis2.dataaccess.base.IPageableDAOBase;
import ru.sovzond.mgis2.property.dao.lands.ILandControlInspectionResultAvailabilityOfViolationsDao;
import ru.sovzond.mgis2.property.model.lands.control.LandControlAvailabilityOfViolations;
import ru.sovzond.mgis2.property.services.lands.ILandControlInspectionResultAvailabilityOfViolationsService;

/**
 * Created by Alexander Arakelyan on 31.07.15.
 *
 */
@Service
public class LandControlInspectionResultAvailabilityOfViolationsService
		extends CRUDBeanBase<LandControlAvailabilityOfViolations>
		implements ILandControlInspectionResultAvailabilityOfViolationsService {

	@Autowired
	private ILandControlInspectionResultAvailabilityOfViolationsDao dao;

	@Override
	protected IPageableDAOBase<LandControlAvailabilityOfViolations> getPageableDao() {
		return dao;
	}

	@Override
	protected IIdentifiableDao<LandControlAvailabilityOfViolations> getIIdentifiableDao() {
		return dao;
	}
}
