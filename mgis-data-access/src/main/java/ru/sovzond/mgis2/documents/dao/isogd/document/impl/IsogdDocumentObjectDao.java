package ru.sovzond.mgis2.documents.dao.isogd.document.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import ru.sovzond.mgis2.dataaccess.base.impl.CRUDDaoBase;
import ru.sovzond.mgis2.dataaccess.base.impl.PagerBuilderCriteria;
import ru.sovzond.mgis2.documents.dao.isogd.document.IIsogdDocumentObjectDao;
import ru.sovzond.mgis2.documents.model.isogd.document.IsogdDocumentClass;
import ru.sovzond.mgis2.documents.model.isogd.document.IsogdDocumentObject;

/**
 * Created by Alexander Arakelyan on 22.06.15.
 */
@Repository
public class IsogdDocumentObjectDao extends CRUDDaoBase<IsogdDocumentObject> implements IIsogdDocumentObjectDao {

	public static final String DOCUMENT_CLASS = "documentClass";

	private class DocumentObjectBaseBuilder extends PagerBuilderCriteria<IsogdDocumentObject> {
		private IsogdDocumentClass documentClass;

		public DocumentObjectBaseBuilder(IsogdDocumentClass documentClass, int first, int max) {
			super(first, max);
			this.documentClass = documentClass;
		}

		@Override
		protected void applyFilter(Criteria criteria) {
			criteria.add(Restrictions.eq(DOCUMENT_CLASS, documentClass));
		}
	}

	public PagerBuilderCriteria<IsogdDocumentObject> createFilter(IsogdDocumentClass documentClass, int first, int max) {
		return new DocumentObjectBaseBuilder(documentClass, first, max);
	}
}
