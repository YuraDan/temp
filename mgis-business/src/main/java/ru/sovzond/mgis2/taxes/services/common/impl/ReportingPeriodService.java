package ru.sovzond.mgis2.taxes.services.common.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sovzond.mgis2.business.CRUDBeanBase;
import ru.sovzond.mgis2.dataaccess.base.IIdentifiableDao;
import ru.sovzond.mgis2.dataaccess.base.IPageableDAOBase;
import ru.sovzond.mgis2.taxes.dao.common.IReportingPeriodDao;
import ru.sovzond.mgis2.taxes.model.common.ReportingPeriod;
import ru.sovzond.mgis2.taxes.services.common.IReportingPeriodService;

/**
 * Created by Sergey Lvov on 28.03.16.
 */
@Service
public class ReportingPeriodService extends CRUDBeanBase<ReportingPeriod> implements IReportingPeriodService {

	@Autowired
	private IReportingPeriodDao dao;

	@Override
	protected IPageableDAOBase<ReportingPeriod> getPageableDao() {
		return null;
	}

	@Override
	protected IIdentifiableDao<ReportingPeriod> getIIdentifiableDao() {
		return null;
	}

}
