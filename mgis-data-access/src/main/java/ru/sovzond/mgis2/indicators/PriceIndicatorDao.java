package ru.sovzond.mgis2.indicators;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import ru.sovzond.mgis2.indicators.PriceIndicator;
import ru.sovzond.mgis2.dataaccess.base.impl.CRUDDaoBase;

/**
 * Created by Alexander Arakelyan on 07/11/15.
 */
@Repository
public class PriceIndicatorDao extends CRUDDaoBase<PriceIndicator> {
	public PriceIndicator findByName(String name) {
		return (PriceIndicator) createCriteria().add(Restrictions.eq("name", name)).uniqueResult();
	}
}
