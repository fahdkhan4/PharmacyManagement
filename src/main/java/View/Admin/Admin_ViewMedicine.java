package View.Admin;

import Model.Product;
import Service.ProductService;
import dao.ProductFunctionality_Dao;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.util.HashSet;

public class Admin_ViewMedicine  {

    public static Product updateProduct;

    public JFrame viewMedicine_frame = new JFrame("Medicine");
    public JTable medicine;
    public static Boolean deleterowError;
    DefaultTableModel medicineDefaultTable;
    JButton update,delete;
    JButton exit;


    Admin_ViewMedicine(){

        HashSet<Long> medicineCode_Delete = new HashSet<>();

        String[] columns = new String[] {"Medicine Code", "Medicine Name", "Medicine Varient", "Medicine price","Medicine Quantity"};

        ProductService productService = new ProductService();
        Object [][] data = productService.getAllMedicineForUpdate();

        medicineDefaultTable = new DefaultTableModel(data,columns);
        medicine = new JTable(medicineDefaultTable);

        medicine.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        for (int col = 0; col < medicine.getColumnCount(); col++)
        {
            TableColumn tableColumn = medicine.getColumnModel().getColumn(col);
            int preferredWidth = tableColumn.getMinWidth()+200;
            int maxWidth = tableColumn.getMaxWidth();

            for (int row = 0; row < medicine.getRowCount(); row++)
            {
                TableCellRenderer cellRenderer = medicine.getCellRenderer(row, col);
                Component c = medicine.prepareRenderer(cellRenderer, row, col);
                int width = c.getPreferredSize().width + medicine.getIntercellSpacing().width;
                preferredWidth = Math.max(preferredWidth, width);

                if (preferredWidth >= maxWidth)
                {
                    preferredWidth = maxWidth;
                    break;
                }
            }
            tableColumn.setPreferredWidth(preferredWidth);
        }

        medicine.setRowHeight(medicine.getRowHeight()+10);

        JScrollPane scroll = new JScrollPane(medicine);
        medicine.setFillsViewportHeight(true);

        JLabel labelHead = new JLabel("View Medicines",SwingConstants.CENTER);
        labelHead.setFont(new Font("Arial",Font.TRUETYPE_FONT,40));

//

        exit = new JButton("Back");
        exit.setBounds(1250,3,90,40);
        exit.setBackground(Color.ORANGE);
        exit.setForeground(Color.BLACK);
        viewMedicine_frame.add(exit);


        workingOf_ExitButton(exit);
        update = new JButton("Update Medicine");
        update.setBackground(Color.ORANGE);
        update.setForeground(Color.BLACK);
        update.setBounds(1150,60,200,50);
        viewMedicine_frame.add(update);

        delete = new JButton("Delete Medicine");
        delete.setBackground(Color.ORANGE);
        delete.setForeground(Color.BLACK);
        delete.setBounds(1150,130,200,50);
        viewMedicine_frame.add(delete);


        medicine.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = medicine.rowAtPoint(e.getPoint());
                int col = 0;
                if(row >= 0) {
                    try {
                        Long id = (Long) medicine.getModel().getValueAt(row, col);
                        String medicine_name = (String) medicine.getModel().getValueAt(row, col + 1);
                        String medicine_varient = (String) medicine.getModel().getValueAt(row, col + 2);
                        Double medicine_price = Double.parseDouble(medicine.getModel().getValueAt(row, col + 3).toString());
                        Integer medicine_quantity = (Integer) medicine.getModel().getValueAt(row, col + 4);

                        updateProduct = new Product(id, medicine_name, medicine_varient, null, medicine_price, medicine_quantity);
                        medicineCode_Delete.add(id);
                    }catch (Exception err){
                        System.out.println(err);
                    }
                }
            }
            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
        });

        update.addActionListener(el->{
            if(updateProduct == null){
                JOptionPane.showMessageDialog(viewMedicine_frame,"First Select a product to update");
            }
            else {
                viewMedicine_frame.dispose();
                UpdateMedicine update = new UpdateMedicine(updateProduct);
                updateProduct = null;
            }
        });

        delete.addActionListener(el->{
            if(medicineCode_Delete.size() != 0){
                deleterowError = true;
                updateProduct = null;
                ProductFunctionality_Dao functionality_dao = new ProductFunctionality_Dao();


                if(deleterowError) {

                    int option = JOptionPane.showConfirmDialog(viewMedicine_frame, "Press Ok to Delete", "Delete Medicine", JOptionPane.OK_CANCEL_OPTION);
                    if(option == JOptionPane.OK_OPTION) {
                        functionality_dao.delete_Medicines(medicineCode_Delete);

                        medicineDefaultTable = (DefaultTableModel) medicine.getModel();
                        medicineDefaultTable.setRowCount(0);
                        productService.addingData(medicineDefaultTable,medicine);

                        deleterowError = false;
                    }

                }

                medicineCode_Delete.clear();

            }
            else{
                JOptionPane.showMessageDialog(viewMedicine_frame,"First choose the medicine to delete");
            }
        });

        viewMedicine_frame.getContentPane().add(labelHead,BorderLayout.PAGE_START);
                                                                                    //add table to frame
        viewMedicine_frame.getContentPane().add(scroll,BorderLayout.CENTER);
        viewMedicine_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        viewMedicine_frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        viewMedicine_frame.setUndecorated(true);
        viewMedicine_frame.setVisible(true);

    }
    public void workingOf_ExitButton(JButton exit) {
        exit.addActionListener(el->{
            viewMedicine_frame.dispose();
            AdminFunctionality_UI admin = new AdminFunctionality_UI();
        });
    }

    public static void errorOnDeleteProduct(){
        deleterowError = false;
        JOptionPane.showMessageDialog(null,"Cannot delete the product\n \t first remove it from cart");
    }
}
