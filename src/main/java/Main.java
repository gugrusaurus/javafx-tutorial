import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/** Part 1: a minimal JavaFX application. */
public class Main extends Application {
    @Override
    public void start(Stage stage) {
        stage.setTitle("Hello World");
        stage.setScene(new Scene(new Label("Hello World!"), 400, 200));
        stage.show();
    }
}
