package ru.sovzond.mgis2.national_classifiers;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import ru.sovzond.mgis2.dataaccess.base.impl.CRUDDaoBase;
import ru.sovzond.mgis2.dataaccess.base.impl.PagerBuilderCriteria;
import ru.sovzond.mgis2.registers.national_classifiers.OKTMO;
import ru.sovzond.mgis2.registers.national_classifiers.OkatoToOktmo;

import java.util.List;


/**
 * Created by donchenko-y on 1/25/16.
 */

@Repository
public class OkatoToOktmoDao extends CRUDDaoBase<OkatoToOktmo> {

	public PagerBuilderCriteria<OkatoToOktmo> createFilter(String name, String orderBy, int first, int max) {
		return new OkatoToOktmoFilter(name, orderBy, first, max);
	}

	public List<OkatoToOktmo> findByOktmoList(String oktmo) {
		return getSession().createCriteria(persistentClass).add(Restrictions.eq("oktmo", oktmo)).list();
	}

	public List<OkatoToOktmo> findByOkatoList(String okato) {
		return getSession().createCriteria(persistentClass).add(Restrictions.eq("okato", okato)).list();
	}

	private class OkatoToOktmoFilter extends PagerBuilderCriteria<OkatoToOktmo> {

		private String name;

		public OkatoToOktmoFilter(String name, String orderBy, int first, int max) {
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
}
