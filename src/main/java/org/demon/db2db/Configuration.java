package org.demon.db2db;

import org.demon.db2db.db.*;
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
public class Configuration {
    private static final Logger logger = LoggerFactory.getLogger(Configuration.class);

    static final String SHOW_GUI = "show.gui";
    static final String DB1_TYPE = "db1.type";
    static final String DB2_TYPE = "db2.type";
    static final String DB1_CLASS = "db1.class";
    static final String DB2_CLASS = "db2.class";
    static final String DB1_JDBC_URL = "db1.jdbc.url";
    static final String DB2_JDBC_URL = "db2.jdbc.url";
    static final String DB1_USERNAME = "db1.user";
    static final String DB2_USERNAME = "db2.user";
    static final String DB1_PASSWORD = "db1.password";
    static final String DB2_PASSWORD = "db2.password";

    private DatabaseType sourceDatabaseType;
    private DatabaseType destinationDatabaseType;
    private String sourceURL;
    private String destinationURL;
    private String sourceClass;
    private String destinationClass;
    private String sourceUserName;
    private String destinationUserName;
    private String sourcePassword;
    private String destinationPassword;
    private boolean gui;

    private Configuration() {
    }

    public static Configuration fromProperties(Properties properties) {
        Configuration configuration = new Configuration();
        configuration.sourceDatabaseType = DatabaseType.forName(properties.getProperty(DB1_TYPE));
        configuration.destinationDatabaseType = DatabaseType.forName(properties.getProperty(DB2_TYPE));

        configuration.sourceClass = properties.getProperty(DB1_CLASS);
        configuration.destinationClass = properties.getProperty(DB2_CLASS);

        configuration.sourceURL = properties.getProperty(DB1_JDBC_URL);
        configuration.destinationURL = properties.getProperty(DB2_JDBC_URL);

        configuration.sourceUserName = nonEmptyValue(properties.getProperty(DB1_USERNAME));
        configuration.destinationUserName = nonEmptyValue(properties.getProperty(DB2_USERNAME));

        configuration.sourcePassword = nonEmptyValue(properties.getProperty(DB1_PASSWORD));
        configuration.destinationPassword = nonEmptyValue(properties.getProperty(DB2_PASSWORD));

        configuration.gui = toBoolean(nonEmptyValue(properties.getProperty(SHOW_GUI)));
        return configuration;
    }

    private static boolean toBoolean(String s) {
        if (s == null || s.isEmpty())
            return false;
        return Boolean.parseBoolean(s);
    }

    private static Properties readPropertyFile(String fileName) {
        if (!new File(fileName).exists()) {
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

    private static Configuration parseParameters(Properties properties) {
        boolean fail;
        fail = checkNotExists(properties, Configuration.DB1_TYPE);
        fail |= checkNotExists(properties, Configuration.DB2_TYPE);
        fail |= checkNotExists(properties, Configuration.DB1_CLASS);
        fail |= checkNotExists(properties, Configuration.DB2_CLASS);
        fail |= checkNotExists(properties, Configuration.DB1_JDBC_URL);
        fail |= checkNotExists(properties, Configuration.DB2_JDBC_URL);
        if (!fail)
            return Configuration.fromProperties(properties);
        return null;
    }

    private static boolean checkNotExists(Properties properties, String name) {
        boolean flag = !properties.containsKey(name);
        if (flag)
            logger.error("Flag with name {} is not found in configuration file", name);
        return flag;
    }

    private static String nonEmptyValue(String property) {
        if (property != null && !property.isEmpty())
            return property;
        return null;
    }

    public DatabaseType getSourceDatabaseType() {
        return sourceDatabaseType;
    }

    public DatabaseType getDestinationDatabaseType() {
        return destinationDatabaseType;
    }

    public String getSourceURL() {
        return sourceURL;
    }

    public String getDestinationURL() {
        return destinationURL;
    }

    public String getSourceClass() {
        return sourceClass;
    }

    public String getDestinationClass() {
        return destinationClass;
    }

    public String getSourceUserName() {
        return sourceUserName;
    }

    public String getDestinationUserName() {
        return destinationUserName;
    }

    public String getSourcePassword() {
        return sourcePassword;
    }

    public String getDestinationPassword() {
        return destinationPassword;
    }

    public static Configuration tryParseConfig(String configName) {
        Properties properties = readPropertyFile(configName);
        if (properties == null)
            return null;
        return parseParameters(properties);
    }

    public boolean showGUI() {
        return gui;
    }
}
