package ru.sovzond.mgis2.lands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.business.CRUDBeanBase;
import ru.sovzond.mgis2.dataaccess.base.IIdentifiableDao;
import ru.sovzond.mgis2.dataaccess.base.IPageableDAOBase;
import ru.sovzond.mgis2.dataaccess.base.PageableContainer;
import ru.sovzond.mgis2.dataaccess.base.impl.Pageable;
import ru.sovzond.mgis2.property.model.lands.TerritorialZone;
import ru.sovzond.mgis2.registers.national_classifiers.TerritorialZoneType;

import java.util.List;

/**
 * Created by Alexander Arakelyan on 27.07.15.
 */
@Service
public class TerritorialZoneBean extends CRUDBeanBase<TerritorialZone> {

	@Autowired
	private TerritorialZoneDao dao;

	@Override
	protected IPageableDAOBase<TerritorialZone> getPageableDao() {
		return dao;
	}

	@Override
	protected IIdentifiableDao<TerritorialZone> getIIdentifiableDao() {
		return dao;
	}

	public PageableContainer<TerritorialZone> list(String orderBy, int first, int max, String name) {
		Pageable<TerritorialZone> pager = dao.pager(dao.createFilter(name, orderBy, first, max));
		return new PageableContainer<>(pager.list(), pager.count(), first, max);
	}

	public List<TerritorialZone> findByCadastralNumberAndZoneType(String cadastralNumber, TerritorialZoneType zoneType) {
		return dao.findByCadastralNumberAndZoneType(cadastralNumber, zoneType);
	}
}
