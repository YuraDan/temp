package ru.sovzond.mgis2.documents.services.property.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.business.CRUDBeanBase;
import ru.sovzond.mgis2.dataaccess.base.IIdentifiableDao;
import ru.sovzond.mgis2.dataaccess.base.IPageableDAOBase;
import ru.sovzond.mgis2.documents.dao.property.IPropertyDocumentDao;
import ru.sovzond.mgis2.documents.model.property.PropertyDocument;
import ru.sovzond.mgis2.documents.services.property.IPropertyDocumentService;

/**
 * Created by Sergey Lvov on 28.03.16.
 */
@Service
public class PropertyDocumentService extends CRUDBeanBase<PropertyDocument> implements IPropertyDocumentService {

	@Autowired
	private IPropertyDocumentDao dao;

	@Override
	protected IPageableDAOBase<PropertyDocument> getPageableDao() {
		return dao;
	}

	@Override
	protected IIdentifiableDao<PropertyDocument> getIIdentifiableDao() {
		return dao;
	}

	public PropertyDocument load(Long id) {
		return dao.findById(id);
	}

}
