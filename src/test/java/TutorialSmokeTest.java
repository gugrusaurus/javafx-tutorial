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
                if (!stage.isShowing() || !(stage.getScene().getRoot() instanceof Label label)
                        || !label.getText().equals("Hello World!")) {
                    throw new AssertionError("Hello World window did not open correctly");
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
