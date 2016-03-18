package ru.sovzond.mgis2.property;

import javax.persistence.*;

/**
 * Created by Sergey Lvov on 16.03.16.
 *
 * Status of cadastral record for property object
 */
@Entity
@Table(name = "mgis2_cadastral_record_status", indexes = @Index(name = "cadastral_record_status_code_ind", unique = true, columnList = "code"))
public class CadastralRecordStatus implements Cloneable {
	@Id
	@SequenceGenerator(name = "pk_sequence", sequenceName = "cadastral_record_status_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
	@Column
	private Long id;

	@Column(length = 2, name = "code")
	private String code;

	@Column(length = 50)
	private String name;

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

	@SuppressWarnings("CloneDoesntCallSuperClone")
	public CadastralRecordStatus clone() {
		CadastralRecordStatus cadastralRecordStatus = new CadastralRecordStatus();
		cadastralRecordStatus.setId(getId());
		cadastralRecordStatus.setCode(getCode());
		cadastralRecordStatus.setName(getName());
		return cadastralRecordStatus;
	}
}
