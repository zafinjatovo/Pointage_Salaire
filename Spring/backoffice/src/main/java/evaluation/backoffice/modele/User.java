package evaluation.backoffice.modele;

import java.util.ArrayList;
import java.util.List;

public class User {
    int id;
    String username;
    String email;
    String password;
    int isValid;
    String dateInscription;
    public User() {
    }
    public User(int id, String username, String email, String password, int isValid, String dateInscription) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.isValid = isValid;
        this.dateInscription = dateInscription;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public int getIsValid() {
        return isValid;
    }
    public void setIsValid(int isValid) {
        this.isValid = isValid;
    }
    public String getDateInscription() {
        return dateInscription;
    }
    public void setDateInscription(String dateInscription) {
        this.dateInscription = dateInscription;
    }

    

    public static List<Object> toListObj(List<User> list){
		List<Object> result=new ArrayList<Object>();
		for(User route:list) {
			result.add(route);
		}
		return result;
	}
}
