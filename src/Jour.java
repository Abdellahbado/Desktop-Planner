import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Iterator;
import java.util.TreeSet;

public class Jour implements Comparable<Jour>, Serializable {
    static int nombreJours = 0;
    private TreeSet<Creneau> listeCreneaux;
    private LocalDate date;
    private int minTask=3;


    public Jour(LocalDate date, TreeSet<Creneau> listeCreneaux) {
        this.date = date;

        this.listeCreneaux = listeCreneaux;
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

    public int getMinTask() {
        return minTask;
    }

    public void setMinTask(int minTask) {
        this.minTask = minTask;
    }

    //private EtatAvancement etatAvancement;
    //private int nbTachesMinimales;
    public void afficher() {
        System.out.println("---------------------------------------------------------------");
        System.out.println("Date: " + date);
        for (Creneau creneau : listeCreneaux) {
            creneau.afficher();
        }
    }

    public void creerCreneauPeriodique(LocalTime heurD, LocalTime heurF) {
        Creneau creneau = new Creneau(heurD, heurF, EtatCreneau.Libre, null);
        if (listeCreneaux != null && listeCreneaux.contains(creneau)) {
            return;
        } else if (listeCreneaux == null) {
            listeCreneaux = new TreeSet<>();
            listeCreneaux.add(creneau);
        } else {
            listeCreneaux.add(creneau);
        }
        ;
    }

    public boolean plannifierTacheAuto(Tache tache) {
        //fonction nchof fiha ida kayn creneau libre w la duree ta3o tkfi
        Iterator<Creneau> iterator = this.listeCreneaux.iterator();

        while (iterator.hasNext()) {
            Creneau creneau = iterator.next();
            if (creneau.getEtatCreneau() == EtatCreneau.Libre) {
                if (creneau.getDuration().toMinutes() >= tache.getDuree()) {
                    creneau.planifierTache(tache);
                    if (creneau.getDuration().toMinutes() - tache.getDuree() >= 30) {
                        LocalTime heurFin = creneau.getHeureDebut().plusMinutes(tache.getDuree());
                        LocalTime nouvHeureDeb = creneau.getHeureDebut().minusMinutes(tache.getDuree());
                        Creneau nouvCreneau = new Creneau(nouvHeureDeb, creneau.getHeureFin(), EtatCreneau.Libre, null);
                        creneau.setHeureFin(heurFin);
                        this.listeCreneaux.add(nouvCreneau);
                    }
                    return true;
                } else return false;
            } else return false;
        }
        return false;
    }

    public boolean plannifierTacheManu(TacheSimple tache, LocalTime heurD, boolean bloque) {
        for (Creneau creneau : this.listeCreneaux) {
            if (creneau.getHeureDebut().equals(heurD)) {
                if (creneau.getDuration().toMinutes() >= tache.getDuree()) {
                    creneau.planifierTache(tache);
                    creneau.setBloque(bloque);
                    if (creneau.getDuration().toMinutes() - tache.getDuree() >= 30) {
                        LocalTime heurFin = creneau.getHeureDebut().plusMinutes(tache.getDuree());
                        LocalTime nouvHeureDeb = creneau.getHeureDebut().plusMinutes(tache.getDuree());
                        Creneau nouvCreneau = new Creneau(nouvHeureDeb, creneau.getHeureFin(), EtatCreneau.Libre, null);
                        creneau.setHeureFin(heurFin);
                        this.listeCreneaux.add(nouvCreneau);
                    }
                    return true;
                } else return false;
            }
        }
        return false;
    }

    public TacheDecomposable plannifierTacheDecomp(TacheDecomposable tache) {
        Iterator<Creneau> iterator = this.listeCreneaux.iterator();
        TacheDecomposable nouvTache = tache;
        while (iterator.hasNext() && nouvTache != null) {
            Creneau creneau = iterator.next();
            if (creneau.getDuration().toMinutes() >= 30 && creneau.getEtatCreneau() == EtatCreneau.Libre) {
                nouvTache = creneau.plannifierTacheDecomp(nouvTache);
            }
        }
        return nouvTache;
    }


    public LocalDate getDate() {
        return this.date;
    }

    public void ajouterCreneau(Creneau creneau) {
        this.listeCreneaux.add(creneau);
    }

    public int compareTo(Jour jour) {
        return this.date.compareTo(jour.getDate());
    }
    public TreeSet<Creneau> getListeCreneaux(){return this.listeCreneaux;}
    public int TacheComplet(){
        int count=0;
        for(Creneau creneau :listeCreneaux){
            if(creneau.getTache().getEtatAvancement()==EtatAvancement.Completed){
                count++;
            }
        }
        return count;
    }
    public boolean passedMin(){
        return this.TacheComplet()>this.minTask;
    }
}
