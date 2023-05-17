package tp;

import java.time.LocalTime;
import java.util.Date;

public  class Tache {
	  protected String nom;
	    protected long duree;
	    protected Priorite priorite;
	    protected Date dateLimite;
	    protected Categorie categorie;
	    protected EtatAvancement etatAvancement;
	    
	    
	    
	    public void afficher() {
	        System.out.println("Nom de la tâche: " + this.nom);
	        System.out.println("Durée de la tâche: " + this.duree + " minutes");
	        System.out.println("Priorité de la tâche: " + this.priorite);
	        System.out.println("Date limite de la tâche: " + this.dateLimite);
	        System.out.println("Catégorie de la tâche: " + this.categorie);
	        System.out.println("État d'avancement de la tâche: " + this.etatAvancement);
	    }
	    
	    public long getDuree() {
	    	return this.duree;
	    }
	    public void setEtatAvancement(EtatAvancement e) {
	         this.etatAvancement = e;
	    }
	    public EtatAvancement getEtatAvancement() {
	       return  this.etatAvancement ;
	    }
	    
}
