package ru.sovzond.mgis2.taxes.services.land.privilege.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.business.CRUDBeanBase;
import ru.sovzond.mgis2.dataaccess.base.IIdentifiableDao;
import ru.sovzond.mgis2.dataaccess.base.IPageableDAOBase;
import ru.sovzond.mgis2.taxes.dao.land.privilege.ILandTaxSumPrivilegeDao;
import ru.sovzond.mgis2.taxes.model.land.privilege.LandTaxSumPrivilege;
import ru.sovzond.mgis2.taxes.services.land.privilege.ILandTaxSumPrivilegeService;

/**
 * Created by Sergey Lvov on 28.03.16.
 */
@Service
public class LandTaxSumPrivilegeService extends CRUDBeanBase<LandTaxSumPrivilege> implements ILandTaxSumPrivilegeService {
	@Autowired
	private ILandTaxSumPrivilegeDao dao;

	@Override
	protected IPageableDAOBase<LandTaxSumPrivilege> getPageableDao() {
		return null;
	}

	@Override
	protected IIdentifiableDao<LandTaxSumPrivilege> getIIdentifiableDao() {
		return null;
	}

}
