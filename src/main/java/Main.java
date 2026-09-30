import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/** Part 2: creates the chat layout using JavaFX controls. */
public class Main extends Application {
    private final VBox dialogContainer = new VBox();
    private final TextField userInput = new TextField();
    private final Button sendButton = new Button("Send");
    private final ScrollPane scrollPane = new ScrollPane(dialogContainer);
    private final Image userImage = new Image(getClass().getResourceAsStream("/images/DaUser.png"));

    @Override
    public void start(Stage stage) {
        dialogContainer.setId("dialogContainer");
        userInput.setId("userInput");
        sendButton.setId("sendButton");
        scrollPane.setId("scrollPane");
        dialogContainer.getChildren().add(new DialogBox("Hello!", userImage));
        AnchorPane root = new AnchorPane(scrollPane, userInput, sendButton);
        scrollPane.setPrefSize(385, 535);
        scrollPane.setFitToWidth(true);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        userInput.setPrefWidth(325);
        sendButton.setPrefWidth(55);
        AnchorPane.setTopAnchor(scrollPane, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);
        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);
        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setScene(new Scene(root, 400, 600));
        stage.show();
    }
}
