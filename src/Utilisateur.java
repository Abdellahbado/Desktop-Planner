import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.time.format.DateTimeFormatter;
public class Utilisateur {
    static int lastId = 0;
    private int id;

    //private String pseudo;
    private Calendrier calendrier;
    private SortedSet<Projet> listeProjets = new TreeSet<Projet>();
    private ArrayList<Tache> tachesIntroduits;
    private Compte compte;
    private long dureeMinimale;
    private Badge[] listeBadges;
    private int encouragement;


    public boolean plannifierTacheAuto(Tache tache) {
        return this.calendrier.plannifierTacheAuto(tache);
    }

    public boolean plannifierTacheAuto(Tache tache, LocalDate dateLimit) {
        return this.calendrier.plannifierTacheAuto(tache, dateLimit);
    }

    // this will return a tache introduit par user
    public Tache introduireTaches() throws ParseException {
        System.out.println("Veuiller introduire toutes les informations de la tache: ");
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the name of the task: ");
        String nom = sc.nextLine();

        System.out.println("Enter the duration of the task in minutes: ");
        long duree = sc.nextLong();

        System.out.println("Enter the priority of the task (Low, Medium, High): ");
        String prioriteString = sc.nextLine();
        Priorite priorite = Priorite.valueOf(prioriteString);

        System.out.println("La date limite (AAAA-MM-JJ): ");
        String dateString = sc.next();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dateLimite = LocalDate.parse(dateString, formatter);

        System.out.println("La categorie de la tache (STUDIES,WORK,HOBBY,SPORT,HEALTH,PERSONAL_FINANCE,ETC): ");
        String categorieString = sc.nextLine();
        Categorie categorie = Categorie.valueOf(categorieString);

        System.out.println("Le type de la tache (simple ou décomposable): ");
        String type = sc.next();
        Tache t;
        if (type.toLowerCase().compareTo("simple") == 0) {
            System.out.println("Entrer la periodicite de la tache: ");
            int periode = sc.nextInt();
            t = new TacheSimple(nom, duree, priorite, dateLimite, categorie, EtatAvancement.notRealised, periode);
        } else {
            t = new TacheDecomposable(nom, duree, priorite, dateLimite, categorie, EtatAvancement.notRealised, nom + Integer.toString(1), 1);
        }
        return t;
    }

    // hna ki yji creneau tache ta3o tkon null w lazm ymd jour win ykon creneau
    public void creerCreneau(Creneau creneau, Jour jour) {
        this.calendrier.creerCreneau(creneau, jour);
    }
}
