package ru.sovzond.mgis2.documents.services.isogd.document.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.business.CRUDBeanBase;
import ru.sovzond.mgis2.dataaccess.base.IIdentifiableDao;
import ru.sovzond.mgis2.dataaccess.base.IPageableDAOBase;
import ru.sovzond.mgis2.documents.dao.isogd.document.IIsogdSpecialPartDao;
import ru.sovzond.mgis2.documents.model.isogd.document.IsogdSpecialPart;
import ru.sovzond.mgis2.documents.services.isogd.document.IIsogdSpecialPartService;

/**
 * Created by Alexander Arakelyan on 30.06.15.
 */
@Service
public class IsogdSpecialPartService extends CRUDBeanBase<IsogdSpecialPart> implements IIsogdSpecialPartService {

    @Autowired
    private IIsogdSpecialPartDao dao;

    @Override
    protected IPageableDAOBase<IsogdSpecialPart> getPageableDao() {
        return dao;
    }

    @Override
    protected IIdentifiableDao<IsogdSpecialPart> getIIdentifiableDao() {
        return dao;
    }
}
