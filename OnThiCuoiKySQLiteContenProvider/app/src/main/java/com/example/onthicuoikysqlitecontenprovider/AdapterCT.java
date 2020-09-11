package com.example.onthicuoikysqlitecontenprovider;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class AdapterCT extends BaseAdapter {

    private Context context;
    private int layout;
    private List<Employee> list;

    public AdapterCT(Context context, int layout, List<Employee> list) {
        this.context = context;
        this.layout = layout;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(layout,null);

        TextView txt = view.findViewById(R.id.txt);

        Employee e = list.get(i);

        txt.setText(e.getId()+"\t"+e.getName()+"\t"+e.getGioiTinh()+"\t \t"+e.getPhoneNumber());

        return view;
    }
}
