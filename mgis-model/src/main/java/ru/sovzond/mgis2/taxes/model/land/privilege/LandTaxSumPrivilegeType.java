package ru.sovzond.mgis2.taxes.model.land.privilege;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Map;

/**
 * Created by Sergey Lvov on 25.03.16.
 *
 * Land tax sum privilege type
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum LandTaxSumPrivilegeType {
	NONE("LandTaxSumPrivilegeType.None", 1L), // не применяется
	PERCENT("LandTaxSumPrivilegeType.Percent", 2L), // в процентах
	SUM("LandTaxSumPrivilegeType.Sum", 3L); // в сумме

	private Long id;
	private String name;
	private String translateTemplate;

	LandTaxSumPrivilegeType(String translateTemplateVal, Long idVal) {
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
	public static LandTaxSumPrivilegeType create(Map record) {
		if(record.containsKey("name")) return LandTaxSumPrivilegeType.valueOf((String) record.get("name"));
		return null;
	}

}
