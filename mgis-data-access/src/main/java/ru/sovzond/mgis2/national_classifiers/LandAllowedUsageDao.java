package ru.sovzond.mgis2.national_classifiers;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import ru.sovzond.mgis2.dataaccess.base.impl.CRUDDaoBase;
import ru.sovzond.mgis2.registers.national_classifiers.LandAllowedUsage;

@Repository
public class LandAllowedUsageDao extends CRUDDaoBase<LandAllowedUsage> {

	public LandAllowedUsage findByCode(String code) {
		return (LandAllowedUsage) createCriteria().add(Restrictions.eq("classificationCode", code)).uniqueResult();
	}

}
