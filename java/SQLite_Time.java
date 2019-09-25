package com.example.habits_01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.Calendar;

import static android.R.layout.simple_list_item_1;

public class SQLite_Time extends AppCompatActivity implements View.OnClickListener  {

    DBHelper dbHelper;
    Spinner sp1;
    EditText habit_data;

    private DatePicker dpInputDate;

    private RadioButton mCalendarRadionButton;
    private RadioButton mSpinnerRadioButton;

    private int year;
    private int month;
    private int day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite__time);

        dbHelper = new DBHelper(this);
        sp1 = (Spinner) findViewById(R.id.spinner);

        habit_data = (EditText) findViewById(R.id.editText5);

        Button button6 = (Button) findViewById(R.id.button6);
        button6.setOnClickListener(this);


        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.optGroup);
        mCalendarRadionButton = (RadioButton) findViewById(R.id.optCalendar);
        mSpinnerRadioButton = (RadioButton) findViewById(R.id.optSpinners);


        dpInputDate = (DatePicker) findViewById(R.id.dpInputData);
        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);
        dpInputDate.setCalendarViewShown(true);
        dpInputDate.setSpinnersShown(false);

        //set current date into textview
        habit_data.setText(new StringBuilder()
                //Month is 0 based, just add 1
                .append(month + 1).append("-").append(day).append("-")
                .append(year).append(" "));

        //set current date into datepicker
        dpInputDate.init(year, month, day, null);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (mCalendarRadionButton.isChecked()) {
                    dpInputDate.setCalendarViewShown(true);
                    dpInputDate.setSpinnersShown(false);
                } else if (mSpinnerRadioButton.isChecked()) {
                    dpInputDate.setCalendarViewShown(false);
                    dpInputDate.setSpinnersShown(true);
                } else {
                    dpInputDate.setCalendarViewShown(true);
                    dpInputDate.setSpinnersShown(true);
                }
            }
        });

        SQLiteDatabase database = dbHelper.getReadableDatabase();

        Cursor cursor = database.query(DBHelper.TABLE_HABITS, null, null, null, null, null, null);
        ArrayList<String> str1 = new ArrayList<String>();
        ArrayAdapter<String> adapter;
        if (cursor.moveToFirst()) {
            int idIndex = cursor.getColumnIndex(DBHelper.KEY_ID_T_HABITS);
            int nameIndex = cursor.getColumnIndex(DBHelper.KEY_NAME);

            do {
                str1.add(cursor.getString(idIndex));
            } while (cursor.moveToNext());
        } else
            str1.add("Element is NULL");

        cursor.close();
        adapter = new ArrayAdapter<String>(this, simple_list_item_1, str1);
        //all_habits.setAdapter(adapter);
        sp1.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {

        // по id определеяем кнопку, вызвавшую этот обработчик
        switch (v.getId()) {
            case R.id.button6: {

                habit_data.setText(dpInputDate.getDayOfMonth() + "/" +
                        (dpInputDate.getMonth() + 1) + "/" + dpInputDate.getYear());

                SQLiteDatabase database = dbHelper.getWritableDatabase();
                ContentValues contentValues = new ContentValues();

                contentValues.put(DBHelper.KEY_ID_F_HABITS, sp1.getSelectedItem().toString());
                contentValues.put(DBHelper.KEY_DATE, habit_data.getText().toString());

                database.insert(DBHelper.TABLE_HABIT_TIME, null, contentValues);
                break;
            }
        }

    }



}


