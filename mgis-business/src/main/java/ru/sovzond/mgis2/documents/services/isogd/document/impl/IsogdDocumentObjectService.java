package ru.sovzond.mgis2.documents.services.isogd.document.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.business.CRUDBeanBase;
import ru.sovzond.mgis2.dataaccess.base.IIdentifiableDao;
import ru.sovzond.mgis2.dataaccess.base.IPageableDAOBase;
import ru.sovzond.mgis2.dataaccess.base.PageableContainer;
import ru.sovzond.mgis2.dataaccess.base.impl.Pageable;
import ru.sovzond.mgis2.documents.dao.isogd.document.IIsogdDocumentObjectDao;
import ru.sovzond.mgis2.documents.model.isogd.document.IsogdDocumentClass;
import ru.sovzond.mgis2.documents.model.isogd.document.IsogdDocumentObject;
import ru.sovzond.mgis2.documents.services.isogd.document.IIsogdDocumentObjectService;

import java.util.stream.Collectors;

/**
 * Created by Alexander Arakelyan on 22.06.15.
 */
@Service
public class IsogdDocumentObjectService extends CRUDBeanBase<IsogdDocumentObject> implements IIsogdDocumentObjectService {

	@Autowired
	private IIsogdDocumentObjectDao dao;

	@Override
	protected IPageableDAOBase<IsogdDocumentObject> getPageableDao() {
		return dao;
	}

	@Override
	protected IIdentifiableDao<IsogdDocumentObject> getIIdentifiableDao() {
		return dao;
	}

	public PageableContainer<IsogdDocumentObject> list(IsogdDocumentClass documentClass, int first, int max) {
		Pageable<IsogdDocumentObject> pager = dao.pager(dao.createFilter(documentClass, first, max));
		return new PageableContainer<>(pager.list().stream().map(IsogdDocumentObject::clone).collect(Collectors.toList()), pager.count(), first, max);
	}
}
