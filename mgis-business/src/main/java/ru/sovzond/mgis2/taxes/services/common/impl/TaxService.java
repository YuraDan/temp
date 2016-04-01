package ru.sovzond.mgis2.taxes.services.common.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.business.CRUDBeanBase;
import ru.sovzond.mgis2.dataaccess.base.IIdentifiableDao;
import ru.sovzond.mgis2.dataaccess.base.IPageableDAOBase;
import ru.sovzond.mgis2.taxes.dao.common.ITaxDao;
import ru.sovzond.mgis2.taxes.model.common.Tax;
import ru.sovzond.mgis2.taxes.services.common.ITaxService;

/**
 * Created by Sergey Lvov on 28.03.16.
 */
@Service
public class TaxService extends CRUDBeanBase<Tax> implements ITaxService {

	@Autowired
	private ITaxDao dao;

	@Override
	protected IPageableDAOBase<Tax> getPageableDao() {
		return null;
	}

	@Override
	protected IIdentifiableDao<Tax> getIIdentifiableDao() {
		return null;
	}

}
