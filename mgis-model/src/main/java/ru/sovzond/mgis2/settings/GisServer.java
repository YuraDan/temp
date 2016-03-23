package ru.sovzond.mgis2.settings;

import javax.persistence.*;

/**
 * Created by Alexander Arakelyan on 16.09.15.
 */

@Entity
@Table(name = "mgis2_gis_server")
public class GisServer implements Cloneable {

	@Id
	@SequenceGenerator(name = "pk_sequence", sequenceName = "mgis2_gis_server_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
	@Column
	private Long id;

	@Column(unique = true, nullable = false)
	private String code;

	@Column
	private String url;

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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@SuppressWarnings("CloneDoesntCallSuperClone")
	public GisServer clone() {
		GisServer gisServer = new GisServer();
		gisServer.setCode(code);
		gisServer.setId(id);
		gisServer.setUrl(url);
		return gisServer;
	}

}
