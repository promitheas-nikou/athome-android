package com.example.athome.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ui.AppBarConfiguration;

import com.example.athome.R;
import com.example.athome.data.ArrangementData;
import com.example.athome.data.ArrangementsList;
import com.example.athome.data.ProfessionalType;
import com.example.athome.databinding.ActivityBookNewHairdresserArrangementFinalizationBinding;
import com.example.athome.ui.arrangements.ArrangementLookupOption;
import com.google.android.material.snackbar.Snackbar;

import java.time.LocalDateTime;
import java.util.Collections;

public class BookNewHairdresserArrangementFinalizationActivity extends AppCompatActivity {
    private AppBarConfiguration mAppBarConfiguration;
    private ActivityBookNewHairdresserArrangementFinalizationBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle b = getIntent().getExtras();
        if(b==null) {
            finish();
            return;
        }
        binding = ActivityBookNewHairdresserArrangementFinalizationBinding.inflate(getLayoutInflater());
        View root = binding.getRoot();
        TextView nameText = root.findViewById(R.id.hairdresser_arrangement_name_text);
        TextView priceText = root.findViewById(R.id.hairdresser_arrangement_price_text);
        TextView timeText = root.findViewById(R.id.hairdresser_arrangement_time_text);
        nameText.setText("NAME: \""+b.getString("name")+"\"");
        int cost = b.getInt("cost");
        priceText.setText("PRICE: "+String.format("%d.%02d€",cost/100,cost%100));
        timeText.setText("TIME: "+b.getString("time"));
        Button book = root.findViewById(R.id.hairdresser_arrangement_finalization_button);
        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int cost = b.getInt("cost");

                BookNewArrangementResultsViewActivity.instance.ShowSuccessfullyBookedSnackbar(
                        ArrangementsList.addArrangement(new ArrangementData(b.getString("name"), cost, ProfessionalType.HAIRDRESSER,
                        "HAIRDRESSER", ArrangementLookupOption.PENDING, Collections.emptyList(), LocalDateTime.now())));
                finish();
            }
        });
        setContentView(root);
    }

}
