package evaluation.backoffice.modele;

public class Contrat {
    //id | matricule | datedebut | datefin
    int id;
    String matricule;
    String datedebut;
    String datefin;
    
    public Contrat(int id, String matricule, String datedebut, String datefin) {
        this.id = id;
        this.matricule = matricule;
        this.datedebut = datedebut;
        this.datefin = datefin;
    }
    

    public Contrat() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getDatedebut() {
        return datedebut;
    }

    public void setDatedebut(String datedebut) {
        this.datedebut = datedebut;
    }

    public String getDatefin() {
        return datefin;
    }

    public void setDatefin(String datefin) {
        this.datefin = datefin;
    }

    
}
