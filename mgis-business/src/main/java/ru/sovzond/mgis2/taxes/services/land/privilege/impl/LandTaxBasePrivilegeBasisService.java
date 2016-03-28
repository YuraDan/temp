package ru.sovzond.mgis2.taxes.services.land.privilege.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.business.CRUDBeanBase;
import ru.sovzond.mgis2.dataaccess.base.IIdentifiableDao;
import ru.sovzond.mgis2.dataaccess.base.IPageableDAOBase;
import ru.sovzond.mgis2.taxes.dao.land.privilege.ILandTaxBasePrivilegeBasisDao;
import ru.sovzond.mgis2.taxes.model.land.privilege.LandTaxBasePrivilegeBasis;
import ru.sovzond.mgis2.taxes.services.land.privilege.ILandTaxBasePrivilegeBasisService;

/**
 * Created by Sergey Lvov on 28.03.16.
 */
@Service
public class LandTaxBasePrivilegeBasisService
		extends CRUDBeanBase<LandTaxBasePrivilegeBasis>
		implements ILandTaxBasePrivilegeBasisService {
	@Autowired
	private ILandTaxBasePrivilegeBasisDao dao;

	@Override
	protected IPageableDAOBase<LandTaxBasePrivilegeBasis> getPageableDao() {
		return null;
	}

	@Override
	protected IIdentifiableDao<LandTaxBasePrivilegeBasis> getIIdentifiableDao() {
		return null;
	}

}
