package com.example.proyecto32;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static javafx.application.Application.launch;

public class view_avion_list {

    private ListView<Avion> view_list;
    private ObservableList<Avion> aviones;
    private List<Lugar> lugares;
    private ComboBox<Lugar> comboBox;
    private ComboBox<String> comboBox_filtro; // Nuevo ComboBox para el filtro

    private Lugar op_seleccionada;

    public view_avion_list() {
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) {
        // Crear la lista de aviones
        aviones = FXCollections.observableArrayList();
        Avion avion1 = new Avion("Boeing 787 Dreamliner", 801, 1200, 1000);
        Avion avion2 = new Avion("Airbus A320neo", 951, 1001, 1000);
        Avion avion3 = new Avion("Sukhoi Su-35", 1000, 1101, 1000);
        Avion avion4 = new Avion("Embraer E-Jet E2", 920, 1050, 1000);
        Avion avion5 = new Avion("Gulfstream G650", 850, 1151, 1000);
        Avion avion6 = new Avion("Antonov An-225 Mriya", 880, 1002, 1000);

        aviones.add(avion1);
        aviones.add(avion2);
        aviones.add(avion3);
        aviones.add(avion4);
        aviones.add(avion5);
        aviones.add(avion6);
        System.out.println(aviones);

        // Crear el ListView
        view_list = new ListView<>(aviones);
        view_list.setPrefWidth(400);
        view_list.setPrefHeight(300);
        view_list.setCellFactory(new Callback<ListView<Avion>, ListCell<Avion>>() {
            @Override
            public ListCell<Avion> call(ListView<Avion> param) {
                return new ListCell<Avion>() {
                    @Override
                    protected void updateItem(Avion avion, boolean empty) {
                        super.updateItem(avion, empty);
                        if (empty || avion == null) {
                            setText(null);
                        } else {
                            String itemText = String.format("%s, %d, %.0f, %d",
                                    avion.getNombre(), avion.getVelocidad(), avion.getEficiencia(), avion.getFortaleza());
                            setText(itemText);
                        }
                    }
                };
            }
        });
        Label lugarLabel = new Label("Selecciona un Lugar");
        lugares = MainMap.Grafo_v2.nodes;
        comboBox = new ComboBox<>();
        comboBox.getItems().addAll(lugares);
        comboBox.setCellFactory(new Callback<ListView<Lugar>, ListCell<Lugar>>() {
            @Override
            public ListCell<Lugar> call(ListView<Lugar> param) {
                return new ListCell<>() {
                    @Override
                    protected void updateItem(Lugar lugar, boolean empty) {
                        super.updateItem(lugar, empty);
                        if (lugar != null) {
                            setText(lugar.getName()); // Mostrar solo el nombre del lugar en el dropdown
                        } else {
                            setText(null);
                        }
                    }
                };
            }
        });
        comboBox.setOnAction(event -> {
            Lugar lugarSeleccionado = comboBox.getValue();
            if (lugarSeleccionado != null) {
                op_seleccionada = lugarSeleccionado;
                // Realiza cualquier acción adicional que desees con el lugar seleccionado
            }
        });

        comboBox_filtro = new ComboBox<>();

        comboBox_filtro.getItems().addAll("Eficiencia", "Velocidad");
        comboBox_filtro.setOnAction(event -> filtrarAviones(comboBox_filtro.getValue()));
        HBox hboxFiltro = new HBox();
        hboxFiltro.setAlignment(Pos.CENTER_RIGHT);

// Otros códigos relacionados con el ComboBox
        hboxFiltro.getChildren().add(comboBox_filtro);

        Button seleccionarBtn = new Button("Seleccionar");
        seleccionarBtn.setAlignment(Pos.CENTER_RIGHT);
        seleccionarBtn.setOnAction(event -> {
            Avion avionSeleccionado = new Avion(
                    view_list.getSelectionModel().getSelectedItem().getNombre(),
                    view_list.getSelectionModel().getSelectedItem().getVelocidad(),
                    view_list.getSelectionModel().getSelectedItem().getEficiencia(),
                    view_list.getSelectionModel().getSelectedItem().getFortaleza());
            if (avionSeleccionado != null) {
                // Llamar al método de creación del objeto en OtraClase
                MainMap.Grafo_v2.recibirAvion(op_seleccionada, avionSeleccionado);
            }
        });

        // Crear el campo de búsqueda
        TextField buscarField = new TextField();
        Button buscarBtn = new Button("Buscar");
        buscarBtn.setOnAction(event -> Avion_x_nombre(buscarField.getText()));

        HBox buscarContainer = new HBox(10);
        buscarContainer.getChildren().addAll(buscarField, buscarBtn, hboxFiltro);
        // Crear el contenedor principal
        VBox root = new VBox(10);
        root.getChildren().addAll(lugarLabel, comboBox, buscarContainer, view_list, seleccionarBtn);

        // Configurar la escena y mostrar la ventana
        Scene scene = new Scene(root, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Lista de Aviones");
        primaryStage.show();
    }

    private void filtrarAviones(String filtro) {
        Comparator<Avion> comparador;
        if (filtro.equalsIgnoreCase("Eficiencia")) {
            comparador = Comparator.comparing(Avion::getEficiencia);
        } else if (filtro.equalsIgnoreCase("Velocidad")) {
            comparador = Comparator.comparing(Avion::getVelocidad);
        } else {
            // Si no se selecciona un filtro válido, no se aplica ningún filtro
            return;
        }

        List<Avion> avionesList = new ArrayList<>(aviones);
        avionesList.sort(comparador);
        aviones.setAll(avionesList);
    }

    private void ordenarPorEficiencia() {
        List<Avion> avionesList = new ArrayList<>(aviones);
        shellSort(avionesList);
        aviones.setAll(avionesList);
    }

    private void ordenarPorVelocidad() {
        List<Avion> avionesList = new ArrayList<>(aviones);
        insertionSort(avionesList);
        aviones.setAll(avionesList);
    }

    private void Avion_x_nombre(String nombre) {
        for (Avion avion : aviones) {
            if (avion.getNombre().equalsIgnoreCase(nombre)) {
                view_list.getSelectionModel().select(avion);
                view_list.scrollTo(avion);
                return;
            }
        }
        view_list.getSelectionModel().clearSelection();
    }

    private void cargarAvionesDesdeArchivo(String archivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length == 4) {
                    String nombre = partes[0];
                    int velocidad = Integer.parseInt(partes[1]);
                    double eficiencia = Double.parseDouble(partes[2]);
                    int fortaleza = Integer.parseInt(partes[3]);
                    Avion avion = new Avion(nombre, velocidad, eficiencia, fortaleza);
                    System.out.println("Nombre: " + nombre);
                    aviones.add(avion);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void shellSort(List<Avion> lista) {
        int n = lista.size();
        int intervalo = 1;
        while (intervalo < n / 3) {
            intervalo = 3 * intervalo + 1;
        }

        while (intervalo > 0) {
            for (int i = intervalo; i < n; i++) {
                Avion temp = lista.get(i);
                int j = i;
                while (j > intervalo - 1 && lista.get(j - intervalo).getEficiencia() > temp.getEficiencia()) {
                    lista.set(j, lista.get(j - intervalo));
                    j -= intervalo;
                }
                lista.set(j, temp);
            }
            intervalo = (intervalo - 1) / 3;
        }
    }

    private void insertionSort(List<Avion> lista) {
        int n = lista.size();
        for (int i = 1; i < n; ++i) {
            Avion key = lista.get(i);
            int j = i - 1;
            while (j >= 0 && lista.get(j).getVelocidad() > key.getVelocidad()) {
                lista.set(j + 1, lista.get(j));
                j = j - 1;
            }
            lista.set(j + 1, key);
        }
    }
}
