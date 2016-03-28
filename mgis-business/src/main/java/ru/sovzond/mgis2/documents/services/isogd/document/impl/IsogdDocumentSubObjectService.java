package ru.sovzond.mgis2.documents.services.isogd.document.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.business.CRUDBeanBase;
import ru.sovzond.mgis2.dataaccess.base.IIdentifiableDao;
import ru.sovzond.mgis2.dataaccess.base.IPageableDAOBase;
import ru.sovzond.mgis2.dataaccess.base.PageableContainer;
import ru.sovzond.mgis2.dataaccess.base.impl.Pageable;
import ru.sovzond.mgis2.documents.dao.isogd.document.IIsogdDocumentSubObjectDao;
import ru.sovzond.mgis2.documents.model.isogd.document.IsogdDocumentObject;
import ru.sovzond.mgis2.documents.model.isogd.document.IsogdDocumentSubObject;
import ru.sovzond.mgis2.documents.services.isogd.document.IIsogdDocumentSubObjectService;

/**
 * Created by Alexander Arakelyan on 22.06.15.
 */
@Service
public class IsogdDocumentSubObjectService extends CRUDBeanBase<IsogdDocumentSubObject> implements IIsogdDocumentSubObjectService {

	@Autowired
	private IIsogdDocumentSubObjectDao dao;

	@Override
	protected IPageableDAOBase<IsogdDocumentSubObject> getPageableDao() {
		return dao;
	}

	@Override
	protected IIdentifiableDao<IsogdDocumentSubObject> getIIdentifiableDao() {
		return dao;
	}

	public PageableContainer<IsogdDocumentSubObject> list(IsogdDocumentObject documentObject, int first, int max) {
		Pageable<IsogdDocumentSubObject> pager = dao.pager(dao.createFilter(documentObject, first, max));
		return new PageableContainer<>(pager.list(), pager.count(), first, max);
	}

}
