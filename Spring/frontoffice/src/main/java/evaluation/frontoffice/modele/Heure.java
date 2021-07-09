package evaluation.frontoffice.modele;

public class Heure {
    String designation;
    double value;
    double pourcentage;

    
    public Heure() {
    }


    public Heure(String designation, double value) {
        this.designation = designation;
        this.value = value;

    }


    public String getDesignation() {
        return designation;
    }


    public void setDesignation(String designation) {
        this.designation = designation;
    }


    public double getValue() {
        return value;
    }


    public void setValue(double value) {
        this.value = value;
    }


    public double getPourcentage() {
        return pourcentage;
    }


    public void setPourcentage(double pourcentage) {
        this.pourcentage = pourcentage;
    }
    
}
