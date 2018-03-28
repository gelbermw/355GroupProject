package cmsc355.group.moms_warehouse;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class createAccount extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
    }

    public void onButtonClick(View v) {
        if (v.getId() == R.id.Bsubmit) {
            Intent i = new Intent(createAccount.this, LoginActivity.class);
            startActivity(i);
        }
    }
}
