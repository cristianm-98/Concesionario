package co.edu.uniquindio.fx10.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import co.edu.uniquindio.fx10.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

    public class DashboardController {

        @FXML
        private ResourceBundle resources;

        @FXML
        private URL location;

        @FXML
        private Button btnFormularioProducto;

        @FXML
        private Button btnListaProducto;

        @FXML
        private VBox contenedorPrincipal;

        @FXML
        private Label lblTitulo;

        @FXML
        void onFormularioProducto(ActionEvent event) {
            try {
                FXMLLoader loader = new FXMLLoader(App.class.getResource("/co/edu/uniquindio/fx10/vista/FormularioProducto.fxml"));
                Parent formulario = loader.load();

                // Obtener el controlador del formulario
                FormularioProductoController controller = loader.getController();
                controller.setDashboardController(this);

                // Reemplazar el contenido del contenedor principal
                contenedorPrincipal.getChildren().clear();
                contenedorPrincipal.getChildren().add(formulario);

            } catch (IOException e) {
                mostrarAlerta("Error", "No se pudo cargar el formulario", Alert.AlertType.ERROR);
                e.printStackTrace();
            }
        }

        private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
            Alert alerta = new Alert(tipo);
            alerta.setTitle(titulo);
            alerta.setHeaderText(null);
            alerta.setContentText(mensaje);
            alerta.showAndWait();
        }

        @FXML
        void onListaProducto(ActionEvent event) {
            try {
                FXMLLoader loader = new FXMLLoader(App.class.getResource("/co/edu/uniquindio/fx10/vista/ListaProducto.fxml"));
                Parent listaProducto = loader.load();

                // Obtener el controlador del formulario
                ListaProductoController controller= loader.getController();
                controller.setDashboardController(this);

                // Reemplazar el contenido del contenedor principal
                contenedorPrincipal.getChildren().clear();
                contenedorPrincipal.getChildren().add(listaProducto);

            } catch (IOException e) {
                mostrarAlerta("Error", "No se pudo cargar el formulario", Alert.AlertType.ERROR);
                e.printStackTrace();
            }
        }

        @FXML
        void initialize() {
        }
        public VBox getContenedorPrincipal() {
            return contenedorPrincipal;
        }


    }
