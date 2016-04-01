package ru.sovzond.mgis2.taxes.dao.property.impl;

import org.springframework.stereotype.Repository;
import ru.sovzond.mgis2.dataaccess.base.impl.CRUDDaoBase;
import ru.sovzond.mgis2.taxes.dao.property.IPropertyTaxPeriodicPaymentDetailsDao;
import ru.sovzond.mgis2.taxes.model.property.PropertyTaxPeriodicPaymentDetails;

/**
 * Created by Sergey Lvov on 28.03.16.
 */
@Repository
public class PropertyTaxPeriodicPaymentDetailsDao
		extends CRUDDaoBase<PropertyTaxPeriodicPaymentDetails>
		implements IPropertyTaxPeriodicPaymentDetailsDao {
}
