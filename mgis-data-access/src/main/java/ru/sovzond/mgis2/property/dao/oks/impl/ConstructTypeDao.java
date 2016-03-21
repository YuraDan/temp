package ru.sovzond.mgis2.property.dao.oks.impl;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import ru.sovzond.mgis2.dataaccess.base.impl.CRUDDaoBase;
import ru.sovzond.mgis2.property.dao.oks.IConstructTypeDao;
import ru.sovzond.mgis2.property.model.oks.ConstructionType;

/**
 * Created by Alexander Arakelyan on 12/11/15.
 *
 */
@Repository
public class ConstructTypeDao extends CRUDDaoBase<ConstructionType> implements IConstructTypeDao {
	public ConstructionType findByCode(String code) {
		return (ConstructionType) createCriteria().add(Restrictions.eq("code", code)).uniqueResult();
	}
}
