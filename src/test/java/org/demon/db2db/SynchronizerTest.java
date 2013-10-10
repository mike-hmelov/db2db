package org.demon.db2db;

import org.junit.Test;

import java.io.File;
import java.util.Properties;

import static junit.framework.Assert.*;

/**
 * @author Mihail Hmelov (demon)
 *         Date: 10/10/13
 *         mailto:mike.hmelov@gmail.com"
 */
public class SynchronizerTest {
    private Synchronizer synchronizer = new Synchronizer();

    @Test
    public void testReadPropertyFile() throws Exception {
        Properties properties = synchronizer.readPropertyFile("hello");
        assertNull(properties);

        File tempFile = File.createTempFile("test", "dummy");
        properties = synchronizer.readPropertyFile(tempFile.getCanonicalPath());
        assertNotNull(properties);

        tempFile.delete();
    }

    @Test
    public void testParseParameters() throws Exception {
        Properties properties = new Properties();
        boolean result = synchronizer.parseParameters(properties);
        assertFalse(result);

        properties.put("db1.type", "param");
        properties.put("db2.type", "param");
        properties.put("db1.class", "param");
        properties.put("db2.class", "param");
        properties.put("db1.jdbc.url", "param");
        properties.put("db2.jdbc.url", "param");
        result = synchronizer.parseParameters(properties);
        assertTrue(result);
    }
}
