package cmsc355.group.moms_warehouse;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class createAccount extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        mAuth = FirebaseAuth.getInstance();

        Button signup_button = findViewById(R.id.Bsignup);
        signup_button.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view){

                EditText text = findViewById(R.id.TFname);
                String name = text.getText().toString();

                text = findViewById(R.id.TFemail);
                String email = text.getText().toString();

                text = findViewById(R.id.TFuname);
                String user_name = text.getText().toString();

                text = findViewById(R.id.TFpass1);
                String password_one = text.getText().toString();

                text = findViewById(R.id.TFpass2);
                String password_two = text.getText().toString();

                /*if(!password_one.equals(password_two)){
                    System.out.println("You goofed!");
                    Intent i = new Intent(createAccount.this, LoginActivity.class);
                    startActivity(i);
                }
                else{
                    create_new_user(email, password_one);
                }*/
            }
        });
    }

    public void create_new_user(String email, String password){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Log.d("Error", "createUserWithEmail:success");
                        }
                        else{
                            Log.w("Error", "createUserWithEmail:failure", task.getException());

                        }
                    }
                });
    }

    public void onButtonClick(View v) {
        if (v.getId() == R.id.Bsubmit) {
            Intent i = new Intent(createAccount.this, LoginActivity.class);
            startActivity(i);
        }
    }
}
