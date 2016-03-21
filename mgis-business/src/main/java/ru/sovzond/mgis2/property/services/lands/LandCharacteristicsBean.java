package ru.sovzond.mgis2.property.services.lands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.business.CRUDBeanBase;
import ru.sovzond.mgis2.dataaccess.base.IIdentifiableDao;
import ru.sovzond.mgis2.dataaccess.base.IPageableDAOBase;
import ru.sovzond.mgis2.property.dao.lands.LandCharacteristicsDao;
import ru.sovzond.mgis2.property.model.lands.characteristics.LandCharacteristics;

/**
 * Created by Alexander Arakelyan on 31.07.15.
 */
@Service
public class LandCharacteristicsBean extends CRUDBeanBase<LandCharacteristics> {

	@Autowired
	private LandCharacteristicsDao dao;

	@Override
	protected IPageableDAOBase<LandCharacteristics> getPageableDao() {
		return dao;
	}

	@Override
	protected IIdentifiableDao<LandCharacteristics> getIIdentifiableDao() {
		return dao;
	}
}
