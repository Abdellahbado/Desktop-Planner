import java.time.LocalDate;
import java.time.LocalTime;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {
        TacheDecomposable tacheDecomposable = new TacheDecomposable("TP POO", 300, Priorite.Medium, LocalDate.of(2023, 5, 25), Categorie.STUDIES, EtatAvancement.notRealised, "TP POO 1", 1);

        Creneau creneau1 = new Creneau(LocalTime.of(9, 0), LocalTime.of(10, 0), EtatCreneau.Libre, null);
        Creneau creneau2 = new Creneau(LocalTime.of(10, 0), LocalTime.of(11, 0), EtatCreneau.Libre, null);
        Creneau creneau3 = new Creneau(LocalTime.of(13, 0), LocalTime.of(14, 0), EtatCreneau.Libre, null);
        Creneau creneau4 = new Creneau(LocalTime.of(14, 0), LocalTime.of(15, 0), EtatCreneau.Libre, null);
        TreeSet<Creneau> listCreneau = new TreeSet<>();
        listCreneau.add(creneau1);
        listCreneau.add(creneau2);
        listCreneau.add(creneau3);
        listCreneau.add(creneau4);
        Jour jour =  new Jour(LocalDate.of(2023,5,17),listCreneau);
        TreeSet<Jour> listeJours = new TreeSet<>();
        listeJours.add(jour);
        Calendrier calendrier = new Calendrier(listeJours);
        boolean bool = calendrier.plannifierTacheDecomp(tacheDecomposable);
        calendrier.afficherListeJours();
        System.out.println("Boolean = "+bool);
    }
}