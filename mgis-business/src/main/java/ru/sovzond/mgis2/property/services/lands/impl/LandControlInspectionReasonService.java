package ru.sovzond.mgis2.property.services.lands.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.business.CRUDBeanBase;
import ru.sovzond.mgis2.dataaccess.base.IIdentifiableDao;
import ru.sovzond.mgis2.dataaccess.base.IPageableDAOBase;
import ru.sovzond.mgis2.property.dao.lands.ILandControlInspectionReasonDao;
import ru.sovzond.mgis2.property.model.lands.control.LandControlInspectionReason;
import ru.sovzond.mgis2.property.services.lands.ILandControlInspectionReasonService;

/**
 * Created by Alexander Arakelyan on 31.07.15.
 *
 */
@Service
public class LandControlInspectionReasonService
		extends CRUDBeanBase<LandControlInspectionReason>
		implements ILandControlInspectionReasonService {

	@Autowired
	private ILandControlInspectionReasonDao dao;

	@Override
	protected IPageableDAOBase<LandControlInspectionReason> getPageableDao() {
		return dao;
	}

	@Override
	protected IIdentifiableDao<LandControlInspectionReason> getIIdentifiableDao() {
		return dao;
	}
}
