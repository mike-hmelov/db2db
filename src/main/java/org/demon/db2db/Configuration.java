package org.demon.db2db;

import org.demon.db2db.db.DatabaseType;

import java.util.Properties;

/**
 * @author Mihail Hmelov (demon)
 *         Date: 10/9/13
 *         mailto:mike.hmelov@gmail.com"
 */
public class Configuration {
    public static final String DB1_TYPE = "db1.type";
    public static final String DB2_TYPE = "db2.type";
    public static final String DB1_CLASS = "db1.class";
    public static final String DB2_CLASS = "db2.class";
    public static final String DB1_JDBC_URL = "db1.jdbc.url";
    public static final String DB2_JDBC_URL = "db2.jdbc.url";
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
        return configuration;
    }

    private static String nonEmptyValue(String property) {
        if(property != null && !property.isEmpty())
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
}
