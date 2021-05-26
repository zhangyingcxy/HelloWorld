package com.swufe.helloworld;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class RateAdapter extends ArrayAdapter {

    public RateAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Rate> list) {
        super(context, resource, list);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//        return super.getView(position, convertView, parent);
        View itemView = convertView;
        if(itemView == null) {
            itemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }
        Rate rate = (Rate) getItem(position);
        TextView title = (TextView) itemView.findViewById(R.id.leftitem);
        TextView detail = (TextView) itemView.findViewById(R.id.rightitem);

        title.setText(rate.getCountryName());
        detail.setText(rate.getRate());

        return itemView;


    }

}
