package ru.sovzond.mgis2.taxes.model.property;

import ru.sovzond.mgis2.taxes.model.common.TaxPeriodicPayerPaymentDetails;

import javax.persistence.*;

/**
 * Created by Sergey Lvov on 25.03.16.
 *
 * Property tax periodic payments by payer details
 */
@Entity
@Table(name = "mgis2_taxes_property_periodic_payer_payment_details")
public class PropertyTaxPeriodicPayerPaymentDetails extends TaxPeriodicPayerPaymentDetails implements Cloneable {

	@Id
	@SequenceGenerator(name = "pk_sequence", sequenceName = "mgis2_taxes_property_periodic_payer_payment_details_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
	@Column
	private Long id;

	@Column
	private double taxPrivilege;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getTaxPrivilege() {
		return taxPrivilege;
	}

	public void setTaxPrivilege(double taxPrivilege) {
		this.taxPrivilege = taxPrivilege;
	}

	public PropertyTaxPeriodicPayerPaymentDetails clone() {
		return (PropertyTaxPeriodicPayerPaymentDetails) super.clone();
	}

}
