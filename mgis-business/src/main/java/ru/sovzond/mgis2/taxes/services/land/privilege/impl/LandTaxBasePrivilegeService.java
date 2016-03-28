package ru.sovzond.mgis2.taxes.services.land.privilege.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.business.CRUDBeanBase;
import ru.sovzond.mgis2.dataaccess.base.IIdentifiableDao;
import ru.sovzond.mgis2.dataaccess.base.IPageableDAOBase;
import ru.sovzond.mgis2.taxes.dao.land.privilege.ILandTaxBasePrivilegeDao;
import ru.sovzond.mgis2.taxes.model.land.privilege.LandTaxBasePrivilege;
import ru.sovzond.mgis2.taxes.services.land.privilege.ILandTaxBasePrivilegeService;

/**
 * Created by Sergey Lvov on 28.03.16.
 */
@Service
public class LandTaxBasePrivilegeService extends CRUDBeanBase<LandTaxBasePrivilege> implements ILandTaxBasePrivilegeService {
	@Autowired
	private ILandTaxBasePrivilegeDao dao;

	@Override
	protected IPageableDAOBase<LandTaxBasePrivilege> getPageableDao() {
		return null;
	}

	@Override
	protected IIdentifiableDao<LandTaxBasePrivilege> getIIdentifiableDao() {
		return null;
	}

}
