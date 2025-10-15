package co.edu.uniquindio.fx10;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

/**
 * Clase principal de la aplicación JavaFX
 */
public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("/co/edu/uniquindio/fx10/vista/Dashboard.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);
        scene.getStylesheets().add((Objects.requireNonNull(getClass().getResource("/co/edu/uniquindio/fx10/vista/style.css"))).toExternalForm());
        primaryStage.setScene(scene);

        primaryStage.setTitle("Sistema de Gestión de Productos");
        primaryStage.setResizable(false);
        primaryStage.setWidth(650);
        primaryStage.setHeight(450);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

