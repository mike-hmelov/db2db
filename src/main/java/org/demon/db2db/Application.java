package org.demon.db2db;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @author Mihail Hmelov (demon)
 *         Date: 10/9/13
 *         mailto:mike.hmelov@gmail.com"
 */
public class Application {

    public static final String DB2DB_PROPERTIES = "db2db.properties";

    private Configuration configuration;

    public static void main(String[] args) {
        Application app = new Application();
        if (app.parseParameters(readPropertyFile(DB2DB_PROPERTIES)))
            return;
        app.doLogic();
    }

    private static Properties readPropertyFile(String fileName) {
        if (!new File(fileName).exists())
            throw new RuntimeException("Cannot find configuration file `" + fileName + "`");
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(fileName));
        } catch (IOException e) {
            throw new RuntimeException("Error reading property file", e);
        }
        return properties;
    }

    boolean parseParameters(Properties properties) {
        boolean fail;
        fail = checkNotExists(properties, "db1.type");
        fail |= checkNotExists(properties, "db1.type");
        fail |= checkNotExists(properties, "db1.type");
        if(!fail)
            configuration = Configuration.fromProperties(properties);
        return !fail;
    }

    private boolean checkNotExists(Properties properties, String name) {
        boolean flag = !properties.containsKey(name);
        if(flag)
            System.out.println(String.format("\tMissing parameter `%s`", name));
        return flag;
    }

    private void doLogic() {
        //TODO load and validate parameters
        //TODO create 2 connection holders
        //TODO run sync processor
        //TODO run data sync
    }
}
