import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Iterator;
import java.util.TreeSet;

public class Planning implements Serializable {
    private TreeSet<Jour> listeJours;

    public Planning(LocalDate dateD, LocalDate dateF) {
        listeJours = new TreeSet<>();

        LocalDate currentDate = dateD;
        while (!currentDate.isAfter(dateF)) {
            this.listeJours.add(new Jour(currentDate, new TreeSet<Creneau>()));
            currentDate = currentDate.plusDays(1);
        }
    }

    public Planning(TreeSet<Jour> listeJours) {
        this.listeJours = listeJours;
    }

    public static void serializePlanning(Planning planningVariable, String filename) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(filename);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(planningVariable);
            objectOutputStream.close();
            fileOutputStream.close();
            System.out.println("Planning variable serialized and saved to file: " + filename);
        } catch (Exception e) {
            System.out.println("Error occurred while serializing the planning variable: " + e.getMessage());
        }
    }

    public static Planning deserializePlanningVariable(String filename) {
        Object planningVariable = null;
        try {
            FileInputStream fileInputStream = new FileInputStream(filename);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            planningVariable = objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
            System.out.println("Planning variable deserialized from file: " + filename);
        } catch (Exception e) {
            System.out.println("Error occurred while deserializing the planning variable: " + e.getMessage());
        }
        return (Planning) planningVariable;
    }

    public void afficherListeJours() {
        for (Jour jour : listeJours) {
            jour.afficher();
        }
    }

    public void ajouterJour(Jour jour) {
        listeJours.add(jour);
    }

    public boolean plannifierTacheAuto(TacheSimple tache, int n) {
        Iterator<Jour> iterator = this.listeJours.iterator();
        int i = 0;
        boolean tachePlanifiee = false;
        while (iterator.hasNext()) {

            Jour jour = iterator.next();
            if ((i % n) == 0) {
                if (jour.plannifierTacheAuto(tache)) {
                    tachePlanifiee = true;
                }
            }
            if (tachePlanifiee) i++;

        }
        return tachePlanifiee;
    }

    public boolean plannifierTacheManu(TacheSimple tache, LocalDate date, LocalTime heurD, boolean bloque) {
        Iterator<Jour> iterator = this.listeJours.iterator();
        int n = tache.getPeriodicite();
        int i = 0;
        boolean tachePlanifiee = false;
        while (iterator.hasNext()) {

            Jour jour = iterator.next();
            if (jour.getDate().equals(date)) {
                tachePlanifiee = jour.plannifierTacheManu(tache, heurD, bloque);
                return tachePlanifiee;
            }

        }
        return tachePlanifiee;
    }

    public void creerCreneauPeriodique(LocalTime heurD, LocalTime heurF, int p) {
        int i = 0;
        for (Jour jour : this.listeJours) {
            if (i % p == 0)
                jour.creerCreneauPeriodique(heurD, heurF);
            i++;
        }
    }

    public boolean plannifierTacheAuto(TacheSimple tache, LocalDate dateLimit, int n) {
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

    public void introduitCreneau(Creneau creneau, LocalDate date) {
        boolean trouv = false;
        Iterator<Jour> iterator = this.listeJours.iterator();
        while (iterator.hasNext() && !trouv) {
            Jour jour = iterator.next();
            if (jour.getDate().equals(date)) {
                jour.ajouterCreneau(creneau);
                trouv = true;
            }
        }
    }

}
