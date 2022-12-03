package com.example.fitstep.ui.bmi;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.fitstep.R;
import com.example.fitstep.models.Activity;
import com.example.fitstep.models.BodyMesurement;
import com.example.fitstep.models.GlobalData;
import com.example.fitstep.models.adapter.BmiAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import java.util.ArrayList;


public class bmiFragment extends Fragment {
    ListView lvBmi;
    ArrayList<BodyMesurement> listOfBMI;
    BmiAdapter adapter;
    Button btnAdd, btnShowGraphics;
    private  View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_bmi, container, false);

        listOfBMI = new ArrayList<BodyMesurement>();
        lvBmi = view.findViewById(R.id.lvBMI);
        //set header for the list view
        LayoutInflater headerInflater = LayoutInflater.from(view.getContext());
        View headerView = headerInflater.inflate(R.layout.bmi_item_header,container,false);
        lvBmi.addHeaderView(headerView,null,false);
        adapter = new BmiAdapter(view.getContext(),listOfBMI);
        lvBmi.setAdapter(adapter);


        lvBmi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                BodyMesurement bodyMesurement = listOfBMI.get(i-1);
                ModifyBmiFragment fragment = new ModifyBmiFragment();
                Bundle bundle = new Bundle();
                bundle.putString("mode","edit");
                bundle.putSerializable("bmi",bodyMesurement);
                fragment.setArguments(bundle);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment_content_main, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        GlobalData.userDatabase.child(GlobalData.loggedUser.getEmail())
                .child("listOfBmi")
                .addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                        BodyMesurement bodyMesurement = snapshot.getValue(BodyMesurement.class);
                        listOfBMI.add(bodyMesurement);
                        GlobalData.loggedUser.setTrackBodyMesurements(listOfBMI);
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                    }

                    @Override
                    public void onChildRemoved(@NonNull DataSnapshot snapshot) {

                    }

                    @Override
                    public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
        btnAdd = view.findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ModifyBmiFragment fragment = new ModifyBmiFragment();
                Bundle bundle = new Bundle();
                bundle.putString("mode","add");
                fragment.setArguments(bundle);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment_content_main,fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        btnShowGraphics = view.findViewById(R.id.btnShowGraph);
        btnShowGraphics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BmiGraphicFragment fragment = new BmiGraphicFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment_content_main,fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        return view;
    }
}