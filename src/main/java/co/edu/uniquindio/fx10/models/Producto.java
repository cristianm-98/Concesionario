package co.edu.uniquindio.fx10.models;

/**
 * Clase que representa un producto
 */
public class Producto {
    private String code;
    private String name;
    private String description;
    private double price;
    private int stock;

    public Producto() {
    }

    public Producto(String codigo, String nombre, String descripcion, double precio, int stock) {
        this.code = codigo;
        this.name = nombre;
        this.description = descripcion;
        this.price = precio;
        this.stock = stock;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "codigo='" + code + '\'' +
                ", nombre='" + name + '\'' +
                ", descripcion='" + description + '\'' +
                ", precio=" + price +
                ", stock=" + stock +
                '}';
    }
}

