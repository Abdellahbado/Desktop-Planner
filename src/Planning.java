import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
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

    public ArrayList<Creneau> getCreneaux() {
        ArrayList<Creneau> list = new ArrayList<>();
        for (Jour jour : this.listeJours) {
            list.addAll(jour.getListeCreneaux());
        }
        return list;
    }

    public ArrayList<String> getListNomTaches() {
        ArrayList<String> list = new ArrayList<>();
        int i = 0;
        for (Jour jour : this.listeJours) {
            for (Creneau creneau : jour.getListeCreneaux()) {
                i++;
                if (creneau.getTache() == null) {
                    list.add("Aucune tâche assignée");
                } else {
                    list.add(creneau.getTache().getNom());
                }
            }
        }
        return list;
    }

    public ArrayList<Tache> getListTaches() {
        ArrayList<Tache> list = new ArrayList<>();
        for (Jour jour : this.listeJours) {
            for (Creneau creneau : jour.getListeCreneaux()) {
                list.add(creneau.getTache());
            }
        }
        return list;
    }


    public ArrayList<Creneau> getCreneauxDate(LocalDate date) {
        ArrayList<Creneau> list = new ArrayList<>();
        for (Jour jour : listeJours) {
            if (jour.getDate().equals(date)) {
                list.addAll(jour.getListeCreneaux());
                break;
            }
        }
        return list;
    }

    public ArrayList<Tache> getTachesDate(LocalDate date) {
        ArrayList<Tache> list = new ArrayList<>();
        for (Jour jour : listeJours) {
            if (jour.getDate().equals(date)) {
                for (Creneau creneau : jour.getListeCreneaux()) {
                    list.add(creneau.getTache());
                }
            }
        }
        return list;
    }


    public ArrayList<Jour> getJourrDupl() {
        ArrayList<Jour> list = new ArrayList<>();
        for (Jour jour : listeJours) {
            int nbCr = jour.getNombreCreneaux();
            for (int i = 0; i < nbCr; i++) {
                list.add(jour);
            }
        }
        return list;
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

    public TreeSet<Jour> getListeJours() {
        return listeJours;
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

    public List<Creneau> SousTacheMN(String name) {
        List<Creneau> listCr = new ArrayList<>();
        Tache tache;
        for (Jour jour : this.listeJours) {
            for (Creneau creneau : jour.getListeCreneaux()) {
                tache = creneau.getTache();
                if (tache.getNom().equals(name)) {
                    listCr.add(creneau);
                }
            }
        }

        return listCr;
    }


    public void setEtatAvancTache(LocalDate date, String tacheNom, EtatAvancement etatAvancement) {
        for (Jour jour : listeJours) {
            if (jour.getDate().equals(date)) {
                for (Creneau creneau : jour.getListeCreneaux()) {
                    if (creneau.getTache() != null)
                        if (creneau.getTache().getNom().equals(tacheNom)) {
                            creneau.getTache().setEtatAvancement(etatAvancement);
                            break;
                        }
                }
                break;
            }
        }
    }

    public boolean rePlanifier(TacheDecomposable tache, long dureeSupp, long dureeMin) {

        TacheDecomposable tacheReplanifier = new TacheDecomposable(tache.getNom(), dureeSupp, tache.getPriorite(), tache.getCategorie());
        List<Creneau> LesCr = SousTacheMN(tache.getNom());
        if (LesCr.get(1).getEtatCreneau() != EtatCreneau.Bloque) {
            TacheDecomposable tacheDec = (TacheDecomposable) LesCr.get(LesCr.size() - 1).getTache();
            tacheReplanifier.setNumSousTache(tacheDec.getNumSousTache() + 1);
            tacheReplanifier.setNomSousTache(tache.getNom() + tacheDec.getNumSousTache());
            tacheReplanifier.setEtatAvancement(EtatAvancement.notRealised);
            return plannifierTacheDecomp(tacheReplanifier);
        } else {
            return false;
        }
    }


    public boolean rePlanifier(TacheDecomposable tache, long dureeSupp, long dureeMin, LocalDate dateLimite) {

        TacheDecomposable tacheReplanifier = new TacheDecomposable(tache.getNom(), dureeSupp, tache.getPriorite(), tache.getCategorie());
        List<Creneau> LesCr = SousTacheMN(tache.getNom());
        if (LesCr.get(1).getEtatCreneau() != EtatCreneau.Bloque) {

            TacheDecomposable tacheDec = (TacheDecomposable) LesCr.get(LesCr.size() - 1).getTache();
            tacheReplanifier.setNumSousTache(tacheDec.getNumSousTache() + 1);
            tacheReplanifier.setNomSousTache(tache.getNom() + tacheDec.getNumSousTache());
            tacheReplanifier.setEtatAvancement(EtatAvancement.notRealised);
            return plannifierTacheDecomp(tacheReplanifier);
        } else {
            return false;
        }
    }

    public Jour rentable() {
        Jour rentDay = listeJours.first();
        for (Jour jour : listeJours) {
            if (jour.TacheComplet() > rentDay.TacheComplet()) {
                rentDay = jour;
            }
        }
        return rentDay;
    }

    public long categTime(Categorie categorie) {
        long time = 0;
        for (Jour jour : listeJours) {
            for (Creneau creneau : jour.getListeCreneaux()) {
                if (creneau.getTache().getCategorie() == categorie) {
                    time = time + creneau.getTache().getDuree();
                }
            }
        }
        return time;
    }

    public long rendRate(Jour jour) throws ArithmeticException {
        int total = 0;
        for (Creneau creneau : jour.getListeCreneaux()) {
            if (creneau.getTache() != null) {
                total++;
            }
        }
        return total / jour.TacheComplet();
    }

    public Jour getJourByDate(LocalDate date){
        for(Jour jour:listeJours){
            if(jour.getDate().equals(date)) return jour;
        }
        return null;
    }

    public long moyRend() {
        int totalDays = 0;
        long totalRate = 0;


        for (Jour day : listeJours) {
            totalRate = totalRate + this.rendRate(day);
            totalDays++;
        }
        return totalRate / totalDays;
    }
}
