package com.example.habits_01;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;

import static android.R.layout.simple_list_item_1;

public class SQLLite_Manager extends AppCompatActivity implements View.OnClickListener{

    DBHelper dbHelper;

    ScrollView scr1;

    Button button10;
    TextView name_habbit;
    ListView all_habits, habit_info, habit_date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqllite__manager);

        dbHelper = new DBHelper(this);

        all_habits = (ListView)findViewById(R.id.lv1);
        habit_info = (ListView)findViewById(R.id.lv2);
        habit_date = (ListView)findViewById(R.id.lv3);


        scr1 = (ScrollView)findViewById(R.id.scrView1);
        button10 = (Button)findViewById(R.id.button10);
        button10.setOnClickListener(this);

        name_habbit = (TextView)findViewById(R.id.textView4);

       /* habit_date.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                scr1.setScrollContainer(false);
            }
         });*/


        all_habits.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String val =(String) parent.getItemAtPosition(position);
                name_habbit.setText(val);

                SQLiteDatabase database = dbHelper.getReadableDatabase();

                String query = "SELECT * FROM habits WHERE _id = "+val;

                Cursor  cursor = database.rawQuery(query,null);

                ArrayList<String> str1 = new ArrayList<String>();
                ArrayAdapter<String> adapter1;

                if (cursor != null) {
                    cursor.moveToFirst();

                    int idIndex = cursor.getColumnIndex(DBHelper.KEY_ID_T_HABITS);
                    int nameIndex = cursor.getColumnIndex(DBHelper.KEY_NAME);
                    int deskIndex = cursor.getColumnIndex(DBHelper.KEY_DESCRIPTION);
                    int startdateIndex = cursor.getColumnIndex(DBHelper.KEY_START_DATE);
                    int duratIndex = cursor.getColumnIndex(DBHelper.KEY_DURATION);
                    do {
                        str1.add("ID: " + cursor.getInt(idIndex));
                        str1.add("name: " + cursor.getString(nameIndex));
                        str1.add("deskription: " + cursor.getInt(deskIndex));
                        str1.add("start date: " + cursor.getInt(startdateIndex));
                        str1.add("duration = " + cursor.getInt( duratIndex));


                    } while (cursor.moveToNext());

                }
                cursor.close();
                adapter1 = new ArrayAdapter<String>(SQLLite_Manager.this, simple_list_item_1, str1);
                habit_info.setAdapter(adapter1);

                // Отображение временных данных по нввыку

                String query1 = "SELECT * FROM habittime WHERE id_f_habits = "+val;

                Cursor  cursor1 = database.rawQuery(query1,null);

                ArrayList<String> str2 = new ArrayList<String>();
                ArrayAdapter<String> adapter2;

                if (cursor1 != null) {
                    cursor1.moveToFirst();

                    int idIndex = cursor1.getColumnIndex(DBHelper.KEY_ID_T_HABIT_TIME);
                    int id_f_habbits_Index = cursor1.getColumnIndex(DBHelper.KEY_ID_F_HABITS);
                    int dataIndex = cursor1.getColumnIndex(DBHelper.KEY_DATE);
                    str2.add("Habbit: " + cursor1.getString(id_f_habbits_Index));
                    do {
                        str2.add("id: " + cursor1.getInt(idIndex) + " date: "+cursor1.getString(dataIndex));

                    } while (cursor1.moveToNext());

                }
                cursor1.close();
                adapter2 = new ArrayAdapter<String>(SQLLite_Manager.this, simple_list_item_1, str2);
                habit_date.setAdapter(adapter2);
            }
        });
    }

    @Override
    public void onClick(View v) {

        SQLiteDatabase database = dbHelper.getWritableDatabase();

        // по id определеяем кнопку, вызвавшую этот обработчик
        switch (v.getId()) {
            case R.id.button10: {
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
                all_habits.setAdapter(adapter);
                break;

            }

        }

    }


}
