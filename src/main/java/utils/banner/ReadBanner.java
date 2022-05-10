package utils.banner;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;


public class ReadBanner {
    private static Logger logger = LoggerFactory.getLogger(ReadBanner.class);
    private static final String BANNER_FILE_NAME = "banner.txt";
    public static final String BANNER = readBannerAsString();


    private static String readBannerAsString() {

        String result = "";

        try {
            result = IOUtils.toString(Objects.requireNonNull(ReadBanner.class.getClassLoader().getResourceAsStream(BANNER_FILE_NAME)), StandardCharsets.UTF_8);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }

        return result;
    }


}
