package evaluation.frontoffice.helper;

import java.util.ArrayList;
import java.util.List;

public class ActionResult {
    List<Error> errors;

    

    public ActionResult() {
        this.errors=new ArrayList<>();
    }

    public List<Error> getErrors() {
        return errors;
    }

    public void setErrors(List<Error> errors) {
        this.errors = errors;
    }

    public boolean hasErrors(){
        boolean result=false;
            if(this.errors.size()>0){
                result=true;
            }
        return result;
    }

    public boolean hasError(String name){
        boolean result=false;
        for(Error error:errors){
            if(error.getName().equals(name)){
                result=true;
                break;
            }
        }
        return result;
    }

    public void setError(String name,String message)throws Exception{

        boolean isAlreadyUse=this.hasError(name);
        if(isAlreadyUse){
            throw new Exception("Nom d'erreur deja utilise :" + name);
        }else{
            this.errors.add(new Error(name, message));
        }
    }

    public Error getError(String name)throws Exception{
        Error result=null;
        for(Error error:errors){
            if(error.getName().equals(name)){
                result=error;
                break;
            }
        }
        if(result==null){
            throw new Exception("Nom d'erreur introuvable:" + name);
        }
        return result;
    }
    
}
