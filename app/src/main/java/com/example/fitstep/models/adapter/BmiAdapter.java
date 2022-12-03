package com.example.fitstep.models.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.fitstep.R;
import com.example.fitstep.models.Activity;
import com.example.fitstep.models.BodyMesurement;

import java.util.ArrayList;

public class BmiAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<BodyMesurement> listOfBmi;

    public BmiAdapter(Context context, ArrayList<BodyMesurement> listOfBmi) {
        this.context = context;
        this.listOfBmi = listOfBmi;
    }

    @Override
    public int getCount() {
        return listOfBmi.size();
    }

    @Override
    public Object getItem(int i) {
        return listOfBmi.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View BmiItem;
        TextView tvHeight, tvWeight, tvDateInput;
        LayoutInflater inflater = LayoutInflater.from(context);
        BmiItem = inflater.inflate(R.layout.bmi_item,viewGroup,false);
        tvHeight = BmiItem.findViewById(R.id.tvHeight);
        tvWeight = BmiItem.findViewById(R.id.tvWeight);
        tvDateInput = BmiItem.findViewById(R.id.tvDateInput);
        BodyMesurement bodyMesurement = (BodyMesurement) getItem(i);
        tvHeight.setText(String.valueOf(bodyMesurement.getHeight()));
        tvWeight.setText(String.valueOf(bodyMesurement.getWeight()));
        tvDateInput.setText(String.valueOf(bodyMesurement.getInputDate()));
        return BmiItem;
    }
}
