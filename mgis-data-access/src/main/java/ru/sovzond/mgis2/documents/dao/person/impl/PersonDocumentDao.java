package ru.sovzond.mgis2.documents.dao.person.impl;

import org.springframework.stereotype.Repository;
import ru.sovzond.mgis2.dataaccess.base.impl.CRUDDaoBase;
import ru.sovzond.mgis2.documents.dao.person.IPersonDocumentDao;
import ru.sovzond.mgis2.documents.model.person.PersonDocument;

/**
 * Created by Sergey Lvov on 28.03.16.
 */
@Repository
public class PersonDocumentDao extends CRUDDaoBase<PersonDocument> implements IPersonDocumentDao {
}
