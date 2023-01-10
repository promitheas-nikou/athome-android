package com.example.athome.activities;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.athome.R;
import com.example.athome.databinding.ActivitySettingsBinding;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import java.io.File;

public class SettingsActivity  extends AppCompatActivity {
    private AppBarConfiguration mAppBarConfiguration;
    private ActivitySettingsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySettingsBinding.inflate(getLayoutInflater());
        View root = binding.getRoot();
        Button b = (Button)root.findViewById(R.id.settings_invalidate_manifest_button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                File outputDir = MainActivity.GetSingletonInstance().getCacheDir();
                File manifestFile = new File(outputDir, "manifest.json");
                if(manifestFile.exists())
                    manifestFile.delete();
            }
        });
        setContentView(root);
    }

}
