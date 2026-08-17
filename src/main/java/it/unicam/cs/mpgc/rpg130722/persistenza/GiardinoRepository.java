package it.unicam.cs.mpgc.rpg130722.persistenza;

import it.unicam.cs.mpgc.rpg130722.modello.entita.Giardino;

import java.io.IOException;
import java.util.Optional;

public interface GiardinoRepository {

    void salva(Giardino giardino) throws IOException;

    Optional<Giardino> carica() throws IOException;
}
