package ru.sovzond.mgis2.national_classifiers;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import ru.sovzond.mgis2.dataaccess.base.impl.CRUDDaoBase;
import ru.sovzond.mgis2.registers.national_classifiers.ObjectsPurpose;

/**
 * Created by donchenko-y on 2/4/16.
 */
@Repository
public class ObjectsPurposeDao extends CRUDDaoBase<ObjectsPurpose> {

	public ObjectsPurpose findByCode(String code) {
		return (ObjectsPurpose) createCriteria().add(Restrictions.eq("code", code)).uniqueResult();
	}

}
