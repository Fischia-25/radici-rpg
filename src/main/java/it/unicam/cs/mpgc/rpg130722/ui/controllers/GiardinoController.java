package it.unicam.cs.mpgc.rpg130722.ui.controllers;

import it.unicam.cs.mpgc.rpg130722.modello.entita.Pianta;
import it.unicam.cs.mpgc.rpg130722.service.GiardinoService;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;

import java.io.IOException;

public class GiardinoController {

    @FXML
    private ListView<Pianta> listaPiante;

    @FXML
    private Label statoSelezionato;

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
    }

    private void aggiornaLista()
    {
        listaPiante.getItems().setAll(service.getGiardino().getPiante());
    }

    @FXML
    private void onAnnaffia()
    {
        eseguiSelezione(Pianta::annaffia);
    }

    @FXML
    private void onTrascura()
    {
        eseguiSelezione(Pianta::trascura);
    }

    @FXML
    private void onPurifica()
    {
        eseguiSelezione(Pianta::purifica);
    }

    @FXML
    private void onNuovoGiorno()
    {
        service.avanzaGiorno();
        listaPiante.refresh();
        aggiornaEtichetta (listaPiante.getSelectionModel().getSelectedItem());
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

    private void eseguiSelezione (java.util.function.Consumer<Pianta> azione)
    {
        Pianta selezionata = listaPiante.getSelectionModel().getSelectedItem();
        if (selezionata != null)
        {
            azione.accept(selezionata);
            listaPiante.refresh();
            aggiornaEtichetta(selezionata);
        }
    }

    private void aggiornaEtichetta (Pianta p)
    {
        statoSelezionato.setText(p == null ?
                "Seleziona una pianta" :
                p.getNomePianta() +
                        ": " + p.getDescrizioneStato()
        );
    }
}
