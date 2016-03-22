package ru.sovzond.mgis2.property.services.lands.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.business.CRUDBeanBase;
import ru.sovzond.mgis2.dataaccess.base.IIdentifiableDao;
import ru.sovzond.mgis2.dataaccess.base.IPageableDAOBase;
import ru.sovzond.mgis2.property.dao.lands.ILandControlInspectionSubjectDao;
import ru.sovzond.mgis2.property.model.lands.control.LandControlInspectionSubject;
import ru.sovzond.mgis2.property.services.lands.ILandControlInspectionSubjectService;

/**
 * Created by Alexander Arakelyan on 31.07.15.
 *
 */
@Service
public class LandControlInspectionSubjectService
		extends CRUDBeanBase<LandControlInspectionSubject>
		implements ILandControlInspectionSubjectService {

	@Autowired
	private ILandControlInspectionSubjectDao dao;

	@Override
	protected IPageableDAOBase<LandControlInspectionSubject> getPageableDao() {
		return dao;
	}

	@Override
	protected IIdentifiableDao<LandControlInspectionSubject> getIIdentifiableDao() {
		return dao;
	}
}
