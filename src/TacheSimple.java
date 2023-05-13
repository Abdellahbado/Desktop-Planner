import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class TacheSimple extends Tache{
    private int periodicite;


    public TacheSimple(String nom, long duree, Priorite priorite, Date dateLimite,
                       Categorie categorie, EtatAvancement etatAvancement, int periodicite) {
        super(nom, duree, priorite, dateLimite, categorie, etatAvancement);
        this.periodicite = periodicite;
    }
}
