package com.inncretech.merchant.utility;

import java.io.StringWriter;
import java.text.SimpleDateFormat;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.inncretech.catalogue.db.beans.Item;

public final class TagLibClass {

	private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
	private static final JsonFactory JSON_FACTORY = new JsonFactory();

	static {
		OBJECT_MAPPER.setDateFormat(new SimpleDateFormat("dd-MM-yyyy"));
		OBJECT_MAPPER.setNodeFactory(JsonNodeFactory.withExactBigDecimals(true)); 
	}

	public static String convertToJson(final Object object) throws Exception {

		return convertObjectToString(object);
	}

	public static String getItemBlankJsonObject() throws Exception {

		return convertObjectToString(new Item());
	}

	private static String convertObjectToString(final Object object) throws Exception {
		StringWriter stringWriter = new StringWriter();
		JsonGenerator jsonGenerator = JSON_FACTORY.createGenerator(stringWriter);
		jsonGenerator.enable(JsonGenerator.Feature.WRITE_NUMBERS_AS_STRINGS);
		OBJECT_MAPPER.writeValue(jsonGenerator, object);
		return stringWriter.toString();
	}

}
