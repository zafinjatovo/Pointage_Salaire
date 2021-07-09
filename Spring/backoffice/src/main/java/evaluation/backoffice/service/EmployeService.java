package evaluation.backoffice.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import evaluation.backoffice.dto.EmployeDto;
import evaluation.backoffice.helper.DatabaseAcess;
import evaluation.backoffice.modele.Employe;

@Service
public class EmployeService {
    
    public String save(Connection connection,EmployeDto employe)throws Exception{
        String result="";
        String sql=String.format("select * from saveEmploye(CAST(%d AS SMALLINT),'%s','%s','%s','%s','%s')",employe.getCategorie(),employe.getNom(),employe.getPrenom(),employe.getDateNaissance(),employe.getDateEmbauche(),employe.getDateFinContrat());
        PreparedStatement statement=null;
        if(connection.isClosed()){
            connection=DatabaseAcess.getConnexion();
        }
        try {
            statement=connection.prepareStatement(sql);
            ResultSet resultSet=statement.executeQuery();
            if(resultSet.next()){
                result=resultSet.getString(1);
            }           
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(statement!=null) statement.close();
            if(connection!=null) connection.close();
        }
        return result;
    }

    public boolean update(Connection connection,EmployeDto employe) throws Exception{
        boolean result=false;
        String sql=String.format("select * from updateEmploye('%s',CAST(%d AS SMALLINT),'%s','%s','%s','%s','%s',CAST(%d AS SMALLINT))",employe.getMatricule(),employe.getCategorie(),employe.getNom(),employe.getPrenom(),employe.getDateNaissance(),employe.getDateEmbauche(),employe.getDateFinContrat(),0);
        System.out.println(sql);
        PreparedStatement statement=null;
        if(connection.isClosed()){
            connection=DatabaseAcess.getConnexion();
        }
        try {
            statement=connection.prepareStatement(sql);
            ResultSet resultSet=statement.executeQuery();
            if(resultSet.next()){
                result=resultSet.getBoolean(1);
            }           
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(statement!=null) statement.close();
            if(connection!=null) connection.close();
        }
        return result;
    }

    public EmployeDto findByMatricule(Connection connection,String matricule) throws Exception{
        EmployeDto result=new EmployeDto();
        //matricule | id | categorie |        nom        |    prenom     | datenaissance | dateembauche | isdeleted
        String sql=String.format("select *,getFinContrat('%s') as finContrat from employee_d where matricule='%s'",matricule,matricule);
        PreparedStatement statement=null;
        if(connection.isClosed()){
            connection=DatabaseAcess.getConnexion();
        }
        try {
            statement=connection.prepareStatement(sql);
            ResultSet resultSet=statement.executeQuery();
            if(resultSet.next()){
                result=new EmployeDto(resultSet.getInt(2),resultSet.getString(4), resultSet.getString(5),resultSet.getString(6),resultSet.getString(7),resultSet.getString(9));
                result.setMatricule(resultSet.getString(1));
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
