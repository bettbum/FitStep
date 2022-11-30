package com.example.fitstep.ui.bmi;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.fitstep.R;
import com.example.fitstep.models.Activity;
import com.example.fitstep.models.BodyMesurement;

import java.util.ArrayList;


public class bmiFragment extends Fragment {
    ListView lvActivities;
    ArrayList<BodyMesurement> listOfBMI;
    ArrayAdapter<BodyMesurement> adapter;
    Button btnAdd, btnShowGraphics;
    private  View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_bmi, container, false);

        btnAdd = view.findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btnShowGraphics = view.findViewById(R.id.btnShowGraph);
        btnShowGraphics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        return view;
    }
}