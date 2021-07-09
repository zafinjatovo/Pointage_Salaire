package evaluation.frontoffice.modele;

import java.util.ArrayList;
import java.util.List;

public class HeureMajore {
    //id |   type   | designation | pourcentage
    int id;
    String type;
    String designation;
    double pourcentage;

    

    public HeureMajore() {
    }
    public HeureMajore(int id, String type, String designation, double pourcentage) {
        this.id = id;
        this.type = type;
        this.designation = designation;
        this.pourcentage = pourcentage;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getDesignation() {
        return designation;
    }
    public void setDesignation(String designation) {
        this.designation = designation;
    }
    public double getPourcentage() {
        return pourcentage;
    }
    public void setPourcentage(double pourcentage) {
        this.pourcentage = pourcentage;
    }

   
    public List<Heure> getHeureNomal(Categorie categorie,List<PointageDetails> listes){
        List<Heure> heureresult=new ArrayList<>();
        
        return heureresult;
    }
}
