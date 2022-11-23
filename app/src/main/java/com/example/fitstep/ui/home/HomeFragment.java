package com.example.fitstep.ui.home;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.fitstep.R;
import com.example.fitstep.databinding.FragmentHomeBinding;
import com.example.fitstep.models.User;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class HomeFragment extends Fragment {
    private View view;
    private User user;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_home, container, false);
        TextView tvDate = view.findViewById(R.id.tvDate);
        String today = DateFormat.getDateInstance().format(Calendar.getInstance().getTime());
        tvDate.setText("Today: " + today );

        TextView tvUserBmi = view.findViewById(R.id.tvUserBmi);
        tvUserBmi.setText(String.valueOf(user.getBodyMesurementAtDate(today).BMI()));

        setProgressCaloryIntake(50);
        setProgressCaloryOutput(50);



        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
    private void setProgressCaloryIntake(int prg){
        ProgressBar prgCaloryIntake = view.findViewById(R.id.prbCaloryIntake);
        prgCaloryIntake.setProgress(prg);
        TextView txtCaloryIntake = view.findViewById(R.id.tvCaloryIntake);
        txtCaloryIntake.setText(prg + " %");
    }
    private void setProgressCaloryOutput(int prg){
        ProgressBar prgCaloryOutput = view.findViewById(R.id.prbCaloryOutput);
        prgCaloryOutput.setProgress(prg);
        TextView txtCaloryOutput = view.findViewById(R.id.tvCaloryOutput);
        txtCaloryOutput.setText(prg + " %");
    }
}