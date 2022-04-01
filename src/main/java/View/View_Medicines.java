package View;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.util.List;

import Model.Product;
import Service.GetAllMedicines;
import Service.ProductService;

public class View_Medicines {

    public JFrame viewMedicine_frame = new JFrame("Medicine Listing");
    JButton orderMedicine;
    public JTable medicine;


    public View_Medicines(){

        JButton exit;
        JPanel panel_medicine = new JPanel();
        String[] columns = new String[] {"MedicineName", "Varient", "Price", "Quantity"};


        ProductService productService = new ProductService();
        Object [][] data = productService.getAllMedicines();
        medicine = new JTable(data, columns);


        TableColumnModel columnModel = medicine.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(20);
        columnModel.getColumn(1).setPreferredWidth(40);
        columnModel.getColumn(2).setPreferredWidth(40);
        columnModel.getColumn(3).setPreferredWidth(100);
//        columnModel.getColumn(4).setPreferredWidth(100);

        medicine.setRowHeight(medicine.getRowHeight()+10);

        JScrollPane scroll = new JScrollPane(medicine);
        medicine.setFillsViewportHeight(true);

        JLabel labelHead = new JLabel("View Medicines",SwingConstants.CENTER);
        labelHead.setFont(new Font("Arial",Font.TRUETYPE_FONT,40));

//
        exit = new JButton("Exit");
        exit.setBounds(1250,3,90,40);
        exit.setBackground(Color.ORANGE);
        exit.setForeground(Color.BLACK);
        viewMedicine_frame.add(exit);

        viewMedicine_frame.getContentPane().add(labelHead,BorderLayout.PAGE_START);
                                                                                        //add table to frame
        viewMedicine_frame.getContentPane().add(scroll,BorderLayout.CENTER);
        viewMedicine_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        viewMedicine_frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        viewMedicine_frame.setUndecorated(true);
        viewMedicine_frame.setVisible(true);

       workingOf_ExitButton(exit);

    }
    public void workingOf_ExitButton(JButton exit){
        exit.addActionListener(el->{
            viewMedicine_frame.dispose();
            Home home = new Home();
        });

    }



}
