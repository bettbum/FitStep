package com.example.fitstep.models.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.fitstep.R;
import com.example.fitstep.models.Activity;

import java.util.ArrayList;

public class ActivityAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Activity> listOfActivities;

    public ActivityAdapter(Context context, ArrayList<Activity> listOfActivities) {
        this.context = context;
        this.listOfActivities = listOfActivities;

    }

    @Override
    public int getCount() {
        return listOfActivities.size();
    }

    @Override
    public Object getItem(int i) {
        return listOfActivities.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View ActItem;
        TextView tvActName, tvDateDone;
        LayoutInflater inflater = LayoutInflater.from(context);
        ActItem = inflater.inflate(R.layout.activity_item,viewGroup,false);
        tvActName = ActItem.findViewById(R.id.tvActName);
        tvDateDone = ActItem.findViewById(R.id.tvDateDone);
        Activity act = (Activity) getItem(i);
        tvActName.setText(act.getName());
        tvDateDone.setText(act.getDateDone());
        return ActItem;
    }
}
