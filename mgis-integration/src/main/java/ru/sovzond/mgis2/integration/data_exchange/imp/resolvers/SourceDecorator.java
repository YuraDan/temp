package ru.sovzond.mgis2.integration.data_exchange.imp.resolvers;

import ru.sovzond.mgis2.integration.data_exchange.imp.dto.EntitySpatialDTO;

/**
 * Created by Alexander Arakelyan on 26.12.15.
 */
public interface SourceDecorator<T> {
	SourceDecorator wrap(T land);

	String getName();

	EntitySpatialDTO getEntitySpatial();
}

