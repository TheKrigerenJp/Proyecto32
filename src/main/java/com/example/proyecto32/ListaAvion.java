package com.example.proyecto32;

import java.io.*;

/**
 * Se crea la clase ListaAvion con sus variables y constructor
 */
public class ListaAvion {
    private Nodo head;
    private int size;

    // Constructor
        public ListaAvion() {
        this.head = null;
        this.size = 0;
    }
    public void obteberLista(String Archivo) {
        try (BufferedReader lector = new BufferedReader(new FileReader("src/archivos/" + Archivo))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                String[] datos = linea.split(",");
                String nombre = datos[0];
                int velocidad = Integer.parseInt(datos[1]);
                double eficiencia = Double.parseDouble(datos[2]);
                int fortaleza = Integer.parseInt(datos[3]);

                Avion avion = new Avion(nombre, velocidad, eficiencia, fortaleza);
                addAvion(avion);
            }
            System.out.println("La lista de aviones cargada correctamente."+ Archivo);
        } catch (IOException e) {
            System.out.println("Error al leer el archivo " + Archivo + ": " + e.getMessage());
        }
    }
    public void agregarLista(ListaAvion listaAviones, String Archivo) {
        try (BufferedWriter escritor = new BufferedWriter(new FileWriter("src/archivos/"+Archivo))) {
            ListaAvion.Nodo nodoActual = listaAviones.head;
            while (nodoActual != null) {
                Avion avion = nodoActual.getAvion();
                String linea = String.format("%s,%d,%.2f,%d",
                        avion.getName(), avion.getVelocidad(), avion.getEficiencia(), avion.getFortaleza());
                escritor.write(linea);
                escritor.newLine();
                nodoActual = nodoActual.getSiguiente();
            }
            System.out.println("La lista de aviones se ha guardado en el archivo " + Archivo + " correctamente.");
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo " + Archivo + ": " + e.getMessage());
        }
    }

    /**
     * Método para agregar un avión al final de la lista
     */

    public void addAvion(Avion avion) {
        Nodo elNodo = new Nodo(avion);

        if (head == null) {
            head = elNodo;
        } else {
            Nodo nodoActual = head;
            while (nodoActual.getSiguiente() != null) {
                nodoActual = nodoActual.getSiguiente();
            }
            nodoActual.setSiguiente(elNodo);
        }
        size++;
    }

    /**
     * Obtiene el avion con un indice
     * @param indice
     * @return
     */
    public Avion obtenerAvion(int indice) {
        if (indice < 0 || indice >= size) {
            // El índice está fuera de rango
            return null;
        }

        Nodo nodoActual = head;
        int cont = 0;

        while (cont < indice) {
            nodoActual = nodoActual.getSiguiente();
            cont++;
        }

        return nodoActual.getAvion();
    }

    /**
     * Algortimo de ordenamiento ShellSort
     */
    public void shellSort() {
        int n = size;
        int gap = n / 2;

        while (gap > 0) {
            for (int i = gap; i < n; i++) {
                Avion temp = obtenerAvion(i);
                int j = i;

                while (j >= gap && obtenerAvion(j - gap).getEficiencia() > temp.getEficiencia()) {
                    setAvion(j, obtenerAvion(j - gap));
                    j -= gap;
                }

                setAvion(j, temp);
            }

            gap /= 2;
        }
    }

    public void setAvion(int indice, Avion avion) {
        if (indice < 0 || indice >= size) {
            return;
        }

        Nodo nodoActual = head;
        int contador = 0;

        while (contador < indice) {
            nodoActual = nodoActual.getSiguiente();
            contador++;
        }

        nodoActual.setAvion(avion);
    }

    public void printAviones() {
        //shellSort();

        Nodo nodoActual = head;
        while (nodoActual != null) {
            System.out.println("Nombre: " + nodoActual.getAvion().getName());
            System.out.println("Velocidad: " + nodoActual.getAvion().getVelocidad());
            System.out.println("Eficiencia: " + nodoActual.getAvion().getEficiencia());
            System.out.println("Fortaleza: " + nodoActual.getAvion().getFortaleza());
            System.out.println("--------------------------");
            nodoActual = nodoActual.getSiguiente();
        }
    }

    /**
     * Clase Nodo a implmentar en la lista enlazada
     */
    public static class Nodo {

        private Avion avion;
        private Nodo siguiente;

        // Constructor
        public Nodo(Avion avion) {
            this.avion = avion;
            this.siguiente = null;
        }

        // Getters y Setters
        public Avion getAvion() {
            return avion;
        }

        public void setAvion(Avion avion) {
            this.avion = avion;
        }

        public Nodo getSiguiente() {
            return siguiente;
        }

        public void setSiguiente(Nodo siguiente) {
            this.siguiente = siguiente;
        }
    }
}

