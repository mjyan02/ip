package zirnitra.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import zirnitra.ui.MainWindow;

import java.io.IOException;
import java.nio.file.Paths;

/**
 * A GUI for Zirnitra using FXML.
 * This class sets up the ZirnitraGui using JavaFX.
 * This class is used in Zirnitra Launcher to launch the GUI.
 */
public class Main extends Application {
    private static final String FXML_PATH = "/view/MainWindow.fxml";
    private final Zirnitra zirnitra = new Zirnitra(Paths.get(System.getProperty("user.dir"), "TaskList")
            .toString());

    /**
     * Displays main window of the Zirnitra Chatbot.
     * Loads the FXML file for the mainWindow, sets up the scene, and shows the stage.
     *
     * @param stage The stage for ZirnitraGUI.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class
                    .getResource(FXML_PATH));

            AnchorPane anchor = fxmlLoader.load();
            Scene scene = new Scene(anchor);
            stage.setScene(scene);
            stage.setTitle("Zirnitra Chatbot");
            MainWindow zGui = fxmlLoader.getController();
            zGui.setZirnitra(zirnitra);
            stage.show();
        } catch (IOException e) {
            e.getMessage();
        }
    }

}
