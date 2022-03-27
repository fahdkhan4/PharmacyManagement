package Service;

import dao.AdminLogin_Dao;

import java.util.HashMap;
import java.util.Map;

public class AdminLoginServices {

    public static Boolean adminLogin_details(String username,String password){
        AdminLogin_Dao.getAdminDetail();

        for(Map.Entry m :AdminLogin_Dao.getAdminDetail().entrySet()){
            if(m.getKey().equals(username)){
                if(m.getValue().equals(password)){
                    return true;
                }
            }
        }
        return false;

    }

}
