package Main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import HibernateUtil.HibernateUtil;

public class Main extends Application{

        @Override
        public void start(Stage primaryStage) throws IOException {
            Parent loader = FXMLLoader.load(getClass().getResource("/FxmlFiles/Login.fxml"));
            primaryStage.setScene(new Scene(loader));
            primaryStage.show();

    }
}
