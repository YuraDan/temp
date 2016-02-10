package ru.sovzond.mgis2.integration.data_exchange.imp.handlers;

import ru.sovzond.mgis2.integration.data_exchange.imp.dto.BuildingDTO;
import ru.sovzond.mgis2.integration.data_exchange.imp.dto.IncompleteDTO;
import ru.sovzond.mgis2.integration.data_exchange.imp.dto.LandDTO;
import ru.sovzond.mgis2.integration.data_exchange.imp.handlers.region_cadastr.Region_CadastrCategoryPropertyExtractor;
import ru.sovzond.mgis2.integration.data_exchange.imp.resolvers.ILandResolver;

/**
 * Created by Alexander Arakelyan on 18.11.15.
 */
public class Region_CadastrHandler extends RusRegisterHandlerBase {

	public Region_CadastrHandler(ILandResolver<LandDTO> landResolver, ILandResolver<BuildingDTO> buildingResolver,
	                             ILandResolver<IncompleteDTO> incompleteConstructResolver) {
		super(landResolver, buildingResolver, incompleteConstructResolver, Region_CadastrHandler.class,
				new Region_CadastrCategoryPropertyExtractor());
	}

}
