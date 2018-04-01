package cmsc355.group.moms_warehouse.database.tables;


import com.google.firebase.database.DatabaseReference;

import cmsc355.group.moms_warehouse.database.data.ItemData;

public class ItemTable extends DatabaseTable<ItemData>{

    public ItemTable(){
        super("items", ItemData.class);
    }

    protected DatabaseReference newEntry(ItemData entry){
        return db.child(entry.name);
    }
}
