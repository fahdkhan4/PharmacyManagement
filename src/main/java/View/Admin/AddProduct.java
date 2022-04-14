package View.Admin;

import Model.Product;
import View.View_Medicines;
import dao.*;

import javax.swing.*;
import java.awt.*;


public class AddProduct {

    //                                                                  Making frame.....
    JFrame addProduct_frame = new JFrame();
    JLabel p_barcode, p_name , p_variant, p_quantity , cost_price, sell_price;
    JTextField p_codeText, p_nameText, p_variantText,p_quantityText,p_costpriceText,p_sellpriceText;
    JButton add_product , back_toAdminPage , medicineListing;


    public AddProduct(){

//                                                                     Main heading
        mainHeading();
        addProduct_frame.getContentPane().setBackground(Color.BLUE);
//                                                                     Making Inputs Fields.....
        p_barcode = new JLabel("Product BarCode: ");
        p_barcode.setFont(new Font("Serif",Font.BOLD,18));
        p_barcode.setForeground(Color.ORANGE);
        p_codeText = new JTextField();

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

        cost_price = new JLabel("Cost Price : ");
        cost_price.setFont(new Font("Serif",Font.BOLD,18));
        cost_price.setForeground(Color.ORANGE);
        p_costpriceText = new JTextField();

        sell_price= new JLabel("Sell Price: ");
        sell_price.setFont(new Font("Serif",Font.BOLD,18));
        sell_price.setForeground(Color.ORANGE);
        p_sellpriceText = new JTextField();

//                                                                         Label size.....

        p_barcode.setBounds(450,250,150,30);
        p_name.setBounds(450,300,100,30);
        p_variant.setBounds(450,350,220,30);
        p_quantity.setBounds(450,400,200,30);
        cost_price.setBounds(450,450,150,30);
        sell_price.setBounds(450,500,150,30);

//                                                                          Text Field Size......
        p_codeText.setBounds(600,250,250,40);
        p_nameText.setBounds(600,300,250,40);
        p_variantText.setBounds(600,350,250,40);
        p_quantityText.setBounds(600,400,250,40);
        p_costpriceText.setBounds(600,450,250,40);
        p_sellpriceText.setBounds(600,500,250,40);

//                                                                            Frame Buttons
        addProduct_frame.setTitle("Add Medicine");
        add_product = new JButton("Add Medicine");
        medicineListing = new JButton("Medicine Listing");
        back_toAdminPage = new JButton("Back to Admin Page");



//                                                                  Size of Buttons, inputs and frame....

        add_product.setBounds(500,600,120,40);
        add_product.setBackground(Color.ORANGE);
        add_product.setForeground(Color.BLACK);
        medicineListing.setBounds(650,600,140,40);
        medicineListing.setBackground(Color.ORANGE);
        medicineListing.setForeground(Color.BLACK);
        back_toAdminPage.setBounds(550,650,170,40);
        back_toAdminPage.setBackground(Color.ORANGE);
        back_toAdminPage.setForeground(Color.BLACK);
        addProduct_frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        addProduct_frame.setUndecorated(true);

//                                                                    Add Label AND TEXT FIELD ....
        addProduct_frame.add(p_barcode);
        addProduct_frame.add(p_codeText);

        addProduct_frame.add(p_name);
        addProduct_frame.add(p_nameText);

        addProduct_frame.add(p_variant);
        addProduct_frame.add(p_variantText);

        addProduct_frame.add(p_quantity);
        addProduct_frame.add(p_quantityText);

        addProduct_frame.add(cost_price);
        addProduct_frame.add(p_costpriceText);

        addProduct_frame.add(sell_price);
        addProduct_frame.add(p_sellpriceText);

//                                                                      ADD BUTTONS.....

        addProduct_frame.add(add_product);
        addProduct_frame.add(medicineListing);
        addProduct_frame.add(back_toAdminPage);

        addProduct_frame.setLayout(null);
        addProduct_frame.setVisible(true);

        add_product.addActionListener(e->{
            add_ProductFunctionality();
        });
        view_Medicines(medicineListing);
        backTo_AdminPage(back_toAdminPage);

    }
//                                                                              add product functionality
    public void add_ProductFunctionality() {

        if(p_codeText.getText().equalsIgnoreCase("") || p_nameText.getText().equalsIgnoreCase("") || p_variantText.getText().equalsIgnoreCase("") || p_costpriceText.getText().equalsIgnoreCase("") || p_sellpriceText.getText().equalsIgnoreCase("") || p_quantityText.getText().equalsIgnoreCase("")) {
            JOptionPane.showMessageDialog(addProduct_frame,"Please fill all the fields !!");

        }else {
            try {

                Long product_id = Long.parseLong(p_codeText.getText());
                Double productCost_price = Double.parseDouble(p_costpriceText.getText());
                Double productSell_price = Double.parseDouble(p_sellpriceText.getText());
                Integer product_quantity = Integer.parseInt(p_quantityText.getText());


                Product product = new Product(product_id, p_nameText.getText(), p_variantText.getText(), productCost_price, productSell_price, product_quantity);

                ProductFunctionality_Dao functionality_dao = new ProductFunctionality_Dao();
                functionality_dao.insertProduct_ToDB(product);

                if (DBService.duplicate_check) {
                    JOptionPane.showMessageDialog(addProduct_frame, "This Product is already Exist");
                    p_codeText.setText("");
                    p_nameText.setText("");
                    p_variantText.setText("");
                    p_costpriceText.setText("");
                    p_sellpriceText.setText("");
                    p_quantityText.setText("");
                } else {
                    JOptionPane.showMessageDialog(addProduct_frame, "Product added");
                    p_codeText.setText("");
                    p_nameText.setText("");
                    p_variantText.setText("");
                    p_costpriceText.setText("");
                    p_sellpriceText.setText("");
                    p_quantityText.setText("");
                }
            }catch (Exception e){
                System.out.println(e);
                JOptionPane.showMessageDialog(addProduct_frame,"Invalid input type");
            }
        }
    }

    public void view_Medicines(JButton viewMedicine){
        viewMedicine.addActionListener(el->{
            addProduct_frame.dispose();
            Admin_ViewMedicine view = new Admin_ViewMedicine();
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
