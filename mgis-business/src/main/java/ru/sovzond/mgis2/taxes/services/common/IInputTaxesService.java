package ru.sovzond.mgis2.taxes.services.common;


import ru.sovzond.mgis2.dataaccess.base.PageableContainer;
import ru.sovzond.mgis2.taxes.model.common.InputTaxes;

/**
 * Created by donchenko-y on 3/22/16.
 *
 */
public interface IInputTaxesService extends ITaxesService<InputTaxes> {
	PageableContainer<InputTaxes> list(String orderBy, int first, int max, String cadastralNumber);
}
