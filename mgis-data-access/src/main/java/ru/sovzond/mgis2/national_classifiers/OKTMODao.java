package ru.sovzond.mgis2.national_classifiers;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import ru.sovzond.mgis2.dataaccess.base.impl.CRUDDaoBase;
import ru.sovzond.mgis2.dataaccess.base.impl.PagerBuilderCriteria;
import ru.sovzond.mgis2.registers.national_classifiers.OKTMO;

import java.util.List;

@Repository
public class OKTMODao extends CRUDDaoBase<OKTMO> {
	public PagerBuilderCriteria<OKTMO> createFilter(String code, String name, String orderBy, int first, int max) {
		return new OKTMOFilter(code, name, orderBy, first, max);
	}

//	public OKTMO findByCode(String code) {
//		return (OKTMO) getSession().createCriteria(persistentClass).add(Restrictions.eq("code", code)).uniqueResult();
//	}

	public OKTMO findByCode(String code) { //if more then one result return OKTMO with control number 1
		List<OKTMO> list = createCriteria().add(Restrictions.eq("code", code)).list();
		if (list.size() > 1) {
			return findMainOktmoFromList(list);
		} else if ((list.size() == 1)) {
			return list.get(0);
		}
		return null;
	}

	private OKTMO findMainOktmoFromList(List<OKTMO> list) {
		for (OKTMO oktmo : list) {
			if (oktmo.getControlNumber() == 1) return oktmo;
		}
		return null;
	}

	private class OKTMOFilter extends PagerBuilderCriteria<OKTMO> {

		private String code;
		private String name;

		public OKTMOFilter(String code, String name, String orderBy, int first, int max) {
			super(orderBy, first, max);
			this.code = code;
			this.name = name;
		}


		@Override
		protected void applyFilter(Criteria criteria) {
			if (code != null && code.length() > 0) {
				criteria.add(Restrictions.like("code", "%" + code + "%"));
			}
			if (name != null && name.length() > 0) {
				criteria.add(Restrictions.ilike("name", "%" + name + "%"));
			}
		}
	}
}
