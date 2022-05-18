package com.example.stilltryintofigureoutdbs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button button,open;
    EventActivity event;
    SQLiteDatabase db;
    OpenHelper openHelper;
    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button=findViewById(R.id.button);
        open=findViewById(R.id.buttonOpen);
        textView=findViewById(R.id.textView);

        openHelper=new OpenHelper(getBaseContext());
        db=openHelper.getReadableDatabase();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,EventActivity.class);
                startActivity(intent);
            }
        });

        open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursor=db.query(OpenHelper.TABLE_NAME, new String[]{OpenHelper.COLUMN_HEADER,OpenHelper.COLUMN_DESCRIPTION,
                        OpenHelper.COLUMN_TIME}, null,null,null,null,null);

                cursor.moveToFirst();
                while (cursor.moveToNext()){
                    String header=cursor.getString(cursor.getColumnIndex(OpenHelper.COLUMN_HEADER));
                    String description=cursor.getString(cursor.getColumnIndex(OpenHelper.COLUMN_DESCRIPTION));
                    String time=cursor.getString(cursor.getColumnIndex(OpenHelper.COLUMN_TIME));
                    textView.setText(header+" "+description+" "+time);
                }
                cursor.close();
            }
        });
    }

}