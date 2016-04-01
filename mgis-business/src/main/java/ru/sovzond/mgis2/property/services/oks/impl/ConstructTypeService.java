package ru.sovzond.mgis2.property.services.oks.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.business.CRUDBeanBase;
import ru.sovzond.mgis2.dataaccess.base.IIdentifiableDao;
import ru.sovzond.mgis2.dataaccess.base.IPageableDAOBase;
import ru.sovzond.mgis2.dataaccess.base.PageableContainer;
import ru.sovzond.mgis2.property.dao.oks.IConstructTypeDao;
import ru.sovzond.mgis2.property.model.oks.ConstructionType;
import ru.sovzond.mgis2.property.services.oks.IConstructTypeService;

import java.util.stream.Collectors;

/**
 * Created by Alexander Arakelyan on 12/11/15.
 *
 */
@Service
public class ConstructTypeService
		extends CRUDBeanBase<ConstructionType>
		implements IConstructTypeService {

	@Autowired
	private IConstructTypeDao dao;

	@Override
	protected IPageableDAOBase<ConstructionType> getPageableDao() {
		return dao;
	}

	@Override
	protected IIdentifiableDao<ConstructionType> getIIdentifiableDao() {
		return dao;
	}

	@Override
	public PageableContainer<ConstructionType> list(String orderBy, int first, int max) {
		PageableContainer<ConstructionType> pager = super.list(orderBy, first, max);
		return new PageableContainer<>(pager.getList().stream().map(ConstructionType::clone).collect(Collectors.toList()), pager.getTotalNumberOfItems(), pager.getCurrentPosition(), pager.getItemsPerPage());
	}

	public ConstructionType findByCode(String code) {
		return dao.findByCode(code);
	}
}
