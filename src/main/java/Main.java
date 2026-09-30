import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/** Part 3: sends messages and displays Duke replies. */
public class Main extends Application {
    private final VBox dialogContainer = new VBox();
    private final TextField userInput = new TextField();
    private final Button sendButton = new Button("Send");
    private final ScrollPane scrollPane = new ScrollPane(dialogContainer);
    private final Image userImage = new Image(getClass().getResourceAsStream("/images/DaUser.png"));

    private final Image dukeImage = new Image(getClass().getResourceAsStream("/images/DaDuke.png"));
    private final Duke duke = new Duke();

    @Override
    public void start(Stage stage) {
        dialogContainer.setId("dialogContainer");
        userInput.setId("userInput");
        sendButton.setId("sendButton");
        scrollPane.setId("scrollPane");
        sendButton.setOnAction(event -> handleUserInput());
        userInput.setOnAction(event -> handleUserInput());
        dialogContainer.heightProperty().addListener(observable -> scrollPane.setVvalue(1.0));
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

    private void handleUserInput() {
        String input = userInput.getText();
        dialogContainer.getChildren().addAll(DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(duke.getResponse(input), dukeImage));
        userInput.clear();
    }
}
