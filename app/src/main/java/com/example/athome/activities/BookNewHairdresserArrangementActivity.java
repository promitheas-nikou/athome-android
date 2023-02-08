package com.example.athome.activities;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ui.AppBarConfiguration;

import com.example.athome.R;
import com.example.athome.data.HairdresserArrangementSearchResultEntry;
import com.example.athome.data.HairdresserLookupParameters;
import com.example.athome.data.HairdresserProfessionalDescriptionData;
import com.example.athome.data.HairdresserProfessionalsList;
import com.example.athome.data.HairdresserServices;
import com.example.athome.data.NetworkWorker;
import com.example.athome.databinding.ActivityBookNewHairdresserArrangementBinding;

import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookNewHairdresserArrangementActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {
    private AppBarConfiguration mAppBarConfiguration;
    private ActivityBookNewHairdresserArrangementBinding binding;
    private TextView dateText;
    public static List<HairdresserArrangementSearchResultEntry> resultEntries;
    private int maxPriceCents = 100;
    public Date selectedDate;

    private int day, month, year, hour, minute;
    private int myDay, myMonth, myYear, myHour, myMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityBookNewHairdresserArrangementBinding.inflate(getLayoutInflater());
        View root = binding.getRoot();

        selectedDate = Calendar.getInstance().getTime();

        TextView nameText = root.findViewById(R.id.book_new_arrangement_name_lookup_option_text);
        TextView priceText = root.findViewById(R.id.book_new_arrangement_max_price_textview);
        dateText = root.findViewById(R.id.book_new_arrangement_selected_date_textview);
        dateText.setText("Selected Date: "+ selectedDate.toLocaleString());
        EditText nameTextIn = root.findViewById(R.id.book_new_arrangement_name_lookup_option_plaintextin);
        SeekBar priceSeekbar = root.findViewById(R.id.book_new_arrangement_max_price_seekbar);
        Button findResultsButton = root.findViewById(R.id.book_new_arrangement_find_results_button);
        Button selectDateButton = root.findViewById(R.id.book_new_arrangement_select_date_button);
        priceSeekbar.setMin(0);
        priceSeekbar.setMax(20000);
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
                int male_haircut = Integer.parseInt(((EditText)root.findViewById(R.id.book_new_arrangement_num_in_count_male_haircut)).getText().toString());
                int female_haircut = Integer.parseInt(((EditText)root.findViewById(R.id.book_new_arrangement_num_in_count_female_haircut)).getText().toString());
                int child_haircut = Integer.parseInt(((EditText)root.findViewById(R.id.book_new_arrangement_num_in_count_child_haircut)).getText().toString());
                int hair_coloring = Integer.parseInt(((EditText)root.findViewById(R.id.book_new_arrangement_num_in_count_hair_coloring)).getText().toString());
                int hair_highlight = Integer.parseInt(((EditText)root.findViewById(R.id.book_new_arrangement_num_in_count_hair_highlight)).getText().toString());
                int hair_attachment = Integer.parseInt(((EditText)root.findViewById(R.id.book_new_arrangement_num_in_count_hair_attachment)).getText().toString());

                Map<HairdresserServices, Integer> services = new HashMap<>();
                services.put(HairdresserServices.MALE_HAIRCUT, male_haircut);
                services.put(HairdresserServices.FEMALE_HAIRCUT, female_haircut);
                services.put(HairdresserServices.CHILD_HAIRCUT, child_haircut);
                services.put(HairdresserServices.HAIR_COLORING, hair_coloring);
                services.put(HairdresserServices.HAIR_HIGHLIGHT, hair_highlight);
                services.put(HairdresserServices.HAIR_ATTACHMENT, hair_attachment);

                //resultEntries = NetworkWorker.LookupPossibleHairdresserArrangements(new HairdresserLookupParameters(myYear, myMonth, myDay, myHour*2+myMinute/30, maxPriceCents, services));
                resultEntries = new ArrayList<>();
                resultEntries.add(new HairdresserArrangementSearchResultEntry("Μαρία Παπαδημητρίου", "id1024", 6402));
                resultEntries.add(new HairdresserArrangementSearchResultEntry("Αντώνης Παπαδόπουλος", "id4264", 5829));
                resultEntries.add(new HairdresserArrangementSearchResultEntry("Αθανάσιος Καραπέτρου", "id4564", 6200));
                resultEntries.add(new HairdresserArrangementSearchResultEntry("Δήμητρα Θεοδωρίδου", "id2354", 5944));
                resultEntries.sort(new Comparator<HairdresserArrangementSearchResultEntry>() {
                    @Override
                    public int compare(HairdresserArrangementSearchResultEntry t1, HairdresserArrangementSearchResultEntry t2) {
                        return t1.getTotalCost()-t2.getTotalCost();
                    }
                });
                BookNewHairdresserArrangementActivity.this.startActivity(new Intent(BookNewHairdresserArrangementActivity.this, BookNewArrangementResultsViewActivity.class));
            }
        });

        selectDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(BookNewHairdresserArrangementActivity.this, BookNewHairdresserArrangementActivity.this ,year, month,day);
                datePickerDialog.show();
            }
        });

        setContentView(root);
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
        myYear = year;
        myDay = day;
        myMonth = month;
        Calendar c = Calendar.getInstance();
        hour = c.get(Calendar.HOUR);
        minute = c.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(BookNewHairdresserArrangementActivity.this, BookNewHairdresserArrangementActivity.this, hour, minute, DateFormat.is24HourFormat(this));
        timePickerDialog.show();
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
        myHour = hourOfDay;
        myMinute = minute;
        Calendar c = new GregorianCalendar();
        c.set(myYear,myMonth,myDay,myHour,myMinute,0);
        selectedDate = c.getTime();
        dateText.setText("Selected Date: "+ selectedDate.toLocaleString());
    }
}
