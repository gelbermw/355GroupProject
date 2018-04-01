package cmsc355.group.moms_warehouse.database.data.Sorts;

import java.util.Comparator;

import cmsc355.group.moms_warehouse.database.data.ItemData;

public class SortByNameAscending implements Comparator<ItemData>{
    @Override
    public int compare(ItemData o1, ItemData o2) {
        return o1.name.compareTo(o2.name);
    }
}
