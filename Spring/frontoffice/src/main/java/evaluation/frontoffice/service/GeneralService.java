package evaluation.frontoffice.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.springframework.stereotype.Service;

import evaluation.frontoffice.helper.DatabaseAcess;

@Service
public class GeneralService {
    public int count(Connection connection,String table)throws Exception{
        int result=0;
        String sql=String.format("select count(*) from %s", table);
        PreparedStatement statement=null;
        if(connection.isClosed()) connection=DatabaseAcess.getConnexion();
        try {
            statement=connection.prepareStatement(sql);
            ResultSet results=statement.executeQuery();
            if(results.next()){
                result=results.getInt(1);
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
