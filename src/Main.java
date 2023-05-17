import java.time.LocalDate;
import java.time.LocalTime;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {
        TacheDecomposable tacheDecomposable = new TacheDecomposable("TP POO", 480, Priorite.Medium, LocalDate.of(2023, 5, 25), Categorie.STUDIES, EtatAvancement.notRealised, "TP POO 1", 1);

        Creneau creneau1 = new Creneau(LocalTime.of(9, 0), LocalTime.of(10, 0), EtatCreneau.Libre, null);
        Creneau creneau2 = new Creneau(LocalTime.of(10, 0), LocalTime.of(11, 0), EtatCreneau.Libre, null);
        Creneau creneau3 = new Creneau(LocalTime.of(13, 0), LocalTime.of(14, 0), EtatCreneau.Libre, null);
        Creneau creneau4 = new Creneau(LocalTime.of(14, 0), LocalTime.of(15, 0), EtatCreneau.Libre, null);
        Creneau creneau5 = new Creneau(LocalTime.of(9, 0), LocalTime.of(10, 0), EtatCreneau.Libre, null);
        Creneau creneau6 = new Creneau(LocalTime.of(10, 0), LocalTime.of(11, 0), EtatCreneau.Libre, null);
        Creneau creneau7 = new Creneau(LocalTime.of(13, 0), LocalTime.of(14, 0), EtatCreneau.Libre, null);
        Creneau creneau8 = new Creneau(LocalTime.of(14, 0), LocalTime.of(15, 0), EtatCreneau.Libre, null);
        TreeSet<Creneau> listCreneau = new TreeSet<>();
        listCreneau.add(creneau1);
        listCreneau.add(creneau2);
        listCreneau.add(creneau3);
        listCreneau.add(creneau4);
        TreeSet<Creneau> listCreneau2 = new TreeSet<>();
        listCreneau2.add(creneau5);
        listCreneau2.add(creneau6);
        listCreneau2.add(creneau7);
        listCreneau2.add(creneau8);
        Jour jour = new Jour(LocalDate.of(2023, 5, 17), listCreneau);
        Jour jour1 = new Jour(LocalDate.of(2023, 5, 18), listCreneau2);

        TreeSet<Jour> listeJours = new TreeSet<>();
        listeJours.add(jour);
        listeJours.add(jour1);
        Calendrier calendrier = new Calendrier(listeJours);
        boolean bool = calendrier.plannifierTacheDecomp(tacheDecomposable);
        calendrier.afficherListeJours();
        System.out.println("Boolean = " + bool);
    }
}