package it.unicam.cs.mpgc.rpg130722.modello.entita;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Giardino {

    private static final long serialVersionUID = 1L;

    private final List<Pianta> piante = new ArrayList<>();;

    private List<Pianta> pianteCurate;

    public void aggiungiPianta(Pianta p) {
        piante.add(p);
    }

    public List<Pianta> getPiante()
    {
        return Collections.unmodifiableList(piante);
    }

    public void nuovoGiorno(Random r)
    {
        for (Pianta p : piante)
            p.passaGiorno(r);
    }

    void setPianteCurate(Pianta p)
    {
        pianteCurate.add(p);
        rimuoviPianta(p);
    }

    void rimuoviPianta(Pianta p)
    {
        this.piante.remove(p);
    }
}