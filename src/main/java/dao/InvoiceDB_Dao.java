package dao;

import Model.Invoice;
import Model.Invoice_line;

import java.sql.SQLException;
import java.util.List;

public interface InvoiceDB_Dao {


    void insertInto_InvoiceDB(Invoice invoice);

    List<Invoice> getDataOf_Invoice();

    List<Invoice_line> getDataOf_InvoiceLine();

    void insertingInvoiceDataIn_InvoiceLine();

    List<Invoice_line> gettingInvoiceLine_Data();
}
