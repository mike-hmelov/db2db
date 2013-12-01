package org.demon.db2db;

import org.demon.db2db.db.*;
import org.demon.db2db.gui.Event;
import org.demon.db2db.gui.EventHandler;
import org.demon.db2db.gui.GUIFactory;

/**
 * @author Mihail Hmelov (demon)
 *         Date: 10/9/13
 *         mailto:mike.hmelov@gmail.com"
 */
public class Synchronizer {

    private Configuration configuration;
    private EventHandler eventHandler;

    public Synchronizer(Configuration configuration) {
        this.configuration = configuration;
    }


    public void doLogic() {
        registerGUI(GUIFactory.createGUI(this, configuration));
        if (configuration.showGUI()) {
            showGUI();
        } else {
            doSync();
        }
    }

    private void doSync() {
        DBConnectionHolder sourceConnectionHolder = DBConnectionManager.createConnection(configuration.getSourceDatabaseType(), configuration.getSourceClass(), configuration.getSourceURL(), configuration.getSourceUserName(), configuration.getSourcePassword());
        DBConnectionHolder targetConnectionHolder = DBConnectionManager.createConnection(configuration.getSourceDatabaseType(), configuration.getSourceClass(), configuration.getSourceURL(), configuration.getSourceUserName(), configuration.getSourcePassword());
        if(sourceConnectionHolder == null || targetConnectionHolder == null)
            return;
    }

    private void showGUI() {
        eventHandler.handle(new Event(Event.Type.SHOW));
    }

    private void registerGUI(EventHandler gui) {
        this.eventHandler = gui;
    }
}
