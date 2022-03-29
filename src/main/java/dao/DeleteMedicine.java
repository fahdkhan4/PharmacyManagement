package dao;


import java.util.ArrayList;
import java.util.HashSet;

public class DeleteMedicine {

    DeleteMedicine deleteMedicine = new DeleteMedicine();

    public static void delete_Medicines(HashSet<Integer> productCode){
        String query ;
        for (Integer code:productCode) {
            query = "DELETE FROM medicines  WHERE id="+code;
            DBService.PreparedQuery(query);
        }
    }
}
