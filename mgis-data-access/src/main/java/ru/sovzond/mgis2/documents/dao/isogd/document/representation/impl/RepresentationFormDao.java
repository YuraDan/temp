package ru.sovzond.mgis2.documents.dao.isogd.document.representation.impl;

import org.springframework.stereotype.Repository;
import ru.sovzond.mgis2.dataaccess.base.impl.CRUDDaoBase;
import ru.sovzond.mgis2.documents.dao.isogd.document.representation.IRepresentationFormDao;
import ru.sovzond.mgis2.documents.model.isogd.document.representation.RepresentationForm;

/**
 * Created by Alexander Arakelyan on 22.06.15.
 */
@Repository
public class RepresentationFormDao extends CRUDDaoBase<RepresentationForm> implements IRepresentationFormDao {
}
