package evaluation.frontoffice.dto;

import evaluation.frontoffice.helper.ActionResult;

public class UserSaveDto {
    String username;
    String email;
    String password;
    String confirm;
    
    public UserSaveDto(){}

    public UserSaveDto(String username, String email, String password, String confirm) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.confirm = confirm;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirm() {
        return confirm;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }

    public boolean isValidEmail() {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return this.email.matches(regex);
     }


    public void validation(ActionResult result)throws Exception{
        if(username.equals("")){
            result.setError("username","Champ Obligatoire!");
        }
        if(email.equals("")){
            result.setError("email","Champ Obligatoire!");
        }
        if(password.equals("")){
            result.setError("password","Champ Obligatoire!");
        }
        if(confirm.equals("")){
            result.setError("confirm","Champ Obligatoire!");
        }
        if( !email.equals("") && !isValidEmail()){
            result.setError("email","Email not valid!");
        }
        if(!password.equals("") && !confirm.equals("")){
            if(!password.equals(confirm)){
                result.setError("confirmpassword","Confirmation: non confirmer");
            }
        }
    }
    
}
