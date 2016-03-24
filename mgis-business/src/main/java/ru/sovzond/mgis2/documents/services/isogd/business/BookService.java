package ru.sovzond.mgis2.documents.services.isogd.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.business.CRUDBeanBase;
import ru.sovzond.mgis2.dataaccess.base.IIdentifiableDao;
import ru.sovzond.mgis2.dataaccess.base.IPageableDAOBase;
import ru.sovzond.mgis2.dataaccess.base.PageableContainer;
import ru.sovzond.mgis2.dataaccess.base.impl.Pageable;
import ru.sovzond.mgis2.documents.model.isogd.Book;
import ru.sovzond.mgis2.documents.dao.isogd.BookDao;
import ru.sovzond.mgis2.documents.model.isogd.Section;
import ru.sovzond.mgis2.documents.model.isogd.classifiers.documents.DocumentObject;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Alexander Arakelyan on 28/06/15.
 */
@Service
public class BookService extends CRUDBeanBase<Book> {

	@Autowired
	private BookDao dao;

	@Override
	protected IPageableDAOBase<Book> getPageableDao() {
		return dao;
	}

	@Override
	protected IIdentifiableDao<Book> getIIdentifiableDao() {
		return dao;
	}

	public PageableContainer<Book> list(Section section, String orderBy, int first, int max) {
		Pageable<Book> pager = dao.pager(dao.createFilter(section, orderBy, first, max));
		List<Book> books = pager.list().stream().map(book -> book.clone()).collect(Collectors.toList());
		return new PageableContainer<>(books, pager.count(), first, max);
	}

	public List<DocumentObject> listDocumentObjectsBySection(Section section) {
		return dao.listAvailableDocumentObjects(section);
	}

}
