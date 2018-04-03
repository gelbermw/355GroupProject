package cmsc355.group.moms_warehouse.database.data;


import android.support.annotation.NonNull;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Item extends DatabaseData implements Comparable<Item>{

    private String name;
    private String description;
    private String location;
    private String expire;
    private String quantity;

    public Item(){

    }

    public Item(String name, String description, String location, String expire, String quantity){
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
    public void setName(String name){
        this.name = name;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description){
        this.description = description;
    }

    public String getLocation() {
        return location;
    }
    public void setLocation(String location){
        this.location = location;
    }

    public String getExpire() {
        return expire;
    }
    public void setExpire(String expire){
        this.expire = expire;
    }

    public String getQuantity() {
        return quantity;
    }
    public void setQuantity(String quantity){
        this.quantity = quantity;
    }

    public String toString(){
        return name;
    }

    @Override
    public int compareTo(@NonNull Item o) {
        return (this.name.compareTo(o.name));
    }
}
