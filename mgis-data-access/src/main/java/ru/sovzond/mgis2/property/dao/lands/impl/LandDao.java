package ru.sovzond.mgis2.property.dao.lands.impl;

import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import ru.sovzond.mgis2.dataaccess.base.impl.CRUDDaoBase;
import ru.sovzond.mgis2.dataaccess.base.impl.PagerBuilderQuery;
import ru.sovzond.mgis2.property.dao.lands.ILandDao;
import ru.sovzond.mgis2.property.model.IncludedObjects;
import ru.sovzond.mgis2.property.model.lands.Land;

import java.util.List;

@Repository
public class LandDao extends CRUDDaoBase<Land> implements ILandDao {
	public LandsFilter createFilter(String cadastralNumber, Long[] ids, String orderBy, int first, int max) {
		return new LandsFilter(cadastralNumber, ids, orderBy, first, max);
	}

	public List<Land> find(String cadastralNumber) {
		//noinspection unchecked
		return getSession().createCriteria(persistentClass).add(Restrictions.eq("cadastralNumber", cadastralNumber)).list();
	}

	public List<Land> getByIncludedObjects(List<IncludedObjects> landIncludedObjects) {
		//noinspection unchecked
		return getSession().createCriteria(persistentClass).add(Restrictions.in("includedObjects", landIncludedObjects)).list();
	}

	class LandsFilter extends PagerBuilderQuery<Land> {
		private String cadastralNumber;
		private Long[] ids;

		LandsFilter(String cadastralNumber, Long[] ids, String orderBy, int first, int max) {
			super(orderBy, first, max);
			this.cadastralNumber = cadastralNumber;
			this.ids = ids;
		}

		@Override
		protected void applyFilter(StringBuilder queryBuilder) {
			if (cadastralNumber != null && cadastralNumber.length() > 0) {
				addFilter(queryBuilder, "cadastralNumber LIKE :cadastralNumber");
			}
		}

		@Override
		protected void applyOrder(StringBuilder queryBuilder) {
			if (ids != null && ids.length > 0) {
				StringBuilder sb = new StringBuilder();
				sb.append(ids[0]);
				for (int i = 1; i < ids.length; i++) {
					sb.append(",").append(ids[i]);
				}
				queryBuilder.append(" ORDER BY CASE WHEN id IN (").append(sb.toString()).append(") THEN 0 ELSE 1 END ");
			} else {
				super.applyOrder(queryBuilder);
			}
		}

		@Override
		protected void applyParameters(Query query) {
			if (cadastralNumber != null && cadastralNumber.length() > 0) {
				query.setParameter("cadastralNumber", "%" + cadastralNumber + "%");
			}
		}
	}
}
