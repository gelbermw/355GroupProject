package cmsc355.group.moms_warehouse;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class itemDetails extends AppCompatActivity {
    FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);

        TextView text = findViewById(R.id.TVname);
        text.append(getIntent().getStringExtra("name"));
        text.setClickable(false);
        text = findViewById(R.id.TVDescription);
        text.append(getIntent().getStringExtra("description"));
        text.setClickable(false);
        text = findViewById(R.id.TVLocation);
        text.append(getIntent().getStringExtra("location"));
        text.setClickable(false);
        text = findViewById(R.id.TVExpDate);
        text.append(getIntent().getStringExtra("expire"));
        text.setClickable(false);
        text = findViewById(R.id.TVQuantity);
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
    }

    public void onButtonClick(View v) {
        user = FirebaseAuth.getInstance().getCurrentUser();
        if (v.getId() == R.id.Bback) {
            Intent i = new Intent(itemDetails.this, warehouse.class);
            startActivity(i);
        }
        else if(v.getId() == R.id.BDelete){
            String text = getIntent().getStringExtra("name");
            FirebaseDatabase.getInstance().getReference().child("users/").child(user.getUid()).child("items").child(text).setValue(null);
            Toast.makeText(itemDetails.this, "Item Deleted", Toast.LENGTH_LONG).show();
            Intent i = new Intent(itemDetails.this, warehouse.class);
            startActivity(i);
        }
    }

    public void onEditClick(View v) {
        Intent i = new Intent(itemDetails.this, editWarehouse.class);
        startActivity(i);
    }

}
