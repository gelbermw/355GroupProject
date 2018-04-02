package cmsc355.group.moms_warehouse;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import cmsc355.group.moms_warehouse.database.data.ItemData;
import cmsc355.group.moms_warehouse.database.tables.ItemTable;

public class addWarehouse extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_warehouse);

        Button warehouseScreen = findViewById(R.id.Badditem);
        warehouseScreen.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view){

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

                ItemTable itemTable = new ItemTable();
                itemTable.addEntry(new ItemData(itemName,itemDescription,itemLocation,itemExpire,itemQuantity));
            }
        });
    }
}
