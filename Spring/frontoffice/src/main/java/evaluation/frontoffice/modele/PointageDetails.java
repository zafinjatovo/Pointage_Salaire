package evaluation.frontoffice.modele;

public class PointageDetails {
    int id;
    int idPointage;
    double ferier;
    int jour;
    double heureJour;
    double heureNuit;
    public PointageDetails() {
    }
    public PointageDetails(int id, int idPointage, double isFerier, int jour, double heureJour, double heureNuit) {
        this.id = id;
        this.idPointage = idPointage;
        this.ferier = isFerier;
        this.jour = jour;
        this.heureJour = heureJour;
        this.heureNuit = heureNuit;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getIdPointage() {
        return idPointage;
    }
    public void setIdPointage(int idPointage) {
        this.idPointage = idPointage;
    }
    
    public double getFerier() {
        return ferier;
    }
    public void setFerier(double ferier) {
        this.ferier = ferier;
    }
    public int getJour() {
        return jour;
    }
    public void setJour(int jour) {
        this.jour = jour;
    }
    public double getHeureJour() {
        return heureJour;
    }
    public void setHeureJour(double heureJour) {
        this.heureJour = heureJour;
    }
    public double getHeureNuit() {
        return heureNuit;
    }
    public void setHeureNuit(double heureNuit) {
       this.heureNuit = heureNuit;
    }

    
}
