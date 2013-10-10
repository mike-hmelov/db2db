package org.demon.db2db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @author Mihail Hmelov (demon)
 *         Date: 10/9/13
 *         mailto:mike.hmelov@gmail.com"
 */
public class Synchronizer {
    private static final Logger logger = LoggerFactory.getLogger(Synchronizer.class);

    public static final String DB2DB_PROPERTIES = "db2db.properties";

    private Configuration configuration;

    public static void main(String[] args) {
        logger.info("Starting application");
        Synchronizer app = new Synchronizer();
        String configName = DB2DB_PROPERTIES;
        if(args.length == 1)
            configName = args[0];
        logger.info("Using {} as configuration file", configName);
        Properties properties = app.readPropertyFile(configName);
        if(properties == null)
            return;
        if (app.parseParameters(properties))
            return;
        app.doLogic();
    }

    Properties readPropertyFile(String fileName) {
        if (!new File(fileName).exists()){
            logger.error("File `{}` not found", fileName);
            return null;
        }
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(fileName));
        } catch (IOException e) {
            logger.error("Error reading configuration file", e);
            return null;
        }
        return properties;
    }

    boolean parseParameters(Properties properties) {
        boolean fail;
        fail = checkNotExists(properties, "db1.type");
        fail |= checkNotExists(properties, "db2.type");
        fail |= checkNotExists(properties, "db1.class");
        fail |= checkNotExists(properties, "db2.class");
        fail |= checkNotExists(properties, "db1.jdbc.url");
        fail |= checkNotExists(properties, "db2.jdbc.url");
        if(!fail)
            configuration = Configuration.fromProperties(properties);
        return !fail;
    }

    private boolean checkNotExists(Properties properties, String name) {
        boolean flag = !properties.containsKey(name);
        if(flag)
            logger.error("Flag with name {} is not found in configuration file", name);
        return flag;
    }

    private void doLogic() {
        //TODO load and validate parameters
        //TODO create 2 connection holders
        //TODO run sync processor
        //TODO run data sync
    }
}
