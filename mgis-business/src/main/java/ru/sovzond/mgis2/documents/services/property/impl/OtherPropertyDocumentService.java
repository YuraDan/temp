package ru.sovzond.mgis2.documents.services.property.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.business.CRUDBeanBase;
import ru.sovzond.mgis2.dataaccess.base.IIdentifiableDao;
import ru.sovzond.mgis2.dataaccess.base.IPageableDAOBase;
import ru.sovzond.mgis2.documents.dao.property.IOtherPropertyDocumentDao;
import ru.sovzond.mgis2.documents.model.property.OtherPropertyDocument;
import ru.sovzond.mgis2.documents.services.property.IOtherPropertyDocumentService;

/**
 * Created by Sergey Lvov on 25.03.16.
 */
@Service
public class OtherPropertyDocumentService extends CRUDBeanBase<OtherPropertyDocument> implements IOtherPropertyDocumentService {

	@Autowired
	private IOtherPropertyDocumentDao dao;

	@Override
	protected IPageableDAOBase<OtherPropertyDocument> getPageableDao() {
		return dao;
	}

	@Override
	protected IIdentifiableDao<OtherPropertyDocument> getIIdentifiableDao() {
		return dao;
	}

	public OtherPropertyDocument load(Long id) {
		return dao.findById(id);
	}

}
