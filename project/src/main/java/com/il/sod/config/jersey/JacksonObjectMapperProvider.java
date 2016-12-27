package com.il.sod.config.jersey;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.ext.ContextResolver;
import java.text.SimpleDateFormat;

public class JacksonObjectMapperProvider implements ContextResolver<ObjectMapper> {
	final static Logger LOGGER = LoggerFactory.getLogger(JacksonObjectMapperProvider.class);

	public static final ObjectMapper MAPPER = new ObjectMapper();

	static {
		MAPPER.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
		MAPPER.disable(MapperFeature.USE_GETTERS_AS_SETTERS);
		MAPPER.configure(Feature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER, true);
		MAPPER.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
//		MAPPER.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
//		MAPPER.setDateFormat(new SimpleDateFormat("yyyy-MM-dd:"));
		MAPPER.setDateFormat(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX"));
	}

	public JacksonObjectMapperProvider() {
		LOGGER.info("Instantiate JacksonObjectMapperProvider");
	}

	@Override
	public ObjectMapper getContext(Class<?> type) {
		LOGGER.info("JacksonObjectMapperProvider.getContext() called with type: " + type);
		return MAPPER;
	}
}