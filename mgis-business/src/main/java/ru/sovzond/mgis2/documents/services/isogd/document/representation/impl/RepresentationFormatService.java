package ru.sovzond.mgis2.documents.services.isogd.document.representation.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.business.CRUDBeanBase;
import ru.sovzond.mgis2.dataaccess.base.IIdentifiableDao;
import ru.sovzond.mgis2.dataaccess.base.IPageableDAOBase;
import ru.sovzond.mgis2.documents.dao.isogd.document.representation.IRepresentationFormatDao;
import ru.sovzond.mgis2.documents.model.isogd.document.representation.RepresentationFormat;
import ru.sovzond.mgis2.documents.services.isogd.document.representation.IRepresentationFormatService;

import java.util.List;

/**
 * Created by Alexander Arakelyan on 22.06.15.
 */
@Service
public class RepresentationFormatService extends CRUDBeanBase<RepresentationFormat> implements IRepresentationFormatService {

    @Autowired
    private IRepresentationFormatDao dao;

    @Override
    protected IPageableDAOBase<RepresentationFormat> getPageableDao() {
        return dao;
    }

    @Override
    protected IIdentifiableDao<RepresentationFormat> getIIdentifiableDao() {
        return dao;
    }

    public List<RepresentationFormat> findByFormat(String contentType) {
        return dao.findByFormat(contentType);
    }
}
