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
public enum TaxCalculationType {
	CADASTRAL_VALUE("TaxCalculationType.CadastralValue", 1L),
	INVENTORY_VALUE("TaxCalculationType.InventoryValue", 2L),
	BASE_RATE("TaxCalculationType.BaseRate", 3L);

	private Long id;
	private String name;
	private String translateTemplate;

	TaxCalculationType(String translateTemplateVal, Long idVal) {
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
	public static TaxCalculationType create(Map record) {
		if(record.containsKey("name")) return TaxCalculationType.valueOf((String) record.get("name"));
		return null;
	}

}
