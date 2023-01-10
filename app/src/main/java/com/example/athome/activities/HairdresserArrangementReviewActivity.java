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
        nameText.setText("NAME: \""+aData.getTitle()+"\"");
        priceText.setText("PRICE: "+aData.getCost());
        timeText.setText("TIME: "+aData.getTime().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        setContentView(root);
    }

}
