package com.example.hairappointments;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;



public class ViewAppointmentActivity extends AppCompatActivity {
    private EditText namedisplayed;
    private EditText datedisplayed;
    private EditText styledisplayed;
    private EditText pricedisplayed;
    private Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_appoinment);

        namedisplayed = findViewById(R.id.namedisplayed);
        datedisplayed = findViewById(R.id.datedisplayed);
        styledisplayed = findViewById(R.id.styledisplayed);
        pricedisplayed = findViewById(R.id.pricedisplayed);
        button = findViewById(R.id.button);

        DatabaseManager dbm = new DatabaseManager(this);
        Intent i =getIntent();
        String name = i.getStringExtra("NAME");
        String[] entry = dbm.get(name);

        namedisplayed.setText(entry[0]);
        datedisplayed.setText(entry[1]);
        styledisplayed.setText(entry[2]);
        pricedisplayed.setText(entry[3]);
    }


    public void editPressed(View v){
        Intent i = new Intent(this, MainActivity.class);
        i.putExtra("ADD", false);
        i.putExtra("NAME",namedisplayed.getText().toString());
        i.putExtra("DATE", datedisplayed.getText().toString());
        i.putExtra("STYLE", styledisplayed.getText().toString());
        i.putExtra("PRICE", pricedisplayed.getText().toString());
        startActivity(i);

    }


}
