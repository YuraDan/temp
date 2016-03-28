package ru.sovzond.mgis2.documents.dao.isogd.section.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import ru.sovzond.mgis2.dataaccess.base.impl.CRUDDaoBase;
import ru.sovzond.mgis2.dataaccess.base.impl.PagerBuilderCriteria;
import ru.sovzond.mgis2.documents.dao.isogd.section.IBookDao;
import ru.sovzond.mgis2.documents.model.isogd.section.Book;
import ru.sovzond.mgis2.documents.model.isogd.section.Section;
import ru.sovzond.mgis2.documents.model.isogd.document.IsogdDocumentObject;

import java.util.List;

@Repository
public class BookDao extends CRUDDaoBase<Book> implements IBookDao {
	public Book findById(Long id) {
		return (Book) filter(Restrictions.eq("id", id)).uniqueResult();
	}

	public BookBaseBuilder createFilter(Section section, String orderBy, int first, int max) {
		return new BookBaseBuilder(section, orderBy, first, max);
	}

	public List<IsogdDocumentObject> listAvailableDocumentObjects(Section section) {
		//noinspection unchecked,JpaQlInspection
		return getSession()
				.createQuery("SELECT docObj FROM Section s JOIN s.documentClass docCls JOIN docCls.documentObjects docObj WHERE s = :sect ") //
				.setParameter("sect", section) //
				.list();
	}

	class BookBaseBuilder extends PagerBuilderCriteria<Book> {
		private Section section;

		private BookBaseBuilder(Section section, String orderBy, int first, int max) {
			super(orderBy, first, max);
			this.section = section;
		}

		@Override
		protected void applyFilter(Criteria criteria) {
			criteria.add(Restrictions.eq("section", section));
		}
	}
}
