package com.example.proyecto32;

public class Ruta {
    private double peligro;
    private Lugar despegue;
    private Lugar llegada;

    public Ruta(Lugar salida, Lugar destino, double peligro) {
        this.despegue = salida;
        this.llegada = destino;
        this.peligro = peligro;
    }

    public double Distancia() {
        double latitud1 = Math.toRadians(despegue.getLatitude());
        double longitud1 = Math.toRadians(despegue.getLongitude());
        double latitud2 = Math.toRadians(llegada.getLatitude());
        double longitud2 = Math.toRadians(llegada.getLongitude());

        double dlon = longitud2 - longitud1;
        double dlat = latitud2 - latitud1;

        double a = Math.pow(Math.sin(dlat / 2), 2) + Math.cos(latitud1) * Math.cos(latitud2) * Math.pow(Math.sin(dlon / 2), 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        // Radio de la Tierra en kilómetros
        double r = 1000;

        double distancia = r * c;
        return distancia;
    }

    public double Peso() {
        double distancia = Distancia();
        double peso = distancia;

        if (llegada instanceof Porta_aviones) {
            peso *= 1.2; // Aumenta el peso en un 20% si el destino es un portaaviones
        } else {
            peso *= 1; // Aumenta el peso en un 0% si el destino es un aeropuerto
        }

        peso += peligro; // Añade el valor del peligro a la distancia

        return peso;
    }

    // Getters y setters

    public Lugar getDespegue() {
        return despegue;
    }

    public void setDespegue(Lugar salida) {
        this.despegue = despegue;
    }

    public Lugar getLlegada() {
        return llegada;
    }

    public void setLlegada(Lugar destino) {
        this.llegada = llegada;
    }

    public double getPeligro() {
        return peligro;
    }

    public void setPeligro(double peligro) {
        this.peligro = peligro;
    }
}
