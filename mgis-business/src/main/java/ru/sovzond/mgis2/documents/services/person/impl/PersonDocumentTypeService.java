package ru.sovzond.mgis2.documents.services.person.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.business.CRUDBeanBase;
import ru.sovzond.mgis2.dataaccess.base.IIdentifiableDao;
import ru.sovzond.mgis2.dataaccess.base.IPageableDAOBase;
import ru.sovzond.mgis2.documents.dao.person.IPersonDocumentTypeDao;
import ru.sovzond.mgis2.documents.model.person.PersonDocumentType;
import ru.sovzond.mgis2.documents.services.person.IPersonDocumentTypeService;

/**
 * Created by Sergey Lvov on 28.03.16.
 */
@Service
public class PersonDocumentTypeService extends CRUDBeanBase<PersonDocumentType> implements IPersonDocumentTypeService {

	@Autowired
	private IPersonDocumentTypeDao dao;

	@Override
	protected IPageableDAOBase<PersonDocumentType> getPageableDao() {
		return dao;
	}

	@Override
	protected IIdentifiableDao<PersonDocumentType> getIIdentifiableDao() {
		return dao;
	}

	public PersonDocumentType load(Long id) {
		return dao.findById(id);
	}

}
