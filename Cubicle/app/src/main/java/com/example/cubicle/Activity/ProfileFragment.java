package com.example.cubicle.Activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.cubicle.R;
import com.google.firebase.auth.FirebaseAuth;

public class ProfileFragment extends Fragment {

    private Button button;
    private ImageView profileImage;
    private FirebaseAuth fAuth;
    Uri pickedImageUri;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_profile, container, false);

        button = v.findViewById(R.id.logoutButton);
        profileImage = v.findViewById(R.id.profileImage);
        fAuth = FirebaseAuth.getInstance();

        ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result != null && result.getResultCode() == Activity.RESULT_OK){
                    pickedImageUri = result.getData().getData();
                    profileImage.setImageURI(pickedImageUri);
                }
                else {
                    Toast.makeText(getContext(), "No image selected.", Toast.LENGTH_LONG).show();
                }
            }
        });

        button.setOnClickListener(view -> {
            fAuth.signOut();
            Intent intent = new Intent(getActivity(), Login.class);
            startActivity(intent);
        });

        profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                    if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE)){
                        Toast.makeText(getContext(), "Please grant the permission.", Toast.LENGTH_LONG).show();
                    }
                    else {
                        ActivityCompat.requestPermissions(getActivity(), new String[]{ Manifest.permission.READ_EXTERNAL_STORAGE }, 1);
                    }
                }
                Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                activityResultLauncher.launch(galleryIntent);
            }
        });

        return v;
    }
}