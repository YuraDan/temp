package ru.sovzond.mgis2.documents.services.isogd.document.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.business.CRUDBeanBase;
import ru.sovzond.mgis2.dataaccess.base.IIdentifiableDao;
import ru.sovzond.mgis2.dataaccess.base.IPageableDAOBase;
import ru.sovzond.mgis2.documents.dao.isogd.document.IIsogdDocumentTypeDao;
import ru.sovzond.mgis2.documents.model.isogd.document.IsogdDocumentType;
import ru.sovzond.mgis2.documents.services.isogd.document.IIsogdDocumentTypeService;

/**
 * Created by asd on 22.06.15.
 */
@Service
public class IsogdDocumentTypeService extends CRUDBeanBase<IsogdDocumentType> implements IIsogdDocumentTypeService {

    @Autowired
    private IIsogdDocumentTypeDao dao;

    @Override
    protected IPageableDAOBase<IsogdDocumentType> getPageableDao() {
        return dao;
    }

    @Override
    protected IIdentifiableDao<IsogdDocumentType> getIIdentifiableDao() {
        return dao;
    }
}
