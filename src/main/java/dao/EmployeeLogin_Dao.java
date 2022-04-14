package dao;

import java.sql.ResultSet;
import java.util.HashMap;

public interface EmployeeLogin_Dao {

    //                                                                      retrieve data from employee table DB
    static HashMap<String ,String> getEmployeeDetail(){
        HashMap<String,String> loginRequirements = new HashMap();

        try{
            ResultSet rs = DBService.query("SELECT * FROM employeelogin");
            while(true){
                assert rs != null;
                if(!rs.next())
                    break;
                loginRequirements.put(rs.getString("employeeName"),rs.getString("emp_password"));
            }
            DBService.con.close();
        }catch (Exception e)
        {
            System.out.println(e);
        }
        return loginRequirements;
    }
}
