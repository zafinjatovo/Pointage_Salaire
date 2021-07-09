package evaluation.backoffice.helper;

import java.io.InputStream;
import java.util.Properties;

public class DatabaseHelper {
    String url;
    String username;
    String password;

    public DatabaseHelper(){}

    
    public DatabaseHelper(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }


    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public void loadProperty() throws Exception{
       InputStream inputStream=null;
       try {
           Properties properties=new Properties();
           inputStream=getClass().getClassLoader().getResourceAsStream("./datasource.properties");

           properties.load(inputStream);
           this.url=properties.getProperty("datasource.url");
           this.username=properties.getProperty("datasource.username");
           this.password=properties.getProperty("datasource.password");
       } catch (Exception e) {
          e.printStackTrace();
       }finally{
           inputStream.close();
       }

    }

    
}
