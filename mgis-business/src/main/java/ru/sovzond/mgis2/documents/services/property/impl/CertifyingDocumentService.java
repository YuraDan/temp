package ru.sovzond.mgis2.documents.services.property.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.business.CRUDBeanBase;
import ru.sovzond.mgis2.dataaccess.base.IIdentifiableDao;
import ru.sovzond.mgis2.dataaccess.base.IPageableDAOBase;
import ru.sovzond.mgis2.documents.dao.property.ICertifyingDocumentDao;
import ru.sovzond.mgis2.documents.model.property.CertifyingDocument;
import ru.sovzond.mgis2.documents.services.property.ICertifyingDocumentService;

/**
 * Created by Sergey Lvov on 25.03.16.
 */
@Service
public class CertifyingDocumentService extends CRUDBeanBase<CertifyingDocument> implements ICertifyingDocumentService {

	@Autowired
	private ICertifyingDocumentDao dao;

	@Override
	protected IPageableDAOBase<CertifyingDocument> getPageableDao() {
		return dao;
	}

	@Override
	protected IIdentifiableDao<CertifyingDocument> getIIdentifiableDao() {
		return dao;
	}

	public CertifyingDocument load(Long id) {
		return dao.findById(id);
	}

}
