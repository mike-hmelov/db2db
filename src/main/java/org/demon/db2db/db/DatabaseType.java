package org.demon.db2db.db;

/**
 * @author Mihail Hmelov (demon)
 *         Date: 10/10/13
 *         mailto:mike.hmelov@gmail.com"
 */
public enum DatabaseType {
    Oracle("oracle"),
    H2("h2");

    private final String name;

    DatabaseType(String name) {
        this.name = name;
    }

    public static DatabaseType forName(String name){
        for (DatabaseType databaseType : values()) {
            if(databaseType.name.toLowerCase().equals(name))
                return databaseType;
        }
        return null;
    }
}
