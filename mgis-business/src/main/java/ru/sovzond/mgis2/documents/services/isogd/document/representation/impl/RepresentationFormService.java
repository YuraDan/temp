package ru.sovzond.mgis2.documents.services.isogd.document.representation.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.business.CRUDBeanBase;
import ru.sovzond.mgis2.dataaccess.base.IIdentifiableDao;
import ru.sovzond.mgis2.dataaccess.base.IPageableDAOBase;
import ru.sovzond.mgis2.documents.dao.isogd.document.representation.IRepresentationFormDao;
import ru.sovzond.mgis2.documents.model.isogd.document.representation.RepresentationForm;
import ru.sovzond.mgis2.documents.services.isogd.document.representation.IRepresentationFormService;

/**
 * Created by Alexander Arakelyan on 22.06.15.
 */
@Service
public class RepresentationFormService extends CRUDBeanBase<RepresentationForm> implements IRepresentationFormService {

    @Autowired
    private IRepresentationFormDao dao;

    @Override
    protected IPageableDAOBase<RepresentationForm> getPageableDao() {
        return dao;
    }

    @Override
    protected IIdentifiableDao<RepresentationForm> getIIdentifiableDao() {
        return dao;
    }
}
