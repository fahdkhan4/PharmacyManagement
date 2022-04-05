package dao;

import Model.Invoice;
import Model.Invoice_line;

import java.util.List;

public interface InvoiceDB_Dao {


    void insertInto_InvoiceDB(Invoice invoice);


    List<Invoice_line> getDataOf_InvoiceLine();

    void insertingInvoiceDataIn_InvoiceLine();

    List<Invoice_line> gettingInvoiceLine_Data();
}
