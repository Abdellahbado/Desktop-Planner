import java.util.Date;
import java.util.SortedSet;
import java.util.TreeSet;

public class TacheDecomposable extends Tache implements Comparable<TacheDecomposable> {
    private SortedSet<Tache> ensembleSousTaches;

    public TacheDecomposable(String nom, long duree, Priorite priorite, Date dateLimite,
                             Categorie categorie, EtatAvancement etatAvancement, TreeSet<Tache> ensSTaches) {
        super(nom, duree, priorite, dateLimite, categorie, etatAvancement);
        this.ensembleSousTaches = ensSTaches;
    }

    @Override
    public int compareTo(TacheDecomposable other) {
        return this.nom.compareTo(other.nom);
    }
}
