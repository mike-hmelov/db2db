package org.demon.db2db.gui;

import org.demon.db2db.Configuration;
import org.demon.db2db.Synchronizer;

/**
 * @author Mihail Hmelov (demon)
 *         Date: 12/1/13
 *         mailto:mike.hmelov@gmail.com"
 */
public class GUIFactory {
    public static EventHandler createGUI(Synchronizer synchronizer, Configuration configuration) {
        if(configuration.showGUI())
            return new FXGUI(synchronizer);
        return new ConsoleGUI();
    }
}
