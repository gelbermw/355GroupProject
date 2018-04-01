package cmsc355.group.moms_warehouse.database.data;


import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class ItemData extends DatabaseData {

    public String name;
    public String description;
    public String location;
    public String expire;
    public String quantity;

    public ItemData(){

    }

    public ItemData(String name, String description, String location, String expire, String quantity){
        this.name = name;
        this.description = description;
        this.location = location;
        this.expire = expire;
        this.quantity = quantity;
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
}
