package ru.sovzond.mgis2.taxes.model.land.privilege;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Map;

/**
 * Created by Sergey Lvov on 25.03.16.
 *
 * Land tax base privilege reduction type
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum LandTaxBasePrivilegeReductionType {
	ARTICLE391("LandTaxBasePrivilegeReductionType.Article391", 1L), // по статье 391 НК РФ на 10000 рублей
	DOCUMENT("LandTaxBasePrivilegeReductionType.Document", 2L); // на установленную местным нормативным актом сумму

	private Long id;
	private String name;
	private String translateTemplate;

	LandTaxBasePrivilegeReductionType(String translateTemplateVal, Long idVal) {
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
	public static LandTaxBasePrivilegeReductionType create(Map record) {
		if(record.containsKey("name")) return LandTaxBasePrivilegeReductionType.valueOf((String) record.get("name"));
		return null;
	}

}
