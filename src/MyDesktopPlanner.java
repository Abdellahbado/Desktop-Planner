import java.io.*;
import java.util.HashSet;

public class MyDesktopPlanner implements Serializable {
    private HashSet<Utilisateur> listeUtilisateures;

    public MyDesktopPlanner(HashSet<Utilisateur> listeUtilisateures) {
        this.listeUtilisateures = listeUtilisateures;
    }

    public void signUp(String pseudo, String passWord) {

        listeUtilisateures.add(new Utilisateur(pseudo, passWord));
    }

    public void serialize(String filename) {
        try (FileOutputStream fileOut = new FileOutputStream(filename);
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(this);
            System.out.println("Object stored in " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public MyDesktopPlanner deserialize(String filename) {
        MyDesktopPlanner myDesktopPlanner = null;
        try (FileInputStream fileIn = new FileInputStream(filename);
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            myDesktopPlanner = (MyDesktopPlanner) in.readObject();
            System.out.println("Object deserialized from " + filename);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return myDesktopPlanner;
    }

    public boolean signIn(String pseudo, String passWord) {
        Utilisateur u = new Utilisateur(pseudo, passWord);
        for (Utilisateur user : listeUtilisateures) {
            if (user.equals(u)) {
                user.setConnected(true);
                return true;
            }
        }
        return false;

    }

    public void ajouterUtilisateur(Utilisateur utilisateur) {
        this.listeUtilisateures.add(utilisateur);
    }
}
