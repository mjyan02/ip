package zirnitra.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.util.Collections;

/**
 * A custom dialog box used in the Zirnitra GUI.
 * It contains an ImageView to represent Zirnitra and a Label containing the user's text.
 */
public class DialogBox extends HBox {
    private static final String FXML_PATH = "/view/DialogBox.fxml";
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Creates a DialogBox instance.
     *
     * @param text The message text.
     * @param img  The image of the speaker.
     */
    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class
                    .getResource(FXML_PATH));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.getMessage();
        }

        assert dialog != null : "dialog cannot be null after loading FXML!";
        assert displayPicture != null : "displayPicture cannot be null after loading FXML!";

        dialog.setWrapText(true);
        dialog.setText(text);
        displayPicture.setImage(img);
    }

    /**
     * Flips the dialog box so that the image is on the left.
     * This is used for Zirnitra's responses.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    /**
     * Returns a dialog box for the user.
     *
     * @param text The message text.
     * @param img  The user's image.
     * @return A user dialog box.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    /**
     * Returns a dialog box for Zirnitra.
     *
     * @param text The message text.
     * @param img  Zirnitra's image.
     * @return A Zirnitra dialog box.
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }
}