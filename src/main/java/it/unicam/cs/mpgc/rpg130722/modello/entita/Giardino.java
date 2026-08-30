package it.unicam.cs.mpgc.rpg130722.modello.entita;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Giardino implements Serializable {

    private static final long serialVersionUID = 1L;

    private final List<Pianta> piante = new ArrayList<>();
    private final Giocatore giocatore;

    public Giardino()
    {
        this(new Giocatore());
    }

    public Giardino(Giocatore giocatore)
    {
        this.giocatore = giocatore;
    }

    public void aggiungiPianta(Pianta p)
    {
        piante.add(p);
    }

    public List<Pianta> getPiante()
    {
        return Collections.unmodifiableList(piante);
    }

    public Giocatore getGiocatore()
    {
        return giocatore;
    }

    public void nuovoGiorno(Random r)
    {
        for (Pianta p : piante)
        {
            p.passaGiorno(r);
            p.ripristinaAzioneGiornaliera();
        }
        giocatore.riposaNuovoGiorno();
    }
}