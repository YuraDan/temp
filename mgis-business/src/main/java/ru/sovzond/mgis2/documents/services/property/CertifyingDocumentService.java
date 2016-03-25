package ru.sovzond.mgis2.documents.services.property;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.business.CRUDBeanBase;
import ru.sovzond.mgis2.dataaccess.base.IIdentifiableDao;
import ru.sovzond.mgis2.dataaccess.base.IPageableDAOBase;
import ru.sovzond.mgis2.documents.dao.property.CertifyingDocumentDao;
import ru.sovzond.mgis2.documents.model.property.CertifyingDocument;

/**
 * Created by Sergey Lvov on 25.03.16.
 */
@Service
public class CertifyingDocumentService extends CRUDBeanBase<CertifyingDocument> {

	@Autowired
	private CertifyingDocumentDao dao;

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
