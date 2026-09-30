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
                if (dialogs.getChildren().size() != 1) {
                    throw new AssertionError("Sample dialog is missing");
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
