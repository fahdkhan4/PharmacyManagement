package View.Admin;

import View.View_Medicines;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Admin_ViewMedicine extends View_Medicines {

    JButton update,delete;

    @Override
    public void workingOf_ExitButton(JButton exit) {
        exit.addActionListener(el->{
            AdminFunctionality_UI admin = new AdminFunctionality_UI();
        });
    }

    Admin_ViewMedicine(){
        medicine.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = medicine.rowAtPoint(e.getPoint());
                int col = 0;
                if(row >= 0) {
                    String value = medicine.getModel().getValueAt(row, col).toString();
                    System.out.println(value);
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

    }
}
