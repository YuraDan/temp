package ru.sovzond.mgis2.documents.dao.nesting.impl;

import org.springframework.stereotype.Repository;
import ru.sovzond.mgis2.dataaccess.base.impl.CRUDDaoBase;
import ru.sovzond.mgis2.documents.dao.nesting.IIncludedDocumentsDao;
import ru.sovzond.mgis2.documents.model.nesting.IncludedDocuments;

/**
 * Created by Sergey Lvov on 10.03.16.
 *
 */

@Repository
public class IncludedDocumentsDao extends CRUDDaoBase<IncludedDocuments> implements IIncludedDocumentsDao {

}
