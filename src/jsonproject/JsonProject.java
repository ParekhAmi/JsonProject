package jsonproject;

import java.io.IOException;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


public class JsonProject {

    
    public static void main(String[] args) throws IOException {
        String myJSON = FileReader.loadFileIntoString("json/Manager.json", "UTF-8");
        
        JSONObject myObject = JSONObject.fromObject(myJSON);
        JSONArray myArray = new JSONArray();
               
        JSONArray deptArray = myObject.getJSONArray("departments");      
        JSONObject deptObject= new JSONObject();
      
        
        String ID;
        int year_hire,totalEmployee=0;
        double averageMonths=0,totalMonth=0;
        
        int manager_number,numberEmployees,department_id,months,date_hire;
        String first_name,last_name,department_name,department_idname;
        boolean retired,current;
        double Salary;
        
       
        manager_number = myObject.getInt("manager_number");
        first_name = myObject.getString("first_name");
        last_name = myObject.getString("last_name");
        date_hire = Integer.parseInt(myObject.getString("date_hire").substring(myObject.getString("date_hire").length()-4));
        retired = myObject.getBoolean("retired");
        Salary = myObject.getDouble("Salary");
        
     
        
        JSONObject objWrite = new JSONObject();
         JSONArray departArray = new JSONArray();
        JSONObject departObject = new JSONObject();
           for(int i=0;i<deptArray.size();i++){
            deptObject = deptArray.getJSONObject(i);
            
            department_id = deptObject.getInt("department_id");
            department_name = deptObject.getString("department_name");
            current = deptObject.getBoolean("current");
            numberEmployees = deptObject.getInt("numberEmployees");
            months = deptObject.getInt("months");
            
            
            totalEmployee  +=  numberEmployees;    
            totalMonth += months;
          
        
        department_idname = deptObject.getInt("department_id")+"-"+deptObject.getString("department_name");
        departObject.accumulate("department",department_idname);
        
        if(deptObject.getBoolean("current")){
            departObject.accumulate("current", "Is current");
        }else{
            departObject.accumulate("current", "Is not current");
        }
        departArray.add(departObject);
        
        departObject.clear();
          
           }        
            averageMonths = totalMonth/deptObject.size();
             objWrite.accumulate("ID", manager_number+"-"+last_name);
        objWrite.accumulate("year_hire", date_hire);
        objWrite.accumulate("totalEmployee", totalEmployee);
        objWrite.accumulate("averageMonths", averageMonths);
       
    
        
        
        objWrite.accumulate("departments", departArray);
        FileWriter.saveStringIntoFile("json/ManagerResult.json", objWrite.toString());
        System.out.println(objWrite);  
        
    }
}   
