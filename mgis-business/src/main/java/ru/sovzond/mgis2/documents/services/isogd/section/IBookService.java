package ru.sovzond.mgis2.documents.services.isogd.section;

import ru.sovzond.mgis2.dataaccess.base.PageableContainer;
import ru.sovzond.mgis2.documents.model.isogd.document.IsogdDocumentObject;
import ru.sovzond.mgis2.documents.model.isogd.section.Book;
import ru.sovzond.mgis2.documents.model.isogd.section.Section;
import ru.sovzond.mgis2.documents.services.common.IDocumentsService;

import java.util.List;

/**
 * Created by Sergey Lvov on 28.03.16.
 */
public interface IBookService extends IDocumentsService<Book> {
	PageableContainer<Book> list(Section section, String orderBy, int first, int max);
	List<IsogdDocumentObject> listDocumentObjectsBySection(Section section);
}
