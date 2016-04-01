package ru.sovzond.mgis2.property.services.lands.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.business.CRUDBeanBase;
import ru.sovzond.mgis2.dataaccess.base.IIdentifiableDao;
import ru.sovzond.mgis2.dataaccess.base.IPageableDAOBase;
import ru.sovzond.mgis2.property.dao.lands.ILandControlInspectionKindDao;
import ru.sovzond.mgis2.property.model.lands.control.LandControlInspectionKind;
import ru.sovzond.mgis2.property.services.lands.ILandControlInspectionKindService;

/**
 * Created by Alexander Arakelyan on 31.07.15.
 *
 */
@Service
public class LandControlInspectionKindService
		extends CRUDBeanBase<LandControlInspectionKind>
		implements ILandControlInspectionKindService {

	@Autowired
	private ILandControlInspectionKindDao dao;

	@Override
	protected IPageableDAOBase<LandControlInspectionKind> getPageableDao() {
		return dao;
	}

	@Override
	protected IIdentifiableDao<LandControlInspectionKind> getIIdentifiableDao() {
		return dao;
	}
}
