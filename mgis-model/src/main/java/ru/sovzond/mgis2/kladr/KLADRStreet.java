package ru.sovzond.mgis2.kladr;

import javax.persistence.*;

/**
 * Created by Alexander Arakelyan on 08.09.15.
 */
@SuppressWarnings("unused")
@Entity
@Table(name = "kladr_street", indexes = {@Index(columnList = "code", name = "kladr_street_code_index"), @Index(columnList = "name", name = "kladr_street_name_index")})
public class KLADRStreet implements Cloneable {
	@Id
	@SequenceGenerator(name = "pk_sequence", sequenceName = "kladr_street_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
	@Column
	private Long id;

	/**
	 * Наименование объекта
	 */
	@Column(name = "name")
	private String name;

	/**
	 * Сокращенное наименование типа объекта
	 */
	@Column(name = "socr")
	private String socr;

	/**
	 * Код
	 * <p/>
	 * СС РРР ГГГ ППП УУУУ АА, где
	 * СС – код субъекта Российской Федерации (региона), коды регионов представлены в Приложении 2 к Описанию классификатора адресов Российской Федерации (КЛАДР);
	 * РРР – код района;
	 * ГГГ – код города;
	 * ППП – код населенного пункта;
	 * УУУУ – код улицы;
	 * АА – признак актуальности наименования адресного объекта.
	 */
	@Column(name = "code", unique = true)
	private String code;

	/**
	 * Почтовый индекс
	 */
	@Column(name = "index")
	private String index;

	/**
	 * Код ИФНС
	 */
	@Column(name = "gninmb")
	private String gninmb;

	/**
	 * Код территориального участка ИФНС
	 */
	@Column(name = "uno")
	private String uno;

	/**
	 * Код ОКАТО
	 */
	@Column(name = "ocatd")
	private String ocatd;

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

	public String getSocr() {
		return socr;
	}

	public void setSocr(String socr) {
		this.socr = socr;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public String getGninmb() {
		return gninmb;
	}

	public void setGninmb(String gninmb) {
		this.gninmb = gninmb;
	}

	public String getUno() {
		return uno;
	}

	public void setUno(String uno) {
		this.uno = uno;
	}

	public String getOcatd() {
		return ocatd;
	}

	public void setOcatd(String ocatd) {
		this.ocatd = ocatd;
	}

	@SuppressWarnings("CloneDoesntCallSuperClone")
	public KLADRStreet clone() {
		KLADRStreet street = new KLADRStreet();
		street.setCode(code);
		street.setGninmb(gninmb);
		street.setId(id);
		street.setIndex(index);
		street.setName(name);
		street.setOcatd(ocatd);
		street.setSocr(socr);
		street.setUno(uno);
		return street;
	}
}
