package ru.sovzond.mgis2.property.services.nesting.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.business.CRUDBeanBase;
import ru.sovzond.mgis2.dataaccess.base.IIdentifiableDao;
import ru.sovzond.mgis2.dataaccess.base.IPageableDAOBase;
import ru.sovzond.mgis2.property.dao.nesting.IIncludedObjectsDao;
import ru.sovzond.mgis2.property.model.nesting.IncludedObjects;
import ru.sovzond.mgis2.property.services.nesting.IIncludedObjectsService;

import java.util.List;

/**
 * Created by Alexander Arakelyan on 16.12.15.
 *
 */
@Service
public class IncludedObjectsService extends CRUDBeanBase<IncludedObjects> implements IIncludedObjectsService {

	@Autowired
	private IIncludedObjectsDao dao;

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
