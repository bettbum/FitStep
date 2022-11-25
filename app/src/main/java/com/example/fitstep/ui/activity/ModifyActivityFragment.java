package com.example.fitstep.ui.activity;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.fitstep.R;


public class ModifyActivityFragment extends Fragment {
    private View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_modify_activity, container, false);
        TextView tvTitle = view.findViewById(R.id.tvTitle);
        String mode = getArguments().getString("mode");
        EditText edName = view.findViewById(R.id.edActName);
        EditText edHours = view.findViewById(R.id.edHours);
        Button btnSave = view.findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().popBackStack();
            }
        });
        Button btnDelete = view.findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        if(mode.equals("add")){
            btnDelete.setEnabled(false);
        }else{
            btnDelete.setEnabled(true);
        }
        return view;
    }
}