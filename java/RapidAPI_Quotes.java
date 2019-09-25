package com.example.habits_01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.widget.TextView;

import com.rapidapi.rapidconnect.RapidApiConnect;

// https://github.com/RapidSoftwareSolutions/rapidapi-android-sdk

public class RapidAPI_Quotes extends AppCompatActivity implements View.OnClickListener{

    TextView tIndicator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rapid_api__quotes);

        Button button11 = (Button)findViewById(R.id.button11);
        button11.setOnClickListener(this);

        tIndicator = (TextView)findViewById(R.id.textView2);
    }

    @Override
    public void onClick(View v) {

        // по id определеяем кнопку, вызвавшую этот обработчик
        switch (v.getId()) {
            case R.id.button11: {
                try {
                    RapidApiConnect connect = new RapidApiConnect("andruxnet-random-famous-quotes.p.rapidapi.com", "SIGN-UP-FOR-KEY");

                    tIndicator.setText("connect  completed");


                }
                catch (Exception e)
                {
                    tIndicator.setText("connect  no completed");
                }


                break;
            }

        }
    }

}

