import java.io.Serializable;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Scanner;

public class Creneau implements Comparable<Creneau>, Serializable {
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

    public static Creneau creerCreneau() {
        Creneau cr = new Creneau(null, null, EtatCreneau.Occupe, null);
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
        cr.setEtatCreneau(EtatCreneau.Occupe);
        ;
        return cr;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Creneau other = (Creneau) obj;
        // Compare attributes to determine equality
        return Objects.equals(heureDebut, other.getHeureDebut()) &&
                Objects.equals(heureFin, other.getHeureFin());
    }

    public void setBloque(boolean b) {
        if (b == true)
            this.etatCreneau = EtatCreneau.Bloque;
        else this.etatCreneau = EtatCreneau.Occupe;
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

    public void afficher() {
        System.out.println("Le creneu heure debut:" + this.heureDebut + ", heure fin: " + this.heureFin);
        System.out.println("Heure de début: " + this.heureDebut);
        System.out.println("Heure de fin: " + this.heureFin);
        System.out.println("Etat du créneau:" + this.etatCreneau);
        if (tache != null) {
            tache.afficher();
        }
    }


    public void planifierTache(Tache tache) {
        this.tache = tache;
        this.etatCreneau = EtatCreneau.Occupe;
        this.tache.setEtatAvancement(EtatAvancement.InProgress);

    }

    public Duration getDuration() {
        Duration duree = Duration.between(this.heureDebut, this.heureFin);
        return duree;
    }


    // this function is called only on tacheDecomposable
    public TacheDecomposable plannifierTacheDecomp(TacheDecomposable tache) {
        long dureCreneau = this.getDuration().toMinutes();
        if (dureCreneau >= tache.duree) {
            this.planifierTache(tache); // cas trivial
            return null;
        } else {
            // on duplique la tache dans une nouvelle var et on la planifier et on retourne
            // le rest de la tache mais la duree doit etre >  30
            TacheDecomposable nouvTache = tache.decomposerTache(dureCreneau);
            this.planifierTache(tache);
            return nouvTache;
        }
    }

    public int compareTo(Creneau creneau) {
        return this.heureDebut.compareTo(creneau.getHeureDebut());
    }
}


