package View.Admin;

import Service.AdminLoginServices;
import View.EmployeeLogin;

import javax.swing.*;
import java.awt.*;

public class Admin{

        public static String admin_name;

    public Admin(){

        JFrame admin_frame = new JFrame("Admin Login Page");
        JMenuBar menubar = new JMenuBar();
        JLabel label = new JLabel("Admin Login");
        menubar.add(label);
        admin_frame.add(menubar);
//                                                                                  INPUTS .....
        JLabel name_label = new JLabel("User Name : ");
        name_label.setFont(new Font("Serif",Font.BOLD,20));
        name_label.setForeground(Color.ORANGE);
        JTextField name_text = new JTextField();
        admin_frame.getContentPane().setBackground(Color.BLUE);

        JLabel password_label  = new JLabel("Password");
        password_label.setFont(new Font("Serif",Font.BOLD,20));
        password_label.setForeground(Color.ORANGE);
        JPasswordField password = new JPasswordField();

        JButton login = new JButton("Login");
        login.setForeground(Color.BLACK);
        login.setBackground(Color.ORANGE);
        JButton exit = new JButton("Back");
        exit.setForeground(Color.BLACK);
        exit.setBackground(Color.ORANGE);
//                                                                                  heading field
        mainHeading(admin_frame);

//                                                                                  INPUTS SIZE...
        name_label.setBounds(500,350,120,40);
        name_text.setBounds(620,350,250,40);

        password_label.setBounds(500,400,100,40);
        password.setBounds(620,400,250,40);

        login.setBounds(530,500,120,40);
        exit.setBounds(680,500,120,40);
//                                                                                   ADDING BUTTONS
        admin_frame.add(name_label);
        admin_frame.add(name_text);

        admin_frame.add(password_label);
        admin_frame.add(password);

        admin_frame.add(login);
        admin_frame.add(exit);

        admin_frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        admin_frame.setUndecorated(true);
        admin_frame.setLayout(null);
        admin_frame.setVisible(true);

//                                                                                   Buttons on click

          login.addActionListener(el -> {

              Boolean loginResult = AdminLoginServices.adminLogin_details(name_text.getText(),String.valueOf(password.getPassword()));
              if(loginResult) {
                  admin_name = name_text.getText();
                  admin_frame.dispose();
                  AdminFunctionality_UI admin = new AdminFunctionality_UI();
              }
              else{
                  JOptionPane.showMessageDialog(admin_frame,"Invalid Username AND Password");
              }
          });

          exit.addActionListener(el->{
            admin_frame.dispose();
              EmployeeLogin emp_login = new EmployeeLogin();
        });
    }

    public void mainHeading(Frame admin_frame){
        JLabel heading = new JLabel("ADMIN LOGIN");
        heading.setForeground(Color.ORANGE);
        heading.setBounds(500,100,700,100);
        heading.setFont(new Font("Serif", Font.BOLD, 60));
        admin_frame.add(heading);
    }



}
