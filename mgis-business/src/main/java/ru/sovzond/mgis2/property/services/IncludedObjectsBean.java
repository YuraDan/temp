package ru.sovzond.mgis2.property.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.business.CRUDBeanBase;
import ru.sovzond.mgis2.dataaccess.base.IIdentifiableDao;
import ru.sovzond.mgis2.dataaccess.base.IPageableDAOBase;
import ru.sovzond.mgis2.property.dao.IncludedObjectsDao;
import ru.sovzond.mgis2.property.model.IncludedObjects;

import java.util.List;

/**
 * Created by Alexander Arakelyan on 16.12.15.
 *
 */
@Service
public class IncludedObjectsBean extends CRUDBeanBase<IncludedObjects> {

	@Autowired
	private IncludedObjectsDao dao;

	@Override
	protected IPageableDAOBase<IncludedObjects> getPageableDao() {
		return dao;
	}

	@Override
	protected IIdentifiableDao<IncludedObjects> getIIdentifiableDao() {
		return dao;
	}

	public List<IncludedObjects> getIncludedObjectsByCapitalConstruct(Long constructId) {
		return dao.findByNestedObject(null,  constructId);
	}

	public List<IncludedObjects> getIncludedObjectsByLand(Long landId) {
		return dao.findByNestedObject(landId, null);
	}

}
