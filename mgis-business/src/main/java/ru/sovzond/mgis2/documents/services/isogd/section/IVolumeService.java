package ru.sovzond.mgis2.documents.services.isogd.section;

import ru.sovzond.mgis2.dataaccess.base.PageableContainer;
import ru.sovzond.mgis2.documents.model.isogd.section.Book;
import ru.sovzond.mgis2.documents.model.isogd.section.Volume;
import ru.sovzond.mgis2.documents.services.common.IDocumentsService;

/**
 * Created by Sergey Lvov on 28.03.16.
 */
public interface IVolumeService extends IDocumentsService<Volume> {
	Volume readVolume(Long id);
	PageableContainer<Volume> list(Book book, String orderBy, int first, int max);
}
