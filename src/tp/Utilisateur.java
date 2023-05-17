package tp;

import java.util.*;


public class Utilisateur {
	   static int lastId = 0;
	    private int id;

	    
	    private Calendrier calendrier;
	    private SortedSet<Projet> listeProjets = new TreeSet<Projet>();
	    private Compte compte;
	    private long dureeMinimale;
	    private Badge [] listeBadges;
	    private int encouragement;
	    
	    public void sAuthentifier(HashSet<Compte> comptes) {

              for (Compte compte :comptes) {
                if(compte.getPseudo() == this.compte.getPseudo()) {
                	this.compte.setConnected(true);
                	System.out.println("connected");
                }
                
                      }
              if(this.compte.getConnectivite()== false) {
            	  System.out.println("not connected");
              }
	    }
	    
	    
	    public void creerCompte(String pseudo, String motDePasse,HashSet<Compte> comptes ) {
	    	 boolean b= false;
	    	 for (Compte compte :comptes) {
	                if(compte.getPseudo() == pseudo) {
	                	System.out.println("this name already exist");
	                	b = true;
	                }
	                };
	                if(b==false) {
	                	this.compte = new Compte (pseudo,motDePasse);
	                }
	                comptes.add(this.compte);
	    }
	    
	    
	    public int hashCode() {
	 	   return this.compte.getPseudo().hashCode();
	    }
	    
}
