package ru.sovzond.mgis2.documents.dao.property.impl;

import org.springframework.stereotype.Repository;
import ru.sovzond.mgis2.dataaccess.base.impl.CRUDDaoBase;
import ru.sovzond.mgis2.documents.dao.property.ICertifyingDocumentDao;
import ru.sovzond.mgis2.documents.model.property.CertifyingDocument;

/**
 * Created by Sergey Lvov on 25.03.16.
 */
@Repository
public class CertifyingDocumentDao extends CRUDDaoBase<CertifyingDocument> implements ICertifyingDocumentDao {
}
