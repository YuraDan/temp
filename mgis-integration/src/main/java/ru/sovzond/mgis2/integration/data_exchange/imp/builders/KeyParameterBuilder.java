package ru.sovzond.mgis2.integration.data_exchange.imp.builders;

import ru.sovzond.mgis2.integration.data_exchange.imp.builders.base.AttributeValueExtractor;
import ru.sovzond.mgis2.integration.data_exchange.imp.builders.base.NodeBuilder;

import java.util.function.Predicate;

import static ru.sovzond.mgis2.integration.data_exchange.imp.handlers.RusRegisterFieldKeys.*;

/**
 * Created by donchenko-y on 2/10/16.
 */
public class KeyParameterBuilder extends NodeBuilder<Number[]> {
	private Integer type;
	private Double value;

	protected KeyParameterBuilder(ConstructBuilder constructBuilder, Predicate<String> keyParameterPredicate) {
		super(constructBuilder, keyParameterPredicate);
	}

	@Override
	protected void extractAttributes(AttributeValueExtractor attributeValueExtractor) {
		value = Double.parseDouble(attributeValueExtractor.attribute(KEY_VALUE_ATTR));
		type = Integer.parseInt(attributeValueExtractor.attribute(KEY_TYPE_ATTR));
	}

	@Override
	protected Number[] buildImpl() {
		return new Number[]{value, type};
	}

	@Override
	protected void resetImpl() {
		super.resetImpl();
		value = null;
		type = null;
	}
}
