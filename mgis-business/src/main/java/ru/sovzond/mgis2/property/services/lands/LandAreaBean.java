package ru.sovzond.mgis2.property.services.lands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.business.CRUDBeanBase;
import ru.sovzond.mgis2.dataaccess.base.IIdentifiableDao;
import ru.sovzond.mgis2.dataaccess.base.IPageableDAOBase;
import ru.sovzond.mgis2.property.dao.lands.LandAreaDao;
import ru.sovzond.mgis2.property.model.lands.LandArea;

/**
 * Created by Alexander Arakelyan on 03.08.15.
 *
 *
 */
@Service
public class LandAreaBean extends CRUDBeanBase<LandArea> {

	@Autowired
	private LandAreaDao dao;

	@Override
	protected IPageableDAOBase<LandArea> getPageableDao() {
		return dao;
	}

	@Override
	protected IIdentifiableDao<LandArea> getIIdentifiableDao() {
		return dao;
	}
}
