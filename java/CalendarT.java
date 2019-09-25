package com.example.habits_01;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.skyhope.eventcalenderlibrary.CalenderEvent;
import com.skyhope.eventcalenderlibrary.model.Event;

import java.util.Calendar;

// https://android-arsenal.com/details/1/7368

public class CalendarT extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_t);

        Calendar calendar = Calendar.getInstance();;
        CalenderEvent calenderEvent = findViewById(R.id.calender_event);
        Event event = new Event(calendar.getTimeInMillis(), "Test", Color.RED);
        calenderEvent.addEvent(event);
        
    }


}
