package cmsc355.group.moms_warehouse.database.data.Sorts;

import java.util.Comparator;

import cmsc355.group.moms_warehouse.database.data.Item;

public class SortByNameAscending implements Comparator<Item>{
    @Override
    public int compare(Item o1, Item o2) {
        return o1.getName().compareTo(o2.getName());
    }
}