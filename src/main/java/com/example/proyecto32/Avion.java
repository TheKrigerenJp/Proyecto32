package com.example.proyecto32;

public class Avion {
    private String name;
    private int speed;
    private int eficiencia;
    private int fortaleza;
    private String ubicacionActual;
    private EstadoAvion estate;
    private int combustible;

    public Avion(String name, int speed, int eficiencia, int fortaleza) {
        this.name = name;
        this.speed = speed;
        this.eficiencia = eficiencia;
        this.fortaleza = fortaleza;
        this.estate = EstadoAvion.EN_ESPERA;
        this.combustible = 0;
    }


    public String getUbicacionActual() {
        return ubicacionActual;
    }

    public void setUbicacionActual(String ubicacionActual) {
        this.ubicacionActual = ubicacionActual;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFortaleza() {
        return fortaleza;
    }

    public void setFortaleza(int fortaleza) {
        this.fortaleza = fortaleza;
    }

    public EstadoAvion getEstate() {
        return estate;
    }

    public void setEstate(EstadoAvion estate) {
        this.estate = estate;
    }

    public int getEficiencia() {
        return eficiencia;
    }

    public void setEficiencia(int eficiencia) {
        this.eficiencia = eficiencia;
    }

    public int getCombustible() {
        return combustible;
    }

    public void setCombustible(int combustible) {
        this.combustible = combustible;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }


    public void despegar(String destino) {
        ubicacionActual = destino;
        estate = EstadoAvion.EN_VUELO;
        System.out.println("Avión " + name + " ha despegado hacia " + destino);
    }

    public void aterrizar() {
        estate = EstadoAvion.EN_ESPERA;
        System.out.println("Avión " + name + " ha aterrizado en " + ubicacionActual);
    }

    public void actualizarUbicacion(String nuevaUbicacion) {
        ubicacionActual = nuevaUbicacion;
    }

    public void gestionarCombustible(int cantidad) {
        combustible += cantidad;
        System.out.println("Avión " + name + " ha recargado " + cantidad + " unidades de combustible");
    }



    // Otros métodos y getters/setters según sea necesario

    private enum EstadoAvion {
        EN_ESPERA,
        EN_VUELO
    }
}
