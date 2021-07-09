package evaluation.frontoffice.modele;

public class FicheModele {
    String designation;
    double totalHeure;
    double tauxHaire;
    double montant;
    public FicheModele(String designation, double totalHeure, double tauxHaire, double montant) {
        this.designation = designation;
        this.totalHeure = totalHeure;
        this.tauxHaire = tauxHaire;
        this.montant = montant;
    }
    public FicheModele() {
    }
    public String getDesignation() {
        return designation;
    }
    public void setDesignation(String designation) {
        this.designation = designation;
    }
    public double getTotalHeure() {
        return totalHeure;
    }
    public void setTotalHeure(double totalHeure) {
        this.totalHeure = totalHeure;
    }
    public double getTauxHaire() {
        return tauxHaire;
    }
    public void setTauxHaire(double tauxHaire) {
        this.tauxHaire = tauxHaire;
    }
    public double getMontant() {
        return montant;
    }
    public void setMontant(double montant) {
        this.montant = montant;
    }

    
}
