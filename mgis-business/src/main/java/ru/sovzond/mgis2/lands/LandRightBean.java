package ru.sovzond.mgis2.lands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.business.CRUDBeanBase;
import ru.sovzond.mgis2.dataaccess.base.IIdentifiableDao;
import ru.sovzond.mgis2.dataaccess.base.IPageableDAOBase;
import ru.sovzond.mgis2.property.dao.lands.LandRightDao;
import ru.sovzond.mgis2.property.model.lands.rights.LandRight;

/**
 * Created by Alexander Arakelyan on 30.07.15.
 *
 */
@Service
public class LandRightBean extends CRUDBeanBase<LandRight> {

	@Autowired
	private LandRightDao dao;

	@Override
	protected IPageableDAOBase<LandRight> getPageableDao() {
		return dao;
	}

	@Override
	protected IIdentifiableDao<LandRight> getIIdentifiableDao() {
		return dao;
	}
}
