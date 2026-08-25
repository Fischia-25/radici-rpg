package it.unicam.cs.mpgc.rpg130722.modello.entita;

import java.io.Serializable;

public class Giocatore implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final int ENERGIA_BASE = 10;
    private static final int ENERGIA_PER_LIVELLO = 2;
    private static final int XP_PER_LIVELLO = 50;

    private int livello;
    private int esperienza;
    private int energia;

    public Giocatore()
    {
        this.livello = 1;
        this.esperienza = 0;
        this.energia = energiaMassima();
    }

    public boolean puoEseguireAzione(int costo)
    {
        return energia >= costo;
    }

    public void consumaEnergia(int costo)
    {
        if (!puoEseguireAzione(costo))
            throw new IllegalStateException("Energia insufficiente");
        energia -= costo;
    }

    public void riposaNuovoGiorno()
    {
        energia = energiaMassima();
    }

    public void guadagnaEsperienza(int quantita)
    {
        esperienza += quantita;
        while (esperienza >= xpProssimoLivello())
        {
            esperienza -= xpProssimoLivello();
            livello++;
        }
    }

    private int xpProssimoLivello()
    {
        return XP_PER_LIVELLO * livello;
    }

    public int energiaMassima()
    {
        return ENERGIA_BASE + (livello - 1) * ENERGIA_PER_LIVELLO;
    }

    public int getEnergia() { return energia; }
    public int getLivello() { return livello; }
    public int getEsperienza() { return esperienza; }
}