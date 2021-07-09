package evaluation.frontoffice.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import evaluation.frontoffice.dto.PointageDto;
import evaluation.frontoffice.helper.DatabaseAcess;
import evaluation.frontoffice.modele.Employe;
import evaluation.frontoffice.modele.HeureNJ;
import evaluation.frontoffice.modele.HeureSup;
import evaluation.frontoffice.modele.Pointage;
import evaluation.frontoffice.modele.PointageDetails;
import evaluation.frontoffice.modele.PointageEmploye;

@Service
public class PointageService {
    
    @Autowired
    EmployeService employeService;


    //Transacation
    public int getSequence(Connection connection) throws Exception{
        int result=0;
        String sql="select nextval('pointage')";
        PreparedStatement statement=null;
        if(connection.isClosed()){
            connection=DatabaseAcess.getConnexion();
        }
        try {
            statement=connection.prepareStatement(sql);
            ResultSet results=statement.executeQuery();
            if(results.next()){
               result=results.getInt(1);
            }           
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception();
        }finally{
            if(statement!=null) statement.close();
            if(connection!=null) connection.close();
        }
        return result;
    }

    public void save(Connection connection,Pointage pointage) throws Exception{
        String sql=String.format("insert into T_POINTAGE (id,matricule,commentaire) values(%d,'%s','%s')",pointage.getId(),pointage.getMatricule(),"");
        PreparedStatement statement=null;
        try {
            statement=connection.prepareStatement(sql);
            statement.execute();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception();
        }finally{
            if(statement!=null) statement.close();
        }
    }

    public void savePointageDetails(Connection connection,PointageDetails pointage) throws Exception{
        String sql=String.format("insert into T_DETAILS_POINTAGE (idPointage,ferier,jour,HeureJour,HeureNuit) values(%d,%s,%d,%s,%s)",pointage.getIdPointage(),pointage.getFerier(),pointage.getJour(),pointage.getHeureJour(),pointage.getHeureNuit());
        PreparedStatement statement=null;
        System.out.println(sql);
        try {
            statement=connection.prepareStatement(sql);
            statement.execute();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception();
        }finally{
            if(statement!=null) statement.close();
        }
    }

    public void pointage(Connection connection,Pointage pointage,List<PointageDetails> details) throws Exception{
        int sequence=getSequence(connection);
        if(connection.isClosed()){
            connection=DatabaseAcess.getConnexion();
        }
        connection.setAutoCommit(false);
        try {
            pointage.setId(sequence);
            save(connection, pointage);
            if(details.size()>0){
                for(PointageDetails detail:details){
                    detail.setIdPointage(sequence);
                    savePointageDetails(connection, detail);
                }
            }
            connection.commit();
        }catch (Exception e) {
            connection.rollback();
            e.printStackTrace();
            throw new Exception();
        }finally{
            if(connection!=null) connection.close();
        }
    }


    public List<Pointage> findByMatricule(Connection connection,String matricule)throws Exception{
        List<Pointage> pointages=new ArrayList<>();
        String sql=String.format("select *,getHeureFerier(id),getHeureFerierTravailer(id),getHeureJour(id),getHeureNuit(id,CAST(7 AS smallint)),getHeureJourSpecifier(id,CAST(7 AS smallint)),getsommeHT(id) from t_pointage where matricule='%s'",matricule);
        PreparedStatement statement=null;
        if(connection.isClosed()) connection=DatabaseAcess.getConnexion();
        try {
            statement=connection.prepareStatement(sql);
            ResultSet results=statement.executeQuery();
            while(results.next()){
                // matricule | id | categorie |     nom     |    prenom     | datenaissance | dateembauche | isdeleted
                Pointage pointage=new Pointage(results.getInt(1),results.getString(2),results.getString(3));
                HeureNJ heure=new HeureNJ(results.getDouble(4),results.getDouble(5),results.getDouble(6),results.getDouble(7),results.getDouble(8),results.getDouble(9));
                pointage.setHeure(heure);
                pointages.add(pointage);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(statement!=null) statement.close();
            if(connection!=null) connection.close();
        }
        return pointages;
    }

    public Pointage findByIdPointage(Connection connection,String matricule,int id)throws Exception{
        Pointage pointage=new Pointage();
        String sql=String.format("select *,getHeureFerier(id),getHeureFerierTravailer(id),getHeureJour(id),getHeureNuit(id,CAST(7 AS smallint)),getHeureJourSpecifier(id,CAST(7 AS smallint)),getsommeHT(id) from t_pointage where matricule='%s' and id=%d",matricule,id);
        PreparedStatement statement=null;
        if(connection.isClosed()) connection=DatabaseAcess.getConnexion();
        try {
            statement=connection.prepareStatement(sql);
            ResultSet results=statement.executeQuery();
            if(results.next()){
                // matricule | id | categorie |     nom     |    prenom     | datenaissance | dateembauche | isdeleted
                pointage=new Pointage(results.getInt(1),results.getString(2),results.getString(3));
                HeureNJ heure=new HeureNJ(results.getDouble(4),results.getDouble(5),results.getDouble(6),results.getDouble(7),results.getDouble(8),results.getDouble(9));
                pointage.setHeure(heure);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(statement!=null) statement.close();
            if(connection!=null) connection.close();
        }
        return pointage;
    }

    public List<PointageDetails> findByPointage(Connection connection,int idpointage)throws Exception{
        List<PointageDetails> pointages=new ArrayList<>();
        String sql=String.format("select * from t_details_pointage where idpointage=%d",idpointage);
        PreparedStatement statement=null;
        if(connection.isClosed()) connection=DatabaseAcess.getConnexion();
        try {
            statement=connection.prepareStatement(sql);
            ResultSet results=statement.executeQuery();
            while(results.next()){
                //id | idpointage | ferier | jour | heurejour | heurenuit
                PointageDetails pointage=new PointageDetails(results.getInt(1),results.getInt(2),results.getDouble(3),results.getInt(4),results.getDouble(5),results.getDouble(6));
                pointages.add(pointage);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(statement!=null) statement.close();
            if(connection!=null) connection.close();
        }
        return pointages;
    }
    
    public PointageEmploye get(Connection connection,String matricule,List<HeureSup> listes)throws Exception{
        PointageEmploye result=new PointageEmploye();
        if(connection.isClosed()) connection=DatabaseAcess.getConnexion();
        try {
            Employe employe=employeService.findByMatricule(connection, matricule);
            List<Pointage> pointages=findByMatricule(connection, matricule);
            List<PointageDto> pointagesDto=new ArrayList<>();
            for (Pointage pointage : pointages) {
                PointageDto tempDto=new PointageDto();
                List<HeureSup> heureSup=getHeureSuplementaire(employe, pointage, listes);
                System.out.println(heureSup.size());
                pointage.setHeureSUP(heureSup);
                tempDto.setPointage(pointage);
                List<PointageDetails> details=findByPointage(connection, pointage.getId());
                tempDto.setDetails(details);
                pointagesDto.add(tempDto);
            }
            result.setEmploye(employe);
            result.setPointages(pointagesDto);
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(connection!=null && connection.isClosed()) connection.close();
        }
        return result;
    }

    public List<HeureSup> getHeureSuplementaire(Employe employe,Pointage pointage,List<HeureSup> listes){
        List<HeureSup> heureSuplist=new ArrayList<>();
        double sommeHT=pointage.getHeure().getSommeht();
        double tempHeureSuplementaire=sommeHT-employe.getCategoriedetail().getHn();
        for(HeureSup heuresup : listes){
            HeureSup temp=new HeureSup();
            temp.setDesignation(heuresup.getDesignation());
            if(tempHeureSuplementaire>0){
                if(tempHeureSuplementaire>heuresup.getHeure()){
                    temp.setHeure(heuresup.getHeure());
                }else{
                    temp.setHeure(tempHeureSuplementaire);
                }
            }
            tempHeureSuplementaire=tempHeureSuplementaire-heuresup.getHeure();
            heureSuplist.add(temp);
        }
        return heureSuplist;
    }
}
