package dao;
import java.awt.*;
import java.sql.*;

public class DBService {
    public static Connection con;
    public static Boolean duplicate_check;

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

    public static void PreparedQuery(String query) {
        duplicate_check = false;
        try {
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.executeUpdate();

        }catch (Exception e){
            duplicate_check = true;
            System.out.println(e);
        }
    }





}
