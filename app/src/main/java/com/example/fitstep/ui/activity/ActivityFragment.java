package com.example.fitstep.ui.activity;

import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.fitstep.R;

public class ActivityFragment extends Fragment {



    public static ActivityFragment newInstance() {
        return new ActivityFragment();
    }
    private View view;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_activity, container, false);
        Button btnShowGraphical = view.findViewById(R.id.btnShowGraph);
        btnShowGraphical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityGraphicFragment fragment = new ActivityGraphicFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment_content_main,fragment);
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