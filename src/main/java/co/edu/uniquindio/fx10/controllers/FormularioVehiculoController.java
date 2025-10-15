package co.edu.uniquindio.fx10.controllers;

import co.edu.uniquindio.fx10.App;
import co.edu.uniquindio.fx10.models.Moto;
import co.edu.uniquindio.fx10.models.Vehiculo;
import co.edu.uniquindio.fx10.repository.VehiculoRepository;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.IOException;

/**
 * Controlador para el formulario de creación de productos
 */
public class FormularioVehiculoController {

    @FXML
    private TextField txtPlaca;

    @FXML
    private TextField txtMarca;

    @FXML
    private TextField txtModelo;

    @FXML
    private TextField txtTipo;

    @FXML
    private Button btnGuardar;

    @FXML
    private Button btnCancelar;

    private VehiculoRepository vehiculoRepository;
    private DashboardController dashboardController;
    private VBox contenedorPrincipal;

    /**
     * Maneja el evento de guardar producto
     */
    @FXML
    private void onGuardarProducto() {
        if (!validarCampos()) {
            return;
        }

        try {
            String placa = txtPlaca.getText().trim();
            String marca = txtMarca.getText().trim();
            String modelo = txtModelo.getText().trim();
            String tipo=txtTipo.getText().trim();

            // Verificar si el código ya existe
            if (vehiculoRepository.buscarPorPlaca(placa) != null) {
                mostrarAlerta("Error", "Ya existe un producto con ese código", Alert.AlertType.ERROR);
                return;
            }

            // Crear y guardar el producto
            Moto nuevaMoto = new Moto.Builder().placa(placa)
                    .marca(marca)
                    .modelo(modelo)
                    .esElectrica(tipo)
                    .build();
            vehiculoRepository.agregarVehiculos(nuevaMoto);

            mostrarAlerta("Éxito", "Producto creado correctamente", Alert.AlertType.INFORMATION);

            // Volver al dashboard
            volverAlDashboard();

        } catch (NumberFormatException e) {
            mostrarAlerta("Error", "El precio y stock deben ser valores numéricos válidos", Alert.AlertType.ERROR);
        }
    }

    /**
     * Maneja el evento de cancelar
     */
    @FXML
    private void onCancelar() {
        volverAlDashboard();
    }

    @FXML
    public void initialize() {
        vehiculoRepository = VehiculoRepository.getInstancia();
    }

    /**
     * Establece el controlador del dashboard para poder regresar
     */
    public void setDashboardController(DashboardController dashboardController) {
        this.dashboardController = dashboardController;
        this.contenedorPrincipal = dashboardController.getContenedorPrincipal();
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
     * Valida que los campos del formulario estén completos
     */

    private boolean validarCampos() {
        if (txtPlaca.getText().trim().isEmpty()) {
            mostrarAlerta("Error de validación", "La placa es obligatorio", Alert.AlertType.WARNING);
            return false;
        }
        if (txtMarca.getText().trim().isEmpty()) {
            mostrarAlerta("Error de validación", "La marca es obligatorio", Alert.AlertType.WARNING);
            return false;
        }
        if (txtModelo.getText().trim().isEmpty()) {
            mostrarAlerta("Error de validación", "El modelo es obligatoria", Alert.AlertType.WARNING);
            return false;
        }
        if (txtTipo.getText().trim().isEmpty()) {
            mostrarAlerta("Error de validación", "El tipo es obligatoria", Alert.AlertType.WARNING);
            return false;
        }
        return true;
    }

    /**
     * Muestra una alerta al usuario
     */

    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}

