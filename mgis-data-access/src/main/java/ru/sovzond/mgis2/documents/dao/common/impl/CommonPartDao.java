package ru.sovzond.mgis2.documents.dao.common.impl;

import org.springframework.stereotype.Repository;
import ru.sovzond.mgis2.dataaccess.base.impl.CRUDDaoBase;
import ru.sovzond.mgis2.documents.dao.common.ICommonPartDao;
import ru.sovzond.mgis2.documents.model.isogd.document.IsogdDocument;
import ru.sovzond.mgis2.documents.model.common.DocumentContent;
import ru.sovzond.mgis2.documents.model.common.CommonPart;

import java.util.List;

/**
 * Created by asd on 22/06/15.
 */
@Repository
public class CommonPartDao extends CRUDDaoBase<CommonPart> implements ICommonPartDao {
	public List<DocumentContent> listDocumentContents(IsogdDocument document) {
		//noinspection JpaQlInspection,unchecked
		return getSession().createQuery("SELECT dcs FROM Document d JOIN d.commonPart cp JOIN cp.documentContents dcs WHERE d = :doc") //
				.setParameter("doc", document) //
				.list();
	}
}
