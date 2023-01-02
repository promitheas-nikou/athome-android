package com.example.athome.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ui.AppBarConfiguration;

import com.example.athome.databinding.ActivityAboutBinding;
import com.example.athome.databinding.ActivityBookNewArrangementBinding;

public class BookNewArrangementActivity extends AppCompatActivity {
    private AppBarConfiguration mAppBarConfiguration;
    private ActivityBookNewArrangementBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityBookNewArrangementBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

}
