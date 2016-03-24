package ru.sovzond.mgis2.documents.dao.isogd.classifiers.documents;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import ru.sovzond.mgis2.dataaccess.base.impl.CRUDDaoBase;
import ru.sovzond.mgis2.dataaccess.base.impl.PagerBuilderCriteria;
import ru.sovzond.mgis2.documents.model.isogd.classifiers.documents.DocumentClass;
import ru.sovzond.mgis2.documents.model.isogd.classifiers.documents.DocumentObject;

/**
 * Created by Alexander Arakelyan on 22.06.15.
 */
@Repository
public class DocumentObjectDao extends CRUDDaoBase<DocumentObject> {

	public static final String DOCUMENT_CLASS = "documentClass";

	private class DocumentObjectBaseBuilder extends PagerBuilderCriteria<DocumentObject> {
		private DocumentClass documentClass;

		public DocumentObjectBaseBuilder(DocumentClass documentClass, int first, int max) {
			super(first, max);
			this.documentClass = documentClass;
		}

		@Override
		protected void applyFilter(Criteria criteria) {
			criteria.add(Restrictions.eq(DOCUMENT_CLASS, documentClass));
		}
	}

	public PagerBuilderCriteria<DocumentObject> createFilter(DocumentClass documentClass, int first, int max) {
		return new DocumentObjectBaseBuilder(documentClass, first, max);
	}
}
