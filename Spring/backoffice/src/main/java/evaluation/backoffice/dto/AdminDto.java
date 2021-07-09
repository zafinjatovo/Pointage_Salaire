package evaluation.backoffice.dto;

import evaluation.backoffice.helper.ActionResult;

public class AdminDto {
    
    String username;
    String password;

    public AdminDto(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public AdminDto() {
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
