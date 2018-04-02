package cmsc355.group.moms_warehouse.database.data;

public class User extends DatabaseData {
    private String family_group;

    public User(String family_group){
        this.family_group = family_group;
    }

    public String getFamily_group(){
        return this.family_group;
    }

    public void setFamily_group(String family_group){
        this.family_group = family_group;
    }
}