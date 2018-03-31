package cmsc355.group.moms_warehouse;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    private static int SPLASH_TIME = 4000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent homeIntent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(homeIntent);
                finish();
            }
        },SPLASH_TIME);


        /*database stuff in progress
        //FirebaseAuth.getInstance().createUserWithEmailAndPassword();
        DatabaseReference db = FirebaseDatabase.getInstance().getReference("warehouseData");
        db.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot snap, String s) {
                warehouseData data = snap.getValue(warehouseData.class);
                if(data.itemName.equals("Fuck")) {
                    System.out.printf("name: %s, desc: %s\n", data.itemName, data.description);
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
//
//        warehouseData dummyData = new warehouseData();
//        dummyData.itemName = "test1";
//        dummyData.description = "test2";
//
//        db.push().setValue(dummyData);
//        dummyData.itemName = "Mehama";
//        dummyData.description = "SFDsfsdf";
//
//        db.push().setValue(dummyData);

          */
    }
}
