package cmsc355.group.moms_warehouse.database.data;


import android.support.annotation.NonNull;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class ItemData extends DatabaseData implements Comparable<ItemData>{

    public String name;
    public String description;
    public String location;
    public String expire;
    public String quantity;

    public ItemData(){

    }

    public ItemData(String name, String description, String location, String expire, String quantity){
        this.name = format_cases(name);
        this.description = description;
        this.location = location;
        this.expire = expire;
        this.quantity = quantity;
    }

    private String format_cases(String unformed_string){
        return unformed_string.substring(0, 1).toUpperCase() + unformed_string.substring(1).toLowerCase();
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getLocation() {
        return location;
    }

    public String getExpire() {
        return expire;
    }

    public String getQuantity() {
        return quantity;
    }

    public String toString(){
        return name;
    }

    @Override
    public int compareTo(@NonNull ItemData o) {
        return (this.name.compareTo(o.name));
    }
}
