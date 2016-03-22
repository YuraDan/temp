package ru.sovzond.mgis2.taxes;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import ru.sovzond.mgis2.dataaccess.base.impl.CRUDDaoBase;
import ru.sovzond.mgis2.dataaccess.base.impl.PagerBuilderCriteria;
import ru.sovzond.mgis2.dataaccess.base.impl.PagerBuilderQuery;

/**
 * Created by donchenko-y on 3/22/16.
 */

@Repository
public class InputTaxesDao extends CRUDDaoBase<InputTaxes> {

	public InputTaxesFilter createFilter(String cadastralNumber, String orderBy, int first, int max) {
		return new InputTaxesFilter(cadastralNumber, orderBy, first, max);
	}

	class InputTaxesFilter extends PagerBuilderCriteria<InputTaxes> {
		private String cadastralNumber;

		InputTaxesFilter(String cadastralNumber, String orderBy, int first, int max) {
			super(orderBy, first, max);
			this.cadastralNumber = cadastralNumber;
		}

		@Override
		protected void applyFilter(Criteria criteria) {
			if (cadastralNumber != null && cadastralNumber.length() > 0) {
				criteria.add(Restrictions.ilike("cadastralNumber", "%" + cadastralNumber + "%"));
			}
		}
	}

}
