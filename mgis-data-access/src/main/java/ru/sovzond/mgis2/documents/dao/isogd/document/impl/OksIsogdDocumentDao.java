package ru.sovzond.mgis2.documents.dao.isogd.document.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import ru.sovzond.mgis2.dataaccess.base.impl.CRUDDaoBase;
import ru.sovzond.mgis2.dataaccess.base.impl.PagerBuilderCriteria;
import ru.sovzond.mgis2.documents.dao.isogd.document.IOksIsogdDocumentDao;
import ru.sovzond.mgis2.documents.model.isogd.section.Section;
import ru.sovzond.mgis2.documents.model.isogd.section.Volume;
import ru.sovzond.mgis2.documents.model.isogd.document.OksIsogdDocument;
import ru.sovzond.mgis2.documents.model.isogd.document.IsogdDocumentClass;
import ru.sovzond.mgis2.documents.model.isogd.document.IsogdDocumentSubObject;

import java.util.Date;
import java.util.List;

/**
 * Created by Sergey Lvov on 28.03.16.
 */
@Repository
public class OksIsogdDocumentDao extends CRUDDaoBase<OksIsogdDocument> implements IOksIsogdDocumentDao {
	public OksIsogdDocument findById(Long id) {
		return (OksIsogdDocument) filter(Restrictions.eq("id", id)).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<IsogdDocumentSubObject> listAvailableDocumentSubObjects(Volume volume) {
		// return volume.getBook().getDocumentObject().getDocumentSubObjects();
		//noinspection JpaQlInspection
		return getSession() //
				.createQuery(
						"SELECT docSubObj FROM Volume vol " +
								"JOIN vol.book book " +
								"JOIN book.documentObject docObj " +
								"JOIN docObj.documentSubObjects docSubObj " +
								"WHERE vol = :vol ") //
				.setParameter("vol", volume) //
				.list();
	}

	public IsogdDocumentClass readDocumentClassByVolume(Volume volume) {
		//noinspection JpaQlInspection
		return (IsogdDocumentClass) getSession() //
				.createQuery(
						"SELECT docClass FROM Volume vol " +
								"JOIN vol.book book " +
								"JOIN book.section sect " +
								"JOIN sect.documentClass docClass " +
								"WHERE vol = :vol") //
				.setParameter("vol", volume) //
				.uniqueResult();
	}

	class DocumentBaseBuilder extends PagerBuilderCriteria<OksIsogdDocument> {
		private Volume volume;

		private DocumentBaseBuilder(Volume volume, String orderBy, int first, int max) {
			super(orderBy, first, max);
			this.volume = volume;
		}

		@Override
		protected void applyFilter(Criteria criteria) {
			criteria.add(Restrictions.eq("volume", volume));
		}
	}

	public PagerBuilderCriteria<OksIsogdDocument> createFilter(Volume volume, String order, int first, int max) {
		return new DocumentBaseBuilder(volume, order, first, max);
	}

	public PagerBuilderCriteria<OksIsogdDocument> createSearchDocumentFilter(Section section, String documentName, Date documentDate, Date documentDateFrom, Date documentDateTill, String documentNumber, String orderBy, int first, int max) {
		return new SearchDocumentFilter(section, documentName, documentDate, documentDateFrom, documentDateTill, documentNumber, orderBy, first, max);
	}

	private class SearchDocumentFilter extends PagerBuilderCriteria<OksIsogdDocument> {
		private Section section;
		private String documentName;
		private Date documentDate;
		private Date documentDateFrom;
		private Date documentDateTill;
		private String documentNumber;

		private SearchDocumentFilter(Section section, String documentName, Date documentDate, Date documentDateFrom, Date documentDateTill, String documentNumber, String orderBy, int first, int max) {
			super(orderBy, first, max);
			this.section = section;
			this.documentName = documentName;
			this.documentDate = documentDate;
			this.documentDateFrom = documentDateFrom;
			this.documentDateTill = documentDateTill;
			this.documentNumber = documentNumber;
		}

		@Override
		protected void applyFilter(Criteria criteria) {
			criteria.createAlias("volume", "vol");
			criteria.createAlias("vol.book", "bk");
			criteria.createAlias("bk.section", "section");
			if (section != null) {
				criteria.add(Restrictions.eq("bk.section", section));
			}
			if (documentName != null && documentName.length() > 0) {
				criteria.add(Restrictions.ilike("name", "%" + documentName + "%"));
			}
			if (documentDate != null) {
				criteria.add(Restrictions.eq("docDate", documentDate));
			}
			if (documentDateFrom != null) {
				criteria.add(Restrictions.ge("docDate", documentDateFrom));
			}
			if (documentDateTill != null) {
				criteria.add(Restrictions.le("docDate", documentDateTill));
			}
			if (documentNumber != null && documentNumber.length() > 0) {
				criteria.add(Restrictions.like("docNumber", "%" + documentNumber + "%"));
			}
		}
	}

}
