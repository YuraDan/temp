package ru.sovzond.mgis2.documents.dao.isogd.document.representation;

import ru.sovzond.mgis2.documents.dao.common.IDocumentBaseDao;
import ru.sovzond.mgis2.documents.model.isogd.document.representation.RepresentationFormat;

import java.util.List;

/**
 * Created by Sergey Lvov on 28.03.16.
 */
public interface IRepresentationFormatDao extends IDocumentBaseDao<RepresentationFormat> {
	List<RepresentationFormat> findByFormat(String contentType);
}
