package com.example.fitstep.ui.setgoal;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.fitstep.R;
import com.example.fitstep.models.GlobalData;
import com.example.fitstep.models.Goal;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class SetGoalFragment extends Fragment {

    EditText edBmi, edCaloryIntake, edCaloryOutput;
    Button btnSave;

    public static SetGoalFragment newInstance() {
        return new SetGoalFragment();
    }
    private View view;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_set_goal, container, false);
        edBmi = view.findViewById(R.id.edBmi);
        edCaloryIntake = view.findViewById(R.id.edCaloryIntake);
        edCaloryOutput = view.findViewById(R.id.edCaloryOutput);
        btnSave = view.findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double bmi = Double.valueOf(edBmi.getText().toString());
                long caloryIntake = Long.valueOf(edCaloryIntake.getText().toString());
                long caloryOutput = Long.valueOf(edCaloryOutput.getText().toString());
                GlobalData.loggedUser.setGoal(new Goal(bmi,caloryIntake,caloryOutput));
                GlobalData.userDatabase.child(GlobalData.loggedUser.getEmail()).child("goal").setValue(GlobalData.loggedUser.getGoal());
            }
        });
        DatabaseReference goalRef = GlobalData.userDatabase.child(GlobalData.loggedUser.getEmail()).child("goal");
        goalRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    if(snapshot.child("bmi").getValue() != null){
                        edBmi.setText(snapshot.child("bmi").getValue().toString());
                    }
                    if(snapshot.child("energyIntake").getValue()!=null){
                        edCaloryIntake.setText(snapshot.child("energyIntake").getValue().toString());
                    }
                    if(snapshot.child("energyOuput").getValue()!=null) {
                        edCaloryOutput.setText(snapshot.child("energyOuput").getValue().toString());
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

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