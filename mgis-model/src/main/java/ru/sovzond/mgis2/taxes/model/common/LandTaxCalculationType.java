package ru.sovzond.mgis2.taxes.model.common;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Map;

/**
 * Created by Sergey Lvov on 23.03.16.
 *
 * Tax calculation type
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum LandTaxCalculationType {
	CADASTRAL_VALUE("LandTaxCalculationType.CadastralValue", 1L),
	INVENTORY_VALUE("LandTaxCalculationType.InventoryValue", 2L),
	BASE_RATE("LandTaxCalculationType.BaseRate", 3L);

	private Long id;
	private String name;
	private String translateTemplate;

	LandTaxCalculationType(String translateTemplateVal, Long idVal) {
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
	public static LandTaxCalculationType create(Map record) {
		if(record.containsKey("name")) return LandTaxCalculationType.valueOf((String) record.get("name"));
		return null;
	}

}
