package ru.sovzond.mgis2.lands;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import ru.sovzond.mgis2.dataaccess.base.impl.CRUDDaoBase;
import ru.sovzond.mgis2.dataaccess.base.impl.PageableBase;
import ru.sovzond.mgis2.registers.lands.TerritorialZone;

/**
 * Created by Alexander Arakelyan on 27.07.15.
 */
@Repository
public class TerritorialZoneDao extends CRUDDaoBase<TerritorialZone> {
	public PageableBase<TerritorialZone> createFilter(String name, String orderBy, int first, int max) {
		return new TerritorialZoneFilter(name, orderBy, first, max);
	}

	private class TerritorialZoneFilter extends PageableBase<TerritorialZone> {
		private String name;

		private TerritorialZoneFilter(String name, String orderBy, int first, int max) {
			super(orderBy, first, max);
			this.name = name;
		}


		@Override
		protected void applyFilter(Criteria criteria) {
			if (name != null && name.length() > 0) {
				criteria.add(Restrictions.like("name", "%" + name + "%"));
			}
		}
	}
}