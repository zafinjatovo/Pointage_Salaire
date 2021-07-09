package evaluation.backoffice.modele;

import evaluation.backoffice.helper.ActionResult;

public class Categorie {
    /**
     *CREATE TABLE IF NOT EXISTS T_CATEGORIE(
       id smallint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 32767 CACHE 1 ),
       nom character varying COLLATE pg_catalog."default" NOT NULL,
       CONSTRAINT "KEY_CATEGORIE" PRIMARY KEY (id)
     );
     */

     int id;
     String nom;
     double HN;
     double SN;
     double indemnite;

    public Categorie(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    
    public double getHN() {
        return HN;
    }


    public void setHN(double hN) {
        HN = hN;
    }


    public double getSN() {
        return SN;
    }


    public void setSN(double sN) {
        SN = sN;
    }


    public double getIndemnite() {
        return indemnite;
    }


    public void setIndemnite(double indemnite) {
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

    public void validation(ActionResult result) throws Exception{
        if(this.getNom().equals("")){
            result.setError("nom","Champ Obligatoire!");
        }
        if(this.getHN()<=0){
            result.setError("heure","Value: doit superieure 0!");
        }
        if(this.getSN()<=0){
            result.setError("salaire","Value: doit superieure 0!");
        }
        if(this.getIndemnite()<=0){
            result.setError("indemnite","Value: doit superieure 0!");
        }
    }
     
}
