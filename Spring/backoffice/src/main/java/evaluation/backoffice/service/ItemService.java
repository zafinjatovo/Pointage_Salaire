package evaluation.backoffice.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import evaluation.backoffice.helper.DatabaseAcess;
import evaluation.backoffice.modele.Item;

@Service
public class ItemService {
    
    /**
     * delete
     * @param connection
     * @param id
     * @throws Exception
     */
    public void delete(Connection connection,int id)throws Exception{
        String sql=String.format("",id);
        PreparedStatement statement=null;
        if(connection==null) connection=DatabaseAcess.getConnexion();
        try {
            connection.setAutoCommit(false);
            statement=connection.prepareStatement(sql);
            statement.execute();
            connection.commit();
        } catch (Exception e) {
            connection.rollback();
            e.printStackTrace();
        }finally{
            if(statement!=null) statement.close();
            if(connection!=null) connection.close();
        }
    }
    /**
     * insert 
     * @param connection
     * @param item
     * @throws Exception
     */

    public void save(Connection connection,Item item)throws Exception{
        String sql=String.format("",0);
        PreparedStatement statement=null;
        if(connection==null) connection=DatabaseAcess.getConnexion();
        try {
            connection.setAutoCommit(false);
            statement=connection.prepareStatement(sql);
            statement.execute();
            connection.commit();
        } catch (Exception e) {
            connection.rollback();
            e.printStackTrace();
        }finally{
            if(statement!=null) statement.close();
            if(connection!=null) connection.close();
        }
    }

    /**
     * update
     * @param connection
     * @param item
     * @throws Exception
     */

    public void update(Connection connection,Item item)throws Exception{
        String sql=String.format("",0);
        PreparedStatement statement=null;
        if(connection==null) connection=DatabaseAcess.getConnexion();
        try {
            connection.setAutoCommit(false);
            statement=connection.prepareStatement(sql);
            statement.execute();
            connection.commit();
        } catch (Exception e) {
            connection.rollback();
            e.printStackTrace();
        }finally{
            if(statement!=null) statement.close();
            if(connection!=null) connection.close();
        }
    }

    public List<Item> findAll(Connection connection,String colonne,String ordre,int offset,int limit)throws Exception{
        List<Item> items=new ArrayList<>();
        String sql=String.format("select * from item order by %s %s offset %d limit %d",colonne,ordre,offset,limit);
        System.out.println(sql);
        PreparedStatement statement=null;
        if(connection.isClosed()){
            connection=DatabaseAcess.getConnexion();
        }
        try {
            statement=connection.prepareStatement(sql);
            ResultSet results=statement.executeQuery();
            while(results.next()){
                Item item=new Item();
                items.add(item);
            }
        } catch (Exception e) {
            connection.rollback();
            e.printStackTrace();
        }finally{
            if(statement!=null) statement.close();
            if(connection!=null) connection.close();
        }
        return items;
    }
}

