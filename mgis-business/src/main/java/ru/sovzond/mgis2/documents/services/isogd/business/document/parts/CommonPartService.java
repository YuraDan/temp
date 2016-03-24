package ru.sovzond.mgis2.documents.services.isogd.business.document.parts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.business.CRUDBeanBase;
import ru.sovzond.mgis2.dataaccess.base.IIdentifiableDao;
import ru.sovzond.mgis2.dataaccess.base.IPageableDAOBase;
import ru.sovzond.mgis2.documents.dao.isogd.document.CommonPartDao;
import ru.sovzond.mgis2.documents.model.isogd.document.parts.CommonPart;

/**
 * Created by Alexander Arakelyan on 30.06.15.
 */
@Service
public class CommonPartService extends CRUDBeanBase<CommonPart> {

    @Autowired
    private CommonPartDao dao;

    @Override
    protected IPageableDAOBase<CommonPart> getPageableDao() {
        return dao;
    }

    @Override
    protected IIdentifiableDao<CommonPart> getIIdentifiableDao() {
        return dao;
    }
}
