package configuration.yaml;


import configuration.yaml.model.ConfigModel;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;

@Getter
public class YamlReader extends BaseYamlReader {

    private ConfigModel envConfig;
    private Logger log = LoggerFactory.getLogger(YamlReader.class);
    public YamlReader() {
        try {
            InputStream is = new ClassPathResource("config-local.yml").getInputStream();
            envConfig = loadYaml(is, ConfigModel.class);
        } catch (IOException ex) {
        log.error("Exception from YamlReader class");
        }
    }

    public ConfigModel getEnvConfig() {
        return envConfig;
    }
}
