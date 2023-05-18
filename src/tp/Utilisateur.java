package tp;

import java.util.*;


public class Utilisateur implements Comparable<Utilisateur> {
	  // static int lastId = 0;
	  //  private int id;
	    private String pseudo;
	    private String motDePasse;
	    private boolean connected;

	    
	    private Calendrier calendrier;
	    private SortedSet<Projet> listeProjets = new TreeSet<Projet>();
	 //   private long dureeMinimale;
	    private Badge [] listeBadges;
	    private int encouragement;
	  
	    
	    public Utilisateur (String pseudo, String motDePasse) {
	    	this.pseudo = pseudo;
	    	this.motDePasse = motDePasse;
	    	
	    } 
	    
	    
	    public void setConnected(boolean state) {
	    	this.connected = state;
	    }
	    
	    
	    
	    @Override
	    public boolean equals(Object obj) {
	        if (this == obj) {
	            return true; 
	        }

	        if (obj == null || getClass() != obj.getClass()) {
	            return false; 
	        }

	        Utilisateur otherUser = (Utilisateur) obj; 

	       
	        return Objects.equals(pseudo, otherUser.pseudo)
	                && Objects.equals(motDePasse, otherUser.motDePasse);
	    }
	    @Override
	    public int hashCode() {
	        return Objects.hash(pseudo);
	    }


		@Override
		public int compareTo(Utilisateur o) {
			
			return 0;
		}
	    
}
