package com.example.fitstep.ui.activity;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.fitstep.R;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.sql.Array;


public class ActivityGraphicFragment extends Fragment {
    private View view;
    private GraphView gvActivity;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_activity_graphic, container, false);

        //line graph creation
        gvActivity = view.findViewById(R.id.gvActivity);

        LineGraphSeries<DataPoint> series = new LineGraphSeries();
        DataPoint point = new DataPoint(0,1);
        series.appendData(point,true,30);
        point = new DataPoint(1,3);
        series.appendData(point,true,30);
        point = new DataPoint(2,9);
        series.appendData(point,true,30);
        point = new DataPoint(3,6);
        series.appendData(point,true,30);
        point = new DataPoint(4,31);
        series.appendData(point,true,30);
        point = new DataPoint(5,31);
        series.appendData(point,true,30);
        point = new DataPoint(6,31);
        series.appendData(point,true,30);
        point = new DataPoint(7,31);
        series.appendData(point,true,30);
        point = new DataPoint(8,31);
        series.appendData(point,true,30);
        point = new DataPoint(9,31);
        series.appendData(point,true,30);

        gvActivity.animate();
       // gvActivity.getViewport().setScrollable(true);
       //gvActivity.getViewport().setScalable(true);
        gvActivity.getViewport().setScrollableY(true);
        series.setColor(R.color.buttonColor);
        gvActivity.addSeries(series);

        Button btnReturn = view.findViewById(R.id.btnReturn);
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().popBackStack();
            }
        });

        return view;
    }
}