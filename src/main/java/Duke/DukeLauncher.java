package duke;

import duke.ui.DukeGui;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class DukeLauncher {
    public static void main(String[] args) {
        Application.launch(DukeGui.class, args);
    }
}