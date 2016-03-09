package ru.sovzond.mgis2.capital_construct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.business.CRUDBeanBase;
import ru.sovzond.mgis2.capital_constructs.rights.ConstructionRights;
import ru.sovzond.mgis2.dataaccess.base.IIdentifiableDao;
import ru.sovzond.mgis2.dataaccess.base.IPageableDAOBase;

/**
 * Created by Alexander Arakelyan on 12/11/15.
 *
 */
@Service
public class ConstructionRightsBean extends CRUDBeanBase<ConstructionRights> {

	@Autowired
	private ConstructionRightsDao dao;

	@Override
	protected IPageableDAOBase<ConstructionRights> getPageableDao() {
		return dao;
	}

	@Override
	protected IIdentifiableDao<ConstructionRights> getIIdentifiableDao() {
		return dao;
	}
}
