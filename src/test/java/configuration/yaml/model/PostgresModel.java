package configuration.yaml.model;


import lombok.Data;

@Data
public class PostgresModel {
    private String host;
    private String user;
    private String password;

}
