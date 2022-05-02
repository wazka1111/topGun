package configuration.yaml.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class EnvironmentModel {

    private BaseModel prod;
    private BaseModel cvp;
    private BaseModel stag;

    public List<BaseModel> getListOfEnvironments() {
        List<BaseModel> listOfEnvironments = new ArrayList<>();
        listOfEnvironments.add(getStag());
        listOfEnvironments.add(getCvp());
        listOfEnvironments.add(getProd());
        return listOfEnvironments;
    }
}
