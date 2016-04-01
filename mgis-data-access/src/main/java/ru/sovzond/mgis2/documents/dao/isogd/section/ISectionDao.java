package ru.sovzond.mgis2.documents.dao.isogd.section;

import ru.sovzond.mgis2.dataaccess.base.impl.PagerBuilderBase;
import ru.sovzond.mgis2.documents.dao.common.IDocumentBaseDao;
import ru.sovzond.mgis2.documents.model.isogd.section.Section;

/**
 * Created by Sergey Lvov on 28.03.16.
 */
public interface ISectionDao extends IDocumentBaseDao<Section> {
	Section findById(Long id);
	PagerBuilderBase<Section> createFilter(String name, String orderBy, int first, int max);
}
