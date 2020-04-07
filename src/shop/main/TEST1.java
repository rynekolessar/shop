package shop.main;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import shop.command.Command;
import shop.data.Data;
import shop.data.Record;
import shop.data.Video;
import shop.data.Inventory;
import java.util.Iterator;

public class TEST1  {
    private Inventory _inventory = Data.newInventory();
    private void expect(Video v, String s) {
        Assertions.assertEquals(s,_inventory.get(v).toString());
    }
    private void expect(Record r, String s) {
        Assertions.assertEquals(s,r.toString());
    }

    @Test
    public void test1() {
        Command clearCmd = Data.newClearCmd(_inventory);
        clearCmd.run();

        Video v1 = Data.newVideo("Title1", 2000, "Director1");
        Assertions.assertEquals(0, _inventory.size());
        Assertions.assertTrue(Data.newAddCmd(_inventory, v1, 5).run());
        Assertions.assertEquals(1, _inventory.size());
        Assertions.assertTrue(Data.newAddCmd(_inventory, v1, 5).run());
        Assertions.assertEquals(1, _inventory.size());
        expect(v1,"Title1 (2000) : Director1 [10,0,0]");

        Video v2 = Data.newVideo("Title2", 2001, "Director2");
        Assertions.assertTrue(Data.newAddCmd(_inventory, v2, 1).run());
        Assertions.assertEquals(2, _inventory.size());
        expect(v2,"Title2 (2001) : Director2 [1,0,0]");

        Assertions.assertFalse(Data.newAddCmd(_inventory, null, 5).run());
        Assertions.assertEquals(2, _inventory.size());

        Assertions.assertTrue(Data.newAddCmd(_inventory, v2, -1).run());
        Assertions.assertEquals(1, _inventory.size());
        expect(v1,"Title1 (2000) : Director1 [10,0,0]");

        Assertions.assertTrue(Data.newAddCmd(_inventory, v2, 5).run());
        Assertions.assertEquals(2, _inventory.size());
        expect(v2,"Title2 (2001) : Director2 [5,0,0]");

        Iterator<Record> it = _inventory.iterator(new java.util.Comparator<Record>() {
            public int compare (Record r1, Record r2) {
                return r2.numRentals() - r1.numRentals();
            }
        });
    }
}
