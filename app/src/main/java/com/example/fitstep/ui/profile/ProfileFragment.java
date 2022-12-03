package com.example.fitstep.ui.profile;

import static android.app.Activity.RESULT_OK;
import java.lang.Object;
import java.util.UUID;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fitstep.R;
import com.example.fitstep.models.GlobalData;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class ProfileFragment extends Fragment {
    private View view;
    public static ProfileFragment newInstance() {
        return new ProfileFragment();
    }
    EditText edName, edEmail, edPassword, edRepeatedPassword;
    ImageView imgUser;
    Button btnUploadPicture, btnSave;
    StorageReference storageReference, sRef;
    ActivityResultLauncher actRest;
    Uri filepath;

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
            view = inflater.inflate(R.layout.fragment_profile, container, false);
            edName = view.findViewById(R.id.edName);
            edPassword = view.findViewById(R.id.edPassword);
            edEmail = view.findViewById(R.id.edEmail);
            edRepeatedPassword = view.findViewById(R.id.edRepeatedPassword);
            imgUser = view.findViewById(R.id.imgUser);
            if(GlobalData.loggedUser != null){
                edName.setText(GlobalData.loggedUser.getName());
                edEmail.setText(GlobalData.loggedUser.getEmail());
                edPassword.setText(GlobalData.loggedUser.getPassword());
                edRepeatedPassword.setText(GlobalData.loggedUser.getPassword());
                if(GlobalData.loggedUser.getUrlProfilePicture() != ""){
                    Picasso.with(view.getContext()).load(GlobalData.loggedUser.getUrlProfilePicture()).placeholder(R.drawable.rounding_image).into(imgUser);
                }
            }
            btnUploadPicture = view.findViewById(R.id.btnUploadPicture);
            btnUploadPicture.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    selectPhoto();
                }
            });

            btnSave = view.findViewById(R.id.btnSave);
            btnSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    saveUser();
                }
            });


        storageReference = FirebaseStorage.getInstance().getReference();

        actRest = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                            filepath = result.getData().getData();
                            try {
                                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(),filepath);
                                imgUser.setImageBitmap(bitmap);
                            } catch (Exception ex) {
                                Toast.makeText(ProfileFragment.newInstance().getContext(), ex.getMessage(),Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                }

        );
            return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
    private void selectPhoto(){
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        actRest.launch(Intent.createChooser(i, "Please select a photo"));
    }
    private void saveUser(){
        if(filepath != null
                && edPassword.getText().toString().equals(edRepeatedPassword.getText().toString())
                && edName.getText().toString() != null){
            String name = edName.getText().toString();
            String password = edPassword.getText().toString();
            sRef = storageReference.child("images/"+ UUID.randomUUID());
            sRef.putFile(filepath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    sRef.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                        @Override
                        public void onComplete(@NonNull Task<Uri> task) {
                            String url = task.getResult().toString();
                            GlobalData.loggedUser.setUrlProfilePicture(url);
                            GlobalData.loggedUser.setName(name);
                            GlobalData.loggedUser.setPassword(password);
                            GlobalData.userDatabase.child(GlobalData.loggedUser.getEmail()).setValue(GlobalData.loggedUser);
                        }
                    });
                }
            });
            sRef.putFile(filepath).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                }
            });
        }

    }
}