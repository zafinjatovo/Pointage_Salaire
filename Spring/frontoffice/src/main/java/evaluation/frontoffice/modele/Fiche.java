package evaluation.frontoffice.modele;

import java.util.List;

public class Fiche {
    Employe employe;
    List<FicheModele> modeles;
    double indemnite;
    double totalPayer;
    
    public Fiche() {
    }


    public Fiche(Employe employe, FicheModele modele, double indemnite, double totalPayer) {
        this.employe = employe;
        this.indemnite = indemnite;
        this.totalPayer = totalPayer;
    }



    public List<FicheModele> getModeles() {
        return modeles;
    }


    public void setModeles(List<FicheModele> modeles) {
        this.modeles = modeles;
    }


    public Employe getEmploye() {
        return employe;
    }
    public void setEmploye(Employe employe) {
        this.employe = employe;
    }
    public double getIndemnite() {
        return indemnite;
    }
    public void setIndemnite(double indemnite) {
        this.indemnite = indemnite;
    }
    public double getTotalPayer() {
        return totalPayer;
    }
    public void setTotalPayer(double totalPayer) {
        this.totalPayer = totalPayer;
    }

    public void calculMontantTotal(double salaireBase,List<FicheModele> ficheModeles){
        double result=0;
        for(FicheModele modele:ficheModeles){
            result+=modele.getMontant();
        }
        result=result +((salaireBase*indemnite)/100);
        this.totalPayer=result;
    }
    
}
