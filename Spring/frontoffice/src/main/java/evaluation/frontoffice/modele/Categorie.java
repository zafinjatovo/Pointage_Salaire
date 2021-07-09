package evaluation.frontoffice.modele;

public class Categorie {
    // id |      nom      | hn |   sb    | indemnite
    int id;
    String nom;
    double hn;
    double sb;
    double indemnite;
    
    public Categorie(int id, String nom, double hn, double sb, double indemnite) {
        this.id = id;
        this.nom = nom;
        this.hn = hn;
        this.sb = sb;
        this.indemnite = indemnite;
    }

    public Categorie() {
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public double getHn() {
        return hn;
    }
    public void setHn(double hn) {
        this.hn = hn;
    }
    public double getSb() {
        return sb;
    }
    public void setSb(double sb) {
        this.sb = sb;
    }
    public double getIndemnite() {
        return indemnite;
    }
    public void setIndemnite(double indemnite) {
        this.indemnite = indemnite;
    }   
}
