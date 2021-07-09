package evaluation.frontoffice.modele;

public class HeureNJ {
    // select * from getHeure(cast(21 as smallint),cast(7 as smallint));
 //idpointage | ferier | heuretravailferier | heurejour | heurenuit | heuredimance | sommeht
    double ferier;
    double heuretravailferier;
    double heurejour;
    double heurenuit;
    double heuredimance;
    double sommeht;

    
    public HeureNJ(double ferier, double heuretravailferier, double heurejour, double heurenuit, double heuredimance,
            double sommeht) {
        this.ferier = ferier;
        this.heuretravailferier = heuretravailferier;
        this.heurejour = heurejour;
        this.heurenuit = heurenuit;
        this.heuredimance = heuredimance;
        this.sommeht = sommeht;
    }
    public HeureNJ() {
    }
    public double getFerier() {
        return ferier;
    }
    public void setFerier(double ferier) {
        this.ferier = ferier;
    }
    public double getHeuretravailferier() {
        return heuretravailferier;
    }
    public void setHeuretravailferier(double heuretravailferier) {
        this.heuretravailferier = heuretravailferier;
    }
    public double getHeurejour() {
        return heurejour;
    }
    public void setHeurejour(double heurejour) {
        this.heurejour = heurejour;
    }
    public double getHeurenuit() {
        return heurenuit;
    }
    public void setHeurenuit(double heurenuit) {
        this.heurenuit = heurenuit;
    }
    public double getHeuredimance() {
        return heuredimance;
    }
    public void setHeuredimance(double heuredimance) {
        this.heuredimance = heuredimance;
    }
    public double getSommeht() {
        return sommeht;
    }
    public void setSommeht(double sommeht) {
        this.sommeht = sommeht;
    }

    
}
