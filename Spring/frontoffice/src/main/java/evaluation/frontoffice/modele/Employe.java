package evaluation.frontoffice.modele;

public class Employe {
    // matricule | idcategorie | nom | prenom | datenaissance | dateembauche | isdeleted
    String  matricule;
    String categorie;
    String nom;
    String prenom;
    String dateNaissance;
    String dateEmbauche;
    Categorie categoriedetail;
    public Employe(String matricule, String categorie, String nom, String prenom, String dateNaissance,
            String dateEmbauche) {
        this.matricule = matricule;
        this.categorie = categorie;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.dateEmbauche = dateEmbauche;
    }

    
    public Employe() {
    }
    public String getMatricule() {
        return matricule;
    }
    public Categorie getCategoriedetail() {
        return categoriedetail;
    }


    public void setCategoriedetail(Categorie categoriedetail) {
        this.categoriedetail = categoriedetail;
    }


    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }
    public String getCategorie() {
        return categorie;
    }
    public void setCategorie(String categorie) {
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

    
}
