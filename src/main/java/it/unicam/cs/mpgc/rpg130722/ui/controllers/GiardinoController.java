package it.unicam.cs.mpgc.rpg130722.ui.controllers;

import it.unicam.cs.mpgc.rpg130722.modello.entita.Giocatore;
import it.unicam.cs.mpgc.rpg130722.modello.entita.Pianta;
import it.unicam.cs.mpgc.rpg130722.service.GiardinoService;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.util.function.Function;

public class GiardinoController {

    @FXML
    private ListView<Pianta> listaPiante;

    @FXML
    private Label statoSelezionato;

    @FXML
    private Label statoGiocatore;

    private GiardinoService service;

    @FXML
    public void initialize()
    {
        listaPiante.setCellFactory(lv -> new ListCell<>() {
            @Override
            protected void updateItem (Pianta pianta, boolean vuoto)
            {
                super.updateItem(pianta, vuoto);
                setText(vuoto ||
                        pianta == null ?
                        null : pianta.getNomePianta() +
                        " - " +
                        pianta.getDescrizioneStato());
            }
        });

        listaPiante.getSelectionModel().selectedItemProperty().addListener((
                obs, vecchia, nuova) -> aggiornaEtichetta(nuova));
    }

    public void impostaService (GiardinoService service)
    {
        this.service = service;
        aggiornaLista();
        aggiornaEtichettaGiocatore();
    }

    private void aggiornaLista()
    {
        listaPiante.getItems().setAll(service.getGiardino().getPiante());
    }

    @FXML
    private void onAnnaffia()
    {
        eseguiSelezione(service::annaffia);
    }

    @FXML
    private void onTrascura()
    {
        Pianta selezionata = listaPiante.getSelectionModel().getSelectedItem();
        if (selezionata == null)
            return;

        service.trascura(selezionata);
        listaPiante.refresh();
        aggiornaEtichetta(selezionata);
    }

    @FXML
    private void onPurifica()
    {
        eseguiSelezione(service::purifica);
    }

    @FXML
    private void onNuovoGiorno()
    {
        service.avanzaGiorno();
        listaPiante.refresh();
        aggiornaEtichetta(listaPiante.getSelectionModel().getSelectedItem());
        aggiornaEtichettaGiocatore();
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

    private void eseguiSelezione (Function<Pianta, Boolean> azione)
    {
        Pianta selezionata = listaPiante.getSelectionModel().getSelectedItem();
        if (selezionata == null)
            return;

        boolean eseguita = azione.apply(selezionata);
        if (!eseguita)
        {
            statoSelezionato.setText("Energia insufficiente per questa azione");
            aggiornaEtichettaGiocatore();
            return;
        }

        listaPiante.refresh();
        aggiornaEtichetta(selezionata);
        aggiornaEtichettaGiocatore();
    }

    private void aggiornaEtichetta (Pianta p)
    {
        statoSelezionato.setText(p == null ?
                "Seleziona una pianta" :
                p.getNomePianta() +
                        ": " + p.getDescrizioneStato()
        );
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