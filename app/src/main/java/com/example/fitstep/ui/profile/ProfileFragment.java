package com.example.fitstep.ui.profile;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.fitstep.R;

public class ProfileFragment extends Fragment {



    public static ProfileFragment newInstance() {
        return new ProfileFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
//        ProfileViewModel profileViewModel = new ViewModelProvider(this).get(ProfileViewModel.class);
//
//        binding = FragmentSlideshowBinding.inflate(inflater,container,false);
//        View root = binding.getRoot();
//
//        final TextView textView = binding.textSlideshow;
//        profileViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
//        return root;
          return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

}