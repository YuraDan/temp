package ru.sovzond.mgis2.taxes.model.land.privilege;

/**
 * Created by Sergey Lvov on 25.03.16.
 */
public enum LandTaxBasePrivilegeType {
	NONE, // не применяется
	DOCUMENT, // освобождение от налогообложения в соответствии с местным нормативным актом
	ARTICLE395, // освобождение от налогообложения по статье 395 НК РФ
	REDUCTION // уменьшение налоговой базы
}
