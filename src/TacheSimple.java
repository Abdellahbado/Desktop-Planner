import java.time.LocalDate;


public class TacheSimple extends Tache {
    private int periodicite;


    public TacheSimple(String nom, long duree, Priorite priorite, LocalDate dateLimite,
                       Categorie categorie, EtatAvancement etatAvancement, int periodicite) {
        super(nom, duree, priorite, dateLimite, categorie, etatAvancement);
        this.periodicite = periodicite;
    }

    public int getPeriodicite() {
        return this.periodicite;
    }
}
