package cmsc355.group.moms_warehouse;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void onButtonClick(View v){
        if(v.getId() == R.id.Blogin){
            Intent i = new Intent(LoginActivity.this, warehouse.class);
            startActivity(i);
        }
        if(v.getId() == R.id.Bsignup){
            Intent i = new Intent(LoginActivity.this, createAccount.class);
            startActivity(i);
        }
    }
}
