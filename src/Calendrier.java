import java.time.LocalDate;
import java.util.Iterator;
import java.util.TreeSet;

public class Calendrier {
    private TreeSet<Jour> listeJours;

    public Calendrier(TreeSet<Jour> listeJours) {
        this.listeJours = listeJours;
    }

    public void afficherListeJours() {
        for (Jour jour : listeJours) {
            jour.afficher();
        }
    }

    public boolean plannifierTacheAuto(Tache tache) {
        Iterator<Jour> iterator = this.listeJours.iterator();
        while (iterator.hasNext()) {
            Jour jour = iterator.next();
            if (jour.plannifierTacheAuto(tache)) return true;
        }
        return false;
    }

    public boolean plannifierTacheAuto(Tache tache, LocalDate dateLimit) {
        Iterator<Jour> iterator = this.listeJours.iterator();
        while ((iterator.hasNext()) && (iterator.next().getDate().compareTo(dateLimit) < 0)) {
            Jour jour = iterator.next();
            if (jour.plannifierTacheAuto(tache)) return true;
        }
        return false;
    }

    public boolean plannifierTacheDecomp(TacheDecomposable tache) {
        Iterator<Jour> iterator = this.listeJours.iterator();
        // on parcours les jours et pour chaque jour on appelle plannifierDecomp
        // si elle retourne une tache donc il ya encore une tahce qui n'a pas eté programmée
        TacheDecomposable t1 = tache;

        while (iterator.hasNext()) {
            Jour jour = iterator.next();
            t1.afficher();
            t1 = jour.plannifierTacheDecomp(t1);
            if (t1 == null) return true; // la tache a ete planifier
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
