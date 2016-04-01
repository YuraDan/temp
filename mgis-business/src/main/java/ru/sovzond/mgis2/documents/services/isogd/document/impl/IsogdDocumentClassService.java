package ru.sovzond.mgis2.documents.services.isogd.document.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.business.CRUDBeanBase;
import ru.sovzond.mgis2.dataaccess.base.IIdentifiableDao;
import ru.sovzond.mgis2.dataaccess.base.IPageableDAOBase;
import ru.sovzond.mgis2.documents.dao.isogd.document.IIsogdDocumentClassDao;
import ru.sovzond.mgis2.documents.model.isogd.document.IsogdDocumentClass;
import ru.sovzond.mgis2.documents.services.isogd.document.IIsogdDocumentClassService;

/**
 * Created by Alexander Arakelyan on 22.06.15.
 */
@Service
public class IsogdDocumentClassService extends CRUDBeanBase<IsogdDocumentClass> implements IIsogdDocumentClassService {

    @Autowired
    private IIsogdDocumentClassDao dao;

    @Override
    protected IPageableDAOBase<IsogdDocumentClass> getPageableDao() {
        return dao;
    }

    @Override
    protected IIdentifiableDao<IsogdDocumentClass> getIIdentifiableDao() {
        return dao;
    }
}
