package ru.sovzond.mgis2.taxes.model.common;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Map;

/**
 * Created by Sergey Lvov on 23.03.16.
 *
 * Payment period type
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum PaymentPeriodType {
	YEAR("PaymentPeriodType.Year", 1L),
	HALF_YEAR("PaymentPeriodType.HalfYear", 2L),
	QUARTER("PaymentPeriodType.Quarter", 3L),
	MONTH("PaymentPeriodType.Month", 4L);

	private Long id;
	private String name;
	private String translateTemplate;

	PaymentPeriodType(String translateTemplateVal, Long idVal) {
		setTranslateTemplate(translateTemplateVal);
		setId(idVal);
		setName(name());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@SuppressWarnings("unused")
	public String getTranslateTemplate() {
		return translateTemplate;
	}

	public void setTranslateTemplate(String translateTemplate) {
		this.translateTemplate = translateTemplate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@JsonCreator
	public static PaymentPeriodType create(Map record) {
		if(record.containsKey("name")) return PaymentPeriodType.valueOf((String) record.get("name"));
		return null;
	}

}
