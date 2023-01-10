package com.example.athome.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ui.AppBarConfiguration;

import com.example.athome.R;
import com.example.athome.data.NetworkWorker;
import com.example.athome.databinding.ActivityBookNewHairdresserArrangementBinding;
import com.google.android.material.textfield.TextInputEditText;

public class BookNewHairdresserArrangementActivity extends AppCompatActivity {
    private AppBarConfiguration mAppBarConfiguration;
    private ActivityBookNewHairdresserArrangementBinding binding;

    private int maxPriceCents = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityBookNewHairdresserArrangementBinding.inflate(getLayoutInflater());
        View root = binding.getRoot();

        TextView nameText = root.findViewById(R.id.book_new_arrangement_name_lookup_option_text);
        TextView priceText = root.findViewById(R.id.book_new_arrangement_max_price_textview);
        EditText nameTextIn = root.findViewById(R.id.book_new_arrangement_name_lookup_option_plaintextin);
        SeekBar priceSeekbar = root.findViewById(R.id.book_new_arrangement_max_price_seekbar);
        Button findResultsButton = root.findViewById(R.id.book_new_arrangement_find_results_button);
        priceSeekbar.setMin(0);
        priceSeekbar.setMax(5000);
        priceSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                maxPriceCents = i;
                priceText.setText(String.format("Maximum Price: %.2f",i/100.f));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        priceSeekbar.setProgress(1000);

        findResultsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NetworkWorker.Do();
            }
        });

        setContentView(root);
    }

}
