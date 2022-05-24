package com.example.stilltryintofigureoutdbs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
  FloatingActionButton fab;
    SQLiteDatabase db;
    OpenHelper openHelper;
    FragmentManager fm;
    MeinAdapter adapter;
    ArrayList<ObjectItem> maps;
    ListView list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fab=findViewById(R.id.fab);
        list=findViewById(R.id.list);

        openHelper=new OpenHelper(getBaseContext());
        db=openHelper.getReadableDatabase();


        viewData();

        fm=getSupportFragmentManager();

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               String selected= (String) list.getItemAtPosition(position);
               //Убрать элемент
                openHelper.deleteData(selected);
                maps.clear();
                viewData();
/*                String desc = null,time = null;
                Cursor csr=db.rawQuery("SELECT description FROM events WHERE header ="+selected,null);
                if(csr!=null)
                    if(csr.moveToFirst())
                        desc=csr.getString(0);
                Cursor csr2=db.rawQuery("SELECT time FROM events WHERE header =" +selected,null);
                if(csr2!=null)
                    if(csr2.moveToFirst())
                        time=csr2.getString(0);
                ItemDialogFragment itd=new ItemDialogFragment(selected,desc,time);
                itd.show(fm,"ItemDialog");
*/

            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,EventActivity.class);
                startActivity(intent);
            }
        });
    }

    private void viewData() {
        list.setAdapter(null);
        Cursor cursor=openHelper.viewData();

        maps = new ArrayList<ObjectItem>();

        if(cursor.getCount()==0){
            Toast.makeText(this,"Нет информации",Toast.LENGTH_SHORT).show();
        } else{
            while (cursor.moveToNext()){
                maps.add(new ObjectItem(cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),cursor.getString(4)));
            }

            adapter=new MeinAdapter(this, maps);
            list.setAdapter(adapter);
        }
    }


}