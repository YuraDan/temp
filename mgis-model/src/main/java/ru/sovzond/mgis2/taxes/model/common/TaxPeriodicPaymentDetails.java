package ru.sovzond.mgis2.taxes.model.common;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Sergey Lvov on 23.03.16.
 *
 * Tax periodic payment details
 */
@MappedSuperclass
public class TaxPeriodicPaymentDetails {

	@Id
	@SequenceGenerator(name = "pk_sequence", sequenceName = "tax_periodic_payment_details_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
	@Column
	private Long id;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	private ReportingPeriod reportingPeriod;

	@Column
	private double taxSum;

	@Enumerated(EnumType.STRING)
	@Column
	private TaxCalculationType taxCalculationType;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<TaxPeriodicPayerPaymentDetails> payerPaymentDetails;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ReportingPeriod getReportingPeriod() {
		return reportingPeriod;
	}

	public void setReportingPeriod(ReportingPeriod reportingPeriod) {
		this.reportingPeriod = reportingPeriod;
	}

	public double getTaxSum() {
		return taxSum;
	}

	public void setTaxSum(double taxSum) {
		this.taxSum = taxSum;
	}

	public TaxCalculationType getTaxCalculationType() {
		return taxCalculationType;
	}

	public void setTaxCalculationType(TaxCalculationType taxCalculationType) {
		this.taxCalculationType = taxCalculationType;
	}

	public List<TaxPeriodicPayerPaymentDetails> getPayerPaymentDetails() {
		return payerPaymentDetails;
	}

	public void setPayerPaymentDetails(List<TaxPeriodicPayerPaymentDetails> payerPaymentDetails) {
		this.payerPaymentDetails = payerPaymentDetails;
	}

	public Object clone() {
		TaxPeriodicPaymentDetails details;
		try {
			details = (TaxPeriodicPaymentDetails) super.clone();
		} catch (CloneNotSupportedException e) {
			return null;
		}
		details.setId(getId());
		details.setReportingPeriod(getReportingPeriod() != null ? getReportingPeriod().clone() : null);
		details.setTaxSum(getTaxSum());
		details.setTaxCalculationType(getTaxCalculationType());
		details.setPayerPaymentDetails(getPayerPaymentDetails().stream().map(TaxPeriodicPayerPaymentDetails::clone).collect(Collectors.toList()));
		return details;
	}
}
