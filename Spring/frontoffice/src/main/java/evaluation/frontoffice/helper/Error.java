package evaluation.frontoffice.helper;

public class Error {
    String name;
    String message;
    public Error() {
    }
    public Error(String name, String message) {
        this.name = name;
        this.message = message;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    
}
