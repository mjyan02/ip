package duke.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

/**
 * A customised DialogBox using JavaFX for displaying chat messages in Duke.
 */
public class DialogBox extends HBox {

    public DialogBox(String message, Image img) {
        Label text = new Label(message);
        ImageView displayPicture = new ImageView(img);

        text.setWrapText(true);
        text.setPadding(new Insets(8));
        text.setStyle("-fx-background-color: #40444b; -fx-text-fill: white; -fx-background-radius: 10px;");
        text.setMinHeight(Region.USE_PREF_SIZE);

        displayPicture.setFitWidth(50);
        displayPicture.setFitHeight(50);

        this.setSpacing(10);
        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(text, displayPicture);
    }

    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    public static DialogBox getUserDialog(String message, Image img) {
        return new DialogBox(message, img);
    }

    public static DialogBox getDukeDialog(String message, Image img) {
        var db = new DialogBox(message, img);
        db.flip();
        return db;
    }
}