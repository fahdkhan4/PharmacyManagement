package View.Admin;

import View.WindowShow;
import dao.CartProduct;
import dao.DBService;
import dao.Invoice_Dao;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.geom.*;

public class AdminReceipt extends JFrame implements ActionListener {
    Invoice_Dao invoice_dao = new Invoice_Dao();
    CartProduct cart = new CartProduct();
    public static Double admin_bHeight = 0.0;

    public static void main(String[] args) {
        new AdminReceipt();
    }

    public AdminReceipt() {

        super("Receipt");
        WindowShow.setNativeLookAndFeel();
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                super.windowOpened(e);
            }
        });

        JLabel labelHead = new JLabel("Medicine Receipt",SwingConstants.CENTER);
        labelHead.setFont(new Font("Arial",Font.TRUETYPE_FONT,40));
        getContentPane().add(labelHead,BorderLayout.PAGE_START);

        Container content = getContentPane();

        JButton exit = new JButton("Back");
        exit.setForeground(Color.BLACK);
        exit.setBackground(Color.ORANGE);
        exit.setBounds(1100,100,100,50);
        exit.setFont(new Font("TimeRomans",Font.BOLD,15));
        add(exit);

        JButton printButton = new JButton("Print the receipt");
        printButton.addActionListener(this);
        printButton.setBackground(Color.ORANGE);
        printButton.setForeground(Color.BLUE);
        printButton.setFont(new Font("TimeRomans",Font.BOLD,15));
        printButton.setBounds(900,100,160,50);
        add(printButton);

        DrawingPane drawingPanel = new DrawingPane();
        content.add(drawingPanel, BorderLayout.CENTER);

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setVisible(true);

        workingOfExitButton(exit);
    }

    public void workingOfExitButton(JButton exit){
        exit.addActionListener(el->{
            dispose();
            AdminOrder_Medicine medicine = new AdminOrder_Medicine();
        });
    }


    public void actionPerformed(ActionEvent event) {
        Admin_PrintableDocument.printComponent(this);
    }

    class DrawingPane extends JPanel {
        private int fontSize = 20;
        private int messageWidth;

        public DrawingPane() {
            setBackground(Color.white);
            int width = messageWidth*5/3;
            int height = fontSize*3;
            setPreferredSize(new Dimension(width, height));
        }

        public void paintComponent(Graphics g) {

            Graphics2D graph = (Graphics2D)g;
            int y=20;
            int yShift = 15;
            int headerRectHeight=15;


            graph.setFont(new Font("Monospaced",Font.PLAIN,15));

            graph.drawString("--------------------------------------",12,y);y+=yShift;
            graph.drawString("      PharmacyManagement.pk       ",12,y);y+=yShift;
            graph.drawString("    No 00000 Address Line One   ",12,y);y+=yShift;
            graph.drawString("     Gulistan-e-Johor Karachi    ",12,y);y+=yShift;
            graph.drawString("     www.pharmacyManagement     ",12,y);y+=yShift;
            graph.drawString("        Contact us 0000       ",12,y);y+=yShift;
            graph.drawString("--------------------------------------",12,y);y+=headerRectHeight;

            graph.drawString(" ORDER ID  : "+ DBService.orderID,10,y);y+=headerRectHeight;
            graph.drawString(" Employee Name : "+invoice_dao.getDataOf_Invoice().get(0).getEmp_name(),10,y);y+=headerRectHeight;

            graph.drawString("--------------------------------------",10,y);y+=headerRectHeight;
            graph.drawString(" Item Name                       Price  ",10,y);y+=yShift;
            graph.drawString("--------------------------------------",10,y);y+=headerRectHeight;

            int size = invoice_dao.getDataOf_InvoiceLine().size();
            admin_bHeight = Double.valueOf(invoice_dao.getDataOf_InvoiceLine().size()+10);

            for(int s=0; s < size; s++)
            {
                graph.drawString("   "+invoice_dao.gettingInvoiceLine_Data().get(s).getItem_name()+" "+invoice_dao.gettingInvoiceLine_Data().get(s).getItem_varient()+"                           ",10,y);y+=yShift;
                graph.drawString("           "+invoice_dao.gettingInvoiceLine_Data().get(s).getItem_qty()+" * "+invoice_dao.gettingInvoiceLine_Data().get(s).getProduct_price(),10,y); graph.drawString("                "+invoice_dao.gettingInvoiceLine_Data().get(s).getItemQuantity_price(),160,y);y+=yShift;

            }

            graph.drawString("-------------------------------------",10,y);y+=yShift;
            graph.drawString(" Amount :                        "+cart.cartProductTotalAmount()+"   ",10,y);y+=yShift;
            graph.drawString("-------------------------------------",10,y);y+=yShift;
            graph.drawString(" Discount      :                 "+20+"   ",10,y);y+=yShift;
            graph.drawString("-------------------------------------",10,y);y+=yShift;
            graph.drawString(" Total Amount   :                "+cart.cartProductTotalAmount()+50+"   ",10,y);y+=yShift;

            graph.drawString("*************************************",10,y);y+=yShift;
            graph.drawString("       THANK YOU COME AGAIN            ",10,y);y+=yShift;
            graph.drawString("*************************************",10,y);y+=yShift;
            graph.drawString("         Fahd and Anus          ",10,y);y+=yShift;
            graph.drawString("   CONTACT: fk1271543@gmail.com       ",10,y);y+=yShift;

            AffineTransform origTransform = graph.getTransform();
            graph.shear(-0.95, 0);
            graph.scale(1, 3);


        }
    }
}