package tp;

import java.util.Date;

public class SousTache extends Tache {
	  private long dureeSousTache;
	    private int numeroTache;
	    private String nomSousTache;

	    public SousTache(String nom, long duree, Priorite priorite, Date dateLimite,
	                             Categorie categorie, EtatAvancement etatAvancement) {
	    	  this.nom = nom;
		        this.duree = duree;
		        this.priorite = priorite;
		        this.dateLimite = dateLimite;
		        this.categorie = categorie;
		        this.etatAvancement = etatAvancement;
	        this.numeroTache = 0;
	        this.dureeSousTache = duree;
	       
	    }
}
