package ru.sovzond.mgis2.lands;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import ru.sovzond.mgis2.dataaccess.base.impl.CRUDDaoBase;
import ru.sovzond.mgis2.lands.includes.LandIncludedObjects;

import java.util.List;

@Repository
public class LandIncludedObjectsDao extends CRUDDaoBase<LandIncludedObjects> {

	public List<LandIncludedObjects> findByNestedObject(Long landId, Long capitalConstructId) {
		Session session = getSession();
		Criteria criteria = session.createCriteria(persistentClass);
		if (landId != null && landId != 0) {
			criteria = criteria.createCriteria("includedLands").add(Restrictions.eq("id", landId));
		}
		if (capitalConstructId != null && capitalConstructId != 0) {
			criteria = criteria.createCriteria("includedCapitalConstructions").add(Restrictions.eq("id", capitalConstructId));
		}
		return criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY).list();
	}

}
