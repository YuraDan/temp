package ru.sovzond.mgis2.taxes.model.land.privilege;

import javax.persistence.*;

/**
 * Created by Sergey Lvov on 25.03.16.
 *
 * Land tax base privilege basis
 */
@Entity
@Table(name = "mgis2_taxes_land_privilege_base_basis")
public class LandTaxBasePrivilegeBasis implements Cloneable {

	@Id
	@SequenceGenerator(name = "pk_sequence", sequenceName = "mgis2_taxes_land_privilege_base_basis_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
	@Column
	private Long id;

	@Column
	private String paragraph; // абзац

	@Column
	private String chapter; // глава налогового кодекса

	@Column
	private String article; // статья

	@Column
	private String item; // пункт

	@Column
	private String subItem; // подпункт

	@Column
	private String name; // наименование

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getParagraph() {
		return paragraph;
	}

	public void setParagraph(String paragraph) {
		this.paragraph = paragraph;
	}

	public String getChapter() {
		return chapter;
	}

	public void setChapter(String chapter) {
		this.chapter = chapter;
	}

	public String getArticle() {
		return article;
	}

	public void setArticle(String article) {
		this.article = article;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public String getSubItem() {
		return subItem;
	}

	public void setSubItem(String subItem) {
		this.subItem = subItem;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LandTaxBasePrivilegeBasis clone() {
		try {
			return (LandTaxBasePrivilegeBasis) super.clone();
		} catch (CloneNotSupportedException e) {
			return null;
		}
	}

}
