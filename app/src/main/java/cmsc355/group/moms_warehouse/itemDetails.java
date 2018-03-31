package cmsc355.group.moms_warehouse;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class itemDetails extends AppCompatActivity {

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
        text.setClickable(false);
    }

    public void onButtonClick(View v) {
        if (v.getId() == R.id.Bback) {
            Intent i = new Intent(itemDetails.this, warehouse.class);
            startActivity(i);
        }
    }
}
