package co.edu.uniquindio.fx10.models;

public class Moto extends Vehiculo {

    private String esElectrica;

    private Moto(Builder builder) {
        super(builder.placa, builder.marca, builder.modelo);
        this.esElectrica= builder.esElectrica;
    }
    public static class Builder{
        private String placa;
        private String marca;
        private String modelo;
        private String esElectrica;

        public Builder placa(String placa){
            this.placa=placa;
            return this;
        }
        public Builder marca(String marca){
            this.marca=marca;
            return this;
        }

        public Builder modelo(String modelo){
            this.modelo=modelo;
            return this;
        }
        public Builder esElectrica(String esElectrica){
            this.esElectrica=esElectrica;
            return this;
        }

        public Moto build(){
            return new Moto(this);
        }
    }

    public String getEsElectrica() {
        return esElectrica;
    }

    public void setEsElectrica(String esElectrica) {
        this.esElectrica = esElectrica;
    }
}
