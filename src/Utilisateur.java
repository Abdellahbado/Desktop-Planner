import java.io.*;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.*;

public class Utilisateur implements Serializable {
    static int lastId = 0;
    private int id;
    private String pseudo;
    private String motDePasse;
    private boolean connected;
    private Planning calendrier;
    private SortedSet<Projet> listeProjets = new TreeSet<Projet>();
    private ArrayList<Tache> tachesIntroduites;
    private long dureeMinimale;
    private ArrayList<Badge> badges;
    private int encouragement;

    public Utilisateur(Planning planning) {
        this.calendrier = planning;
    }

    public Utilisateur(String pseudo, String motDePasse) {
        this.pseudo = pseudo;
        this.motDePasse = motDePasse;

    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Utilisateur otherUser = (Utilisateur) obj;

        return Objects.equals(pseudo, otherUser.pseudo)
                && Objects.equals(motDePasse, otherUser.motDePasse);
    }


    public void sauvgarderPlanning(Planning planning, String fileName) {
        try (FileOutputStream fileOut = new FileOutputStream(fileName);
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(planning);
            System.out.println("Planning saved to " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Planning chargerPlanning(String fileName) {
        Planning planning = null;
        try (FileInputStream fileIn = new FileInputStream(fileName);
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            planning = (Planning) in.readObject();
            System.out.println("Planning loaded from " + fileName);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return planning;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pseudo, motDePasse);
    }

    public ArrayList<Tache> getTachesIntroduites() {
        return this.tachesIntroduites;
    }

    public boolean plannifierTacheAuto(TacheSimple tache, int n) {
        return this.calendrier.plannifierTacheAuto(tache, n);
    }

    public boolean plannifierTacheAuto(TacheSimple tache, LocalDate dateLimit, int n) {
        return this.calendrier.plannifierTacheAuto(tache, dateLimit, n);
    }

    // this will return a tache introduit par user
    public Tache introduitTache(String nom, long duree, Priorite priorite, LocalDate dateLimite, Categorie categorie, String type, int periode) throws ParseException {

        Tache t;
        if (type.toLowerCase().compareTo("simple") == 0) {
            t = new TacheSimple(nom, duree, priorite, dateLimite, categorie, EtatAvancement.Unscheduled, periode);
        } else {
            t = new TacheDecomposable(nom, duree, priorite, dateLimite, categorie, EtatAvancement.Unscheduled, nom + " " + Integer.toString(1), 1);
        }
        this.tachesIntroduites.add(t);
        return t;
    }

    public void creerProjet(String titre, String description) {
        Projet projet = new Projet(titre, description, null);
        this.listeProjets.add(projet);
    }


    public boolean plannifierTacheInroduites() {
        if (this.tachesIntroduites == null) return false;
        boolean tachesPlanifiee = false;
        for (Tache tache : this.tachesIntroduites) {
            if (tache instanceof TacheDecomposable) {
                tachesPlanifiee = this.calendrier.plannifierTacheDecomp((TacheDecomposable) tache);
                if (!tachesPlanifiee) return false;
            } else if (tache instanceof TacheSimple) {
                tachesPlanifiee = this.calendrier.plannifierTacheAuto((TacheSimple) tache, ((TacheSimple) tache).getPeriodicite());
                if (!tachesPlanifiee) return false;
            }
        }
        return true;
    }

    // methode for sorting tachesIntroduites
    public void sortTachesIntroduitesByPriority() {
        Collections.sort(tachesIntroduites);
        // hna ndir reverse bach tkon high hiya lawla omb3d medium...
        Collections.reverse(tachesIntroduites);
    }

    public void setConnected(boolean state) {
        this.connected = state;
    }

    public void countBadges() {
        int passedMinCounter = 0;
        int goodCounter = 0;
        int veryGoodCounter = 0;

        for (Jour day : this.calendrier.getListeJours()) {
            if (day.passedMin()) {
                passedMinCounter++;
                if (passedMinCounter == 5) {
                    goodCounter++;
                    badges.add(Badge.Good);
                    passedMinCounter = 0;
                    if (goodCounter == 3) {
                        veryGoodCounter++;
                        badges.add(Badge.VeryGood);
                        goodCounter = 0;
                        if (veryGoodCounter == 3) {
                            badges.add(Badge.Excellent);
                            veryGoodCounter = 0;
                        }
                    }
                }
            } else {
                passedMinCounter = 0;
            }
        }
    }
}
