package com.example.proyecto32;

public class ShellSort {
    public static void main(String[] args) {
        Avion[] aviones = {
                new Avion("Avion1", 500, 80, 200),
                new Avion("Avion2", 300, 70, 150),
                new Avion("Avion3", 700, 90, 250),
                new Avion("Avion4", 400, 60, 180),
                new Avion("Avion5", 600, 85, 220)
        };

        System.out.println("Lista de Aviones sin ordenar:");
        imprimirAviones(aviones);

        shellSort(aviones);

        System.out.println("\nLista de Aviones ordenada por eficiencia:");
        imprimirAviones(aviones);
    }

    public static void shellSort(Avion[] aviones) {
        int n = aviones.length;
        int gap = n / 2;

        while (gap > 0) {
            for (int i = gap; i < n; i++) {
                Avion temp = aviones[i];
                int j = i;

                while (j >= gap && aviones[j - gap].getEficiencia() > temp.getEficiencia()) {
                    aviones[j] = aviones[j - gap];
                    j -= gap;
                }

                aviones[j] = temp;
            }

            gap /= 2;
        }
    }

    public static void imprimirAviones(Avion[] aviones) {
        for (Avion avion : aviones) {
            System.out.println(avion.getName() + " - Eficiencia: " + avion.getEficiencia());
        }
    }
}
