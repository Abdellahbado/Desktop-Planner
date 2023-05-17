package tp;

import java.util.Date;

public class TacheSimple extends Tache {
	 private int periodicite;

	    
	    public TacheSimple(String nom, long duree, Priorite priorite, Date dateLimite,
	                       Categorie categorie, EtatAvancement etatAvancement, int periodicite) {
	        this.nom = nom;
	        this.duree = duree;
	        this.priorite = priorite;
	        this.dateLimite = dateLimite;
	        this.categorie = categorie;
	        this.etatAvancement = etatAvancement;
	        this.periodicite = periodicite;
	    }
	    
	  public int getPeriodicite() {
		  return this.periodicite;
	  }
}
