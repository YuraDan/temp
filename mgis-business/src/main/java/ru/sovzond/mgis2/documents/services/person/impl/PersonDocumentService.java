package ru.sovzond.mgis2.documents.services.person.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.business.CRUDBeanBase;
import ru.sovzond.mgis2.dataaccess.base.IIdentifiableDao;
import ru.sovzond.mgis2.dataaccess.base.IPageableDAOBase;
import ru.sovzond.mgis2.documents.dao.person.IPersonDocumentDao;
import ru.sovzond.mgis2.documents.model.person.PersonDocument;
import ru.sovzond.mgis2.documents.services.person.IPersonDocumentService;

/**
 * Created by Sergey Lvov on 28.03.16.
 */
@Service
public class PersonDocumentService extends CRUDBeanBase<PersonDocument> implements IPersonDocumentService {

	@Autowired
	private IPersonDocumentDao dao;

	@Override
	protected IPageableDAOBase<PersonDocument> getPageableDao() {
		return dao;
	}

	@Override
	protected IIdentifiableDao<PersonDocument> getIIdentifiableDao() {
		return dao;
	}

	public PersonDocument load(Long id) {
		return dao.findById(id);
	}

}
