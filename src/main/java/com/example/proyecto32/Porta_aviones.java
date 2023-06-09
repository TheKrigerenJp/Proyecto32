package com.example.proyecto32;


import java.util.ArrayList;
import java.util.List;

public class Porta_aviones extends Lugar {

    private String name;
    private int capHang;
    private List<Avion> aviones_en_espe;
    private int combustibleDisponible;

    public Portaavion(String name, int capHang, double latitud, double longitud) {
        super(latitud, longitud);
        this.name = name;
        this.capHang = capHang;
        this.aviones_en_espe = new ArrayList<>();
        this.combustibleDisponible = 0;
    }

    @Override
    public void recibir_aviones(Avion avion) {
        if (aviones_en_espe.size() < capHang) {
            aviones_en_espe.add(avion);
            System.out.println("Avión " + avion + " recibido en el portaavión " + name);
        } else {
            System.out.println("Portaavión " + name + " NO hay campo en los hangares" + avion);
        }
    }

    public double getCapHang() {
        return capHang;
    }

    @Override
    public Avion tramitar_avion(Avion avion) {
        if (!aviones_en_espe.isEmpty()) {
            boolean removed = aviones_en_espe.remove(avion);
            if (removed) {
                System.out.println("Avión " + avion + " enviando desde el portaavión " + name);
                return avion;
            } else {
                System.out.println("El avión " + avion + " no está esperando en el portaavión " + name);
                return null;
            }
        } else {
            System.out.println("No hay aviones esperando en el portaavión " + name);
            return null;
        }
    }

    public double getLatitud() {return latitud;}
    @Override
    public List<Avion> getaviones_en_espe() {
        return aviones_en_espe;
    }

    public String getName() {
        return name;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

}