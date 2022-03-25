package View.Admin;

import View.View_Medicines;

import javax.swing.*;
import java.awt.*;

public class AddProduct {

    //                                                                  Making frame.....
    JFrame addProduct_frame = new JFrame();
    JLabel p_id, p_name , p_variant, p_quantity , p_price;
    JTextField p_idText, p_nameText, p_variantText,p_quantityText, p_priceText;
    JButton add_product , back_toAdminPage , medicineListing;

    public AddProduct(){

//                                                                  Main heading
        mainHeading();
        addProduct_frame.getContentPane().setBackground(Color.BLUE);
//                                                                   Making Inputs Fields.....
        p_id = new JLabel("Product ID : ");
        p_id.setFont(new Font("Serif",Font.BOLD,18));
        p_id.setForeground(Color.ORANGE);
        p_idText = new JTextField();

        p_name = new JLabel(" Name :");
        p_name.setFont(new Font("Serif",Font.BOLD,18));
        p_name.setForeground(Color.ORANGE);
        p_nameText = new JTextField();


        p_variant = new JLabel(" Product Varient : ");
        p_variant.setFont(new Font("Serif",Font.BOLD,18));
        p_variant.setForeground(Color.ORANGE);
        p_variantText = new JTextField();

        p_quantity = new JLabel("Product Quantity :");
        p_quantity.setFont(new Font("Serif",Font.BOLD,18));
        p_quantity.setForeground(Color.ORANGE);
        p_quantityText = new JTextField();

        p_price = new JLabel("Product Price : ");
        p_price.setFont(new Font("Serif",Font.BOLD,18));
        p_price.setForeground(Color.ORANGE);
        p_priceText = new JTextField();

//                                                                         Label size.....

        p_id.setBounds(450,250,150,30);
        p_name.setBounds(450,300,100,30);
        p_variant.setBounds(450,350,220,30);
        p_quantity.setBounds(450,400,200,30);
        p_price.setBounds(450,450,150,30);

//                                                                          Text Field Size......
        p_idText.setBounds(600,250,250,40);
        p_nameText.setBounds(600,300,250,40);
        p_variantText.setBounds(600,350,250,40);
        p_quantityText.setBounds(600,400,250,40);
        p_priceText.setBounds(600,450,250,40);

//                                                                            Frame Buttons
        addProduct_frame.setTitle("Add Medicine");
        add_product = new JButton("Add Medicine");
        medicineListing = new JButton("Medicine Listing");
        back_toAdminPage = new JButton("Back to Admin Page");



//                                                                  Size of Buttons, inputs and frame....

        add_product.setBounds(500,550,120,40);
        add_product.setBackground(Color.ORANGE);
        add_product.setForeground(Color.BLACK);
        medicineListing.setBounds(650,550,140,40);
        medicineListing.setBackground(Color.ORANGE);
        medicineListing.setForeground(Color.BLACK);
        back_toAdminPage.setBounds(550,600,170,40);
        back_toAdminPage.setBackground(Color.ORANGE);
        back_toAdminPage.setForeground(Color.BLACK);
        addProduct_frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        addProduct_frame.setUndecorated(true);

//                                                                    Add Label AND TEXT FIELD ....
        addProduct_frame.add(p_id);
        addProduct_frame.add(p_idText);

        addProduct_frame.add(p_name);
        addProduct_frame.add(p_nameText);

        addProduct_frame.add(p_variant);
        addProduct_frame.add(p_variantText);

        addProduct_frame.add(p_quantity);
        addProduct_frame.add(p_quantityText);

        addProduct_frame.add(p_price);
        addProduct_frame.add(p_priceText);

//                                                                      ADD BUTTONS.....

        addProduct_frame.add(add_product);
        addProduct_frame.add(medicineListing);
        addProduct_frame.add(back_toAdminPage);

        addProduct_frame.setLayout(null);
        addProduct_frame.setVisible(true);

        view_Medicines(medicineListing);
        backTo_AdminPage(back_toAdminPage);

    }
    public void add_Product(JButton addProduct){
//            A Service method will take all values and then locate it to the database
    }
    public void view_Medicines(JButton viewMedicine){
        viewMedicine.addActionListener(el->{
            View_Medicines view = new View_Medicines();
        });
    }
    public void mainHeading(){
        JLabel heading = new JLabel("ADD PRODUCT");
        heading.setBounds(480,100,500,100);
        heading.setForeground(Color.ORANGE);
        heading.setFont(new Font("Serif", Font.BOLD, 60));
        addProduct_frame.add(heading);
    }
    public void backTo_AdminPage(JButton back_ToAdmin){
        back_ToAdmin.addActionListener(el->{
            addProduct_frame.dispose();
            AdminFunctionality_UI functionality = new AdminFunctionality_UI();
        });
    }


}
