package cmsc355.group.moms_warehouse;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class addWarehouse extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_warehouse);
    }
    public void onButtonClick(View v) {
        if (v.getId() == R.id.Badditem) {
            Intent i = new Intent(addWarehouse.this, warehouse.class);
            startActivity(i);

            //DATABASE
            //This section reads in the values from the text fields and puts them into the database
            DatabaseReference db = FirebaseDatabase.getInstance().getReference("warehouseData");
            db.addChildEventListener(new ChildEventListener() {

                @Override
                public void onChildAdded(DataSnapshot snap, String s) {
                    warehouseData data = snap.getValue(warehouseData.class);
                    if (data.name.equals("test1")) {
                        System.out.printf("name: %s, desc: %s\n", data.name, data.description);
                    }
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

                }
            });

            TextView text = findViewById(R.id.TFItemName);
            String itemName = text.getText().toString();
            text = findViewById(R.id.TFdescription);
            String itemDescription = text.getText().toString();
            text = findViewById(R.id.TFItemLoc);
            String itemLocation = text.getText().toString();
            text = findViewById(R.id.TFExpDate);
            String itemExpire = text.getText().toString();
            text = findViewById(R.id.TFQuantity);
            String itemQuantity = text.getText().toString();

            //EventsTable eventsTable = new EventsTable();
            //eventsTable.addEntry(new EventData(name, category, description, date, organization, location));

            warehouseData itemData = new warehouseData();
            itemData.name = itemName;
            itemData.description = itemDescription;
            itemData.location = itemLocation;
            itemData.expire = itemExpire;
            itemData.quantity = itemQuantity;
            db.push().setValue(itemData);

//            itemData.itemName = "Mehama";
//            itemData.description = "SFDsfsdf";
//            db.push().setValue(itemData);

        }
    }

}
