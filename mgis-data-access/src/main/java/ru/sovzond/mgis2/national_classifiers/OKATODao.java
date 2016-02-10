package ru.sovzond.mgis2.national_classifiers;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import ru.sovzond.mgis2.dataaccess.base.impl.CRUDDaoBase;
import ru.sovzond.mgis2.dataaccess.base.impl.PagerBuilderBase;
import ru.sovzond.mgis2.dataaccess.base.impl.PagerBuilderCriteria;
import ru.sovzond.mgis2.registers.national_classifiers.OKATO;

import java.util.List;

/**
 * Created by Alexander Arakelyan on 29.07.15.
 * modify by y.donchenko
 */
@Repository
public class OKATODao extends CRUDDaoBase<OKATO> {
	public PagerBuilderBase<OKATO> createFilter(String code, String name, String orderBy, int first, int max) {
		return new OKATOPagerBuilder(code, name, orderBy, first, max);
	}

	public OKATO findByCode(String code) { //if more then one result return OKATO with control number 1
		List<OKATO> list = createCriteria().add(Restrictions.eq("code", code)).list();

		if (list.size() > 1) {
			return findMainOkatoFromList(list);
		} else if (list.size() == 1) {
			//return (OKATO) createCriteria().add(Restrictions.eq("code", code)).uniqueResult();
			return list.get(0);
		}
		return null;
	}


	private OKATO findMainOkatoFromList(List<OKATO> list) {
		for (OKATO okato : list) {
			if (okato.getControlNumber() == 1) return okato;
		}
		return null;
	}

	private class OKATOPagerBuilder extends PagerBuilderCriteria<OKATO> {
		private String code;
		private String name;

		public OKATOPagerBuilder(String code, String name, String orderBy, int first, int max) {
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
				criteria.add(Restrictions.like("name", "%" + name + "%"));
			}
		}
	}
}
