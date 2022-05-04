package configuration.yaml.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;

import java.util.LinkedHashMap;
import java.util.Map;

public class Browser {
    private Map<String, Object> browserConfig = new LinkedHashMap<>();
    public Browser() {
    }

    @JsonAnyGetter
    public Map<String, Object> getBrowserConfig() {
        return browserConfig;
    }

    @JsonAnySetter
    public void setBrowserConfig(String key, Object value) {
        browserConfig.put(key, value);
    }
}
