package com.example.stilltryintofigureoutdbs;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatDialogFragment;

public class ItemDialogFragment extends AppCompatDialogFragment {
    OpenHelper openHelper;
    String header,desc,time;
    TextView headerText,descText,timeText;
    SQLiteDatabase db;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;


  /*  public static ItemDialogFragment newInstance(String param1, String param2) {
        ItemDialogFragment fragment = new ItemDialogFragment("1","1","1");
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
*/
    public ItemDialogFragment(String header, String desc, String time) {
        this.header=header;
        this.desc=desc;
        this.time=time;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        headerText=inflater.inflate(R.layout.fragment_item_dialog,container,false).findViewById(R.id.ItemHeader);
        descText=inflater.inflate(R.layout.fragment_item_dialog,container,false).findViewById(R.id.ItemDesc);
        timeText=inflater.inflate(R.layout.fragment_item_dialog,container,false).findViewById(R.id.ItemTime);
        openHelper=new OpenHelper(getContext());
        db=openHelper.getReadableDatabase();



        return inflater.inflate(R.layout.fragment_item_dialog, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        Window window=getDialog().getWindow();
        window.setLayout(1000,750);
        window.setGravity(Gravity.CENTER);

    }
}