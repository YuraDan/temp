package ru.sovzond.mgis2.documents.dao.common.impl;

import org.springframework.stereotype.Repository;
import ru.sovzond.mgis2.dataaccess.base.impl.CRUDDaoBase;
import ru.sovzond.mgis2.documents.dao.common.IDocumentContentDao;
import ru.sovzond.mgis2.documents.model.common.DocumentContent;

/**
 * Created by Alexander Arakelyan on 28/06/15.
 */
@Repository
public class DocumentContentDao extends CRUDDaoBase<DocumentContent> implements IDocumentContentDao {

}
