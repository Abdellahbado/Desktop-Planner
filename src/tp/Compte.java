package tp;

public class Compte {
	 private String pseudo;
	    private String motDePasse;
	    private boolean connected;

	    public Compte(String pseudo, String motDePass) {
	        this.setPseudo(pseudo);
	        this.setMotDePasse(motDePass);
	        this.setConnected(false);
	    }
	    
	    
	    public void setConnected(boolean connected) {
	        this.connected = connected;
	    }
	    public void setPseudo(String pseudo) {
	        this.pseudo = pseudo;
	    }
	    public void setMotDePasse(String motDePasse) {
	        this.motDePasse = motDePasse;
	    }
	    public String getPseudo() {
	      return  this.pseudo ;
	    }
	    public String getMotDePasse() {
	        return this.motDePasse ;
	    }
	    
	    public boolean getConnectivite() {
	        return this.connected ;
	    }
}
