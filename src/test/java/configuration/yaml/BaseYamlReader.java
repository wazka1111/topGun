package configuration.yaml;


import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;

/**
 * Yaml reader that can be used for extension or in initial way
 */
@Getter
@Slf4j
public class BaseYamlReader {

    public BaseYamlReader() {
    }

    /**
     * @param fileStream yaml-file stream
     * @param type       class of model
     * @return model with data from parsed yaml file
     */
    public final <T> T loadYaml(InputStream fileStream, Class<T> type) {
        try {
            return JacksonUtil.yamlMapper().readValue(fileStream, type);
        } catch (IOException ex) {
            log.error("Cannot read config to model {}", type, ex);
            return null;
        }
    }

}
