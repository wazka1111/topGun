package configuration.yaml.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class EnvironmentModel {

    private BaseModel integrate;
    private BaseModel test;
    private BaseModel stag;

    public List<BaseModel> getListOfEnvironments() {
        List<BaseModel> listOfEnvironments = new ArrayList<>();
        listOfEnvironments.add(getIntegrate());
        listOfEnvironments.add(getTest());
        listOfEnvironments.add(getStag());
        return listOfEnvironments;
    }
}
