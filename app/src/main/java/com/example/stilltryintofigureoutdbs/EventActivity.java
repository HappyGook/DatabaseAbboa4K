package com.example.stilltryintofigureoutdbs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class EventActivity extends AppCompatActivity {
    OpenHelper openHelper;
    EditText name,desc;
    Button button;
    TimePicker time;
    SQLiteDatabase db;
    CalendarView calendarView;

    String timet;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        openHelper=new OpenHelper(getBaseContext());
        db=openHelper.getWritableDatabase();

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");


        name=findViewById(R.id.header);
        desc=findViewById(R.id.desc);
        time=findViewById(R.id.time);
        button=findViewById(R.id.button);
        calendarView=findViewById(R.id.calendarView);

        Calendar now = Calendar.getInstance();

        time.setIs24HourView(true);

        time.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {

            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                timet=String.valueOf(hourOfDay)+":"+String.valueOf(minute);
            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selectedDate = sdf.format(new Date(calendarView.getDate()));
                ContentValues values=new ContentValues();
                values.put(OpenHelper.COLUMN_HEADER,name.getText().toString());
                values.put(OpenHelper.COLUMN_DESCRIPTION,desc.getText().toString());
                values.put(OpenHelper.COLUMN_TIME,timet);
                values.put(OpenHelper.COLUMN_DATE,selectedDate);
                db.insert(OpenHelper.TABLE_NAME,null,values);

                Intent intent=new Intent(EventActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }


}