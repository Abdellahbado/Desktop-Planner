import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.SortedSet;
import java.util.TreeSet;

public class Utilisateur {
    static int lastId = 0;
    private int id;
    private String pseudo;
    private String motDePasse;
    private boolean connected;
    //private String pseudo;
    private Calendrier calendrier;
    private SortedSet<Projet> listeProjets = new TreeSet<Projet>();
    private ArrayList<Tache> tachesIntroduites;
    private long dureeMinimale;
    private Badge[] listeBadges;
    private int encouragement;


    public boolean plannifierTacheAuto(Tache tache, int n) {
        return this.calendrier.plannifierTacheAuto(tache, n);
    }

    public boolean plannifierTacheAuto(Tache tache, LocalDate dateLimit, int n) {
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

}
