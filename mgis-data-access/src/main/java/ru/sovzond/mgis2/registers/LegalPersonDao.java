package ru.sovzond.mgis2.registers;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import ru.sovzond.mgis2.dataaccess.base.impl.CRUDDaoBase;
import ru.sovzond.mgis2.dataaccess.base.impl.PagerBuilderCriteria;
import ru.sovzond.mgis2.registers.persons.LegalPerson;

/**
 * Created by Alexander Arakelyan on 11.08.15.
 */
@Repository
public class LegalPersonDao extends CRUDDaoBase<LegalPerson> {

	private class LegalPersonFilter extends PagerBuilderCriteria<LegalPerson> {
		private String name;

		public LegalPersonFilter(String name, String orderBy, int first, int max) {
			super(orderBy, first, max);
			this.name = name;
		}

		@Override
		protected void applyFilter(Criteria criteria) {
			if (name != null && name.length() > 0) {
				criteria.add(Restrictions.ilike("name", "%" + name + "%"));
			}
		}
	}

	public PagerBuilderCriteria<LegalPerson> newPagerBuilder(String name, String orderBy, int first, int max) {
		return new LegalPersonFilter(name, orderBy, first, max);
	}
}
