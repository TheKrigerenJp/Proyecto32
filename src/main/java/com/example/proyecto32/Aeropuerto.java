package com.example.proyecto32;

import java.util.ArrayList;
import java.util.List;

public class Aeropuerto {
    private int cantidadHang;
    private List<Hangar> hangares;
    private double coordsX;
    private double coordsY;

    public Aeropuerto(int cantidadHang, double coordsX, double coordsY) {
        this.cantidadHang = cantidadHang;
        this.coordsX = coordsX;
        this.coordsY = coordsY;
        this.hangares = new ArrayList<>();

        for (int i = 0; i < cantidadHang; i++) {
            Hangar hangar = new Hangar();
            hangares.add(hangar);
        }
    }

    public int getCantidadHang() {
        return cantidadHang;
    }

    public void setCantidadHang(int cantidadHang) {
        this.cantidadHang = cantidadHang;
    }

    public List<Hangar> getHangares() {
        return hangares;
    }

    public void setHangares(List<Hangar> hangares) {
        this.hangares = hangares;
    }

    public double getCoordsX() {
        return coordsX;
    }

    public void setCoordsX(double coordenadaX) {
        this.coordsX = coordenadaX;
    }

    public double getCoordsY() {
        return coordsY;
    }

    public void setCoordsY(double coordsY) {
        this.coordsY = coordsY;
    }

    public static class Hangar {
        private int cantidad_Aviones;

        public Hangar() {
            this.cantidad_Aviones = 0;
        }

        public int getCantidad_Aviones() {
            return cantidad_Aviones;
        }

        public void setCantidad_Aviones(int cantidad_Aviones) {
            this.cantidad_Aviones = cantidad_Aviones;
        }
    }
}

