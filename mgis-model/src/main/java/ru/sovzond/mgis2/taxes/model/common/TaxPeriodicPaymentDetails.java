package ru.sovzond.mgis2.taxes.model.common;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by Sergey Lvov on 23.03.16.
 *
 * Tax periodic payment details
 */
@MappedSuperclass
public class TaxPeriodicPaymentDetails implements Cloneable {

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	private ReportingPeriod reportingPeriod;

	@Column
	private double taxSum;

	@Enumerated(EnumType.STRING)
	@Column
	private TaxCalculationType taxCalculationType;

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

	public Object clone() {
		TaxPeriodicPaymentDetails details;
		try {
			details = (TaxPeriodicPaymentDetails) super.clone();
		} catch (CloneNotSupportedException e) {
			return null;
		}
		details.setReportingPeriod(getReportingPeriod() != null ? getReportingPeriod().clone() : null);
		return details;
	}
}
