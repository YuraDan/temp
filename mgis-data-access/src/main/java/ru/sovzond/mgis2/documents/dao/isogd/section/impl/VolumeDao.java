package ru.sovzond.mgis2.documents.dao.isogd.section.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import ru.sovzond.mgis2.dataaccess.base.impl.CRUDDaoBase;
import ru.sovzond.mgis2.dataaccess.base.impl.PagerBuilderCriteria;
import ru.sovzond.mgis2.documents.dao.isogd.section.IVolumeDao;
import ru.sovzond.mgis2.documents.model.isogd.section.Book;
import ru.sovzond.mgis2.documents.model.isogd.section.Volume;

@Repository
public class VolumeDao extends CRUDDaoBase<Volume> implements IVolumeDao {
	private static final String ID2 = "id";
	private static final String SECTION = "book";

	public Volume findById(Long id) {
		return (Volume) filter(Restrictions.eq(ID2, id)).uniqueResult();
	}

	public VolumeBaseBuilder createFilter(Book book, String sortOrder, int first, int max) {
		return new VolumeBaseBuilder(book, sortOrder, first, max);
	}

	class VolumeBaseBuilder extends PagerBuilderCriteria<Volume> {
		private Book book;

		private VolumeBaseBuilder(Book book, String sortOrder, int first, int max) {
			super(sortOrder, first, max);
			this.book = book;
		}

		@Override
		protected void applyFilter(Criteria criteria) {
			criteria.add(Restrictions.eq(SECTION, book));
		}
	}
}
