package evaluation.frontoffice.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import evaluation.frontoffice.helper.DatabaseAcess;
import evaluation.frontoffice.modele.Categorie;
import evaluation.frontoffice.modele.Employe;

@Service
public class EmployeService {
    
    
    public Employe findByMatricule(Connection connection,String matricule) throws Exception{
        Employe result=new Employe();
        //matricule | id | categorie |        nom        |    prenom     | datenaissance | dateembauche | isdeleted
        String sql=String.format("select *,getFinContrat('%s') as finContrat from employee_d where matricule='%s'",matricule,matricule);
        PreparedStatement statement=null;
        if(connection.isClosed()){
            connection=DatabaseAcess.getConnexion();
        }
        try {
            statement=connection.prepareStatement(sql);
            ResultSet results=statement.executeQuery();
            if(results.next()){
                result=new Employe(results.getString(1),results.getString(3),results.getString(4), results.getString(5), results.getString(6), results.getString(7));
                Categorie categorie=new Categorie(results.getInt(2),results.getString(3),results.getDouble(9),results.getDouble(10),results.getDouble(11));
                result.setCategoriedetail(categorie);
            }           
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(statement!=null) statement.close();
            if(connection!=null) connection.close();
        }
        return result;
    }

    public List<Employe> findAll(Connection connection,String colonne,String ordre,int offset,int limit)throws Exception{
        List<Employe> employes=new ArrayList<>();
        String sql=String.format("select * from EMPLOYEE_D order by %s %s offset %d limit %d",colonne,ordre,offset,limit);
        PreparedStatement statement=null;
        if(connection.isClosed()) connection=DatabaseAcess.getConnexion();
        try {
            statement=connection.prepareStatement(sql);
            ResultSet results=statement.executeQuery();
            while(results.next()){
                // matricule | id | categorie |     nom     |    prenom     | datenaissance | dateembauche | isdeleted
                Employe employe=new Employe(results.getString(1),results.getString(3),results.getString(4), results.getString(5), results.getString(6), results.getString(7));
                Categorie categorie=new Categorie(results.getInt(2),results.getString(3),results.getDouble(9),results.getDouble(10),results.getDouble(11));
                employe.setCategoriedetail(categorie);
                employes.add(employe);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(statement!=null) statement.close();
            if(connection!=null) connection.close();
        }
        return employes;
    }

}
