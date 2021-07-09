package evaluation.backoffice.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.springframework.stereotype.Service;

import evaluation.backoffice.dto.AdminDto;
import evaluation.backoffice.helper.DatabaseAcess;

@Service
public class AdminService {
    
    public String connect(Connection connection,AdminDto admin)throws Exception{
        String token="";
        String sql=String.format("select token from admin where username='%s' and password='%s'",admin.getUsername(),admin.getPassword());
        PreparedStatement statement=null;
        if(connection.isClosed()){
            connection=DatabaseAcess.getConnexion();
        }
        try {
            statement=connection.prepareStatement(sql);
            ResultSet resultSet=statement.executeQuery();
            if(resultSet.next()){
                token=resultSet.getString(1);
            }           
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(statement!=null) statement.close();
            if(connection!=null) connection.close();
        }
        return token;
    }


    public boolean checkToken(Connection connection,String token)throws Exception{
        boolean result=false;
        String sql=String.format("select checktoken('%s')",token);
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
}
