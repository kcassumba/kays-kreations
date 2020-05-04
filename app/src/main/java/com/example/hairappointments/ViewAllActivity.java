package com.example.hairappointments;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ViewAllActivity extends AppCompatActivity {
    private ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all);
        scrollView = findViewById(R.id.scrollView);
        scrollView.removeAllViews();
        final DatabaseManager dbm = new DatabaseManager(this);
        ArrayList<String> list = dbm.getNames();
        GridLayout grid = new GridLayout(this);
        grid.setColumnCount(1);
        grid.setRowCount(list.size());

        for(String name: list){
            TextView text = new TextView(this);
            text.setText(name);
            text.setTextSize(35);
            text.setClickable(true);
            text.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(getApplicationContext(), ViewAppointmentActivity.class);
                    i.putExtra("NAME",((TextView)view).getText().toString());
                    startActivity(i);
                }
            });
            text.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    String delete_appointment = ((TextView)view).getText().toString();
                    dbm.delete(delete_appointment);
                    return true;

                }
            });
            grid.addView(text);
        }

        scrollView.addView(grid);
    }


}


