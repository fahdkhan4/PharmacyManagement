package View;


import dao.DBService;
import dao.Invoice_Dao;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.geom.*;

public class Receipt extends JFrame implements ActionListener {
    Invoice_Dao invoice_dao = new Invoice_Dao();
    public static Double bHeight = 0.0;

    public static void main(String[] args) {
        new Receipt();
    }

    public Receipt() {
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

        JButton printButton = new JButton("Print the receipt");
        printButton.addActionListener(this);
        printButton.setBackground(Color.ORANGE);
        printButton.setForeground(Color.BLUE);
        printButton.setFont(new Font("TimeRomans",Font.BOLD,15));
        printButton.setBounds(1150,100,160,50);
        add(printButton);

        DrawingPane drawingPanel = new DrawingPane();
        content.add(drawingPanel, BorderLayout.CENTER);

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent event) {
        PrintableDocument.printComponent(this);
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
            int yShift = 12;
            int headerRectHeight=15;


            graph.setFont(new Font("Monospaced",Font.PLAIN,15));

            graph.drawString("-------------------------------------",12,y);y+=yShift;
            graph.drawString("      PharmacyManagement.pk       ",12,y);y+=yShift;
            graph.drawString("    No 00000 Address Line One   ",12,y);y+=yShift;
            graph.drawString("     Gulistan-e-Johor Karachi    ",12,y);y+=yShift;
            graph.drawString("     www.pharmacyManagement     ",12,y);y+=yShift;
            graph.drawString("        Contact us 0000       ",12,y);y+=yShift;
            graph.drawString("-------------------------------------",12,y);y+=headerRectHeight;

            graph.drawString(" ORDER ID  : "+ DBService.orderID,10,y);y+=headerRectHeight;
            graph.drawString(" Employee Name : "+ EmployeeLogin.activeEmployee,10,y);y+=headerRectHeight;

            graph.drawString(" Item Name                             Price   ",10,y);y+=yShift;
            graph.drawString("------------------------------------------------",10,y);y+=headerRectHeight;

            int size = invoice_dao.getDataOf_InvoiceLine().size();
            bHeight = Double.valueOf(invoice_dao.getDataOf_InvoiceLine().size());


            for(int s=0; s < size; s++)
            {
                graph.drawString(" "+invoice_dao.getDataOf_InvoiceLine().get(s).getItem_name()+"                            ",10,y);y+=yShift;
                graph.drawString("      "+invoice_dao.getDataOf_InvoiceLine().get(s).getItem_qty()+" * "+invoice_dao.getDataOf_InvoiceLine().get(s).getItem_varient(),10,y); graph.drawString(" "+invoice_dao.getDataOf_InvoiceLine().get(s).getItem_price(),160,y);y+=yShift;

            }

            AffineTransform origTransform = graph.getTransform();
            graph.shear(-0.95, 0);
            graph.scale(1, 3);

        }
    }
}