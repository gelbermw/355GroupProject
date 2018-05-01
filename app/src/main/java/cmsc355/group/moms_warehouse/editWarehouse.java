package cmsc355.group.moms_warehouse;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import cmsc355.group.moms_warehouse.database.data.Item;
import cmsc355.group.moms_warehouse.database.tables.ItemTable;

public class editWarehouse extends AppCompatActivity {
    FirebaseUser user;
    String origName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_warehouse);

        TextView text = findViewById(R.id.TFEditName);
        text.append(getIntent().getStringExtra("name"));
        text = findViewById(R.id.TFEditDescription);
        text.append(getIntent().getStringExtra("description"));
        text = findViewById(R.id.TFEditLoc);
        text.append(getIntent().getStringExtra("location"));
        text = findViewById(R.id.TFEditDate);
        text.append(getIntent().getStringExtra("expire"));
        text = findViewById(R.id.TFEditQuantity);
        text.append(getIntent().getStringExtra("quantity"));
        int quant = Integer.parseInt(getIntent().getStringExtra("quantity"));
        text.setClickable(false);
        text = findViewById(R.id.TVRestock);
        if(quant == 0) {
            text.append("Restock! You have nothing left!");
            text.setClickable(false);
        }
        else if(quant < 3){
            text.append("You should probably buy some more soon.");
            text.setClickable(false);
        }
        else{
            text.append("You're OK on stocks.");
            text.setClickable(false);
        }

        text = findViewById(R.id.TFEditName);
        origName = text.getText().toString();
    }

    public void onButtonClick(View v){


        if (v.getId() == R.id.Bback) {
            Intent i = new Intent(editWarehouse.this, warehouse.class);
            startActivity(i);
        }
        if(v.getId() == R.id.BDelete){
            user = FirebaseAuth.getInstance().getCurrentUser();
            TextView text = findViewById(R.id.TFEditName);
            String newName = text.getText().toString();
            //String text = getIntent().getStringExtra("name");
            FirebaseDatabase.getInstance().getReference().child("users/").child(user.getUid()).child("items").child(newName).setValue(null);
            Toast.makeText(editWarehouse.this, "Item Deleted", Toast.LENGTH_LONG).show();
            Intent i = new Intent(editWarehouse.this, warehouse.class);
            startActivity(i);
        }
        if(v.getId() == R.id.Bedit) {

            TextView text = findViewById(R.id.TFEditName);
            String newName = text.getText().toString();
            text = findViewById(R.id.TFEditDescription);
            String newDescription = text.getText().toString();
            text = findViewById(R.id.TFEditLoc);
            String newLocation = text.getText().toString();
            text = findViewById(R.id.TFEditDate);
            String newExpire = text.getText().toString();
            text = findViewById(R.id.TFEditQuantity);
            String newQuantity = text.getText().toString();

            user = FirebaseAuth.getInstance().getCurrentUser();
            if(newName.equals(origName)) {
                FirebaseDatabase.getInstance().getReference().child("users/").child(user.getUid()).child("items").child(origName).child("description").setValue(newDescription);
                FirebaseDatabase.getInstance().getReference().child("users/").child(user.getUid()).child("items").child(origName).child("location").setValue(newLocation);
                FirebaseDatabase.getInstance().getReference().child("users/").child(user.getUid()).child("items").child(origName).child("expire").setValue(newExpire);
                FirebaseDatabase.getInstance().getReference().child("users/").child(user.getUid()).child("items").child(origName).child("quantity").setValue(newQuantity);
            }
            else{
                FirebaseDatabase.getInstance().getReference().child("users/").child(user.getUid()).child("items").child(origName).setValue(null);
                FirebaseDatabase.getInstance().getReference().child("users/").child(user.getUid()).child("items").child(newName).child("name").setValue(newName);
                FirebaseDatabase.getInstance().getReference().child("users/").child(user.getUid()).child("items").child(newName).child("description").setValue(newDescription);
                FirebaseDatabase.getInstance().getReference().child("users/").child(user.getUid()).child("items").child(newName).child("location").setValue(newLocation);
                FirebaseDatabase.getInstance().getReference().child("users/").child(user.getUid()).child("items").child(newName).child("expire").setValue(newExpire);
                FirebaseDatabase.getInstance().getReference().child("users/").child(user.getUid()).child("items").child(newName).child("quantity").setValue(newQuantity);
            }
            Toast.makeText(editWarehouse.this, "Item Edited", Toast.LENGTH_LONG).show();

            Intent myIntent = new Intent(editWarehouse.this, warehouse.class);
            editWarehouse.this.startActivity(myIntent);

        }
    }
}
