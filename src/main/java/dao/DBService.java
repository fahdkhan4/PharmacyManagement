package dao;
import java.sql.*;

public class DBService {
    public static Connection con;
    static{
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con= DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/pharmacy_management","root","");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public static ResultSet query(String query) throws SQLException {

        try{
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery(query);

            return rs;
        }catch(Exception e){
            System.out.println(e);
        }
        return null;
    }
}
