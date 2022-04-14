package View.Admin;


import java.awt.*;
import javax.swing.*;
import java.awt.print.*;

public class Admin_PrintableDocument implements Printable {

        private Component compent;

        public static void printComponent(Component c) {
            new View.Admin.Admin_PrintableDocument(c).print();
        }

        public Admin_PrintableDocument(Component compent) {
            this.compent = compent;
        }

        public PageFormat getPageFormat(PrinterJob pj)
        {

            Double bHeight = AdminReceipt.admin_bHeight ;
            PageFormat pf = pj.defaultPage();
            Paper paper = pf.getPaper();

            double bodyHeight = bHeight;
            double headerHeight = 5.0;
            double footerHeight = 5.0;
            double width = cm_to_pp(14);
            double height = cm_to_pp(headerHeight+bodyHeight+footerHeight);
            paper.setSize(width, height);
            paper.setImageableArea(0,10,width,height - cm_to_pp(1));

            pf.setOrientation(PageFormat.PORTRAIT);
            pf.setPaper(paper);

            return pf;
        }
        protected static double cm_to_pp(double cm)
        {
            return toPPI(cm * 0.393600787);
        }

        protected static double toPPI(double inch)
        {
            return inch * 72d;
        }

        public void print() {

            PrinterJob printJob = PrinterJob.getPrinterJob();
            printJob.setPrintable(this,getPageFormat(printJob));
            if(printJob.printDialog())
                try {
                    printJob.print();
                }
                catch(PrinterException pe) {
                    System.out.println("Error printing: " + pe);
                }
        }

        public int print(Graphics g, PageFormat pageFormat, int pageIndex) {
            if (pageIndex > 0) {
                return(NO_SUCH_PAGE);
            }
            else {
                Graphics2D graph = (Graphics2D)g;
                graph.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
                disableBuffering(compent);
                compent.paint(graph);
                enableBuffering(compent);
                return(PAGE_EXISTS);
            }
        }

        public static void disableBuffering(Component c) {
            RepaintManager currentManager = RepaintManager.currentManager(c);
            currentManager.setDoubleBufferingEnabled(false);
        }

        public static void enableBuffering(Component c) {
            RepaintManager currentManager = RepaintManager.currentManager(c);
            currentManager.setDoubleBufferingEnabled(true);
        }
    }


