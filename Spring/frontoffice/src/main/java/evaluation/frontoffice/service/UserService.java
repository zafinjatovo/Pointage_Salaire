package evaluation.frontoffice.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.springframework.stereotype.Service;

import evaluation.frontoffice.dto.UserDto;
import evaluation.frontoffice.dto.UserSaveDto;
import evaluation.frontoffice.dto.ValidUserDto;
import evaluation.frontoffice.helper.DatabaseAcess;

@Service
public class UserService {
    
    public String connect(Connection connection,UserDto admin)throws Exception{
        String token="";
        String sql=String.format("select token from USERS where username='%s' and password='%s'",admin.getUsername(),admin.getPassword());
        PreparedStatement statement=null;
        if(connection==null){
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

    public boolean isConnected(String token){
        boolean result = false;
        if(token==null){
            result=false;
        }else{
            result=true;
        }
        return result;
    }

    public boolean checkToken(Connection connection,String token)throws Exception{
        boolean result=false;
        String sql=String.format("select checktokenUser('%s')",token);
        PreparedStatement statement=null;
        if(connection==null){
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

    public ValidUserDto save(Connection connection,UserSaveDto user)throws Exception{
        //select * from saveuser('lina','lina@tovogmail.com','lina',cast(0 as smallint));
        ValidUserDto valid=new ValidUserDto();
        String sql=String.format("select * from saveuser('%s','%s','%s',cast(%d as smallint))",user.getUsername(),user.getEmail(),user.getPassword(),0);
        PreparedStatement statement=null;
        if(connection==null){
            connection=DatabaseAcess.getConnexion();
        }
        try {
            statement=connection.prepareStatement(sql);
            ResultSet resultSet=statement.executeQuery();
            if(resultSet.next()){
                valid=new ValidUserDto(resultSet.getString(1),resultSet.getString(2));
            }           
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(statement!=null) statement.close();
            if(connection!=null) connection.close();
        }
        return valid;
    }
}
