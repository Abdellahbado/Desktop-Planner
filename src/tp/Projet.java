package tp;

import java.util.HashSet;

public class Projet {
	 private String nomProjet;
	    private String description;
	    private HashSet<Tache> listeTaches;

	    public Projet (String nomProjet, String description, HashSet<Tache> listeTaches) {
	        this.nomProjet = nomProjet;
	        this.description = description;
	        this.listeTaches = new HashSet<Tache>(listeTaches);
	    }
}
