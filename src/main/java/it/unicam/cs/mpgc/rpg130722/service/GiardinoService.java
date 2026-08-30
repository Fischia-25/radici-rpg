package it.unicam.cs.mpgc.rpg130722.service;

import it.unicam.cs.mpgc.rpg130722.modello.entita.Giardino;
import it.unicam.cs.mpgc.rpg130722.modello.entita.Giocatore;
import it.unicam.cs.mpgc.rpg130722.modello.entita.Pianta;
import it.unicam.cs.mpgc.rpg130722.persistenza.GiardinoRepository;

import java.io.IOException;
import java.util.Random;
import java.util.function.Supplier;

public class GiardinoService {

    private static final int COSTO_ANNAFFIA = 1;
    private static final int COSTO_PURIFICA = 3;
    private static final int XP_PIANTA_CURATA = 20;

    private final GiardinoRepository repository;
    private final Giardino giardino;
    private final Random random;

    public GiardinoService(GiardinoRepository repository) throws IOException
    {
        this(repository, new Random());
    }

    public GiardinoService(GiardinoRepository repository, Random random) throws IOException
    {
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

    public Giocatore getGiocatore()
    {
        return giardino.getGiocatore();
    }

    public boolean annaffia(Pianta p)
    {
        return eseguiAzione(p, COSTO_ANNAFFIA, p::annaffia);
    }

    public boolean purifica(Pianta p)
    {
        return eseguiAzione(p, COSTO_PURIFICA, p::purifica);
    }

    public boolean trascura(Pianta p)
    {
        return eseguiAzione(p, 0, p::trascura);
    }

    private boolean eseguiAzione(Pianta p, int costo, Supplier<Boolean> azione)
    {
        if (!p.getAzioneDisponibileOggi())
        return false;

        Giocatore giocatore = giardino.getGiocatore();
        if (!giocatore.puoEseguireAzione(costo))
            return false;

        String statoPrima = p.getDescrizioneStato();
        boolean eseguita = azione.get();
        if (!eseguita)
            return false;

        p.consumaAzioneGiornaliera();
        giocatore.consumaEnergia(costo);
        assegnaXpSeCompletata(p, statoPrima, giocatore);
        return true;
    }

    private void assegnaXpSeCompletata(Pianta p, String statoPrima, Giocatore giocatore)
    {
        if (!"Curata".equals(statoPrima) && "Curata".equals(p.getDescrizioneStato()))
            giocatore.guadagnaEsperienza(XP_PIANTA_CURATA);
    }

    public void avanzaGiorno()
    {
        giardino.nuovoGiorno(random);
    }

    public void salva() throws IOException
    {
        repository.salva(giardino);
    }
}