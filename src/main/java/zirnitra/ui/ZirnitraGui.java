package zirnitra.ui;

import zirnitra.Zirnitra;

import zirnitra.exceptions.ZirnitraException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Objects;

/**
 * GUI interface using JavaFX for Zirnitra.
 */
public class ZirnitraGui extends Application {

    private ScrollPane scrollPane;
    private VBox vB;
    private TextField userInput;
    private final Zirnitra zirnitra = new Zirnitra("C:/Users/ymj53/OneDrive/Documents/Uni/Y2S2/CS2103T/data/dukegui.txt");

    private final Image userImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/User.jpg")));
    private final Image dukeImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/Zirnitra.jpg")));

    public ZirnitraGui() throws ZirnitraException {
    }

    /**
     * Setup function for the GUI
     */
    @Override
    public void start(Stage stage) {
        scrollPane = new ScrollPane();
        vB = new VBox();
        scrollPane.setContent(vB);
        scrollPane.setStyle("-fx-background: #D0E8FF; -fx-border-color: transparent;");
        vB.setStyle("-fx-background-color: #D0E8FF;");

        userInput = new TextField();
        userInput.setPromptText("Message Duke...");
        Button sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);
        Scene scene = new Scene(mainLayout, 510, 700);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getClassLoader()
                .getResource("styles/styles.css")).toExternalForm());

        stage.setTitle("zirnitra");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();

        stage.setMinHeight(750.0);
        stage.setMinWidth(520.0);
        stage.setMaxHeight(750.0);
        stage.setMaxWidth(520.0);

        String welcomeText = printResponse("help");
        vB.getChildren().add(DialogBox.getDukeDialog(welcomeText, dukeImage));
        vB.setPrefSize(485, 500);
        AnchorPane.setTopAnchor(scrollPane, 0.0);
        AnchorPane.setBottomAnchor(scrollPane, 50.0);

        scrollPane.setPrefSize(510, 620);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        userInput.setPrefWidth(380);
        userInput.setStyle("-fx-background-color: #A5C7E6; -fx-text-fill: black;");
        sendButton.setPrefWidth(80);
        sendButton.setStyle("-fx-background-color: #2C7A7B; -fx-text-fill: white;");

        AnchorPane.setBottomAnchor(userInput, 13.0);
        AnchorPane.setLeftAnchor(userInput, 15.0);
        AnchorPane.setBottomAnchor(sendButton, 13.0);
        AnchorPane.setRightAnchor(sendButton, 15.0);

        vB.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        sendButton.setOnAction(event -> handleInput());
        userInput.setOnAction(event -> handleInput());
    }

    /**
     * Handles user inputs and redirects it to Zirnitra GUI.
     */
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