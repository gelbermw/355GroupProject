package cmsc355.group.moms_warehouse;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import cmsc355.group.moms_warehouse.database.data.Item;
import cmsc355.group.moms_warehouse.database.tables.ItemTable;

public class addWarehouse extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_warehouse);

    }

    public void onButtonClick(View view){
        Intent myIntent = new Intent(addWarehouse.this, warehouse.class);
        addWarehouse.this.startActivity(myIntent);

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

        Item item = new Item(itemName, itemDescription, itemLocation, itemExpire, itemQuantity);

        ItemTable itemTable = new ItemTable();
        itemTable.addEntry(item);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        /*DatabaseReference database = FirebaseDatabase.getInstance().getReference().child(user.getUid()).child("items");
        database.child(itemName).setValue(itemName);*/
    }
}
