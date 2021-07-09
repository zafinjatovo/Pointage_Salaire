package evaluation.frontoffice.dto;

import java.util.List;

import evaluation.frontoffice.modele.Pointage;
import evaluation.frontoffice.modele.PointageDetails;

public class PointageDto {

    Pointage pointage;
    List<PointageDetails> details;


    public PointageDto(){
    }
    

    public PointageDto(Pointage pointage,List<PointageDetails> details) {
        this.pointage=pointage;
        this.details = details;
    }



    public Pointage getPointage() {
        return pointage;
    }



    public void setPointage(Pointage pointage) {
        this.pointage = pointage;
    }



    public List<PointageDetails> getDetails() {
        return details;
    }

    public void setDetails(List<PointageDetails> details) {
        this.details = details;
    }


    
}
