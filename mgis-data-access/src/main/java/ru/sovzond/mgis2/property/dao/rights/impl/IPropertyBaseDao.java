package ru.sovzond.mgis2.property.dao.rights.impl;

import org.springframework.stereotype.Repository;
import ru.sovzond.mgis2.dataaccess.base.impl.CRUDDaoBase;
import ru.sovzond.mgis2.property.dao.rights.ISubjectRightDao;
import ru.sovzond.mgis2.property.model.rights.SubjectRight;

/**
 * Created by Alexander Arakelyan on 30.07.15.
 *
 */
@Repository
public class IPropertyBaseDao extends CRUDDaoBase<SubjectRight> implements ISubjectRightDao {
}
