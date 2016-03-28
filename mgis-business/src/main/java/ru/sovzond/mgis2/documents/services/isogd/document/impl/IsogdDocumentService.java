package ru.sovzond.mgis2.documents.services.isogd.document.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.business.CRUDBeanBase;
import ru.sovzond.mgis2.dataaccess.base.IIdentifiableDao;
import ru.sovzond.mgis2.dataaccess.base.IPageableDAOBase;
import ru.sovzond.mgis2.dataaccess.base.PageableContainer;
import ru.sovzond.mgis2.dataaccess.base.impl.Pageable;
import ru.sovzond.mgis2.dataaccess.base.impl.PagerBuilderCriteria;
import ru.sovzond.mgis2.documents.dao.isogd.document.IIsogdDocumentDao;
import ru.sovzond.mgis2.documents.model.isogd.section.Section;
import ru.sovzond.mgis2.documents.model.isogd.section.Volume;
import ru.sovzond.mgis2.documents.model.isogd.document.IsogdDocument;
import ru.sovzond.mgis2.documents.model.isogd.document.IsogdDocumentClass;
import ru.sovzond.mgis2.documents.model.isogd.document.IsogdDocumentSubObject;
import ru.sovzond.mgis2.documents.services.isogd.document.IIsogdDocumentService;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Alexander Arakelyan on 28/06/15.
 *
 */
@Service
public class IsogdDocumentService extends CRUDBeanBase<IsogdDocument> implements IIsogdDocumentService {

	@Autowired
	private IIsogdDocumentDao dao;

	@Override
	protected IPageableDAOBase<IsogdDocument> getPageableDao() {
		return dao;
	}

	@Override
	protected IIdentifiableDao<IsogdDocument> getIIdentifiableDao() {
		return dao;
	}

	public IsogdDocument load(Long id) {
		return dao.findById(id);
	}

	public IsogdDocument save(IsogdDocument document) {
		dao.save(document);
		return document;
	}

	public void delete(IsogdDocument document) {
		dao.delete(document);
	}

	public PageableContainer<IsogdDocument> pageDocuments(Volume volume, String orderBy, int first, int max) {
		Pageable<IsogdDocument> pager = dao.pager(dao.createFilter(volume, orderBy, first, max));
		List<IsogdDocument> documents = pager.list().stream().map(document -> (IsogdDocument) document.clone()).collect(Collectors.toList());
		return new PageableContainer<>(documents, pager.count(), first, max);
	}

	public List<IsogdDocumentSubObject> listDocumentSubObjectsByVolume(Volume volume) {
		return dao.listAvailableDocumentSubObjects(volume);
	}

	public IsogdDocumentClass readDocumentClassByVolume(Volume volume) {
		return dao.readDocumentClassByVolume(volume);
	}

	public PageableContainer<IsogdDocument> find(Section section, String documentName, Date documentDate, Date documentDateFrom, Date documentDateTill, String documentNumber, String orderBy, int first, int max) {
		PagerBuilderCriteria<IsogdDocument> filter = dao.createSearchDocumentFilter(section, documentName, documentDate, documentDateFrom, documentDateTill, documentNumber, orderBy, first, max);
		Pageable<IsogdDocument> pager = dao.pager(filter);
		return new PageableContainer<>(pager.list().stream().map(document -> (IsogdDocument) document.clone()).collect(Collectors.toList()), pager.count(), first, max);
	}
}
