package evaluation.backoffice.dto;

import evaluation.backoffice.helper.ActionResult;
import evaluation.backoffice.helper.Util;

public class EmployeDto {
    String matricule;
    int categorie;
    String nom;
    String prenom;
    String dateNaissance;
    String dateEmbauche;
    String dateFinContrat;

    
    public EmployeDto(int categorie, String nom, String prenom, String dateNaissance, String dateEmbauche,
            String dateFinContrat) {
        this.categorie = categorie;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.dateEmbauche = dateEmbauche;
        this.dateFinContrat = dateFinContrat;
    }

    
    
    public String getMatricule() {
        return matricule;
    }



    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }



    public EmployeDto() {
    }


    public int getCategorie() {
        return categorie;
    }
    public void setCategorie(int categorie) {
        this.categorie = categorie;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getPrenom() {
        return prenom;
    }
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    public String getDateNaissance() {
        return dateNaissance;
    }
    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }
    public String getDateEmbauche() {
        return dateEmbauche;
    }
    public void setDateEmbauche(String dateEmbauche) {
        this.dateEmbauche = dateEmbauche;
    }
    public String getDateFinContrat() {
        return dateFinContrat;
    }
    public void setDateFinContrat(String dateFinContrat) {
        this.dateFinContrat = dateFinContrat;
    }


    public void validation(ActionResult result)throws Exception{
        if(this.getNom().equals("")){
            result.setError("nom","Champ Obligatoire!");
        }
        if(this.getPrenom().equals("")){
            result.setError("prenom","Champ Obligatoire!");
        }
        if(this.getDateNaissance().equals("")){
            result.setError("datenaissance","Champ Obligatoire!");
        }
        if(this.getDateEmbauche().equals("")){
            result.setError("dateembauche","Champ Obligatoire!");
        }
        if(this.getDateFinContrat().equals("")){
            result.setError("datecontrat","Champ Obligatoire!");
        }
        /// dateContrat doit superieure date Embauche
        if(!this.getDateEmbauche().equals("") && !this.getDateFinContrat().equals("")){
            int compareDate=Util.compareDate(this.getDateEmbauche() + " 00:00:00", this.getDateFinContrat() + " 00:00:00");
            if( compareDate>0 ){
                result.setError("invalidDate","Votre date interval n'est pas valid!");
            }
        }
    }
    
}
