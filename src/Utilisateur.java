import java.io.Serializable;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.*;

public class Utilisateur implements Serializable {
    static int lastId = 0;
    private int id;
    private String pseudo;
    private String motDePasse;
    private boolean connected;
    private Planning calendrier;
    private SortedSet<Projet> listeProjets = new TreeSet<Projet>();
    private ArrayList<Tache> tachesIntroduites;
    private long dureeMinimale;
    private Badge[] listeBadges;
    private int encouragement;

    public Utilisateur(Planning planning) {
        this.calendrier = planning;
    }

    public Utilisateur(String pseudo, String motDePasse) {
        this.pseudo = pseudo;
        this.motDePasse = motDePasse;

    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Utilisateur otherUser = (Utilisateur) obj;

        return Objects.equals(pseudo, otherUser.pseudo)
                && Objects.equals(motDePasse, otherUser.motDePasse);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pseudo, motDePasse);
    }

    public ArrayList<Tache> getTachesIntroduites() {
        return this.tachesIntroduites;
    }

    public boolean plannifierTacheAuto(TacheSimple tache, int n) {
        return this.calendrier.plannifierTacheAuto(tache, n);
    }

    public boolean plannifierTacheAuto(TacheSimple tache, LocalDate dateLimit, int n) {
        return this.calendrier.plannifierTacheAuto(tache, dateLimit, n);
    }

    // this will return a tache introduit par user
    public void introduitTaches(String nom, long duree, Priorite priorite, LocalDate dateLimite, Categorie categorie, String type, int periode) throws ParseException {

        Tache t;
        if (type.toLowerCase().compareTo("simple") == 0) {
            t = new TacheSimple(nom, duree, priorite, dateLimite, categorie, EtatAvancement.Unscheduled, periode);
        } else {
            t = new TacheDecomposable(nom, duree, priorite, dateLimite, categorie, EtatAvancement.Unscheduled, nom + " " + Integer.toString(1), 1);
        }
        this.tachesIntroduites.add(t);
    }

    public void creerProjet(String titre, String description) {
        Projet projet = new Projet(titre, description, null);
        this.listeProjets.add(projet);
    }


    public boolean plannifierTacheInroduites() {
        if (this.tachesIntroduites == null) return false;
        boolean tachesPlanifiee = false;
        for (Tache tache : this.tachesIntroduites) {
            if (tache instanceof TacheDecomposable) {
                tachesPlanifiee = this.calendrier.plannifierTacheDecomp((TacheDecomposable) tache);
                if (!tachesPlanifiee) return false;
            } else if (tache instanceof TacheSimple) {
                tachesPlanifiee = this.calendrier.plannifierTacheAuto((TacheSimple) tache, ((TacheSimple) tache).getPeriodicite());
                if (!tachesPlanifiee) return false;
            }
        }
        return true;
    }

    // methode for sorting tachesIntroduites
    public void sortTachesIntroduitesByPriority() {
        Collections.sort(tachesIntroduites);
        // hna ndir reverse bach tkon high hiya lawla omb3d medium...
        Collections.reverse(tachesIntroduites);
    }

    public void setConnected(boolean state) {
        this.connected = state;
    }

}
