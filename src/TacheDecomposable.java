import java.time.LocalDate;

public class TacheDecomposable extends Tache {
    //private SortedSet<Tache> ensembleSousTaches;
    private String nomSousTache;
    private int numSousTache;

    public TacheDecomposable(String nom, long duree, Priorite priorite, LocalDate dateLimite, Categorie categorie, EtatAvancement etatAvancement, String nomSousTache, int numSousTache) {
        super(nom, duree, priorite, dateLimite, categorie, etatAvancement);
        this.nomSousTache = nomSousTache;
        this.numSousTache = numSousTache;
    }

    @Override
    public void afficher() {
        super.afficher();
        System.out.println("Nom de la sous-tache: " + nomSousTache);
        System.out.println("Numéro de la sous-tache: " + numSousTache);
        System.out.println("From tache decomposable " );
    }

    public TacheDecomposable decomposerTache(Long dureeCreneau) {
        if (this.getDuree() > dureeCreneau) {
            int i = this.getNumSousTache();
            String nomSousTacheNouv = this.getNom() + " " + Integer.toString(i + 1);
            TacheDecomposable t = new TacheDecomposable(this.getNom(), this.getDuree() - dureeCreneau, this.priorite, this.dateLimite, this.categorie, this.etatAvancement, nomSousTacheNouv, i + 1);
            this.setDuree(dureeCreneau);
            return t;
        }
        return null;
    }


    public int getNumSousTache() {
        return this.numSousTache;
    }

    public String getNomSousTache() {
        return this.nomSousTache;
    }

}
