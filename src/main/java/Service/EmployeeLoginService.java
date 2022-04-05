package Service;

import dao.Admin_Dao;
import dao.EmployeeLogin_Dao;

import java.util.Map;

public class EmployeeLoginService {

    public static Boolean employeeLogin_details(String username,String password){

        EmployeeLogin_Dao.getEmployeeDetail();
        for(Map.Entry m :      EmployeeLogin_Dao.getEmployeeDetail().entrySet()){
            if(m.getKey().equals(username)){
                if(m.getValue().equals(password)){
                    return true;
                }
            }

        }
        return false;
    }

}
