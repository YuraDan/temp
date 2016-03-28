package ru.sovzond.mgis2.taxes.model.land.privilege;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Map;

/**
 * Created by Sergey Lvov on 25.03.16.
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum LandTaxBasePrivilegeType {
	NONE("LandTaxBasePrivilegeType.None", 1L), // не применяется
	DOCUMENT("LandTaxBasePrivilegeType.Document", 2L), // освобождение от налогообложения в соответствии с местным нормативным актом
	ARTICLE395("LandTaxBasePrivilegeType.Article395", 3L), // освобождение от налогообложения по статье 395 НК РФ
	REDUCTION("LandTaxBasePrivilegeType.Reduction", 4L); // уменьшение налоговой базы

	private Long id;
	private String name;
	private String translateTemplate;

	LandTaxBasePrivilegeType(String translateTemplateVal, Long idVal) {
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
	public static LandTaxBasePrivilegeType create(Map record) {
		if(record.containsKey("name")) return LandTaxBasePrivilegeType.valueOf((String) record.get("name"));
		return null;
	}

}
