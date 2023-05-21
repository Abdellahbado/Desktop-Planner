package tp;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.TreeSet;

public class Jour  implements Comparable <Jour> {
	static int nombreJours = 0;
    private TreeSet<Creneau> listeCreneaux;
    private LocalDate date;
    private EtatAvancement etatAvancement;
    private int nbTachesMinimales;
    
    
    
    public Jour (LocalDate date)  {
       this.date = date;
       nombreJours++;
       DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm");
       String str1 = "00:00";
       String str2 = "00:00";
       LocalTime heurDeb = LocalTime.parse(str1, format);
       LocalTime heurFin = LocalTime.parse(str2, format);
       Creneau cr = new Creneau(heurDeb, heurFin, EtatCreneau.Libre, null);
       listeCreneaux = new TreeSet<Creneau>();
       listeCreneaux.add(cr);
       
    }
    
    
     public void setCrenaux(TreeSet<Creneau> listeCreneaux) {
    	 this.listeCreneaux = listeCreneaux;
     }
    
    
    public void afficher() {
    	 System.out.println(this.date);
        for(Creneau cr : listeCreneaux) {
           cr.afficher();
        }
        System.out.println("========");
     }

    public LocalDate getDate() {
        return date;
     }
    
    public void planifierManuellement(LocalTime debut ,TacheSimple tache) {
        TreeSet<Creneau> nouveauxCreneaux = new TreeSet<>();
       
        for (Creneau creneau : listeCreneaux) {
            if ((creneau.getEtatCreneau() == EtatCreneau.Libre) 
                    && (Math.abs(Duration.between(creneau.getHeureFin(),creneau.getHeureDebut()).toMinutes()) >= tache.getDuree())
                    &&(tache.etatAvancement != EtatAvancement.InProgress
                    && (creneau.getHeureDebut().equals(debut)))
                      ) {
                tache.setEtatAvancement(EtatAvancement.InProgress);
                
            	if ((Math.abs(Duration.between(creneau.getHeureFin(),creneau.getHeureDebut()).toMinutes()) - tache.getDuree() < Creneau.dureeMinimale)
                	) {
                    creneau.setEtatCreneau(EtatCreneau.Occupe);
                    creneau.setTache(tache);
                    nouveauxCreneaux.add(creneau);
                   
                } else {
                    Creneau nouveauCreneau = new Creneau(creneau.getHeureDebut(), creneau.getHeureDebut().plusMinutes(tache.getDuree()), EtatCreneau.Occupe, tache);
                    creneau.setHeureDebut(creneau.getHeureDebut().plusMinutes(tache.getDuree()));
     
                  
                    nouveauxCreneaux.add(nouveauCreneau);
                    nouveauxCreneaux.add(creneau);
                }
                
            	  listeCreneaux.clear();
                  
                  listeCreneaux.addAll(nouveauxCreneaux);
                  
                  this.afficher();
            }else {
            	tache.setEtatAvancement(EtatAvancement.Unscheduled);
            }
             
        }
      
      
    }

    public void ajouterCreneau(LocalTime debut, LocalTime fin) {
    	Creneau cr = new Creneau (debut, fin , EtatCreneau.Libre,null);
    	listeCreneaux.add(cr);
    }
    
    
	@Override
	 public int compareTo(Jour autreJour) {
        return this.date.compareTo(autreJour.getDate());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Jour other = (Jour) obj;
        return Objects.equals(date, other.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date);
    }
    
    
    
    
    
    }
