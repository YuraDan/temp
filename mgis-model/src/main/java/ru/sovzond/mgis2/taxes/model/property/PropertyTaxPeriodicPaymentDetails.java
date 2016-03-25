package ru.sovzond.mgis2.taxes.model.property;

import ru.sovzond.mgis2.taxes.model.common.TaxPeriodicPaymentDetails;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Sergey Lvov on 23.03.16.
 * Property tax periodic payment details
 */
@Entity
@Table(name = "mgis2_taxes_property_periodic_payment_details")
public class PropertyTaxPeriodicPaymentDetails extends TaxPeriodicPaymentDetails {

}
