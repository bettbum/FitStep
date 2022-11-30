package com.example.fitstep.ui.activity;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.fitstep.R;
import com.example.fitstep.models.Activity;
import com.example.fitstep.models.GlobalData;
import com.google.firebase.database.DatabaseReference;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.UUID;


public class ModifyActivityFragment extends Fragment {
    private View view;
    private Activity act;
    AlertDialog.Builder alertDialog;
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
                if(edName.getText() != null && edHours.getText() != null){
                    String name = edName.getText().toString();
                    Double hours = Double.valueOf(edHours.getText().toString());
                    String date = DateFormat.getDateInstance().format(Calendar.getInstance().getTime());
                    if(mode.equals("add")) {
                        act = new Activity(name, hours, date);
                    }else{
                        act.setName(name);
                        act.setNbOfHoursDone(hours);
                    }
                    GlobalData.userDatabase.child(GlobalData.loggedUser.getEmail()).child("listOfActivities").child(act.getId()).setValue(act);
                }
                getFragmentManager().popBackStack();
            }
        });
        Button btnDelete = view.findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.create().show();
            }
        });
        if(mode.equals("add")){
            btnDelete.setEnabled(false);
            edName.setText("");
            edHours.setText("");
        }else{
            btnDelete.setEnabled(true);
            act = (Activity)getArguments().getSerializable("activity");
            edName.setText(act.getName());
            edHours.setText(String.valueOf(act.getNbOfHoursDone()));
        }

        alertDialog = new AlertDialog.Builder(view.getContext());
        alertDialog.setTitle("Deletion of countries");
        alertDialog.setMessage("Do you want to delete (Y/N)");
        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                GlobalData.userDatabase.child(GlobalData.loggedUser.getEmail()).child("listOfActivities").child(act.getId()).setValue(null);
            }
        });
        alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        return view;
    }
}