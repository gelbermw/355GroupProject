package cmsc355.group.moms_warehouse.database.tables;


import com.google.firebase.database.DatabaseReference;
import cmsc355.group.moms_warehouse.database.data.Item;

public class ItemTable extends DatabaseTable<Item>{

    public ItemTable(){
        super("items", Item.class);
    }

    protected DatabaseReference newEntry(Item entry){
        return db.child(entry.getName());
    }
}
