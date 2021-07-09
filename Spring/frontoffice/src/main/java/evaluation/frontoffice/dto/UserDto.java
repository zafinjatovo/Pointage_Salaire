package evaluation.frontoffice.dto;

import evaluation.frontoffice.helper.ActionResult;

public class UserDto {
    
    String username;
    String password;

    public UserDto(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public UserDto() {
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

    public void validation(ActionResult result)throws Exception{
        if(this.getUsername().equals("")){
            result.setError("username","Champ Obligatoire!");
        }
        if(this.getPassword().equals("")){
            result.setError("password","Champ Obligatoire!");
        }
    }
}
