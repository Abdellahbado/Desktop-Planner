package tp;

import java.util.HashSet;
import java.util.Objects;

public class Application {
  
   private HashSet<Utilisateur> utilisateurs = new HashSet<>();
   
     public boolean signUp (String pseudo, String passWord) {
    	  
    	 utilisateurs.add(new Utilisateur(pseudo, passWord));
    	 return true;
     }
     
    
     public boolean signIn (String pseudo, String passWord) {
   	     Utilisateur u = new Utilisateur(pseudo,passWord);
   	     boolean b = false;
    	 for (Utilisateur user : utilisateurs) {
             if(user.equals(u)) {
            	 user.setConnected(true);
            	 b = true;
             }
            
         }
    	
    	return b;
     }
     
     
     
     
     @Override
     public boolean equals(Object obj) {
         if (this == obj) {
             return true; // If the objects are the same instance, they are equal
         }

         if (obj == null || getClass() != obj.getClass()) {
             return false; // If the object is null or of a different class, they are not equal
         }

         Application otherApp = (Application) obj; // Cast the object to Application type

         // Compare the users sets of the Application objects
         return Objects.equals(utilisateurs , otherApp.utilisateurs);
     }

     @Override
     public int hashCode() {
         return Objects.hash(utilisateurs);
     }  
   
}
