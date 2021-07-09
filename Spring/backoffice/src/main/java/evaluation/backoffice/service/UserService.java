package evaluation.backoffice.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import evaluation.backoffice.helper.DatabaseAcess;
import evaluation.backoffice.modele.User;

@Service
public class UserService {

    public UserService(){}
    
    public void valid(Connection connection,int userId)throws Exception{
        String sql=String.format("update  t_user set isValid=1 where id=%d",userId);
        PreparedStatement statement=null;
        if(connection.isClosed()){
            connection=DatabaseAcess.getConnexion();
        }
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
     * Find pagination
     * @param connection
     * @param colonne
     * @param ordre
     * @param offset
     * @param limit
     * @return
     * @throws Exception
     */

    public List<User> findAll(Connection connection,String colonne,String ordre,int offset,int limit)throws Exception{
        List<User> users=new ArrayList<>();
        String sql=String.format("select * from V_USER_NOVALID order by %s %s offset %d limit %d",colonne,ordre,offset,limit);
        PreparedStatement statement=null;
        if(connection.isClosed()) connection=DatabaseAcess.getConnexion();
        try {
            statement=connection.prepareStatement(sql);
            ResultSet results=statement.executeQuery();
            while(results.next()){
                //id | username |       email        | password | isvalid |      dateinscription
                User user=new User(results.getInt(1),results.getString(2),results.getString(3),results.getString(4),results.getInt(5),results.getString(6));
                users.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(statement!=null) statement.close();
            if(connection!=null) connection.close();
        }
        return users;
    }
    

}
