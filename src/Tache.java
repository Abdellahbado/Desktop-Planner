import java.io.Serializable;
import java.time.LocalDate;

public abstract class Tache implements Serializable,Comparable<Tache> {
    protected String nom;
    protected long duree;
    protected Priorite priorite;
    protected LocalDate dateLimite;
    protected Categorie categorie;
    protected EtatAvancement etatAvancement;


    // protected boolean decomposable;
    public Tache(String nom, long duree, Priorite priorite, LocalDate dateLimite,
                 Categorie categorie, EtatAvancement etatAvancement) {
        this.nom = nom;
        this.duree = duree; //en minutes
        this.priorite = priorite;
        this.dateLimite = dateLimite;
        this.categorie = categorie;
        this.etatAvancement = etatAvancement;
    }


    protected void afficher() {
        System.out.println("Nom de la tâche: " + this.nom);
        System.out.println("Durée de la tâche: " + this.duree + " minutes");
        System.out.println("Priorité de la tâche: " + this.priorite);
        System.out.println("Date limite de la tâche: " + this.dateLimite);
        System.out.println("Catégorie de la tâche: " + this.categorie);
        System.out.println("État d'avancement de la tâche: " + this.etatAvancement);
        System.out.println("From tache  " );
    }

    public boolean delaiDepasse(LocalDate date) {
        if (this.dateLimite == null) {
            return false;
        }
        return false;
    }

    public void classerTache(Categorie categorie) {
        this.categorie = categorie;
    }

    public void renommer(String nouveauNom) {
        this.nom = nouveauNom;
    }

    public void changerEtat(EtatAvancement nouvelEtat) {
        this.etatAvancement = nouvelEtat;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Long getDuree() {
        return duree;
    }

    public void setDuree(Long duree) {
        this.duree = duree;
    }

    public Priorite getPriorite() {
        return priorite;
    }

    public void setPriorite(Priorite priorite) {
        this.priorite = priorite;
    }

    public LocalDate getDateLimite() {
        return dateLimite;
    }

    public void setDateLimite(LocalDate dateLimite) {
        this.dateLimite = dateLimite;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public EtatAvancement getEtatAvancement() {
        return etatAvancement;
    }

    public void setEtatAvancement(EtatAvancement etatAvancement) {
        this.etatAvancement = etatAvancement;
    }

    public int hashode() {
        return (this.nom + this.categorie).hashCode();
    }

    public boolean equals(Object o) {
        return this.hashCode() == ((Tache) o).hashCode();
    }

    public int compareTo(Tache tache1){
        return this.priorite.compareTo(tache1.priorite);
    }
}
