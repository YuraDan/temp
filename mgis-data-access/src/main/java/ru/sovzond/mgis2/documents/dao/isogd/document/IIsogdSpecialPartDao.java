package ru.sovzond.mgis2.documents.dao.isogd.document;

import ru.sovzond.mgis2.documents.dao.common.IDocumentBaseDao;
import ru.sovzond.mgis2.documents.model.common.DocumentContent;
import ru.sovzond.mgis2.documents.model.isogd.document.IsogdDocument;
import ru.sovzond.mgis2.documents.model.isogd.document.IsogdSpecialPart;

import java.util.List;

/**
 * Created by Sergey Lvov on 28.03.16.
 */
public interface IIsogdSpecialPartDao extends IDocumentBaseDao<IsogdSpecialPart> {
	List<DocumentContent> listDocumentContents(IsogdDocument document);
}
