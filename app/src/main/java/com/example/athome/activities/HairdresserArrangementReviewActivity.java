package com.example.athome.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ui.AppBarConfiguration;

import com.example.athome.R;
import com.example.athome.data.ArrangementData;
import com.example.athome.data.ArrangementsList;
import com.example.athome.databinding.ActivityHairdresserArrangementReviewBinding;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.TemporalField;

public class HairdresserArrangementReviewActivity extends AppCompatActivity {
    private AppBarConfiguration mAppBarConfiguration;
    private ActivityHairdresserArrangementReviewBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle b = getIntent().getExtras();
        if(b==null)
            finish();
        int aId = b.getInt("arrangementID");
        ArrangementData aData = ArrangementsList.getArrangementData(aId);

        binding = ActivityHairdresserArrangementReviewBinding.inflate(getLayoutInflater());
        View root = binding.getRoot();
        TextView nameText = root.findViewById(R.id.hairdresser_arrangement_name_text);
        TextView priceText = root.findViewById(R.id.hairdresser_arrangement_price_text);
        TextView timeText = root.findViewById(R.id.hairdresser_arrangement_time_text);
        nameText.setText(String.format("%s: \"%s\"",getString(R.string.general_text_name_label) ,aData.getTitle()));
        int c = aData.getCost();
        priceText.setText(String.format("%s: %d.%02d€",getString(R.string.general_text_price_label), c/100,c%100));
        LocalDateTime t = aData.getTime();
        timeText.setText(String.format("%s: %d:%02d | %s: %d-%d-%d",getString(R.string.general_text_time_label),t.getHour(),t.getMinute(),getString(R.string.general_text_date_label), t.getDayOfMonth(), t.getMonth().ordinal()+1, t.getYear()));
        setContentView(root);
    }

}
