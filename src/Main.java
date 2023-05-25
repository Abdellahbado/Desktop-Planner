import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;


public class Main {
    public static void main(String[] args) {
        TacheDecomposable tacheDecomposable11 = new TacheDecomposable("TP POO", 240, Priorite.Low, LocalDate.of(2023, 5, 25), Categorie.STUDIES, EtatAvancement.notRealised, "TP POO 1", 1);
        TacheDecomposable tacheDecomposable = new TacheDecomposable("TP POO", 240, Priorite.High, LocalDate.of(2023, 5, 25), Categorie.STUDIES, EtatAvancement.notRealised, "TP POO 1", 1);
        TacheSimple tacheSimple = new TacheSimple("Révision CF", 60, Priorite.Medium,
                LocalDate.now().plusDays(7), Categorie.STUDIES, EtatAvancement.Unscheduled, 2);
        Creneau creneau1 = new Creneau(LocalTime.of(9, 0), LocalTime.of(10, 0), EtatCreneau.Libre, null);
        Creneau creneau2 = new Creneau(LocalTime.of(10, 0), LocalTime.of(11, 0), EtatCreneau.Libre, null);
        Creneau creneau3 = new Creneau(LocalTime.of(13, 0), LocalTime.of(14, 0), EtatCreneau.Libre, null);
        Creneau creneau4 = new Creneau(LocalTime.of(14, 0), LocalTime.of(15, 0), EtatCreneau.Libre, null);
        Creneau creneau5 = new Creneau(LocalTime.of(9, 0), LocalTime.of(10, 0), EtatCreneau.Libre, null);
        Creneau creneau6 = new Creneau(LocalTime.of(10, 0), LocalTime.of(11, 0), EtatCreneau.Libre, null);
        Creneau creneau7 = new Creneau(LocalTime.of(13, 0), LocalTime.of(14, 0), EtatCreneau.Libre, null);
        Creneau creneau8 = new Creneau(LocalTime.of(14, 0), LocalTime.of(15, 0), EtatCreneau.Libre, null);
        Planning planning = new Planning(LocalDate.of(2023, 5, 17), LocalDate.of(2023, 5, 28));
        planning.introduitCreneau(creneau1, LocalDate.of(2023, 5, 17));
        planning.introduitCreneau(creneau2, LocalDate.of(2023, 5, 17));
        planning.introduitCreneau(creneau3, LocalDate.of(2023, 5, 18));
        planning.introduitCreneau(creneau4, LocalDate.of(2023, 5, 19));
        planning.introduitCreneau(creneau5, LocalDate.of(2023, 5, 20));
        planning.introduitCreneau(creneau6, LocalDate.of(2023, 5, 21));
        planning.introduitCreneau(creneau7, LocalDate.of(2023, 5, 22));
        planning.introduitCreneau(creneau8, LocalDate.of(2023, 5, 23));

        boolean bool1 = planning.plannifierTacheDecomp(tacheDecomposable);
        boolean bool = planning.plannifierTacheAuto(tacheSimple, tacheSimple.getPeriodicite());
        planning.afficherListeJours();
        System.out.println("Boolean = " + bool + " Boolean2 = " + bool1);
        Utilisateur utilisateur = new Utilisateur(planning);

        System.out.println("-----------------------------------------------User2-------------------------------------------------");
        // another user with different creneax and taches
        Planning planning1 = new Planning(LocalDate.of(2023, 5, 28), LocalDate.of(2023, 6, 6));
        TacheDecomposable tacheDecomposable1 = new TacheDecomposable("Faire CF", 240, Priorite.Low, LocalDate.of(2023, 6, 25), Categorie.STUDIES, EtatAvancement.notRealised, "Faire CF", 1);
        TacheSimple tacheSimple1 = new TacheSimple("CF ANA", 60, Priorite.High, LocalDate.of(2023, 06, 25), Categorie.WORK, EtatAvancement.notRealised, 1);
        Creneau creneau11 = new Creneau(LocalTime.of(9, 0), LocalTime.of(10, 0), EtatCreneau.Libre, null);
        Creneau creneau12 = new Creneau(LocalTime.of(10, 0), LocalTime.of(11, 0), EtatCreneau.Libre, null);
        Creneau creneau13 = new Creneau(LocalTime.of(13, 0), LocalTime.of(14, 0), EtatCreneau.Libre, null);
        Creneau creneau14 = new Creneau(LocalTime.of(14, 0), LocalTime.of(15, 0), EtatCreneau.Libre, null);
        Creneau creneau15 = new Creneau(LocalTime.of(9, 0), LocalTime.of(10, 0), EtatCreneau.Libre, null);
        Creneau creneau16 = new Creneau(LocalTime.of(10, 0), LocalTime.of(11, 0), EtatCreneau.Libre, null);
        Creneau creneau17 = new Creneau(LocalTime.of(13, 0), LocalTime.of(14, 0), EtatCreneau.Libre, null);
        Creneau creneau18 = new Creneau(LocalTime.of(14, 0), LocalTime.of(15, 0), EtatCreneau.Libre, null);
        planning1.introduitCreneau(creneau11, LocalDate.of(2023, 5, 29));
        planning1.introduitCreneau(creneau12, LocalDate.of(2023, 5, 29));
        planning1.introduitCreneau(creneau13, LocalDate.of(2023, 5, 29));
        planning1.introduitCreneau(creneau14, LocalDate.of(2023, 5, 29));
        planning1.introduitCreneau(creneau15, LocalDate.of(2023, 6, 1));
        planning1.introduitCreneau(creneau16, LocalDate.of(2023, 6, 2));
        planning1.introduitCreneau(creneau17, LocalDate.of(2023, 6, 3));
        planning1.introduitCreneau(creneau18, LocalDate.of(2023, 6, 4));
        //planning1.creerCreneauPeriodique(LocalTime.of(21, 0), LocalTime.of(22, 0), 1);
        boolean bool2 = planning1.plannifierTacheDecomp(tacheDecomposable1);
        boolean bool4 = planning1.plannifierTacheAuto(tacheSimple1, tacheSimple1.getPeriodicite());
        ///boolean bool4 = planning1.plannifierTacheManu(tacheSimple1, LocalDate.of(2023, 6, 2), LocalTime.of(21, 0), false);
        planning1.afficherListeJours();
        /*for (Jour jour : planning1.getJourrDupl()) {
            System.out.println("le jour est : " + jour.getDate());
        }*/
        System.out.println("------------------------------------------------------------------------");
        for (Creneau creneau : planning1.getCreneaux()) {
            creneau.afficher();
            System.out.println("-----------------------Fin creneau-------------------------------------------------");

        }
        System.out.println("------------------------------------------------------------------------");
        planning1.setEtatAvancTache(LocalDate.of(2023, 5, 29), "Faire CF", EtatAvancement.Completed);
        planning1.setEtatAvancTache(LocalDate.of(2023, 5, 29), "CF ANA", EtatAvancement.Completed);
        Utilisateur utilisateur1 = new Utilisateur(planning1);
        utilisateur1.countBadges();
        System.out.println(utilisateur1.getBadges().toString());



        //System.out.println("Boolean = " + bool2 + " Boolean2 = " + bool4);
        Projet projet = utilisateur1.creerProjet("test", "test");
        HashSet<Utilisateur> listeUtilisateures = new HashSet<>();
        listeUtilisateures.add(utilisateur);
        listeUtilisateures.add(utilisateur1);
        //MyDesktopPlanner myDesktopPlanner = new MyDesktopPlanner(listeUtilisateures);
        //String filename = "utilisateurs.ser"; // File name to store the serialized object
        //myDesktopPlanner.serialize(filename);
        //MyDesktopPlanner myDesktopPlanner1 = MyDesktopPlanner.deserialize(filename);
        /*try (FileOutputStream fileOut = new FileOutputStream(filename);
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(myDesktopPlanner);
            System.out.println("Object stored in " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }

        MyDesktopPlanner myDesktopPlanner1 = null;
        try (FileInputStream fileIn = new FileInputStream(filename);
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            myDesktopPlanner = (MyDesktopPlanner) in.readObject();
            System.out.println("Object deserialized from " + filename);

            // Use the deserialized MyDesktopPlanner object as needed
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }*/
        System.out.println("-------------------------------------------------------------------");
        System.out.println("Apres la serialisation: ");

/*
        Planning.serializePlanning(planning1, "planning.ser");
        Planning planning2 = Planning.deserializePlanningVariable("planning.ser");
        planning2.afficherListeJours();*/

    }
}