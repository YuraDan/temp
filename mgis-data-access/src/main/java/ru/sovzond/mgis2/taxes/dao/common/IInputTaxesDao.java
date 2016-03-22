package ru.sovzond.mgis2.taxes.dao.common;

import ru.sovzond.mgis2.dataaccess.base.impl.PagerBuilderCriteria;
import ru.sovzond.mgis2.taxes.model.InputTaxes;

/**
 * Created by donchenko-y on 3/22/16.
 */
public interface IInputTaxesDao extends ITaxesBaseDao<InputTaxes> {

	PagerBuilderCriteria<InputTaxes> createFilter(String cadastralNumber, String orderBy, int first, int max);

}
