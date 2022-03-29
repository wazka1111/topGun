package configuration.yaml.model;


import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import lombok.Data;

import java.util.LinkedHashMap;
import java.util.Map;

@Data
public class BaseModel {
    private boolean active;
    private Map<String, Object> properties = new LinkedHashMap<>();
    public BaseModel() {
    }

    public boolean isActive() {
        return active;
    }

    @JsonAnySetter  //https://www.concretepage.com/jackson-api/jackson-jsonanygetter-and-jsonanysetter-example
    void setProperties(String key, Object value) {
        properties.put(key, value);
    }

    @JsonAnyGetter  //https://www.concretepage.com/jackson-api/jackson-jsonanygetter-and-jsonanysetter-example
    public Map<String, Object> getProperties() {
        return properties;
    }

}
