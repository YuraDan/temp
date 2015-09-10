package ru.sovzond.mgis2.national_classifiers;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import ru.sovzond.mgis2.dataaccess.base.impl.CRUDDaoBase;
import ru.sovzond.mgis2.dataaccess.base.impl.PagerBuilderBase;
import ru.sovzond.mgis2.dataaccess.base.impl.PagerBuilderCriteria;
import ru.sovzond.mgis2.registers.national_classifiers.OKATO;

/**
 * Created by Alexander Arakelyan on 29.07.15.
 */
@Repository
public class OKATODao extends CRUDDaoBase<OKATO> {
	public PagerBuilderBase<OKATO> createFilter(String name, String orderBy, int first, int max) {
		return new OKATOPagerBuilder(name, orderBy, first, max);
	}

	private class OKATOPagerBuilder extends PagerBuilderCriteria<OKATO> {
		private String name;

		public OKATOPagerBuilder(String name, String orderBy, int first, int max) {
			super(orderBy, first, max);
			this.name = name;
		}

		@Override
		protected void applyFilter(Criteria criteria) {
			if (name != null && !"".equals(name)) {
				criteria.add(Restrictions.like("name", "%" + name + "%"));
			}
		}
	}
}
