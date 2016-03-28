package ru.sovzond.mgis2.documents.dao.isogd.section;

import ru.sovzond.mgis2.dataaccess.base.impl.PagerBuilderCriteria;
import ru.sovzond.mgis2.documents.dao.common.IDocumentBaseDao;
import ru.sovzond.mgis2.documents.model.isogd.section.Book;
import ru.sovzond.mgis2.documents.model.isogd.section.Volume;

/**
 * Created by Sergey Lvov on 28.03.16.
 */
public interface IVolumeDao extends IDocumentBaseDao<Volume> {
	Volume findById(Long id);
	PagerBuilderCriteria<Volume> createFilter(Book book, String sortOrder, int first, int max);
}
