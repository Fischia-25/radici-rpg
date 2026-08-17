package it.unicam.cs.mpgc.rpg130722.modello.service;

import it.unicam.cs.mpgc.rpg130722.modello.entita.Giardino;
import it.unicam.cs.mpgc.rpg130722.persistenza.GiardinoRepository;

import java.io.IOException;
import java.util.Random;

public class GiardinoService {

    private final GiardinoRepository repository;
    private final Giardino giardino;
    private final Random random;

    public GiardinoService(GiardinoRepository repository) throws IOException {
        this(repository, new Random());
    }

    public GiardinoService(GiardinoRepository repository, Random random) throws IOException {
        this.repository = repository;
        this.random = random;
        this.giardino = repository.carica().orElseGet(this::creaGiardinoDefault);
    }

    private Giardino creaGiardinoDefault()
    {
        Giardino nuovo = new Giardino();
        nuovo.aggiungiPianta(PiantaFactory.creaPianta(
                "Rosa di Anna", "Anna", "La rosa di una vita ... "
        ));
        nuovo.aggiungiPianta(PiantaFactory.creaPianta(
                "Ulivo di Marco", "Marco", "Una storia da raccontare ... "
        ));
        return nuovo;
    }

    public Giardino getGiardino()
    {
        return giardino;
    }

    public void avanzaGiorno()
    {
        giardino.nuovoGiorno(random);
    }

    public void salva() throws IOException {
        repository.salva(giardino);
    }
}
