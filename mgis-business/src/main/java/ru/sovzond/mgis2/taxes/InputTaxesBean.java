package ru.sovzond.mgis2.taxes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.business.CRUDBeanBase;
import ru.sovzond.mgis2.dataaccess.base.IIdentifiableDao;
import ru.sovzond.mgis2.dataaccess.base.IPageableDAOBase;
import ru.sovzond.mgis2.dataaccess.base.PageableContainer;
import ru.sovzond.mgis2.dataaccess.base.impl.Pageable;
import ru.sovzond.mgis2.indicators.PriceIndicator;

import java.util.stream.Collectors;

/**
 * Created by donchenko-y on 3/22/16.
 */

@Service
public class InputTaxesBean extends CRUDBeanBase<InputTaxes> {

	@Autowired
	private InputTaxesDao dao;


	@Override
	protected IPageableDAOBase<InputTaxes> getPageableDao() {
		return null;
	}

	@Override
	protected IIdentifiableDao<InputTaxes> getIIdentifiableDao() {
		return null;
	}

	public PageableContainer<InputTaxes> list(String orderBy, int first, int max, String cadastralNumber) {
		Pageable<InputTaxes> pager = dao.pager(dao.createFilter(cadastralNumber, orderBy, first, max));
		return new PageableContainer<>(pager.list().stream().map(InputTaxes::clone).collect(Collectors.toList()), pager.count(), first, max);
	}

}
