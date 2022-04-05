package Service;

import dao.Admin_Dao;
import java.util.Map;

public class AdminLoginServices {

//             Finding username and password from hashmap

    public static Boolean adminLogin_details(String username,String password){

        Admin_Dao.getAdminDetail();
        for(Map.Entry m :  Admin_Dao.getAdminDetail().entrySet()){
            if(m.getKey().equals(username)){
                if(m.getValue().equals(password)){
                    return true;
                }
            }

        }
        return false;
    }

}
