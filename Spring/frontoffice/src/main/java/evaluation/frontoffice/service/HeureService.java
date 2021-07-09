package evaluation.frontoffice.service;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import evaluation.frontoffice.helper.DatabaseAcess;
import evaluation.frontoffice.modele.Categorie;
import evaluation.frontoffice.modele.Heure;
import evaluation.frontoffice.modele.HeureMajore;
import evaluation.frontoffice.modele.HeureSup;
import evaluation.frontoffice.modele.PointageDetails;

@Service
public class HeureService {

    @Autowired
    PointageService pointageService;

    public List<HeureMajore> findHeuresMajore(Connection connection) throws Exception{
        List<HeureMajore> heures=new ArrayList<>();
        String sql="select * from T_HEURE_MAJORE";
        PreparedStatement statement=null;
        if(connection.isClosed()) connection=DatabaseAcess.getConnexion();
        try {
            statement=connection.prepareStatement(sql);
            ResultSet results=statement.executeQuery();
            while(results.next()){
                //id |   type   | designation | pourcentage
                HeureMajore heure=new HeureMajore(results.getInt(1),results.getString(2),results.getString(3),results.getDouble(4));
                heures.add(heure);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(statement!=null) statement.close();
            if(connection!=null) connection.close();
        }
        return heures;
    }

    public List<HeureSup> findHeureSup(Connection connection) throws Exception{
        List<HeureSup> heures=new ArrayList<>();
        String sql="select * from T_HEURE_SUP";
        PreparedStatement statement=null;
        if(connection.isClosed()) connection=DatabaseAcess.getConnexion();
        try {
            statement=connection.prepareStatement(sql);
            ResultSet results=statement.executeQuery();
            while(results.next()){
                //id | designation | heure | pourcentage
                HeureSup heure=new HeureSup(results.getInt(1),results.getString(2),results.getDouble(3),results.getDouble(4));
                heures.add(heure);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(statement!=null) statement.close();
            if(connection!=null) connection.close();
        }
        return heures;
    }

    

    /**
     *  select * from getHeure(cast(21 as smallint),cast(7 as smallint));
 idpointage | ferier | heuretravailferier | heurejour | heurenuit | heuredimance | sommeht
     * @param categorie
     * @param details
     * @return
     */
    public List<Heure> getHeureSuplemantaire(Categorie categorie,List<PointageDetails> details){
        List<Heure> result=new ArrayList<>();
        return result;
    }


    public HeureMajore findByDesignation(Connection connection,String designation)throws Exception{
        String sql="select * from t_heure_MAJORE where designation='" +designation+ "'";
        HeureMajore heure=new HeureMajore(); 
        PreparedStatement statement=null;
        if(connection.isClosed()) connection=DatabaseAcess.getConnexion();
        try {
            statement=connection.prepareStatement(sql);
            ResultSet results=statement.executeQuery();
            if(results.next()){
                //id | designation | heure | pourcentage
                heure=new HeureMajore(results.getInt(1),results.getString(2),results.getString(3),results.getDouble(4));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(statement!=null) statement.close();
            if(connection!=null) connection.close();
        }
        return heure;
    }

    public HeureSup findByDesignationSup(Connection connection,String designation)throws Exception{
        String sql="select * from t_heure_sup where designation='" +designation+ "'";
        HeureSup heure=new HeureSup(); 
        PreparedStatement statement=null;
        if(connection.isClosed()) connection=DatabaseAcess.getConnexion();
        try {
            statement=connection.prepareStatement(sql);
            ResultSet results=statement.executeQuery();
            if(results.next()){
                //id | designation | heure | pourcentage
                heure=new HeureSup(results.getInt(1),results.getString(2),results.getDouble(3),results.getDouble(4));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(statement!=null) statement.close();
            if(connection!=null) connection.close();
        }
        return heure;
    }
}
