package cmsc355.group.moms_warehouse.database.tables;

import com.google.firebase.database.DatabaseReference;

import cmsc355.group.moms_warehouse.database.data.User;

public class UserTable extends DatabaseTable<User>{

    public UserTable(){
        super("Users", User.class);
    }

    @Override
    protected DatabaseReference newEntry(User entry) {
        return super.newEntry(entry);
    }
}
