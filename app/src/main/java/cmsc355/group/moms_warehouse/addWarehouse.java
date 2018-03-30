package cmsc355.group.moms_warehouse;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

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
        }
    }

    //move the database stuff form MainActivity to here
}
