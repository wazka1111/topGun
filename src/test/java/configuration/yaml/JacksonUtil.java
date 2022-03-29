package configuration.yaml;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.ser.DefaultSerializerProvider.Impl;
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class JacksonUtil {
    private static final Logger log = LoggerFactory.getLogger(JacksonUtil.class);

    public JacksonUtil() {
    }

    public static ObjectMapper yamlMapper() {
        return new ObjectMapper(new YAMLFactory());
    }

    public static ObjectMapper jsonMapper() {
        return jsonMapper(false, false);
    }

    public static ObjectMapper xmlMapper() {
        JacksonXmlModule module = new JacksonXmlModule();
        module.setDefaultUseWrapper(false);
        return new XmlMapper(module);
    }

    public static ObjectMapper jsonMapper(boolean includeNulls, boolean nullAsEmpty) {
        ObjectMapper mapper = new ObjectMapper();
        if (nullAsEmpty) {
            Impl sp = new Impl();
            sp.setNullValueSerializer(new NullSerializer());
            mapper.setSerializerProvider(sp);
        }

        return mapper.findAndRegisterModules().setDefaultPropertyInclusion(includeNulls ? Include.USE_DEFAULTS : Include.NON_EMPTY).disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS).enable(SerializationFeature.WRITE_DATES_WITH_ZONE_ID).enable(SerializationFeature.INDENT_OUTPUT);
    }

    public static Map<String, Object> dictionaryMapper(Object valueToConvert) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, Visibility.NONE);
        objectMapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
        return (Map)objectMapper.convertValue(valueToConvert, new TypeReference<Map<String, Object>>() {
        });
    }

    public static String writeJsonAsString(Object valueToConvert) {
        return writeJsonAsString(valueToConvert, false, false);
    }

    public static String writeJsonAsString(Object valueToConvert, boolean includeNulls, boolean nullAsEmpty) {
        try {
            return jsonMapper(includeNulls, nullAsEmpty).writeValueAsString(valueToConvert);
        } catch (JsonProcessingException var4) {
            log.error("Failed to get json from '{}'", valueToConvert.toString());
            return "";
        }
    }

    public static <T> T readXmlAs(InputStream stream, Class<T> type) {
        try {
            return xmlMapper().readValue(stream, type);
        } catch (IOException var3) {
            log.error("Cannot read xml to type {}}", type, var3);
            return null;
        }
    }

    public static <T> T readXmlAs(String filePath, Class<T> type) {
        try {
            return readXmlAs((InputStream)(new FileInputStream(filePath)), type);
        } catch (FileNotFoundException var3) {
            log.error("Cannot read file '{}' to input stream", filePath, var3);
            return null;
        }
    }

    public static boolean isJson(String value) {
        try {
            jsonMapper().reader().readTree(value);
            return true;
        } catch (JsonProcessingException var2) {
            log.info("Cannot convert value to JSON:\n{}", value);
            return false;
        }
    }
}
