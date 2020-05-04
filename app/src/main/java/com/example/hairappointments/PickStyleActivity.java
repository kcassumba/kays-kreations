package com.example.hairappointments;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;


public class PickStyleActivity extends AppCompatActivity {

    private RadioButton boxbraids;
    private RadioButton passiontwists;
    private RadioButton extensions;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_style);

        boxbraids = findViewById(R.id.boxbraids);
        passiontwists = findViewById(R.id.passiontwists);
        extensions = findViewById(R.id.extensions);

    }

    @Override
    public void onBackPressed(){

        String style;
        int price;
        if(boxbraids.isChecked()){
            style = "Box Braids";
            price = 90;
        }else if(passiontwists.isChecked()){
            style = "Passion Twists";
            price = 85;
        }else{
            style = "Extensions";
            price  = 65;
        }

        Intent i = new Intent();
        i.putExtra("STYLE", style);
        i.putExtra("PRICE", price);
        setResult(RESULT_OK, i);
        finish();



    }




}
