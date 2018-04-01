package cmsc355.group.moms_warehouse.database;

import java.util.ArrayList;
import java.util.List;

import cmsc355.group.moms_warehouse.database.data.ItemData;
import cmsc355.group.moms_warehouse.database.tables.ItemTable;

public class DatabaseManager{

    private ItemTable itemTable;

    public DatabaseManager()
    {
        itemTable = new ItemTable();
    }

    /*******************************************************
     Warehouse Item Stuff
     *******************************************************/
    public void loadItemData()
    {
        itemTable.loadData();
    }

    public List<ItemData> getAllItems()
    {
        return new ArrayList<>(itemTable.getData().values());
    }

}

