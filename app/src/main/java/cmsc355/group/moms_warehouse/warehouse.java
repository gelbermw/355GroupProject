package cmsc355.group.moms_warehouse;


import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Collections;

import cmsc355.group.moms_warehouse.database.data.Item;
import cmsc355.group.moms_warehouse.database.data.Sorts.SortByNameAscending;

public class warehouse extends AppCompatActivity {


    FirebaseUser user;
    ArrayList<Item> list = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warehouse);

        Toolbar toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);

        user = FirebaseAuth.getInstance().getCurrentUser();

        TextView textView = findViewById(R.id.TVuname);

        if(user != null){
            textView.append(user.getEmail());
        }
        else{
            textView.append("Error");
        }

            ListView listView = findViewById(R.id.itemList);
            Collections.sort(list, new SortByNameAscending());
            final ArrayAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);

            listView.setAdapter(adapter);

            DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("users/").child(user.getUid()).child("items");
            ref.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                    Item item = dataSnapshot.getValue(Item.class);

                    list.add(item);
                    Collections.sort(list, new SortByNameAscending());
                    adapter.notifyDataSetChanged();
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {
                    if (list.remove(dataSnapshot.getValue(Item.class)))
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
                    Item item = (Item) parent.getItemAtPosition(i);
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

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            Toast.makeText(this, "Searching by: "+ query, Toast.LENGTH_SHORT).show();

        } else if (Intent.ACTION_VIEW.equals(intent.getAction())) {
            String uri = intent.getDataString();
            Toast.makeText(this, "Suggestion: "+ uri, Toast.LENGTH_SHORT).show();
        }
    }

    public void onButtonClick(View v) {
        if (v.getId() == R.id.BaddNewItem) {
            Intent i = new Intent(warehouse.this, addWarehouse.class);
            startActivity(i);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.navigation, menu);

        SearchManager searchManager = (SearchManager)getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView)menu.findItem(R.id.action_search).getActionView();

        if (searchManager != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        }
        searchView.setIconifiedByDefault(false);

        return true;
    }
}
