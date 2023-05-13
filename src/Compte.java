public class Compte {
    private String pseudo;
    private String motDePasse;
    private boolean connected;

    public Compte(String pseudo, String motDePass) {
        this.setPseudo(pseudo);
        this.setMotDePasse(motDePass);
        this.setConnected(true);
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

}

