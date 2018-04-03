package cmsc355.group.moms_warehouse;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onStart(){
        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();

        if(currentUser != null){
            Intent i = new Intent(LoginActivity.this, warehouse.class);
        }
    }

    public void onButtonClick(View v){
        if(v.getId() == R.id.Blogin){

            TextView text = findViewById(R.id.TFusername);
            String email = text.getText().toString();

            text = findViewById(R.id.TFpassword);
            String password = text.getText().toString();

            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Log.d("Login", "signInWithEmail:Success");
                                FirebaseUser user = mAuth.getCurrentUser();

                                Intent i = new Intent(LoginActivity.this, warehouse.class);
                                startActivity(i);
                            }
                            else{
                                Log.w("Login", "signInWithEmail:failure", task.getException());
                                Toast.makeText(LoginActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
        if(v.getId() == R.id.Bsignup){
            Intent i = new Intent(LoginActivity.this, createAccount.class);
            startActivity(i);
        }
    }
}
