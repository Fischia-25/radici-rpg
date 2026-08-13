package it.unicam.cs.mpgc.rpg130722.modello.entita;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Giardino {

    private final List<Pianta> piante = new ArrayList<>();

    public void aggiungiPianta(Pianta pianta) {
        piante.add(pianta);
    }

    public List<Pianta> getPiante() {
        return Collections.unmodifiableList(piante);
    }
}