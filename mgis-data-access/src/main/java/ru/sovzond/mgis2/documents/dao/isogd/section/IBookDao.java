package ru.sovzond.mgis2.documents.dao.isogd.section;

import ru.sovzond.mgis2.dataaccess.base.impl.PagerBuilderCriteria;
import ru.sovzond.mgis2.documents.dao.common.IDocumentBaseDao;
import ru.sovzond.mgis2.documents.model.isogd.section.Book;
import ru.sovzond.mgis2.documents.model.isogd.section.Section;
import ru.sovzond.mgis2.documents.model.isogd.document.IsogdDocumentObject;

import java.util.List;

/**
 * Created by Sergey Lvov on 28.03.16.
 */
public interface IBookDao extends IDocumentBaseDao<Book> {
	Book findById(Long id);
	PagerBuilderCriteria<Book> createFilter(Section section, String orderBy, int first, int max);
	List<IsogdDocumentObject> listAvailableDocumentObjects(Section section);
}
