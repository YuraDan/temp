package ru.sovzond.mgis2.taxes.dao.land.privilege.impl;

import org.springframework.stereotype.Repository;
import ru.sovzond.mgis2.dataaccess.base.impl.CRUDDaoBase;
import ru.sovzond.mgis2.taxes.dao.land.privilege.ILandTaxBasePrivilegeBasisDao;
import ru.sovzond.mgis2.taxes.model.land.privilege.LandTaxBasePrivilegeBasis;

/**
 * Created by Sergey Lvov on 28.03.16.
 */
@Repository
public class LandTaxBasePrivilegeBasisDao
		extends CRUDDaoBase<LandTaxBasePrivilegeBasis>
		implements ILandTaxBasePrivilegeBasisDao {
}
