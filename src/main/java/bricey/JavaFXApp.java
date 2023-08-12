package bricey;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class JavaFXApp extends Application {
    private Stage mainStage;

    @Override
    public void start(Stage stage) {
        mainStage = stage;

        switchScene(new LoginView(userName -> switchScene(new ItemView(userName))));
    }

    private void switchScene(Viewable view) {
        mainStage.setScene(view.getScene());
        mainStage.show();
    }

}