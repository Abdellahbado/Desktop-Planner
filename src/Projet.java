import java.util.HashSet;


public class Projet {
    private String nomProjet;
    private String description;
    private HashSet<Tache> listeTaches;

    public Projet(String nomProjet, String description, HashSet<Tache> listeTaches) {
        this.nomProjet = nomProjet;
        this.description = description;
        this.listeTaches = new HashSet<Tache>(listeTaches);
    }

    @Override
    public int hashCode() {
        int result = nomProjet != null ? nomProjet.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    public void ajouterTache(Tache tache) {
        if (this.listeTaches == null) {
            this.listeTaches = new HashSet<Tache>();
        }
        this.listeTaches.add(tache);
    }

    public boolean plannifierProjet(Calendrier calendrier) {
        if (this.listeTaches == null) return false;
        boolean tachesPlanifiee = false;
        for (Tache tache : this.listeTaches) {
            if (tache instanceof TacheDecomposable) {
                tachesPlanifiee = calendrier.plannifierTacheDecomp((TacheDecomposable) tache);
                if (!tachesPlanifiee) return false;
            } else if (tache instanceof TacheSimple) {
                tachesPlanifiee = calendrier.plannifierTacheAuto((TacheSimple) tache, ((TacheSimple) tache).getPeriodicite());
                if (!tachesPlanifiee) return false;
            }
        }
        return true;
    }
}
