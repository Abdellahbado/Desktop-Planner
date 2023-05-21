import java.time.LocalDate;


public class TacheSimple extends Tache {
    private int periodicite;


    public TacheSimple(String nom, long duree, Priorite priorite, LocalDate dateLimite,
                       Categorie categorie, EtatAvancement etatAvancement, int periodicite) {
        super(nom, duree, priorite, dateLimite, categorie, etatAvancement);
        this.periodicite = periodicite;
    }
    @Override
    public void afficher() {
        super.afficher();
        System.out.println("La periodicite: " + this.periodicite);
        System.out.println("From tache simple " );
    }

    public int getPeriodicite() {
        return this.periodicite;
    }
}
