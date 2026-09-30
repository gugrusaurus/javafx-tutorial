import java.util.Collections;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/** A reusable message bubble and speaker avatar. */
public class DialogBox extends HBox {
    public DialogBox(String text, Image image) {
        Label label = new Label(text);
        label.setWrapText(true);
        label.setMinHeight(USE_PREF_SIZE);
        ImageView avatar = new ImageView(image);
        avatar.setFitWidth(100);
        avatar.setFitHeight(100);
        avatar.setPreserveRatio(true);
        setAlignment(Pos.TOP_RIGHT);
        getChildren().addAll(label, avatar);
    }

    public static DialogBox getUserDialog(String text, Image image) {
        return new DialogBox(text, image);
    }

    public static DialogBox getDukeDialog(String text, Image image) {
        DialogBox dialog = new DialogBox(text, image);
        Collections.reverse(dialog.getChildren());
        dialog.setAlignment(Pos.TOP_LEFT);
        return dialog;
    }
}
