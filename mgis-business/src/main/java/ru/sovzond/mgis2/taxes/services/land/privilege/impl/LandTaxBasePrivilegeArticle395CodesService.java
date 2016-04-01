package ru.sovzond.mgis2.taxes.services.land.privilege.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.business.CRUDBeanBase;
import ru.sovzond.mgis2.dataaccess.base.IIdentifiableDao;
import ru.sovzond.mgis2.dataaccess.base.IPageableDAOBase;
import ru.sovzond.mgis2.taxes.dao.land.privilege.ILandTaxBasePrivilegeArticle395CodesDao;
import ru.sovzond.mgis2.taxes.model.land.privilege.LandTaxBasePrivilegeArticle395Codes;
import ru.sovzond.mgis2.taxes.services.land.privilege.ILandTaxBasePrivilegeArticle395CodesService;

/**
 * Created by Sergey Lvov on 28.03.16.
 */
@Service
public class LandTaxBasePrivilegeArticle395CodesService
		extends CRUDBeanBase<LandTaxBasePrivilegeArticle395Codes>
		implements ILandTaxBasePrivilegeArticle395CodesService {
	@Autowired
	private ILandTaxBasePrivilegeArticle395CodesDao dao;

	@Override
	protected IPageableDAOBase<LandTaxBasePrivilegeArticle395Codes> getPageableDao() {
		return null;
	}

	@Override
	protected IIdentifiableDao<LandTaxBasePrivilegeArticle395Codes> getIIdentifiableDao() {
		return null;
	}

}
