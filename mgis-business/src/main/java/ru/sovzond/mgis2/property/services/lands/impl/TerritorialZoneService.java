package ru.sovzond.mgis2.property.services.lands.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.business.CRUDBeanBase;
import ru.sovzond.mgis2.dataaccess.base.IIdentifiableDao;
import ru.sovzond.mgis2.dataaccess.base.IPageableDAOBase;
import ru.sovzond.mgis2.dataaccess.base.PageableContainer;
import ru.sovzond.mgis2.dataaccess.base.impl.Pageable;
import ru.sovzond.mgis2.property.dao.lands.ITerritorialZoneDao;
import ru.sovzond.mgis2.property.model.lands.TerritorialZone;
import ru.sovzond.mgis2.property.services.lands.ITerritorialZoneService;
import ru.sovzond.mgis2.registers.national_classifiers.TerritorialZoneType;

import java.util.List;

/**
 * Created by Alexander Arakelyan on 27.07.15.
 *
 */
@Service
public class TerritorialZoneService
		extends CRUDBeanBase<TerritorialZone>
		implements ITerritorialZoneService {

	@Autowired
	private ITerritorialZoneDao dao;

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
