import java.util.*;


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
}
