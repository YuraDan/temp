package ru.sovzond.mgis2.taxes.services.common.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.business.CRUDBeanBase;
import ru.sovzond.mgis2.dataaccess.base.IIdentifiableDao;
import ru.sovzond.mgis2.dataaccess.base.IPageableDAOBase;
import ru.sovzond.mgis2.dataaccess.base.PageableContainer;
import ru.sovzond.mgis2.dataaccess.base.impl.Pageable;
import ru.sovzond.mgis2.taxes.dao.common.IInputTaxesDao;
import ru.sovzond.mgis2.taxes.model.common.InputTaxes;
import ru.sovzond.mgis2.taxes.services.common.IInputTaxesService;

import java.util.stream.Collectors;

/**
 * Created by donchenko-y on 3/22/16.
 *
 */

@Service
public class InputTaxesService extends CRUDBeanBase<InputTaxes> implements IInputTaxesService {

	@Autowired
	private IInputTaxesDao dao;

	@Override
	protected IPageableDAOBase<InputTaxes> getPageableDao() {
		return null;
	}

	@Override
	protected IIdentifiableDao<InputTaxes> getIIdentifiableDao() {
		return null;
	}

	@Override
	public PageableContainer<InputTaxes> list(String orderBy, int first, int max, String cadastralNumber) {
		Pageable<InputTaxes> pager = dao.pager(dao.createFilter(cadastralNumber, orderBy, first, max));
		return new PageableContainer<>(pager.list().stream().map(InputTaxes::clone).collect(Collectors.toList()), pager.count(), first, max);
	}

}
