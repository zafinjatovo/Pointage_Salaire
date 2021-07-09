package evaluation.backoffice.helper;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseAcess {
    public DatabaseAcess(){}
    public static Connection getConnexion(){
        Connection connection=null;
        try {
            DatabaseHelper helper=new DatabaseHelper();
            helper.loadProperty();
            connection=DriverManager.getConnection(helper.getUrl(),helper.getUsername(),helper.getPassword());
            System.out.println("Succes");
        } catch (Exception e) {
            e.printStackTrace();
        }finally{

        }
        return connection;
    }
}
