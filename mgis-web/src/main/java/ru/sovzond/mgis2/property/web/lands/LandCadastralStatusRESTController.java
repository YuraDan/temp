package ru.sovzond.mgis2.property.web.lands;

import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.sovzond.mgis2.property.model.lands.LandCadastralStatus;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Sergey Lvov on 16.03.16.
 *
 * REST controller for land cadastral status
 */
@RestController
@RequestMapping("/lands/land-cadastral-status")
@Scope("session")
public class LandCadastralStatusRESTController implements Serializable {
	private static final long serialVersionUID = -8969104464490987777L;

	@RequestMapping(method = RequestMethod.GET)
	public List<LandCadastralStatus> list() {
		return Arrays.asList(LandCadastralStatus.values());
	}
}
