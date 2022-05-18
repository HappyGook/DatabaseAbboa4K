package com.example.stilltryintofigureoutdbs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class EventActivity extends AppCompatActivity {
    OpenHelper openHelper;
    EditText name,desc,time;
    Button button;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        openHelper=new OpenHelper(getBaseContext());
        db=openHelper.getWritableDatabase();




        name=findViewById(R.id.header);
        desc=findViewById(R.id.desc);
        time=findViewById(R.id.time);
        button=findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues values=new ContentValues();
                values.put(OpenHelper.COLUMN_HEADER,name.getText().toString());
                values.put(OpenHelper.COLUMN_DESCRIPTION,desc.getText().toString());
                values.put(OpenHelper.COLUMN_TIME,time.getText().toString());
                db.insert(OpenHelper.TABLE_NAME,null,values);
                db.close();

                Intent intent=new Intent(EventActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}