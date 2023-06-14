package com.example.proyecto32;

import java.util.ArrayList;
import java.util.List;

public class AeropuertoV2 extends Lugar {

    private String name;
    private int capHang;
    private List<Avion> aviones_en_espe;
    private int combustibleDispo;

    public AeropuertoV2(String nombre, int capHang, double latitud, double longitud) {
        super(latitud, longitud);
        this.name = nombre;
        this.capHang = capHang;
        this.aviones_en_espe = new ArrayList<>();
        this.combustibleDispo = 0;
    }
    @Override
    public void recibir_aviones(Avion avion) {
        if (aviones_en_espe.size() < capHang) {
            aviones_en_espe.add(avion);
            System.out.println("Avión " + avion + " recibido en el aeropuerto " + name);
        } else {
            System.out.println("Aeropuerto " + name + " sin espacio en los hangares. No se puede recibir el avión " + avion);
        }
    }

    @Override
    public Avion tramitar_avion(Avion avion) {
        if (!aviones_en_espe.isEmpty()) {
            boolean removed = aviones_en_espe.remove(avion);
            if (removed) {
                System.out.println("Avión " + avion + " despachado desde el aeropuerto " + name);
                return avion;
            } else {
                System.out.println("El avión " + avion + " no está esperando en el aeropuerto " + name);
                return null;
            }
        } else {
            System.out.println("No hay aviones esperando en el aeropuerto " + name);
            return null;
        }
    }
    @Override
    public List<Avion> getAviones_en_espe() {
        return aviones_en_espe;
    }
    // Otros métodos y getters/setters según sea necesario
    public double getLatitude() {
        return latitud;
    }

    public double getCapHang() {
        return capHang;
    }

    public String getName() {
        return name;
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

}
