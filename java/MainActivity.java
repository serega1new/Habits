 package com.example.habits_01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

 public class MainActivity extends AppCompatActivity implements View.OnClickListener{

     DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button)findViewById(R.id.button);
        button.setOnClickListener(this);

        Button button2 = (Button)findViewById(R.id.button2);
        button2.setOnClickListener(this);

        Button button4 = (Button)findViewById(R.id.button4);
        button4.setOnClickListener(this);
        Button button5 = (Button)findViewById(R.id.button5);
        button5.setOnClickListener(this);

        Button button7 = (Button)findViewById(R.id.button7);
        button7.setOnClickListener(this);

        Button button8 = (Button)findViewById(R.id.button8);
        button8.setOnClickListener(this);

        dbHelper = new DBHelper(this);
    }
     @Override
     public void onClick(View v) {
         Intent i;

         // по id определеяем кнопку, вызвавшую этот обработчик
         switch (v.getId()) {
             case R.id.button: {
                 i = new Intent(this, CalendarT.class);
                 startActivity(i);
                 break;
             }
             case R.id.button2: {
                 i = new Intent(this, SQLite_Habbits.class);
                 startActivity(i);
                 break;
             }
             case R.id.button4: {
                 i = new Intent(this, SQLLite_Manager.class);
                 startActivity(i);
                 break;
             }
             case R.id.button5: {
                 i = new Intent(this, SQLite_Time.class);
                 startActivity(i);
                 break;
             }
             case R.id.button7: {
                 i = new Intent(this, CalendarDataPicker.class);
                 startActivity(i);
                 /*SQLiteDatabase database = dbHelper.getWritableDatabase();
                 database.delete(DBHelper.TABLE_HABIT_TIME, null, null);*/
                 break;
             }
             case R.id.button8: {
                 i = new Intent(this, RapidAPI_Quotes.class);
                 startActivity(i);
                 break;
             }
         }

     }
}
