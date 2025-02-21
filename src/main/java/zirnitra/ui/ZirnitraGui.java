package zirnitra.ui;

import zirnitra.Zirnitra;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Objects;
import java.nio.file.Paths;

/**
 * GUI interface using JavaFX for Zirnitra.
 */
public class ZirnitraGui extends Application {
    @FXML
    public Button sendButton;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox vB;
    @FXML
    private TextField userInput;
    private Zirnitra zirnitra = new Zirnitra(Paths.get(System.getProperty("user.dir"), "TaskList")
            .toString());
    private final Image userImage = new Image(Objects.requireNonNull(getClass()
            .getResourceAsStream("/images/User.jpg")));
    private final Image dukeImage = new Image(Objects.requireNonNull(getClass()
            .getResourceAsStream("/images/Zirnitra.jpg")));

    public ZirnitraGui() {
    }

    /**
     * Welcome Message for Zirnitra.
     */
    public void welcomeMessage() {
        String welcomeText = printResponse("help");
        vB.getChildren().add(DialogBox.getDukeDialog(welcomeText, dukeImage));
    }

    /**
     * Setup function for GUI.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);

            stage.setTitle("zirnitra");
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();

            fxmlLoader.<ZirnitraGui>getController().setZirnitra(zirnitra);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setZirnitra(Zirnitra z) {
        this.zirnitra = z;
        welcomeMessage();
        vB.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
        sendButton.setOnAction(event -> handleInput());
        userInput.setOnAction(event -> handleInput());
    }

    /**
     * Handles user inputs and redirects it to Zirnitra GUI.
     */
    @FXML
    private void handleInput() {
        String userText = userInput.getText();
        String dukeText = printResponse(userText);

        vB.getChildren().addAll(
                DialogBox.getUserDialog(userText, userImage),
                DialogBox.getDukeDialog(dukeText, dukeImage)
        );

        userInput.clear();
    }

    /**
     * Converts outputs from Zirnitra.java into a string.
     * This allows the console to print out the same output.
     *
     * @param input User input keyword.
     * @return The output from Duke.
     */
    private String printResponse(String input) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream dukeOut = System.out;
        System.setOut(new PrintStream(output));

        zirnitra.run(input);

        System.setOut(dukeOut);
        return output.toString().trim();
    }
}