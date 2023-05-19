import java.util.*;

public class Calendrier {
    private TreeSet<Jour> listeJours;
    private int TacheCons;
    private int nbTachesMinimales;


    public Calendrier() {
        listeJours = new TreeSet<Jour>();
    }

    public boolean plannifierTacheAuto(Tache tache) {
        Iterator<Jour> iterator = this.listeJours.iterator();
        while (iterator.hasNext()) {
            Jour jour = iterator.next();
            if (jour.plannifierTacheAuto(tache)) return true;
        }
        return false;
    }



    public void creerCreneau(Creneau creneau, Jour jour) {
        Iterator<Jour> iterator = this.listeJours.iterator();
        boolean trouv = false;
        while ((iterator.hasNext()) && (!trouv)) {
            Jour j = iterator.next();
            if (j.getDate().equals(jour.getDate())) {
                j.creerCreneau(creneau);
            }
        }
    }

    public int incTacheCons(Date date) {
        Iterator<Jour> iter = listeJours.iterator();
        int TacheCons = 0;
        int n = 0;
        while (iter.hasNext()) {
            Jour jour = iter.next();
            n++;
            if (jour.getDate().equals(date)) {
                jour.TacheComplet();
                if (jour.getNombrTache() >= this.nbTachesMinimales) {
                    Iterator<Jour> remainingIter = listeJours.iterator();
                    for (int i = 0; i < n; i++) {
                        Jour jourCourant = remainingIter.next();
                        if (jourCourant.getNombrTache() >= this.nbTachesMinimales) {
                            TacheCons++;
                        } else {
                            break;
                        }
                    }
                } else {
                    break;
                }
            }
        }
        return TacheCons;
    }
}
