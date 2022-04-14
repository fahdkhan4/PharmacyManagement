package dao;

import java.sql.ResultSet;
import java.util.HashMap;

public interface Admin_Dao {

//                                                                      retrieve data from admin table DB
    static HashMap<String ,String> getAdminDetail(){
        HashMap<String,String> loginRequirements = new HashMap();

        try{
            ResultSet rs = DBService.query("SELECT * FROM adminlogin");
            while(true){
                assert rs != null;
                if(!rs.next())
                    break;
                loginRequirements.put(rs.getString("username"),rs.getString("password"));
            }
            DBService.con.close();
        }catch (Exception e)
        {
            System.out.println(e);
        }

        return loginRequirements;
    }
}
