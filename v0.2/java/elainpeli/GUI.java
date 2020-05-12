/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elainpeli;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Minna
 */
public class GUI extends Application {

    Peli peli = new Peli();
    boolean saakoValita = false;
    VBox ohjeet = new VBox();
    VBox pelaajalista = new VBox();

    Button yksi = new Button("1");
    Button kaksi = new Button("2");
    Button kolme = new Button("3");
    Button nelja = new Button("4");
    Button viisi = new Button("5");
    Button noppa = new Button("Napsauta!");

    // Eläimet
    HBox elaimet = new HBox(5);
    VBox sammakko = new VBox();
    VBox ankka = new VBox();
    VBox possu = new VBox();
    VBox lehma = new VBox();
    VBox hevonen = new VBox();

    @Override
    public void start(Stage ikkuna) throws Exception {

        // Aloitusnäkymä
        GridPane aloitus = new GridPane();
        aloitus.getStylesheets().add(getClass().getResource("resources/elainpeli.css").toExternalForm());
        aloitus.setPadding(new Insets(20, 20, 20, 20));
        aloitus.setPrefSize(500, 100);
        aloitus.setAlignment(Pos.CENTER);
        TextField pelaaja1 = new TextField();
        TextField pelaaja2 = new TextField();
        TextField pelaaja3 = new TextField();
        TextField pelaaja4 = new TextField();
        Label ohje = new Label("Anna pelaajien nimet (1-4)");

        aloitus.add(ohje, 0, 0);
        aloitus.add(pelaaja1, 0, 1);
        aloitus.add(pelaaja2, 0, 2);
        aloitus.add(pelaaja3, 1, 1);
        aloitus.add(pelaaja4, 1, 2);

        Button valmis = new Button("Valmis");
        GridPane.setMargin(valmis, new Insets(0, 0, 0, 10));
        aloitus.add(valmis, 2, 2);

        Scene aloitusnakyma = new Scene(aloitus);

        // Pelinäkymä
        BorderPane pelialue = new BorderPane();
        pelialue.setPrefSize(800, 600);

        Scene pelinakyma = new Scene(pelialue);
        // Maven etsii tiedostoja polusta src/main/resources. 
        // Jotta buildattu tiedosto menee samaan paikkaan classien kanssa, 
        // resources-kansiossa on vielä paketin nimen mukainen kansio
        pelinakyma.getStylesheets().add(getClass().getResource("resources/elainpeli.css").toExternalForm());

        pelaajalista.setPrefWidth(200);
        pelialue.setRight(pelaajalista);

        VBox pelitilanne = new VBox();
        pelitilanne.setAlignment(Pos.CENTER);
        pelitilanne.setSpacing(20);
        BorderPane.setMargin(pelitilanne, new Insets(12, 12, 12, 12));
        pelialue.setCenter(pelitilanne);

        yksi.setId("button-sammakko");
        kaksi.setId("button-ankka");
        kolme.setId("button-possu");
        nelja.setId("button-lehma");
        viisi.setId("button-hevonen");
        
        elaimet.setPrefHeight(250);

        // TODO? getChildren():in avulla kaikkien määritys kerralla
        sammakko.setPrefWidth(60);
        ankka.setPrefWidth(60);
        possu.setPrefWidth(60);
        lehma.setPrefWidth(60);
        hevonen.setPrefWidth(60);
        
        sammakko.setAlignment(Pos.TOP_CENTER);
        ankka.setAlignment(Pos.TOP_CENTER);
        possu.setAlignment(Pos.TOP_CENTER);
        lehma.setAlignment(Pos.TOP_CENTER);
        hevonen.setAlignment(Pos.TOP_CENTER);
        
        sammakko.getChildren().add(yksi);
        ankka.getChildren().add(kaksi);
        possu.getChildren().add(kolme);
        lehma.getChildren().add(nelja);
        hevonen.getChildren().add(viisi);

        elaimet.getChildren().addAll(sammakko, ankka, possu, lehma, hevonen);

        valmis.setOnAction((event) -> {
            ArrayList<String> pelaajat = new ArrayList<>();
            pelaajat.add(pelaaja1.getText());
            pelaajat.add(pelaaja2.getText());
            pelaajat.add(pelaaja3.getText());
            pelaajat.add(pelaaja4.getText());
            lisaaPelaajat(pelaajat);
            ikkuna.setScene(pelinakyma);
            pelaajalistanPaivitys();
            ohjeidenPaivitys("Peli alkaa! " + peli.pelaajaVuorossa() + " aloittaa!");
        });

        // Noppa
        // TODO? setdisable(true) siksi aikaa kun eläimen valinta on kesken
        
        noppa.setId("noppa");
        noppa.setPrefSize(70, 70);

        noppa.setOnMousePressed((event) -> {
            if (saakoValita == false) {
                noppa.setGraphic(null);
            }
        });
        noppa.setOnMouseReleased((event) -> {
            if (saakoValita == false) {
                int luku = peli.nopanHeitto();
                String noppakuva = "noppa" + luku + ".png";
                Image kuva = new Image(getClass().getResourceAsStream("resources/" + noppakuva));
                noppa.setText("");
                noppa.setGraphic(new ImageView(kuva));
                if (luku == 6) {
                    ohjeidenPaivitys(peli.pelaajaVuorossa() + ", valitse jokin eläimistä.");
                    saaValita(); // falsesta trueksi
                } else {
                    numerovalinta(luku);
                }
            }
            if (peli.peliLoppu()) {
                noppa.setDisable(true);
                yksi.setDisable(true);
                kaksi.setDisable(true);
                kolme.setDisable(true);
                nelja.setDisable(true);
                viisi.setDisable(true);
            }
        });

        // TODO? setdisable(true) kunnes joku heittää 6:n
        yksi.setOnAction((event) -> {
            if (saakoValita == true) {
                numerovalinta(1);
            }
        });
        kaksi.setOnAction((event) -> {
            if (saakoValita == true) {
                numerovalinta(2);
            }
        });
        kolme.setOnAction((event) -> {
            if (saakoValita == true) {
                numerovalinta(3);
            }
        });
        nelja.setOnAction((event) -> {
            if (saakoValita == true) {
                numerovalinta(4);
            }
        });
        viisi.setOnAction((event) -> {
            if (saakoValita == true) {
                numerovalinta(5);
            }
        });

        pelitilanne.getChildren().addAll(elaimet, noppa, ohjeet);

        ikkuna.setScene(aloitusnakyma);

        ikkuna.setTitle("Eläinpeli (o)__(O) Minna (c) 2020");
        ikkuna.show();

    }

    private void lisaaPelaajat(ArrayList<String> lista) {
        for (String nimi : lista) {
            if (!nimi.isEmpty()) {
                peli.lisaaPelaaja(nimi);
            }
        }
    }

    private void saaValita() {
        if (saakoValita == false) {
            saakoValita = true;
        } else {
            saakoValita = false;
        }
    }

    private void pelaajalistanPaivitys() {
        pelaajalista.getChildren().clear();
        Label otsikko = new Label("Pelaajat");
        otsikko.setId("pelaajat-otsikko");
        pelaajalista.getChildren().add(otsikko);
        for (String pisteet : peli.pistetilanne()) {
            Label nimi = new Label(pisteet);
            pelaajalista.getChildren().add(nimi);
        }
    }

    private void ohjeidenPaivitys(String ohje) {
        ohjeet.getChildren().clear();
        ohjeet.getChildren().add(new Text("Tavoitteena on saada eläimet valmiiksi.\nKussakin eläimessä on eri määrä palasia, ja saat pisteen,\nkun saat laitettua pääpalan pelilaudalle."));
        ohjeet.getChildren().add(new Text(""));
        ohjeet.getChildren().add(new Text(ohje));
    }

    private boolean elaimenPaivitys(int elain, VBox paivitettava) {
        paivitettava.getChildren().removeIf(n -> !(n instanceof Button));

        int paloja = peli.elaimenOsat(elain);

        Image paa = null;
        Image pala = new Image(getClass().getResourceAsStream("resources/pala.png"));
        switch (elain) {
            case 1:
                paa = new Image(getClass().getResourceAsStream("resources/sammakonPaa.png"));
                break;
            case 2:
                paa = new Image(getClass().getResourceAsStream("resources/ankanPaa.png"));
                break;
            case 3:
                paa = new Image(getClass().getResourceAsStream("resources/possunPaa.png"));
                break;
            case 4:
                paa = new Image(getClass().getResourceAsStream("resources/lehmanPaa.png"));
                break;
            case 5:
                paa = new Image(getClass().getResourceAsStream("resources/hevosenPaa.png"));
        }

        if (peli.elainValmistui(elain)) {
            paivitettava.getChildren().add(new ImageView(paa));
            for (int i = 0; i < paloja - 1; i++) {
                paivitettava.getChildren().add(new ImageView(pala));
            }
            return true;
        } else {
            for (int i = 0; i < paloja; i++) {
                paivitettava.getChildren().add(new ImageView(pala));
            }
            return false;
        }
    }

    private void numerovalinta(int numero) {

        boolean onnistuiko = false;
        VBox paivitettava = null;

        if (numero == 1) {
            if (peli.elaimenValinta(1)) {
                paivitettava = sammakko;
                onnistuiko = true;
            }
        }
        if (numero == 2) {
            if (peli.elaimenValinta(2)) {
                paivitettava = ankka;
                onnistuiko = true;
            }
        }
        if (numero == 3) {
            if (peli.elaimenValinta(3)) {
                paivitettava = possu;
                onnistuiko = true;
            }
        }
        if (numero == 4) {
            if (peli.elaimenValinta(4)) {
                paivitettava = lehma;
                onnistuiko = true;
            }
        }
        if (numero == 5) {
            if (peli.elaimenValinta(5)) {
                paivitettava = hevonen;
                onnistuiko = true;
            }
        }

        boolean piste = false;
        if (!onnistuiko) {
            ohjeidenPaivitys("Olepas tarkkana, se eläin on jo valmis.");
        } else {
            piste = elaimenPaivitys(numero, paivitettava);
            if (saakoValita) {
                saaValita(); // muutetaan takaisin falseksi
            }
        }

        vuoronvaihto(piste);
    }

    private void vuoronvaihto(boolean piste) {

        if (piste) {
            // Pelaaja saa pisteen
            peli.pelaajallePiste();
            pelaajalistanPaivitys();
            ohjeidenPaivitys(peli.pelaajaVuorossa() + " sai pisteen!");
            peli.seuraavaPelaaja();
            ohjeet.getChildren().add(new Text(peli.pelaajaVuorossa() + ", heitä noppaa!"));
        } else {
            peli.seuraavaPelaaja();
            ohjeidenPaivitys(peli.pelaajaVuorossa() + ", heitä noppaa!");
        }

        if (peli.peliLoppu()) {
            ohjeidenPaivitys(peli.voittaja() + " voitti, onnea!");
            noppa.setDisable(true);
            yksi.setDisable(true);
            kaksi.setDisable(true);
            kolme.setDisable(true);
            nelja.setDisable(true);
            viisi.setDisable(true);
        }
    }

    public static void main(String[] args) {
        launch(GUI.class);
    }

}
