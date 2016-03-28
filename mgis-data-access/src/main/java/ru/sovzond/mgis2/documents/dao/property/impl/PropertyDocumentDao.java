package ru.sovzond.mgis2.documents.dao.property.impl;

import org.springframework.stereotype.Repository;
import ru.sovzond.mgis2.dataaccess.base.impl.CRUDDaoBase;
import ru.sovzond.mgis2.documents.dao.property.IPropertyDocumentDao;
import ru.sovzond.mgis2.documents.model.property.PropertyDocument;

/**
 * Created by Sergey Lvov on 28.03.16.
 */
@Repository
public class PropertyDocumentDao extends CRUDDaoBase<PropertyDocument> implements IPropertyDocumentDao {
}
