package ru.sovzond.mgis2.taxes.model.common;

import ru.sovzond.mgis2.persons.model.Person;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by Sergey Lvov on 23.03.16.
 *
 * Details about payment by tax payer
 */
@Entity
@Table(name = "mgis2_taxes_periodic_payer_payment_details")
public class TaxPeriodicPayerPaymentDetails {

	@Id
	@SequenceGenerator(name = "pk_sequence", sequenceName = "mgis2_taxes_periodic_payer_payment_details_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
	@Column
	private Long id;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	private Person taxPayer;

	@Column
	private double multiplyingFactor;

	@Column
	private double reductionFactor;

	@Column
	private String comment;

	@Column
	private double taxSum;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getTaxSum() {
		return taxSum;
	}

	public void setTaxSum(double taxSum) {
		this.taxSum = taxSum;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public double getReductionFactor() {
		return reductionFactor;
	}

	public void setReductionFactor(double reductionFactor) {
		this.reductionFactor = reductionFactor;
	}

	public double getMultiplyingFactor() {
		return multiplyingFactor;
	}

	public void setMultiplyingFactor(double multiplyingFactor) {
		this.multiplyingFactor = multiplyingFactor;
	}

	public Person getTaxPayer() {
		return taxPayer;
	}

	public void setTaxPayer(Person taxPayer) {
		this.taxPayer = taxPayer;
	}

	public TaxPeriodicPayerPaymentDetails clone() {
		TaxPeriodicPayerPaymentDetails details;
		try {
			details = (TaxPeriodicPayerPaymentDetails) super.clone();
		} catch (CloneNotSupportedException e) {
			return null;
		}
		details.setTaxPayer(getTaxPayer() != null ? getTaxPayer().clone() : null);
		return details;
	}
}
