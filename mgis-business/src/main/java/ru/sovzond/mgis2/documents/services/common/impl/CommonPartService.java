package ru.sovzond.mgis2.documents.services.common.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.business.CRUDBeanBase;
import ru.sovzond.mgis2.dataaccess.base.IIdentifiableDao;
import ru.sovzond.mgis2.dataaccess.base.IPageableDAOBase;
import ru.sovzond.mgis2.documents.dao.common.ICommonPartDao;
import ru.sovzond.mgis2.documents.model.common.CommonPart;
import ru.sovzond.mgis2.documents.services.common.ICommonPartService;

/**
 * Created by Alexander Arakelyan on 30.06.15.
 */
@Service
public class CommonPartService extends CRUDBeanBase<CommonPart> implements ICommonPartService {

    @Autowired
    private ICommonPartDao dao;

    @Override
    protected IPageableDAOBase<CommonPart> getPageableDao() {
        return dao;
    }

    @Override
    protected IIdentifiableDao<CommonPart> getIIdentifiableDao() {
        return dao;
    }
}
