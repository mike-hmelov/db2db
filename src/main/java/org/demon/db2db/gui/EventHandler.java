package org.demon.db2db.gui;

/**
 * @author Mihail Hmelov (demon)
 *         Date: 12/1/13
 *         mailto:mike.hmelov@gmail.com"
 */
@FunctionalInterface
public interface EventHandler {
    void handle(Event event);
}
