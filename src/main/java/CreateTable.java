import java.sql.*;
import java.util.Scanner;

public class CreateTable {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice = 0;
        do {
            try{
                System.out.println("1.Connection \n2.Create Table \n3.insert Data\n3.Delete DB\n");
                System.out.println("Enter your choice");
                choice = sc.nextInt();
                switch (choice){
                    case 1 :
                        System.out.println("Connection Ensure");
                        Connection conn = getConnection();
                        break;
                    case 2 :
                        System.out.println("Create Table");
                        createTable();
                        break;
                    case 3 :
                        System.out.println("insert data");
                        insertDB();
                        break;
                    case 4 :
                        System.out.println("Drop Schema");
                        DeleteDatabase();
                        break;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }while (choice != 5 );
    }
    // inserting Database
    private static void insertDB(){
        Scanner in = new Scanner(System.in);
        int id;
        String  Database, Tablename;
        String name,email,phone,address;
        System.out.println("Enter database and table name");
        Database = in.nextLine();
        Tablename = in.nextLine();

        try{
            Connection conn = getConnection();
            System.out.println("Enter id,Name,Email,Phone,Address to insert : ");
            id = Integer.parseInt(in.nextLine());
            name = in.nextLine();
            email = in.nextLine();
            phone = in.nextLine();
            address = in.nextLine();

            assert conn != null;
            PreparedStatement post = conn.prepareStatement("INSERT INTO " + Tablename + "(id, st_name, email, phone_no, address) VALUES ('"+id+"', '"+name+"','"+email+"', '"+phone+"', '"+address+"')");
            post.executeUpdate();
            System.out.println("Data should be inserted...");
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
        }
    }
    //Creating Table
    private static void createTable(){
        Scanner sc = new Scanner(System.in);
        String tableName = null;

        System.out.println("Enter the tablename");
        tableName = sc.nextLine();
        try {
            Connection con = getConnection();
            assert con != null;
            PreparedStatement create = con.prepareStatement("CREATE TABLE " +tableName+ "(\n" +
                    "    id int,\n" +
                    "    st_name varchar(255),\n" +
                    "    email varchar(255),\n" +
                    "    phone_no varchar(255),\n" +
                    "    address varchar(255)\n" +
                    ");");
            create.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error : "+e.getMessage());
        }
    }
    //Delete Database
    private static Connection DeleteDatabase(){
        Scanner sc = new Scanner(System.in);
        String dataBase = null;
        System.out.println("Enter database :");
        dataBase = sc.nextLine();
        try {
            String driver = "com.mysql.cj.jdbc.Driver";
            String user = "root";
            String pass = "kingsultan12345";
            Class.forName(driver);
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+dataBase   , user,pass);
            PreparedStatement drop = conn.prepareStatement("DROP DATABASE "+dataBase);
            drop.executeUpdate();
            System.out.println("Database dropped");
            return conn;

        } catch (Exception e) {
            System.out.println("Error : "+e.getMessage());
        }
        return null;
    }
    // getting Connection
    private static Connection getConnection()throws Exception, SQLException {
        Scanner sc = new Scanner(System.in);
        String database = null;
        System.out.println("Enter the database name");
        database = sc.nextLine();

        try {
            String driver = "com.mysql.cj.jdbc.Driver";
            String user = "root";
            String pass = "kingsultan12345";
            Class.forName(driver);

            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+database, user,pass);
            System.out.println("Connected with database");
            return conn;
        } catch (Exception e) {
            System.out.println("Error : "+e.getMessage());
        }
        return null;
    }
}
