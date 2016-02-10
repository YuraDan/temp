package ru.sovzond.mgis2.indicators;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import ru.sovzond.mgis2.indicators.TechnicalIndicator;
import ru.sovzond.mgis2.dataaccess.base.impl.CRUDDaoBase;

/**
 * Created by Alexander Arakelyan on 07/11/15.
 */
@Repository
public class TechnicalIndicatorDao extends CRUDDaoBase<TechnicalIndicator> {
	public TechnicalIndicator findByName(String name) {
		return (TechnicalIndicator) createCriteria().add(Restrictions.eq("name", name)).uniqueResult();
	}
}
