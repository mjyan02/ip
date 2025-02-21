package zirnitra.ui;

import javafx.scene.layout.AnchorPane;
import zirnitra.main.Zirnitra;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Objects;

/**
 * GUI interface using JavaFX for Zirnitra.
 */
public class MainWindow extends AnchorPane {
    /**
     * Send button in Zirnitra.
     */
    @FXML
    public Button sendButton;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox vB;
    @FXML
    private TextField userInput;
    private Zirnitra zirnitra;
    private final Image userImage = new Image(Objects.requireNonNull(getClass()
            .getResourceAsStream("/images/User.jpg")));
    private final Image dukeImage = new Image(Objects.requireNonNull(getClass()
            .getResourceAsStream("/images/Zirnitra.jpg")));

    /**
     * Default constructor for MainWindow.
     */
    public MainWindow() {
    }

    /**
     * Welcome Message for Zirnitra.
     */
    public void welcomeMessage() {
        String welcomeText = printResponse("help");
        vB.getChildren().add(DialogBox.getDukeDialog(welcomeText, dukeImage));
    }

    /**
     * Initializes ZirnitraGui and ensures scroll pane automatically scrolls
     * down to the bottom when new messages are added.
     */
    @FXML
    public void initialize() {
        vB.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    /**
     * Sets Zirnitra instance.
     * Displays welcomeMessage for Zirnitra
     * Ensures pressing ENTER key leads to handling user inputs
     *
     * @param z Zirnitra instance to be set.
     */
    public void setZirnitra(Zirnitra z) {
        this.zirnitra = z;
        welcomeMessage();

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