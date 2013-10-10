package org.demon.db2db;

import org.demon.db2db.db.DatabaseType;
import org.junit.Test;

import java.util.Properties;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * @author Mihail Hmelov (demon)
 *         Date: 10/10/13
 *         mailto:mike.hmelov@gmail.com"
 */
public class ConfigurationTest {

    @Test
    public void testFromProperties() throws Exception {
        Properties properties = new Properties();
        properties.put(Configuration.DB1_TYPE, "oracle");
        properties.put(Configuration.DB2_TYPE, "h2");
        properties.put(Configuration.DB1_CLASS, "class1");
        properties.put(Configuration.DB2_CLASS, "class2");
        properties.put(Configuration.DB1_JDBC_URL, "url1");
        properties.put(Configuration.DB2_JDBC_URL, "url2");
        properties.put(Configuration.DB1_USERNAME, "user1");
        properties.put(Configuration.DB2_USERNAME, "user2");
        properties.put(Configuration.DB1_PASSWORD, "pass1");
        properties.put(Configuration.DB2_PASSWORD, "pass2");


        Configuration configuration = Configuration.fromProperties(properties);

        assertEquals(DatabaseType.Oracle, configuration.getSourceDatabaseType());
        assertEquals(DatabaseType.H2, configuration.getDestinationDatabaseType());
        assertEquals("class1", configuration.getSourceClass());
        assertEquals("class2", configuration.getDestinationClass());
        assertEquals("url1", configuration.getSourceURL());
        assertEquals("url2", configuration.getDestinationURL());
        assertEquals("user1", configuration.getSourceUserName());
        assertEquals("user2", configuration.getDestinationUserName());
        assertEquals("pass1", configuration.getSourcePassword());
        assertEquals("pass2", configuration.getDestinationPassword());

        properties.remove(Configuration.DB1_USERNAME);
        properties.remove(Configuration.DB2_USERNAME);
        properties.remove(Configuration.DB1_PASSWORD);
        properties.remove(Configuration.DB2_PASSWORD);
        configuration = Configuration.fromProperties(properties);

        assertEquals(DatabaseType.Oracle, configuration.getSourceDatabaseType());
        assertEquals(DatabaseType.H2, configuration.getDestinationDatabaseType());
        assertEquals("class1", configuration.getSourceClass());
        assertEquals("class2", configuration.getDestinationClass());
        assertEquals("url1", configuration.getSourceURL());
        assertEquals("url2", configuration.getDestinationURL());
        assertNull(configuration.getSourceUserName());
        assertNull(configuration.getDestinationUserName());
        assertNull(configuration.getSourcePassword());
        assertNull(configuration.getDestinationPassword());
    }
}
