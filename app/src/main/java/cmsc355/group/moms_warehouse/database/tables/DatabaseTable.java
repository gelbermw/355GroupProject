package cmsc355.group.moms_warehouse.database.tables;

import android.provider.ContactsContract;
import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

import cmsc355.group.moms_warehouse.database.data.DatabaseData;

public class DatabaseTable<T extends DatabaseData> implements ChildEventListener {
    protected DatabaseReference db;

    private Class<T> t;

    protected Map<String, T> data;
    private String table;

    public DatabaseTable(String table, Class<T> t){
        this.t = t;
        this.table = table;
        data = new HashMap<>();
        db = FirebaseDatabase.getInstance().getReference().child(table);
        db.addChildEventListener(this);
    }

    public boolean containsKey(String key){
        return data.containsKey(key.replace(".","-"));
    }

    protected DatabaseReference newEntry(T entry){
        return db.push();
    }

    public void loadData(){
        db = FirebaseDatabase.getInstance().getReference().child(table);
        db.addChildEventListener(this);
    }

    public void stopLoading(){
        db.removeEventListener(this);
    }

    public void dispose(){
        stopLoading();
        data.clear();
    }

    public Map<String, T> getData(){
        return data;
    }

    public void addEntry(T entry){
        newEntry(entry).setValue(entry);
    }

    public void updateEntry(T entry){
        db.child(entry.key).setValue(entry);
    }

    @Override
    public void onChildAdded(DataSnapshot dataSnapshot, String s) {

    }

    @Override
    public void onChildChanged(DataSnapshot dataSnapshot, String s) {

    }

    @Override
    public void onChildRemoved(DataSnapshot dataSnapshot) {

    }

    @Override
    public void onChildMoved(DataSnapshot dataSnapshot, String s) {

    }

    @Override
    public void onCancelled(DatabaseError databaseError) {
        Log.w("DatabaseTable", databaseError.toException());
    }
}
