package evaluation.frontoffice.helper;

import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Util {
    public Util() {
    }
    
     public static String toString(Calendar c){
        String endTime = "";
        DateFormat timeFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
        endTime = timeFormat.format(c.getTime());
        return endTime;
    }
    
    public static Calendar toCalendar(String dateTime){
        Calendar cal = Calendar.getInstance();
        String [] dateHeure=dateTime.split(" ");
        String [] date=dateHeure[0].split("-");
        String [] heure=dateHeure[1].split(":");
        cal.set(Integer.parseInt(date[0]),Integer.parseInt(date[1])-1,Integer.parseInt(date[2]),Integer.parseInt(heure[0]), Integer.parseInt(heure[1]),Integer.parseInt(heure[2]));
        return cal;
    }
    
        //date1 < date2
    public static long getMillisDifference(String date1,String date2){
        Calendar cal1=Calendar.getInstance();
        cal1=Util.toCalendar(date1);
        Calendar cal2=Calendar.getInstance();
        cal2=Util.toCalendar(date2);
        long g=cal2.getTimeInMillis()-cal1.getTimeInMillis();
        return g;
    }
    
    //date1 < date2
    public static int getJourDifference(String date1,String date2){
        int result =0;
        long g=Util.getMillisDifference(date1, date2);
        double minutes = (g / 1000) / 60 / 60; 
        result=(int) Math.floor(minutes/24);
        return  result;
    }
    
    public static  int getHeureDifference(String date1,String date2){
        int result =0;
        long g=Util.getMillisDifference(date1, date2);
        double minutes = (g / 1000) / 60 / 60; 
        result=(int) Math.floor(minutes);
        return  result;
    }
    
    public static  String ajoutJour(String date,int jour){
        Calendar calendar=Util.toCalendar(date);
        calendar.add(Calendar.DAY_OF_MONTH, jour);
        return  Util.toString(calendar);
    }
     
    public static boolean isWeekendDay(String date){
        boolean check=false;
        Calendar calendar=Util.toCalendar(date);
        int day=calendar.get(Calendar.DAY_OF_WEEK);
        if(day==6 || day==7){
            check=true;
        }
        return check;
    }
    
    public static int compareDate(String date1,String date2){
        return Util.toCalendar(date1).compareTo(Util.toCalendar(date2));
    }

    public  static String getDateInDateTime(String datetime){
        String result="";
        result=datetime.split(" ")[0];
        return result;
    }
    
    public static String getTimeInDateTime(String datetime){
        String result="";
        result=datetime.split(" ")[1];
        return result;
    }

    public static int nombrePage(int nombre,int size) {
		int result=0;
		result=Math.round(nombre/size);
		if(nombre%size>0) {
			result=result+1;
		}
		return result;
	}

    public static ArrayList<String> getFieldsName(Object modele) {
		ArrayList<String> colonnes=new ArrayList<String>();
		Field [] fields=modele.getClass().getDeclaredFields();
		for(Field field:fields) {
			colonnes.add(field.getName());
		}
		return colonnes;
	}
	
	
	public static ArrayList<String> getMethodGet(ArrayList<String> fields){
		ArrayList<String> method=new ArrayList<String>();
		for(String field:fields) {
			method.add("get"+ field.substring(0,1).toUpperCase() + field.substring(1));
		}
		return method;
	}
	
}
