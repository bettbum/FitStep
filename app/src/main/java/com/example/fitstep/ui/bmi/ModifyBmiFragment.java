package com.example.fitstep.ui.bmi;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.fitstep.R;
import com.example.fitstep.models.BodyMesurement;
import com.example.fitstep.models.GlobalData;

import java.text.DateFormat;
import java.util.Calendar;


public class ModifyBmiFragment extends Fragment {
    private View view;
    private BodyMesurement bodyMesurement;
    AlertDialog.Builder alert;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_modify_bmi, container, false);

        String mode = getArguments().getString("mode");
        EditText edHeight = view.findViewById(R.id.edHeight);
        EditText edWeight = view.findViewById(R.id.edWeight);
        Button btnSave = view.findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edHeight.getText()!=null && edWeight.getText()!=null){
                    double height = Double.valueOf(edHeight.getText().toString());
                    double weight = Double.valueOf(edWeight.getText().toString());
                    String date = DateFormat.getDateInstance().format(Calendar.getInstance().getTime());
                    if(mode.equals("add")){
                        bodyMesurement = new BodyMesurement(height,weight,date);
                    }else{
                        bodyMesurement.setHeight(height);
                        bodyMesurement.setWeight(weight);
                    }
                    GlobalData.userDatabase.child(GlobalData.loggedUser.getEmail()).child("listOfBmi").child(bodyMesurement.getId()).setValue(bodyMesurement);
                }
                getFragmentManager().popBackStack();
            }
        });
        Button btnDelete = view.findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alert.create().show();
            }
        });
        if (mode.equals("add")){
            btnDelete.setEnabled(false);
            edHeight.setText(null);
            edWeight.setText(null);
        }else{
            btnDelete.setEnabled(false);
            bodyMesurement = (BodyMesurement) getArguments().getSerializable("bmi");
            edHeight.setText(String.valueOf(bodyMesurement.getHeight()));
            edWeight.setText(String.valueOf(bodyMesurement.getWeight()));
        }
        alert = new AlertDialog.Builder(view.getContext());
        alert.setTitle("Delete information");
        alert.setMessage("Do you want to delete?");
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                GlobalData.userDatabase.child(GlobalData.loggedUser.getEmail()).child("listOfBmi").child(bodyMesurement.getId()).setValue(null);

            }
        });
        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        return view;
    }
}