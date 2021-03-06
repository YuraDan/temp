package ru.sovzond.mgis2.property.web;

import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.sovzond.mgis2.property.model.common.CadastralRecordStatus;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Sergey Lvov on 16.03.16.
 *
 * REST controller for cadastral record status
 */
@RestController
@RequestMapping("/property/cadastral-record-status")
@Scope("session")
public class CadastralRecordStatusRESTController implements Serializable {
	private static final long serialVersionUID = -8967456786545446777L;

	@RequestMapping(method = RequestMethod.GET)
	public List<CadastralRecordStatus> list() {
		return Arrays.asList(CadastralRecordStatus.values());
	}
}
