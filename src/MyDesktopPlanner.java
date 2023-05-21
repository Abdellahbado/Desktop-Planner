import java.io.Serializable;
import java.util.HashSet;

public class MyDesktopPlanner implements Serializable {
    private HashSet<Utilisateur> listeUtilisateures;

    public MyDesktopPlanner(HashSet<Utilisateur> listeUtilisateures) {
        this.listeUtilisateures = listeUtilisateures;
    }

    public void signUp(String pseudo, String passWord) {

        listeUtilisateures.add(new Utilisateur(pseudo, passWord));
    }


    public void signIn(String pseudo, String passWord) {
        Utilisateur u = new Utilisateur(pseudo, passWord);
        for (Utilisateur user : listeUtilisateures) {
            if (user.equals(u)) {
                user.setConnected(true);
            }
        }


    }

    public void ajouterUtilisateur(Utilisateur utilisateur) {
        this.listeUtilisateures.add(utilisateur);
    }
}
