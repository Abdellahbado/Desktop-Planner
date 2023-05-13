import java.time.LocalDate;
import java.util.Iterator;
import java.util.TreeSet;

public class Jour {
    static int nombreJours = 0;
    private TreeSet<Creneau> listeCreneaux;
    private LocalDate date;
    private EtatAvancement etatAvancement;
    private int nbTachesMinimales;

    public Jour(LocalDate date) {
        this.date = date;
        nombreJours++;

        /*DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm");
        String str1 = "00:00";
        String str2 = "23:59";
        LocalTime heurDeb = LocalTime.parse(str1, format);
        LocalTime heurFin = LocalTime.parse(str2, format);
        Creneau cr = new Creneau(heurDeb, heurFin, EtatCreneau.Libre, null);
        listeCreneaux = new TreeSet<Creneau>();
        listeCreneaux.add(cr);*/
    }

    public boolean plannifierTacheAuto(Tache tache) {
        //fonction nchof fiha ida kayn creneau libre w la duree ta3o tkfi
        Iterator<Creneau> iterator = this.listeCreneaux.iterator();

        while (iterator.hasNext()) {
            Creneau creneau = iterator.next();
            if (creneau.getEtatCreneau() == EtatCreneau.Libre) {
                if (creneau.getDuration().toMinutes() >= tache.duree) {
                    creneau.planifierTache(tache);
                    return true;
                } else return false;
            } else return false;
        }
        return false;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public void creerCreneau(Creneau creneau) {
        listeCreneaux.add(creneau);
        System.out.println("Creneau: " + creneau.getHeureDebut() + "-" + creneau.getHeureFin() + " crée avec succes");
    }
}
