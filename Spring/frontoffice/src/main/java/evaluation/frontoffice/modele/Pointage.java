package evaluation.frontoffice.modele;

import java.util.List;

public class Pointage {
    int id;
    String matricule;
    String commentaire;
    HeureNJ heure;
    List<HeureSup> heureSUP;
    
    public Pointage(int sequence, String matricule, String commentaire) {
        this.id = sequence;
        this.matricule = matricule;
        this.commentaire = commentaire;
    }
    public Pointage() {
    }
   
    public HeureNJ getHeure() {
        return heure;
    }
    
    public List<HeureSup> getHeureSUP() {
        return heureSUP;
    }
    public void setHeureSUP(List<HeureSup> heureSUP) {
        this.heureSUP = heureSUP;
    }
    public void setHeure(HeureNJ heure) {
        this.heure = heure;
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
    public String getCommentaire() {
        return commentaire;
    }
    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }


}
