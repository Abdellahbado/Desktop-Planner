import java.util.Date;

public class SousTache extends Tache {
    private int numeroTache;

    public SousTache(String nom, long duree, Priorite priorite, Date dateLimite,
                     Categorie categorie, EtatAvancement etatAvancement,int numeroTache) {
        super(nom, duree, priorite, dateLimite, categorie, etatAvancement);
        this.numeroTache = numeroTache;
    }

}
