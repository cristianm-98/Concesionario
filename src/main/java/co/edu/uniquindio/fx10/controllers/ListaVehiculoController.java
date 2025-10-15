package co.edu.uniquindio.fx10.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.fx10.App;
import co.edu.uniquindio.fx10.models.Vehiculo;
import co.edu.uniquindio.fx10.repository.VehiculoRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

public class ListaVehiculoController {

    private VehiculoRepository vehiculoRepository;
    private DashboardController dashboardController;
    private VBox contenedorPrincipal;
    private ObservableList<Vehiculo> listaVehiculos;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnCancelar;

    @FXML
    private TableColumn<Vehiculo, String> colPlaca;

    @FXML
    private TableColumn<Vehiculo, String> colMarca;

    @FXML
    private TableColumn<Vehiculo,String > colModelo;

    @FXML
    private TableColumn<Vehiculo,String > colTipo;

    @FXML
    private TableView<Vehiculo> tablaVehiculo;

    @FXML
    void onEliminar(ActionEvent event) {
        Vehiculo vehiculoSeleccionado = tablaVehiculo.getSelectionModel().getSelectedItem();

        if (vehiculoSeleccionado == null) {
            mostrarAlerta("Advertencia", "Por favor seleccione un producto para eliminar", Alert.AlertType.WARNING);
            return;
        }

        Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
        confirmacion.setTitle("Confirmar eliminación");
        confirmacion.setHeaderText("¿Está seguro de eliminar el producto?");
        confirmacion.setContentText("Producto: " + vehiculoSeleccionado.getPlaca());

        confirmacion.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                vehiculoRepository.eliminarVehiculos(vehiculoSeleccionado);
                cargarVehiculos();
                mostrarAlerta("Éxito", "Producto eliminado correctamente", Alert.AlertType.INFORMATION);
            }
        });
    }

    @FXML
    void onCancelar(ActionEvent event) {
        volverAlDashboard();

    }

    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    @FXML
    void initialize() {
        vehiculoRepository = VehiculoRepository.getInstancia();

        // Configurar las columnas de la tabla
        colPlaca.setCellValueFactory(new PropertyValueFactory<>("placa"));
        colMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
        colModelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));
        colTipo.setCellValueFactory(new PropertyValueFactory<>("esElectrica"));

        // Cargar los productos
        cargarVehiculos();
    }

    /**
      *Carga los productos en la tabla
     */
    public void cargarVehiculos() {
        listaVehiculos=FXCollections.observableArrayList(VehiculoRepository.getInstancia().getVehiculos());
        tablaVehiculo.setItems(listaVehiculos);
    }
    /**
     * Vuelve a mostrar el dashboard
     */
    private void volverAlDashboard() {
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource("/co/edu/uniquindio/fx10/vista/Dashboard.fxml"));
            Parent dashboard = loader.load();

            contenedorPrincipal.getChildren().clear();
            contenedorPrincipal.getChildren().add(dashboard);

        } catch (IOException e) {
            mostrarAlerta("Error", "No se pudo volver al dashboard", Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }
    /**
     * Establece el controlador del dashboard para poder regresar
     */
    public void setDashboardController(DashboardController dashboardController) {
        this.dashboardController = dashboardController;
        this.contenedorPrincipal = dashboardController.getContenedorPrincipal();
    }

}
