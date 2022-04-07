package View;

import Service.EmployeeLoginService;
import View.Admin.Admin;
import javax.swing.*;
import java.awt.*;


public class EmployeeLogin {

    public static String activeEmployee;

    public EmployeeLogin(){
        JFrame employeeLogin = new JFrame("Employee Login Page");
        JPanel panel = new JPanel();

//                                                                                  heading field
        mainHeading(employeeLogin);
        subHeading(employeeLogin);
//                                                                                  INPUTS .....
        JLabel name_label = new JLabel("User Name : ");
        name_label.setFont(new Font("Serif",Font.BOLD,20));
        name_label.setForeground(Color.ORANGE);
        JTextField name_text = new JTextField();
        employeeLogin.getContentPane().setBackground(Color.BLUE);

        JLabel password_label  = new JLabel("Password");
        password_label.setFont(new Font("Serif",Font.BOLD,20));
        password_label.setForeground(Color.ORANGE);
        JPasswordField password = new JPasswordField();

        JButton login = new JButton("Login");
        login.setForeground(Color.BLACK);
        login.setBackground(Color.ORANGE);
        JButton adminpage = new JButton("Admin login");
        adminpage.setForeground(Color.BLACK);
        adminpage.setBackground(Color.ORANGE);


//                                                                                  INPUTS SIZE...
        name_label.setBounds(500,350,120,40);
        name_text.setBounds(620,350,250,40);

        password_label.setBounds(500,400,100,40);
        password.setBounds(620,400,250,40);

        login.setBounds(530,500,120,40);
        adminpage.setBounds(680,500,120,40);
//                                                                                   ADDING BUTTONS
        employeeLogin.add(name_label);
        employeeLogin.add(name_text);

        employeeLogin.add(password_label);
        employeeLogin.add(password);

        employeeLogin.add(login);
        employeeLogin.add(adminpage);


//                                                                          employee frame
        employeeLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        employeeLogin.setExtendedState(JFrame.MAXIMIZED_BOTH);
        employeeLogin.setLocationRelativeTo(null);
        employeeLogin.setUndecorated(false);
        employeeLogin.setLayout(null);
        employeeLogin.setVisible(true);



        JMenuBar menu_bar;
        JMenu file, edit, about,x;
        JMenuItem cut, copy, paste, selectAll;
        employeeLogin.getJMenuBar();

//                                                              declaration Menu bar
        cut = new JMenuItem("Cut");
        copy = new JMenuItem("Copy");
        paste = new JMenuItem("Paste");
        selectAll = new JMenuItem("Select All");


        file = new JMenu("File");
        edit = new JMenu("Edit");
        about = new JMenu("About");
//
        edit.add(cut);edit.add(copy);edit.add(paste);edit.add(selectAll);
        menu_bar = new JMenuBar();
        menu_bar.add(file);menu_bar.add(edit);menu_bar.add(about);

//                                                                                   Buttons on click

        login.addActionListener(el -> {

            Boolean bool1 =  EmployeeLoginService.employeeLogin_details(name_text.getText(),String.valueOf(password.getPassword()));
            if(bool1) {
                employeeLogin.dispose();
                activeEmployee = activeEmployee_name(name_text.getText());
                Employee_Functionality employeeFunctionality = new Employee_Functionality();
            }
            else{
                JOptionPane.showMessageDialog(employeeLogin,"Invalid Username OR Password");
            }
        });

        adminpage.addActionListener(el->{
            employeeLogin.dispose();
            Admin admin = new Admin();
        });
    }

    public void mainHeading(Frame frame){
        JLabel heading = new JLabel("Pharmacy Management System");
        heading.setForeground(Color.BLACK);
        heading.setBounds(230,30,1000,100);
        heading.setFont(new Font("Serif", Font.BOLD, 70));
        frame.add(heading);
    }
    public void subHeading(Frame frame){
        JLabel subheading = new JLabel("Employee Login");
        subheading.setForeground(Color.ORANGE);
        subheading.setBounds(520,200,700,100);
        subheading.setFont(new Font("Serif", Font.BOLD, 50));
        frame.add(subheading);
    }
    public static String activeEmployee_name(String name){
        return name;
    }

}

