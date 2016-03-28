package ru.sovzond.mgis2.documents.services.isogd.section;

import ru.sovzond.mgis2.dataaccess.base.PageableContainer;
import ru.sovzond.mgis2.documents.model.isogd.section.Section;
import ru.sovzond.mgis2.documents.services.common.IDocumentsService;

/**
 * Created by Sergey Lvov on 28.03.16.
 */
public interface ISectionService extends IDocumentsService<Section> {
	Section readSection(Long id);
	Section save(Section section);
	void delete(Section section);
	PageableContainer<Section> pageSections(String orderBy, int first, int max, String name);
}
