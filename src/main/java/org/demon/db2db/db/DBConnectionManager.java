package org.demon.db2db.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Mihail Hmelov (demon)
 *         Date: 12/1/13
 *         mailto:mike.hmelov@gmail.com"
 */
public class DBConnectionManager {
    private static final Logger logger = LoggerFactory.getLogger(DBConnectionManager.class);

    public static DBConnectionHolder createConnection(DatabaseType type, String jdbcClass, String url, String userName, String password) {
        try {
            Class.forName(jdbcClass);
            Connection connection = DriverManager.getConnection(url, userName, password);
            return new DBConnectionHolder(type, connection);
        } catch (ClassNotFoundException | SQLException e) {
            logger.error("Fail to create DB connection", e);
        }
        return null;
    }
}
