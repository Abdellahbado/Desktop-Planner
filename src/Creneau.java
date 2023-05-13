import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Creneau {
    private Tache tache;
    private LocalTime heureDebut;
    private LocalTime heureFin;
    private EtatCreneau etatCreneau;

    public Creneau(LocalTime heure1, LocalTime heure2, EtatCreneau etCr, Tache tache) {
        this.heureDebut = heure1;
        this.heureFin = heure2;
        this.tache = tache;
        this.etatCreneau = etCr;
    }

    public LocalTime getHeureDebut() {
        return heureDebut;
    }

    public void setHeureDebut(LocalTime heureDebut) {
        this.heureDebut = heureDebut;
    }

    public LocalTime getHeureFin() {
        return heureFin;
    }

    public void setHeureFin(LocalTime heureFin) {
        this.heureFin = heureFin;
    }

    public Tache getTache() {
        return tache;
    }

    public void setTache(Tache tache) {
        this.tache = tache;
    }

    public EtatCreneau getEtatCreneau() {
        return etatCreneau;
    }

    public void setEtatCreneau(EtatCreneau etatCreneau) {
        this.etatCreneau = etatCreneau;
    }
    public void afficher(){
        System.out.println("l'heure du début de ce créneau:"+this.heureDebut);
        System.out.println("l'heure de la fin de ce créneau:"+this.heureFin);
        System.out.println("libre (true), occupé (false):"+this.etatCreneau);
        if(tache != null) {
            tache.afficher();
        }
        System.out.println("----------");

    }

    public static Creneau creerCreneau(){
        Creneau cr=new Creneau(null, null, EtatCreneau.Occupe, null);
        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime heure = null;
        do {
            System.out.println("Entrez l'heure de début de format HH:mm :");
            String input = scanner.nextLine();
            try {
                heure = LocalTime.parse(input, format);
            } catch (Exception e) {
                System.out.println("L'heure saisie est invalide. Veuillez réessayer.");
            }
        } while (heure == null);
        cr.setHeureDebut(heure);
        do {
            System.out.println("Entrez l'heure de fin de format HH:mm :");
            String input = scanner.nextLine();
            try {
                heure = LocalTime.parse(input, format);
            } catch (Exception e) {
                System.out.println("L'heure saisie est invalide, Veuillez réessayer");
            }
        } while (heure == null);
        cr.setHeureFin(heure);
        System.out.println("veuillez entrer la durée minimale du créneau en minutes");
        long a = scanner.nextInt();
        cr.setEtatCreneau(EtatCreneau.Occupe);;
        return cr;
    }



    public boolean estDecomposable(long dureeTache, long duree_min) { //méthode qui indique si on peut décomposer un creneau donné lors de l'introduction d'une tache

        Duration duree = Duration.between(this.heureDebut, this.heureFin);
        return duree.toMinutes() - dureeTache > duree_min;
        //on peut decomposer quand la duree du creneau - duree tache > duree minimale
    }

    //on utilise cette méthode seulement après s'assurer que le créneau est décomposable par la fonction précédente
    public Creneau decomposer(Tache tache, long duree_min){
        long dureeTache = tache.getDuree();
        Creneau cr1, cr2;
        cr1 = this;
        LocalTime h = cr1.getHeureDebut();

        // ajout de la durée en minutes à l'heure de départ
        LocalTime heureArrivee = h.plusMinutes(dureeTache);

        cr2 = new Creneau(heureArrivee, this.getHeureFin(), EtatCreneau.Libre, null);

        cr1.setHeureFin(heureArrivee);
        cr1.planifierTache(tache);

        //  tab[0].afficher();
        //  tab[1].afficher();

        return cr2;
    }

    public void planifierTache(Tache tache) {
        this.tache = tache;
        this.etatCreneau = EtatCreneau.Occupe;
        this.tache.setEtatAvancement(EtatAvancement.InProgress);
    }

    public Duration getDuration(){
        Duration duree = Duration.between(this.heureDebut, this.heureFin);
        return duree;
    }


}


