package baseTest;


import configuration.yaml.YamlConfigControler;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.IOException;

public class TestBasePlaywright {
    private static Logger logger = LoggerFactory.getLogger("TestBasePlaywright.class");
    private static YamlConfigControler controler;


    @BeforeAll
    static void setUp() {
        controler = new YamlConfigControler();
    }

    @BeforeEach
    void beforeEach() throws IOException {
        logger.debug("Driver initialized");
    }

    @AfterEach
     void tearDown() {

        logger.debug("Browser window closed closed");
    }

}
