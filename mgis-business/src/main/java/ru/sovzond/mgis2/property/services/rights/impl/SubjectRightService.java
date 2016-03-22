package ru.sovzond.mgis2.property.services.rights.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.business.CRUDBeanBase;
import ru.sovzond.mgis2.dataaccess.base.IIdentifiableDao;
import ru.sovzond.mgis2.dataaccess.base.IPageableDAOBase;
import ru.sovzond.mgis2.property.dao.rights.ISubjectRightDao;
import ru.sovzond.mgis2.property.model.rights.SubjectRight;
import ru.sovzond.mgis2.property.services.rights.ISubjectRightService;

/**
 * Created by Alexander Arakelyan on 30.07.15.
 *
 */
@Service
public class SubjectRightService extends CRUDBeanBase<SubjectRight> implements ISubjectRightService {

	@Autowired
	private ISubjectRightDao dao;

	@Override
	protected IPageableDAOBase<SubjectRight> getPageableDao() {
		return dao;
	}

	@Override
	protected IIdentifiableDao<SubjectRight> getIIdentifiableDao() {
		return dao;
	}
}
