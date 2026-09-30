import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/** Runs a real JavaFX startup check and closes the window automatically. */
public class TutorialSmokeTest {
    public static void main(String[] args) throws Exception {
        CountDownLatch finished = new CountDownLatch(1);
        AtomicReference<Throwable> failure = new AtomicReference<>();
        Platform.startup(() -> { });
        Platform.runLater(() -> {
            Stage stage = new Stage();
            try {
                new Main().start(stage);
                if (!stage.isShowing() || stage.getScene().lookup("#userInput") == null
                        || stage.getScene().lookup("#sendButton") == null) {
                    throw new AssertionError("Chat controls are missing");
                }
                javafx.scene.layout.VBox dialogs = (javafx.scene.layout.VBox)
                        stage.getScene().lookup("#dialogContainer");
                javafx.scene.control.TextField input = (javafx.scene.control.TextField)
                        stage.getScene().lookup("#userInput");
                javafx.scene.control.Button send = (javafx.scene.control.Button)
                        stage.getScene().lookup("#sendButton");
                input.setText("Hello Duke");
                send.fire();
                input.setText("Second message");
                input.fireEvent(new javafx.event.ActionEvent());
                if (dialogs.getChildren().size() != 4 || !input.getText().isEmpty()) {
                    throw new AssertionError("Send and Enter must each append two dialogs and clear input");
                }
                javafx.scene.layout.HBox reply = (javafx.scene.layout.HBox) dialogs.getChildren().get(3);
                if (!(reply.getChildren().get(0) instanceof javafx.scene.image.ImageView)
                        || !(reply.getChildren().get(1) instanceof Label label)
                        || !label.getText().equals("Duke heard: Second message")) {
                    throw new AssertionError("Duke reply must be flipped and contain the response");
                }
                String[] commands = {"todo read", "deadline work", "event meeting", "mark 1", "delete 1", "hello"};
                String[] styles = {"add-label", "add-label", "add-label", "marked-label", "delete-label", ""};
                for (int i = 0; i < commands.length; i++) {
                    input.setText(commands[i]);
                    send.fire();
                    javafx.scene.layout.HBox bubble = (javafx.scene.layout.HBox)
                            dialogs.getChildren().get(dialogs.getChildren().size() - 1);
                    Label response = (Label) bubble.getChildren().get(1);
                    if (!styles[i].isEmpty() && !response.getStyleClass().contains(styles[i])) {
                        throw new AssertionError("Missing command style: " + styles[i]);
                    }
                    if (styles[i].isEmpty() && response.getStyleClass().stream()
                            .anyMatch(style -> style.equals("add-label") || style.equals("marked-label")
                                    || style.equals("delete-label"))) {
                        throw new AssertionError("Command style leaked into a regular reply");
                    }
                }
                javafx.scene.control.ScrollPane scroll = (javafx.scene.control.ScrollPane)
                        stage.getScene().lookup("#scrollPane");
                if (!scroll.isFitToWidth() || stage.getMinWidth() < 400 || stage.getMinHeight() < 200) {
                    throw new AssertionError("Resize constraints are missing");
                }
                stage.setWidth(650);
                stage.setHeight(750);
                stage.getScene().getRoot().applyCss();
                stage.getScene().getRoot().layout();
                if (scroll.getViewportBounds().getWidth() <= 0) {
                    throw new AssertionError("Scroll viewport is not laid out");
                }
            } catch (Throwable error) {
                failure.set(error);
            } finally {
                stage.close();
                finished.countDown();
            }
        });
        if (!finished.await(20, TimeUnit.SECONDS)) {
            throw new AssertionError("JavaFX startup timed out");
        }
        Platform.exit();
        if (failure.get() != null) {
            throw new AssertionError("JavaFX smoke test failed", failure.get());
        }
        System.out.println("JavaFX smoke test passed");
    }
}
