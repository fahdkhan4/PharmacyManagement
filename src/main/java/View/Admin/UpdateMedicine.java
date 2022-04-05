package View.Admin;

import Model.Product;
import dao.ProductFunctionality_Dao;

import javax.swing.*;
import java.awt.*;

public class UpdateMedicine {

    private JFrame updateProduct_frame = new JFrame();
    private JLabel p_id, p_name , p_variant, p_quantity , p_price;
    private JTextField p_idText, p_nameText, p_variantText,p_quantityText, p_priceText;

    private JButton updateProduct ,backto_AdminView;

    public UpdateMedicine(Product updateData){

        mainHeading();
        updateProduct_frame.getContentPane().setBackground(Color.BLUE);
//                                                                   Making Inputs Fields.....
        p_id = new JLabel("Product ID :");
        p_id.setFont(new Font("Serif",Font.BOLD,18));
        p_id.setForeground(Color.ORANGE);
        p_idText = new JTextField();
        p_idText.setText(String.valueOf(updateData.getBarCode()));
        p_idText.setEnabled(false);

        p_name = new JLabel(" Name :");
        p_name.setFont(new Font("Serif",Font.BOLD,18));
        p_name.setForeground(Color.ORANGE);
        p_nameText = new JTextField();
        p_nameText.setText(updateData.getMedicine_name());


        p_variant = new JLabel(" Product Varient : ");
        p_variant.setFont(new Font("Serif",Font.BOLD,18));
        p_variant.setForeground(Color.ORANGE);
        p_variantText = new JTextField();
        p_variantText.setText(updateData.getMedicine_varient());

        p_quantity = new JLabel("Product Quantity :");
        p_quantity.setFont(new Font("Serif",Font.BOLD,18));
        p_quantity.setForeground(Color.ORANGE);
        p_quantityText = new JTextField();
        p_quantityText.setText(String.valueOf(updateData.getMedicine_quantity()));

        p_price = new JLabel("Product Price : ");
        p_price.setFont(new Font("Serif",Font.BOLD,18));
        p_price.setForeground(Color.ORANGE);
        p_priceText = new JTextField();
        p_priceText.setText(String.valueOf(updateData.getMedicine_Saleprice()));
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
        updateProduct_frame.setTitle("Update Medicine");
        updateProduct = new JButton("Update Medicine");
        backto_AdminView = new JButton("Back to View Medicine");

//                                                                  Size of Buttons, inputs and frame....

        updateProduct.setBounds(500,550,160,40);
        updateProduct.setBackground(Color.ORANGE);
        updateProduct.setForeground(Color.BLACK);
        backto_AdminView.setBounds(680,550,170,40);
        backto_AdminView.setBackground(Color.ORANGE);
        backto_AdminView.setForeground(Color.BLACK);
        updateProduct_frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        updateProduct_frame.setUndecorated(true);

//                                                                    Add Label AND TEXT FIELD ....
        updateProduct_frame.add(p_id);
        updateProduct_frame.add(p_idText);

        updateProduct_frame.add(p_name);
        updateProduct_frame.add(p_nameText);

        updateProduct_frame.add(p_variant);
        updateProduct_frame.add(p_variantText);

        updateProduct_frame.add(p_quantity);
        updateProduct_frame.add(p_quantityText);

        updateProduct_frame.add(p_price);
        updateProduct_frame.add(p_priceText);

//                                                                      ADD BUTTONS.....
        updateProduct_frame.add(updateProduct);
        updateProduct_frame.add(backto_AdminView);

        updateProduct_frame.setLayout(null);
        updateProduct_frame.setVisible(true);

        updateProduct.addActionListener(e->{
            try {
                Long id = Long.parseLong(p_idText.getText());
                String updatedName = p_nameText.getText();
                String updatedVarient = p_variantText.getText();
                Double updatedPrice = Double.parseDouble(p_priceText.getText());
                Integer updatedquantity = Integer.parseInt(p_quantityText.getText());

                Product updatedData = new Product(id, updatedName, updatedVarient, null, updatedPrice, updatedquantity);

                ProductFunctionality_Dao updateMedicine_dao = new ProductFunctionality_Dao();
                updateMedicine_dao.updateMedicine(updatedData);

                JOptionPane.showMessageDialog(updateProduct_frame, "Medicine Updated");
            }catch (Exception error){
                JOptionPane.showMessageDialog(updateProduct_frame,"Invalid data");
            }

        });

        backTo_AdminPage(backto_AdminView);

    }
    public void mainHeading(){
        JLabel heading = new JLabel("UPDATE MEDICINE");
        heading.setBounds(350,100,900,100);
        heading.setForeground(Color.ORANGE);
        heading.setFont(new Font("Serif", Font.BOLD, 60));
        updateProduct_frame.add(heading);
    }
    public void backTo_AdminPage(JButton back_ToAdmin){
        back_ToAdmin.addActionListener(el->{
            updateProduct_frame.dispose();
            Admin_ViewMedicine view  = new Admin_ViewMedicine();
        });
    }

    }

