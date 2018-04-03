package cmsc355.group.moms_warehouse;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class createAccount extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        mAuth = FirebaseAuth.getInstance();
    }

    public void onButtonClick(View view){
        EditText text = findViewById(R.id.TFemail);
        String email = text.getText().toString();

        text = findViewById(R.id.TFpass1);
        String password_one = text.getText().toString();

        text = findViewById(R.id.TFpass2);
        String password_two = text.getText().toString();

        if(!password_one.equals(password_two)){
            Context context = getApplicationContext();
            Toast.makeText(context, "Passwords don't match", Toast.LENGTH_LONG).show();
        }
        else{
            create_new_user(email, password_one);

            Intent i = new Intent(createAccount.this, LoginActivity.class);
            startActivity(i);
        }
    }

    public void create_new_user(final String email, String password){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){

                            Log.d("Error", "createUserWithEmail:success");
                            Context context = getApplicationContext();
                            Toast.makeText(context, "Great Success!", Toast.LENGTH_LONG).show();
                        }
                        else{
                            Log.w("Error", "createUserWithEmail:failure", task.getException());
                            Context context = getApplicationContext();
                            Toast.makeText(context, "Great Failure =(", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    public void send_verification_email(){
        FirebaseUser user = mAuth.getCurrentUser();
        user.sendEmailVerification()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d("Verification Email", "Email sent.");
                        }
                    }
                });
    }
}
