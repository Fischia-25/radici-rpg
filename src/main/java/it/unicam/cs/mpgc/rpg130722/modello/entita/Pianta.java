package it.unicam.cs.mpgc.rpg130722.modello.entita;

import it.unicam.cs.mpgc.rpg130722.modello.stato.StatoPianta;
import it.unicam.cs.mpgc.rpg130722.modello.stato.StatoAppassita;

import java.io.Serializable;
import java.util.Random;

// Rappresenta una pianta del giardino che è collegata a un ricordo
public class Pianta implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String nome;
    private StatoPianta stato;
    private final Ricordo ricordoCollegato;

    /**
     * Creo una nuova pianta sempre nello stato iniziale di appassita.
     * Rappresenta una pianta appena scoperta, quindi il giocatore non se ne è ancora preso cura
     */
    public Pianta(String nome, Ricordo ricordoCollegato)
    {
        this.nome = nome;
        this.ricordoCollegato = ricordoCollegato;
        this.stato = new StatoAppassita();
    }

    // le azioni sono delegate agli stati
    public void annaffia()
    {
        stato.annaffia(this);
    }
    public void trascura()
    {
        stato.trascura(this);
    }
    public void purifica()
    {
        stato.purifica(this);
    }

    public void setStato(StatoPianta s)
    {
        this.stato = s;
    }

    public void passaGiorno(Random r)
    {
        stato.passaGiorno(this, r);
    }

    public String getDescrizioneStato()
    {
        return stato.getDescrizione();
    }

    public String getNomePianta()
    {
        return nome;
    }

    public Ricordo getRicordoCollegato()
    {
        return ricordoCollegato;
    }

}
