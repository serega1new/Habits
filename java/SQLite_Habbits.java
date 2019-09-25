package com.example.habits_01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SQLite_Habbits extends AppCompatActivity implements View.OnClickListener{

    DBHelper dbHelper;
    EditText habit_name, habit_description, habit_start_data, habit_duration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite__habbits);

        dbHelper = new DBHelper(this);

        Button button3 = (Button)findViewById(R.id.button3);
        button3.setOnClickListener(this);

        habit_name = (EditText)findViewById(R.id.editText);
        habit_description = (EditText)findViewById(R.id.editText2);
        habit_start_data = (EditText)findViewById(R.id.editText4);
        habit_duration = (EditText)findViewById(R.id.editText3);
    }

    @Override
    public void onClick(View v) {



        // по id определеяем кнопку, вызвавшую этот обработчик
        switch (v.getId()) {
            case R.id.button3: {

                SQLiteDatabase database = dbHelper.getWritableDatabase();
                ContentValues contentValues = new ContentValues();

                contentValues.put(DBHelper.KEY_NAME, habit_name.getText().toString());
                contentValues.put(DBHelper.KEY_DESCRIPTION, habit_description.getText().toString());
                contentValues.put(DBHelper.KEY_START_DATE, habit_start_data.getText().toString());
                contentValues.put(DBHelper.KEY_DURATION, habit_duration.getText().toString());

                database.insert(DBHelper.TABLE_HABITS, null, contentValues);
                break;
            }
        }

    }

}
