package ru.sovzond.mgis2.documents.services.isogd.document.representation;

import ru.sovzond.mgis2.documents.model.isogd.document.representation.RepresentationFormat;
import ru.sovzond.mgis2.documents.services.common.IDocumentsService;

import java.util.List;

/**
 * Created by Sergey Lvov on 28.03.16.
 */
public interface IRepresentationFormatService extends IDocumentsService<RepresentationFormat> {
	List<RepresentationFormat> findByFormat(String contentType);
}
