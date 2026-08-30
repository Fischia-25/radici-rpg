package it.unicam.cs.mpgc.rpg130722.ui.controllers;

import it.unicam.cs.mpgc.rpg130722.modello.entita.Giocatore;
import it.unicam.cs.mpgc.rpg130722.modello.entita.Pianta;
import it.unicam.cs.mpgc.rpg130722.service.GiardinoService;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.io.IOException;
import java.util.List;
import java.util.function.Function;

public class GiardinoController {

    private static final int COLONNE = 4;

    @FXML
    private GridPane grigliaPiante;

    @FXML
    private Label statoSelezionato;

    @FXML
    private Label statoGiocatore;

    private GiardinoService service;
    private Pianta piantaSelezionata;

    public void impostaService(GiardinoService service)
    {
        this.service = service;
        ricostruisciGriglia();
        aggiornaEtichettaGiocatore();
        aggiornaEtichettaSelezione();
    }

    private void ricostruisciGriglia()
    {
        grigliaPiante.getChildren().clear();

        List<Pianta> piante = service.getGiardino().getPiante();
        for (int i = 0; i < piante.size(); i++)
        {
            Pianta p = piante.get(i);
            VBox tessera = creaTessera(p);
            grigliaPiante.add(tessera, i % COLONNE, i / COLONNE);
        }
    }

    private VBox creaTessera(Pianta p)
    {
        Label nomeLabel = new Label(p.getNomePianta());
        nomeLabel.getStyleClass().add("tessera-nome");

        Label statoLabel = new Label(p.getDescrizioneStato());
        statoLabel.getStyleClass().add("tessera-stato");

        Circle indicatore = new Circle(4);
        indicatore.getStyleClass().add(p.getAzioneDisponibileOggi()
                ? "indicatore-azione-disponibile"
                : "indicatore-azione-usata");

        VBox tessera = new VBox(4, nomeLabel, statoLabel, indicatore);
        tessera.setAlignment(Pos.CENTER);
        tessera.getStyleClass().addAll("tessera-pianta", classeColoreStato(p));

        Tooltip tooltip = new Tooltip(descrizioneCompleta(p));
        tooltip.setShowDelay(Duration.millis(150));
        tooltip.setWrapText(true);
        tooltip.setMaxWidth(260);
        Tooltip.install(tessera, tooltip);

        tessera.setOnMouseClicked(e -> selezionaPianta(p, tessera));

        if (p == piantaSelezionata)
            tessera.getStyleClass().add("tessera-selezionata");

        return tessera;
    }

    private String classeColoreStato(Pianta p)
    {
        return switch (p.getDescrizioneStato())
        {
            case "Sana" -> "stato-sana";
            case "Appassita" -> "stato-appassita";
            case "Corrotta" -> "stato-corrotta";
            case "Curata" -> "stato-curata";
            default -> "stato-appassita";
        };
    }

    private String descrizioneCompleta(Pianta p)
    {
        String azioneOggi = p.getAzioneDisponibileOggi()
                ? "Azione disponibile oggi"
                : "Hai già agito su questa pianta oggi";

        return p.getNomePianta() + "\n" +
                "Stato: " + p.getDescrizioneStato() + "\n" +
                azioneOggi + "\n\n" +
                "In memoria di: " + p.getRicordoCollegato().getNomeDefunto() + "\n" +
                p.getRicordoCollegato().getStoria();
    }

    private void selezionaPianta(Pianta p, VBox tessera)
    {
        piantaSelezionata = p;
        ricostruisciGriglia(); // ridisegna per aggiornare l'evidenziazione
        aggiornaEtichettaSelezione();
    }

    @FXML
    private void onAnnaffia()
    {
        eseguiSuSelezionata(service::annaffia);
    }

    @FXML
    private void onTrascura()
    {
        eseguiSuSelezionata(service::trascura);
    }

    @FXML
    private void onPurifica()
    {
        eseguiSuSelezionata(service::purifica);
    }

    @FXML
    private void onNuovoGiorno()
    {
        service.avanzaGiorno();
        ricostruisciGriglia();
        aggiornaEtichettaGiocatore();
        aggiornaEtichettaSelezione();
    }

    @FXML
    private void onSalva()
    {
        try {
            service.salva();
            statoSelezionato.setText("Salvataggio avvenuto con successo");
        }   catch (IOException e)
        {
            statoSelezionato.setText("Errore nel salvataggio");
        }
    }

    private void eseguiSuSelezionata(Function<Pianta, Boolean> azione)
    {
        if (piantaSelezionata == null)
        {
            statoSelezionato.setText("Seleziona prima una pianta dalla griglia");
            return;
        }

        boolean eseguita = azione.apply(piantaSelezionata);
        if (!eseguita)
        {
            statoSelezionato.setText("Azione non disponibile per questa pianta oggi");
            aggiornaEtichettaGiocatore();
            return;
        }

        ricostruisciGriglia();
        aggiornaEtichettaGiocatore();
        aggiornaEtichettaSelezione();
    }

    private void aggiornaEtichettaSelezione()
    {
        statoSelezionato.setText(piantaSelezionata == null
                ? "Seleziona una pianta"
                : piantaSelezionata.getNomePianta() + ": " + piantaSelezionata.getDescrizioneStato());
    }

    private void aggiornaEtichettaGiocatore()
    {
        Giocatore g = service.getGiocatore();
        statoGiocatore.setText(
                "Livello " + g.getLivello() +
                        " — Energia: " + g.getEnergia() + "/" + g.energiaMassima() +
                        " — Esperienza: " + g.getEsperienza()
        );
    }
}