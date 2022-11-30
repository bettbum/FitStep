package com.example.fitstep.ui.activity;

import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.fitstep.R;
import com.example.fitstep.models.Activity;
import com.example.fitstep.models.GlobalData;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import java.util.ArrayList;

public class ActivityFragment extends Fragment {
    ListView lvActivities;
    ArrayList<Activity> listOfActivities;
    ArrayAdapter<Activity> adapter;
    public static ActivityFragment newInstance() {
        return new ActivityFragment();
    }
    private View view;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_activity, container, false);

        listOfActivities = new ArrayList<Activity>();
        lvActivities = view.findViewById(R.id.lvActivities);
        adapter = new ArrayAdapter<Activity>(view.getContext(), android.R.layout.simple_expandable_list_item_1, listOfActivities);
        lvActivities.setAdapter(adapter);

        GlobalData.userDatabase.child(GlobalData.loggedUser.getEmail())
                .child("listOfActivities")
                .addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                        Activity act = snapshot.getValue(Activity.class);
                        listOfActivities.add(act);
                        GlobalData.loggedUser.setListOfActivities(listOfActivities);
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
        Button btnShowGraphical = view.findViewById(R.id.btnShowGraph);
        btnShowGraphical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityGraphicFragment fragment = new ActivityGraphicFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment_content_main,fragment);
                transaction.addToBackStack(null);
                transaction.commit();

            }
        });
        Button btnAdd = view.findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ModifyActivityFragment fragment = new ModifyActivityFragment();
                Bundle bundle = new Bundle();
                bundle.putString("mode","add");
                fragment.setArguments(bundle);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment_content_main,fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });


        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // TODO: Use the ViewModel
    }

}