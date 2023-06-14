package com.example.proyecto32;


import java.util.ArrayList;
import java.util.List;

/**
 * se defina la clase con sus respectivas variable a utilizar
 */
public class Porta_aviones extends Lugar {

    private String name;
    private int capHang;
    private List<Avion> aviones_en_espe;
    private int combustibleDisponible;

    public Porta_aviones(String name, int capHang, double latitud, double longitud) {
        super(latitud, longitud);
        this.name = name;
        this.capHang = capHang;
        this.aviones_en_espe = new ArrayList<>();
        this.combustibleDisponible = 0;
    }

    /**
     * Recibe objetos de la clase avion y permite añadorlos a los hangares, si hay esapcio en estos
     * @param avion
     */
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

    /**
     * Tramites de aviones, con los abjetos de la clase avion
     * @param avion
     * @return
     */
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

    public double getLatitude() {return latitud;}

    /**
     * setters y getters
     * @return
     */
    @Override
    public List<Avion> getAviones_en_espe() {
        return aviones_en_espe;
    }

    public String getName() {
        return name;
    }

    public void setLatitude(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitude() {
        return longitud;
    }

    public void setLongitude(double longitud) {
        this.longitud = longitud;
    }

}

