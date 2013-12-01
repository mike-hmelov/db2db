package org.demon.db2db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Mihail Hmelov (demon)
 *         Date: 12/1/13
 *         mailto:mike.hmelov@gmail.com"
 */
public class Application {
    private static final Logger logger = LoggerFactory.getLogger(Synchronizer.class);

    public static final String DB2DB_PROPERTIES = "db2db.properties";

    public static void main(String[] args) {
        logger.info("Starting application");
        String configName = DB2DB_PROPERTIES;
        if(args.length == 1)
            configName = args[0];
        logger.info("Using {} as configuration file", configName);
        Configuration configuration = Configuration.tryParseConfig(configName);
        if(configuration == null)
            return;
        Synchronizer app = new Synchronizer(configuration);
        app.doLogic();
    }
}
