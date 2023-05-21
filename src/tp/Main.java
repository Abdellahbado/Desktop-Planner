package tp;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.TreeSet;

public class Main {
	
	
	public static void main(String[] args) {
		
		Jour j1 = new Jour ( LocalDate.of(2022, 1, 1));
		Jour j2 = new Jour ( LocalDate.of(2022, 1, 2));
		Jour j3 = new Jour ( LocalDate.of(2022, 1, 3));
		 TreeSet<Jour> jours = new TreeSet<>();
		 jours.add(j1);
		 jours.add(j2);
		 jours.add(j3);
		Date mydate = new  Date(123, 4, 12, 14, 30, 0);
		 TacheSimple t = new TacheSimple("n",20,Priorite.High, mydate,Categorie.HEALTH,null,2);
	   
	    Calendrier ca = new Calendrier(j1);
	    
	   ca.setJours( LocalDate.of(2022, 1, 2), LocalDate.of(2022, 1, 10));
	   ca.ajouterCreneau(LocalDate.of(2022, 1, 2), LocalTime.of(12, 0), LocalTime.of(13, 0));
	    ca.planifierManuellement( LocalDate.of(2022, 1, 2), null, t);
	    
	}
 
}
