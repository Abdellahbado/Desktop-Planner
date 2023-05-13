import java.util.*;

public class Calendrier {
    private TreeSet<Jour> listeJours;

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
}
