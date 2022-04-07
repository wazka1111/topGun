package configuration.yaml;


import configuration.yaml.model.BaseModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class YamlConfigControler {
    Logger logger = LoggerFactory.getLogger(YamlConfigControler.class);

    public YamlConfigControler() {
        setSystemPropertiesFromYamlEnvironment();
    }

    private void setSystemPropertiesFromYamlEnvironment() {
        YamlReader yamlReader = new YamlReader();
        List<BaseModel> listOfEnvironments = yamlReader.getEnvConfig().getEnvironment().getListOfEnvironments();
        boolean foundActiveEnvironment = false;
//        for (EnvironmentModel environmentModel : listOfEnvironments) {                         ok
//            if (environmentModel.isActive()) {                                                 ok
//                foundActiveEnvironment = true;                                                 ok
//                Map<String, Object> environmentProperties = environmentModel.getProperties();  ok
//                for (Map.Entry entry : environmentProperties.entrySet()) {                     ok
//                    System.setProperty(entry.getKey().toString(), entry.getValue().toString());
//                    logger.info("Loaded environment property: {} = {}", entry.getKey().toString(), entry.getValue().toString());
//                }
//                logger.info("Loaded environment properties total: {}", environmentProperties.size());
//                break;
//            }
//
//        }
        //  lista -> obj EnvironmentModel -> filter isActive() -> ustawić  foundActiveEnvironment = true ->
        //  zmiana  environmentModel.getProperties() na  Map<String, Object> environmentProperties ->
        // pętla po environmentProperties.entrySet() ->  System.setProperty(entry.getKey().toString(), entry.getValue().toString());
        AtomicInteger counter = new AtomicInteger();
        listOfEnvironments.stream()
                .filter(e -> e.isActive())
                .map(e -> e.getProperties())
                .forEach(o-> o.entrySet().stream()
                        .forEach(s-> {
                            System.setProperty(s.getKey().toString(), s.getValue().toString());
                            counter.getAndIncrement();
                            logSensualData(s);

                        }));

        foundActiveEnvironment = true;
        logger.info("Loaded environment properties total: {}", counter);

        if (foundActiveEnvironment == false) loadDefaultEnvironment();
    }

    private void logSensualData(Map.Entry<String, Object> s) {
        String[] sensualKeys = {"login","password"};
        if(Arrays.stream(sensualKeys).anyMatch(s.getKey().toString()::contains))
        {
            logger.info("Loaded environment property: {} = {}", s.getKey().toString(), "************");
        }
        else{
            logger.info("Loaded environment property: {} = {}", s.getKey().toString(), s.getValue().toString());
        }
    }

    private void loadDefaultEnvironment() {
        logger.info("No environment was specified in config.yaml. Loading default properties for Test1");
        Map<String, Object> environmentProperties = new YamlReader().getEnvConfig().getEnvironment().getTest().getProperties();
        for (Map.Entry entry : environmentProperties.entrySet()) {
            System.setProperty(entry.getKey().toString(), entry.getValue().toString());
            logger.info("Loaded environment property: {} = {}", entry.getKey().toString(), entry.getValue().toString());
        }
        logger.info("Loaded environment properties total: {}", environmentProperties.size());
    }
}
