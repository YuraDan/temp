package ru.sovzond.mgis2.taxes.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;
import ru.sovzond.mgis2.dataaccess.base.PageableContainer;
import ru.sovzond.mgis2.taxes.model.common.InputTaxes;
import ru.sovzond.mgis2.taxes.services.common.IInputTaxesService;

import javax.transaction.Transactional;
import java.io.Serializable;

/**
 * Created by donchenko-y on 3/22/16.
 */

@RestController
@RequestMapping("/inputTaxes")
@Scope("session")
public class InputTaxesRESTController implements Serializable {

	@Autowired
	private IInputTaxesService inputTaxesService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	@Transactional
	public PageableContainer<InputTaxes> list(@RequestParam(value = "orderBy", defaultValue = "") String orderBy, @RequestParam(defaultValue = "0") int first, @RequestParam(defaultValue = "0") int max, @RequestParam(defaultValue = "") String cadastralNumber) {
		return inputTaxesService.list(orderBy, first, max, cadastralNumber);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@Transactional
	public InputTaxes read(@PathVariable("id") Long id) {
		return inputTaxesService.load(id).clone();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@Transactional
	public void delete(@PathVariable Long id) {
		inputTaxesService.remove(inputTaxesService.load(id));
	}

}
