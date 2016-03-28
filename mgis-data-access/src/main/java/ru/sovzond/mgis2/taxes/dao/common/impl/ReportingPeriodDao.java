package ru.sovzond.mgis2.taxes.dao.common.impl;

import org.springframework.stereotype.Repository;
import ru.sovzond.mgis2.dataaccess.base.impl.CRUDDaoBase;
import ru.sovzond.mgis2.taxes.dao.common.IReportingPeriodDao;
import ru.sovzond.mgis2.taxes.model.common.ReportingPeriod;

/**
 * Created by Sergey Lvov on 28.03.16.
 */
@Repository
public class ReportingPeriodDao extends CRUDDaoBase<ReportingPeriod> implements IReportingPeriodDao {
}
