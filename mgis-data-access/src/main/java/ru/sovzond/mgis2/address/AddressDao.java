package ru.sovzond.mgis2.address;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import ru.sovzond.mgis2.dataaccess.base.impl.CRUDDaoBase;
import ru.sovzond.mgis2.dataaccess.base.impl.PagerBuilderBase;
import ru.sovzond.mgis2.dataaccess.base.impl.PagerBuilderCriteria;
import ru.sovzond.mgis2.kladr.KLADRLocality;
import ru.sovzond.mgis2.kladr.KLADRStreet;

import java.util.List;

@Repository
public class AddressDao extends CRUDDaoBase<Address> {

	public PagerBuilderBase<Address> createFilter(String name, String okato, String orderBy, int first, int max) {
		return new AddressPagerBuilder(name, okato, orderBy, first, max);
	}

	public List<Address> find(KLADRLocality subject, KLADRLocality region, KLADRLocality locality, KLADRStreet street, String home, String housing, String building, String apartment) {
		Criteria criteria = getSession().createCriteria(persistentClass);
		criteria.add(subject != null ? Restrictions.eq("subject", subject) : Restrictions.isNull("subject"));
		criteria.add(region != null ? Restrictions.eq("region", region) : Restrictions.isNull("region"));
		criteria.add(locality != null ? Restrictions.eq("locality", locality) : Restrictions.isNull("locality"));
		criteria.add(street != null ? Restrictions.eq("street", street) : Restrictions.isNull("street"));
		criteria.add(home != null ? Restrictions.eq("home", home) : Restrictions.isNull("home"));
		criteria.add(housing != null ? Restrictions.eq("housing", housing) : Restrictions.isNull("housing"));
		criteria.add(building != null ? Restrictions.eq("building", building) : Restrictions.isNull("building"));
		criteria.add(apartment != null ? Restrictions.eq("apartment", apartment) : Restrictions.isNull("apartment"));
		return criteria.list();
	}

	private class AddressPagerBuilder extends PagerBuilderCriteria<Address> {

		private String name;
		private String oktmo;

		public AddressPagerBuilder(String name, String oktmo, String orderBy, int first, int max) {
			super(orderBy, first, max);
			this.name = name;
			this.oktmo = oktmo;
		}

		@Override
		protected void applyFilter(Criteria criteria) {
			if (name != null && name.length() > 0) {
				criteria.createAlias("subject", "subject");
				criteria.createAlias("region", "region");
				criteria.createAlias("locality", "locality");
				criteria.createAlias("street", "street");
				criteria.add(Restrictions.or( //
						Restrictions.ilike("subject.name", "%" + name + "%"), //
						Restrictions.ilike("region.name", "%" + name + "%"),//
						Restrictions.ilike("locality.name", "%" + name + "%"), //
						Restrictions.ilike("street.name", "%" + name + "%"), //
						Restrictions.ilike("home", name + "%") //
				));
			}
			if (oktmo != null && oktmo.length() > 0) {
				criteria.createAlias("oktmo", "oktmo");
				criteria.add(Restrictions.ilike("oktmo.code", "%" + oktmo + "%"));
			}
		}
	}
}
