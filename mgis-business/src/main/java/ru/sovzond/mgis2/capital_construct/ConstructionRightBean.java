package ru.sovzond.mgis2.capital_construct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.business.CRUDBeanBase;
import ru.sovzond.mgis2.property.dao.oks.ConstructionRightDao;
import ru.sovzond.mgis2.property.model.oks.rights.ConstructionRight;
import ru.sovzond.mgis2.dataaccess.base.IIdentifiableDao;
import ru.sovzond.mgis2.dataaccess.base.IPageableDAOBase;

/**
 * Created by Alexander Arakelyan on 12/11/15.
 *
 */
@Service
public class ConstructionRightBean extends CRUDBeanBase<ConstructionRight> {

	@Autowired
	private ConstructionRightDao dao;

	@Override
	protected IPageableDAOBase<ConstructionRight> getPageableDao() {
		return dao;
	}

	@Override
	protected IIdentifiableDao<ConstructionRight> getIIdentifiableDao() {
		return dao;
	}
}
