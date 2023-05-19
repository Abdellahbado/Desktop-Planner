import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Utilisateur {
    static int lastId = 0;
    private int id;

    //private String pseudo;
    private Calendrier calendrier;
    private SortedSet<Projet> listeProjets = new TreeSet<Projet>();
    private ArrayList<Tache> tachesIntroduits;
    private Compte compte;
    private long dureeMinimale;
    private String[] listeBadges;

    private int encouragement;


    public boolean plannifierTacheAuto(Tache tache) {
        return this.calendrier.plannifierTacheAuto(tache);
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

        Date dateLimite = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);

        System.out.println("La categorie de la tache (STUDIES,WORK,HOBBY,SPORT,HEALTH,PERSONAL_FINANCE,ETC): ");
        String categorieString = sc.nextLine();
        Categorie categorie = Categorie.valueOf(categorieString);

        System.out.println("Le type de la tache (simple ou décomposable): ");
        String type = sc.next();
        Tache t;
        if (type.toLowerCase().compareTo("simple") == 0) {
            System.out.println("Entrer la periodicite de la tache: ");
            int periode = sc.nextInt();
            t = new TacheSimple(nom, duree, priorite, dateLimite, categorie, null, periode);
        } else {
            t = new TacheDecomposable(nom, duree, priorite, dateLimite, categorie, null, null);
        }
        return t;
    }

    // hna ki yji creneau tache ta3o tkon null w lazm ymd jour win ykon creneau
    public void creerCreneau(Creneau creneau, Jour jour) {
        this.calendrier.creerCreneau(creneau, jour);
    }

    public String[] getListeBadges(Date date) {
        if(calendrier.incTacheCons(date)==5){
            long numGood = Arrays.stream(listeBadges)
                    .filter(badge -> badge.equals("Good"))
                    .count();
            long numExcellent = Arrays.stream(listeBadges)
                    .filter(badge -> badge.equals("Excellent"))
                    .count();
            long numVeryGood = Arrays.stream(listeBadges)
                    .filter(badge -> badge.equals("VeryGood"))
                    .count();
            String[] newList = Arrays.copyOf(listeBadges, listeBadges.length + 1);
            newList[newList.length - 1] = "Good";
            listeBadges = newList;
            if(numGood==3){
                newList = Arrays.copyOf(listeBadges, listeBadges.length + 1);
                newList[newList.length - 1] = "VeryGood";
                listeBadges = newList;
                List<String> badgesList = new ArrayList<>(Arrays.asList(listeBadges));
                badgesList.removeIf(badge -> badge.equals("Good"));
                listeBadges = badgesList.toArray(new String[0]);

            }
            if(numVeryGood==3){
                newList = Arrays.copyOf(listeBadges, listeBadges.length + 1);
                newList[newList.length - 1] = "Excellent";
                listeBadges = newList;
                List<String> badgesList = new ArrayList<>(Arrays.asList(listeBadges));
                badgesList.removeIf(badge -> badge.equals("VeryGood"));
                listeBadges = badgesList.toArray(new String[0]);

            }
        }
        return listeBadges;
    }
    public String Collect(){
        return  this.listeBadges[listeBadges.length-1];
    }
}
