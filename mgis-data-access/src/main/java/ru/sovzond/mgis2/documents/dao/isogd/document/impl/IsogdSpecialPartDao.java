package ru.sovzond.mgis2.documents.dao.isogd.document.impl;

import org.springframework.stereotype.Repository;
import ru.sovzond.mgis2.dataaccess.base.impl.CRUDDaoBase;
import ru.sovzond.mgis2.documents.dao.isogd.document.IIsogdSpecialPartDao;
import ru.sovzond.mgis2.documents.model.common.DocumentContent;
import ru.sovzond.mgis2.documents.model.isogd.document.IsogdDocument;
import ru.sovzond.mgis2.documents.model.isogd.document.IsogdSpecialPart;

import java.util.List;

/**
 * Created by asd on 22/06/15.
 */
@Repository
public class IsogdSpecialPartDao extends CRUDDaoBase<IsogdSpecialPart> implements IIsogdSpecialPartDao {
	public List<DocumentContent> listDocumentContents(IsogdDocument document) {
		//noinspection unchecked,JpaQlInspection
		return getSession().createQuery("SELECT dcs FROM Document d JOIN d.specialPart sp JOIN sp.documentContents dcs WHERE d = :doc") //
				.setParameter("doc", document) //
				.list();
	}
}
