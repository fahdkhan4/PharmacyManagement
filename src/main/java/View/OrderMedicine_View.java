package View;

import Model.Product;
import Service.FindMedicine;
import Service.ProductService;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.ArrayList;

public class OrderMedicine_View {

    JFrame order_frame = new JFrame("Order Medicine");
    private JTable order_medicine_table , userorder_table;
    JPanel medicineouterpanel,medicinetoppanel,headerpanel;
    private JScrollPane medicinescrollpane,userorder_Scroll;
    private ArrayList<Product> userProducts = new ArrayList<>();
    Object [][] data;
    FindMedicine medFind = new FindMedicine();

    public OrderMedicine_View(){

        JLabel nameFor_Search ;
        JTextField nameFor_SearchText;
        JButton buy_product,exit,find;

        headerpanel = new JPanel(new BorderLayout());
        medicineouterpanel = new JPanel(new BorderLayout());
        medicinetoppanel = new JPanel(new BorderLayout());


        nameFor_Search = new JLabel("Medicine Name :");
        nameFor_Search.setFont(new Font("Serif",Font.BOLD,20));
        nameFor_Search.setBounds(5,10,200,40);

        nameFor_SearchText = new JTextField(SwingConstants.LEFT);
        nameFor_SearchText.setBounds(150,7,200,40);

        find = new JButton("Find");
        find.setBounds(350,7,100,40);
        find.setBackground(Color.ORANGE);
        find.setForeground(Color.BLACK);

        order_frame.add(find);

        order_frame.add(nameFor_Search);
        order_frame.add(nameFor_SearchText);

        ProductService productService = new ProductService();
        data = productService.getAllMedicines();
        String [] column = {"Medicine ID","Medicine Name","Medicine Varient","Medicine Price","Quantity"};


        order_medicine_table = new JTable(data, column);
        order_medicine_table.setRowHeight(order_medicine_table.getRowHeight()+10);
        order_medicine_table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);


        for (int col = 0; col < order_medicine_table.getColumnCount(); col++)
        {
            TableColumn tableColumn = order_medicine_table.getColumnModel().getColumn(col);
            int preferredWidth = tableColumn.getMinWidth()+150;
            int maxWidth = tableColumn.getMaxWidth();

            for (int row = 0; row < order_medicine_table.getRowCount(); row++)
            {
                TableCellRenderer cellRenderer = order_medicine_table.getCellRenderer(row, col);
                Component c = order_medicine_table.prepareRenderer(cellRenderer, row, col);
                int width = c.getPreferredSize().width + order_medicine_table.getIntercellSpacing().width;
                preferredWidth = Math.max(preferredWidth, width);

                if (preferredWidth >= maxWidth)
                {
                    preferredWidth = maxWidth;
                    break;
                }
            }
            tableColumn.setPreferredWidth( preferredWidth );
        }

        medicinescrollpane = new JScrollPane(order_medicine_table);
        medicinetoppanel.add(medicinescrollpane,BorderLayout.CENTER);
        medicineouterpanel.add(medicinetoppanel);
        order_frame.add(medicineouterpanel);
        order_frame.pack();

        find.addActionListener(el->{

            if(nameFor_SearchText.getText().isEmpty()) {
                JOptionPane.showMessageDialog(order_frame,"Invalid Search");
            }
            else{
                medicinetoppanel.setVisible(false);
                medicinetoppanel.setVisible(false);
                medFind.getMedicineName(nameFor_SearchText.getText());
                Object  [][]data2 = medFind.findMedicine_OnSearch();
                table2(data2,column);
            }
        });

        order_medicine_table.setFillsViewportHeight(true);
        order_frame.setLocationRelativeTo(null);
        order_frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        order_frame.setVisible(true);
    }

    public  void table2(Object [][] data1,String [] column1){

        JLabel nameFor_Search ;
        JTextField nameFor_SearchText;
        JButton buy_product,exit,find;
        JPanel medicineouterpanel1,medicinetoppanel1;

        medicineouterpanel1 = new JPanel(new BorderLayout());
        medicinetoppanel1 = new JPanel(new BorderLayout());


        nameFor_Search = new JLabel("Medicine Name :");
        nameFor_Search.setFont(new Font("Serif",Font.BOLD,20));
        nameFor_Search.setBounds(5,10,200,40);

        nameFor_SearchText = new JTextField(SwingConstants.LEFT);
        nameFor_SearchText.setBounds(150,7,200,40);

        find = new JButton("Find");
        find.setBounds(350,7,100,40);
        find.setBackground(Color.ORANGE);
        find.setForeground(Color.BLACK);
        order_frame.add(find);

        order_frame.add(nameFor_Search);
        order_frame.add(nameFor_SearchText);



        userorder_table= new JTable(data1, column1);
        userorder_table.setRowHeight(userorder_table.getRowHeight()+10);
        userorder_table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);


        for (int col = 0; col < userorder_table.getColumnCount(); col++)
        {
            TableColumn tableColumn =userorder_table.getColumnModel().getColumn(col);
            int preferredWidth = tableColumn.getMinWidth()+150;
            int maxWidth = tableColumn.getMaxWidth();

            for (int row = 0; row < userorder_table.getRowCount(); row++)
            {
                TableCellRenderer cellRenderer = userorder_table.getCellRenderer(row, col);
                Component c =userorder_table.prepareRenderer(cellRenderer, row, col);
                int width = c.getPreferredSize().width + userorder_table.getIntercellSpacing().width;
                preferredWidth = Math.max(preferredWidth, width);

                if (preferredWidth >= maxWidth)
                {
                    preferredWidth = maxWidth;
                    break;
                }
            }
            tableColumn.setPreferredWidth( preferredWidth );
        }

        medicinescrollpane = new JScrollPane(userorder_table);
        medicinetoppanel1.add(medicinescrollpane,BorderLayout.CENTER);
        medicineouterpanel.add(medicinetoppanel1);
        order_frame.add(medicineouterpanel1);
        order_frame.pack();

        userorder_table.setFillsViewportHeight(true);
        order_frame.setLocationRelativeTo(null);
        order_frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        order_frame.setVisible(true);
    }

    public static void main(String[] args) {

        OrderMedicine_View v =  new OrderMedicine_View();
    }
}
