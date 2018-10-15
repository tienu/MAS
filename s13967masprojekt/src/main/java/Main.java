import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class Main extends Application {



    public void start(Stage primaryStage) throws SQLException, IOException {
        /*
        String urldata = "jdbc:sqlite:database.db" ;

        ConnectionSource connectionSource =
                new JdbcConnectionSource(urldata);
        */


        primaryStage.setTitle("Main window");
        primaryStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("MainView.fxml"))));
        primaryStage.show();



    }
}
