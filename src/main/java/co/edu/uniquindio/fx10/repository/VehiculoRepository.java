package co.edu.uniquindio.fx10.repository;

import co.edu.uniquindio.fx10.models.Moto;
import co.edu.uniquindio.fx10.models.Vehiculo;
import java.util.ArrayList;

/**
 * Repositorio centralizado para gestionar los productos
 * Singleton para garantizar una única instancia en toda la aplicación
 */
public class VehiculoRepository {
    private static VehiculoRepository instancia;
    private ArrayList<Vehiculo> vehiculos;

    private VehiculoRepository() {
        vehiculos = new ArrayList<>();
        cargarDatosEjemplo();
    }

    /**
     * Obtiene la instancia única del repositorio
     */
    public static VehiculoRepository getInstancia() {
        if (instancia == null) {
            instancia = new VehiculoRepository();
        }
        return instancia;
    }

    /**
     * Carga algunos productos de ejemplo
     */
    private void cargarDatosEjemplo() {

        vehiculos.add(new Moto.Builder().placa("ZDA98F")
                .marca("YAMAHA")
                .modelo("2015")
                .esElectrica("SI")
                .build());
        vehiculos.add(new Moto.Builder().placa("TRZ25A")
                .marca("SUZUKI")
                .modelo("2021")
                .esElectrica("NO")
                .build());
        vehiculos.add(new Moto.Builder().placa("EGI71E")
                .marca("HONDA")
                .modelo("2020")
                .esElectrica("SI")
                .build());
    }

    /**
     * Obtiene todos los productos
     */
    public ArrayList<Vehiculo> getVehiculos() {
        return vehiculos;
    }

    public void setVehiculos(ArrayList<Vehiculo> vehiculos) {
        this.vehiculos = vehiculos;
    }

    /**
     * Agrega un nuevo producto
     */
    public void agregarVehiculos(Vehiculo vehiculo) {
        vehiculos.add(vehiculo);
    }

    /**
     * Elimina un producto
     */
    public boolean eliminarVehiculos(Vehiculo vehiculo) {
        return vehiculos.remove(vehiculo);
    }

    /**
     * Busca un producto por código
     */
    public Vehiculo buscarPorPlaca(String placa) {
        return vehiculos.stream()
                .filter(p -> p.getPlaca().equals(placa))
                .findFirst()
                .orElse(null);
    }

    /**
     * Obtiene la cantidad de productos
     */
    public int getCantidadProductos() {
        return vehiculos.size();
    }
}

