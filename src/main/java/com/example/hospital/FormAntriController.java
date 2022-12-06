package com.example.hospital;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

public class FormAntriController implements Initializable{

    @FXML
    private TextField noantrian;
    @FXML
    private TextField nama;
    @FXML
    private ComboBox poli;
    @FXML
    private ComboBox dokter;
    @FXML
    private DatePicker tanggal;

    String jenisTerpilih = "";

    public void openHomepageScene(ActionEvent actionEvent) throws IOException {
        FXMLLoader InvoiceLoader = new FXMLLoader(getClass().getResource("Homepage.fxml"));
        Parent InvoicePage = InvoiceLoader.load();
        Scene InvoiceScene = new Scene(InvoicePage, 1200, 700);

        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(InvoiceScene);
    }

    public void openFormAntriScene(ActionEvent actionEvent) throws IOException {
        FXMLLoader InvoiceLoader = new FXMLLoader(getClass().getResource("FormAntri.fxml"));
        Parent InvoicePage = InvoiceLoader.load();
        Scene InvoiceScene = new Scene(InvoicePage, 1200, 700);

        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(InvoiceScene);
    }

    public void openHomepagePetugasScene(ActionEvent actionEvent) throws IOException {
        FXMLLoader InvoiceLoader = new FXMLLoader(getClass().getResource("HomepagePetugas.fxml"));
        Parent InvoicePage = InvoiceLoader.load();
        Scene InvoiceScene = new Scene(InvoicePage, 1200, 700);

        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(InvoiceScene);
    }

    public void openLoginPetugasScene(ActionEvent actionEvent) throws IOException {
        FXMLLoader InvoiceLoader = new FXMLLoader(getClass().getResource("LoginPetugas.fxml"));
        Parent InvoicePage = InvoiceLoader.load();
        Scene InvoiceScene = new Scene(InvoicePage, 1200, 700);

        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(InvoiceScene);
    }

    public void openFormAntriPetugasScene(ActionEvent actionEvent) throws IOException {
        FXMLLoader InvoiceLoader = new FXMLLoader(getClass().getResource("FormAntriPetugas.fxml"));
        Parent InvoicePage = InvoiceLoader.load();
        Scene InvoiceScene = new Scene(InvoicePage, 1200, 700);

        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(InvoiceScene);
    }

    public void openLihatAntriPetugasScene(ActionEvent actionEvent) throws IOException {
        FXMLLoader InvoiceLoader = new FXMLLoader(getClass().getResource("LihatAntriPetugas.fxml"));
        Parent InvoicePage = InvoiceLoader.load();
        Scene InvoiceScene = new Scene(InvoicePage, 1200, 700);

        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(InvoiceScene);
    }

    public void openInvoiceScene(ActionEvent actionEvent) throws IOException {

        int noantrian;
        String nama, poli, dokter, tanggal, jenis;

        noantrian = Integer.parseInt(this.noantrian.getText());
        nama = this.nama.getText();
        poli = this.poli.getValue().toString();
        dokter = this.dokter.getValue().toString();
        tanggal = this.tanggal.getValue().toString();
        jenis = this.jenisTerpilih;

        System.out.println("Telah masuk Antrian => (" + jenisTerpilih + " " + poli  + " " + "99" + ") Nama: " + nama + ", Dokter: " + dokter + ", Tanggal: " + tanggal);

        Database database = Database.getInstance();

        database.enQueue(noantrian, nama, poli, dokter, tanggal, jenis);

        FXMLLoader InvoiceLoader = new FXMLLoader(getClass().getResource("Invoice.fxml"));
        Parent InvoicePage = InvoiceLoader.load();
        Scene InvoiceScene = new Scene(InvoicePage, 1200, 700);

        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(InvoiceScene);
    }

    public void poliTelahTerpilih(ActionEvent actionEvent) {

        poli.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            dokter.getItems().clear();
            dokter.setPromptText("Pilih Dokter");
            if (newValue.equals("Poli Mata")) {
                dokter.getItems().addAll("Dr. Suluri", "Dr. Hilmi", "Dr. Arkan");
            } else if (newValue.equals("Poli Umum")) {
                dokter.getItems().addAll("Dr. Suci");
            } else if (newValue.equals("Poli Bedah")) {
                dokter.getItems().addAll("Dr. Doc", "Dr. Dia");
            }
        });
    }

   public void memilihBPJS(ActionEvent actionEvent) {
        jenisTerpilih = "BPJS";
   }

    public void memilihUmum(ActionEvent actionEvent) {
        jenisTerpilih = "Umum";
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        poli.getItems().clear();
        poli.getItems().addAll("Poli Mata", "Poli Umum", "Poli Bedah");

        Database database = Database.getInstance();
        Queue queue = database.getQueue();

        noantrian.setText(String.valueOf(queue.getnItems()));
    }
}