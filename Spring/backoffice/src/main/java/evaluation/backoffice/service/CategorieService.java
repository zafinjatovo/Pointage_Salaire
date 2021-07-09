package evaluation.backoffice.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import evaluation.backoffice.helper.DatabaseAcess;
import evaluation.backoffice.modele.Categorie;

@Service
public class CategorieService {
    public List<Categorie> findAll(Connection connection)throws Exception{
        List<Categorie> categories=new ArrayList<>();
        String sql="select id,nom from t_categorie";
        PreparedStatement statement=null;
        if(connection.isClosed()) connection=DatabaseAcess.getConnexion();
        try {
            statement=connection.prepareStatement(sql);
            ResultSet results=statement.executeQuery();
            while(results.next()){
               Categorie categorie=new Categorie(results.getInt(1),results.getString(2));
               categories.add(categorie);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(statement!=null) statement.close();
            if(connection!=null) connection.close();
        }
        return categories;
    }
    public List<Categorie> findAll_2(Connection connection)throws Exception{
        List<Categorie> categories=new ArrayList<>();
        String sql="select * from t_categorie";
        PreparedStatement statement=null;
        if(connection.isClosed()) connection=DatabaseAcess.getConnexion();
        try {
            // id |    nom     | hn |   sb   | indemnite
            statement=connection.prepareStatement(sql);
            ResultSet results=statement.executeQuery();
            while(results.next()){
               Categorie categorie=new Categorie(results.getInt(1),results.getString(2));
               categorie.setHN(results.getDouble(3));
               categorie.setSN(results.getDouble(4));
               categorie.setIndemnite(results.getDouble(5));

               categories.add(categorie);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(statement!=null) statement.close();
            if(connection!=null) connection.close();
        }
        return categories;
    }
    public Categorie findById(Connection connection,int id)throws Exception{
        Categorie categorie=new Categorie();
        String sql="select * from t_categorie where id=" + id;
        PreparedStatement statement=null;
        if(connection.isClosed()) connection=DatabaseAcess.getConnexion();
        try {
            // id |    nom     | hn |   sb   | indemnite
            statement=connection.prepareStatement(sql);
            ResultSet results=statement.executeQuery();
            if(results.next()){
               categorie=new Categorie(results.getInt(1),results.getString(2));
               categorie.setHN(results.getDouble(3));
               categorie.setSN(results.getDouble(4));
               categorie.setIndemnite(results.getDouble(5));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(statement!=null) statement.close();
            if(connection!=null) connection.close();
        }
        return categorie;
    }

    public boolean save(Connection connection,Categorie categorie) throws Exception{
        String sql=String.format("insert into T_CATEGORIE (nom,HN,SB,indemnite) values('%s',%s,%s,%s)",categorie.getNom(),categorie.getHN(),categorie.getSN(),categorie.getIndemnite());
        boolean result=false;
        PreparedStatement statement=null;
        if(connection.isClosed()) connection=DatabaseAcess.getConnexion();
        try {
            // id |    nom     | hn |   sb   | indemnite
            statement=connection.prepareStatement(sql);
            result=statement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(statement!=null) statement.close();
            if(connection!=null) connection.close();
        }
        return result;
    }

    public boolean update(Connection connection,Categorie categorie) throws Exception{
        String sql=String.format("update  T_CATEGORIE set nom='%s',HN=%s,SB=%s,indemnite=%s where id=%s",categorie.getNom(),categorie.getHN(),categorie.getSN(),categorie.getIndemnite(),categorie.getId());
        boolean result=false;
        PreparedStatement statement=null;
        if(connection.isClosed()) connection=DatabaseAcess.getConnexion();
        try {
            // id |    nom     | hn |   sb   | indemnite
            statement=connection.prepareStatement(sql);
            result=statement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(statement!=null) statement.close();
            if(connection!=null) connection.close();
        }
        return result;
    }
}
