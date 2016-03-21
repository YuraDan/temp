package ru.sovzond.mgis2.property.dao.lands.impl;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import ru.sovzond.mgis2.dataaccess.base.impl.CRUDDaoBase;
import ru.sovzond.mgis2.property.dao.lands.ITerritorialZoneTypeDao;
import ru.sovzond.mgis2.registers.national_classifiers.TerritorialZoneType;

import java.util.List;

/**
 * Created by Alexander Arakelyan on 04.08.15.
 *
 */
@Repository
public class TerritorialZoneTypeDao extends CRUDDaoBase<TerritorialZoneType> implements ITerritorialZoneTypeDao {
	public List<TerritorialZoneType> findByNameSubstring(String nameSubstring) {
		//noinspection unchecked
		return createCriteria().add(Restrictions.ilike("name", nameSubstring)).list();
	}

	public TerritorialZoneType findByCode(String code) {
		return (TerritorialZoneType) createCriteria().add(Restrictions.eq("code", code)).uniqueResult();
	}
}
