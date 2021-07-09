package evaluation.frontoffice.modele;

public class HeureSup {
    //id | designation | heure | pourcentage
    int id;
    String designation;
    double heure;
    double pourcentage;

    public HeureSup() {
    }

    public HeureSup(int id, String designation, double heure, double pourcentage) {
        this.id = id;
        this.designation = designation;
        this.heure = heure;
        this.pourcentage = pourcentage;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getDesignation() {
        return designation;
    }
    public void setDesignation(String designation) {
        this.designation = designation;
    }
    public double getHeure() {
        return heure;
    }
    public void setHeure(double heure) {
        this.heure = heure;
    }
    public double getPourcentage() {
        return pourcentage;
    }
    public void setPourcentage(double pourcentage) {
        this.pourcentage = pourcentage;
    }

    
}
