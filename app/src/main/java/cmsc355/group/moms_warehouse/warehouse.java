package cmsc355.group.moms_warehouse;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cmsc355.group.moms_warehouse.database.data.ItemData;
import cmsc355.group.moms_warehouse.database.data.Sorts.SortByNameAscending;

public class warehouse extends AppCompatActivity {


    FirebaseUser user;
    ArrayList<ItemData> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warehouse);

        user = FirebaseAuth.getInstance().getCurrentUser();

        TextView textView = findViewById(R.id.TVusername);

        if(user != null){
            textView.setText(user.getEmail());
        }
        else{
            textView.setText("Error");
        }

        ListView listView = (ListView) findViewById(R.id.itemList);
        Collections.sort(list, new SortByNameAscending());
        final ArrayAdapter adapter = new ArrayAdapter<ItemData>(this, android.R.layout.simple_list_item_1, list);

        listView.setAdapter(adapter);

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("items");
        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Log.d("Results", "Item Added to List!");

                ItemData item = dataSnapshot.getValue(ItemData.class);

                list.add(item);
                Collections.sort(list, new SortByNameAscending());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Log.d("RESULTS", "Item Removed From List!");
                if (list.remove(dataSnapshot.getValue(ItemData.class)))
                {
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                ItemData item = (ItemData) parent.getItemAtPosition(i);
                Intent intent = new Intent(warehouse.this, itemDetails.class);

                intent.putExtra("name", item.getName());
                intent.putExtra("description", item.getDescription());
                intent.putExtra("location", item.getLocation());
                intent.putExtra("expire", item.getExpire());
                intent.putExtra("quantity", item.getQuantity());

                startActivity(intent);
            }
        });
    }

    public void onButtonClick(View v) {
        if (v.getId() == R.id.BaddNewItem) {
            Intent i = new Intent(warehouse.this, addWarehouse.class);
            startActivity(i);
        }
    }

}
