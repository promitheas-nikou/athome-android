package com.example.athome.activities;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.app.Fragment;
import androidx.navigation.ui.AppBarConfiguration;

import com.example.athome.R;
import com.example.athome.data.HairdresserArrangementSearchResultEntry;
import com.example.athome.data.ProfessionalSuggestionData;
import com.example.athome.databinding.ActivityBookNewHairdresserArrangementResultsViewBinding;
import com.example.athome.databinding.ActivitySettingsBinding;
import com.example.athome.ui.arrangements.ArrangementsFragment;
import com.google.android.material.snackbar.Snackbar;

import java.io.File;

public class BookNewArrangementResultsViewActivity extends AppCompatActivity {
    private AppBarConfiguration mAppBarConfiguration;
    private ActivityBookNewHairdresserArrangementResultsViewBinding binding;
    private LinearLayout cardContainerLayout;

    private void AddCardView(HairdresserArrangementSearchResultEntry data) {
        CardView cardView = (CardView) LayoutInflater.from(this).inflate(R.layout.professional_suggestion_card_layout, cardContainerLayout, false);
        TextView titleTextView = cardView.findViewById(R.id.card_title_text);
        titleTextView.setText(Html.fromHtml(data.getName()));
        TextView priceTextView = cardView.findViewById(R.id.card_price_text);
        priceTextView.setText(Html.fromHtml(String.format("%.2f€",data.getTotalCost()/100.f)));
        TextView typeTextView = cardView.findViewById(R.id.card_type_text);
        typeTextView.setText(Html.fromHtml("HAIRDRESSER"));
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle b = new Bundle();
                b.putString("name",data.getName());
                b.putInt("cost",data.getTotalCost());
                b.putString("time","16:30");
                Intent i = new Intent(BookNewArrangementResultsViewActivity.this, BookNewHairdresserArrangementFinalizationActivity.class);
                instance = BookNewArrangementResultsViewActivity.this;
                i.putExtras(b);
                BookNewArrangementResultsViewActivity.this.startActivity(i);
            }
        });
        cardContainerLayout.addView(cardView);
        ImageView imageView = cardView.findViewById(R.id.card_professional_thumbnail_image);
        imageView.setImageDrawable(getResources().getDrawable(R.drawable.logo));
    }

    private View root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityBookNewHairdresserArrangementResultsViewBinding.inflate(getLayoutInflater());
        root = binding.getRoot();
        cardContainerLayout = root.findViewById(R.id.book_new_arrangement_results_review_linear_layout);

        for(HairdresserArrangementSearchResultEntry e: BookNewHairdresserArrangementActivity.resultEntries) {
            AddCardView(e);
        }

        setContentView(root);
    }

    public static BookNewArrangementResultsViewActivity instance;

    void ShowSuccessfullyBookedSnackbar(int bookingID) {
        Snackbar snackbar = Snackbar.make(root,"Arrangement Successfully Booked!!!",Snackbar.LENGTH_SHORT);
        snackbar.setAction("VIEW", new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                BookNewArrangementResultsViewActivity.this.finish();
                Intent i = new Intent(BookNewArrangementResultsViewActivity.this, HairdresserArrangementReviewActivity.class);
                Bundle b = new Bundle();
                b.putInt("arrangementID", bookingID);
                i.putExtras(b);
                startActivity(i);
            }

        });
        snackbar.show();
    }

}
