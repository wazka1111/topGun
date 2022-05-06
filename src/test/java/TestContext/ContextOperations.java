package TestContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ContextOperations extends TestContext {

    private static Logger logger = LoggerFactory.getLogger(ContextOperations.class);
    public static void saveTestContext(String tags, TestContext context) {
        String[] allTags = tags.split(",");
        int paramLength = allTags.length;

        for(int i = 0; i < paramLength; ++i) {
            String tag = allTags[i];

            try {
                String[] keyValueTags;
                 if (tag.contains("=")) {
                    keyValueTags = new String[0];

                    try {
                        keyValueTags = tag.split("=");
                        if (keyValueTags[1].trim().equals("''")) {
                            context.setProperty(keyValueTags[0], "");
                        } else {
                            context.setProperty(keyValueTags[0], keyValueTags[1].trim());
                        }
                    } catch (ArrayIndexOutOfBoundsException var9) {
                        logger.error("Please provide value for " + keyValueTags[0]);
                    }
                } else {
                     logger.error("Please provide correct tags");
                }
            } catch (Exception e) {
               e.printStackTrace();
            }
        }

        logger.info("Context has been set properly");
        logger.info(context.toString());
    }
}
