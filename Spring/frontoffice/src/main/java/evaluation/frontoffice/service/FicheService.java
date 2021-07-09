package evaluation.frontoffice.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import evaluation.frontoffice.modele.Employe;
import evaluation.frontoffice.modele.Fiche;
import evaluation.frontoffice.modele.FicheModele;
import evaluation.frontoffice.modele.HeureSup;
import evaluation.frontoffice.modele.Pointage;

@Service
public class FicheService {

    @Autowired
    EmployeService employeservice;

    @Autowired
    PointageService pointageSer;

    @Autowired
    GeneralService service;

    @Autowired
    HeureService heureService;

    @Autowired
    UserService userService;
    
    public Fiche generate(Connection connection,String matricule,int idPointage) throws Exception{
        Fiche fiche=new Fiche();
        Employe employe=employeservice.findByMatricule(connection, matricule);
        Pointage pointage=pointageSer.findByIdPointage(connection,matricule,idPointage);
        List<HeureSup> listes=heureService.findHeureSup(connection);
        List<HeureSup> heuresup=pointageSer.getHeureSuplementaire(employe, pointage, listes);
        pointage.setHeureSUP(listes);
        List<FicheModele> modeles=new ArrayList<>();
        double salaireBase=employe.getCategoriedetail().getSb();
        double heureNomal=employe.getCategoriedetail().getHn();
        double indemnite=employe.getCategoriedetail().getIndemnite();
        if(pointage.getHeure().getSommeht()<heureNomal){
            indemnite=0;
        }
        fiche.setEmploye(employe);
        fiche.setIndemnite(indemnite);
        modeles.add(
            new FicheModele("HM30",
            pointage.getHeure().getHeurenuit(),
            heureService.findByDesignation(connection,"HM30").getPourcentage(),
            calculMontant(pointage.getHeure().getHeurenuit(),salaireBase,heureNomal, heureService.findByDesignation(connection,"HM30").getPourcentage())
            )
        );
        modeles.add(
            new FicheModele("HM40",
            pointage.getHeure().getHeurenuit(),
            heureService.findByDesignation(connection,"HM40").getPourcentage(),
            calculMontant(pointage.getHeure().getHeuredimance(),salaireBase,heureNomal, heureService.findByDesignation(connection,"HM40").getPourcentage())
            )
        );
        modeles.add(
            new FicheModele("HM50",
            pointage.getHeure().getHeurenuit(),
            heureService.findByDesignation(connection,"HM50").getPourcentage(),
            calculMontant(pointage.getHeure().getHeuretravailferier(),salaireBase,heureNomal, heureService.findByDesignation(connection,"HM50").getPourcentage())
            )
        );
        modeles.add(
            new FicheModele(
                "Heure jour",
                pointage.getHeure().getHeurejour(),
                100,
                calculMontant(pointage.getHeure().getHeurejour(),salaireBase,heureNomal,100)
            )
        );
        modeles.add(
            new FicheModele(
                "Heure Ferier",
                pointage.getHeure().getFerier(),
                100,
                calculMontant(pointage.getHeure().getFerier(),salaireBase,heureNomal,100)
            )
        );
        // caclul fiche supLementaire 
        for(HeureSup sup:heuresup){
            double pourcetange= heureService.findByDesignationSup(connection,sup.getDesignation()).getPourcentage();
            modeles.add(
                new FicheModele(
                    sup.getDesignation(),
                    sup.getHeure(),
                    pourcetange,
                    calculMontant(sup.getHeure(),salaireBase,heureNomal,pourcetange)
                )
            );
        }
        fiche.setModeles(modeles);
        fiche.calculMontantTotal(salaireBase,modeles);
        return fiche;
    }

    public double calculMontant(double heureTotal,double salaireBase,double HeureNomal,double pourcentage){
        return heureTotal*(salaireBase/HeureNomal)*pourcentage/100;
    }
}
