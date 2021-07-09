package evaluation.frontoffice.dto;

public class ValidUserDto {
    //validusername | validemail
    String validUsername;
    String validEmail;

    public ValidUserDto(){}

    
    public ValidUserDto(String validUsername, String validEmail) {
        this.validUsername = validUsername;
        this.validEmail = validEmail;
    }


    public String getValidUsername() {
        return validUsername;
    }
    public void setValidUsername(String validUsername) {
        this.validUsername = validUsername;
    }
    public String getValidEmail() {
        return validEmail;
    }
    public void setValidEmail(String validEmail) {
        this.validEmail = validEmail;
    }
    
    
}
