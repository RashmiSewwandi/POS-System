import animatefx.animation.FadeIn;
import animatefx.animation.FadeInLeft;
import animatefx.animation.Jello;
import animatefx.animation.RollIn;
import com.mysql.cj.Session;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import util.FactoryConfiguration;

import java.io.IOException;

public class AppInitalizer extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("views/MainForm.fxml"));
        primaryStage.setTitle("Store Market (v-1.0.0)");
        primaryStage.initStyle(StageStyle.DECORATED);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        new RollIn(root).play();

    }


}
