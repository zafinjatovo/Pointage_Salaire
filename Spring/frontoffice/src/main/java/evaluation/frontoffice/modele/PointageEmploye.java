package evaluation.frontoffice.modele;


import java.util.List;

import evaluation.frontoffice.dto.PointageDto;

public class PointageEmploye {
    Employe employe;
    List<PointageDto> pointages;
    public PointageEmploye() {
    }
    public PointageEmploye(Employe employe, List<PointageDto> pointages) {
        this.employe = employe;
        this.pointages = pointages;
    }
    
    public Employe getEmploye() {
        return employe;
    }
    public void setEmploye(Employe employe) {
        this.employe = employe;
    }
    public List<PointageDto> getPointages() {
        return pointages;
    }
    public void setPointages(List<PointageDto> pointages) {
        this.pointages = pointages;
    }

    
}
