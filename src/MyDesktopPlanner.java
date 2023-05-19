import java.util.HashSet;

public class MyDesktopPlanner {
    private HashSet<Utilisateur> listeUtilisateures;

    public MyDesktopPlanner(HashSet<Utilisateur> listeUtilisateures) {
        this.listeUtilisateures = listeUtilisateures;
    }

    public void ajouterUtilisateur(Utilisateur utilisateur) {
        this.listeUtilisateures.add(utilisateur);
    }
}
