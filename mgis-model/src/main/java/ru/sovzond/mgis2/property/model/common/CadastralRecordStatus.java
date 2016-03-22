package ru.sovzond.mgis2.property.model.common;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Map;

/**
 * Created by Sergey Lvov on 16.03.16.
 *
 * Status of cadastral record for property object
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum CadastralRecordStatus {

	EARLIE_REGISTERED("CadastralRecordStatus.EarlieRegistered", 1L, "01"),
	TEMPORARY("CadastralRecordStatus.Temporary", 2L, "05"),
	NOW_REGISTERED("CadastralRecordStatus.NowRegistered", 3L, "06"),
	REMOVED("CadastralRecordStatus.Removed", 4L, "07"),
	ANNULLED("CadastralRecordStatus.Annulled", 5L, "08");

	private Long id;
	private String code;
	private String name;
	private String translateTemplate;

	CadastralRecordStatus(String translateTemplateVal, Long idVal, String codeVal) {
		setTranslateTemplate(translateTemplateVal);
		setId(idVal);
		setCode(codeVal);
		setName(name());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@SuppressWarnings("unused")
	public String getTranslateTemplate() {
		return translateTemplate;
	}

	public void setTranslateTemplate(String translateTemplate) {
		this.translateTemplate = translateTemplate;
	}

	@JsonCreator
	public static CadastralRecordStatus create(Map record) {
		if(record.containsKey("name")) return CadastralRecordStatus.valueOf((String) record.get("name"));
		return null;
	}
}
