package com.example.hairappointments;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private EditText namebox;
    private EditText datebox;
    private EditText stylebox;
    private EditText pricebox;
    private Button bookbutton;
    private Button viewallbutton;
    private TextView show;


    private int price;
    private String style;
    private boolean add;
    private Calendar currentDate;
    private Calendar bookingDate;
   // private SimpleDateFormat dateFormat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        namebox = findViewById(R.id.namebox);
        datebox = findViewById(R.id.datebox);
        stylebox = findViewById(R.id.stylebox);
        pricebox = findViewById(R.id.pricebox);

        bookbutton =findViewById(R.id.bookbutton);
        viewallbutton = findViewById(R.id.viewallbutton);
        show = findViewById(R.id.show);




        Intent i = getIntent();
        add = i.getBooleanExtra("ADD", true);
        if(add){
            //add an appointment
            bookbutton.setText("BOOK");
        }else{
            //edit an appointment
           bookbutton.setText("EDIT BOOKING");
           namebox.setText(i.getStringExtra("NAME"));
           datebox.setText(i.getStringExtra("DATE"));
           stylebox.setText(i.getStringExtra("STYLE"));
           pricebox.setText(i.getStringExtra("PRICE"));

        }


    }

    public void stylePressed(View view ){
        Intent i = new Intent (this, PickStyleActivity.class);
        i.putExtra("STYLE", style);
        i.putExtra("PRICE", price);
        startActivityForResult(i, 1);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //getIntExtra - you have to specify what you are getting  int, double, string
        style = data.getStringExtra("STYLE");
        price =data.getIntExtra("PRICE",1);

        stylebox.setText(style);
        pricebox.setText(price+"");


    }
    public void bookPressed(View view){

        String name = namebox.getText().toString();
        String date = datebox.getText().toString();
        String style2 = stylebox.getText().toString();
        String price = pricebox.getText().toString();
        DatabaseManager dbm = new DatabaseManager(this);


        currentDate = Calendar.getInstance();

        /*just wanted to see what the current day was
        dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        String date_display = dateFormat.format(currentDate.getTime());
        */



        bookingDate = Calendar.getInstance();
        bookingDate.clear();


        String[] separate = date.split("/");
        int m = Integer.parseInt(separate[0]);
        int d = Integer.parseInt(separate[1]);
        int y = Integer.parseInt(separate[2]);
        bookingDate.set(y,m,d);

        /* The following formats won't be accepted YYYY/DD/MM , YYYY/MM/DD, MM/YYYY/DD
        * However, error stills exists because it accepts dates prior to currentDate
        * */

       if(currentDate.after(bookingDate)){

           Toast.makeText(getApplicationContext(), "Invalid Date, Try Again!", Toast.LENGTH_LONG).show();
            
       }else {

           if (add) {
               Toast.makeText(getApplicationContext(), "Thank You!", Toast.LENGTH_LONG).show();
               dbm.insert(name, date, style2, price);
           } else {
               dbm.updateByName(name, date, style2, price);
           }
       }



    }

    public void viewAllPressed(View view){
        Intent i = new Intent(this,ViewAllActivity.class);
        startActivity(i);


    }






}
