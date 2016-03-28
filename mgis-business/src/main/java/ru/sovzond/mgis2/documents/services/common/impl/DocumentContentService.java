package ru.sovzond.mgis2.documents.services.common.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.business.CRUDBeanBase;
import ru.sovzond.mgis2.dataaccess.base.IIdentifiableDao;
import ru.sovzond.mgis2.dataaccess.base.IPageableDAOBase;
import ru.sovzond.mgis2.documents.dao.common.IDocumentContentDao;
import ru.sovzond.mgis2.documents.model.common.DocumentContent;
import ru.sovzond.mgis2.documents.services.common.IDocumentContentService;

/**
 * Created by Alexander Arakelyan on 30.06.15.
 */
@Service
public class DocumentContentService extends CRUDBeanBase<DocumentContent> implements IDocumentContentService {

    @Autowired
    private IDocumentContentDao dao;

    @Override
    protected IPageableDAOBase<DocumentContent> getPageableDao() {
        return dao;
    }

    @Override
    protected IIdentifiableDao<DocumentContent> getIIdentifiableDao() {
        return dao;
    }
}
