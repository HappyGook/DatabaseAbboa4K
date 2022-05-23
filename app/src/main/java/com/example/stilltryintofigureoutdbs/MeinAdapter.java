package com.example.stilltryintofigureoutdbs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MeinAdapter extends ArrayAdapter<String> {

    private List<ObjectItem> data;
    private Context context;

    public MeinAdapter(Context context, List<ObjectItem> data) {
        super(context, R.layout.item);
        this.data = data;
        this.context = context;
    }

    @Override
    public int getCount() {
        // возвращаем количество элементов списка
        return data.size();
    }

    @Override
    public String getItem(int position) {
        // получение одного элемента по индексу
        return data.get(position).getHeader();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    // заполнение элементов списка
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // задаем вид элемента списка, который мы создали высше
        View view = inflater.inflate(R.layout.item, parent, false);

        // проставляем данные для элементов
        TextView header = (TextView)view.findViewById(R.id.Iheader);
        TextView time = (TextView)view.findViewById(R.id.Itime);
        TextView desc = (TextView)view.findViewById(R.id.Idesc);


        // получаем элемент со списка
        ObjectItem objectItem = data.get(position);

        // устанавливаем значения компонентам одного эелемента списка
        header.setText(objectItem.getHeader().toString());
        time.setText(objectItem.getTime().toString());
        desc.setText(objectItem.getDesc().toString());

        return view;
    }
}