package ru.sovzond.mgis2.taxes.model.common;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by Sergey Lvov on 23.03.16.
 */
@Entity
@Table(name = "mgis2_taxes_reporting_period")
public class ReportingPeriod {

	@Id
	@SequenceGenerator(name = "pk_sequence", sequenceName = "mgis2_taxes_reporting_period_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
	@Column
	private Long id;

	@NotNull
	@Column
	private Date startDate;

	@NotNull
	@Column
	private Date endDate;

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column
	private PaymentPeriodType paymentType;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public PaymentPeriodType getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(PaymentPeriodType paymentType) {
		this.paymentType = paymentType;
	}

	public ReportingPeriod clone() {
		ReportingPeriod period;
		try {
			period = (ReportingPeriod) super.clone();
		} catch (CloneNotSupportedException e) {
			return null;
		}
		return period;
	}
}
