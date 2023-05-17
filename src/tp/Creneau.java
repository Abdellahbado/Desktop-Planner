package tp;

import java.time.LocalTime;
import java.time.Duration;
public class Creneau implements Comparable <Creneau>{
	 private Tache tache;
	    private LocalTime heureDebut;
	    private LocalTime heureFin;
	    private EtatCreneau etatCreneau;
      static long dureeMinimale=30;
	    public Creneau(LocalTime heure1, LocalTime heure2, EtatCreneau etCr, Tache tache) {
	        this.heureDebut = heure1;
	        this.heureFin = heure2;
	        this.tache = tache;
	        this.etatCreneau = etCr;
	        
	    }

	    
	    
	    
	    public LocalTime getHeureDebut() {
	        return heureDebut;
	    }

	    public void setHeureDebut(LocalTime heureDebut) {
	        this.heureDebut = heureDebut;
	    }

	    public LocalTime getHeureFin() {
	        return heureFin;
	    }

	    public void setHeureFin(LocalTime heureFin) {
	        this.heureFin = heureFin;
	    }

	    public Tache getTache() {
	        return tache;
	    }

	    public void setTache(Tache tache) {
	        this.tache = tache;
	    }

	    public EtatCreneau getEtatCreneau() {
	        return etatCreneau;
	    }
        
	    public void setEtatCreneau(EtatCreneau etatCreneau) {
	        this.etatCreneau = etatCreneau;
	    }
	    public void afficher(){
	        System.out.println("l'heure du début de ce créneau:"+this.heureDebut);
	        System.out.println("l'heure de la fin de ce créneau:"+this.heureFin);
	        System.out.println("libre (true), occupé (false):"+this.etatCreneau);
	        if(tache != null) {
	            tache.afficher();
	        }
	        System.out.println("----------");

	    }

		@Override
		 public int compareTo(Creneau other) {
	        return this.heureDebut.compareTo(other.getHeureDebut());
	    }
}
