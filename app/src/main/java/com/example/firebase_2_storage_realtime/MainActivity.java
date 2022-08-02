package com.example.firebase_2_storage_realtime;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    //Declaring xml components
    TextView text_string;
    Button button_read_data;

    //Initializing firebase components
    FirebaseDatabase mfirebasedatabase;
    DatabaseReference mdatabaseref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initializing components
        text_string = findViewById(R.id.string_Gunjan);
        button_read_data = findViewById(R.id.button_read_data);

        mfirebasedatabase = FirebaseDatabase.getInstance();


    }

    @Override
    protected void onStart(){
        super.onStart();

        button_read_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mdatabaseref = mfirebasedatabase.getReference().child("Name");
                ValueEventListener fetch_data = new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Toast.makeText(MainActivity.this,"Listener worked!!",Toast.LENGTH_LONG).show();
                        String data = snapshot.getValue(String.class);
                        text_string.setText(data);
                        Toast.makeText(MainActivity.this,data,Toast.LENGTH_LONG).show();


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                };
                mdatabaseref.addValueEventListener(fetch_data);
                //mdatabaseref = mfirebasedatabase.getReference();
                //mdatabaseref.child("Surname").setValue("Kukreja");
                //mdatabaseref.push().child("Food").setValue("Maggie");
                mdatabaseref = mfirebasedatabase.getReference().push();
                mdatabaseref.child("Car_Name").setValue("Jaguar");
                mdatabaseref.child("engine").setValue("V 8 Type");
                mdatabaseref.child("Wheel_type").setValue("Four Wheel Drive");

            }
        });
    }
}