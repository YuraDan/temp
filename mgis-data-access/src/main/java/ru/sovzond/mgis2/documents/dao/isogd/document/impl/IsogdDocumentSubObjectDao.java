package ru.sovzond.mgis2.documents.dao.isogd.document.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import ru.sovzond.mgis2.dataaccess.base.impl.CRUDDaoBase;
import ru.sovzond.mgis2.dataaccess.base.impl.PagerBuilderCriteria;
import ru.sovzond.mgis2.documents.dao.isogd.document.IIsogdDocumentSubObjectDao;
import ru.sovzond.mgis2.documents.model.isogd.document.IsogdDocumentObject;
import ru.sovzond.mgis2.documents.model.isogd.document.IsogdDocumentSubObject;

/**
 * Created by Alexander Arakelyan on 22.06.15.
 */
@Repository
public class IsogdDocumentSubObjectDao extends CRUDDaoBase<IsogdDocumentSubObject> implements IIsogdDocumentSubObjectDao {

	public static final String DOCUMENT_OBJECT = "documentObject";

	private class DocumentSubObjectBaseBuilder extends PagerBuilderCriteria<IsogdDocumentSubObject> {
		private IsogdDocumentObject documentObject;

		public DocumentSubObjectBaseBuilder(IsogdDocumentObject documentObject, int first, int max) {
			super(first, max);
			this.documentObject = documentObject;
		}

		@Override
		protected void applyFilter(Criteria criteria) {
			criteria.add(Restrictions.eq(DOCUMENT_OBJECT, documentObject));
		}
	}

	public PagerBuilderCriteria<IsogdDocumentSubObject> createFilter(IsogdDocumentObject documentObject, int first, int max) {
		return new DocumentSubObjectBaseBuilder(documentObject, first, max);
	}
}
