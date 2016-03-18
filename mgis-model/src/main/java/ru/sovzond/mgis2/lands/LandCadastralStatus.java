package ru.sovzond.mgis2.lands;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Created by Sergey Lvov on 16.03.16.
 *
 * Statuses of cadastral record for lands
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum LandCadastralStatus {

	PROJECTED("LandCadastralStatus.Projected", 1L),
	WAITED("LandCadastralStatus.Waited", 2L),
	REGISTERED("LandCadastralStatus.Registered", 3L),
	DELETED("LandCadastralStatus.Deleted", 4L);

	private Long id;
	private String translateTemplate;
	private String name;

	LandCadastralStatus(String translateTemplateString, Long curId) {
		setTranslateTemplate(translateTemplateString);
		setId(curId);
		setName(name());
	}

	@SuppressWarnings("unused")
	public String getTranslateTemplate() {
		return translateTemplate;
	}

	public void setTranslateTemplate(String translateTemplate) {
		this.translateTemplate = translateTemplate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
