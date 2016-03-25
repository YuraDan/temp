package ru.sovzond.mgis2.documents.services.isogd.business.classifiers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.business.CRUDBeanBase;
import ru.sovzond.mgis2.dataaccess.base.IIdentifiableDao;
import ru.sovzond.mgis2.dataaccess.base.IPageableDAOBase;
import ru.sovzond.mgis2.documents.model.isogd.classifiers.documents.IsogdDocumentType;
import ru.sovzond.mgis2.documents.dao.isogd.classifiers.documents.DocumentTypeDao;

/**
 * Created by asd on 22.06.15.
 */
@Service
public class DocumentTypeService extends CRUDBeanBase<IsogdDocumentType> {

    @Autowired
    private DocumentTypeDao dao;

    @Override
    protected IPageableDAOBase<IsogdDocumentType> getPageableDao() {
        return dao;
    }

    @Override
    protected IIdentifiableDao<IsogdDocumentType> getIIdentifiableDao() {
        return dao;
    }
}
