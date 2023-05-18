package tp;
import java.util.Scanner;
import java.util.*;
import java.time.*;

public class Calendrier {
  
    TreeSet<Jour> jours = new TreeSet<>();
     public Calendrier(Jour j) {
    	 jours.add(j);
     }
     
     public void setJours(LocalDate debut,LocalDate fin  ) {
    	
    	jours.clear();
        
    	while(debut.isBefore(fin)) {
    		Jour j = new Jour(debut);
    	     
    		jours.add(j);
    	debut =	debut.plusDays(1);
    	}	
    	 jours.forEach(jour -> {System.out.println("jb"+jour.getDate());});	
    
     }
     
  public  void ajouterCreneau(LocalDate j,LocalTime debut ,LocalTime fin ) {
	  jours.forEach(jour -> {
		  if(jour.getDate().isEqual(j)) {
			  jour.ajouterCreneau(debut,fin);
		  }
	  });
     }
     
    public void planifierManuellement(LocalDate date,LocalTime debut, TacheSimple tache) {
    	
        jours.forEach(jour -> {
            if(date.isEqual(jour.getDate())) {
                jour.planifierManuellement(tache);
           
            }
            
            if((!date.isEqual(jour.getDate()))&&(tache.getPeriodicite()>1) && (jours.headSet(jour).size() % tache.getPeriodicite()) == 0 && (tache.getEtatAvancement()== EtatAvancement.InProgress))
            {   tache.setEtatAvancement(null);
            	jour.planifierManuellement(debut,tache);
            	if(tache.getEtatAvancement()== null){tache.setEtatAvancement(EtatAvancement.InProgress);}
            }
        });
    }
}
