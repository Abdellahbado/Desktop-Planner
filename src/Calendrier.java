import java.time.LocalDate;
import java.time.LocalTime;
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

    public void ajouterJour(Jour jour) {
        listeJours.add(jour);
    }

    public boolean plannifierTacheAuto(Tache tache, int n) {
        Iterator<Jour> iterator = this.listeJours.iterator();
        int i = 0;
        boolean tachePlanifiee = false;
        while (iterator.hasNext()) {

            Jour jour = iterator.next();
            if ((i % n) == 0) {
                if (jour.plannifierTacheAuto(tache)) tachePlanifiee = true;
            }
            i++;
        }
        return tachePlanifiee;
    }

    public boolean plannifierTacheAuto(Tache tache, LocalDate dateLimit, int n) {
        Iterator<Jour> iterator = this.listeJours.iterator();
        int i = 0;
        boolean tachePlanifiee = false;
        while ((iterator.hasNext()) && (iterator.next().getDate().compareTo(dateLimit) < 0)) {
            Jour jour = iterator.next();
            if ((i % n) == 0) {
                if (jour.plannifierTacheAuto(tache)) tachePlanifiee = true;
            }
            i++;
        }
        return false;
    }

    public boolean plannifierTacheDecomp(TacheDecomposable tache) {
        // on parcours les jours et pour chaque jour on appelle plannifierDecomp
        // si elle retourne une tache donc il ya encore une tahce qui n'a pas eté programmée
        for (Jour jour : listeJours) {
            tache.afficher();
            tache = jour.plannifierTacheDecomp(tache);
            if (tache == null) {
                return true; // la tache a ete planifier
            }
        }
        return false;
    }


    public boolean plannifierTacheDecomp(TacheDecomposable tache, LocalDate dateLimite) {
        for (Jour jour : listeJours) {
            if (jour.getDate().isBefore(dateLimite)) {
                tache.afficher();
                tache = jour.plannifierTacheDecomp(tache);
                if (tache == null) {
                    return true; // la tache a ete planifier
                }
            } else {
                break;
            }
        }
        return false;
    }


    public void introduitCreneau(LocalTime heurD, LocalTime heurF, LocalDate date) {
        boolean trouv = false;
        Iterator<Jour> iterator = this.listeJours.iterator();
        while (iterator.hasNext() && !trouv) {
            Jour jour = iterator.next();
            if (jour.getDate().equals(date)) {
                Creneau creneau = new Creneau(heurD, heurF, EtatCreneau.Libre, null);
                jour.ajouterCreneau(creneau);
                trouv = true;
            }
        }
    }


}
