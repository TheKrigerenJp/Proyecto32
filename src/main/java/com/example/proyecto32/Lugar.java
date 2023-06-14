package com.example.proyecto32;

import java.util.List;

public abstract class Lugar {

    double latitud;
    double longitud;

    public Lugar(double latitud, double longitud) {
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public double getLatitude() {
        return latitud;
    }

    public void setLatitude(double latitude) {
        this.latitud = latitude;
    }

    public double getLongitude() {
        return longitud;
    }

    public void setLongitude(double longitude) {
        this.longitud = longitude;
    }

    public double getX() {
        double longitudeRange = 180.0;
        return (longitud + longitudeRange / 2.0) / longitudeRange * 1280;
    }

    public double getY() {
        double latitudeRange = 90.0;
        return (latitud + latitudeRange / 2.0) / latitudeRange * 720;
    }

    public abstract void recibir_aviones(Avion avion);

    public abstract Avion tramitar_avion(Avion avion);

    public abstract List<Avion> getAviones_en_espe();
    public abstract String getName();
}
