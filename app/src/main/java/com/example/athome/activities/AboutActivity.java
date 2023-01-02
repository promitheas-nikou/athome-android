package com.example.athome.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ui.AppBarConfiguration;

import com.example.athome.databinding.ActivityAboutBinding;
import com.example.athome.databinding.ActivitySettingsBinding;

public class AboutActivity extends AppCompatActivity {
    private AppBarConfiguration mAppBarConfiguration;
    private ActivityAboutBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAboutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

}
